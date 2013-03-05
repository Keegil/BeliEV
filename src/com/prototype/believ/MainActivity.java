package com.prototype.believ;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	
	
	/*
	 * 
	 * SETTING UP TEST ARRAYS FOR CUSTOM LIST VIEW ADAPTER
	 */
	ArrayList<AchievementList> rowItems;

	// Test set
	public static final int[] id_set = new int[] { 0, 1, 2, 3, 4, 5 };
	public static final String[] type_set = new String[] { 
		"Road Trip",
		"The Quick Charger", 
		"Car Pool",
		"Low Accelerator",
		"Big Credit",
		"Couch Driver"
		};
	public static final int[] current_set = new int[] { 10, 20, 30, 40, 50, 3 };
	public static final int[] high_set = new int[] { 100, 100, 100, 100, 100, 5 };
	public static final int[] credits_set = new int[] { 25, 50, 30, 40, 10, 15 };
	public static final String[] measure_set = new String[] {
		"Drive non-stop for 100Km",
		"Charge your EV to atleast 80% using a Quick Charger",
		"Drive together with a passenger",
		"Hold your acceleration below XX m/s²",
		"Get 100 Credit Points",
		"Drive 5 trips under 1Km"
		};

	/*
	 * 
	 * 
	 * END OF VARIABLES USED FOR TESTING CUSTOM LIST VIEW ADAPTER
	 * 
	 * 
	 * 
	 * *
	 */
	
	
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		
		
		/*
		 * POPULATING TEST ARRAY ROWITEMS FOR CUSTOM LIST VIEW ADAPTER
		 */
		rowItems = new ArrayList<AchievementList>();
		for (int i = 0; i < id_set.length; i++) {
			AchievementList item = new AchievementList(id_set[i], type_set[i],
					current_set[i], high_set[i], credits_set[i], measure_set[i]);
			rowItems.add(item);
		}
		
		
		AchievementCustomListViewAdapter adapter = new AchievementCustomListViewAdapter(
				this, R.layout.list_achievment, rowItems);
		setListAdapter(adapter);
		/*
		 * END OF TESTING CUSTOM LIST VIEW ADAPTER
		 */
		
		
		
		
	}
	
	
	
	
	
	
	
	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {



		/**
		 * Toast message will be shown when you click any list element
		 */
		Toast.makeText(
				this,
				"Clicked "+rowItems.get(position).getType(), Toast.LENGTH_SHORT)
				.show();

		Intent i = new Intent(this, AchievementActivity.class);

		i.putParcelableArrayListExtra("achievmement", rowItems);
		i.putExtra("position", position);
		startActivity(i);

	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
