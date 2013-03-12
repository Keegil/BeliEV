package com.prototype.believ;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends Activity implements OnClickListener,
		OnDragListener, OnLongClickListener {

	LinearLayout llSaver;
	LinearLayout llMoneySaver;
	LinearLayout llTimeSaver;
	LinearLayout llEnvironmentSaver;
	LinearLayout llScrollLeft;
	LinearLayout llScrollRight;
	ScrollView svFeed;
	ImageView ivGraph;
	Drawable llSaverBackground;
	TextView tvTip;
	Display display;
	Random randomTip;
	Point size;
	String activePane;
	float startDragX;
	float currentDragX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_dashboard);

		init();
	}

	public void init() {
		// initialize LinearLayouts
		llSaver = (LinearLayout) findViewById(R.id.ll_saver);
		llMoneySaver = (LinearLayout) findViewById(R.id.ll_moneysaver);
		llTimeSaver = (LinearLayout) findViewById(R.id.ll_timesaver);
		llEnvironmentSaver = (LinearLayout) findViewById(R.id.ll_environmentsaver);
		llScrollLeft = (LinearLayout) findViewById(R.id.ll_scroll_left);
		llScrollRight = (LinearLayout) findViewById(R.id.ll_scroll_right);

		svFeed = (ScrollView) findViewById(R.id.sv_feed);
		ivGraph = (ImageView) findViewById(R.id.iv_graph);

		// set background alpha on all saver panes
		llSaverBackground = llMoneySaver.getBackground();
		llSaverBackground.setAlpha(100);
		llSaverBackground = llTimeSaver.getBackground();
		llSaverBackground.setAlpha(100);
		llSaverBackground = llEnvironmentSaver.getBackground();
		llSaverBackground.setAlpha(100);

		// initialize TextViews
		tvTip = (TextView) findViewById(R.id.tv_tip);

		activePane = "time";

		// set OnClickListeners
		tvTip.setOnClickListener(this);
		llMoneySaver.setOnClickListener(this);
		llScrollLeft.setOnClickListener(this);
		llScrollRight.setOnClickListener(this);
		llSaver.setOnDragListener(this);
		svFeed.setOnLongClickListener(this);
		ivGraph.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_tip:
			randomTip = new Random();
			int i = randomTip.nextInt(4) + 1;
			String s = "tip" + i;
			tvTip.setText(getResources().getIdentifier(s, "string",
					"com.prototype.believ"));
			break;
		case R.id.ll_scroll_left:
			if (activePane.equals("money")) {
				llMoneySaver.setVisibility(View.GONE);
				llTimeSaver.setVisibility(View.VISIBLE);
				activePane = "time";
				llScrollLeft.setBackgroundResource(R.drawable.back_environment);
				llScrollRight.setBackgroundResource(R.drawable.back_money);
			} else if (activePane.equals("time")) {
				llTimeSaver.setVisibility(View.GONE);
				llEnvironmentSaver.setVisibility(View.VISIBLE);
				activePane = "environment";
				llScrollLeft.setBackgroundResource(R.drawable.back_money);
				llScrollRight.setBackgroundResource(R.drawable.back_time);
			} else if (activePane.equals("environment")) {
				llEnvironmentSaver.setVisibility(View.GONE);
				llMoneySaver.setVisibility(View.VISIBLE);
				activePane = "money";
				llScrollLeft.setBackgroundResource(R.drawable.back_time);
				llScrollRight
						.setBackgroundResource(R.drawable.back_environment);
			}
			break;
		case R.id.ll_scroll_right:
			if (activePane.equals("money")) {
				llMoneySaver.setVisibility(View.GONE);
				llEnvironmentSaver.setVisibility(View.VISIBLE);
				activePane = "environment";
				llScrollLeft.setBackgroundResource(R.drawable.back_money);
				llScrollRight.setBackgroundResource(R.drawable.back_time);
			} else if (activePane.equals("time")) {
				llTimeSaver.setVisibility(View.GONE);
				llMoneySaver.setVisibility(View.VISIBLE);
				activePane = "money";
				llScrollLeft.setBackgroundResource(R.drawable.back_time);
				llScrollRight
						.setBackgroundResource(R.drawable.back_environment);
			} else if (activePane.equals("environment")) {
				llEnvironmentSaver.setVisibility(View.GONE);
				llTimeSaver.setVisibility(View.VISIBLE);
				activePane = "time";
				llScrollLeft.setBackgroundResource(R.drawable.back_environment);
				llScrollRight.setBackgroundResource(R.drawable.back_money);
			}
			break;
		case R.id.iv_graph:
			Intent intent = new Intent(this, DriveActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onDrag(View view, DragEvent event) {
		switch (event.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED:
			startDragX = event.getX();
			break;
		case DragEvent.ACTION_DRAG_LOCATION:
			currentDragX = event.getX();
			if (currentDragX >= (startDragX + 100)) {
				Toast.makeText(this, "You have dragged right",
						Toast.LENGTH_LONG).show();
			} else if (currentDragX <= (startDragX - 100)) {
				Toast.makeText(this, "You have dragged left", Toast.LENGTH_LONG)
						.show();
			}
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean onLongClick(View view) {
		switch (view.getId()) {
		case R.id.sv_feed:
			svFeed.animate().scaleYBy(2);
			break;
		}
		return true;
	}

}
