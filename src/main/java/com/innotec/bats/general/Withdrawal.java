package innotec.bats.general_code;

public class Withdrawal 
{
	private final static double MIN_BALANCE = 10.00;
	private int waitingPeriod;
	
	public Withdrawal(int waitingPeriod)
	{
		this.waitingPeriod = waitingPeriod;
	}

	public int getWaitingPeriod() {
		return waitingPeriod;
	}
}
