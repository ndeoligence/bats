package com.innotec.bats.general;

import java.util.ArrayList;

public class AccountHolder extends Person
{
    private String address;
    private String contactNo;
    private Card card;
    private ArrayList<Account> accounts;
    
    public AccountHolder (String name, String surname, String idNo, String address, String contactNo) 
    {
        super (name, surname, idNo);
        
        this.address = new String(address);
        this.contactNo = new String(contactNo);
        accounts = new ArrayList<Account>();
    }
    
    public AccountHolder (String name, String surname, String idNo, String address, String contactNo, Card card) 
    {
        super (name, surname, idNo);
        this.card = card;
        this.address = new String(address);
        this.contactNo = new String(contactNo);
        accounts = new ArrayList<Account>();
    }

    public boolean addAccount (Account newAccount)
    {
        accounts.add(newAccount);
        return true;
    }
    
    public boolean addAccountArrayList(ArrayList accountArrayList)
    {
        accounts = accountArrayList;
        return true;
    }
    
    public boolean addCard (Card card)
    {
    	this.card = card;
    	return true;
    }
    
    public Card getCard()
    {
    	return card;
    }
    
	public String getAddress ()
	{
		return address;
	}

	public void setAddress (String address)
	{
		this.address = address;
	}

	public String getContactNo ()
	{
		return contactNo;
	}

	public void setContactNo (String contactNo)
	{
		this.contactNo = contactNo;
	}

	public ArrayList<Account> getAccounts ()
	{
		return accounts;
	}

	public void setAccounts (ArrayList<Account> accounts)
	{
		this.accounts = accounts;
	}
    
	public String toString ()
	{
		return "AccountHolder Details= \t" + super.toString() +"\taddress: "+ address + "\tcontactNo: " + contactNo;
	}
    
}
