package com.innotec.bats.client.atm.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.innotec.bats.general.*;

public class ATM_ServerComm {
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private Card card = null;
	private Object serverObject = null;
	private AccountHolder accountHolder;
	private final String ATM_ID, ATM_Password;
	private ArrayList statement;
	private Account account;

	// method takes in cardRetrieval object & sends to server
	// method receives object from server & casts it to card be a object
	// open/close connection methods
	public ATM_ServerComm() {
		ATM_ID = "13701";
		ATM_Password = "chiroptera13701";
	}

	public String getATM_ID() {
		return ATM_ID;
	}

	public Card sendCardRetrieval(CardRetrieval cardRetrieval) {
		try {
			oos.writeObject(cardRetrieval);
			oos.flush();
			card = (Card) this.getServerObject();
			return card;
		} catch (IOException e) {
			e.printStackTrace();
			card = null;
			return card;
		}
	}

	public AccountHolder sendAccountHolderRetrievalByCardNo(
			AccountHolderRetrievalByCardNo accountHolderRetrievalByCardNo) {
		try {
			oos.writeObject(accountHolderRetrievalByCardNo);
			oos.flush();
			accountHolder = (AccountHolder) this.getServerObject();
			return accountHolder;
		} catch (IOException e) {
			e.printStackTrace();
			accountHolder = null;
			return accountHolder;
		}
	}

	public AccountHolder sendAccountHolderRetrievalByIdNo(AccountHolderRetrievalByIdNo accountHolderRetrievalByIdNo) {
		try {
			oos.writeObject(accountHolderRetrievalByIdNo);
			oos.flush();
			accountHolder = (AccountHolder) this.getServerObject();
			return accountHolder;
		} catch (IOException e) {
			e.printStackTrace();
			accountHolder = null;
			return accountHolder;
		}
	}

	public AccountHolder sendAccountHolderRetrievalByAccountNo(
			AccountHolderRetrievalByAccountNo accountHolderRetrievalByAccountNo) {
		try {
			oos.writeObject(accountHolderRetrievalByAccountNo);
			oos.flush();
			accountHolder = (AccountHolder) this.getServerObject();
			return accountHolder;
		} catch (IOException e) {
			e.printStackTrace();
			accountHolder = null;
			return accountHolder;
		}
	}

	public boolean sendAccountHolderCreation(AccountHolderCreation accountHolderCreation) {
		try {
			oos.writeObject(accountHolderCreation);
			oos.flush();
			return (boolean) this.getServerObject();
		} catch (IOException e) {
			accountHolder = null;
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendAccountCreation(AccountCreation accountCreation) {
		try {
			oos.writeObject(accountCreation);
			oos.flush();
			return (boolean) this.getServerObject();

		} catch (IOException e) {
			accountHolder = null;
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendAccountClosure(AccountClosure accountClosure) {
		try {
			oos.writeObject(accountClosure);
			oos.flush();
			boolean tf = (Boolean) this.getServerObject();

			return tf;
		} catch (IOException e) {
			e.printStackTrace();
			boolean tf = false;
			return tf;
		}
	}

	public boolean sendWithdrawal(Withdrawal withdrawal) {
		try {
			oos.writeObject(withdrawal);
			oos.flush();
			boolean tf = (Boolean) this.getServerObject();

			return tf;
		} catch (IOException e) {
			e.printStackTrace();
			boolean tf = false;
			return tf;
		}
	}

	public boolean sendDeposit(Deposit deposit) {
		try {
			oos.writeObject(deposit);
			oos.flush();
			boolean tf = (Boolean) this.getServerObject();

			return tf;
		} catch (IOException e) {
			e.printStackTrace();
			boolean tf = false;
			return tf;
		}

	}

	public boolean sendTransfer(Transfer transfer) {
		try {
			oos.writeObject(transfer);
			oos.flush();
			boolean tf = (Boolean) this.getServerObject();

			return tf;
		} catch (IOException e) {
			e.printStackTrace();
			boolean tf = false;
			return tf;
		}

	}

	public boolean sendPINChange(PINChange pinChange) {
		try {
			oos.writeObject(pinChange);
			oos.flush();
			boolean tf = (Boolean) this.getServerObject();

			return tf;
		} catch (IOException e) {
			e.printStackTrace();
			boolean tf = false;
			return tf;
		}

	}

	public ArrayList sendStatementRetrieval(StatementRetrieval statementRetrieval) {
		try {
			oos.writeObject(statementRetrieval);
			oos.flush();
			statement = (ArrayList) this.getServerObject();
			return statement;
		} catch (IOException e) {
			e.printStackTrace();
			statement = null;
			return statement;
		}
	}

	public boolean sendCardDeactivation(CardDeactivation cardDeactivation) {
		try {
			oos.writeObject(cardDeactivation);
			oos.flush();
			boolean tf = (Boolean) this.getServerObject();

			return tf;
		} catch (IOException e) {
			e.printStackTrace();
			boolean tf = false;
			return tf;
		}
	}

	public boolean sendCardReactivation(CardReactivation cardReactivation) {
		try {
			oos.writeObject(cardReactivation);
			oos.flush();
			boolean tf = (Boolean) this.getServerObject();

			return tf;
		} catch (IOException e) {
			e.printStackTrace();
			boolean tf = false;
			return tf;
		}
	}

	public boolean sendBalanceSheetRequest(BalanceSheetRequest balanceSheetRequest) {
		try {
			oos.writeObject(balanceSheetRequest);
			oos.flush();
			boolean tf = (Boolean) this.getServerObject();

			return tf;
		} catch (IOException e) {
			e.printStackTrace();
			boolean tf = false;
			return tf;
		}
	}

	public void sendSessionTermination(SessionTermination sessionTermination) {
		try {
			oos.writeObject(sessionTermination);
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public Object getServerObject() {
		try {
			serverObject = ois.readObject();
			System.out.println("Object retrieved" + serverObject);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			serverObject = null;
		} catch (IOException e) {
			e.printStackTrace();
			serverObject = null;
		}
		return serverObject;
	}

	public boolean openConnection() {
		try {
			socket = new Socket("192.168.43.104", 13700);
			// 192.168.43.72

			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean closeConnection() {
		try {
			oos.close();
			ois.close();
			socket.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
