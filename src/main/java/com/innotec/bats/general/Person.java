package com.innotec.bats.general;
import java.io.Serializable;

/**
 * Created by phoenix on 7/18/16.
 */
public class Person implements Serializable {
    private String name;
    private String surname;
    private String idNo;
    public Person(String name, String surname, String idNo) {
        if (name==null || surname==null || idNo==null)
            throw new IllegalArgumentException("null Person field(s)");
        this.name = new String(name);
        this.surname = new String(surname);
        this.idNo = new String(idNo);
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
}
