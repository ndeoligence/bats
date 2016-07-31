package com.innotec.bats.server.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.innotec.bats.general.*;

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
	private static final String CARD_ACTIVE = "update cardtbl set active = ? where CardNo = ?;";
	private static final String CHANGE_PIN = "update cardtbl set cardPIN = ? where cardNo = ?;";
	private static final String ACCOUNT_ACTIVITY = "update accounttbl set active = ? where accountNo = ?;";

	//private static final String GET_ATM = "select * from atmtbl where ATMID = ?;";
	//private static final String ADD_ATM = "insert into atmtbl values (?,?)";
	//private static final String GET_TRANSACTIONFORATM = "select * from transactiontbl where ATMID = ?;";
	
	  private PreparedStatement pStmt;
	private MySQL_connection conn;
	private ResultSet rs;
	
	public DAO_Class()
	{
		conn = new MySQL_connection();
	}

	@Override
	public AccountHolder getAccountHolderByIdNo(String idNo)
	{

		AccountHolder temp = null;
		
			try {
				pStmt = conn.getConnection().prepareStatement(
						GET_ACCOUNTHOLDERBYID);
				pStmt.setString(1, idNo);
				rs = pStmt.executeQuery();
				String name = null, surname = null;
				rs.next();
				for (int pos = 0; pos < rs.getString("accountHolderName")
						.length(); pos++) {
					if (rs.getString("accountHolderName").charAt(pos) == ' ') {
						name = rs.getString(2).substring(0, pos);
						surname = rs.getString(2).substring(pos,
								rs.getString(2).length());
					}
				}
				temp = new AccountHolder(rs.getString(1), name, surname,
						rs.getString(3), rs.getString(4));
				rs.close();
			} catch (SQLException e) {
				return null;
			}
		return temp;
	}
	@Override
	public AccountHolderCard getAccountHolderCardByCardNo(String cardNo)
	{
		AccountHolderCard temp = null;
		
		try {
			pStmt = conn.getConnection().prepareStatement(
					GET_ACCOUNTHOLDERCARDBYCARD);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			rs.next();
			temp = new AccountHolderCard(rs.getString(1), rs.getString(3),
					rs.getBoolean(2), rs.getString(4));
			rs.close();
		} catch (SQLException e) {
			return null;
		}
	return temp;
	}

	@Override
	public void addAccountHolder(AccountHolder newHolder,
			String tellerId) 
	{
		try {
			pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNTHOLDER);
			pStmt.setString(1, newHolder.getIdNo());
			pStmt.setString(2, newHolder.getName()+" "+newHolder.getSurname());
			pStmt.setString(3, newHolder.getAddress());
			pStmt.setString(4, newHolder.getContactNo());
			//pStmt.setString(5, tellerId);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addAccountHolderCard(AccountHolderCard newCard)
	{
		try {
			pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNTHOLDERCARD);
			pStmt.setString(1, newCard.getCardNo());
			pStmt.setBoolean(2, newCard.isActive());
			pStmt.setString(3, newCard.getPinNo());
			pStmt.setString(4, newCard.getAccountHolderIdNo());
			pStmt.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
}