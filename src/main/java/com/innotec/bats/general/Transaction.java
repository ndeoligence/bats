package com.innotec.bats.general;

import java.sql.Date;

public class Transaction extends Action
{
		private String primAccountNo;
		private double amount;
		private Date datestamp;
		
		public Transaction (String primAccountNo, double amount)
		{
			this.primAccountNo = primAccountNo;
			this.amount = amount;
			datestamp = null;
		}

		public Date getDatestamp ()
		{
			return datestamp;
		}

		public void setDatestamp (Date datestamp)
		{
			this.datestamp = datestamp;
		}

		public String getPrimAccountNo ()
		{
			return primAccountNo;
		}

		public void setPrimAccountNo (String primAccountNo)
		{
			this.primAccountNo = primAccountNo;
		}

		public double getAmount ()
		{
			return amount;
		}

		public void setAmount (double amount)
		{
			this.amount = amount;
		}
		
		public String toString ()
		{
			return "Transaction Details= \t" +  primAccountNo + "\tR" + amount;
		}
		
}
