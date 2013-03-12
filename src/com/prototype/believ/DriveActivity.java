package com.prototype.believ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DriveActivity extends Activity implements OnClickListener {

	TextView textFile;
	Button bAustin;
	Button bSteph;
	Button bAlex;

	InputStream is;
	BufferedReader reader;
	String line;
	List<Double> entryData;
	List<Double> accData;
	XYSeries accSeries;
	XYMultipleSeriesDataset dataset;
	XYMultipleSeriesRenderer renderer;
	XYSeriesRenderer r;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String text = (String) msg.obj;
			textFile.setText(text);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drive);

		init();
		/*
		 * try { readText(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch (InterruptedException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * getRenderer();
		 * 
		 * if (renderer.getSeriesRendererCount() == dataset.getSeriesCount() &&
		 * renderer.getSeriesRendererCount() != 0 && dataset.getSeriesCount() !=
		 * 0) { try { ChartFactory.getLineChartView(this, readText(),
		 * getRenderer()); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch (InterruptedException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } }
		 */
	}

	public void init() {
		textFile = (TextView) findViewById(R.id.textfile);
		bAustin = (Button) findViewById(R.id.b_austin);
		bSteph = (Button) findViewById(R.id.b_steph);
		bAlex = (Button) findViewById(R.id.b_alex);

		bAustin.setOnClickListener(this);
		bSteph.setOnClickListener(this);
		bAlex.setOnClickListener(this);

		entryData = new ArrayList<Double>();
		accData = new ArrayList<Double>();

		accSeries = new XYSeries("Acceleration");
		dataset = new XYMultipleSeriesDataset();
	}

	/*
	 * public XYMultipleSeriesDataset readText() throws IOException,
	 * InterruptedException { line = reader.readLine(); double d = 0; while
	 * (line != null) { String columns[] = line.split("\t");
	 * textFile.setText(textFile.getText() + columns[1] + "\n");
	 * accData.add(Double.parseDouble(columns[1])); accSeries.add(d,
	 * Double.parseDouble(columns[1]) % 100); entryData.add(d); d++; line =
	 * reader.readLine(); } dataset.addSeries(accSeries); return dataset; }
	 */

	/*
	 * private XYMultipleSeriesRenderer getRenderer() { renderer = new
	 * XYMultipleSeriesRenderer(); renderer.setAxisTitleTextSize(12);
	 * renderer.setChartTitleTextSize(12); renderer.setLabelsTextSize(15);
	 * renderer.setLegendTextSize(15); renderer.setPointSize(5f);
	 * renderer.setMargins(new int[] { 20, 30, 15, 0 }); r = new
	 * XYSeriesRenderer(); r.setColor(Color.BLACK);
	 * r.setPointStyle(PointStyle.CIRCLE); r.setFillBelowLine(false);
	 * r.setFillPoints(true); renderer.addSeriesRenderer(r);
	 * setChartSettings(renderer); return renderer; }
	 */

	/*
	 * private void setChartSettings(XYMultipleSeriesRenderer renderer) {
	 * renderer.setChartTitle("Chart demo"); renderer.setXTitle("x values");
	 * renderer.setYTitle("y values"); renderer.setApplyBackgroundColor(false);
	 * renderer.setRange(new double[] { 0, 6, -70, 40 });
	 * renderer.setFitLegend(false); renderer.setAxesColor(Color.BLACK);
	 * renderer.setShowGrid(true); renderer.setXAxisMin(0);
	 * renderer.setXAxisMax(1000); renderer.setYAxisMin(0);
	 * renderer.setZoomEnabled(false); renderer.setYAxisMax(0.5); }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drive, menu);
		return true;
	}

	Thread myThread = new Thread() {

		@Override
		public void run() {
			while (line != null) {
				String columns[] = line.split("\t");
				Message msg = new Message();
				String textTochange = textFile.getText() + columns[1] + "\n";
				msg.obj = textTochange;
				mHandler.sendMessage(msg);
				try {
					line = reader.readLine();
					sleep(500);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	@Override
	public void onClick(View view) {
		if (myThread.isAlive()) {
			myThread.interrupt();
		}
		textFile.setText(null);
		switch (view.getId()) {
		case R.id.b_austin:
			is = getResources().openRawResource(R.raw.austin_elcamino_accel);
			reader = new BufferedReader(new InputStreamReader(is));
			break;
		case R.id.b_steph:
			is = getResources().openRawResource(R.raw.steph_elcamino_accel);
			reader = new BufferedReader(new InputStreamReader(is));
			break;
		case R.id.b_alex:
			is = getResources().openRawResource(R.raw.alex_innout_accel);
			reader = new BufferedReader(new InputStreamReader(is));
			break;
		}
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myThread.start();
	}
}
