package com.team9.finalproject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.team9.finalproject.connectors.ProviderConnector;
import com.team9.finalproject.dataMembers.Member;
import com.team9.finalproject.dataMembers.User;

/**
 * @author Austin Hyde
 */

public class ProviderConnectorTest {
	DataManager dm;
	ProviderConnector pc;
	String result;
	

	@Before
	public void setUp() throws Exception {
		seedDM();
		pc = new ProviderConnector(dm);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void billTestInvalidMember() {
		result = pc.bill("000000003", "000000000", "000000", "12/12/2012", "comments");
		assertEquals("Member Error: Member Does Not Exist", result);
	}
	
	@Test
	public void billTestSuspendedMember(){
		result = pc.bill("000000001",  "000000000", "000000", "12/12/2012", "comments");
		assertEquals("Member Error: suspended", result);
	}
	
	@Test
	public void billTestNonexistentProvider(){
		result = pc.bill("000000000", "000000002", "000000", "12/12/2012", "comments");
		assertEquals("Provider number invalid", result);
	}
	
	@Test
	public void billTestInvalidServiceCode(){
		result = pc.bill("000000000", "000000000", "555555", "12/12/2012",  "comments");
		assertEquals("Service Code Error: Check Directory",result);
	}
	
	@Test
	public void billTestInvalidDate(){
		result = pc.bill("000000000",  "000000000",  "000000", "12/12-20222", "comments");
		assertEquals("Error: Invalid Date",result);
	}
	
	@Test
	public void billTestCorrect(){
		result = pc.bill("000000000",  "000000000",  "000000",  "12/12/2012", "comments");
		assertEquals("Billing Successful",result);	
	}
	
	public void seedDM(){
		dm = new DataManager();
		dm.addMember(new Member("Sally Sue", "000000000", "123 Street Street", "Jamestown", "VA", "33333", "active"));
		dm.addMember(new Member("Billy Bob", "000000001", "333 Drury Lane", "Birmingham", "AL", "35403", "suspended"));
		dm.addProvider(new User("Johnny Bravo", "000000000", "5555 Rock Ridge Rd","Hoover","AL","35201"));
	}

}
