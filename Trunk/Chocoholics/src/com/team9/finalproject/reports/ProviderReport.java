package com.team9.finalproject.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.team9.finalproject.dataMembers.DataInterface;
import com.team9.finalproject.dataMembers.Service;
import java.text.NumberFormat;

/**
 * 
 *   This class is used to receive and
 *   compile Provider data to be used by the report generator
 *   @author Joe Fowler
 *   
 */
public class ProviderReport implements ReportGenerator {

	@Override
	/**
	 * This method returns report for provider using current member data
	 * @param databit: user instance to report on
	 * @param servs: list of services attached to the user
	 * @return report: report for member
	 */
	public String RunReport(DataInterface databit, List<Service> servs) {
		float fee = 0f;
		int count = 0;
		String report = databit.reportFormat();
		report+="\nServices\n";
		for(Service s: servs)
		{
			report+="*************************\n";
			report+=s.providerFormat();
			fee +=Float.parseFloat(s.getFee());
			count++;
		}
		report+="*************************\n";
		report+="Total Members Serviced: "+count+"\n";
		report+="Total Fee Earned: "+NumberFormat.getCurrencyInstance().format(fee) +"\n";
		return report;
	}

	
	@Override
	/**
	 * This method writes provider report to database
	 * so that provider report can be generated and emailed
	 * @param f: file name
	 * @param report: report string
	 * @return Code: Status of file, "Error Writing File" if file fails to write successfully
	 */
	public String saveToFile(String f, String report) { 
		try {
			File file = new File("./reports/provider/"+f+".dat");
			file.getParentFile().mkdirs();
			PrintWriter out = new PrintWriter(file);
			out.write(report);
			out.close();
			return "File written successfully";
		} catch (FileNotFoundException e) {
			return "Error Writing File";
		}
	}
}
