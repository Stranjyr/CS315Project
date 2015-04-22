package com.team9.finalproject;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.team9.finalproject.connectors.InteractiveConnector;
import com.team9.finalproject.dataMembers.Member;
import com.team9.finalproject.dataMembers.User;

public class InteractiveConnectorTest {
	DataManager dm;
	InteractiveConnector interact;

	@Before
	public void setUp() throws Exception {
		seed();
		interact = new InteractiveConnector(dm);
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 * Begin tests for InteractiveConnector.AddMember
	 */
	@Test
	public void testAddMember_ShouldFailName() {
		assertEquals(
				"Name must be under 25 chars", 
				interact.addMember("Reallylongfirstname Reallylonglastname", "123 First St", "Anytown", "AL", "12345"));
	}
	@Test
	public void testAddMember_ShouldFailAddress() {
		assertEquals(
				"Address must be under 25 chars", 
				interact.addMember("John Doe", "123456789 Longassstreetname Street", "Anytown", "AL", "12345"));
	}
	@Test
	public void testAddMember_ShouldFailCity() {
		assertEquals(
				"City must be under 14 chars",
				interact.addMember("John Doe", "123 First St", "Longasscityname", "AL", "12345"));
	}
	@Test
	public void testAddMember_ShouldFailState() {
		assertEquals(
				"State must have 2 chars", 
				interact.addMember("John Doe", "123 First St", "Anytown", "ABC", "12345"));
	}
	@Test
	public void testAddMember_ShouldFailZip() {
		assertEquals(
				"Zip must have 5 chars", 
				interact.addMember("John Doe", "123 First St", "Anytown", "AL", "123456"));
	}
	@Test
	public void testAddMember_ShouldSucceed() {
		assertEquals(
				"Member 000000002 added successfuly", 
				interact.addMember("John Doe", "123 First St", "Anytown", "AL", "12345"));
	}
	
	//Provider Add Tests
	@Test
	public void testAddProvider_ShouldFailName() {
		assertEquals(
				"Name must be under 25 chars", 
				interact.addProvider("Reallylongfirstname Reallylonglastname", "123 First St", "Anytown", "AL", "12345"));
	}
	@Test
	public void testAddProvider_ShouldFailAddress() {
		assertEquals(
				"Address must be under 25 chars", 
				interact.addProvider("John Doe", "123456789 Longstreetname Street", "Anytown", "AL", "12345"));
	}
	@Test
	public void testAddProvider_ShouldFailCity() {
		assertEquals(
				"City must be under 14 chars",
				interact.addProvider("John Doe", "123 First St", "Longasscityname", "AL", "12345"));
	}
	@Test
	public void testAddProvider_ShouldFailState() {
		assertEquals(
				"State must have 2 chars", 
				interact.addProvider("John Doe", "123 First St", "Anytown", "ABC", "12345"));
	}
	@Test
	public void testAddProvider_ShouldFailZip() {
		assertEquals(
				"Zip must have 5 chars", 
				interact.addProvider("John Doe", "123 First St", "Anytown", "AL", "123456"));
	}
	@Test
	public void testAddProvider_ShouldSucceed() {
		assertEquals(
				"Provider 000000002 added successfuly", 
				interact.addProvider("John Doe", "123 First St", "Anytown", "AL", "12345"));
	}
	}
	}
	}
	@Test
	public void testgetMember_ShouldFail() {
		assertEquals(
				"Member does not exist.",
				interact.getMember("00000015"));
	}
	@Test
	public void testgetMember_ShouldSucceed() {
		assertEquals(
				"Member Name: John Doe\nMember Address:123 First St\nMember City:Anytown\nMember State:AL\nMember Zip Code:12345",
				interact.getMember("000000000"));
	}
	@Test
	public void testgetProvider_ShouldFail() {
		assertEquals(
				"Provider does not exist.",
				interact.getProvider("00000015"));
	}
	@Test
	public void testgetProvider_ShouldSucceed() {
		assertEquals(
				"Provider Name: John Doe\nProvider Address:123 First St\nProvider City:Anytown\nProvider State:AL\nProvider Zip Code:12345",
				interact.getProvider("000000000"));
	}
	
	public void seed()
	{
		dm = new DataManager();
		dm.addMember(new Member( "John Doe", dm.getNextMemID(), "123 First St", "Anytown", "AL", "12345", "Active"));
		dm.addMember(new Member( "John Doe", dm.getNextMemID(), "123 First St", "Anytown", "AL", "12345", "Active"));
		dm.addProvider(new User( "John Doe", dm.getNextProvID(), "123 First St", "Anytown", "AL", "12345"));
		dm.addProvider(new User( "John Doe", dm.getNextProvID(), "123 First St", "Anytown", "AL", "12345"));
		for(int i = 0; i<20; i++)
		{
			dm.addService("000000", "1/1/2015", "1/"+i+"/2015", "000000000", "000000000", "Service " + i);
		}
	}

}
