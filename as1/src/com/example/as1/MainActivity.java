package com.example.as1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ListActivity {	
	
	static ArrayList<Counter> counters = new ArrayList<Counter>();
	static ArrayList<String> titles = new ArrayList<String>();
	Button addCounter;
	Button sort;
	String value;
	boolean lowHigh = true;
	
	
	public static ArrayList<String> getTitles(){
		return titles;}
	
	public void setTitles(ArrayList<String> titles){
		this.titles = titles;}
	
	public static ArrayList<Counter> getCounters(){
	
		return counters;}

	public void setCounters(ArrayList<Counter> counters){
	
		this.counters = counters;}

	
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

	}


	protected void catCount(ArrayList<String>titles){
		for (int i=0; i<titles.size(); i++){
			String t = titles.get(i);
			titles.set(i, t.split(" +")[0] + String.format("%30s", Integer.toString(counters.get(i).getCount())));
		}
	}


	@Override
	protected void onResume()
	{

	// TODO Auto-generated method stub
	super.onResume();
	catCount(titles);
	final ArrayAdapter<String> counterAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, titles);
	setListAdapter(counterAdapter);
	counterAdapter.notifyDataSetChanged();
	setContentView(R.layout.counter_select);
	addCounter = (Button) findViewById(R.id.bCreate);
	sort = (Button) findViewById(R.id.bSort);
	addCounter.setOnClickListener(new View.OnClickListener(){
		
		@Override
		public void onClick(View v){
		
			AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

			alert.setMessage("Counter Name:");

			// Set an EditText view to get user input 
			final EditText input = new EditText(MainActivity.this);
			alert.setView(input);

			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  value = input.getText().toString();
			  ArrayList<Date> dates = new ArrayList<Date>();
			  Counter new_counter = new Counter(0, value, dates);
			  counters.add(new_counter);
			  String pad_num = String.format("%30s", "0"); 
			  titles.add(value + pad_num);
			  counterAdapter.notifyDataSetChanged();
			  // Do something with value!
			  }
			});

			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			  }
			});

			alert.show();
			// see http://androidsnippets.com/prompt-user-input-with-an-alertdialog
		}
	});
	
	sort.setOnClickListener(new View.OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			sort(titles);
			//http://stackoverflow.com/questions/18441846/how-sort-a-arraylist-in-java
			if (lowHigh){
			Collections.sort(counters, new Comparator<Counter>() {
		        @Override
		        public int compare(Counter  counter1, Counter  counter2)
		        {
		        	int a = counter1.count;
		        	int b = counter2.count;
		        	int cmp = a > b ? +1 : a < b ? -1 : 0;
		            return  cmp;
		        }
		    });}
			else {Collections.sort(counters, new Comparator<Counter>() {
		        @Override
		        public int compare(Counter  counter1, Counter  counter2)
		        {
		        	int a = counter1.count;
		        	int b = counter2.count;
		        	int cmp = a < b ? +1 : a > b ? -1 : 0;
		            return  cmp;
		        }
		    });}
			counterAdapter.notifyDataSetChanged();
			lowHigh = !lowHigh;
			
		}
	});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{	// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Counter choice = counters.get(position);
		CounterMenu.setCurrentCounter(choice);

		Intent ourIntent = new Intent(MainActivity.this, CounterMenu.class);
		startActivity(ourIntent);
		

	}
	public void sort(ArrayList<String> titles){
		//http://stackoverflow.com/questions/6687807/java-sort-arraylist-with-related-array
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < titles.size(); i++){
			numbers.add(Integer.parseInt(titles.get(i).split(" +")[1]));}
        // Note: This only works if names and numbers are the same size....
        SortedMap<Integer, String> pairs = new TreeMap<Integer, String>();
        for (int i = 0; i < titles.size(); i++) {
        	if (lowHigh) {pairs.put(numbers.get(i), titles.get(i));} 
        	else {pairs.put(numbers.get(numbers.size() - i - 1), titles.get(i));}
            
        }

        // Note: This destroys the original array
        int i = 0;
        for (Map.Entry<Integer, String> e : pairs.entrySet()) {
            titles.set(i++, e.getValue());
        }
		}
	
}
