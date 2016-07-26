package com.innotec.bats.server.DAO;

import com.innotec.bats.general.Account;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.AdminCard;
import com.innotec.bats.general.Card;
import com.innotec.bats.general.CurrentAccount;
import com.innotec.bats.general.Employee;
import com.innotec.bats.general.Transaction;

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
	private static final String ADD_ACCOUNTHOLDERCARD = "insert into accountHolderCardtb values (?,?,?,?,?);";
	private static final String ADD_CURRENTACCOUNT = "insert into currentaccounttbl values (?,?,?,?,?,?);";
	private static final String ADD_CREDITCARDACCOUNT = "insert into creditcardaccounttbl values();";
	private static final String ADD_SAVINGSACCOUNT = "insert into savingsaccount values();";
	private static final String ADD_EMPLOYEE = "insert into employeetbl values (?,?,?,?,?);";
	private static final String ADD_ADMINCARD = "insert into adminCardtbl values (?,?);";
	private static final String ADD_TRANSACTION = "insert into transactiontbl values (?,?,?,?,?,?);";
	private static final String GET_ACCOUNTHOLDER = "Select * from accountholdertbl where Cardtbl_CardNo = ?;";
	private static final String GET_CARD = "select * form cardtbl where CardNo = ?;";
	private static final String GET_CURRENTACCOUNT = "select * from currentaccounttbl where Cardtbl_CardNo = ?;";
	private static final String GET_SAVINGSACCOUNT = "select * from savingsaccounttbl where CardNo = ?";
	private static final String GET_CREDITCARDACCOUNT = "select 8 from creditcardaccounttbl where cardNo =?";
	private static final String GET_EMPLOYEE = "select * from employeetbl where EmployeeID = ?;";
	private static final String GET_ADMINCARD = "select * from adminCardtbl where AdminID = ?;";
	private static final String GET_TRANSACTIONFORACCOUNT = "select * from transactiontbl where AccountID = ?;";
	private static final String GET_ATM = "select * from atmtbl where ATMID = ?;";
	private static final String CARD_ACTIVE = "update cardtbl set Card_Active = ? where CardNo = ?;";
	private static final String ADD_ATM = "insert into atmtbl values (?,?)";
	private static final String CHANGE_PIN = "update cardtbl set Card_PIN = ? where CardNo = ?;";
	private static final String ACCOUNT_ACTIVITY = "update accounttbl set isActive = ? where AccountNo = ?;";
	private static final String GET_TRANSACTIONFORATM = "select * from transactiontbl where ATMID = ?;";
	private static final String GET_HOLDERFROMID = "select * from acountholdertbl where AcountHolderID = ? ";
	private PreparedStatement pStmt;
	private MySQL_connection conn;
	private ResultSet rs;
	@Override
	public void addAccountHolder(AccountHolder newHolder) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addCard(Card newCard) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addAccount(com.innotec.bats.server.DAO.Account newAccount) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addEmployee(Employee newEmployee) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addAdmin(AdminCard newAdmin) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTransaction(Transaction newTransaction) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addATM(ATM newATM) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public AccountHolder getAccountHolder(String cardNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<com.innotec.bats.server.DAO.Account> getAccount(
			String cardNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Card getCard(String cardNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Employee getEmployee(String employeeID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AdminCard getAdmin(String adminID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Transaction getTransactionForAccount(String accountID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ATM getATM(int atmID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateCardActivity(boolean cardActivity, String cardNo) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean changePIN(String newPIN, String cardNo) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateAccountActivity(boolean activity, String accountNo) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Transaction getTransactionForATM(int atmID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
