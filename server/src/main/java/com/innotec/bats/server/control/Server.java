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
import com.innotec.bats.server.model.*;

public class Server {
    /* Static variables */
    private static final int SERVER_PORT_NR = 13700;
    /* Member variables */
    private ServerSocket serverSocket;
    private List<ClientHandler> clientHandlers;
    private DAO_Interface dao;

    /* ctor */
    public Server() throws IOException {
        clientHandlers = new ArrayList<>();
        /* connect to dbms */
        try {
            System.out.println("Server::ctor >>\n\tConnecting to Database...");
            dao = new DAO_Class();
            if (dao != null)
                System.out.println("\tSuccess!");
            else
                throw new SQLException("dao returned null to server");
        } catch (SQLException e) {
            System.err
                    .println("Server::ctor >>\n\tFailed connecting to the Database.\n\t: "
                            + e + "\n\tServer will stop!");
            System.exit(-1);
        }
        /* server socket */
        serverSocket = new ServerSocket(SERVER_PORT_NR);

        /* Start: DB Test Code */
//        System.out.println("Testing the database...");
//        System.out.println("Getting a card...");
//
//        String cardNo = "1234567890123450";
//        System.out.println("Asking for card from database (card # = "
//                + cardNo + ")");
//        AccountHolderCard card = dao.getAccountHolderCard(cardNo);
//        System.out.println("Server >>\n\tReceived card:" + card);
//        System.out
//                .println("Asking for account holder from database (id # = "
//                        + card.getAccountHolderIdNo() + ")");
//        AccountHolder accountHolder = dao.getAccountHolderByIdNo(card
//                .getAccountHolderIdNo());
//        System.out.println("Server >>\n\tReceived account holder:"
//                + accountHolder);
        /* End: DB Test Code */
    }

    /* Methods */
    public ClientHandler newClientHandler() throws IOException {
        ClientHandler handler = new ClientHandler(serverSocket.accept());
        clientHandlers.add(handler);
        System.out.println("Server::newClientHandler >> Connected to "
                + handler.socket.getLocalAddress());
        return handler;
    }

    /* Nested classes */
    class ClientHandler extends Thread {
        Socket socket;
        ObjectInputStream ins = null;
        ObjectOutputStream outs = null;

        /* ctor */
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        /* methods */
        @Override
        public void run() {
            /* connect streams */
            try {
                ins = new ObjectInputStream(socket.getInputStream());
                outs = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.err
                        .println("Server::ClientHandler::run >>\n\tFailed to connect client-server I/O streams.");
            }
            /* handle individual connection */
            try {
                Object object;
                while (true) {
                    object = ins.readObject();
                    processAction((Action) object);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Server::ClientHandler::run >>\n"
                        + "\tError reading action from client.\n\t" + e);
            } catch (SessionTerminationException e) {
                // This is after the session termination.
                System.out
                        .println("Server::ClientHandler::run >>\n\tClient handler stopping...");
                return;
                // The exception isn't an error. Fyi.
            } finally {
                try {
                    if (socket.isConnected())
                        socket.close();
                } catch (IOException e) {
                    System.err
                            .println("Server::ClientHandler::run >>\n\tFailed to close socket for"
                                    + socket.getLocalAddress());
                }
            }

        }

        /**
         * Processes the action given by client - either ATM or Teller. Calls
         * helper methods to handle specific action types, after narrowing down
         * the possible actions.
         *
         * @param action - the action to process
         * @throws SessionTerminationException if the action is of type SessionTermination.
         */
        private void processAction(Action action) throws SessionTerminationException {
            if (action == null) { // make sure action isn't null
                System.err.println("Server::ClientHandler::processAction >>\n\t"
                        + "Null action received.\n\tWriting back Null");
                sendToClient(null);
                return;
            }
            /* First check for session termination */
            if (action instanceof SessionTermination) {
                System.err.println("Server::ClientHandler::processAction >>\n"
                        + "\tHonoring session termination request from "
                        + getClientAlias());
                if (terminateSession()) {
                    System.out.println("\tSuccess!");
                    throw new SessionTerminationException("Session end success");
                } else {
                    System.out.println("\tFailed.");
                    throw new SessionTerminationException("Session end fail");
                }
            }
            /* Then check for other actions */
            // Action: Card Retrieve
            if (action instanceof CardRetrieval) {
                processCardRetrieval((CardRetrieval) action);
            } else if (action instanceof AccountRetrieval) {
                processAccountRetrieval((AccountRetrieval) action);
            } else if (action instanceof AccountHolderRetrieval) {
                processAccountHolderRetrieval((AccountHolderRetrieval) action);
            } else if (action instanceof TellerAction) {
                processTellerAction((TellerAction) action);
            } else 
				if (action instanceof Transaction)
				{
					processTransaction((Transaction)action);
				} else {
                System.out.println("Server::ClientHandler::processAction >>" +
                        "\n\tUnimplemented action handler: "
                        + action);
            }
        }
//        private void processAccountHolderCreation(AccountHolderCreation action) {
//            AccountHolder newAccountHolder = action.getAccountHolder();
//
//            Boolean flag = dao.addAccountHolder(newAccountHolder,action.getEmployeeNo());
//            sendToClient(flag);
//        }
//        private void processAccountCreation(AccountCreation action) {
//            Account newAccount = action.getNewAccount();
//            Boolean flag = false;
//            if (newAccount instanceof CurrentAccount) {
//                flag = dao.addCurrentAccount((CurrentAccount) newAccount,action.getEmployeeNo());
//            } else if (newAccount instanceof SavingsAccount) {
//                flag = dao.addSavingsAccount((SavingsAccount) newAccount,action.getEmployeeNo());
//            } else if (newAccount instanceof CreditCardAccount) {
//                System.out.println("Server::ClientHandler::processAction >>" +
//                        "\n\tEmployee " + action.getEmployeeNo() +
//                        " tried to add [unimplemented] credit card account." +
//                        "\n\tRequest won't be processed.");
//            } else {
//                System.err.println("Server::ClientHandler::processAction >>" +
//                        "\n\tUnrecognized AccountCreation sub-type: " + action);
//            }
//            sendToClient(flag);
//        }
        
