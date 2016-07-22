package com.innotec.bats.server.control;

import com.innotec.bats.general.*;
import com.innotec.bats.general.Action;
import com.innotec.bats.server.model.SessionTerminationException;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    /*Static variables*/
    private static final int SERVER_PORT_NR = 13700;
    private static final int MAX_THREADS = 3000;
    /*Member variables*/
    private ServerSocket serverSocket;
    private List<ClientHandler> clientHandlers;
    /*Methods*/
    public Server() throws IOException {
        clientHandlers = new ArrayList<>();
        /*connect to dbms*/
        /*server socket*/
        serverSocket = new ServerSocket(SERVER_PORT_NR);
    }
    public Socket newClientSocket() throws IOException {
        return serverSocket.accept();
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
                JOptionPane.showMessageDialog(null, "Error while trying to connect I/O streams.",
                        "Client/Server Communication Error", JOptionPane.ERROR_MESSAGE);
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
            /*First check for session termination first*/
            if (action instanceof SessionTermination) {
                System.out.print("Connection termination: ");
                if (terminateSession()) {
                    System.out.println("Success!");
                } else {
                    System.out.println("Failed.");
                }
                throw new SessionTerminationException();
            }
            /*Then check for other actions*/
            // Action: Card Retrieve
            if (action instanceof CardRetrieval) {
                processCardRetrieval((CardRetrieval) action);
            } else if (action instanceof TellerAction) {
                processTellerAction((TellerAction) action);
            } else {
                System.out.println("Unimplemented action handler: " + action);
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
            if (action.getCardNo().length() == AdminCard.CARD_NO_LEN) {
                card = new AdminCard(action.getCardNo(),"1234",true,"12345678");
            } else if (action.getCardNo().length() == AdminCard.CARD_NO_LEN) {
                String accountNumber = "";
                card = new AccountHolderCard(action.getCardNo(),"1234",true);
                ((AccountHolderCard)card).addAccountNo(accountNumber);
            } else {
                System.out.println("Error: Received a card with an invalid length: "
                        + action.getCardNo().length() + ".\nWill send 'null' back.");

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
        /*void processAtmAction(ATMAction action) {
            if (action instanceof Transaction) {
                if (action instanceof innotec.bats.general_code.Withdrawal) {
                    System.out.println("Processing withdrawal...");
                } else if (action instanceof Deposit) {
                    System.out.println("Processing deposit...");
                } else if (action instanceof Transfer) {
                    System.out.println("Processing transfer...");
                }
            } else {

            }
        }*/
    }

    /*Main method*/
    public static void main(String[] args) {
        try {
            Server server = new Server();
            System.out.println("Server started.");
            while (true) {
                (server.new ClientHandler(server.newClientSocket())).start();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error starting server up and listening for connections.\nTry restarting the program",
                                            "Server Startup Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            // System.exit(1); because the server stops anyways.
        }

        System.out.println("Server Stopped.");
    }
}
