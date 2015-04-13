package com.team9.finalproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.team9.finalproject.dataMembers.*;
/**
 * 
 * @author wchampton
 * This class acts as a database for all
 * of the member, provider, and service data
 * we enter into Chocoholics.
 * 
 * It will be initialized once on a machine, and then
 * loaded from a serialized file every other time.
 *
 */
public class DataManager implements Serializable{

	private ArrayList<Member> memberList;
	private ArrayList<User> providerList;
	private ArrayList<Service> serviceList;
	private HashMap<String, String> serviceDir;
	private HashMap<String, Float> serviceCostMap;
	private int idIndex;
	public DataManager() {
		super();
		memberList = new ArrayList<Member>();
		providerList = new ArrayList<User>();
		serviceList = new ArrayList<Service>();
		serviceDir = new HashMap<String, String>();
		serviceCostMap = new HashMap<String, Float>();
		idIndex = 0;
		//Load in serviceDir and serviceCostMap
	}
	public void incIdIndex()
	{
		idIndex++;
		return;
	}
	public int getIdIndex()
	{
		return idIndex;
	}
	public int findMember(String id)
	{
		for(Member m : memberList)
		{
			if(m.getNumber().equals(id))
				//This is necessary because of the enhanced for loop
				return memberList.indexOf(m);
		}
		//Member Doesn't Exist
		return -1;
	}
	public int findProvider(String id)
	{
		for(User p: providerList)
		{
			if(p.getNumber().equals(id))
				//This is necessary because of the enhanced for loop
				return providerList.indexOf(p);
		}
		return -1;
	}
	public Member findAndCloneMember(String id)
	{
		for(Member m : memberList)
		{
			if(m.getNumber().equals(id))
				return m;
		}
		return null;
	}
	public User findAndCloneProvider(String id)
	{
		for(User p: providerList)
		{
			if(p.getNumber().equals(id))
				return p;
		}
		return null;
	}
	public String validateMember(String memberID)
	{
		int m = findMember(memberID);
		if(m!=-1)
		{
			Member mem = memberList.get(m);
			return mem.getStatus();
		}
		return "Member Does Not Exist";
	}
	
	public ArrayList<Service> findServiceByMember(String id)
	{
		ArrayList<Service> ser = new ArrayList<Service>();
		for(Service s: serviceList)
		{
			if(s.getMemberNumber().equals(id))
			{
				ser.add(s);
			}
		}
		return ser;
	}
	public ArrayList<Service> findServiceByProvider(String id)
	{
		ArrayList<Service> ser = new ArrayList<Service>();
		for(Service s: serviceList)
		{
			if(s.getProviderNumber().equals(id))
			{
				ser.add(s);
			}
		}
		return ser;
	}
	public void addProvider(User p)
	{
		providerList.add(p);
	}
	public void addMember(Member m)
	{
		memberList.add(m);
	}
	public void addService(Service s)
	{
		serviceList.add(s);
	}
	public String editMember(String id, Member newM)
	{
		int memIndex = findMember(id);
		if(memIndex == -1)
		{
			return "FAILED: INVALID ID";
		}
		providerList.set(memIndex, newM);
		return "EDIT COMPLETE";
	}
	public String editProvider(String id, User newP)
	{
		int provIndex = findProvider(id);
		if(provIndex == -1)
		{
			return "FAILED: INVALID ID";
		}
		providerList.set(provIndex, newP);
		return "EDIT COMPLETE";
	}
	public String removeMember(String id)
	{
		int memIndex = findMember(id);
		if(memIndex == -1)
		{
			return "FAILED: INVALID ID";
		}
		providerList.remove(memIndex);
		return "EDIT COMPLETE";
	}
	public String removeProvider(String id)
	{
		int provIndex = findProvider(id);
		if(provIndex == -1)
		{
			return "FAILED: INVALID ID";
		}
		providerList.remove(provIndex);
		return "EDIT COMPLETE";
	}
	public HashMap<String, String> getDir()
	{
		return serviceDir;
	}
	public float calculateServiceCost(String serviceCode)
	{
		return serviceCostMap.get(serviceCode);
	}
	public String listMembers()
	{
		String s = "";
		for(Member m: memberList)
		{
			s+="Name:: "+m.getName()+"\n --  ID::  "+m.getNumber();
		}
		return s;
	}
	public String listProviders()
	{
		String s = "";
		for(User p: providerList)
		{
			s+="Name:: "+p.getName()+"\n --  ID::  "+p.getNumber();
		}
		return s;
	}
	public String save()
	{
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("/ChocoData.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         return "Data is saved in /ChocoData.ser";
	      }catch(IOException i)
	      {  
	    	  i.printStackTrace();
	    	  return "Save failed: Data Corrupt";
	      }
	}
	
	

}
