package com.innotec.bats.general;

import java.sql.Date;

public class Transaction extends Action
{
		private String primAccountNo;
		private double amount;
		private String type;
		private int transactionID;
		private Date timeStamp;
		
		public Transaction (String primAccountNo, double amount,String type)
		{
			this.primAccountNo = primAccountNo;
			this.amount = amount;
			this.type = type;
		}

		public Transaction(int transactionID, Date timeStamp, double amount, String primAccountNo,String type) 
		{
			this.primAccountNo = primAccountNo;
			this.amount = amount;
			this.type = type;
			this.timeStamp = timeStamp;
			this.transactionID = transactionID;
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
		
		public String getType() {
			return type;
		}

		public int getTransactionID() {
			return transactionID;
		}

		public Date getTimeStamp() {
			return timeStamp;
		}

		@Override
		public String toString() {
			return "Transaction [primAccountNo=" + primAccountNo + ", amount="
					+ amount + ", type=" + type + ", transactionID="
					+ transactionID + ", timeStamp=" + timeStamp + "]";
		}
		
}
