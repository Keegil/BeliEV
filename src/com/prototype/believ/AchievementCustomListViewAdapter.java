package com.prototype.believ;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AchievementCustomListViewAdapter extends ArrayAdapter<AchievementList> {

	Context context;
	



	public AchievementCustomListViewAdapter(Context context, int resourceId,
			List<AchievementList> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/* private view holder class */
	private class ViewHolder {
		TextView li_id;
		TextView li_type;
		TextView li_current;
		TextView li_high;
		TextView li_low;
		TextView li_measure;
		ProgressBar pb;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		AchievementList rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_achievment, null);
			holder = new ViewHolder();
			
			holder.li_id = (TextView) convertView.findViewById(R.id.li_id);
			holder.li_type = (TextView) convertView.findViewById(R.id.li_type);
			holder.li_current = (TextView) convertView.findViewById(R.id.li_current);
			holder.li_high = (TextView) convertView.findViewById(R.id.li_high);
			holder.li_low = (TextView) convertView.findViewById(R.id.li_low);
			holder.li_measure = (TextView) convertView.findViewById(R.id.li_measure);
			holder.pb = (ProgressBar) convertView.findViewById(R.id.achievmentProgress);
			
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.li_id.setText(""+rowItem.getId());
		holder.li_type.setText(rowItem.getType());
		holder.li_current.setText(""+rowItem.getCurrent());
		holder.li_high.setText("High "+rowItem.getHigh());
		holder.li_low.setText("Low "+rowItem.getCredits());
		holder.li_measure.setText(rowItem.getMeasure());
		holder.pb.setMax(rowItem.getHigh());
		holder.pb.setProgress(rowItem.getCurrent());
		holder.pb.setIndeterminate(false);

		
		return convertView;
	}
}