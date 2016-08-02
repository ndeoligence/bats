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
            System.out.println("Server::ctor >>\n\tConnecting to Database...");
            dao = new DAO_Class();
            if (dao!=null) System.out.println("\tSuccess!");
            else throw new SQLException("dao returned null to server");
        } catch (SQLException e) {
            System.err.println("Server::ctor >>\n\tFailed connecting to the Database.\n\t: " + e +
                    "\n\tServer will stop!");
            System.exit(-1);
        }
        /*server socket*/
        serverSocket = new ServerSocket(SERVER_PORT_NR);

        /*DB Test Code*/
        System.out.println("Testing the database...");
        System.out.println("Getting a card...");

        try {
            String cardNo = "1234567890123450";
            System.out.println("Asking for card from database (card # = " + cardNo + ")");
            AccountHolderCard card = dao.getAccountHolderCardByCardNo(cardNo);
            System.out.println("Server >>\n\tReceived card:" + card);
            System.out.println("Asking for account holder from database (id # = " +card.getAccountHolderIdNo()+")");
            AccountHolder accountHolder = dao.getAccountHolderByIdNo(card.getAccountHolderIdNo());
            System.out.println("Server >>\n\tReceived account holder:" + accountHolder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*Methods*/
    public ClientHandler newClientHandler() throws IOException {
        ClientHandler handler = new ClientHandler(serverSocket.accept());
        clientHandlers.add(handler);
        System.out.println("Server::newClientHandler >> Connected to "+handler.socket.getLocalAddress());
        return handler;
    }
    /*Nested classes*/
    class ClientHandler extends Thread {
        Socket socket;
        ObjectInputStream ins = null;
        ObjectOutputStream outs = null;
        /*ctor*/
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        /*methods*/
        @Override
        public void run() {
            /*connect streams*/
            try {
                ins = new ObjectInputStream(socket.getInputStream());
                outs = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.err.println("Server::ClientHandler::run >>\n\tFailed to connect client-server I/O streams.");
            }
            /*handle individual connection*/
            try {
                Object object;
                while (true) {
                    object = ins.readObject();
                    processAction((Action) object);
                }
            } catch (IOException|ClassNotFoundException e) {
                System.err.println("Server::ClientHandler::run >>\n" +
                        "\tError reading action from client.\n\t"+e);
            } catch (SessionTerminationException e) {
                // This is after the session termination.
                System.out.println("Server::ClientHandler::run >>\n\tClient handler stopping...");
                return;
                // The exception isn't an error. Fyi.
            } finally {
                try {
                    if (socket.isConnected()) socket.close();
                } catch (IOException e) {
                    System.err.println("Server::ClientHandler::run >>\n\tFailed to close socket for"+
                            socket.getLocalAddress());
                }
            }

        }

        /**
         * Processes the action given by client - either ATM or Teller.
         * Calls helper methods to handle specific action types, after narrowing down the possible actions.
         * @param action - the action to process
         * @throws SessionTerminationException
         */
        void processAction(Action action) throws SessionTerminationException {
            if (action==null) { // make sure action isn't null
                System.err.println("Server::ClientHandler::run >>\n\t" +
                        "Null action received.\n\tWriting back Null");
                sendToClient(null);
                return;
            }
            /*First check for session termination first*/
            if (action instanceof SessionTermination) {
                System.err.println("Server::ClientHandler::run >>\n"+
                        "\tHonoring session termination request from "+
                        socket.getLocalAddress());
                if (terminateSession()) {
                    System.out.println("\tSuccess!");
                    throw new SessionTerminationException("Session end success");
                } else {
                    System.out.println("\tFailed.");
                    throw new SessionTerminationException("Session end fail");
                }
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

        private boolean processAccountHolderRetrieval(AccountHolderRetrieval action) {
            AccountHolder accountHolder = null;

            if (action instanceof AccountHolderRetrievalByIdNo) {
//                accountHolder = dao.getAccountHolderByIdNo(((AccountHolderRetrievalByIdNo) action).getIdNo());
            } else if (action instanceof AccountHolderRetrievalByCardNo) {
//                accountHolder = dao.getAccountHolderByCardNo(((AccountHolderRetrievalByCardNo) action).getCardNo());
            } else if (action instanceof AccountHolderRetrievalByAccountNo) {
//                accountHolder = dao.getAccountHolderByAccountNo(((AccountHolderRetrievalByAccountNo) action).getAccountNo());
            }

            return sendToClient(accountHolder);
        }
        private void processAccountRetrieval(AccountRetrieval action) {
            List<Account> accounts = null;
            if (action instanceof AccountRetrievalByAccountNo) {
                accounts = new ArrayList<>();
//                accounts.add(dao.getAccount(((AccountRetrievalByAccountNo) action).getAccountNo()));
            } else if (action instanceof AccountRetrievalByIdNo) {
//                accounts = (dao.getAccountsByIdNo(((AccountRetrievalByIdNo) action).getIdNo()));
            } else if (action instanceof AccountRetrievalByCardNo) {
//                accounts = (dao.getAccountsByCardNo(((AccountRetrievalByCardNo) action).getCardNo()));
            } else {
                System.err.println("ClientHandler::processAccountRetrieval() >> Unrecognized AccountRetrieval sub-action"+
                                    "\n\tWill return empty list");
                accounts = new ArrayList<>();
            }

            sendToClient(accounts);
        }

        public boolean terminateSession() {
            System.out.println("ClientHandler::terminateSession() >>\n\tTerminating session...");
            try {
                if (socket.isConnected()) socket.close();
            } catch (IOException e) {
                System.err.println("ClientHandler::processAccountRetrieval() >>\n\tFailed closing socket");
            }
            return socket.isClosed();
        }
        void processCardRetrieval(CardRetrieval action) {
            Card card = null;
            String cardNo = action.getCardNo();
            System.out.println("ClientHandler::processCardRetrieval (card#: " + cardNo + ")");

            if (cardNo.length() == AdminCard.CARD_NO_LEN) { // admin card
                card = dao.getAdminCardByCardNo(cardNo);
            } else if (cardNo.length() == AccountHolderCard.CARD_NO_LEN) {
                card = dao.getAccountHolderCardByCardNo(cardNo);
            } else {
                System.err.println("Error: Received a card with an invalid card# length: "
                        + cardNo.length() + ".\nWill send write back: 'null'.");
            }

            sendToClient(card);
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
        synchronized private boolean sendToClient(Object obj) {
            System.out.println("ClientHandler -> Client >> " + obj);
            try {
                outs.writeObject(obj);
                return true;
            } catch (IOException e) {
                System.err.println("Server::ClientHandler::sendToClient >>\n\tFailed writing "+obj);
                return false;
            }
        }
    }

    /*Main method*/
    public static void main(String[] args) {

        try {
            Server server = new Server();
            System.out.println("Server started.");
            /*while (true) {
                System.out.println("Waiting for connection...");
                (server.newClientHandler()).start();
                System.out.println("New client found!");
            }*/

        } catch (IOException e) {
            System.err.println("Error starting server up and listening for connections.\nTry restarting the program");
            e.printStackTrace();
            System.out.println("Server is stopping.");
            System.exit(1);
        }
    }
}
