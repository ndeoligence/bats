package com.innotec.bats.general;

public class Teller extends Employee {
    public Teller() {
    }

    public Teller(String name, String surname, String idNo, String employeeNo) {
        super(name, surname, idNo, employeeNo);
    }

    @Override
    public String toString() {
        return "Teller{" +
                super.toString() +
                "}";
    }
}
