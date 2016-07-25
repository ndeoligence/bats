package com.innotec.bats.server.dao;

import innotec.bats.general_code.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class DAO_Class implements DAO_Interface
{
	private static final String SAVINGS_ACCOUNT = "Savings Account";
	private static final String CURRENT_ACCOUNT = "Current Account";
	private static final String CREDIT_ACCOUNT = "Credit Card Account";
	
	private static final String ADD_ACCOUNTHOLDER = "insert into accountholdertbl values(?,?,?,?,?); ";
	private static final String ADD_CARD = "insert into cardtb (?,?,?,?,?);";
	private static final String ADD_ACCOUNT = "insert into accounttbl (?,?,?,?,?,?);";
	private static final String ADD_EMPLOYEE = "insert into employeetbl (?,?,?,?,?);";
	private static final String ADD_ADMIN = "insert into admintbl (?,?);";
	private static final String ADD_TRANSACTION = "insert into transactiontbl (?,?,?,?,?,?);";
	private static final String GET_ACCOUNTHOLDER = "Select * from accountholdertbl where Cardtbl_CardNo = ?;";
	private static final String GET_CARD = "select * form cardtbl where CardNo = ?;";
	private static final String GET_ACCOUNT = "select * from accounttbl where CardNoForAcc = ?;";
	private static final String GET_EMPLOYEE = "select * from employeetbl where EmployeeID = ?;";
	private static final String GET_ADMIN = "select * from admintbl,employeetbl where AdminID = ?;";
	private static final String GET_TRANSACTIONFORACCOUNT = "select * from transactiontbl where AccountID = ?;";
	private static final String GET_ATM = "select * from atmtbl where ATMID = ?;";
	private static final String CARD_ACTIVE = "update cardtbl set Card_Active = ? where CardNo = ?;";
	private static final String ADD_ATM = "insert into atmtbl (?,?)";
	private static final String CHANGE_PIN = "update cardtbl set Card_PIN = ? where CardNo = ?;";
	private static final String ACCOUNT_ACTIVITY = "update accounttbl set isActive = ? where AccountNo = ?;";
	private static final String GET_TRANSACTIONFORATM = "select * from transactiontbl where ATMID = ?;";
	private static final String GET_HOLDERFROMID = "select * from acountholdertbl where AcountHolderID = ? ";
	private PreparedStatement pStmt = null;
	private MySQL_connection conn;
	private ResultSet rs;
	
	public void addAccountHolder(AccountHolder newHolder )
	{
		pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNTHOLDER);
		pStmt.setString(1, newHolder.getID());
		pStmt.setString(2, newHolder.getName()+" "+newHolder.getSurname);
		pStmt.setString(3, newHolder.getAddress());
		pStmt.setString(4, newHolder.getContactNo());
		pStmt.setString(5, newHolder.getCardNo());
		pStmt.executeUpdate();
	}
	
	public void addCard(Card newCard)
	{
		pStmt = conn.getConnection().prepareStatement(ADD_CARD);
		pStmt.setString(1, newCard.getCardNo());
		pStmt.setString(2, newCard.getCardCVC());
		pStmt.setDate(3, newCard.getExpireDate());
		pStmt.setBoolean(4, newCard.getActive());
		pStmt.setString(5, newCard.getPIN());
		pStmt.executeUpdate();
	}

	public void addAccount(Account newAccount)
	{
		String accType;
		if(newAccount.equals(new CurrentAccount))
		{
			accType = CURRENT_ACCOUNT;
		}
		else
		{
			if(newAccount.equals(SavingsAccount))
			{
				accType = SAVINGS_ACCOUNT;
			}
			else
			{
				if(newAccount.equals(CreditCardAccount))
				{
					accType = CREDIT_ACCOUNT;
				}
			}
		}
		pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNT);
		pStmt.setString(1, newAccount.getAccNo());
		pStmt.setString(2, accType);
		pStmt.setDouble(3, newAccount.getBalance());
		pStmt.setDouble(4, newAccount.getMaxWithdrawalPerDay());
		pStmt.setDouble(5, newAccount.getMaxTransferPerDay());
	//	pStmt.setString(6, newAccount.getCardNo());
		pStmt.setBoolean(7, newAccount.getisActive());
		pStmt.executeUpdate();
	}
	
	public void addEmployee(Employee newEmployee)
	{
		pStmt = conn.getConnection().prepareStatement(ADD_EMPLOYEE);
		pStmt.setString(1, newEmployee.getID());
		pStmt.setString(2, newEmployee.getName()+" "+newEmployee.getSurname);
		pStmt.setString(3, newEmployee.getAddress());
		pStmt.setString(4, newEmployee.getContactNo());
		pStmt.setString(5, newEmployee.getAdminID());
		pStmt.executeUpdate();
	}
	
	public void addAdmin(Admin newAdmin)
	{
		pStmt = conn.getConnection().prepareStatement(ADD_ADMIN);
		pStmt.setString(1, newAdmin.getAdminID());
		pStmt.setString(2, newAdmin.getAdminCard());
		pStmt.executeUpdate();
	}
	
	public void addTransaction(Transaction newTransaction)
	{
		pStmt = conn.getConnection().prepareStatement(ADD_TRANSACTION);
		pStmt.setInt(1, newTransaction.getID());
		pStmt.setDate(2, newTransaction.getTimeStamp());
		pStmt.setDouble(3, newTransaction.getAmount());
		pStmt.setString(4, newTransaction.getType());
		pStmt.setInt(5, newTransaction.getATMID());
		pStmt.setString(6, newTransaction.getAccountNo());
		pStmt.executeUpdate();
	}
	
	public void addATM(ATM newATM)
	{
		pStmt = conn.getConnection().prepareStatement(ADD_ATM);
		pStmt.setInt(1, newATM.getID());
		pStmt.setDouble(2, newATM.getBalance);
		pStmt.executeUpdate();
	}
	
	public AccountHolder getAccountHolder(String cardNo)
	{
		pStmt.setString(1, CardNo);
		pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDER);
		rs = pStmt.executeQuery();
			AccountHolder temp = new AccountHolder(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
		return temp;
	}
	
	public Card getCard(String cardNo)
	{
		pStmt.setString(1, cardNo);
		pStmt = conn.getConnection().prepareStatement(GET_CARD);
		rs = pStmt.executeQuery();
			Card temp = new Card(rs.getString(1), rs.getString(2),rs.getDate(3),rs.getBoolean(4),rs.getString(5));
		return temp;
	}
	
	public ArrayList<Account> getAccount(String cardNo)
	{
		ArrayList<Account> temp = new ArrayList<Account>();
		pStmt.setString(1, CardNo);
		pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDER);
		rs = pStmt.executeQuery();
		while(rs.next())
		{
		Account newAcc = new Account(rs.getString(1),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getBoolean(7));
		temp.add(newAcc);
		}
		return temp;
	}
	
	public Employee getEmployee(String employeeID)
	{
		pStmt.setString(1, employeeID);
		pStmt = conn.getConnection().prepareStatement(GET_EMPLOYEE);
		rs = pStmt.executeQuery();
			Employee temp = new Employee(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
		return temp;
	}
	
	public Admin getAdmin(String adminID)
	{
		pStmt.setString(1, adminID);
		pStmt = conn.getConnection().prepareStatement(GET_ADMIN);
		rs = pStmt.executeQuery();
			Admin temp = new Admin(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
		return temp;
	}
	
	public Transaction getTransactionForAccount(String accountID)
	{
		pStmt.setString(1, accountID );
		pStmt = conn.getConnection().prepareStatement(GET_TRANSACTIONFORACCOUNT);
		rs = pStmt.executeQuery();
			Transaction temp = new Transaction(rs.getInt(1), rs.getDate(2),rs.getDouble(3),rs.getString(4),rs.getInt(5),rs.getString(6));
		return temp;
	}
	
	public ATM getATM(int atmID)
	{
		pStmt.setInt(1, atmID);
		pStmt = conn.getConnection().prepareStatement(GET_ATM);
		rs = pStmt.executeQuery();
			ATM temp = new ATM(rs.getInt(1), rs.getDouble(2));
		return temp;
	}
	
	public boolean updateCardActivity(boolean cardActivity, String cardNo)
	{
		boolean check;
		try {
			pStmt.setBoolean(1, cardActivity);
			pStmt.setString(2, cardNo);
			pStmt = conn.getConnection().prepareStatement(CARD_ACTIVE);
			rs = pStmt.executeQuery();
			 check =rs.getBoolean(4) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(check = true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean changePIN(String newPIN, String cardNo)
	{
		String pinCheck = null;
		try {
			pStmt.setString(1, newPIN);
			pStmt.setString(2, cardNo);
			pStmt = conn.getConnection().prepareStatement(CHANGE_PIN);
			rs = pStmt.executeQuery();
			pinCheck = rs.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(pinCheck.equals(newPIN))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean updateAccountActivity(boolean activity, String accountNo)
	{
		boolean check;
		try {
			pStmt.setBoolean(1, activity);
			pStmt.setString(2, accountNo);
			pStmt = conn.getConnection().prepareStatement(ACCOUNT_ACTIVITY);
			rs = pStmt.executeQuery();
			check = rs.getBoolean(6) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(check = true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public Transaction getTransactionForATM(int atmID)
	{
		pStmt.setInt(1, atmID );
		pStmt = conn.getConnection().prepareStatement(GET_TRANSACTIONFORATM);
		rs = pStmt.executeQuery();
			Transaction temp = new Transaction(rs.getInt(1), rs.getDate(2),rs.getDouble(3),rs.getString(4),rs.getInt(5),rs.getString(6));
		return temp;
	}
	
	public AcountHolder getAcountHolderFromID(String holderID)
	{
		pStmt.setString(1, holderID);
		pStmt = conn.getConnection().prepareStatement(GET_HOLDERFROMID);
		rs = pStmt.executeQuery();
			AccountHolder temp = new AccountHolder(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
		return temp;
	}
}
