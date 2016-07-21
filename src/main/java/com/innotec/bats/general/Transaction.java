package com.innotec.bats.general;

public class Transaction extends Action
{
		private String primAccountNo;
		private double amount;
		
		public Transaction (String primAccountNo, double amount)
		{
			this.primAccountNo = primAccountNo;
			this.amount = amount;
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
		
		
}
