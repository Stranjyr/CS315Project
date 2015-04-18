package com.team9.finalproject.reports;

import java.util.List;

import com.team9.finalproject.dataMembers.DataInterface;
import com.team9.finalproject.dataMembers.Service;
/**
 * 
 *  This method generates a report
 *  for member/provider
 *  that is then saved to the
 *  database so that it can
 *  be requested for email.
 *  
 */

public interface ReportGenerator {
	/**
	 * 
	 * @param databit
	 * @param servs
	 */
	public String RunReport(DataInterface databit, List<Service> servs);
	/**
	 * 
	 * @param f
	 * @param Report
	 */
	public String saveToFile(String f, String Report);

}
