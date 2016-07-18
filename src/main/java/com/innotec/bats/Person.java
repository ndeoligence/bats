package com.innotec.bats;

import java.io.Serializable;

/**
 * Created by phoenix on 7/18/16.
 */
public class Person implements Serializable {
    private String name;
    private String surname;
    private String idNo;
    private Gender gender;
    public Person(String name, String surname, String idNo, Gender gender) {
        if (name==null || surname==null || idNo==null || gender==null)
            throw new IllegalArgumentException("null Person field(s)");
        this.name = new String(name);
        this.surname = new String(surname);
        this.idNo = new String(idNo);
        this.gender = gender;
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
    public Gender getGender() {return gender;}
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public enum Gender {MALE, FEMALE};
}
