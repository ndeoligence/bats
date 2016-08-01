package com.innotec.bats.general;
import java.io.Serializable;

public class Person implements Serializable {
	private String name;
	private String surname;
	private String idNo;
	public Person(String name, String surname, String idNo) {
		this.name = name;
		this.surname = surname;
		this.idNo = idNo;
	}
	public String toString() {return name + " " + surname;}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public boolean validateIdNo() {
		return false;
	}
}
