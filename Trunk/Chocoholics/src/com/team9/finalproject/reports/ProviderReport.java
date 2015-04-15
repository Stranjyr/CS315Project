package com.team9.finalproject.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.team9.finalproject.dataMembers.DataInterface;
import com.team9.finalproject.dataMembers.Service;
import java.text.NumberFormat;
public class ProviderReport implements ReportGenerator {

	@Override
	public String RunReport(DataInterface databit, List<Service> servs) {
		float fee = 0f;
		int count = 0;
		String report = databit.reportFormat();
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
	public String saveToFile(String f, String report) {
		try {
			File file = new File("./reports/provider/"+f);
			file.mkdirs();
			PrintWriter out = new PrintWriter(file);
			out.write(report);
			out.close();
			return "File written successfully";
		} catch (FileNotFoundException e) {
			return "Error Writing File";
		}
	}
}
