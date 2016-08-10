package com.innotec.bats.general;

import java.util.Date;



public class Transaction extends Action
{
		private String primAccountNo, ATM_ID;
		private int type;
		private double amount;
		private Date datestamp;
		private final static int TYPE_WITHDRAWAL = 1;
		private final static int TYPE_DEPOSIT = 2;
		private final static int TYPE_TRANSFER = 3;
		
		public Transaction (String primAccountNo, double amount)
		{
			this.primAccountNo = primAccountNo;
			this.amount = amount;
			datestamp = new Date();
		}

		public String getATM_ID ()
		{
			return ATM_ID;
		}

		public void setATM_ID (String atm_id)
		{
			 ATM_ID = atm_id;
		}
		
		public int getType ()
		{
			return type;
		}

		public void setType (int type)
		{
			this.type = type;
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
