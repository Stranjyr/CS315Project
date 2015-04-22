package com.team9.finalproject.dataMembers;

import java.io.Serializable;
/**
 * 
 * 
 *This class is a storage medium for data associated with a  particular member.
 *@author Justin Gifford
 */
public class Member extends User implements DataInterface, Serializable{

	private String status;
	public Member(String name, String number, String address, String city,
			String state, String zip, String status) {
		super(name, number, address, city, state, zip);
		this.status = status;

	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * This method returns a formatted string of all the data in the class
	 * It is used to aid report standardization.
	 * @return report a formatted string of all the data in the Member. 
	 * 
	 */
	@Override
	public String reportFormat() {
		return super.reportFormat();
	}

}
