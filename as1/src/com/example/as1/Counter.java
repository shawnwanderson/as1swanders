package com.example.as1;

import java.util.ArrayList;
import java.util.Date;


public class Counter
{
	public int count;
	public String title;
	public ArrayList<Date> dates;
	public Counter(int count, String title, ArrayList<Date> dates)
	{

		super();
		this.count = count;
		this.title = title;
		this.dates = dates;
	}
	
	public int getCount()
	{
	
		return count;
	}
	
	public void setCount(int count)
	{
	
		this.count = count;
	}
	
	public String getTitle()
	{
	
		return title;
	}
	
	public void setTitle(String title)
	{
	
		this.title = title;
	}
	
	public ArrayList<Date> getDates()
	{
	
		return dates;
	}
	
	public void setDates(ArrayList<Date> dates)
	{
	
		this.dates = dates;
	}
	

}
