package com.innotec.bats.general;

import java.util.Objects;

public class Employee extends Person {
    private String employeeNo;
    public static final int EMPLOYEE_NO_LEN = 5;

    public Employee() {
    }

    public Employee(String name, String surname, String idNumber, String employeeNo) {
        super(name, surname, idNumber);
        this.employeeNo = employeeNo;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeNo, employee.employeeNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeNo);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNo='" + employeeNo + '\'' +
                super.toString() +
                '}';
    }
}