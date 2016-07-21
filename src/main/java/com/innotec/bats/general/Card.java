package com.innotec.bats.general;

import java.io.Serializable;

/**
 * Created by phoenix on 7/18/16.
 */
public class Card implements Serializable {
	private String cardNo;
	private String pin;
	private boolean active;
	public Card(String cardNo, String pin, boolean active) {
		this.cardNo=cardNo;
		this.pin=pin;
		this.active=active;
	}
	public String getCardNo() {return cardNo;}
	public String getPin() {return pin;}
	public boolean isActive() {return active;}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
    public String toString() {return "Card; No.: " + cardNo;}
}
