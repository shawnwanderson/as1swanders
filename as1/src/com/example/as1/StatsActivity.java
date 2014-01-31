package com.example.as1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class StatsActivity extends ListActivity{
	ArrayList<String> Hours = new ArrayList<String>();
	ArrayList<String> days = new ArrayList<String>();
	ArrayList<String> months = new ArrayList<String>();
	static Calendar cal = Calendar.getInstance();
	//some code from http://www.mkyong.com/android/android-spinner-drop-down-list-example/
	static Counter currentCounter;
	Spinner spinner1;
	ArrayList<Date> dates = currentCounter.getDates();
	public static Counter getCurrentCounter(){
		return currentCounter;
	}
	public static void setCurrentCounter(Counter currentCounter){
		StatsActivity.currentCounter = currentCounter;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.print("hello");
		setContentView(R.layout.stats);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		addItemsOnSpinner1();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		
	}
	
	 // add items into spinner dynamically
	  public void addItemsOnSpinner1() {
	 
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		list.add("Hours");
		list.add("Days");
		list.add("Weeks");
		list.add("Months");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(dataAdapter);
	  }


	@Override
	protected void onResume()
	{

		// TODO Auto-generated method stub
		super.onResume();
		int category = spinner1.getSelectedItemPosition();
		//hours
		System.out.print("hello");
		if (category == 0) {
			System.out.print("hello");
			final ArrayAdapter<String> HourAdapter = new ArrayAdapter<String>(StatsActivity.this, android.R.layout.simple_list_item_1, Hours);
			dateModifier.setDates(dates);
			int hours[][][] = dateModifier.getHours();
			System.out.print("hello");
			for (int m = 0; m < 12; m++) {
				for (int day = 0; day < hours[m].length; day++) {
					for (int h = 0; h < hours[m][day].length; h++){
						if (hours[m][day][h] > 0){
							cal.set(Calendar.MONTH, m);
							cal.set(Calendar.DAY_OF_MONTH, day);
							cal.set(Calendar.HOUR_OF_DAY, h);
							Hours.add(cal.toString() + hours[m][day][h]);
						}
					}
				}
			}
			
			
			setListAdapter(HourAdapter);
			HourAdapter.notifyDataSetChanged();
		}
		//days
		else if (category == 1) {
			final ArrayAdapter<String> DayAdapter = new ArrayAdapter<String>(StatsActivity.this, android.R.layout.simple_list_item_1, days);
		}
		//weeks
		else if (category == 2) {
			final ArrayAdapter<String> WeekAdapter = new ArrayAdapter<String>(StatsActivity.this, android.R.layout.simple_list_item_1, Hours);
		}
		//months
		else if (category == 3) {
			final ArrayAdapter<String> MonthAdapter = new ArrayAdapter<String>(StatsActivity.this, android.R.layout.simple_list_item_1, months);
		}
		

		
	}
	
	

	
}
