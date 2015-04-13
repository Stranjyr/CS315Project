package com.team9.finalproject.dataMembers;

import java.io.Serializable;

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

	@Override
	public String reportFormat() {
		return super.reportFormat();
	}

}
