package com.team9.finalproject;

import java.util.Random;
import java.util.Scanner;

import com.team9.finalproject.connectors.InteractiveConnector;
import com.team9.finalproject.connectors.ProviderConnector;
import com.team9.finalproject.dataMembers.Member;
import com.team9.finalproject.dataMembers.User;


/**
 * Startup file for the program. On start, allows user to choose 
 * interactive mode for users at the data center, or provider mode.
 * 
 * For debug purposes, also allows users to generate random members and
 * providers to populate the data manager.
 * 
 * @author William
 */
public class ChocoholicsMain {
	
	/**
	 * Main function of the program
	 * @param args: command line arguments
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		//remove before release
		System.out.println("Type _S_eed to create a new test data template, or any other key to continue");
		switch(scan.next().toUpperCase().charAt(0))
		{
			case 'S':
				seedDataManager();
				break;
		}
		//end dev bit
		System.out.println("Enter I for interactive mode, or P for provider mode");
		switch(scan.next().toUpperCase().charAt(0))
		{
			case 'I':
				new InteractiveConnector();
				break;
			case 'P':
				new ProviderConnector();
				break;
		}
		scan.close();

	}
	
	/**
	 * Generate random members and providers to populate the data manager.
	 */
	private static void seedDataManager()
	{
		DataManager dm = new DataManager();
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
		
		//Save the data
		dm.save();
	}

}
