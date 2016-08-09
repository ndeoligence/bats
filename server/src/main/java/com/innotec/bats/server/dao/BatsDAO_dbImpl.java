package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;
import com.innotec.bats.server.model.*;
import com.sun.corba.se.impl.orb.PrefixParserAction;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatsDAO_dbImpl implements BatsDAO {
    /*constants*/
    private static final String SAVINGS_ACCOUNT = "Savings";
    private static final String CURRENT_ACCOUNT = "Current";
    private static final String CREDIT_ACCOUNT = "CreditCard";
    public static final String TRANSACTION_DEPOSIT="Deposit",
                                TRANSACTION_TRANSFER="Transfer",
                                TRANSACTION_WITHDRAWAL="Withdrawal";
    /*Prepared Statements*/
                                                    // Table: ID, Name, Surname, Address, ContactNumber, AccountHolderCardNo
    private static final String ADD_ACCOUNT_HOLDER = "INSERT INTO accountHolders VALUES (?,?,?,?,?,?);";
                                                // Table: EmployeeNo,ID,Name,Surname
    private static final String ADD_EMPLOYEE = "INSERT INTO employees VALUES(?,?,?,?);";
    // Tables: CardNo,PIN,Active,AccountHolderID
    private static final String ADD_ACCOUNT_HOLDER_CARD = "INSERT INTO accountHolderCards VALUES (?,?,?,?);";
    //Table: CardNo,Active,PIN,EmployeeNo
    private static final String ADD_ADMIN_CARD = "INSERT INTO adminCards VALUES (?,?,?,?);";
    private static final String GET_ACCOUNT_HOLDER_BY_ID_NO = "SELECT * FROM accountHolders WHERE ID=?;";
    private static final String GET_CARD_BY_CARD_NO = "SELECT * FROM accountHolderCards WHERE CardNo=?;";
    private static final String GET_ACCOUNT_TYPE_ID = "SELECT * FROM accountTypes WHERE AccountType=?;";
    // Table: AccountNo,Type,Balance,MinBalance,MaxTransferPerDay,MaxWithdrawalPerDay,Active,CardNo,WithdrawalPending
    private static final String CREATE_NEW_ACCOUNT = "INSERT INTO accounts VALUES (?,?,?,?,?,?,?,?,?);";
    private static final String ADD_ATM = "INSERT INTO atms VALUES (?,?);"; // Table : ID,Description

    private Connection connection;
    /* **********************************************************************************/
    public BatsDAO_dbImpl() throws SQLException, IOException, ClassNotFoundException {
        try {
            connection = new DBConnection().getConnection();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println("BatsDAO_dbImpl::ctor >>\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public String getLastAccountHolderCardNo() throws SQLException {
    	String sqlStr = "SELECT MAX(CardNo) AS LastCardNo FROM accountHolderCards;";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
        ResultSet resultSet=preparedStatement.executeQuery();
    	if (!resultSet.next())
    		return null;
        return resultSet.getString("LastCardNo");
    }

    @Override
    public boolean exist(Account account) throws SQLException, BadAccountTypeException {
        try {
            return (getAccountByAccountNo(account.getAccountNo()) != null);
        } catch (SQLException | BadAccountTypeException e) {
            System.out.println("BatsDAO_dbImpl::exist(account) >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean exist(AccountHolder accountHolder) throws SQLException, BadAccountTypeException {
        try {
            return (getAccountHolderByIdNo(accountHolder.getIdNo()) != null);
        } catch (SQLException | BadAccountTypeException e) {
            System.out.println("BatsDAO_dbImpl::exist(accountHolder) >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean exist(AccountHolderCard card) throws SQLException {
        try {
            return (getAccountHolderCardByCardNo(card.getCardNo()) != null);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::exist(card) >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean exist(AdminCard card) throws SQLException {
        try {
            return (getAdminCardByCardNo(card.getCardNo()) != null);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::exist(card) >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean logTransaction(Transaction transaction) throws SQLException, BadTransactionTypeException {
        try {
            if (transaction instanceof Withdrawal)
                return (logWithdrawal((Withdrawal) transaction));
            else if (transaction instanceof Transfer)
                return (logTransfer((Transfer) transaction));
            else if (transaction instanceof Deposit)
                return (logDeposit((Deposit) transaction));
            else throw new BadTransactionTypeException("Unsupported transaction: "+transaction);
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::logTransaction() >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public double calculateTransactionCharges(Transaction transaction) throws SQLException {
        // Table (transactionCharges) : TypeId,InitialCharge,PerUnitCharge,UnitAmount
        // Table (transactionTypes) : TypeNo,TransactionType
        try {
            String transType;
            if (transaction instanceof Withdrawal)
                transType = TRANSACTION_WITHDRAWAL;
            else if (transaction instanceof Deposit)
                transType = TRANSACTION_DEPOSIT;
            else if (transaction instanceof Transfer)
                transType = TRANSACTION_TRANSFER;
            else return Double.NaN;

            double standardCharge = getTransactionStandardCharge(transType),
                    perUnitCharge = getTransactionChargePerUnit(transType),
                    unitAmount = getTransactionChargeUnitAmount(transType);
            double amount = transaction.getAmount();
            return (amount + standardCharge + perUnitCharge * (Math.ceil((amount * 1.0) / unitAmount)));//because we charge a full amount for a fraction of a R100 as well
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::calculateTransactionCharges() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }
    private double getTransactionStandardCharge(final String transactionType) throws SQLException {
        String sqlStr="SELECT InitialCharge FROM transactionCharges,transactionTypes WHERE TypeId=TypeNo AND TransactionType=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1,transactionType);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) return Double.NaN;
            return resultSet.getDouble("InitialCharge");
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::getTransactionStandardCharge() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }
    private double getTransactionChargePerUnit(final String transactionType) throws SQLException {
        String sqlStr="SELECT PerUnitCharge FROM transactionCharges,transactionTypes WHERE TypeId=TypeNo AND TransactionType=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1,transactionType);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) return Double.NaN;
            return resultSet.getDouble("InitialCharge");
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::getTransactionChargePerUnit() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }
    private double getTransactionChargeUnitAmount(final String transactionType) throws SQLException {
        String sqlStr="SELECT UnitAmount FROM transactionCharges,transactionTypes WHERE TypeId=TypeNo AND TransactionType=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1,transactionType);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) return Double.NaN;
            return resultSet.getDouble("InitialCharge");
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::getTransactionChargeUnitAmount() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }
    @Override
    public String getLastAccountNo() throws SQLException {
        String sqlStr = "SELECT MAX(AccountNo) AS LastAccountNo FROM accounts;";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next())
            return null;
        return resultSet.getString("LastAccountNo");
    }

    @Override
    public boolean addEmployee(Employee newEmployee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_EMPLOYEE);
            preparedStatement.setString(1,newEmployee.getEmployeeNo());
            preparedStatement.setString(2,newEmployee.getIdNo());
            preparedStatement.setString(3,newEmployee.getName());
            preparedStatement.setString(4,newEmployee.getSurname());
            return executeUpdateStatement(preparedStatement)>0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addEmployee() >>" +
                    "\n\tError..." + e +
                    "\n\tArgument: "+newEmployee);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("BatsDAO_dbImpl::addEmployee() >>" +
                        "\n\tError trying to roll back after an error" +
                        "\n\tNew error: "+e1);
            }
            return false;
        }
    }
    @Override
    public boolean addAccountHolder(AccountHolder newAccountHolder, String sourceId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ACCOUNT_HOLDER);
            preparedStatement.setString(1,newAccountHolder.getIdNo());
            preparedStatement.setString(2,newAccountHolder.getName());
            preparedStatement.setString(3,newAccountHolder.getSurname());
            preparedStatement.setString(4,newAccountHolder.getAddress());
            preparedStatement.setString(5,newAccountHolder.getContactNo());
            preparedStatement.setString(6,newAccountHolder.getCard().getCardNo());
            return (executeUpdateStatement(preparedStatement) > 0);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addAccountHolder() >>" +
                    "\n\tError..." + e +
                    "\n\tArgument: "+newAccountHolder);
            return false;
        }
    }
    @Override
    public AccountHolder getAccountHolderByIdNo(String idNo) throws SQLException, BadAccountTypeException {
        PreparedStatement preparedStatement=connection.prepareStatement(GET_ACCOUNT_HOLDER_BY_ID_NO);
        preparedStatement.setString(1,idNo);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }

        String name,surname,address,contactNo;
        Card card;

        idNo=resultSet.getString("ID");
        name=resultSet.getString("Name");
        surname=resultSet.getString("Surname");
        address=resultSet.getString("Address");
        contactNo=resultSet.getString("ContactNumber");
        resultSet.close();

        card = getAccountHolderCardByCardNo(getCardNoByIdNo(idNo));
        AccountHolder accountHolder = new AccountHolder(name,surname,idNo,address,contactNo,card);
        accountHolder.setAccounts((ArrayList<Account>) getAccountsByCardNo(idNo));
        return accountHolder;
    }

    @Override
    public AccountHolder getAccountHolderByAccountNo(String accountNo) throws SQLException, BadAccountTypeException {
        return getAccountHolderByIdNo(getIdNoByAccountNo(accountNo));
    }

    private String getIdNoByAccountNo(String accountNo) throws SQLException {
        String cardNo=getCardNoByAccountNo(accountNo);
        return getIdNoByCardNo(cardNo);
    }

    private String getCardNoByAccountNo(String accountNo) throws SQLException {
        String sqlStr="SELECT CardNo FROM accounts WHERE AccountNo=?;";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
        preparedStatement.setString(1,accountNo);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        else return resultSet.getString("CardNo");
    }

    @Override
    public AccountHolder getAccountHolderByCardNo(String cardNo) throws SQLException, BadAccountTypeException {
        return getAccountHolderByIdNo(getCardNoByIdNo(cardNo));
    }
    @Override
    public List<Account> getAccountsByIdNo(String idNo) throws SQLException, BadAccountTypeException {
        return getAccountsByCardNo(getCardNoByIdNo(idNo));
    }
    @Override
    public List<Account> getAccountsByCardNo(String cardNo) throws SQLException, BadAccountTypeException {
        try {
            List<Account> accounts = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CARD_BY_CARD_NO);
            preparedStatement.setString(1, cardNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            String accountNo, idNo;
            double balance, maxWithdrawalPerDay, maxTransferPerDay;
            boolean active;
            while (resultSet.next()) {

                accountNo = resultSet.getString("AccountNo");
                idNo = getIdNoByCardNo(cardNo);
                balance = resultSet.getDouble("Balance");
                maxWithdrawalPerDay = resultSet.getDouble("MaxWithdrawalPerDay");
                maxTransferPerDay = resultSet.getDouble("MaxTransferPerDay");
                active = resultSet.getBoolean("Active");

                switch (resultSet.getString("Type")) {
                    case (CURRENT_ACCOUNT):
                        accounts.add(new CurrentAccount(accountNo, balance, active, maxWithdrawalPerDay, maxTransferPerDay, idNo));
                        break;
                    case (SAVINGS_ACCOUNT):
                        accounts.add(new SavingsAccount(accountNo, balance, active, maxWithdrawalPerDay, maxTransferPerDay, idNo));
                        ((SavingsAccount) accounts.get(accounts.size() - 1)).setWithdrawalPending(resultSet.getBoolean("WithdrawalPending"));
                        break;
                    case (CREDIT_ACCOUNT):
                        accounts.add(new CreditCardAccount(accountNo, balance, active, maxWithdrawalPerDay, maxTransferPerDay, idNo));
                        break;
                    default:
                        throw new BadAccountTypeException("Unexpected account type: " + resultSet.getString("Type"));
                }
            }
            return accounts;
        } catch (SQLException | BadAccountTypeException e) {
            System.out.println("BatsDAO_dbImpl::getAccountsByCardNo() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }

    @Override
    public boolean setAccountBalance(String accountNo, double newBalance) throws SQLException {
        try {
            String sqlStr = "UPDATE accounts SET Balance=? WHERE AccountNo=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setString(2, accountNo);
            return executeUpdateStatement(preparedStatement) > 0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::setAccountBalance() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }
    @Override
    public double getAccountBalance(String accountNo) throws SQLException {
        String sqlStr = "SELECT Balance FROM accounts WHERE AccountNo=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) return Double.NaN;
            return resultSet.getDouble("Balance");
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::getAccountBalance() >>\n\tError: "+e);
            throw e;
        }
    }
    @Override
    public boolean incrementAccountFunds(String accountNo, double amount) throws SQLException {
        try {
            double curBalance = getAccountBalance(accountNo);
            if (Double.isNaN(curBalance)) return false;
            double newBalance = curBalance+amount;
            return setAccountBalance(accountNo,newBalance);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::incrementAccountFunds() >>\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean decrementAccountFunds(String accountNo, double amount) throws SQLException {
        try {
            double curBalance = getAccountBalance(accountNo);
            if (Double.isNaN(curBalance)) return false;
            double newBalance = curBalance-amount;
            return setAccountBalance(accountNo,newBalance);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::decrementAccountFunds() >>\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean setAccountHolderPinNo(String cardNo, String newPinNo) throws SQLException {
        try {
            String sqlStr = "UPDATE accountHolderCards SET PIN=? WHERE CardNo=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1, newPinNo);
            preparedStatement.setString(2, cardNo);
            return executeUpdateStatement(preparedStatement) > 0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::setAccountHolderPinNo() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }

    @Override
    public boolean setAdminPinNo(String cardNo, String newPinNo) throws SQLException {
        try {
            String sqlStr = "UPDATE adminCards SET PIN=? WHERE CardNo=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1, newPinNo);
            preparedStatement.setString(2, cardNo);
            return executeUpdateStatement(preparedStatement) > 0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::setAdminPinNo() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }

    @Override
    public AccountHolderCard getAccountHolderCardByCardNo(String cardNo) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CARD_BY_CARD_NO);
            preparedStatement.setString(1,cardNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            String pinNo = resultSet.getString("PIN");
            boolean active = resultSet.getBoolean("Active");
            String idNo = resultSet.getString("AccountHolderID");
            return new AccountHolderCard(cardNo, pinNo, active, idNo);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::getAccountHolderCardByCardNo() >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public AccountHolderCard getAccountHolderCardByIdNo(String idNo) throws SQLException{
        return getAccountHolderCardByCardNo(getCardNoByIdNo(idNo));
    }

    @Override
    public AdminCard getAdminCardByCardNo(String cardNo) throws SQLException {
        String sqlStr = "SELECT * FROM adminCards WHERE CardNo=?;";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
        preparedStatement.setString(1,cardNo);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next())
            return null;
        String pinNo = resultSet.getString("PIN");
        boolean active = resultSet.getBoolean("Active");
        String employeeNo = resultSet.getString("EmployeeNo");
        return new AdminCard(cardNo,pinNo,active,employeeNo);
    }

    @Override
    public AdminCard getAdminCardByEmployeeNo(String employeeNo) throws SQLException {
        String sqlStr = "SELECT * FROM adminCards WHERE EmployeeNo=?;";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
        preparedStatement.setString(1,employeeNo);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next())
            return null;
        String pinNo = resultSet.getString("PIN");
        boolean active = resultSet.getBoolean("Active");
        String cardNo = resultSet.getString("CardNo");
        return new AdminCard(cardNo,pinNo,active,employeeNo);
    }

    @Override
    public ATMAdmin getATMAdminByEmployeeNo(String employeeNo) {
        return null;/*todo : implement!*/
    }

    @Override
    public ATMAdmin getATMAdminByCardNo(String cardNo) {
        return null;/*todo : implement!*/
    }

    @Override
    public Account getAccountByAccountNo(String accountNo) throws SQLException, BadAccountTypeException {
        String sqlStr="SELECT * FROM accounts WHERE AccountNo=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
        preparedStatement.setString(1,accountNo);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next()) return null;

        boolean active=resultSet.getBoolean("Active");
        double balance=resultSet.getDouble("Balance"),
                minBalance=resultSet.getDouble("MinBalance"),
                maxTransferPerDay=resultSet.getDouble("MaxTransferPerDay"),
                maxWithdrawalPerDay=resultSet.getDouble("MaxWithdrawalPerDay");
        String cardNo=resultSet.getString("CardNo");
        String idNo=getIdNoByCardNo(cardNo);
        int typeId=resultSet.getInt("Type");
        String typeStr=getAccountTypeString(typeId);
        Account account = null;
        if (typeStr.equals(CURRENT_ACCOUNT)) {
            account=new CurrentAccount(accountNo,balance,active,maxWithdrawalPerDay,maxTransferPerDay,idNo);
        } else if (typeStr.equals(SAVINGS_ACCOUNT)) {
            account=new SavingsAccount(accountNo,balance,active,maxWithdrawalPerDay,maxTransferPerDay,idNo);
            ((SavingsAccount)account).setWithdrawalPending(resultSet.getBoolean("WithdrawalPending"));
        } else if (typeStr.equals(CREDIT_ACCOUNT)) {
            account=new CreditCardAccount(accountNo,balance,active,maxWithdrawalPerDay,maxTransferPerDay,idNo);
        } else {
            throw new BadAccountTypeException("Illegal Account Type: "+typeStr);
        }
        return account;
    }

    private String getAccountTypeString(int accountTypeId) throws SQLException {
        String sqlStr="SELECT AccountType FROM accountTypes WHERE TypeNo=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
        preparedStatement.setInt(1,accountTypeId);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        return resultSet.getString("AccountType");
    }

    @Override
    public boolean addCurrentAccount(String accountHolderId, CurrentAccount newAccount) throws SQLException {
        try {
            return addAccount(accountHolderId, newAccount);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addCurrentAccount() >>" +
                    "Error: "+e);
            throw e;
        }
    }
    @Override
    public boolean addSavingsAccount(String accountHolderId, SavingsAccount newAccount) throws SQLException {
        try {
        return addAccount(accountHolderId,newAccount);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addSavingsAccount() >>" +
                    "Error: "+e);
            throw e;
        }
    }
    @Override
    public boolean addCreditAccount(String accountHolderId, CreditCardAccount newAccount) throws SQLException {
        try {
            return addAccount(accountHolderId, newAccount);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addSavingsAccount() >>" +
                    "Error: "+e);
            throw e;
        }
    }

    @Override
    public boolean addAccountHolderCard(AccountHolderCard newCard) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ACCOUNT_HOLDER_CARD);
            preparedStatement.setString(1, newCard.getCardNo());
            preparedStatement.setString(2, newCard.getPinNo());
            preparedStatement.setBoolean(3, newCard.isActive());
            preparedStatement.setString(4, newCard.getAccountHolderIdNo());
            return (executeUpdateStatement(preparedStatement) > 0);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addAccountHolderCard() >>" +
                    "Error: "+e);
            throw e;
        }
    }
    @Override
    public boolean addAdminCard(AdminCard newCard) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement(ADD_ADMIN_CARD);
        preparedStatement.setString(1,newCard.getCardNo());
        preparedStatement.setBoolean(2,newCard.isActive());
        preparedStatement.setString(3,newCard.getPinNo());
        preparedStatement.setString(4,newCard.getEmployeeNo());
        return (executeUpdateStatement(preparedStatement)>0);
    }
    @Override
    public boolean addAccount(String accountHolderId, Account account) throws SQLException {
        // Table: 1-AccountNo,2-Type,3-Balance,4-MinBalance,5-MaxTransferPerDay,6-MaxWithdrawalPerDay,7-Active,8-CardNo,9-WithdrawalPending
        PreparedStatement preparedStatement=connection.prepareStatement(CREATE_NEW_ACCOUNT);
        preparedStatement.setString(1,account.getAccountNo());
        preparedStatement.setDouble(3,account.getBalance());
        preparedStatement.setDouble(4,account.getBalance());
        preparedStatement.setDouble(5,account.getMaxTransferPerDay());
        preparedStatement.setDouble(6,account.getMaxWithdrawalPerDay());
        preparedStatement.setBoolean(7,account.isActive());
        preparedStatement.setString(8,getCardNoByIdNo(accountHolderId));
        if (account instanceof SavingsAccount) {
            preparedStatement.setBoolean(9, ((SavingsAccount) account).getWithdrawalPending());
            preparedStatement.setInt(2,getAccountTypeId(SAVINGS_ACCOUNT));
        } else {
            preparedStatement.setBoolean(9, false);
            if (account instanceof CurrentAccount)
                preparedStatement.setInt(2,getAccountTypeId(CURRENT_ACCOUNT));
            else if (account instanceof CreditCardAccount)
                preparedStatement.setInt(2,getAccountTypeId(CREDIT_ACCOUNT));
        }
        return (executeUpdateStatement(preparedStatement)>0);
    }
    @Override
    public int addAtm(String description) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement(ADD_ATM);
        preparedStatement.setInt(1,0);
        preparedStatement.setString(2,description);
        if (executeUpdateStatement(preparedStatement)>0)
            return getLastAtmId();
        else
            return -1;
    }
    @Override
    public double getAccountTotalWithdrawnAmountToday(String accountNo) throws SQLException, BadTransactionTypeException {
        String sqlStr = "SELECT Amount FROM transactions WHERE PrimaryAccountNo=? AND Type!=? AND TimeStamp>=CURDATE();";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1,accountNo);
            preparedStatement.setInt(2,getTransactionTypeId(TRANSACTION_DEPOSIT));
            ResultSet resultSet=preparedStatement.executeQuery();
            double sum = 0;
            while (resultSet.next())
                sum += resultSet.getDouble("Amount");
            return sum;
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::getAccountTotalWithdrawnAmountToday() >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }
    private int getAccountTypeId(String accountType) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_TYPE_ID);
            preparedStatement.setString(1, accountType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) return -1;
            return resultSet.getInt("TypeNo");
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::getAccountTypeId() >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }
    private String getCardNoByIdNo(String idNo) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT AccountHolderCardNo FROM accountHolders WHERE ID=?;");
            preparedStatement.setString(1, idNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            return resultSet.getString("AccountHolderCardNo");
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::getCardNoByIdNo() >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }
    private String getIdNoByCardNo(String cardNo) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT ID FROM accountHolders WHERE AccountHolderCardNo=?;");
        preparedStatement.setString(1,cardNo);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next())
            return null;
        return resultSet.getString("ID");
    }
    private int executeUpdateStatement(PreparedStatement preparedStatement) throws SQLException {
        try {
            connection.setAutoCommit(false);
            int affected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affected;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::executeUpdateStatement() >>" +
                    "\n\tError: "+e+
                    "\n\tWill attempt roll-back...");
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("BatsDAO_dbImpl::executeUpdateStatement() >>" +
                        "\n\tError trying to roll back after an error" +
                        "\n\t  Error: "+e1);
            }
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    private boolean logDeposit(Deposit deposit) throws SQLException, BadTransactionTypeException {
        //Table : TransactionID,TimeStamp,Amount,Type,ATM_ID,SecondaryAccountNo,PrimaryAccountNo
        try {
            String sqlStr = "INSERT INTO transactions VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1,0);
            preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.setDouble(3,deposit.getAmount());
            preparedStatement.setInt(4,getTransactionTypeId(deposit));
            preparedStatement.setInt(5,Integer.parseInt(deposit.getATM_ID()));// this is problematic
            preparedStatement.setString(6,null);
            preparedStatement.setString(7,deposit.getPrimAccountNo());
            return (executeUpdateStatement(preparedStatement)>0);
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::logDeposit() >>" +
                    "\n\tError..." + e);
            throw e;
        } catch (NumberFormatException e) {
            System.out.println("BatsDAO_dbImpl::logDeposit() >>" +
                    "\n\tError...............................: " + e);
            throw e;
        }
    }
    private boolean logTransfer(Transfer transfer) throws SQLException, BadTransactionTypeException {
        try {
            String sqlStr = "INSERT INTO transactions VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1,0);
            preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.setDouble(3,transfer.getAmount());
            preparedStatement.setInt(4,getTransactionTypeId(transfer));
            preparedStatement.setInt(5,Integer.parseInt(transfer.getATM_ID()));// this is problematic
            preparedStatement.setString(6,transfer.getSecondaryAccountNo());
            preparedStatement.setString(7,transfer.getPrimAccountNo());
            return (executeUpdateStatement(preparedStatement)>0);
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::logTransfer() >>" +
                    "\n\tError..." + e);
            throw e;
        } catch (NumberFormatException e) {
            System.out.println("BatsDAO_dbImpl::logTransfer() >>" +
                    "\n\tError...............................: " + e);
            throw e;
        }
    }
    private boolean logWithdrawal(Withdrawal withdrawal) throws SQLException, BadTransactionTypeException {
        //Table (transactions) : TransactionID,TimeStamp,Amount,Type,ATM_ID,SecondaryAccountNo,PrimaryAccountNo
        try {
            String sqlStr = "INSERT INTO transactions VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1,0);

            preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.setDouble(3,withdrawal.getAmount());
            preparedStatement.setInt(4,getTransactionTypeId(withdrawal));
            preparedStatement.setInt(5,Integer.parseInt(withdrawal.getATM_ID()));// this is problematic
            preparedStatement.setString(6,null);
            preparedStatement.setString(7,withdrawal.getPrimAccountNo());
            return (executeUpdateStatement(preparedStatement)>0);
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::logWithdrawal() >>" +
                    "\n\tError..." + e);
            throw e;
        } catch (NumberFormatException e) {
            System.out.println("BatsDAO_dbImpl::logWithdrawal() >>" +
                    "\n\tError...............................: " + e);
            throw e;
        }
    }
    private int getTransactionTypeId(Transaction transaction) throws SQLException, BadTransactionTypeException {
        String sqlStr="SELECT TypeNo FROM transactionTypes WHERE TransactionType=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            if (transaction instanceof Withdrawal)
                preparedStatement.setString(1, TRANSACTION_WITHDRAWAL);
            else if (transaction instanceof Deposit)
                preparedStatement.setString(1, TRANSACTION_DEPOSIT);
            else if (transaction instanceof Transfer)
                preparedStatement.setString(1, TRANSACTION_TRANSFER);
            else throw new BadTransactionTypeException("Unsupported transaction: "+transaction);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) return -1;
            return resultSet.getInt("TypeNo");
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::getTransactionTypeId() >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }
    private int getTransactionTypeId(String transactionTypeStr) throws SQLException, BadTransactionTypeException {
        String sqlStr="SELECT TypeNo FROM transactionTypes WHERE TransactionType=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            if (transactionTypeStr.equals(TRANSACTION_WITHDRAWAL))
                preparedStatement.setString(1, TRANSACTION_WITHDRAWAL);
            else if (transactionTypeStr.equals(TRANSACTION_DEPOSIT))
                preparedStatement.setString(1, TRANSACTION_DEPOSIT);
            else if (transactionTypeStr.equals(TRANSACTION_TRANSFER))
                preparedStatement.setString(1, TRANSACTION_TRANSFER);
            else throw new BadTransactionTypeException("Unsupported transaction: "+transactionTypeStr);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(!resultSet.next()) throw new BadTransactionTypeException("Transaction "+transactionTypeStr+" has no ID on DB");
            else return resultSet.getInt("TypeNo");
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::getTransactionTypeId() >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }
    private int getLastAtmId() throws SQLException {
        String sqlStr = "SELECT MAX(ID) AS LastAtmId FROM atms;";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (!resultSet.next())
            return -1;
        return resultSet.getInt("LastAtmId");
    }
}
