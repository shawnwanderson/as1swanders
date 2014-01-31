package com.example.as1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class dateModifier
{
	
	public static ArrayList<Date> dates;
	static int[] months = new int[12];
	static int[][] days = new int[12][31];
	static int[][][] hours = new int[12][31][24];
	static Calendar cal = Calendar.getInstance();
	
	public ArrayList<Date> getDates(){
	
		return dates;}
	
	public static void setDates(ArrayList<Date> dates){
	
		dateModifier.dates = dates;}
	
	public int[] getMonths(){
		Months();
		return months;}
	
	
	public int[][] getDays(){
		Days();
		return days;}
	

	
	public static int[][][] getHours(){
		Hours();
		return hours;}
	

	public void Months(){
	for (int m = 0; m < 12; m++) {
		for (int d = 0; d < dates.size(); d++){
			Date date = dates.get(d);
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			if (month == m) {
				months[m]++;
			}
		}
	}}
	public void Days(){
	
	for (int m = 0; m < 12; m++) {
		if (months[m] > 0){
			for (int day = 0; day < days[m].length; day++) {
				for (int d = 0; d < dates.size(); d++){
					Date date = dates.get(d);
					cal.setTime(date);				
					int today = cal.get(Calendar.DAY_OF_MONTH);
					if (today == day + 1){
						days[m][day]++;
					}
					}
				}
			}
		}
	}
	
	public static void Hours(){
		
	for (int m = 0; m < 12; m++) {
		if (months[m] > 0){
			for (int day = 0; day < days[m].length; day++) {
				if (days[m][day] > 0){
					for (int h = 0; h < hours[m][day].length; h++){
						for (int d = 0; d < dates.size(); d++){
							Date date = dates.get(d);
							cal.setTime(date);				
							int hour = cal.get(Calendar.HOUR_OF_DAY);
							if (hour == h){
								hours[m][day][h]++;
					}
					}
				}
			}
		}
	}}}
	

/*	for m in months {
		if months[m] > 0{
			for day in days{
				if day > 0{
					week of that day
					for i =1 to 7{
						weekofthatday += day + i
					}

				}
			}
		}
	}

				
	
	*/

}
