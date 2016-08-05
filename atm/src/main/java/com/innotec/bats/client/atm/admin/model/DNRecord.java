package com.innotec.bats.client.atm.admin.model;

import java.util.ArrayList;
import java.util.List;

public class DNRecord
{
	private String atmID;
	private ArrayList<DNREntry> dnrEntries;
	private DNRecord dnRecord;
	private DNR_DAO dnr_DAO;

	public DNRecord(String atmID)
	{
		this.atmID = atmID;
		dnrEntries = new ArrayList<DNREntry>();
	}

	public void readFromDAOfile()
	{
		dnRecord =  dnr_DAO.readFromDNR();
		dnrEntries.add(dnRecord);
	}
	
	public void add(DNREntry dnrEntry)
	{
		this.readFromDAOfile();
		dnrEntries.add(dnrEntry);
	}
	
	public String getAtmID()
	{
		return atmID;
	}

	public List<DNREntry> getDnrEntries()
	{
		return dnrEntries;
	}

	public String toString()
	{
		return "DN Record for atm: " + atmID;
	}
}
