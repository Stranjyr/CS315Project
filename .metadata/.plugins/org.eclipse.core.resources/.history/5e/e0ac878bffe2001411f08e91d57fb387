package com.team9.finalproject.dataMembers;

import java.io.Serializable;

public class Service implements DataInterface, Serializable{

	private String serviceCode;
	private String serviceName;
	private String serviceDate;
	private String recivedDate;
	private String providerNumber;
	private String providerName;
	private String memberNumber;
	private String comment;
	
	public Service(String serviceCode, String serviceName, String sericeDate, String recivedDate,
			String providerNumber, String providerName, String memberNumber, String comment) {
		super();
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.serviceDate = sericeDate;
		this.recivedDate = recivedDate;
		this.providerNumber = providerNumber;
		this.providerName = providerName;
		this.memberNumber = memberNumber;
		this.comment = comment;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String sericeDate) {
		this.serviceDate = sericeDate;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getRecivedDate() {
		return recivedDate;
	}

	public void setRecivedDate(String recivedDate) {
		this.recivedDate = recivedDate;
	}

	public String getProviderNumber() {
		return providerNumber;
	}

	public void setProviderNumber(String providerNumber) {
		this.providerNumber = providerNumber;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String reportFormat() {
		return String.format("Service Code: %s\nService Date: %s\nRecived Date: %s\nProvider Number: %s\nMember Number: %s\n"
				, serviceCode, serviceDate, recivedDate, providerNumber, memberNumber);

	}
	public String memberFormat()
	{
		return String.format("Service Date: %s\nRecived Date: %s\nProvider Number: %s\nMember Number: %s\n"
				, serviceCode, serviceDate, recivedDate, providerNumber, memberNumber);
	}
	
	public String providerFormat()
	{
		return String.format("Service Code: %s\nService Date: %s\nRecived Date: %s\nProvider Number: %s\nMember Number: %s\n"
				, serviceCode, serviceDate, recivedDate, providerNumber, memberNumber);
	}

	

}
