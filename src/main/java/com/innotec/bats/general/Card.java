package com.innotec.bats.general;

import java.util.ArrayList;

public class Card
{
	private String cardNo, pinNo;
	private boolean active;
	private ArrayList<String> accountNos;
	
	public Card(String cardNo, String pinNo, boolean active, ArrayList<String> accountNos)
	{
		this.cardNo = cardNo;
		this.pinNo = pinNo;
		this.accountNos = accountNos;
		this.active = active;
	}

	public String getCardNo()
	{
		return cardNo;
	}

	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}

	public String getPinNo()
	{
		return pinNo;
	}

	public void setPinNo(String pinNo)
	{
		this.pinNo = pinNo;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public String toString()
	{
		return "Card [cardNo=" + cardNo + ", pinNo=" + pinNo + ", active="
				+ active + "]";
	}
	
	
}
