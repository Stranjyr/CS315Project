package com.team9.finalproject.dataMembers;

import java.io.Serializable;
import java.util.ArrayDeque;
/**
 * This class keeps an list of sequential id numbers and can give the next valid number. Class will also return an unused
 * id number back to the bag.
 * @author William Hampton
 * 
 *
 */
public class IdBag implements Serializable
{
	private int index;
	private int range;
	private ArrayDeque<String> stack;
	/**
	 * Standard Constructor. It allows ID's to be generated starting at i and running to range.
	 * @param i: the starting value for ids
	 * @param range: the maximum value of ids.
	 */
	public IdBag(int i, int range)
	{
		this.index = i;
		this.range = range;
		stack = new ArrayDeque<String>();
		stack.push(String.format("%09d", index));
	}
	
	
	/**
	 * This method will return the next available ID number. It will go sequentially unless
	 * Someone deletes a previous id.
	 * @return id a 9-digit ID
	 */
	
	public String nextId()
	{
		if(stack.isEmpty())
		{
			return null;
		}
		String out = stack.pop();
		if(Integer.parseInt(out) == index)
		{
			index++;
			if(index<=range)
			{
				stack.push(String.format("%09d", index));
			}
		}
		return out;
	}
	/**
	 * This method is called when an ID is deleted. The ID is pushed onto the stack
	 * allowing it to be reused
	 * @param id: The id that has been deleted.
	 */
	public void freeID(String id)
	{
		stack.push(id);
	}
}