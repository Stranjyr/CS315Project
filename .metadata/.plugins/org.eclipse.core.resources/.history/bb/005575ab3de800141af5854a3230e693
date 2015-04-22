package com.team9.finalproject.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.team9.finalproject.dataMembers.DataInterface;
import com.team9.finalproject.dataMembers.Service;


/**
 * 
 *   This class is used to receive and
 *   compile Member data to be used by the report generator
 *   
 */

public class MemberReport implements ReportGenerator {


	
	@Override
	/**
	 * This method returns report for member using current member data
	 * @param databit
	 * @param servs
	 * @return report - report for member
	 */
	public String RunReport(DataInterface databit, List<Service> servs) {
		String report = databit.reportFormat();
		report+="\nServices\n";
		for(Service s: servs)
		{
			report+="*************************\n";
			report+=s.memberFormat();
		}
		return report;
	}

	@Override
	/**
	 * This method writes member report to database
	 * so that member report can be generated and emailed
	 * @param f
	 * @param report
	 * @return Status of file, "Error Writing File" if file fails to write successfully
	 */

	public String saveToFile(String f, String report) {
		try {
			File file = new File("reports/member/"+f+".dat");
			file.getParentFile().mkdirs();
			file.createNewFile();
			PrintWriter out = new PrintWriter(file);
			out.write(report);
			out.close();
			return "File written successfully";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Error Writing File";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error Writing File";
		}
	}

	

}
