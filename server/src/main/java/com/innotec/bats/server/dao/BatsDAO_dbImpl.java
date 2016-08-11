package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;
import com.innotec.bats.server.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatsDAO_dbImpl implements BatsDAO {
    /*constants*/
    private static final String SAVINGS_ACCOUNT = "Savings",
                                CURRENT_ACCOUNT = "Current",
                                CREDIT_ACCOUNT = "CreditCard";
    public static final String TRANSACTION_DEPOSIT="Deposit",
                                TRANSACTION_TRANSFER="Transfer",
                                TRANSACTION_WITHDRAWAL="Withdrawal",
                                TRANSACTION_WITHDRAWAL_NOTICE="WithdrawalNotice",
                                TRANSACTION_BANK_CHARGES ="BankCharges";
    /*private attributes*/
    private Connection connection;
    /* **********************************************************************************/

    /**
     * Ctor
     * @throws SQLException - from DBConnection
     * @throws IOException - from DBConnection
     * @throws ClassNotFoundException - from DBConnection ctor
     */
    public BatsDAO_dbImpl() throws SQLException, IOException, ClassNotFoundException {
        try {
            connection = new DBConnection().getConnection();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println("BatsDAO_dbImpl::ctor >>\n\tError: "+e);
            throw e;
        }
    }
    /**
     * Returns the last account holder card # used.<br/>
     * This <i>really</i> gets the highest valued card # used in the DB account holder cards table.
     * @return - the highest added account holder card number.
     * @throws SQLException - from SQL method calls
     */
    @Override
    public String getLastAccountHolderCardNo() throws SQLException {
    	String sqlStr = "SELECT MAX(CardNo) AS LastCardNo FROM accountHolderCards;";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
        ResultSet resultSet=preparedStatement.executeQuery();
    	if (!resultSet.next())
    		return null;
        return resultSet.getString("LastCardNo");
    }

    /**
     * Attempts to set the {@code active} attribute of the {@code Account} object to false.
     * @param accNo - the account number to be closed.
     * @param tellerId - the identifier of the teller requesting the account closure.
     * @return - true iff the operation was successful, false otherwise.
     * @throws SQLException - from SQL method calls.
     */
    @Override
    public boolean closeAccount(String accNo, String tellerId) throws SQLException {
        String sqlStr="UPDATE accounts SET Active=false WHERE AccountNo=?;";
        try {
            PreparedStatement ps=connection.prepareStatement(sqlStr);
            ps.setString(1,accNo);
            return executeUpdateStatement(ps)>0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::closeAccount() >>" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean createBalanceSheet(String atmId, Date date) {
        return false; /*todo : implement*/
    }

    @Override
    public List<Transaction> get24hrStatement(String accountNo) {
        return null; /*todo : implement!*/
    }

    @Override
    public List<Transaction> get30dayStatement(String accountNo) {
        return null; /*todo : implement!*/
    }

    /**
     * Checks if a specified account exists within the database.
     * @param account - the account being checked.
     * @return - true iff the account exists, false otherwise.
     * @throws SQLException - from SQL method calls
     * @throws BadAccountTypeException
     */
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
    /**
     * Checks if a specified account holder exists within the database.
     * @param accountHolder - the account holder being checked.
     * @return - true iff the account holder exists, false otherwise.
     * @throws SQLException - from SQL method calls
     * @throws BadAccountTypeException
     */
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
    /**
     * Checks if a specified account holder card exists within the database.
     * @param card - the account holder card being checked.
     * @return - true iff the card exists, false otherwise.
     * @throws SQLException - from SQL method calls
     */
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
    /**
     * Checks if a specified admin card exists within the database.
     * @param card - the admin card being checked.
     * @return - true iff the card exists, false otherwise.
     * @throws SQLException - from SQL method calls
     */
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
    public double calculateTransactionCharges(Transaction transaction) throws SQLException, BadTransactionTypeException {
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
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::calculateTransactionCharges() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }
    private double getTransactionStandardCharge(final String transactionType) throws SQLException, BadTransactionTypeException {
        String sqlStr="SELECT InitialCharge FROM transactionCharges,transactionTypes WHERE TypeId=TypeNo AND TypeNo=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1,getTransactionTypeId(transactionType));
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("BatsDAO_dbImpl::getTransactionStandardCharge() >>" +
                        "\n\tError: ResultSet empty - for InitialCharge.");
                return Double.NaN;
            }
            return resultSet.getDouble("InitialCharge");
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::getTransactionStandardCharge() >>" +
                    "\n\tError..." + e +
                    "\n\tTransactionType = "+transactionType);
            throw e;
        }
    }
    private double getTransactionChargePerUnit(final String transactionType) throws SQLException, BadTransactionTypeException {
        String sqlStr="SELECT PerUnitCharge FROM transactionCharges,transactionTypes WHERE TypeId=TypeNo AND TypeNo=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1,getTransactionTypeId(transactionType));
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) return Double.NaN;
            return resultSet.getDouble("PerUnitCharge");
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::getTransactionChargePerUnit() >>" +
                    "\n\tError..." + e);
            throw e;
        }
    }
    private double getTransactionChargeUnitAmount(final String transactionType) throws SQLException, BadTransactionTypeException {
        String sqlStr="SELECT UnitAmount FROM transactionCharges,transactionTypes WHERE TypeId=TypeNo AND TypeNo=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1,getTransactionTypeId(transactionType));
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) return Double.NaN;
            return resultSet.getDouble("UnitAmount");
        } catch (SQLException|BadTransactionTypeException e) {
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
            String sqlStr = "INSERT INTO employees VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
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
            String sqlStr = "INSERT INTO accountHolders VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1,newAccountHolder.getIdNo());
            preparedStatement.setString(2,newAccountHolder.getName());
            preparedStatement.setString(3,newAccountHolder.getSurname());
            preparedStatement.setString(4,newAccountHolder.getAddress());
            preparedStatement.setString(5,newAccountHolder.getContactNo());
            preparedStatement.setString(6,null);
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
        try {
            String sqlStr = "SELECT * FROM accountHolders WHERE ID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1, idNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("BatsDAO_dbImpl::getAccountHolderByIdNo() >>" +
                        "\n\tAccount holder not found.");
                return null;
            }

            String name, surname, address, contactNo;
            Card card;

            name = resultSet.getString("Name");
            surname = resultSet.getString("Surname");
            address = resultSet.getString("Address");
            contactNo = resultSet.getString("ContactNumber");
            resultSet.close();

            card = getAccountHolderCardByCardNo(getCardNoByIdNo(idNo));
            if (card==null) {
                System.out.println("BatsDAO_dbImpl::getAccountHolderByIdNo() >>" +
                        "\n\tCARD not found.");
            }
            AccountHolder accountHolder = new AccountHolder(name, surname, idNo, address, contactNo, card);
            accountHolder.setAccounts((ArrayList<Account>) getAccountsByCardNo(idNo));
            System.out.println("BatsDAO_dbImpl::getAccountHolderByIdNo() >>" +
                    "\n\tReturning account holder: "+accountHolder);
            return accountHolder;
        } catch (SQLException|BadAccountTypeException e) {
            System.out.println("BatsDAO_dbImpl::getAccountHolderByIdNo() >>" +
                    "\n\tError..." + e);
            throw e;
        }
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
        String idNo=getIdNoByCardNo(cardNo);
        System.out.println("BatsDAO_dbImpl::getAccountHolderByCardNo() >>" +
                "\n\tcalled method, getIdNoByCardNo(), returns - " + idNo);
        return getAccountHolderByIdNo(idNo);
    }
    @Override
    public List<Account> getAccountsByIdNo(String idNo) throws SQLException, BadAccountTypeException {
        return getAccountsByCardNo(getCardNoByIdNo(idNo));
    }
    @Override
    public List<Account> getAccountsByCardNo(String cardNo) throws SQLException, BadAccountTypeException {
        String sqlStr="SELECT * FROM accounts WHERE CardNo=?;";
        try {
            List<Account> accounts = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
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

                switch (getAccountTypeString(resultSet.getInt("Type"))) {
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
        } catch (Exception e) {
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
            String sqlStr = "SELECT * FROM accountHolderCards WHERE CardNo=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
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
    public Employee getEmployeeByEmployeeNo(String employeeNo) {
        return null;/*todo : implement!*/
    }

    @Override
    public Employee getEmployeeByIdNo(String idNo) {
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
            String sqlStr_cardInsert = "INSERT INTO accountHolderCards VALUES (?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr_cardInsert);
            preparedStatement.setString(1, newCard.getCardNo());
            preparedStatement.setString(2, newCard.getPinNo());
            preparedStatement.setBoolean(3, newCard.isActive());
            preparedStatement.setString(4, newCard.getAccountHolderIdNo());
            if (executeUpdateStatement(preparedStatement) < 1) return false;
            // add to account holders also!
            String sqlStr_accHolderUpdate = "UPDATE accountHolders SET AccountHolderCardNo=? WHERE ID=?;";
            PreparedStatement ps=connection.prepareStatement(sqlStr_accHolderUpdate);
            ps.setString(1,newCard.getCardNo());
            ps.setString(2,newCard.getAccountHolderIdNo());
            return (executeUpdateStatement(ps)>0);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addAccountHolderCard() >>" +
                    "Error: "+e);
            throw e;
        }
    }
    @Override
    public boolean addAdminCard(AdminCard newCard) throws SQLException {
        String sqlStr_cardInsert = "INSERT INTO adminCards VALUES (?,?,?,?);";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr_cardInsert);
        preparedStatement.setString(1,newCard.getCardNo());
        preparedStatement.setBoolean(2,newCard.isActive());
        preparedStatement.setString(3,newCard.getPinNo());
        preparedStatement.setString(4,newCard.getEmployeeNo());
        if (executeUpdateStatement(preparedStatement) < 1) return false;
        // add to account holders also!
        String sqlStr_adminUpdate = "UPDATE admins SET AdminCardNo=? WHERE EmployeeNo=?;";
        PreparedStatement ps=connection.prepareStatement(sqlStr_adminUpdate);
        ps.setString(1,newCard.getCardNo());
        ps.setString(2,newCard.getEmployeeNo());
        return (executeUpdateStatement(ps)>0);
    }
    @Override
    public boolean addAccount(String accountHolderId, Account account) throws SQLException {
        // Table: 1-AccountNo,2-Type,3-Balance,4-MinBalance,5-MaxTransferPerDay,6-MaxWithdrawalPerDay,7-Active,8-CardNo,9-WithdrawalPending
        String sqlStr = "INSERT INTO accounts VALUES (?,?,?,?,?,?,?,?,?);";
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
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
        String sqlStr = "INSERT INTO atms VALUES (?,?);"; // Table : ID,Description
        PreparedStatement preparedStatement=connection.prepareStatement(sqlStr);
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

    @Override
    public boolean removeLastTransaction() throws SQLException {
        String sqlStr="REMOVE FROM transactions WHERE ID = (SELECT MAX(ID) FROM transactions);";
        try {
            PreparedStatement ps=connection.prepareStatement(sqlStr);
            return executeUpdateStatement(ps)>0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::removeLastTransaction() >>" +
                    "\n\tError: "+e);
            throw e;
        }

    }

    @Override
    public boolean blockAccountHolderCard(String cardNo) throws SQLException {
        String sqlStr="UPDATE accountHolderCards SET Active=false WHERE CardNo=?;";
        try {
            PreparedStatement ps=connection.prepareStatement(sqlStr);
            ps.setString(1,cardNo);
            return executeUpdateStatement(ps)>0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::blockAccountHolderCard" +
                    "\n\tError: "+e);
            throw e;
        }
    }
    @Override
    public boolean blockAdminCard(String cardNo) throws SQLException {
        String sqlStr="UPDATE adminCards SET Active=false WHERE CardNo=?;";
        try {
            PreparedStatement ps=connection.prepareStatement(sqlStr);
            ps.setString(1,cardNo);
            return executeUpdateStatement(ps)>0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::blockAdminCard" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean unblockAccountHolderCard(String cardNo, String employeeNo) throws SQLException {
        String sqlStr="UPDATE accountHolderCards SET Active=true WHERE CardNo=?;";
        try {
            PreparedStatement ps=connection.prepareStatement(sqlStr);
            ps.setString(1,cardNo);
            return executeUpdateStatement(ps)>0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::unblockAccountHolderCard" +
                    "\n\tError: "+e);
            throw e;
        }
    }
    @Override
    public boolean unblockAdminCard(String cardNo, String employeeNo) throws SQLException {
        String sqlStr="UPDATE adminCards SET Active=true WHERE CardNo=?;";
        try {
            PreparedStatement ps=connection.prepareStatement(sqlStr);
            ps.setString(1,cardNo);
            return executeUpdateStatement(ps)>0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::unblockAdminCard" +
                    "\n\tError: "+e);
            throw e;
        }
    }

    @Override
    public boolean logTransactionCharges(Transaction transaction, double charges) throws SQLException, BadTransactionTypeException {
        //Table (transactions) : TransactionID,TimeStamp,Amount,Type,ATM_ID,SecondaryAccountNo,PrimaryAccountNo
        try {
            String sqlStr = "INSERT INTO transactions VALUES (?,?,?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sqlStr);
            ps.setInt(1,0);
            ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            ps.setDouble(3,transaction.getAmount());
            ps.setInt(4,getTransactionTypeId(TRANSACTION_BANK_CHARGES));
            ps.setInt(5,Integer.parseInt(transaction.getATM_ID()));// this is problematic
            ps.setString(6,null);
            ps.setString(7,transaction.getPrimAccountNo());
            return (executeUpdateStatement(ps)>0);
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::logTransactionCharges() >>" +
                    "\n\tError..." + e);
            throw e;
        } catch (NumberFormatException e) {
            System.out.println("BatsDAO_dbImpl::logTransactionCharges() >>" +
                    "\n\tError: " + e +
                    "\n\tCaused by trying to cast the atm id into a number from string.");
            throw e;
        }
    }

    private int getAccountTypeId(String accountType) throws SQLException {
        try {
            String sqlStr = "SELECT * FROM accountTypes WHERE AccountType=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
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
                    "\n\tError: " + e +
                    "\n\tCaused by trying to cast the atm id into a number from string.");
            throw e;
        }
    }
    private boolean logWithdrawalNotice(Withdrawal withdrawalNotice) throws SQLException, BadTransactionTypeException {
        //Table (transactions) : TransactionID,TimeStamp,Amount,Type,ATM_ID,SecondaryAccountNo,PrimaryAccountNo
        try {
            String sqlStr = "INSERT INTO transactions VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1,0);

            preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.setDouble(3,withdrawalNotice.getAmount());
            preparedStatement.setInt(4,getTransactionTypeId(TRANSACTION_WITHDRAWAL_NOTICE));
            preparedStatement.setInt(5,Integer.parseInt(withdrawalNotice.getATM_ID()));// this is problematic
            preparedStatement.setString(6,null);
            preparedStatement.setString(7,withdrawalNotice.getPrimAccountNo());
            return (executeUpdateStatement(preparedStatement)>0);
        } catch (SQLException|BadTransactionTypeException e) {
            System.out.println("BatsDAO_dbImpl::logWithdrawalNotice() >>" +
                    "\n\tError..." + e);
            throw e;
        } catch (NumberFormatException e) {
            System.out.println("BatsDAO_dbImpl::logWithdrawalNotice() >>" +
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
            preparedStatement.setString(1, transactionTypeStr);
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
