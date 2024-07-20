package com.any.assessment;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcurrentModificationExceptionDemo 
{
	public static void main(String[] args) 
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		
		Iterator<String> iterator = list.iterator();
		
		while(iterator.hasNext())
		{
			String fruit = iterator.next();
			System.out.println(fruit);
			list.remove(fruit);
		}
		
	}
}
