package com.team9.finalproject.connectors;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Scanner;

import com.team9.finalproject.DataManager;
import com.team9.finalproject.dataMembers.Service;

public class ProviderConnector implements ConnectorInterface{

	private DataManager dm;
	Scanner scan;  // Used to get input from user
	
	public ProviderConnector()
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
		  +"**** WELCOME PROVIDER ***************\n"
		  +"*************************************\n"
		  +"**** CHOCOHOLICS DATAMANAGER 1.0 ****\n"
		  +"**** A NEW PARADIGM IN CHOCOLATE ****\n"
		  +"*************************************\n"
		  +"**** COMMANDS ***********************\n"
		  +"**** H == HELP - Shows this list*****\n"
		  +"**** B == BILL - Bill for service****\n"
		  +"**** V == VALIDATE - Check Member****\n"
		  +"**** D == DIRECTORY - Shows Directory\n"
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
	         //i.printStackTrace();
	         return new DataManager();
	      }catch(ClassNotFoundException c)
	      {
	         
	         //c.printStackTrace();
	         return new DataManager();
	      }
		
	}

	@Override
	public String commandLoop() {
		
		while(true)
		{
			display("Next Command?");
			String command = scan.next();
			switch(command.toUpperCase().charAt(0))
			{
				case 'H':
					display(help());
					break;
				case 'B':
					display("Enter Member ID to bill");
					String memberId = scan.next();
					// Validate member before continuing
					String status = dm.validateMember(memberId);
					if(!status.toLowerCase().equals("active"))
					{
						display("Member Error: "+ status);
						display("Canceling");
						break;
					}
					display("Enter your provider Number");
					String provId = scan.next();
					// Validate provider
					if(dm.findProvider(provId) == -1)
					{
						display("Provider number invalid");
						display("Canceling");
						break;
					}
					display("Enter Service Code");
					String serCode = scan.next();
					// Validate Service Code
					if(dm.getDir().get(serCode) == null)
					{
						display("Service Code Error: Check Directory");
						display("Canceling");
						break;
					}
					display("Enter month service was provided as a 2 digit number");
					String m = scan.next();
					display("Enter day of month service was provided as a 2 digit number");
					String d = scan.next();
					display("Enter year service was provided as a four digit number");
					String y = scan.next();
					// Validate year
					int year = Integer.parseInt(y);
					if (year < 2000 || year > 3000)
					{
						display("Invalid year (2000 < Year < 3000)");
						break;
					}
					display("Enter comment (- for no comment)");
					String com = scan.next();
					display("Do you want to add this bill? (Y or N)");
					if (scan.next().toUpperCase().charAt(0) != 'Y')
					{
						display("Canceling...");
						break;
					}
					display(bill(memberId, provId, serCode, m+"/"+d+"/"+y, com));
					break;
				case 'V':
					display("Enter Member ID to validate");
					display(validate(scan.next()));
					break;
				case 'D':
					display(getDirectory());
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
	public String help()
	{
		String s = "**** COMMANDS ***********************\n"
				  +"**** H == HELP - Shows this list*****\n"
				  +"**** B == BILL - Bill for service****\n"
				  +"**** V == VALIDATE - Check Member****\n"
				  +"**** D == DIRECTORY - Shows Directory\n"
				  +"**** Q == QUIT - Quits***************\n"
				  +"*************************************\n"
				  +"*************************************\n"; 
		return(s);
	}
	public String bill(String memberId, String provId, String serviceCode, String dateBill, String comment)
	{
		
		String status = dm.validateMember(memberId);
		if(!(status.toLowerCase()).equals("active"))
		{
			return("Member Error: "+ status);
			
		}
		if(dm.findProvider(provId) == -1)
		{
			return("Provider number invalid");
		}
		if(dm.getDir().get(serviceCode) == null)
		{
			return("Service Code Error: Check Directory");
		}
		Date bill;
		try{

			bill = new SimpleDateFormat("MM/dd/yyyy").parse(dateBill);
		}
		catch(ParseException p)
		{
			return "Error: Invalid Date";
		}
		if(comment.length()> 100)
		{
			comment = comment.substring(0,  100);
		}
		
		//Now that all the validation is successful, add the service
		dm.addService(serviceCode, dateBill, new Date().toString(), provId, memberId, comment);
		
		return "Billing Successful";
		
	}
	public String validate(String memberId)
	{
		String status = dm.validateMember(memberId);
		if(!(status.toLowerCase()).equals("active"))
		{
			return("Member Error: "+ status);
			
		}
		return status;
	}
	public String getDirectory()
	{
		String d = "";
		for(Entry<String, String> k: dm.getDir().entrySet())
		{
			d+=k.getKey()+" ::: "+k.getValue()+"\n";
		}
		return d;
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

}
