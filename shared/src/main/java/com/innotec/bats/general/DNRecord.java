package com.innotec.bats.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by phoenix on 7/20/16.
 */
public class DNRecord {
    private String atmID;
    private List<DNREntry> dnrEntries;

    public DNRecord() {
    }

    public DNRecord(String atmID) {
        this.atmID = atmID;
        dnrEntries = new ArrayList<>();
    }

    public void add(DNREntry dnrEntry) {
        dnrEntries.add(dnrEntry);
    }

    public String getAtmID() {
        return atmID;
    }

    public void setAtmID(String atmID) {
        this.atmID = atmID;
    }

    public List<DNREntry> getDnrEntries() {
        return dnrEntries;
    }

    public void setDnrEntries(List<DNREntry> dnrEntries) {
        this.dnrEntries = dnrEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DNRecord)) return false;
        DNRecord dnRecord = (DNRecord) o;
        return Objects.equals(atmID, dnRecord.atmID) &&
                Objects.equals(dnrEntries, dnRecord.dnrEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atmID, dnrEntries);
    }

    @Override
    public String toString() {
        return "DNRecord{" +
                "atmID='" + atmID + '\'' +
                ", dnrEntries=" + ListUtils.toString(dnrEntries) +
                '}';
    }
}
