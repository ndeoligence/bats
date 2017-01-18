package com.innotec.bats.general;

import java.util.Date;
import java.util.Objects;

/**
 * Created by phoenix on 7/22/16.
 */
public class BalanceSheetRequest extends Action {
    private int atmID;
    private Date startDate;
    private Date endDate;

    public BalanceSheetRequest() {
    }

    public BalanceSheetRequest(int atmID, Date startDate, Date endDate) {
        this.atmID = atmID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAtmID() {
        return atmID;
    }

    public void setAtmID(int atmID) {
        this.atmID = atmID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BalanceSheetRequest)) return false;
        BalanceSheetRequest that = (BalanceSheetRequest) o;
        return atmID == that.atmID &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atmID, startDate, endDate);
    }

    @Override
    public String toString() {
        return "BalanceSheetRequest{" +
                "atmID=" + atmID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
