package com.innotec.bats.general;

/**
 * Created by phoenix on 7/20/16.
 */
public class ATMAdmin extends Employee {
    private AdminCard card;
    public ATMAdmin(String idNo, String name, String surname, String employeeNo) {
        super(idNo,name,surname,employeeNo);
        card = null;
    }
    public ATMAdmin(String idNo, String name, String surname, String employeeNo, AdminCard card) {
        this(idNo,name,surname,employeeNo);
        this.card = card;
    }
    public AdminCard getCard() {return card;}
    public void setCard(AdminCard newCard) {card = newCard;}
    public String toString() {return super.toString() + "(ATM Administrator)";}
}
