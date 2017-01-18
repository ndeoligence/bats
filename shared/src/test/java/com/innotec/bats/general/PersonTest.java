package com.innotec.bats.general;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
	
	Person person;
	String name,surname,idNo;
	String name2,surname2,idNo2;
	
	@Before
	public void setUp() throws Exception {
		name="John";
		surname="Wilde";
		idNo="984832649239";
		name2="Mark";
		surname2="Shuttle";
		idNo2="92837937432";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCtor() {
		person = new Person(name,surname,idNo);
		assertEquals(name,person.getName());
		assertEquals(surname,person.getSurname());
		assertEquals(idNo,person.getIdNo());
	}
	
	@Test
	public void testSetters() {
		person = new Person();
		person.setName(name2);
		person.setSurname(surname2);
		person.setIdNo(idNo2);
		assertEquals(name2,person.getName());
		assertEquals(surname2,person.getSurname());
		assertEquals(idNo2,person.getIdNo());
	}
//	@Ignore
	@Test
	public void testValidateIdNo() { // Not fully implemented!
        person = new Person();
		person.setIdNo("9012315856071");
		assertTrue(person.validateIdNo());
		person.setIdNo("384293");
		assertFalse(person.validateIdNo());
	}
}
