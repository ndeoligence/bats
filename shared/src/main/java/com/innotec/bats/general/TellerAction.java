package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/20/16.
 */
public class TellerAction extends Action {
    private String employeeNo;

    public TellerAction() {
    }

    public TellerAction(String employeeNo) {
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
        if (!(o instanceof TellerAction)) return false;
        TellerAction that = (TellerAction) o;
        return Objects.equals(employeeNo, that.employeeNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo);
    }

    @Override
    public String toString() {
        return "TellerAction{" +
                "employeeNo='" + employeeNo + '\'' +
                '}';
    }
}
