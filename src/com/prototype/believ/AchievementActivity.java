package com.prototype.believ;




import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;



public class AchievementActivity extends Activity {
	
	private ProgressBar pb;


	private static final String LOG_TAG = "FreEV_AchievementActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// set content
		setContentView(R.layout.activity_achievement);

		Bundle extras = getIntent().getExtras();
		List<AchievementList> rowItems = extras.getParcelableArrayList("achievmement");
		int pos = extras.getInt("position");

		TextView type = (TextView) findViewById(R.id.text_achievement_type);
		TextView desc = (TextView) findViewById(R.id.text_achievement_desc);
		TextView current = (TextView) findViewById(R.id.text_achievement_current);
		TextView high = (TextView) findViewById(R.id.text_achievement_high);
		TextView credits = (TextView) findViewById(R.id.text_achievement_credits);
		type.setText(rowItems.get(pos).getType().toString());
		desc.setText(rowItems.get(pos).getMeasure().toString());
		current.setText("You have "+rowItems.get(pos).getCurrent()+" points");
		high.setText("This achievement requires "+rowItems.get(pos).getHigh()+" points");
		credits.setText("Credits awarded for completed achievement: "+rowItems.get(pos).getCredits());
		
		pb = (ProgressBar) findViewById(R.id.achievmentProgress);
		pb.setMax(rowItems.get(pos).getHigh());
		pb.setProgress(rowItems.get(pos).getCurrent());
		pb.setIndeterminate(false);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}
}
