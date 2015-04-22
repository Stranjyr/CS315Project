package com.team9.finalproject.reports;

import java.util.List;

import com.team9.finalproject.dataMembers.DataInterface;
import com.team9.finalproject.dataMembers.Service;
/**
 * 
 *  This interface generates a report
 *  for member/provider
 *  that is then saved to the
 *  database so that it can
 *  be requested for email.
 *  
 */

public interface ReportGenerator {
	/**
	 * Creates a string report of the databit and it's services
	 * @param databit: the databit class to report on
	 * @param servs: the list of services to report on
	 * @return report: string version of the report
	 */
	public String RunReport(DataInterface databit, List<Service> servs);
	/**
	 * Saves a report string to a file
	 * @param f filename
	 * @param Report report string to save
	 * @return code: string that specified if the operation worked
	 */
	public String saveToFile(String f, String Report);

}
