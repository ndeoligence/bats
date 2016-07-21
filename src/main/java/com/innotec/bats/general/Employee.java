package innotec.bats.general_code;

public class Employee extends Person
{
	private Person employee;
	private int employeeNo;
	
	public Employee(String name, String surname,String idNumber, int employeeNo)
	{
		super(name,surname,idNumber);
		this.employeeNo = employeeNo;
	}
	
	public int getEmployeeNo()
	{
		return this.employeeNo;
	}
}