		private boolean processAccountHolderRetrieval (AccountHolderRetrieval action)
		{
			AccountHolder accountHolder = null;
			ArrayList<Account>accounts = new ArrayList();
			AccountHolderCard card;

			if (action instanceof AccountHolderRetrievalByIdNo)
			{
				 accountHolder = dao.getAccountHolderByIdNo(((AccountHolderRetrievalByIdNo)action).getIdNo());
				 
			}
			else
				if (action instanceof AccountHolderRetrievalByCardNo)
				{
					 try
					{
						accountHolder = dao.getAccountHolderByCardNo(((AccountHolderRetrievalByCardNo)action).getCardNo());
						System.out.println("Accountholder retrieved in server method: " + accountHolder.toString());
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				else
					if (action instanceof AccountHolderRetrievalByAccountNo)
					{
						 accountHolder =dao.getAccountHolderByAccountNo(((AccountHolderRetrievalByAccountNo)action).getAccountNo());
					}
					
					card = dao.getAccountHolderCard(((AccountHolderRetrievalByCardNo)action).getCardNo());
					System.out.println("CardNo param for getAccounts method: " +((AccountHolderRetrievalByCardNo)action).getCardNo() );
					accounts = (ArrayList<Account>) dao.getAccounts(((AccountHolderRetrievalByCardNo)action).getCardNo()).clone();	
					System.out.println("Accounts from dao in Server method accHolderRet: " + accounts.toString());
					accountHolder.addCard(card);
					accountHolder.addAccountArrayList(accounts);
			 
			return sendToClient(accountHolder);
		}

        private void processAccountRetrieval(AccountRetrieval action) {
            List<Account> accounts = null;
            if (action instanceof AccountRetrievalByAccountNo) {
//                accounts = new ArrayList<>();
//                accounts.add(dao.getAccountByAccountNo(((AccountRetrievalByAccountNo) action).getAccountNo()));
            } else if (action instanceof AccountRetrievalByIdNo) {
//                accounts = (dao.getAccountsByIdNo(((AccountRetrievalByIdNo) action).getIdNo()));
            } else if (action instanceof AccountRetrievalByCardNo) {
//                accounts = (dao.getAccountsByCardNo(((AccountRetrievalByCardNo) action).getCardNo()));
            } else {
                System.err.println("ClientHandler::processAccountRetrieval() >>" +
                        "\n\tUnrecognized AccountRetrieval sub-action" +
                        "\n\tWill return empty list");
                accounts = new ArrayList<>();
            }

            sendToClient(accounts);
        }

        public boolean terminateSession() {
            System.out.println("ClientHandler::terminateSession() >>" +
                    "\n\tTerminating session...");
            try {
                if (socket.isConnected())
                    socket.close();
            } catch (IOException e) {
                System.err.println("ClientHandler::terminateSession() >>" +
                        "\n\tFailed closing socket");
            }
            return socket.isClosed();
        }

		private boolean processCardRetrieval (CardRetrieval action)
		{
			Card card = null;
			String cardNo = action.getCardNo();
			System.out.println("ClientHandler::processCardRetrieval (card#: "
					+ cardNo + ")");

			if (cardNo.length() == AdminCard.CARD_NO_LEN)
			{ // admin card
			 card = dao.getAdminCard(cardNo);
			}
			else
				if (cardNo.length() == AccountHolderCard.CARD_NO_LEN)
				{
					
						card = dao.getAccountHolderCard(cardNo);
					
				}
				else
				{
					System.err
							.println("Error: Received a card with an invalid card# length: "
									+ cardNo.length()
									+ ".\nWill send write back: 'null'.");
				}

			return sendToClient(card);
		}

		public boolean processTransaction(Transaction transaction)
		{
			if (transaction instanceof Withdrawal)
			{
				
					return this.sendToClient(dao.processWithdrawal((Withdrawal)transaction));
				
			}
			else 
				if (transaction instanceof Deposit)
				{
					
				}
				else
					if (transaction instanceof Transfer)
					{
						
					}
			return false;
		}
		
        void processTellerAction(TellerAction action) {
//            if (action instanceof AccountHolderCreation) {
//                processAccountHolderCreation((AccountHolderCreation) action);
//            } else if (action instanceof AccountCreation) {
//                processAccountCreation((AccountCreation) action);
//            }
        }

        synchronized private boolean sendToClient(Object obj) {
            System.out.println("ClientHandler -> " + getClientAlias() + " >> " + obj);
            try {
                outs.writeObject(obj);
                return true;
            } catch (IOException e) {
                System.err.println("Server::ClientHandler::sendToClient >>" +
                        "\n\tFailed writing " + obj);
                return false;
            }
        }

        /**
         * This is how the client is referred to by the program, whenever a term
         * more descriptive than 'client' is preferred.
         * @return A descriptive name for the client specific to this handler
         */
        private String getClientAlias() {
            return socket.getLocalAddress().toString();
        }
    }

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
