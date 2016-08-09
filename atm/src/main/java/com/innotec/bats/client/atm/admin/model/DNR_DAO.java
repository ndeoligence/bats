package com.innotec.bats.client.atm.admin.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class DNR_DAO
{
	private File file;
	private String pathway;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private DNRecord dnRecord;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private Date date;
	private String dateString;

	public DNR_DAO()
	{
		date = new Date();
		dateString = date.toString();
		
		pathway = "/ATM_DNR/dnr" + dateString + ".bin";
		file = new File(pathway);

		if (file.exists() == false)
		{
			try
			{
				file.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void writeToDNR(DNRecord dnRecord)
	{
		this.openConnections();
		try
		{
			oos.writeObject(dnRecord);
			oos.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.closeConnections();
	}
	
	public DNRecord readFromDNR()
	{
		this.openConnections();
		try
		{
			dnRecord = (DNRecord) ois.readObject();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.closeConnections();
		return dnRecord;
	}
	
	public void openConnections()
	{
		try
		{
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void closeConnections()
	{
		try
		{
			oos.close();
			fos.close();
			ois.close();
			fis.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
