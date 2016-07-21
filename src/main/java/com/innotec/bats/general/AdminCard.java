package com.innotec.bats.general;

/**
 * Created by phoenix on 7/21/16.
 */
public class AdminCard extends Card {
    private String employeeNo;
    public AdminCard(String cardNo, String pin, boolean active, String employeeNo) {
        super(cardNo, pin, active);
        this.employeeNo = employeeNo;
    }
    public String getEmployeeNo() {return employeeNo;}
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
