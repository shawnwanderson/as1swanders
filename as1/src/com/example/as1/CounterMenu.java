package com.example.as1;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CounterMenu extends Activity
{

	static Counter currentCounter;
	int count = currentCounter.getCount();
	ArrayList<Date> dates = currentCounter.getDates();
	String title = currentCounter.getTitle();
	ArrayList<Counter> counters = MainActivity.getCounters();
	ArrayList<String> titles = MainActivity.getTitles();
	Button add;
	Button delete;
	Button reset;
	Button rename;
	TextView display;
	TextView displayTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		add = (Button) findViewById(R.id.bAdd);
		delete = (Button) findViewById(R.id.bDelete);
		reset = (Button) findViewById(R.id.bReset);
		rename = (Button) findViewById(R.id.bRename);
		display = (TextView) findViewById(R.id.tvDisplay);
		displayTitle = (TextView) findViewById(R.id.tvTitle);
		displayTitle.setText(title);
		display.setText("Your total is " + count);
		add.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				count++;
				Date currentDate = new Date();
				dates.add(currentDate);
				currentCounter.setCount(count);
				currentCounter.setDates(dates);
				display.setText("Your total is " + count);

			}
		});
		reset.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				count = 0;
				currentCounter.setCount(count);
				display.setText("Your total is " + count);

			}
		});

		delete.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				counters.remove(counters.indexOf(currentCounter));
				titles.remove(titles.indexOf(title));

				Intent ourIntent = new Intent(CounterMenu.this,
						MainActivity.class);
				startActivity(ourIntent);

			}
		});
		
		rename.setOnClickListener(new View.OnClickListener()
		{



				@Override
				public void onClick(View v){
				
					AlertDialog.Builder alert = new AlertDialog.Builder(CounterMenu.this);

					alert.setMessage("Counter Name:");

					// Set an EditText view to get user input 
					final EditText input = new EditText(CounterMenu.this);
					alert.setView(input);

					alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					  String value = input.getText().toString();
					  currentCounter.setTitle(value);
					  titles.set(titles.indexOf(title), value);
					  displayTitle.setText(value);
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

	public Counter getCurrentCounter()
	{

		return currentCounter;
	}

	public static void setCurrentCounter(Counter current_counter)
	{

		CounterMenu.currentCounter = current_counter;
	}

}
