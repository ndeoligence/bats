package com.innotec.bats.server.dao;

import com.innotec.bats.general.ATMAdmin;
import com.innotec.bats.general.Account;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.AccountHolderCard;
import com.innotec.bats.general.AdminCard;
import com.innotec.bats.general.Card;
import com.innotec.bats.general.CreditCardAccount;
import com.innotec.bats.general.CurrentAccount;
import com.innotec.bats.general.Employee;
import com.innotec.bats.general.SavingsAccount;
import com.innotec.bats.general.Teller;
import com.innotec.bats.general.Transaction;

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
	private static final String ADD_ACCOUNTHOLDERCARD = "insert into accountHolderCardtbl values (?,?,?,?);";
	private static final String ADD_CURRENTACCOUNT = "insert into accounttbl values (?,?,?,?,?,?,?,null,null,?,?);";
	private static final String ADD_CREDITCARDACCOUNT = "insert into accounttbl values(?,?,?,?,?,?,?,null,null,?,?);";
	private static final String ADD_SAVINGSACCOUNT = "insert into accounttbl values(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String ADD_EMPLOYEE = "insert into employeetbl values (?,?);";
	private static final String ADD_ADMINCARD = "insert into adminCardtbl values (?,?,?,?);";
	private static final String ADD_TRANSACTION = "insert into transactiontbl values (?,?,?,?,?);";
	
	private static final String GET_ACCOUNTHOLDERBYCARD = "Select * from accountholdertbl,accountHolderCardtbl where accountHolderCardtbl.accountHolderID = ?;";
	private static final String GET_ACCOUNTHOLDERBYID = "Select * from accountholdertbl where accountHolderID = ?;";
	private static final String GET_ACCOUNTHOLDERCARDBYCARD = "select * from accountHolderCardtbl where cardNo = ?;";
	private static final String GET_ACCOUNTHOLDERCARDBYID = "select * from accountHolderCardtbl where accountHolderID = ?;";
	private static final String GET_CURRENTACCOUNT = "select * from account where accountHolderID=?, type =?;";
	private static final String GET_SAVINGSACCOUNT = "select * from account where accountHolderID = ?,type =?;";
	private static final String GET_CREDITCARDACCOUNT = "select * from account where accountHolderID =?, type =?;";
	private static final String GET_ACCOUNTS = "select * from accounttbl where accountHolderID = ?;";
	private static final String GET_EMPLOYEE = "select * from employeetbl where employeeID = ?;";
	private static final String GET_ADMINCARDBYID = "select * from adminCardtbl where employeeID = ?;";
	private static final String GET_ADMINCARDBYCARD = "select * from adminCardtbl where cardNo = ?;";
	private static final String GET_TRANSACTIONFORACCOUNT = "select * from transactiontbl where accountNo = ?;";
	//private static final String GET_ATM = "select * from atmtbl where ATMID = ?;";
	private static final String CARD_ACTIVE = "update cardtbl set active = ? where CardNo = ?;";
	//private static final String ADD_ATM = "insert into atmtbl values (?,?)";
	private static final String CHANGE_PIN = "update cardtbl set cardPIN = ? where cardNo = ?;";
	private static final String ACCOUNT_ACTIVITY = "update accounttbl set active = ? where accountNo = ?;";
	//private static final String GET_TRANSACTIONFORATM = "select * from transactiontbl where ATMID = ?;";
	private PreparedStatement pStmt;
	private MySQL_connection conn;
	private ResultSet rs;

	public DAO_Class()
	{
		conn = new MySQL_connection();
	}
	@Override
	public AccountHolderCard getAccountHolderCardByCardNo(String cardNo)  throws SQLException
	{
		AccountHolderCard temp = null;

		pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDERCARDBYCARD);
		pStmt.setString(1, cardNo);
		rs = pStmt.executeQuery();
		rs.next();
		// AccountHolderCard(cardNo, cardPinNo, cardActive, accountHolderIdNo)
		// AccountHolderCard(String cardNo, String pinNo, boolean active)
		temp = new AccountHolderCard(rs.getString(1),rs.getString(3),rs.getBoolean(2),rs.getString(4));
		rs.close();
	return temp;
	}
}
