package com.innotec.bats.general;

public class Person
{
	private String name, surname, idNumber;

	public Person(String name, String surname, String idNumber)
	{
		this.name = name;
		this.surname = surname;
		this.idNumber = idNumber;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getIdNumber()
	{
		return idNumber;
	}

	public void setIdNumber(String idNumber)
	{
		this.idNumber = idNumber;
	}

	public String toString()
	{
		return "Person [name=" + name + ", surname=" + surname + ", idNumber="
				+ idNumber + "]";
	}
	
	
}
