package com.innotec.bats.general;

/**
 * Created by phoenix on 7/20/16.
 */
public class ATMAdmin extends Employee {
    private Card card;
    public ATMAdmin(String idNo, String name, String surname, String employeeNo) {
        super(idNo,name,surname,employeeNo);
        card = null;
    }
    public ATMAdmin(String idNo, String name, String surname, String employeeNo, Card card) {
        this(idNo,name,surname,employeeNo);
        this.card = card;
    }
    public Card getCard() {return card;}
    public void setCard(Card newCard) {card = newCard;}
    public String toString() {return super.toString() + "(ATM Administrator)";}
}
