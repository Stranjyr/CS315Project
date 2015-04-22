package com.team9.finalproject.connectors;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.team9.finalproject.DataManager;
import com.team9.finalproject.dataMembers.Member;
import com.team9.finalproject.dataMembers.Service;
import com.team9.finalproject.dataMembers.User;
import com.team9.finalproject.reports.MemberReport;
import com.team9.finalproject.reports.ProviderReport;
import com.team9.finalproject.reports.ReportGenerator;


/**
 * The interface shown to Chocoholics Anonymous Data Center operators during the day
 * Allows operators to alter data stored for CA provider and members
 * @author amhyd_000
 */
public class InteractiveConnector implements ConnectorInterface{
	private DataManager dm;
	private Scanner scan;
	/**
	 * This constructor loads in the previous data and starts the command loop.
	 */
	public InteractiveConnector()
	{
		scan = new Scanner(System.in);
		display(startScreen());
		dm = loadDataManager();
		commandLoop();
		scan.close();
	}
	/**
	 * For Unit Testing only
	 * @param d datamanager to be loaded
	 */
	public InteractiveConnector(DataManager d)
	{
		
		dm = d;
	}
	
	/**
	 * Creates message shown at the start of Interactive Mode
	 * @return String : Returns a string containing the start screen art and instructions
	 */
	private String startScreen() {
		String s = "";
		s+=""+                                                                                             
				"              ,,                                    ,,                 ,,    ,,                  "+"\n"+
				"  .g8W0Wbgd `7MM                                  `7MM               `7MM    db                  "+"\n"+ 
				".dP`     `M   MM                                    MM                 MM                        "+"\n"+
				"M`           MMpMMMb.  ,pW-Wq.   ,p6-bo   ,pW-Wq.  MMpMMMb.  ,pW-Wq.  MM  `7MM  ,p6-bo  ,pP-Ybd  "+"\n"+
				"MM            MM    MM 6W     Wb 6M`  OO  6W     Wb MM    MM 6W     Wb MM    MM 6M`  OO  8I   `` "+"\n"+
				"MM.           MM    MM 8M     M8 8M       8M     M8 MM    MM 8M     M8 MM    MM 8M       `YMMMa. "+"\n"+
				" Mb.     ,    MM    MM YA.   ,A9 YM.      YA.   ,A9 MM    MM YA.   ,A9 MM    MM YM.      L.   I8 "+"\n"+
				"   =bmmmd`  .JMML  JMML. Ybmd9    YMbmd     Ybmd9 .JMML  JMML. Ybmd9 .JMML..JMML.YMbmd   M9mmmP` "+"\n";
                                                                                                                                                                                     
		s+="*************************************\n"
		  +"**** WELCOME OPERATOR ***************\n"
		  +"*************************************\n"
		  +"**** CHOCOHOLICS DATAMANAGER 1.0 ****\n"
		  +"**** A NEW PARADIGM IN CHOCOLATE ****\n"
		  +"*************************************\n"
		  +"**** COMMANDS ***********************\n"
		  +"**** H == HELP - Shows Commands******\n"
		  +"**** A == ADD - Add new Data*********\n"
		  +"**** E == EDIT - Edit data***********\n"
		  +"**** D == DELETE - Delete data*******\n"
		  +"**** R == REPORT - Run Reports*******\n"
		  +"**** P == PROVIDERS - List Providers*\n"
		  +"**** M == MEMBERS - List Members*****\n"
		  +"**** G == Get Data - Show Data*******\n"
		  +"**** Q == QUIT - Quits***************\n"
		  +"*************************************\n"
		  +"*************************************\n";
		
		  
		return s;
	}
	
