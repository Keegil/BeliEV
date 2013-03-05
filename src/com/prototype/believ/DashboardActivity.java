package com.prototype.believ;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DashboardActivity extends Activity implements OnClickListener {

	View llMoneySaver;
	Drawable llMoneySaverBackground;
	TextView tvTip;
	Random randomTip = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_dashboard);

		init();

		llMoneySaverBackground.setAlpha(100);
	}

	public void init() {
		llMoneySaver = findViewById(R.id.ll_moneysaver);
		llMoneySaverBackground = llMoneySaver.getBackground();
		tvTip = (TextView) findViewById(R.id.tv_tip);
		tvTip.setOnClickListener(this);
		llMoneySaver.setOnClickListener(this);
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
			int i = randomTip.nextInt(4) + 1;
			String s = "tip" + i;
			tvTip.setText(getResources().getIdentifier(s, "string",
					"com.prototype.believ"));
			break;
		case R.id.ll_moneysaver:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}

}
