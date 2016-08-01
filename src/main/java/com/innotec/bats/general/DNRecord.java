package com.innotec.bats.general;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoenix on 7/20/16.
 */
public class DNRecord {
    private String atmID;
    private List<DNREntry> dnrEntries;
    public DNRecord(String atmID) {
        this.atmID = atmID;
        dnrEntries = new ArrayList<>();
    }
    public void add(DNREntry dnrEntry) {
        dnrEntries.add(dnrEntry);
    }
    public String getAtmID() {return atmID;}
    public List<DNREntry> getDnrEntries() {return dnrEntries;}
    public String toString() {return "DN Record for atm: "+atmID;}
}
