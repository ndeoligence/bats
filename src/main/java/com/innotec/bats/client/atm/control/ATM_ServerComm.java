package com.innotec.bats.client.atm.control;

import com.innotec.bats.general.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ATM_ServerComm
{
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private Card card = null;
	private Object serverObject = null;
	private AccountHolder accountHolder;
	private final String ATM_ID = "13701";
	private final String ATM_Password = "chiroptera13701";;
	private ArrayList statement;
	private Account account;

	//method takes in cardRetrieval object & sends to server
	//method receives object from server & casts it to card be a object
	//open/close connection methods
	public ATM_ServerComm()
	{


	}
	
	public boolean openConnection()
	{
		try
		{
			socket = new Socket("localhost", 13700);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
				
			return true;
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			return false;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	// Are these methods really needed on the ServerCom?
	public Socket getSocket ()
	{
		return socket;
	}

	public ObjectInputStream getOis ()
	{
		return ois;
	}

	public ObjectOutputStream getOos ()
	{
		return oos;
	}

	public Card getCard ()
	{
		return card;
	}

	public AccountHolder getAccountHolder ()
	{
		return accountHolder;
	}

	public String getAtmId ()
	{
		return ATM_ID;
	}

	public ArrayList getStatement ()
	{
		return statement;
	}

	public Account getAccount ()
	{
		return account;
	}

	public Card sendCardRetrieval(CardRetrieval cardRetrieval)
	{
        try
        {
            card = (Card) sendAction(cardRetrieval);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            card = null;
        }
        return card;
	}
	
	public AccountHolder sendAccountHolderRetrievalByCardNo (AccountHolderRetrievalByCardNo accountHolderRetrievalByCardNo)
	{
		try
		{
            accountHolder = (AccountHolder) sendAction(accountHolderRetrievalByCardNo);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			accountHolder = null;
		}
        return accountHolder;
	}
	
	public AccountHolder sendAccountHolderRetrievalByIdNo (AccountHolderRetrievalByIdNo accountHolderRetrievalByIdNo)
	{
		try
		{
            accountHolder = (AccountHolder) sendAction(accountHolderRetrievalByIdNo);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			accountHolder = null;
		}
        return accountHolder;
	}
	
	public AccountHolder sendAccountHolderRetrievalByAccountNo (AccountHolderRetrievalByAccountNo accountHolderRetrievalByAccountNo)
	{
		try
		{
			accountHolder = (AccountHolder) sendAction(accountHolderRetrievalByAccountNo);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			accountHolder = null;
		}
        return accountHolder;
	}
	
	public AccountHolder sendAccountHolderCreation(AccountHolderCreation accountHolderCreation)
	{
		try 
		{
			accountHolder = (AccountHolder) sendAction(accountHolderCreation);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			accountHolder = null;
		}
        return accountHolder;
	}
	
	public Account sendAccountCreation(AccountCreation accountCreation)
	{
		try 
		{
            account = (Account) sendAction(accountCreation);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			account = null;
		}
        return account;
	}
	
	public boolean sendAccountClosure (AccountClosure accountClosure)
	{
		try
		{
		    return (Boolean) sendAction(accountClosure);
		}
		catch (IOException e)
		{
			e.printStackTrace();
            return false;
		}		
	}
	
	public boolean sendWithdrawal (Withdrawal withdrawal)
	{
		try
		{
            return (Boolean) sendAction(withdrawal);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}		
	}
	
	public boolean sendDeposit(Deposit deposit)
	{
		try
		{
            return (Boolean) sendAction(deposit);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}		
		
	}
	
	public boolean sendTransfer(Transfer transfer)
	{
		try
		{
            return (Boolean) sendAction(transfer);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendPINChange(PINChange pinChange)
	{
		try
		{
            return (Boolean) sendAction(pinChange);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}		
		
	}
	
	public ArrayList sendStatementRetrieval(StatementRetrieval statementRetrieval)
	{
		try
		{
			statement = (ArrayList) sendAction(statementRetrieval);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			statement = null;
		}
        return statement;
	}
	
	public boolean sendCardDeactivation (CardDeactivation cardDeactivation)
	{
		try
		{
            return (Boolean) sendAction(cardDeactivation);
		}
		catch (IOException e)
		{
            e.printStackTrace();
			return false;
		}		
	}
	
	public boolean sendCardReactivation (CardReactivation cardReactivation)
	{
		try
		{
            return (Boolean) sendAction(cardReactivation);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}		
	}
	
	public boolean sendBalanceSheetRequest (BalanceSheetRequest balanceSheetRequest)
	{
		try
		{
            return (Boolean) sendAction(balanceSheetRequest);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}		
	}

	private Object sendAction(Action action) throws IOException {
		oos.writeObject(action);
		oos.flush();
		return getServerObject();
	}

	public void sendSessionTermination (SessionTermination sessionTermination)
	{
		try
		{
			oos.writeObject(sessionTermination);
			oos.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();

		}
	}

	public Object getServerObject()
	{
		try
		{
			serverObject = ois.readObject();
			System.out.println("Object retrieved"+ serverObject);

		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			serverObject = null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			serverObject = null;
		}
		return serverObject;
	}
	
	
	
	public boolean closeConnection()
	{
		try
		{
			oos.close();
			ois.close();
			socket.close();
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
