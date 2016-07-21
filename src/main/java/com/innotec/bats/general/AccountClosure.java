package innotec.bats.general_code;

public class AccountClosure extends TellerAction
{
	private Account acc;
	private String accNo;
	private boolean isActive;
	
	public AccountClosure(String tellerName, String accNo, boolean isActive)
	{
		super(tellerName);
		this.accNo = accNo;
		this.isActive = isActive;
	}
	
	public boolean closeAccount()
	{
		if(acc.getAccNo().equals(accNo))
		{
		acc.setActive(isActive);
		if(acc.getisActive() == true)
		{
			return false;
		}
		return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
}
