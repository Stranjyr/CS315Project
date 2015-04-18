package com.team9.finalproject.dataMembers;

import java.io.Serializable;

/**
 * 
 * @author wchampton
 * This Class is a storage medium for data tied to
 * a particular user
 *
 */
public class User implements DataInterface, Serializable{
	protected String name;
	protected String number;
	protected String address;
	protected String city;
	protected String state;
	protected String zip;
	/**
	 * 
	 * @param name 
	 * @param number unique id of the user
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 */
	public User(String name, String number, String address, String city,
			String state, String zip) {
		super();
		this.name = name;
		this.number = number;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Override
	public String reportFormat() {
		String report = "";
		report = String.format("Name: %s\nNumber: %s\nAddress: %s\nCity: %s\nState: %s\nZip: %s\n", 
				name, number, address, city, state, zip);
		return report;
	}
	
}
