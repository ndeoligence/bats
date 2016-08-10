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
    private BatsDAO dao;

    /* ctor */
    public Server() {
        clientHandlers = new ArrayList<>();
        /* connect to dbms */
        try {
            System.out.println("Server::ctor >>\n\tConnecting to Database...");
            dao = new BatsDAO_dbImpl();
            if (dao != null)
                System.out.println("\tSuccess!");
            else
                throw new SQLException("dao returned null to server");
            /* server socket */
            serverSocket = new ServerSocket(SERVER_PORT_NR);
        } catch (SQLException e) {
            System.err
                    .println("Server::ctor >>\n\tFailed connecting to the Database.\n\t: "
                            + e + "\n\tServer will stop!");
            System.exit(-1);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        /* Start: Temporal Test Code */
//        tempTesta_db();
        /* End: Temporal Test Code */
    }

    /* Methods */
    public ClientHandler newClientHandler() throws IOException {
        ClientHandler handler = new ClientHandler(serverSocket.accept());
        clientHandlers.add(handler);
        System.out.println("Server::newClientHandler >> Connected to "
                + handler.getClientAlias());
        return handler;
    }

    /* Nested classes */
    class ClientHandler extends Thread {
        Socket socket;
        ObjectInputStream ins = null;
        ObjectOutputStream outs = null;
        String sourceId;

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
                        .println(getHandlerAlias()+"::run >>\n\tFailed to connect client-server I/O streams.");
            }
            /* handle individual connection */
            try {
                Object object;
                while (true) {
                    object = ins.readObject();
                    processAction((Action) object);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(getHandlerAlias()+"::run >>\n"+
                        "\tError reading action from client.\n\t" +
                        "Error: " + e);
            } catch (SessionTerminationException e) {
                // This is after the session termination.
                System.out.println(getHandlerAlias()+"::run >>" +
                        "\n\tStopping. : "+e);
                return;
            } finally {
                try {
                    if (socket.isConnected())
                        socket.close();
                } catch (IOException e) {
                    System.err.println(getHandlerAlias()+"::run >>\n\tFailed to close socket");
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
            System.out.println(getHandlerAlias()+"::processAction >>" +
                    "\n\taction:\t" + action);
            if (action == null) { // make sure action isn't null
                System.err.println(getHandlerAlias()+"::processAction >>\n\t"
                        + "Null action received from client.\n\tWriting back Null");
                sendToClient(null);
                return;
            }
            /* First check for session termination */
            if (action instanceof SessionTermination) {
                System.err.println(getHandlerAlias()+"::processAction >>\n"
                        + "\tHonoring session termination request from client");
                if (terminateSession()) {
                    System.out.println("\tSuccess!");
                    throw new SessionTerminationException("Successful");
                } else {
                    System.out.println("\tFailed.");
                    throw new SessionTerminationException("Failure");
                }
            }
            /* Then check for other actions */
            // card active/inactive
            if (action instanceof CardDeactivation) {
                processCardDeactivation((CardDeactivation) action);
            }
            if (action instanceof PINChange) {
                processPinChange((PINChange)action);
            }
            // Action: Card Retrieve
            if (action instanceof CardRetrieval) {
                processCardRetrieval((CardRetrieval) action);
            } else if (action instanceof AccountRetrieval) {
                processAccountRetrieval((AccountRetrieval) action);
            } else if (action instanceof AccountHolderRetrieval) {
                processAccountHolderRetrieval((AccountHolderRetrieval) action);
            } else if (action instanceof TellerAction) {
                processTellerAction((TellerAction) action);
            } else if (action instanceof Transaction) {
                processTransaction((Transaction) action);
            } else {
                System.out.println(getHandlerAlias()+"::processAction >>" +
                        "\n\tUnsupported action type: "
                        + action);
            }
        }

        private boolean processAccountHolderRetrieval(AccountHolderRetrieval action) {
            if (action instanceof AccountHolderRetrievalByIdNo) {
                return processAccountHolderRetrievalByIdNo((AccountHolderRetrievalByIdNo) action);
            } else if (action instanceof AccountHolderRetrievalByCardNo) {
                return processAccountHolderRetrievalByCardNo((AccountHolderRetrievalByCardNo) action);
            } else if (action instanceof AccountHolderRetrievalByAccountNo) {
                return processAccountHolderRetrievalByAccountNo((AccountHolderRetrievalByAccountNo) action);
            } else {
                System.out.println(getHandlerAlias()+"::processAccountHolderRetrieval >>" +
                        "\n\tUnsupported AccountHolderRetrieval type: " + action);
                return sendToClient(null);
            }
        }

        private boolean processAccountHolderRetrievalByAccountNo(AccountHolderRetrievalByAccountNo action) {
            if (action.getAccountNo() == null) {
                System.out.println("ClientHandler::processAccountHolderRetrievalByAccountNo >>" +
                        "\n\tError: Account holder accountNo (required) is null");
            }
            String accountNo = action.getAccountNo();
            AccountHolder accountHolder = null;
            try {
                accountHolder = dao.getAccountHolderByAccountNo(accountNo);
                String idNo = accountHolder.getIdNo();
                accountHolder.addCard(dao.getAccountHolderCardByIdNo(idNo));
                accountHolder.setAccounts((ArrayList<Account>) dao.getAccountsByIdNo(idNo));
                return sendToClient(accountHolder);
            } catch (SQLException | BadAccountTypeException e) {
                System.out.println("ClientHandler::processAccountHolderRetrievalByAccountNo >>" +
                        "\n\tError: " + e);
                if (sendToClient(accountHolder))
                    return (accountHolder != null);
                else return false;
            }

        }

        private boolean processAccountHolderRetrievalByCardNo(AccountHolderRetrievalByCardNo action) {
            if (action.getCardNo() == null) {
                System.out.println("ClientHandler::processAccountHolderRetrievalByCardNo >>" +
                        "\n\tError: Account holder cardNo  (required) is null");
            }
            String cardNo = action.getCardNo();
            AccountHolder accountHolder = null;
            try {
                accountHolder = dao.getAccountHolderByCardNo(cardNo);
                accountHolder.addCard(dao.getAccountHolderCardByCardNo(cardNo));
                accountHolder.setAccounts((ArrayList<Account>) dao.getAccountsByCardNo(cardNo));
                return sendToClient(accountHolder);
            } catch (SQLException | BadAccountTypeException e) {
                System.out.println("ClientHandler::processAccountHolderRetrievalByCardNo >>" +
                        "\n\tError: " + e);
                if (sendToClient(accountHolder))
                    return (accountHolder != null);
                else return false;
            }
        }

        private boolean processAccountHolderRetrievalByIdNo(AccountHolderRetrievalByIdNo action) {
            if (action.getIdNo() == null) {
                System.out.println("ClientHandler::processAccountHolderRetrievalByIdNo >>" +
                        "\n\tError: Account holder idNo  (required) is null");
            }
            String idNo = action.getIdNo();
            AccountHolder accountHolder = null;
            try {
                accountHolder = dao.getAccountHolderByIdNo(idNo);
                accountHolder.addCard(dao.getAccountHolderCardByIdNo(idNo));
                accountHolder.setAccounts((ArrayList<Account>) dao.getAccountsByIdNo(idNo));
                return sendToClient(accountHolder);
            } catch (SQLException | BadAccountTypeException e) {
                System.out.println("ClientHandler::processAccountHolderRetrievalByCardNo >>" +
                        "\n\tError: " + e);
                if (sendToClient(accountHolder))
                    return (accountHolder != null);
                else return false;
            }
        }

        private boolean processAccountRetrieval(AccountRetrieval action) {
            List<Account> accounts = null;
            try {
                if (action instanceof AccountRetrievalByAccountNo) {
                    accounts = new ArrayList<>();
                    accounts.add(dao.getAccountByAccountNo(((AccountRetrievalByAccountNo) action).getAccountNo()));
                } else if (action instanceof AccountRetrievalByIdNo) {
                    accounts = (dao.getAccountsByCardNo(((AccountRetrievalByIdNo) action).getIdNo()));
                } else if (action instanceof AccountRetrievalByCardNo) {
                    accounts = (dao.getAccountsByCardNo(((AccountRetrievalByCardNo) action).getCardNo()));
                } else {
                    System.err.println("ClientHandler::processAccountRetrieval() >>" +
                            "\n\tUnrecognized AccountRetrieval sub-action" +
                            "\n\tWill return empty list");
                    accounts = new ArrayList<>();
                }
                return sendToClient(accounts);
            } catch (SQLException | BadAccountTypeException e) {
                if (sendToClient(accounts))
                    return (accounts != null);
                else return false;
            }
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

        private boolean processCardRetrieval(CardRetrieval action) {
            Card card = null;
            String cardNo = action.getCardNo();
            System.out.println("ClientHandler::processCardRetrieval >>" +
                    "\n\tcard#: " + cardNo + ", sourceId: " + action.getSourceId());

            try {
                if (cardNo.length() == AdminCard.CARD_NO_LEN) { // admin card
                    card = dao.getAdminCardByCardNo(cardNo);
                } else if (cardNo.length() == AccountHolderCard.CARD_NO_LEN) {

                    card = dao.getAccountHolderCardByCardNo(cardNo);

                } else {
                    System.err
                            .println("Error: Received a card with an invalid card# length: "
                                    + cardNo.length()
                                    + ".\nWill send write back: 'null'.");
                }
            } catch (SQLException e) {
                System.out.println("ClientHandler::processCardRetrieval >>" +
                        "\n\tError: " + e);
            }

            return sendToClient(card);
        }

        public boolean processTransaction(Transaction transaction) {
            if (transaction instanceof Withdrawal) {
                return sendToClient(processWithdrawal((Withdrawal) transaction));
            } else if (transaction instanceof Deposit) {
                return sendToClient(processDeposit((Deposit) transaction));
            } else if (transaction instanceof Transfer) {
                return sendToClient(processTransfer((Transfer) transaction));
            } else {
                System.out.println(getHandlerAlias()+"::processAction >>" +
                        "\n\tUnsupported Transaction type: "+transaction);
                return sendToClient(false);
            }
        }

        void processTellerAction(TellerAction action) {
            if (action.getEmployeeNo()==null) {
                sendToClient(false);
                return;
            }
            if (action instanceof AccountHolderCreation) {
                processAccountHolderCreation((AccountHolderCreation) action);
            } else if (action instanceof AccountCreation) {
                processAccountCreation((AccountCreation) action);
            } else if (action instanceof CardReactivation) {
                processCardReactivation((CardReactivation)action);
            } else if (action instanceof AccountClosure) {
                processAccountClosure((AccountClosure) action);
            }
        }
        void processAccountClosure(AccountClosure action) {
            if (action.getAccountNo()==null || action.getEmployeeNo()==null)
                sendToClient(false);
            else try {
                dao.closeAccount(action.getAccountNo(),action.getEmployeeNo());
            } catch (SQLException e) {
                System.out.println("ClientHandler::processAccountClosure() >>" +
                        "\n\tError: "+e);
            }
        }
        private void processPinChange(PINChange action) {
            try {
                if (action.getCardNo().length()==AdminCard.CARD_NO_LEN)
                    sendToClient(dao.setAdminPinNo(action.getCardNo(),action.getNewPIN()));
                else if (action.getCardNo().length()==AccountHolderCard.CARD_NO_LEN)
                    sendToClient(dao.setAccountHolderPinNo(action.getCardNo(),action.getNewPIN()));
                else sendToClient(false);
            } catch (SQLException e) {
                System.out.println("ClientHandler::processCardDeactivation() >>" +
                        "Error: "+e);
                sendToClient(false);
            }
        }

        private void processCardDeactivation(CardDeactivation action) {
            try {
                if (action.getCardNo().length()==AdminCard.CARD_NO_LEN)
                    sendToClient(dao.blockAdminCard(action.getCardNo()));
                else if (action.getCardNo().length()==AccountHolderCard.CARD_NO_LEN)
                    sendToClient(dao.blockAccountHolderCard(action.getCardNo()));
                else sendToClient(false);
            } catch (SQLException e) {
                System.out.println("ClientHandler::processCardDeactivation() >>" +
                        "Error: "+e);
                sendToClient(false);
            }
        }
        private void processCardReactivation(CardReactivation action) {
            try {
                if (action.getCardNo().length()==AccountHolderCard.CARD_NO_LEN)
                    sendToClient(dao.unblockAccountHolderCard(action.getCardNo(),action.getEmployeeNo()));
                else if (action.getCardNo().length()==AdminCard.CARD_NO_LEN)
                    sendToClient(dao.unblockAdminCard(action.getCardNo(),action.getEmployeeNo()));
                else sendToClient(false);
            } catch (SQLException e) {
                System.out.println("ClientHandler::processCardDeactivation() >>" +
                        "Error: "+e);
                sendToClient(false);
            }
        }

        private void processAccountHolderCreation(AccountHolderCreation action) {
            System.out.println("ClientHandler::processAccountHolderCreation() >>" +
                    "\n\taction details:" +
                    "\n\t\t---" + action.getAccountHolder());
            if (action.getAccountHolder().getIdNo() == null || action.getAccountHolder().getAddress() == null || action.getAccountHolder().getContactNo() == null || action.getAccountHolder().getName() == null || action.getAccountHolder().getSurname() == null) {
                System.out.println("ClientHandler::processAccountHolderCreation() >>" +
                        "\n\tError: One of the required fields for account holder creation is null");
                sendToClient(false);
                return;
            }
            if (action.getAccountHolder().getCard()==null) {
                System.out.println("ClientHandler::processAccountHolderCreation() >>" +
                        "\n\tError: Missing card object : "+action.getAccountHolder().getCard());
                sendToClient(false);
                return;
            }
            AccountHolder newAccountHolder = action.getAccountHolder();
            String newPin=newAccountHolder.getCard().getPinNo();
            try {
                newAccountHolder.addCard(new AccountHolderCard(BankAccountIdGenerator.nextAccountHolderCardNo(dao.getLastAccountHolderCardNo()), newPin, true, newAccountHolder.getIdNo()));
                if (!dao.addAccountHolder(newAccountHolder, action.getEmployeeNo())) {
                    sendToClient(false);
                    return;
                }
                if (!dao.addAccountHolderCard((AccountHolderCard) newAccountHolder.getCard())) {
                    sendToClient(false);
                    return;
                }
                sendToClient(true);
            } catch (SQLException e) {
                System.out.println("ClientHandler::processAccountHolderCreation() >>" +
                        "\n\tError: Missing card object : null");
                sendToClient(false);
                return;
            }
        }

        private void processAccountCreation(AccountCreation action) {
            if (action.getNewAccount() == null) {
                System.out.println("ClientHandler::processAccountCreation() >>" +
                        "\n\tError: AccountCreation newAccount attribute is null!");
                sendToClient(false);
                return;
            }
            if (action.getNewAccount().getAccountHolderId() == null) {
                System.out.println("ClientHandler::processAccountCreation() >>" +
                        "\n\tError: One of the required fields for account creation is null");
                sendToClient(false);
                return;
            }
            Account newAccount = action.getNewAccount();
            Boolean flag = false;
            try {
                if (newAccount instanceof CurrentAccount) {
                    newAccount.setAccountNo(BankAccountIdGenerator.nextCurrentAccountNo(dao.getLastAccountNo()));
                    flag = dao.addCurrentAccount(action.getEmployeeNo(), (CurrentAccount) newAccount);
                } else if (newAccount instanceof SavingsAccount) {
                    flag = dao.addSavingsAccount(action.getEmployeeNo(), (SavingsAccount) newAccount);
                } else if (newAccount instanceof CreditCardAccount) {
                    System.out.println(getHandlerAlias()+"::processAccountCreation >>" +
                            "\n\tEmployee " + action.getEmployeeNo() +
                            " tried to add [unimplemented] credit card account." +
                            "\n\tRequest won't be processed.");
                } else {
                    System.err.println(getHandlerAlias()+"::processAccountCreation >>" +
                            "\n\tUnrecognized AccountCreation sub-type: " + action);
                }
            } catch (SQLException e) {
                System.out.println(getHandlerAlias()+"::processAccountCreation >>" +
                        "\n\tError: " + e);
            }
            sendToClient(flag);
        }

        synchronized private boolean sendToClient(Object obj) {
            System.out.println("ClientHandler --> " + getClientAlias() + " >> " + obj);
            try {
                outs.writeObject(obj);
                return true;
            } catch (IOException e) {
                System.err.println(getHandlerAlias()+"::sendToClient >>" +
                        "\n\tFailed writing " + obj);
                return false;
            }
        }

        /**
         * This is how the client is referred to by the program, whenever a term
         * more descriptive than 'client' is preferred.
         *
         * @return A descriptive name for the client specific to this handler
         */
        private String getClientAlias() {
            return socket.getRemoteSocketAddress().toString() + "[#" + sourceId + "]";
        }
        private String getHandlerAlias() {
            return "Server::ClientHandler{"+getClientAlias()+"}";
        }

        boolean processDeposit(Deposit deposit) {
            return false;
        }

        boolean processTransfer(Transfer transfer) {
            try {
                double charges = dao.calculateTransactionCharges(transfer);
                if (isTransactionPossible(transfer,charges)) {
                    if (dao.logTransaction(transfer)) {
                        if (dao.decrementAccountFunds(transfer.getPrimAccountNo(), (transfer.getAmount() + charges)))
                            return true;
                        else {
                            dao.removeLastTransaction();
                            return false;
                        }
                    } else {
                        System.out.println(getHandlerAlias()+"::processTransfer()>>" +
                                "\n\tTransaction couldn't be logged. Therefore transaction won't be done.");
                        return false;
                    }
                } else {
                    System.out.println(getHandlerAlias()+"::processTransfer()>>" +
                            "\n\tUnmet condition: isTransactionPossible");
                    return false;
                }
            } catch (Exception e) {
                System.out.println(getHandlerAlias()+"::processWithdrawal()>>" +
                        "\n\tError: "+e);
                return false;
            }
        }

        boolean processWithdrawal(Withdrawal withdrawal) {
            try {
                double charges = dao.calculateTransactionCharges(withdrawal);
                if (isTransactionPossible(withdrawal,charges)) {
                    if (dao.logTransaction(withdrawal))
                        if (dao.decrementAccountFunds(withdrawal.getPrimAccountNo(),(withdrawal.getAmount()+charges)))
                            return true;
                        else {
                            return false;
                        }
                    else {
                        System.out.println(getHandlerAlias() + "::processWithdrawal()>>" +
                                "\n\tTransaction couldn't be logged. Therefore transaction won't be done.");
                        return false;
                    }
                } else {
                    System.out.println(getHandlerAlias()+"::processWithdrawal()>>" +
                            "\n\tUnmet condition: isTransactionPossible");
                    return false;
                }
            } catch (Exception e) {
                System.out.println(getHandlerAlias()+"::processWithdrawal()>>" +
                        "\n\tError: "+e);
                return false;
            }
        }

        /**
         * @param transaction - the transaction to be validated
         * @return {@code true} if the transaction is possible, or {@code false} otherwise.
         */
        private boolean isTransactionPossible(Transaction transaction, double charges) throws SQLException, BadAccountTypeException, BadTransactionTypeException {
            try {
                Account account = dao.getAccountByAccountNo(transaction.getPrimAccountNo());
                if(!account.isActive()) return false;
                /*transaction dependant limits*/
                if (transaction instanceof Withdrawal) {
                    return isWithdrawalPossible((Withdrawal) transaction,account,charges);
                } else if (transaction instanceof Deposit) {
                    return isDepositPossible((Deposit) transaction, charges);
                } else if (transaction instanceof Transfer) {
                    Transfer transfer=(Transfer)transaction;
                    Account account2nd=dao.getAccountByAccountNo(transfer.getSecondaryAccountNo());
                    if (!account2nd.isActive()) return false;
                    return isTransferPossible(transfer,account,account2nd, charges);
                } else return false;
                /*account dependant limits*/
            } catch (SQLException|BadAccountTypeException|BadTransactionTypeException e) {
                System.out.println(getHandlerAlias()+"::isTransactionPossible >>" +
                        "\n\tError: "+e);
                throw e;
            }
        }
        boolean isWithdrawalPossible(Withdrawal withdrawal,Account account, double charges) throws SQLException, BadTransactionTypeException {
            double amount = withdrawal.getAmount();
            double newBalance=account.getBalance()-amount-charges;
            try {
                if ((amount + dao.getAccountTotalWithdrawnAmountToday(account.getAccountNo())) > account.getMaxWithdrawalPerDay())
                    return false;
            } catch (SQLException|BadTransactionTypeException e) {
                System.out.println(getHandlerAlias()+"::isWithdrawalPossible >>" +
                        "\n\tError: "+e);
                throw e;
            }

            if (account instanceof CurrentAccount) {
                if (newBalance < CurrentAccount.MIN_BALANCE) return false;
            } else if (account instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) account;
                if (newBalance < savingsAccount.MIN_BALANCE) return false;
                if (withdrawal instanceof Withdrawal) {
                    if (savingsAccount.getWithdrawalPending()) {

                        if (savingsAccount.getFundsAvailableDate().after(new java.util.Date()))
                            return false;
                    } else { // if no pending withdrawal, then the

                    }
                }
            } else if (account instanceof CreditCardAccount) {
                return false;
            }
            return true;

        }
        boolean isDepositPossible(Deposit deposit, double charges) {
            return (deposit.getAmount() >= Deposit.MIN_AMOUNT);
        }
        boolean isTransferPossible(Transfer transfer,Account primaryAccount, Account secondaryAccount, double charges) {
            /*todo : implement!*/
            double transferAmount=transfer.getAmount();
            double newBalance=primaryAccount.getBalance()-transferAmount-charges;
            if (transferAmount > primaryAccount.getMaxTransferPerDay())
                return false;
            if (primaryAccount instanceof CurrentAccount) {
                if (newBalance<CurrentAccount.MIN_BALANCE) return false;
            } else if (primaryAccount instanceof SavingsAccount) {
                SavingsAccount savingsAccount=(SavingsAccount)primaryAccount;
                if (newBalance<SavingsAccount.MIN_BALANCE) return false;
                if (savingsAccount.getWithdrawalPending()) {
                    if (savingsAccount.getPendingWithdrawalAmount()<transferAmount)
                        return false;
                } else { // At the moment, we can't really withdraw from Savings to a different account.
                    return false;
                }
            }
            return true;
        }
    }

    private void tempTesta_db() {

        System.out.println("****************Testing the database...****************");
        System.out.println("Test: ADD ACCOUNT HOLDER");

        try {
            String idNo = "7403051433965";
            System.out.println("\tGetting account holder by idNo: " + idNo);
            AccountHolder accountHolder = dao.getAccountHolderByIdNo(idNo);
            System.out.println("\tReceived:\t" + accountHolder);

            System.out.println("Test: CREATE ACCOUNT");
            System.out.println("Creating an account for " + accountHolder + "...");
//            AccountHolderCard card = new AccountHolderCard(BankAccountIdGenerator.nextAccountHolderCardNo(dao.getLastAccountHolderCardNo()), "1234", true, accountHolder.getIdNo());
//            dao.addAccountHolderCard(card);

            System.out.println("Test: GET CARD");
            String cardNo = "2147483647";
            System.out.println("Getting card from database (card # = "
                    + cardNo + ")");
            AccountHolderCard card = dao.getAccountHolderCardByCardNo(cardNo);
            System.out.println("Server >>\n\tReceived card:" + card);
            System.out.println("Test: GET ACCOUNT HOLDER");
            System.out.println("Asking for account holder from database (id # = "
                    + card.getAccountHolderIdNo() + ")");
            System.out.println("Getting account holder from DB, by idNo: " + card.getAccountHolderIdNo());
            accountHolder = dao.getAccountHolderByIdNo(card.getAccountHolderIdNo());
            System.out.println("Server >>\n\tReceived account holder:"
                    + accountHolder);
        } catch (SQLException | BadAccountTypeException e) {
            System.out.println("Server::tempTesta_db() >>" +
                    "\n\tError: " + e);
        }
        System.out.println("*******************************************************");
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            System.out.println("Server::main >>\n\tServer started." +
                    "\n\tWaiting for connections...");
            while (true) {
                (server.newClientHandler()).start();
            }
        } catch (IOException e) {
            System.out.println("Server::main >>" +
                    "\n\tError: Server encountered an error." +
                    "\n\tTry restarting the program");
            System.out.println("\tError: "+e);
            System.out.flush();
            System.out.println("Server::main >>" +
                    "\n\tServer is stopping.");
            System.exit(1);
        }
    }
}
