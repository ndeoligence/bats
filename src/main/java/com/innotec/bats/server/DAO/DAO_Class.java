package com.innotec.bats.server.DAO;

import com.innotec.bats.general.ATMAdmin;
import com.innotec.bats.general.Account;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.AccountHolderCard;
import com.innotec.bats.general.AdminCard;
import com.innotec.bats.general.Card;
import com.innotec.bats.general.CreditCardAccount;
import com.innotec.bats.general.CurrentAccount;
import com.innotec.bats.general.Deposit;
import com.innotec.bats.general.Employee;
import com.innotec.bats.general.SavingsAccount;
import com.innotec.bats.general.Teller;
import com.innotec.bats.general.Transaction;
import com.innotec.bats.general.Transfer;
import com.innotec.bats.general.Withdrawal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DAO_Class implements DAO_Interface
{
	private static final String SAVINGS_ACCOUNT = "Savings Account";
	private static final String CURRENT_ACCOUNT = "Current Account";
	private static final String CREDIT_ACCOUNT = "Credit Card Account";

	private static final String ADD_ACCOUNTHOLDER = "insert into accountholdertbl values(?,?,?,?); ";
	private static final String ADD_ACCOUNTHOLDERCARD = "insert into accountholdercardtbl values (?,?,?,?,?,?);";
	private static final String ADD_CURRENTACCOUNT = "insert into accounttbl values (?,1,?,100.00,?,?,true,?);";
	private static final String ADD_SAVINGSACCOUNT = "insert into accounttbl values(?,2,?,1000.00,?,?,true,?);";
	private static final String ADD_CREDITCARDACCOUNT = "insert into accounttbl values(?,3,?,?,?,?,true,?);";
	private static final String ADD_TRANSACTION = "insert into transactiontbl values (?,?,?,?);";
	
	private static final String ADD_PENDINGWITHDRAWAL = "insert into pendingwithdrawalstbl values (?, ?, ?);";
	private static final String GET_PENDINGWITHDRAWAL = "select * from pendingwithdrawalstbl where accountNo = ?;";
	private static final String SET_PENDINGWITHDRAWAL = "update accountstbl set WithdrawalPending = ? where AccountNo = ?;";

	private static final String GET_ACCOUNTHOLDERCARDBYCARDNO = "select * from accountholdercardtbl where CardNo = ?;";
	private static final String GET_ADMINCARDBYCARDNO = "select * from admincardtbl where CardNo = ?;";
	
	private static final String GET_ACCOUNTHOLDERBYCARDNO = "select * from accountholdertbl where AccountHolderCardNo = ?;";
	private static final String GET_ACCOUNTHOLDERBYIDNO = "select * from accountholdertbl where ID = ?;";
	
	private static final String GET_CARDNOBYACCOUNTNO = "select * from accounttbl where AccountNo = ?;";
	private static final String GET_ACCOUNTBYACCOUNTNO = "select * from accounttbl where AccountNo = ?;";
	private static final String GET_TRANSACTIONSFORACCOUNT = "select * from transactiontbl where AccountNo = ?;";
	
	private static final String ADJUST_FUNDS = "update Accounttbl set balance = ? where AccountNo = ?;";
	
	private static final String SET_CARDISACTIVE = "update cardtbl set Active = ? where CardNo = ?;";
	private static final String CHANGE_PIN = "update accountholdercardtbl set PIN = ? where CardNo = ?;";
	private static final String SET_ACCOUNTISOPEN = "update accounttbl set Active = ? where AccountNo = ?;";

	 private static final String GET_ACCOUNTSBYCARDNO = "select * from accounttbl where CardNo = ?;";
	
	// private static final String GET_CURRENTACCOUNTBYIDNO =
	// "select * from accounts where accountHolderID=? and type =?;";
	// private static final String GET_SAVINGSACCOUNTBYID =
	// "select * from accounts where accountHolderID = ? and type =?;";
	// private static final String GET_CREDITCARDACCOUNTBYID =
	// "select * from accounts where accountHolderID =? and type =?;";
	

	// private static final String ADD_EMPLOYEE =
	// "insert into employeetbl values (?,?);";
	// private static final String ADD_ADMINCARD =
	// "insert into adminCardtbl values (?,?,?,?);";
	// private static final String GET_ACCOUNTHOLDERCARDBYID =
	// "select * from accountHolderCardtbl where accountHolderID = ?;";
	// private static final String GET_EMPLOYEE =
	// "select * from employeetbl where employeeID = ?;";
	// private static final String GET_ADMINCARDBYID =
	// "select * from adminCardtbl where employeeID = ?;";

	// private static final String GET_ATM =
	// "select * from atmtbl where ATMID = ?;";
	// private static final String ADD_ATM = "insert into atmtbl values (?,?)";
	// private static final String GET_TRANSACTIONFORATM =
	// "select * from transactiontbl where ATMID = ?;";

	private PreparedStatement pStmt;
	private MySQL_connection conn;
	private ResultSet rs;

	public DAO_Class ()
	{
		conn = new MySQL_connection();
	}

	@Override
	public AccountHolderCard getAccountHolderCard (String cardNo) // working
	{
		AccountHolderCard card = null;

		try
		{
			pStmt = conn.getConnection().prepareStatement(
					GET_ACCOUNTHOLDERCARDBYCARDNO);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			rs.next();
			card = new AccountHolderCard(rs.getString(1), rs.getString(2),
					rs.getBoolean(3), rs.getString(6));
			rs.close();
		}
		catch (SQLException e)
		{
			System.out
					.println("Problem retrieving data from database - SQL Exception");
			return null;
		}
		return card;
	}

	@Override
	public AdminCard getAdminCard (String cardNo) // working
	{
		AdminCard card;
		try
		{
			pStmt = conn.getConnection().prepareStatement(GET_ADMINCARDBYCARDNO);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			rs.next();
			card = new AdminCard(rs.getString(1), rs.getString(3),
					rs.getBoolean(2), rs.getString(4));
			rs.close();
		}
		catch (Exception e)
		{
			System.out
					.println("Problem retrieving data from database - SQL Exception");
			return null;
		}
		return card;
	}

	@Override
	public AccountHolder getAccountHolderByCardNo (String cardNo)
	{
		AccountHolder accountHolder;
		try
		{
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDERBYCARDNO);
			System.out.println("cardno: " + cardNo);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			rs.next();

			accountHolder = new AccountHolder(rs.getString(2), rs.getString(3), rs.getString(1),
					rs.getString(4), rs.getString(5));
			rs.close();
			System.out.println(accountHolder.toString());
		}
		catch (SQLException e)
		{
			System.out.println("Problem returning accountholder data from database");
			return null;
		}
		return accountHolder;
	}

	@Override
	public AccountHolder getAccountHolderByIdNo (String idNo)
	{

		AccountHolder accountHolder = null;

		try
		{
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDERBYIDNO);
			pStmt.setString(1, idNo);
			rs = pStmt.executeQuery();
			rs.next();

			accountHolder = new AccountHolder(rs.getString(2), rs.getString(3), rs.getString(1),
					rs.getString(4), rs.getString(5));
			rs.close();
		}
		catch (SQLException e)
		{
			System.out.println("Problem returning accountholder data from database");
			return null;
		}
		return accountHolder;
	}

	@Override
	public AccountHolder getAccountHolderByAccountNo (String accNo)
	{
		AccountHolder accountHolder;
		String cardNo;
		try
		{
			pStmt = conn.getConnection().prepareStatement(GET_CARDNOBYACCOUNTNO);
			pStmt.setString(1, accNo);
			rs = pStmt.executeQuery();
			rs.next();

			cardNo = rs.getString(8);
			rs.close();
		}
		catch (SQLException e)
		{
			System.out.println("Problem returning accountholder data from database");
			return null;
		}
		accountHolder = this.getAccountHolderByCardNo(cardNo);
		return accountHolder;
	}

	@Override
	public ArrayList<Account> getAccounts (String cardNo)
	{
		ArrayList<Account> accounts = new ArrayList<Account>();
		try
		{
			Account account;
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTSBYCARDNO);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			rs.next();
			//while (rs.next())
			//{
				if (rs.getInt(2) == 1)
				{
					account = new CurrentAccount(rs.getString(1),
							rs.getDouble(3), rs.getBoolean(7), rs.getDouble(6), rs.getDouble(5), "");
					System.out.println("DAO currentAcc: " + account.toString());
					accounts.add(account);
				}
				if (rs.getInt(2) == 2)
				{
					account = new SavingsAccount(rs.getString(1),
							rs.getDouble(3), rs.getBoolean(7), rs.getDouble(6), rs.getDouble(5), "");
	//				((SavingsAccount)account).setWithdrawalPending(rs.getBoolean(9));
	//				System.out.println("DAO savingsAcc: " + account.toString());
	//				if (rs.getBoolean(9))
	//				{
	//					pStmt = conn.getConnection().prepareStatement(GET_PENDINGWITHDRAWAL);
	//					pStmt.setString(1, account.getAccountNo());
	//					rs = pStmt.executeQuery();
	//					((SavingsAccount)account).setFundsAvailableDate(rs.getDate(2));
	//					((SavingsAccount)account).setPendingWithdrawalAmount(rs.getDouble(3));
	//				}
					accounts.add(account);
				}
			//}
			rs.close();
		}
		catch (Exception e)
		{
			System.out.println("Problem retrieving accounts from db.");
			return null;
		}
		return accounts;
	}
	
	@Override
	public boolean processWithdrawal (Withdrawal newWithdrawal)
	{
		double accountBalance;
		if (!(newWithdrawal.getWaitingPeriodApplicable()))
		{
			try
			{
				pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTBYACCOUNTNO);
				pStmt.setString(1, newWithdrawal.getPrimAccountNo());
				rs = pStmt.executeQuery();
				rs.next();
				accountBalance = rs.getDouble(3);
				
				accountBalance = accountBalance-newWithdrawal.getAmount();
				
				pStmt = conn.getConnection().prepareStatement(ADJUST_FUNDS);
				pStmt.setDouble(1, accountBalance);
				pStmt.setString(2, newWithdrawal.getPrimAccountNo());
				
				pStmt = conn.getConnection().prepareStatement(ADD_TRANSACTION);
				pStmt.setDouble(3, newWithdrawal.getAmount());
				pStmt.setInt(4, 1);
				pStmt.setString(5, newWithdrawal.getATM_ID());
				pStmt.setString(6, newWithdrawal.getPrimAccountNo());
				pStmt.executeUpdate();
				return true;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			
			return false; //still code withdrawal with waiting period
		}
	}

	@Override
	public boolean processDeposit (Deposit newDeposit)
	{
		return false;
	}

	@Override
	public boolean processTransfer (Transfer newTransfer)
	{
		return false;
	}

	@Override
	public boolean changePIN (String newPIN, String cardNo)
	{
		return false;
	}

	@Override
	public ArrayList<Transaction> getStatement (String accountNo)
	{
		Transaction temp = null;
		ArrayList<Transaction> statement = new ArrayList();
		try
		{
			pStmt = conn.getConnection().prepareStatement(
					GET_TRANSACTIONSFORACCOUNT);
			pStmt.setString(1, accountNo);
			rs = pStmt.executeQuery();
			rs.next();
			// temp = new Transaction(rs.getInt("transactionID"),
			// rs.getDate("transactionTimeStamp"),
			// rs.getDouble("transactionAmount"),
			// rs.getString("accounts_accountNo"), rs.getString("type"));
			// rs.close();
			statement.add(temp);
		}
		catch (Exception e)
		{
			return null;
		}
		return statement;
	}

	@Override
	public boolean addAccountHolder (AccountHolder newHolder, String tellerId)
	{
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNTHOLDER);
			pStmt.setString(1, newHolder.getIdNo());
			pStmt.setString(2,
					newHolder.getName() + " " + newHolder.getSurname());
			pStmt.setString(3, newHolder.getAddress());
			pStmt.setString(4, newHolder.getContactNo());
			// pStmt.setString(5, tellerId);
			pStmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	// @Override
	// public boolean addAccountHolder (AccountHolder newHolder, String
	// tellerId)
	// {
	// return false;
	// }
	//
	// @Override
	// public boolean addCurrentAccount (String accountHolderId,
	// CurrentAccount account)
	// {
	// return false;
	// }
	//
	// @Override
	// public boolean addSavingsAccount (String accountHolderId,
	// SavingsAccount account)
	// {
	// return false;
	// }

	@Override
	public boolean addAccountHolderCard (AccountHolderCard newCard)
	{
		try
		{
			pStmt = conn.getConnection()
					.prepareStatement(ADD_ACCOUNTHOLDERCARD);
			pStmt.setString(1, newCard.getCardNo());
			pStmt.setBoolean(2, newCard.isActive());
			pStmt.setString(3, newCard.getPinNo());
			pStmt.setString(4, newCard.getAccountHolderIdNo());
			pStmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("null")
	@Override
	public boolean addCurrentAccount (String accountHolderId,
			CurrentAccount account)
	{
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_CURRENTACCOUNT);
			pStmt.setString(1, account.getAccountNo());
			pStmt.setString(2, "Current Account");
			pStmt.setDouble(3, account.getBalance());
			pStmt.setDouble(4, account.getMaxWithdrawalPerDay());
			pStmt.setDouble(5, account.getMaxTransferPerDay());
			pStmt.setBoolean(6, account.isActive());
			pStmt.setDate(7, null);
			pStmt.setBoolean(8, (Boolean) null);
			pStmt.setString(9, account.getAccountHolderId());
			pStmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addSavingsAccount (String accountHolderId,
			SavingsAccount account)
	{
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_SAVINGSACCOUNT);
			pStmt.setString(1, account.getAccountNo());
			pStmt.setString(2, "Savings Account");
			pStmt.setDouble(3, account.getBalance());
			pStmt.setDouble(4, account.getMaxWithdrawalPerDay());
			pStmt.setDouble(5, account.getMaxTransferPerDay());
			pStmt.setBoolean(6, account.isActive());
			// pStmt.setDate(7, (Date)
			// account.getDateFromStartOfNoticePeriod());
			pStmt.setBoolean(8, account.getWithdrawalPending());
			pStmt.setString(9, account.getAccountHolderId());
			pStmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean closeAccount (String accNo)
	{
		return false;
	}

	@Override
	public boolean deactivateCard (String cardNo)
	{

		return false;
	}

	@Override
	public boolean reactivateCard (String accNo)
	{
		return false;
	}

	@Override
	public boolean createBalanceSheet (String atmId, java.util.Date date)
	{
		return false;
	}

	// @SuppressWarnings("null")
	// @Override
	// public boolean addCreditCardAccount (String accountHolderId,
	// CreditCardAccount account)
	// {
	// try
	// {
	// pStmt = conn.getConnection()
	// .prepareStatement(ADD_CREDITCARDACCOUNT);
	// pStmt.setString(1, account.getAccountNo());
	// pStmt.setString(2, "Credit Card Account");
	// pStmt.setDouble(3, account.getBalance());
	// pStmt.setDouble(4, account.getMaxWithdrawalPerDay());
	// pStmt.setDouble(5, account.getMaxTransferPerDay());
	// pStmt.setBoolean(6, account.isActive());
	// pStmt.setDate(7, null);
	// pStmt.setBoolean(8, (Boolean) null);
	// pStmt.setString(9, account.getAccountHolderId());
	// pStmt.executeUpdate();
	// }
	// catch (SQLException e)
	// {
	// e.printStackTrace();
	// }
	// }

	// @Override
	// public CreditCardAccount getCreditCardAccount(String accountHolderIdNo) {
	// CreditCardAccount temp;
	// try {
	// pStmt = conn.getConnection().prepareStatement(GET_CREDITCARDACCOUNT);
	// pStmt.setString(1, accountHolderIdNo);
	// pStmt.setString(2, "CreditCardAccount");
	// rs = pStmt.executeQuery();
	// rs.next();
	// temp = new CreditCardAccount(rs.getString("accountNo"),
	// rs.getDouble("balance"),
	// rs.getBoolean("active"), rs.getDouble("maxWithdrawal/day"),
	// rs.getDouble("maxTransfer/day"), rs.getString("accountHolderID"));
	// rs.close();
	// } catch (Exception e) {
	// return null;
	// }
	// return temp;
	// }

	// @Override
	// public CurrentAccount getCurrentAccount (String accountHolderIdNo)
	// {
	// CurrentAccount temp;
	// try
	// {
	// pStmt = conn.getConnection().prepareStatement(GET_CURRENTACCOUNT);
	// pStmt.setString(1, accountHolderIdNo);
	// pStmt.setString(2, "Current Account");
	// rs = pStmt.executeQuery();
	// rs.next();
	// temp = new CurrentAccount(rs.getString("accountNo"),
	// rs.getDouble("balance"), rs.getBoolean("active"),
	// rs.getString("accountHolderID"));
	// rs.close();
	// }
	// catch (Exception e)
	// {
	// return null;
	// }
	// return temp;
	// }
	//
	// @Override
	// public SavingsAccount getSavingsAccount (String accountHolderIdNo)
	// {
	// SavingsAccount temp;
	// try
	// {
	// pStmt = conn.getConnection()
	// .prepareStatement(GET_CREDITCARDACCOUNT);
	// pStmt.setString(1, accountHolderIdNo);
	// pStmt.setString(2, "CreditCardAccount");
	// rs = pStmt.executeQuery();
	// rs.next();
	// temp = new SavingsAccount(rs.getString("accountNo"),
	// rs.getDouble("balance"), rs.getBoolean("active"),
	// rs.getDouble("maxWithdrawal/day"),
	// rs.getDouble("maxTransfer/day"),
	// rs.getString("accountHolderID"));
	// rs.close();
	// }
	// catch (Exception e)
	// {
	// return null;
	// }
	// return temp;
	// }

	// @Override
	// public void addEmployee(Employee newEmployee) {
	// try {
	// pStmt = conn.getConnection().prepareStatement(ADD_EMPLOYEE);
	// pStmt.setString(1, newEmployee.getIdNo());
	// pStmt.setString(2, newEmployee.getName()+" "+newEmployee.getSurname());
	// pStmt.executeUpdate();
	// } catch (SQLException e)
	// {
	// e.printStackTrace();
	// }
	// }

	// @Override
	// public void addAdminCard(AdminCard newAdmin) {
	// try {
	// pStmt = conn.getConnection().prepareStatement(ADD_ADMINCARD);
	// pStmt.setString(1, newAdmin.getEmployeeNo());
	// pStmt.setString(2,newAdmin.getCardNo());
	// pStmt.setString(3, newAdmin.getPinNo());
	// pStmt.setBoolean(4, newAdmin.isActive());
	// pStmt.executeUpdate();
	// } catch (SQLException e)
	// {
	// e.printStackTrace();
	// }
	// }

	// @Override
	// public Employee getEmployee (String employeeID)
	// {
	// Employee temp = null;
	//
	// try
	// {
	// pStmt = conn.getConnection().prepareStatement(GET_EMPLOYEE);
	// pStmt.setString(1, employeeID);
	// rs = pStmt.executeQuery();
	// String name = null, surname = null;
	// rs.next();
	// for (int pos = 0; pos < rs.getString("employeeName").length(); pos++)
	// {
	// if (rs.getString("employeeName").charAt(pos) == ' ')
	// {
	// name = rs.getString("employeeName").substring(0, pos);
	// surname = rs.getString("employeeName").substring(pos,
	// rs.getString("employeeName").length());
	// }
	// }
	// temp = new Employee(name, surname, rs.getString("employeeIDNo"),
	// rs.getInt("employeeID"));
	// rs.close();
	// }
	// catch (SQLException e)
	// {
	// return null;
	// }
	// return temp;
	// }
}