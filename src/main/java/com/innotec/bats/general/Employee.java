package com.innotec.bats.general;

/**
 * Created by phoenix on 7/20/16.
 */
public class Employee extends Person {
    private String employeeNo;
    public Employee(String name, String surname, String idNo, String employeeNo) {
        super(name, surname, idNo);
        this.employeeNo = employeeNo;
    }
    public String getEmployeeNo() {return employeeNo;}
}
