package com.team9.finalproject.dataMembers;

import java.io.Serializable;
import java.text.NumberFormat;


/**
 * This class is a storage medium for data associated with a service.
 * @author Benjamin Kidd 
 */
public class Service implements DataInterface, Serializable{

	private String serviceCode;
	private String serviceName;
	private String fee;
	private String serviceDate;
	private String recivedDate;
	private String providerNumber;
	private String providerName;
	private String memberNumber;
	private String memberName;
	private String comment;
	
	public Service(String serviceCode, String serviceName, String fee, String sericeDate, String recivedDate,
			String providerNumber, String providerName, String memberNumber, String memberName, String comment) {
		super();
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.fee = fee;
		this.serviceDate = sericeDate;
		this.recivedDate = recivedDate;
		this.providerNumber = providerNumber;
		this.providerName = providerName;
		this.memberNumber = memberNumber;
		this.memberName = memberName;
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
	
	
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * This method returns a formatted string of all the data in the class
	 * It is used to aid report standardization.
	 * @return report a formatted string of all the data in the User. 
	 * 
	 */
	@Override
	public String reportFormat() {
		return String.format("Service Code: %s\nService Date: %s\nRecived Date: %s\nProvider Number: %s\nMember Number: %s\n"
				, serviceCode, serviceDate, recivedDate, providerNumber, memberNumber);

	}
	/**
	 * This method returns a formatted string of the data needed for a
	 * member Report.
	 * It is used to aid report standardization.
	 * @return report a formatted string 
	 * 
	 */
	public String memberFormat()
	{
		return String.format("Service Date: %s\nService Name: %s\nProvider Name: %s\n"
				, serviceDate, serviceName, providerName);
	}
	/**
	 * This method returns a formatted string of the data needed for a
	 * provider Report.
	 * It is used to aid report standardization.
	 * @return report a formatted string 
	 * 
	 */
	public String providerFormat()
	{
		String actualFee = NumberFormat.getCurrencyInstance().format(Float.parseFloat(fee));
		return String.format("Service Code: %s\nService Date: %s\nFee Billed: $%s\nReceived Date: %s\nMember Number: %s\nMember Name%s\n"
				,serviceCode, serviceDate, actualFee, recivedDate, memberNumber, memberName);
	}

	

}
