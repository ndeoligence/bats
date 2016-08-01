package com.innotec.bats.general;

public class Employee extends Person
{
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
