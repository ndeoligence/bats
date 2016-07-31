package com.innotec.bats.general;

/**
 * Created by phoenix on 7/21/16.
 */
public class AccountHolderCard extends Card {
    public static final int CARD_NO_LEN = 16;
    private String accountHolderId;
    public AccountHolderCard(String cardNo, String pinNo, boolean active,String accountHolderId) {
        super(cardNo, pinNo, active);
        this.accountHolderId =accountHolderId;
    }

    @Override
    public String toString() {
        return "AccountHolderCard{"+super.toString()+"}";
    }

	public String getAccountHolderId() {
		return accountHolderId;
	}

	public void setAccountHolderId(String accountHolderId) {
		this.accountHolderId = accountHolderId;
	}
}
