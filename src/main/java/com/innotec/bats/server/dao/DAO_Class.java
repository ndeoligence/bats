package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class DAO_Class implements DAO_Interface
{
	private static final String SAVINGS_ACCOUNT = "Savings Account";
	private static final String CURRENT_ACCOUNT = "Current Account";
	private static final String CREDIT_ACCOUNT = "Credit Card Account";
	
	private static final String ADD_ACCOUNTHOLDER = "insert into accountholdertbl values(?,?,?,?,?); ";
	private static final String ADD_ACCOUNTHOLDERCARD = "insert into accountHolderCardtb values (?,?,?,?,?);";
	private static final String ADD_CURRENTACCOUNT = "insert into currentaccounttbl values (?,?,?,?,?,?,?);";
	private static final String ADD_CREDITCARDACCOUNT = "insert into creditcardaccounttbl values(?,?,?,?,?,?);";
	private static final String ADD_SAVINGSACCOUNT = "insert into savingsaccount values(?,?,?,?,?,?,?,?);";
	private static final String ADD_EMPLOYEE = "insert into employeetbl values (?,?,?,?);";
	private static final String ADD_ADMINCARD = "insert into adminCardtbl values (?,?,?,?);";
	private static final String ADD_TRANSACTION = "insert into transactiontbl values (?,?,?,?,?,?,?);";
	private static final String GET_ACCOUNTHOLDER = "Select * from accountholdertbl where Cardtbl_CardNo = ?;";
	private static final String GET_CARD = "select * form cardtbl where CardNo = ?;";
	private static final String GET_CURRENTACCOUNT = "select * from currentaccounttbl where Cardtbl_CardNo = ?;";
	private static final String GET_SAVINGSACCOUNT = "select * from savingsaccounttbl where CardNo = ?";
	private static final String GET_CREDITCARDACCOUNT = "select * from creditcardaccounttbl where cardNo =?";
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
	public void addAccountHolder(AccountHolder newHolder) 
	{
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNTHOLDER);
			pStmt.setString(1, newHolder.getIdNo());
			pStmt.setString(2, newHolder.getName()+" "+ newHolder.getSurname());
			pStmt.setString(3, newHolder.getAddress());
			pStmt.setString(4, newHolder.getContactNo());
			pStmt.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}


	@Override
	public void addTransaction(Transaction newTransaction) {
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_TRANSACTION);
			pStmt.setDouble(3, newTransaction.getAmount());
