package com.innotec.bats.server.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.innotec.bats.general.*;
import com.innotec.bats.server.dao.*;
import com.innotec.bats.server.model.SessionTerminationException;

public class Server {
    /*Static variables*/
    private static final int SERVER_PORT_NR = 13700;
    /*Member variables*/
    private ServerSocket serverSocket;
    private List<ClientHandler> clientHandlers;
    private DAO_Interface dao;
    /*ctor*/
    public Server() throws IOException {
        clientHandlers = new ArrayList<>();
        /*connect to dbms*/
        try {
            dao = new BankDAO_Impl();
        } catch (SQLException e) {
            System.err.println("Error connecting to the DAO!!" + "");
        }
        /*server socket*/
        serverSocket = new ServerSocket(SERVER_PORT_NR);
    }
    /*Methods*/
    public ClientHandler newClientHandler() throws IOException {
        ClientHandler handler = new ClientHandler(serverSocket.accept());
        clientHandlers.add(handler);
        System.out.println("Connected to "+handler.socket.getLocalAddress());
        return handler;
    }
    /*Nested classes*/
    class ClientHandler extends Thread {
        Socket socket;
        ObjectInputStream ins = null;
        ObjectOutputStream outs = null;
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            /*connect streams*/
            try {
                ins = new ObjectInputStream(socket.getInputStream());
                outs = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.err.println("Error while trying to connect I/O streams.");
                e.printStackTrace();
            }
            /*handle individual connection*/
            try {
                Object object;
                while (true) {
                    object = ins.readObject();
                    processAction((Action) object);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SessionTerminationException e) {
                // This is after the session termination.
                // The exception isn't an error. Fyi.
            }

        }
        void processAction(Action action) throws SessionTerminationException {
            if (action==null) { // make sure action isn't null
                System.err.println("Null action received.\n\tWrite back 'Null' and retry...");
                try {
                    Object obj = null;
                    outs.writeObject(obj);
                } catch (IOException e) {
                    System.err.println("Error writing back Null");
                    e.printStackTrace();
                }
                return;
            }
            /*First check for session termination first*/
            if (action instanceof SessionTermination) {
                System.out.print("Connection termination: ");
                if (terminateSession()) {
                    System.out.println("Success!");
                } else {
                    System.out.println("Failed.");
                }
                throw new SessionTerminationException("session termination failure");
            }
            /*Then check for other actions*/
            // Action: Card Retrieve
            if (action instanceof CardRetrieval) {
                processCardRetrieval((CardRetrieval) action);
            } else if (action instanceof AccountRetrieval) {
                processAccountRetrieval((AccountRetrieval) action);
            } else if (action instanceof TellerAction) {
                processTellerAction((TellerAction) action);
            } else {
                System.out.println("Unimplemented action handler: " + action);
            }
        }

        private void processAccountRetrieval(AccountRetrieval action) {
            List<Account> accounts;
            if (action instanceof AccountRetrievalByAccountNo) {
                accounts = new ArrayList<>();
                accounts.add(dao.getAccount(((AccountRetrievalByAccountNo) action).getAccountNo()));
            } else if (action instanceof AccountRetrievalByIdNo) {
                accounts = (dao.getAccountsByIdNo(((AccountRetrievalByIdNo) action).getIdNo()));
            } else if (action instanceof AccountRetrievalByCardNo) {
                accounts = (dao.getAccountsByCardNo(((AccountRetrievalByCardNo) action).getCardNo()));
            } else {
                System.err.println("ClientHandler::processAccountRetrieval() >> Unrecognized AccountRetrieval sub-action"+
                                    "\n\tWill return empty list");
                accounts = new ArrayList<>();
            }

            try {
                outs.writeObject(accounts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean terminateSession() {
            System.out.println("Terminating session...");
            try {
                socket.close();
            } catch (IOException e) {
                return socket.isClosed();
            }
            return socket.isClosed();
        }
        void processCardRetrieval(CardRetrieval action) {
            Card card = null;
            String cardNo = action.getCardNo();
            System.out.println("ClientHandler::processCardRetrieval (card#: " + cardNo + ")");

            if (cardNo.length() == AdminCard.CARD_NO_LEN) { // admin card
                card = dao.getAdminCard(cardNo);
            } else if (cardNo.length() == AccountHolderCard.CARD_NO_LEN) {
                card = dao.getAccountHolderCard(cardNo);
            } else {
                System.err.println("Error: Received a card with an invalid length: "
                        + cardNo.length() + ".\nWill send 'null' back.");
            }

            try {
                outs.writeObject(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void processTellerAction(TellerAction action) {
            /*if (action instanceof AccountManagement) {
                if (action instanceof AccountCreation) {

                } else if (action instanceof CardReactivation) {

                } else if (action instanceof AccountHolderCreation) {

                }
            } else {

            }*/
        }
    }

    /*Main method*/
    public static void main(String[] args) {
        try {
            Server server = new Server();
            System.out.println("Server started.");
            while (true) {
                System.out.println("Waiting for connection...");
                (server.newClientHandler()).start();
                System.out.println("New client found!");
            }
        } catch (IOException e) {
            System.err.println("Error starting server up and listening for connections.\nTry restarting the program");
            e.printStackTrace();
            System.out.println("Server is stopping.");
            System.exit(1);
        }
    }
}
