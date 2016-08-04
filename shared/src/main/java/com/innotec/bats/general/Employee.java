package com.innotec.bats.general;

public class Employee extends Person
{
	private String employeeNo;
	public static final int EMPLOYEE_NO_LEN = 5;
	public Employee(String name, String surname,String idNumber, String employeeNo)
	{
		super(name,surname,idNumber);
		this.employeeNo = employeeNo;
	}
	
	public String getEmployeeNo()
	{
		return employeeNo;
	}
}
