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

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	public void seed()
	{
		dm = new DataManager();
		String[] fn = {"John", "Sally", "Jill", "Kreig", "Jake", "Neil", "Caroline"};
		String[] ln = {"Smith", "Doe", "Baker", "Forth", "Thomson", "Bekensdale", "Dale"};
		String[] StreetName = {"Madison", "1st", "Main", "18th", "Brook", "Mayfield"};
		String[] streetType = {"Ln", "Dr", "Ave", "St", "Way", "Circle"};
		String[] town = {"Benbrook", "Tuscaloosa", "Brekenridge", "Auburn", "Dallas"};
		String[] st = {"Tx", "Al", "Az", "Ms", "Wy", "Nm", "Ak"};
		//Seed Some Members
		for(int i = 0; i<20; i++)
		{
			Random r = new Random();
			String name = fn[r.nextInt(fn.length-1)]+" "+ln[r.nextInt(ln.length-1)];
			String addr = r.nextInt(5000) +" "+StreetName[r.nextInt(StreetName.length-1)]+" "+
					streetType[r.nextInt(streetType.length-1)];
			String city = town[r.nextInt(town.length-1)];
			String state = st[r.nextInt(st.length-1)];
			String status = r.nextInt(10000)%7 == 0?"Suspended":"Active";
			dm.addMember(new Member(name, dm.getNextMemID(),
					addr, city,state, String.format("%05d", r.nextInt(99999)), status));
		}
		//Seed Some Providers
		for(int i = 0; i<20; i++)
		{
			Random r = new Random();
			String name = town[r.nextInt(town.length-1)]+" "+fn[r.nextInt(fn.length-1)];
			String addr = r.nextInt(5000) +" "+StreetName[r.nextInt(StreetName.length-1)]+" "+
					streetType[r.nextInt(streetType.length-1)];
			String city = ln[r.nextInt(ln.length-1)];
			String state = st[r.nextInt(st.length-1)];
			dm.addProvider(new User(name, dm.getNextProvID(),
					addr, city,state, String.format("%05d", r.nextInt(99999))));

		}
		//Add services to member 0 from provider 0;
		for(int i = 0; i<20; i++)
		{
			dm.addService("000000", "1/1/2015", "1/"+i+"/2015", "000000000", "000000000", "Service " + i);
		}
	}

}
