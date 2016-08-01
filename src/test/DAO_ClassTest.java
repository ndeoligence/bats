package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.innotec.bats.general.*;
import com.innotec.bats.server.dao.DAO_Class;

public class DAO_ClassTest {
	private DAO_Class dao = new DAO_Class();
	
	@Test
	public void TestingToSeeIfAllGetterMethodsReturnNull() {
		DAO_Class tester = new DAO_Class();//class to be tested
		
		//assert Statements
		assertEquals("",null,tester.getAccountHolderByidNo(""));
		assertEquals("",null,tester.getAccountHolderCardByCardNo(""));
		assertEquals("",null,tester.getAccounts(""));
		assertEquals("",null,tester.getAdminCardByCardNo(""));
		assertEquals("",null,tester.getCreditCardAccount(""));
		assertEquals("",null,tester.getCurrentAccount(""));
		assertEquals("",null,tester.getEmployee(""));
		assertEquals("",null,tester.getSavingsAccount(""));
		assertEquals("",null,tester.getTransactionForAccount(""));
	}
	
	@Test
	public void testingToSeeIfGettingCurrentAccountWorks()
	{
		CurrentAccount accountTester = new CurrentAccount("05697410673", 10000, true, "9701015029089");
		CurrentAccount toBeTested;
		toBeTested = dao.getCurrentAccount("9701015029089");
		assertEquals("the accounts should match each other",accountTester,toBeTested);
	}
	@Test 
	public void testingToSeeIfGettingAccountHolderWorks()
	{
		AccountHolder tester = new AccountHolder("Michael", "Smith", "9711015029088", "2 Jutinga close", "0834014835");
		AccountHolder tested;
		tested = dao.getAccountHolderByidNo("9711015029088");
		assertEquals("the account holders should equal each other",tester,tested);
	}
	
	@Test 
	public void testingToSeeIfGettingAccountHolderCardWorks()
	{
		AccountHolderCard cardTester = new AccountHolderCard("503956778694658","1234", true, "9701015029089");
		AccountHolderCard cardToTest;
		cardToTest = dao.getAccountHolderCardByCardNo("503956778694658");
		assertEquals("the account holder cards should equal each other",cardTester,cardToTest);
	}
	
	@Test
	public void testingToSeeIfGettingAnEmployeeWorks()
	{
		
	}
	
}
