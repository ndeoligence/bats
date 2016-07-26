package com.innotec.bats.general;

import java.io.Serializable;

/**
 * Created by phoenix on 7/18/16.
 */
public class Card implements Serializable
{
	private String cardNo;
	private String pinNo;
	private boolean active;

	public Card (String cardNo, String pinNo, boolean active)
	{
		this.cardNo = cardNo;
		this.pinNo = pinNo;
		this.active = active;
	}

	public String getCardNo ()
	{
		return cardNo;
	}

	public String getPinNo ()
	{
		return pinNo;
	}

	public boolean isActive ()
	{
		return active;
	}

	public void setCardNo (String cardNo)
	{
		this.cardNo = cardNo;
	}

	public void setPin (String pin)
	{
		this.pinNo = pinNo;
	}

	public void setActive (boolean active)
	{
		this.active = active;
	}

	public String toString ()
	{
		return "Card; No.: " + cardNo;
	}
}
