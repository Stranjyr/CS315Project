package com.team9.finalproject.connectors;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalTime;
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

public class InteractiveConnector implements ConnectorInterface{

	private DataManager dm;
	private Scanner scan;
	public InteractiveConnector()
	{
		scan = new Scanner(System.in);
		display(startScreen());
		dm = loadDataManager();
		commandLoop();
		scan.close();
	}
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
		  +"**** G == Get Data - Show Data*****\n"
		  +"**** Q == QUIT - Quits***************\n"
		  +"*************************************\n"
		  +"*************************************\n";
		
		  
		return s;
	}
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
					char editWhat;
					String val;
					switch(scan.nextLine().toUpperCase().charAt(0))
					{
						case 'M':
							display("Enter Member ID");
							id = scan.nextLine();
							display("Do you want to change the\n"
									+ "_n_ame, _a_ddress, _c_ity, _s_tate, _z_ip, or s_t_atus?");
							editWhat = scan.nextLine().toUpperCase().charAt(0);
							display("Enter new Value");
							val = scan.nextLine();
							display(editMember(id, editWhat, val));
							break;
						case 'P':
							display("Enter Provider ID");
							id = scan.nextLine();
							display("Do you want to change the\n"
									+ "_n_ame, _a_ddress, _c_ity, _s_tate, or _z_ip");
							editWhat = scan.nextLine().toUpperCase().charAt(0);
							display("Enter new Value");
							val = scan.nextLine();
							display(editProvider(id, editWhat, val));
							break;
						default: break;
					}
					break;
				case 'D':
					display("Delete a _M_ember, _P_rovider, or _C_ancel?");
					switch(scan.nextLine().toUpperCase().charAt(0))
					{
						case 'M':
							display("Enter Member ID");
							id = scan.nextLine();
							display(dm.removeMember(id));
							break;
						case 'P':
							display("Enter Provider ID");
							id = scan.nextLine();
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
	private String memberReport(String id) {
		if(dm.findMember(id) == -1)
		{
			return "Error: Invalid Provider Number";
		}
		ArrayList<Service> sers = dm.findServiceByMember(id);
		ReportGenerator r = new MemberReport();
		String rep = r.RunReport(dm.findAndCloneMember(id), sers);
		return r.saveToFile(id+new Date().toString().replace(" ", "").replace(":", "-"), rep);
	}
	public String addMember(String name, String addr, String city, String state, String zip)
	{
		//Todo
		//Validate name
		//Validate addr
		//validate city
		//validate state
		//validate zip
		String id = dm.getNextMemID();
		if(id == null)
		{
			return "Out of member ids. Please Contact Your Support Center";
		}
		dm.addMember(new Member(name, id, addr, city, state, zip, "Active"));
		return "Member "+id+" added successfuly";
	}
	public String addProvider(String name, String addr, String city, String state, String zip)
	{
		//Todo
		//Validate name
		//Validate addr
		//validate city
		//validate state
		//validate zip
		String id = dm.getNextProvID();
		if(id == null)
		{
			return "Out of provider ids. Please Contact Your Support Center";
		}
		dm.addProvider(new User(name, id, addr, city, state, zip));
		return "Provider "+id+" added successfuly";
	}
	public String editMember(String id, char eW, String val)
	{
		if(dm.findMember(id) == -1)
		{
			return "Invalid Member ID";
		}
		Member m = dm.findAndCloneMember(id);
		//TODO 
		//Validate val
		//in switch/case
		switch(eW)
		{
			case 'N':
				m.setName(val);
				break;
			case 'A':
				m.setAddress(val);
				break;
			case 'C':
				m.setCity(val);
				break;
			case 'S':
				m.setState(val);
			case 'Z':
				m.setZip(val);
			case 'T':
				m.setStatus(val);
			default: return "ERROR: Invalid Edit Field";
		}
		return dm.editMember(id, m);
	}
	
	public String editProvider(String id, char eW, String val)
	{
		if(dm.findProvider(id) == -1)
		{
			return "Invalid Member ID";
		}
		User m = dm.findAndCloneProvider(id);
		//TODO 
		//Validate val
		//in switch/case
		switch(eW)
		{
			case 'N':
				m.setName(val);
				break;
			case 'A':
				m.setAddress(val);
				break;
			case 'C':
				m.setCity(val);
				break;
			case 'S':
				m.setState(val);
			case 'Z':
				m.setZip(val);
			default: return "ERROR: Invalid Edit Field";
		}
		return dm.editProvider(id, m);
	}
	
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
	public String getMember(String memberId){
		Member m = dm.findAndCloneMember(memberId);
		if(m==null){
			return ("Member does not exist.");
		}
		return("Member Name: " + m.getName() +"\nMember Address:" + m.getAddress()+"\nMember City:" + m.getCity()+"\nMember State:" + m.getState()+"\nMember Zip Code:" + m.getZip());
	}
	public String getProvider(String providerId){
		User p = dm.findAndCloneProvider(providerId);
		if(p==null){
			return("Provider does not exist.");
		}
		return("Provider Name: " + p.getName() +"\nProvider Address:" + p.getAddress()+"\nProvider City:" + p.getCity()+"\nProvider State:" + p.getState()+"\nProvider Zip Code:" + p.getZip());
	}
	
}