	/**
	 * If stored data can be loaded form ChocoData.ser, loads the data into a new DataManager object
	 * Otherwise creates a new empty DataManager
	 * @return newDataManager: Either a loaded instance of a datamanager, or a new datamanager
	 * if something went wrong.
	 */
	@Override
	public DataManager loadDataManager() {
		try
	      {
	         FileInputStream fileIn = new FileInputStream("/ChocoData.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         DataManager temp = (DataManager) in.readObject();
	         in.close();
	         fileIn.close();
	         return temp;
	      }catch(IOException i)
	      {
	         display("load failed");
	         return new DataManager();
	      }catch(ClassNotFoundException c)
	      {
	         
	    	  display("load failed");
	         return new DataManager();
	      }
		
	}
	
	/**
	 * THis is the main command loop. It is the entry point for all of our
	 * Business logic.
	 * Asks user to enter one of the commands shown at start
	 * Loops until user asks to quit
	 * @return exit: The string "Exited"
	 * 
	 */
	@Override
	public String commandLoop() {
		boolean haveRunReports = false;
		while(true)
		{
			
			//Check if we need to run all the reports
			
			if(!(haveRunReports) && 
					Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 6)
			{
				runBatchReports();
			}
			if( haveRunReports && Calendar.getInstance().get(Calendar.DAY_OF_WEEK) != 6 )
			{
				haveRunReports = false;
			}
			
			//Start Main Command Loop
			display("Next Command?");
			String command = scan.nextLine();
			String id, name, addr, city, state, z;
			switch(command.toUpperCase().charAt(0))
			//Checks commands entered by user and directs user to relevant steps
			{
				case 'H':
					display(help());
					break;
				case 'A':
					display("Add _M_ember, _P_rovider, or _C_ancel?");
					switch(scan.nextLine().toUpperCase().charAt(0))
					{
						case 'M':
							display("Enter Member name");
							name = scan.nextLine();
							display("Enter member address");
							addr = scan.nextLine();
							display("Enter city");
							city = scan.nextLine();
							display("Enter state");
							state = scan.nextLine();
							display("Enter zip");
							z = scan.nextLine();
							display(addMember(name, addr, city, state, z));
							break;
						case 'P':
							display("Enter Provider name");
							name = scan.nextLine();
							display("Enter member address");
							addr = scan.nextLine();
							display("Enter city");
							city = scan.nextLine();
							display("Enter state");
							state = scan.nextLine();
							display("Enter zip");
							z = scan.nextLine();
							display(addProvider(name, addr, city, state, z));
							break;
						default: break;
					}
					break;
				case 'E':
					display("Edit _M_ember, _P_rovider, or _C_ancel?");
					switch(scan.nextLine().toUpperCase().charAt(0))
					{
						case 'M':
							display("Enter Member ID");
							id = scan.nextLine();
							/*display("Do you want to change the\n"
									+ "_n_ame, _a_ddress, _c_ity, _s_tate, _z_ip, or s_t_atus?");
							editWhat = scan.nextLine().toUpperCase().charAt(0);
							display("Enter new Value");
							val = scan.nextLine();
							display(editMember(id, editWhat, val));*/
							display(editMember(id));
							break;
						case 'P':
							display("Enter Provider ID");
							id = scan.nextLine();
							/*display("Do you want to change the\n"
									+ "_n_ame, _a_ddress, _c_ity, _s_tate, or _z_ip");
							editWhat = scan.nextLine().toUpperCase().charAt(0);
							display("Enter new Value");
							val = scan.nextLine();*/
							display(editProvider(id));
							break;
						default: break;
					}
					break;
				case 'D':
					display("Delete a _M_ember, _P_rovider, or _C_ancel?");
					char confirmDelete;
					switch(scan.nextLine().toUpperCase().charAt(0))
					{
						case 'M':
							display("Enter Member ID");
							id = scan.nextLine();
							display("Are you sure you want to delete Member?");
							confirmDelete = scan.nextLine().toUpperCase().charAt(0);
							if(confirmDelete != 'Y')
							{
								break;
							}
							display(dm.removeMember(id));
							break;
						case 'P':
							display("Enter Provider ID");
							id = scan.nextLine();
							display("Are you sure you want to delete Provider?");
							confirmDelete = scan.nextLine().toUpperCase().charAt(0);
							if(confirmDelete != 'Y')
							{
								break;
							}
							display(dm.removeProvider(id));
							break;
						default: break;
					}
					break;
				case 'R':
					display("Reports Screen");
					display("_M_ember, _P_rovider, _E_FT, or _C_ancel?");
					switch(scan.nextLine().toUpperCase().charAt(0))
					{
						case 'M':
							display("Enter Member ID");
							id = scan.nextLine();
							display(memberReport(id));
							break;
						case 'P':
							display("Enter Provider ID");
							id = scan.nextLine();
							display(providerReport(id));
							break;
						case 'E':
							display("EFT not yet implemented");
							break;
						default: break;
					}
					break;
				case 'P':
					display(dm.listProviders());
					break;
				case 'M':
					display(dm.listMembers());
					break;
					
				case 'G':
					display("Get Data Screen");
					display("_M_ember, _P_rovider,  _C_ancel?");
					switch(scan.nextLine().toUpperCase().charAt(0))
					{
						case 'M':
							display("Enter Member ID");
							id = scan.nextLine();
							display(getMember(id));
							break;
						case 'P':
							display("Enter Provider ID");
							id = scan.nextLine();
							display(getProvider(id));
							break;
						default: break;
					}
					break;
				case 'Q':
					//This does not break, it returns to exit the loop
					display(quit());
					return "Exited";
				default:
					display("Invalid Command");
					break;	
			}
			
		}
	}
	/**
	 * Creates and saves to disk reports for each member
	 * Should be called every Friday
	 */
	private void runBatchReports() {
		ReportGenerator r;
		for(String i: dm.listMemberIds())
		{
			ArrayList<Service> sers = dm.findServiceByMember(i);
			r = new MemberReport();
			String rep = r.RunReport(dm.findAndCloneMember(i), sers);
			r.saveToFile(i+new Date().toString().replace(" ", "").replace(":", "-"), rep);
		}
		for(String i: dm.listProviderIds())
		{
			ArrayList<Service> sers = dm.findServiceByProvider(i);
			r = new ProviderReport();
			String rep = r.RunReport(dm.findAndCloneProvider(i), sers);
			r.saveToFile(i+new Date().toString().replace(" ", "").replace(":", "-"), rep);
		}
		
	}
	/**
	 * Runs a report for an individual provider
	 * @param id : Provider's id number
	 * @return String : returns a confirmation message
	 */
	private String providerReport(String id) {
		if(dm.findProvider(id) == -1)
		{
			return "Error: Invalid Provider Number";
		}
		ArrayList<Service> sers = dm.findServiceByProvider(id);
		ReportGenerator r = new ProviderReport();
		String rep = r.RunReport(dm.findAndCloneProvider(id), sers);
		return r.saveToFile(id+new Date().toString().replace(" ", "").replace(":", "-"), rep);
	}
	
	/**
	 * Runs a report for an individual member
	 * @param id : Provider's id number
	 * @return String : returns a confirmation string
	 */
	private String memberReport(String id) {
		if(dm.findMember(id) == -1)
		{
			return "Error: Invalid Member Number";
		}
		ArrayList<Service> sers = dm.findServiceByMember(id);
		ReportGenerator r = new MemberReport();
		String rep = r.RunReport(dm.findAndCloneMember(id), sers);
		return r.saveToFile(id+new Date().toString().replace(" ", "").replace(":", "-"), rep);
	}
	
	/**
	 * Method to add a member to the dataManager's memberList
	 * @param name : New member's name
	 * @param addr : New member's address
	 * @param city : New member's hometown
	 * @param state : New member's state
	 * @param zip : New member's Zip code
	 * @return String : Returns a confirmation or cancellation message to the main control loop
	 */
	public String addMember(String name, String addr, String city, String state, String zip)
	{
		//Validate name
		if(name.length()>25)
			return "Name must be under 25 chars";
		//Validate addr
		if(addr.length()>25)
			return "Address must be under 25 chars";
		//validate city
		if(city.length()>14)
			return "City must be under 14 chars";
		//validate state
		if(state.length()>2)
			return "State must have 2 chars";
		//validate zip
		if(zip.length() !=5)
			return "Zip must have 5 chars";
		String id = dm.getNextMemID();
		if(id == null)
		{
			return "Out of member ids. Please Contact Your Support Center";
		}
		dm.addMember(new Member(name, id, addr, city, state, zip, "Active"));
		return "Member "+id+" added successfuly";
	}
	
	/**
	 * Adds a provider to the dataManager's providerList
	 * @param name : New provider's name
	 * @param addr : New provider's address
	 * @param city : New provider's city
	 * @param state : New provider's state
	 * @param zip : New provider's Zip code
	 * @return String : returns a confirmation or cancellation method to the main control loop
	 */
	public String addProvider(String name, String addr, String city, String state, String zip)
	{
		//Validate name
		if(name.length()>25)
			return "Name must be under 25 chars";
		//Validate addr
		if(addr.length()>25)
			return "Address must be under 25 chars";
		//validate city
		if(city.length()>14)
			return "City must be under 14 chars";
		//validate state
		if(state.length()>2)
			return "State must have 2 chars";
		//validate zip
		if(zip.length() !=5)
			return "Zip must have 5 chars";
		String id = dm.getNextProvID();
		if(id == null)
		{
			return "Out of provider ids. Please Contact Your Support Center";
		}
		dm.addProvider(new User(name, id, addr, city, state, zip));
		return "Provider "+id+" added successfuly";
	}
	
	/**
	 * Edits members already stored in the memberList
	 * @param id : Existing member's id number
	 * @return String : returns a confirmation or cancellation message to the main control loop
	 */
	public String editMember(String id)
	//Method to edit members already stored in the memberList
	{
		char editWhat;
		char contVar;
		String val;
		char statChar;
		contVar = 'Y';
		if(dm.findMember(id) == -1){
			return "Invalid Member ID";
		}
		Member m = dm.findAndCloneMember(id);
		display("Current Member State: \n");
		display(m.reportFormat() + "Status: " + m.getStatus() + "\n");
		
		while(contVar == 'Y'){
			display("Do you want to change the\n"
					+ "_n_ame, _a_ddress, _c_ity, _s_tate, _z_ip or _m_embership status");

			editWhat = scan.nextLine().toUpperCase().charAt(0);
			switch(editWhat){
				case 'N':
					display("Enter the new name: ");
					val = scan.nextLine();
					while(val.length() > 25){
						display("Name must be less than 25 characters: ");
						val = scan.nextLine();
					}
					m.setName(val);
					display("Name set to: " + val);
					break;
				case 'A':
					display("Enter the new address: ");
					val = scan.nextLine();
					while(val.length() > 25){
						display("Address must be less than 25 characters: ");
						val = scan.nextLine();
					}
					m.setAddress(val);
					display("Address set to: " + val);
					break;
				case 'C':
					display("Enter the new city: ");
					val = scan.nextLine();
					while(val.length() > 14){
						display("City must be less than 14 characters: ");
						val = scan.nextLine();
					}
					m.setCity(val);
					display("City set to: " + val);
					break;
				case 'S':
					display("Enter the new state: ");
					val = scan.nextLine();
					while(val.length() != 2){
						display("State must be 2 character abbreviateion: ");
						val = scan.nextLine();
					}
					m.setState(val);
					display("State set to: " + val);
					break;
				case 'Z':
					display("Enter the new zip code: ");
					val = scan.nextLine();
					while(val.length() != 5){
						display("Zip code must be 5 digits: ");
						val = scan.nextLine();
					}
					m.setZip(val);
					display("Zip code set to: " + val);
					break;
				case 'M':
					display("Is this member currently _a_ctive, _s_uspended, or _i_nactive");
					statChar = scan.nextLine().toUpperCase().charAt(0);
					switch(statChar){
						case 'A':
							m.setStatus("active");
							break;
						case 'S':
							m.setStatus("suspended");
							break;
						case 'I':
							m.setStatus("inactive");
							break;
						default: display("Invalid status entered\n");
						display("Current Member Values: ");
						display(m.reportFormat() + "Status: " + m.getStatus() + "\n");
						display("Would you like to keep editing? _y_es or _n_o");
						contVar = scan.nextLine().toUpperCase().charAt(0);
						continue;
					}
					break;
				default: display("Invalid Edit Field: ");
					display("Would you like to keep editing? _y_es or _n_o");
					contVar = scan.nextLine().toUpperCase().charAt(0);
					continue;
				}
			display("Updated Member Values: \n");
			display(m.reportFormat() + "Status: " + m.getStatus() + "\n");
			display("Would you like to keep editing? _y_es or _n_o");
			contVar = scan.nextLine().toUpperCase().charAt(0);
		}
		
		display("Do you want to save changes? Y\n");
		if(scan.nextLine().toUpperCase().charAt(0) !='Y')
		{
			return "Cancled";
		}
		return dm.editMember(id, m);
	}
	
	/**
	 * Edits a provider already stored in the providerList
	 * @param id : Existing provider's id number
	 * @return String : returns a confirmation or cancellation message to the main control loop
	 */
	public String editProvider(String id)
	//method for editing providers already stored in the providerList
	{
		char editWhat;
		char contVar;
		String val;
		contVar = 'Y';
		if(dm.findMember(id) == -1){
			return "Invalid Provider ID";
		}
		User p = dm.findAndCloneProvider(id);
		display("Current Provider State: \n");
		display(p.reportFormat());
		
		while(contVar == 'Y'){
			display("Do you want to change the\n"
					+ "_n_ame, _a_ddress, _c_ity, _s_tate, or _z_ip");

			editWhat = scan.nextLine().toUpperCase().charAt(0);
			switch(editWhat){
				case 'N':
					display("Enter the new name: ");
					val = scan.nextLine();
					while(val.length() > 25){
						display("Name must be less than 25 characters: ");
						val = scan.nextLine();
					}
					p.setName(val);
					display("Name set to: " + val);
					break;
				case 'A':
					display("Enter the new address: ");
					val = scan.nextLine();
					while(val.length() > 25){
						display("Address must be less than 25 characters: ");
						val = scan.nextLine();
					}
					p.setAddress(val);
					display("Address set to: " + val);
					break;
				case 'C':
					display("Enter the new city: ");
					val = scan.nextLine();
					while(val.length() > 14){
						display("City must be less than 14 characters: ");
						val = scan.nextLine();
					}
					p.setCity(val);
					display("City set to: " + val);
					break;
				case 'S':
					display("Enter the new state: ");
					val = scan.nextLine();
					while(val.length() != 2){
						display("State must be 2 character abbreviateion: ");
						val = scan.nextLine();
					}
					p.setState(val);
					display("State set to: " + val);
					break;
				case 'Z':
					display("Enter the new zip code: ");
					val = scan.nextLine();
					while(val.length() != 5){
						display("Zip code must be 5 digits: ");
						val = scan.nextLine();
					}
					p.setZip(val);
					display("Zip code set to: " + val);
					break;
				default: display("Invalid Edit Field: ");
					display("Would you like to keep editing? _y_es or _n_o");
					contVar = scan.nextLine().toUpperCase().charAt(0);
					continue;
				}
			display("Updated Provider Values: \n");
			display(p.reportFormat());
			display("Would you like to keep editing? _y_es or _n_o");
			contVar = scan.nextLine().toUpperCase().charAt(0);
		}
		
		return dm.editProvider(id, p);
	}
	
	/**
	 * Provides a list of accepted user commands
	 * @return String : returns a string containing all accepted Interactive Mode commands
	 */
	public String help()
	{
		String s = "**** COMMANDS ***********************\n"
				  +"**** H == HELP - Shows Commands******\n"
				  +"**** A == ADD - Add new Data*********\n"
				  +"**** E == EDIT - Edit data***********\n"
				  +"**** D == DELETE - Delete data*******\n"
				  +"**** R == REPORT - Run Reports*******\n"
				  +"**** Q == QUIT - Quits***************\n"
				  +"*****G == GET - Show Data*****\n"
				  +"*************************************\n";
		return(s);
	}

	/**
	 * Exits interactive mode and the program entirely
	 */
	@Override
	public String quit() {
		return dm.save()+"\nExiting";
	}

	
	/**
	 * Since this is a Console Display,
	 * This method is just println();
	 * If we later make a GUI,
	 * This method would write info to
	 * the screen
	 * 
	 * @param output String to be displayed
	 */
	@Override
	public String display(String output) {
		System.out.println(output);
		return null;
	}
	
	/**
	 * Returns member info to be used in a report
	 * @param memberId : the id number of the desired member 
	 * @return String : string containing the information for the member report
	 */
	public String getMember(String memberId){
		Member m = dm.findAndCloneMember(memberId);
		if(m==null){
			return ("Member does not exist.");
		}
		return("Member Name: " + m.getName() +"\nMember Address:" + m.getAddress()+"\nMember City:" + m.getCity()+"\nMember State:" + m.getState()+"\nMember Zip Code:" + m.getZip());
	}
	
	/**
	 * Returns provider info to be used in a report
	 * @param providerId : the id number of the desired provider
	 * @return String : string containing the information for the member report
	 */
	public String getProvider(String providerId){
		User p = dm.findAndCloneProvider(providerId);
		if(p==null){
			return("Provider does not exist.");
		}
		return("Provider Name: " + p.getName() +"\nProvider Address:" + p.getAddress()+"\nProvider City:" + p.getCity()+"\nProvider State:" + p.getState()+"\nProvider Zip Code:" + p.getZip());
	}
	
}
