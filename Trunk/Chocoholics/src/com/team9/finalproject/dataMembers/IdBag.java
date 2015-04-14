package com.team9.finalproject.dataMembers;

import java.io.Serializable;
import java.util.ArrayDeque;

public class IdBag implements Serializable
{
	private int index;
	private int range;
	private ArrayDeque<String> stack;
	public IdBag(int i, int range)
	{
		this.index = i;
		this.range = range;
		stack = new ArrayDeque<String>();
		stack.push(String.format("%09d", index));
	}
	
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
	public void freeID(String id)
	{
		stack.push(id);
	}
}