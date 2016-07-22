package com.innotec.bats.client.atm.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.innotec.bats.general.Card;
import com.innotec.bats.general.CardRetrieval;

public class ATM_ServerComm
{
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private Card card = null;
	private Object serverObject = null;
	//method takes in cardRetrieval object & sends to server
	//method receives object from server & casts it to card be a object
	//open/close connection methods
	public ATM_ServerComm()
	{
		
	}
	
	public Card sendCardRetrieval(CardRetrieval cardRetrieval)
	{
			try
			{
				oos.writeObject(cardRetrieval);
				oos.flush();
				card = (Card)this.getServerObject();
				return card;
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				card = null;
				return card;
			}		
	}
	
	public Object getServerObject()
	{
		try
		{
			serverObject = ois.readObject();
			//card = (Card)serverObject;
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
		//return card;	
		return serverObject;
	}
	
	public boolean openConnection()
	{
		try
		{
			socket = new Socket("localhost", 13700);
			
			ois = new ObjectInputStream(socket.getInputStream());
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			
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
