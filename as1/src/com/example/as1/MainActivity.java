package com.example.as1;

import java.util.ArrayList;
import java.util.Date;

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
	String value;
	
	
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
			titles.set(i, t.substring(0, t.length() - 3) + Integer.toString(counters.get(i).getCount()));
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
			  titles.add(value + "                                           " + "0");
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
}
