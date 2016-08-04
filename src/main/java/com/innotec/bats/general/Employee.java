package com.innotec.bats.general;

public class Employee extends Person
{
	private String employeeNo;
	
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
