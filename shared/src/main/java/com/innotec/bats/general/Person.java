package com.innotec.bats.general;
import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
	private String name;
	private String surname;
	private String idNo;

	public Person() {
	}

	public Person(String name, String surname, String idNo) {
		this.name = name;
		this.surname = surname;
		this.idNo = idNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(idNo, person.idNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, idNo);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", idNo='" + idNo + '\'' +
                '}';
    }

    public boolean validateIdNo() {
        return idNo != null && (idNo.matches("[0-9]{13}"));
    }
}
