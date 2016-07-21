package innotec.bats.general_code;

public class Account 
{
	private String accNo;
	private double balance;
	private boolean isActive;
	private double maxWithdrawalPerDay;
	private double minWithdrawalPerDay;
	private int startDate;
	
	public Account(String accNo, double balance,boolean isActive, double maxWithdrawalPerDay,double minWithdrawalPerDay)
	{
		this.accNo = accNo;
		this.balance = balance;
		this.isActive = isActive;
		this.maxWithdrawalPerDay = maxWithdrawalPerDay;
		this.minWithdrawalPerDay = minWithdrawalPerDay;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean getisActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getMaxWithdrawalPerDay() {
		return maxWithdrawalPerDay;
	}

	public void setMaxWithdrawalPerDay(double maxWithdrawalPerDay) {
		this.maxWithdrawalPerDay = maxWithdrawalPerDay;
	}

	public double getMinWithdrawalPerDay() {
		return minWithdrawalPerDay;
	}

	public void setMinWithdrawalPerDay(double minWithdrawalPerDay) {
		this.minWithdrawalPerDay = minWithdrawalPerDay;
	}
	
	
}
