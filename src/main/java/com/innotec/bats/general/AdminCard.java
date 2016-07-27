package com.innotec.bats.general;

/**
 * Created by phoenix on 7/21/16.
 */
public class AdminCard extends Card {
    public static final int CARD_NO_LEN = 8;
    private String employeeNo;
    public AdminCard(String cardNo, String pinNo, boolean active, String employeeNo) {
        super(cardNo, pinNo, active);
        this.employeeNo = employeeNo;
    }
    public String getEmployeeNo() {return employeeNo;}
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
