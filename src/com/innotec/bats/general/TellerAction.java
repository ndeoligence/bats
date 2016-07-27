package com.innotec.bats.general;

/**
 * Created by phoenix on 7/20/16.
 */
public class TellerAction extends Action {
    private String employeeNo;    // employee#
    public TellerAction(String employeeNo) {
        this.employeeNo = employeeNo;
    }
    public String getEmployeeNo() {return employeeNo;}
    public String toString() {return "Action by teller: " + employeeNo;}
}