//			pStmt.setString(3, newTransaction.getATMID());
			String accountType = newTransaction.getPrimAccountNo().substring(3, 5);
			if(accountType.equals("10"))
			{
			pStmt.setString(7, newTransaction.getPrimAccountNo());
			}
			else
			{
				if(accountType.equals("20"))
				{
					pStmt.setString(6, newTransaction.getPrimAccountNo());
				}
				else
				{
					if(accountType.equals("30"))
					{
						pStmt.setString(5, newTransaction.getPrimAccountNo());
					}
				}
			}
			pStmt.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void addSavingsAccount(SavingsAccount newSavingsAccount) {
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_SAVINGSACCOUNT);
			pStmt.setString(1, newSavingsAccount.getAccountNo());
			pStmt.setDouble(2, newSavingsAccount.getBalance());
			pStmt.setDouble(3, newSavingsAccount.getMaxWithdrawalPerDay());
			pStmt.setDouble(4, newSavingsAccount.getMIN_BALANCE());
			pStmt.setBoolean(5, newSavingsAccount.isActive());
			pStmt.setDouble(6, newSavingsAccount.getMaxTransferPerDay());
			pStmt.setString(7, newSavingsAccount.getCardNo());
			pStmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void addCreditCardAccount(CreditCardAccount newCreditCardAccount) {
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_CREDITCARDACCOUNT);
			pStmt.setString(1, newCreditCardAccount.getAccountNo());
			pStmt.setDouble(2, newCreditCardAccount.getBalance());
			pStmt.setDouble(3, newCreditCardAccount.getMaxWithdrawalPerDay());
			pStmt.setDouble(4, newCreditCardAccount.getMaxTransferPerDay());
			pStmt.setBoolean(5, newCreditCardAccount.isActive());
			pStmt.setString(7, newCreditCardAccount.getCardNo());
			pStmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public AccountHolder getAccountHolder(String cardNo)
	{
		AccountHolder temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ACCOUNTHOLDER);
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
	public ArrayList<CurrentAccount> getCurrentAccount(String cardNo) 
	{
		ArrayList<CurrentAccount> currentAccounts = new ArrayList<CurrentAccount>();
		
		try {
			pStmt = conn.getConnection().prepareStatement(GET_CURRENTACCOUNT);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			while(rs.next())
			{
				CurrentAccount temp = new CurrentAccount(rs.getString(1),rs.getDouble(2),rs.getBoolean(5),rs.getDouble(4),
						rs.getDouble(3),rs.getDouble(6),rs.getString(7));
				currentAccounts.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currentAccounts;
	}
	
	public ArrayList<SavingsAccount> getSavingsAccount(String cardNo)
	{
		ArrayList<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
		try {
			pStmt = conn.getConnection().prepareStatement(GET_SAVINGSACCOUNT);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			while(rs.next())
			{
				SavingsAccount temp = new SavingsAccount(rs.getString(1),rs.getDouble(2),rs.getBoolean(5),rs.getDouble(3),
						rs.getDouble(4),rs.getString(7));
				savingsAccounts.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return savingsAccounts;
	}
	
	public ArrayList<CreditCardAccount> getCreditCardAccounts(String cardNo)
	{
		ArrayList<CreditCardAccount> creditCardAccounts = new ArrayList<CreditCardAccount>();
		try {
			pStmt = conn.getConnection().prepareStatement(GET_CREDITCARDACCOUNT);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			while(rs.next())
			{
				CreditCardAccount temp = new CreditCardAccount(rs.getString(1),rs.getDouble(2),rs.getBoolean(5),rs.getDouble(3),rs.getDouble(4)
						,rs.getDouble(7),rs.getString(6));
				creditCardAccounts.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return creditCardAccounts;
	}
	@Override
	public AccountHolderCard getCard(String cardNo) {
		AccountHolderCard tempCard = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_CARD);
			pStmt.setString(1, cardNo);
			rs = pStmt.executeQuery();
			tempCard = new AccountHolderCard(rs.getString(1), rs.getString(4),
					rs.getBoolean(3), rs.getString(5));
		} catch (Exception e) {
		}
		return tempCard;
	}
	@Override
	public Employee getEmployee(String employeeID) {
		Employee temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_EMPLOYEE);
			pStmt.setString(1, employeeID);
			rs = pStmt.executeQuery();
			String name = null, surname = null;
			for (int pos = 0; pos < rs.getString(2).length(); pos++) {
				if (rs.getString(2).charAt(pos) == ' ') {
					name = rs.getString(2).substring(0, pos);
					surname = rs.getString(2).substring(pos,
							rs.getString(2).length());
				}
			}
			temp = new Employee(rs.getString(1), name, surname,
					rs.getString(3), rs.getString(4));
		} catch (Exception e) {
		}
		return temp;
	}
	@Override
	public AdminCard getAdmin(String adminID)
	{
		AdminCard temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(GET_ADMINCARD);
			pStmt.setString(1, adminID);
			 temp = new AdminCard(rs.getString(1),rs.getString(2),rs.getBoolean(4),rs.getString(3));
			rs = pStmt.executeQuery();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return temp;
	}
	@Override
	public Transaction getTransactionForAccount(String accountID) 
	{
		Transaction temp = null;
		try {
			pStmt = conn.getConnection().prepareStatement(
					GET_TRANSACTIONFORACCOUNT);
			pStmt.setString(1, accountID);
			rs = pStmt.executeQuery();
			String account = null;
			if ((!((rs.getString(5)) == null))) {
				account = rs.getString(5);
			} else {
				if ((!((rs.getString(6)) == null))) {
					account = rs.getString(6);
				} else {
					if ((!((rs.getString(7)) == null))) {
						account = rs.getString(7);
					}
				}
			}
			 temp = new Transaction( account,
					rs.getDouble(3));
		} catch (Exception e) {
		}
		return temp;
	}
	@Override
	public boolean updateCardActivity(boolean cardActivity, String cardNo) 
	{
		boolean activity = false;
		try {
			pStmt = conn.getConnection().prepareStatement(CARD_ACTIVE);
			pStmt.setBoolean(1, cardActivity);
			pStmt.setString(2, cardNo);
			rs = pStmt.executeQuery();
			if(rs.getBoolean(3) == true)
			{
				activity =  true;
			}
			else
			{
				activity = false;
			}
		} catch (Exception e) {
		}
		return activity;
	}
	@Override
	public boolean changePIN(String newPIN, String cardNo) {
		boolean activity = false;
		try {
			pStmt = conn.getConnection().prepareStatement(CHANGE_PIN);
			pStmt.setString(1,newPIN);
			pStmt.setString(2,cardNo);
			rs = pStmt.executeQuery();
			if(rs.getString(4).equals(newPIN))
			{
				activity =  true;
			}
			else
			{
				activity = false;
			}
		} catch (Exception e) {
		}
		return activity;
	}
	@Override
	public boolean updateAccountActivity(boolean accountActivity, String accountNo) {
		boolean activity = false;
		try {
			pStmt = conn.getConnection().prepareStatement(CARD_ACTIVE);
			pStmt.setBoolean(1, accountActivity);
			pStmt.setString(2,accountNo);
			rs = pStmt.executeQuery();
			if(rs.getBoolean(5) == true)
			{
				activity =  true;
			}
			else
			{
				activity = false;
			}
		} catch (Exception e) {
		}
		return activity;
	}
	public void addAccountHolderCard(AccountHolderCard newCard)
	{
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNTHOLDERCARD);
			pStmt.setString(1, newCard.getCardNo());
			pStmt.setBoolean(3, newCard.isActive());
			pStmt.setString(4, newCard.getPin());
			pStmt.setString(5, newCard.getAccountHlderID());
			pStmt.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void addCurrentAccount(CurrentAccount newCurrentAccount) {
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_CURRENTACCOUNT);
			pStmt.setString(1, newCurrentAccount.getAccountNo());
			pStmt.setDouble(2, newCurrentAccount.getBalance());
			pStmt.setDouble(3, newCurrentAccount.getMaxWithdrawalPerDay());
			pStmt.setBoolean(5, newCurrentAccount.isActive());
			pStmt.setDouble(6, newCurrentAccount.getMaxTransferPerDay());
			pStmt.setString(7, newCurrentAccount.getCardNo());
			pStmt.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void addEmployee(Employee newEmployee)
	{
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNTHOLDERCARD);
			pStmt.setString(1, newEmployee.getIdNo());
			pStmt.setString(2, newEmployee.getName()+newEmployee.getSurname());
			pStmt.setString(3, newEmployee.getAddress());
			pStmt.setString(4, newEmployee.getContactNo());
			pStmt.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	@Override
	public void addAdmin(AdminCard newAdmin) {
		try
		{
			pStmt = conn.getConnection().prepareStatement(ADD_ACCOUNTHOLDERCARD);
			pStmt.setString(1, newAdmin.getEmployeeNo());
			pStmt.setString(2, newAdmin.getCardNo());
			pStmt.setString(3, newAdmin.getPin());
			pStmt.setBoolean(4, newAdmin.isActive());
			pStmt.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
}
