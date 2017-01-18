package com.innotec.bats.general;

public class ServerAdmin extends Employee {
    public ServerAdmin() {
    }

    public ServerAdmin(String name, String surname, String idNo, String employeeNo) {
        super(name, surname, idNo, employeeNo);
    }

    @Override
    public String toString() {
        return "ServerAdmin{" +
                super.toString() +
                "}";
    }
}
