package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DAO_Class implements DAO_Interface
{
	private static final String SAVINGS_ACCOUNT = "Savings Account";
	private static final String CURRENT_ACCOUNT = "Current Account";
	private static final String CREDIT_ACCOUNT = "Credit Card Account";
	
	private static final String ADD_ACCOUNTHOLDER = "insert into accountholdertbl values(?,?,?,?,?); ";
	private static final String ADD_ACCOUNTHOLDERCARD = "insert into accountHolderCardtb values (?,?,?,?,?);";
	private static final String ADD_CURRENTACCOUNT = "insert into accounttbl values (?,?,?,?,?,?,?,null,null,?,?);";
	private static final String ADD_CREDITCARDACCOUNT = "insert into accounttbl values(?,?,?,?,?,?,?,null,null,?,?);";
	private static final String ADD_SAVINGSACCOUNT = "insert into accounttbl values(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String ADD_EMPLOYEE = "insert into employeetbl values (?,?);";
	private static final String ADD_ADMINCARD = "insert into adminCardtbl values (?,?,?,?);";
	private static final String ADD_TRANSACTION = "insert into transactiontbl values (?,?,?,?,?);";
	
	private static final String GET_ACCOUNTHOLDERBYCARD = "Select * from accountholdertbl where accountHolderCardtbl.cardNo = ?;";
	private static final String GET_ACCOUNTHOLDERBYID = "Select * from accountholdertbl where accountHolderID = ?;";
	private static final String GET_ACCOUNTHOLDERCARDBYCARD = "select * form accountHolderCardtbl where cardNo = ?;";
	private static final String GET_ACCOUNTHOLDERCARDBYID = "select * form accountHolderCardtbl where accountHolderID = ?;";
	private static final String GET_CURRENTACCOUNT = "select * from accounttbl where accountHolderID=?, type =?;";
	private static final String GET_SAVINGSACCOUNT = "select * from accounttbl where accountHolderID = ?,type =?;";
	private static final String GET_CREDITCARDACCOUNT = "select * from accounttbl where accountHolderID =?, type =?;";
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

	@Override
	public AccountHolder getAccountHolderByCardNo(String cardNo) {

		AccountHolder temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDERBYCARD);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			String name = null, surname = null;
			for (int pos = 0; pos < rs.getString(2).length(); pos++) {
				if (rs.getString(2).charAt(pos) == ' ') {
					name = rs.getString(2).substring(0, pos);
					surname = rs.getString(2).substring(pos,
							rs.getString(2).length());
				}
			}
			temp = new AccountHolder(rs.getString(1), name,
					surname, rs.getString(3), rs.getString(4));

		} catch (Exception e) {
		}
		return temp;
	}
	@Override
	public AccountHolder getAccountHolderByidNo(String idNo) {

		AccountHolder temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDERBYID);
			pStmt.setString(1, idNo);
			rs = pStmt.executeQuery();
			String name = null, surname = null;
			for (int pos = 0; pos < rs.getString(2).length(); pos++) {
				if (rs.getString(2).charAt(pos) == ' ') {
					name = rs.getString(2).substring(0, pos);
					surname = rs.getString(2).substring(pos,
							rs.getString(2).length());
				}
			}
			temp = new AccountHolder(rs.getString(1), name,
					surname, rs.getString(3), rs.getString(4));

		} catch (Exception e) {
		}
		return temp;
	}
	@Override
	public AccountHolderCard getAccountHolderCardByCardNo(String cardNo) {
		AccountHolderCard tempCard = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDERCARDBYCARD);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			tempCard = new AccountHolderCard(rs.getString(1), rs.getString(4),rs.getBoolean(3), rs.getString(5));
		} catch (Exception e) {
		}
		return tempCard;
	}
	@Override
	public AccountHolderCard getAccountHolderCardByIdNo(String idNo) {
		AccountHolderCard tempCard = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDERCARDBYID);
			pStmt.setString(1, idNo);
			rs = pStmt.executeQuery();
			tempCard = new AccountHolderCard(rs.getString(1), rs.getString(4),rs.getBoolean(3), rs.getString(5));
		} catch (Exception e) {
		}
		return tempCard;
	}
	@Override
	public AdminCard getAdminCardById(String idNo) {

		AdminCard temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ADMINCARDBYID);
			pStmt.setString(1, idNo);
			 temp = new AdminCard(rs.getString(1),rs.getString(2),rs.getBoolean(4),rs.getString(3));
			rs = pStmt.executeQuery();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	@Override
	public AdminCard getAdminCardByCardNo(String cardNo) {
		AdminCard temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ADMINCARDBYCARD);
			pStmt.setString(1, cardNo);
			 temp = new AdminCard(rs.getString(1),rs.getString(2),rs.getBoolean(4),rs.getString(3));
			rs = pStmt.executeQuery();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	@Override
	public CreditCardAccount getCreditCardAccount(String accountHolderIdNo)
	{
		CreditCardAccount temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_CREDITCARDACCOUNT);
			pStmt.setString(1, accountHolderIdNo);
			rs = pStmt.executeQuery();
				 temp = new CreditCardAccount(rs.getString(1),rs.getDouble(3),rs.getBoolean(6),rs.getDouble(4),rs.getDouble(5),rs.getDouble(7),rs.getString(10));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	@Override
	public CurrentAccount getCurrentAccount(String accountHolderIdNo) {
		CurrentAccount temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_CURRENTACCOUNT);
			pStmt.setString(1, accountHolderIdNo);
			rs = pStmt.executeQuery();
		//	?,?,?,?,?,?,?,null,null,?,?
				 temp = new CurrentAccount(rs.getString(1),rs.getDouble(3),rs.getBoolean(6),rs.getDouble(4),rs.getDouble(5),rs.getDouble(7),rs.getString(10));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	@Override
	public SavingsAccount getSavingsAccount(String accountHolderIdNo) {
		SavingsAccount temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_CURRENTACCOUNT);
			pStmt.setString(1, accountHolderIdNo);
			rs = pStmt.executeQuery();
				 temp = new SavingsAccount(rs.getString(1),rs.getDouble(3),rs.getBoolean(6),rs.getDouble(4),rs.getDouble(5),rs.getString(10));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	@Override
	public List<Account> getAccounts(String accountHolderIdNo) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTS);
			pStmt.setString(1, accountHolderIdNo);
			rs = pStmt.executeQuery();
			while(rs.next())
			{
				Account temp = new Account(rs.getString(1),rs.getDouble(2),rs.getBoolean(5),rs.getDouble(3),rs.getDouble(4),rs.getString(7));
				accounts.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}

}