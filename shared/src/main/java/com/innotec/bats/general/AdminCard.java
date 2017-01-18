package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/21/16.
 */
public class AdminCard extends Card {
    public static final int CARD_NO_LEN = 8;
    private String employeeNo;

    public AdminCard() {
    }

    public AdminCard(String cardNo, String pinNo, boolean active,
                     String employeeNo) {
        super(cardNo, pinNo, active);
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
        if (!(o instanceof AdminCard)) return false;
        if (!super.equals(o)) return false;
        AdminCard adminCard = (AdminCard) o;
        return Objects.equals(employeeNo, adminCard.employeeNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeNo);
    }

    @Override
    public String toString() {
        return "AdminCard{" +
                "employeeNo='" + employeeNo + '\'' +
                '}';
    }
}
