package com.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.image.util.ImageLoader;
import com.desidime.R;
import com.getsetdb.DEALS;

public class DEALSAdapter extends ArrayAdapter<DEALS> {

	@SuppressWarnings("unused")
	private Context mContext;
	private ArrayList<DEALS> dataList = new ArrayList<DEALS>();
	private LayoutInflater inflater;
	private ViewHolder holder;
	private ImageLoader imageLoader;

	public DEALSAdapter(Context context, int resourceId, ArrayList<DEALS> deals) {
		super(context, resourceId, deals);
		this.mContext = context;
		this.dataList = deals;
		inflater = LayoutInflater.from(context);
		imageLoader = new ImageLoader(context);
	}

	public int getCount() {
		return dataList.size();
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_home, null);
			holder.txtOldPrice = (TextView) convertView
					.findViewById(R.id.txtOldPrice);
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.txtTitle);
			holder.txtCurrentPrice = (TextView) convertView
					.findViewById(R.id.txtCurrentPrice);
			holder.txtCommentCount = (TextView) convertView
					.findViewById(R.id.txtCommentCount);
			holder.txtPopularityCount = (TextView) convertView
					.findViewById(R.id.txtPopularityCount);
			holder.txtStoreName = (TextView) convertView
					.findViewById(R.id.txtStoreName);
			holder.imgProduct = (ImageView) convertView
					.findViewById(R.id.imgProduct);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtTitle.setText(dataList.get(position).getTitle());
		holder.txtOldPrice.setText(dataList.get(position).getOriginalPrice());
		holder.txtOldPrice.setPaintFlags(holder.txtOldPrice.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG);
		holder.txtCurrentPrice.setText("Rs."
				+ dataList.get(position).getCurrentPrice() + " ("
				+ dataList.get(position).getOffPercent() + "%off)");
		holder.txtCommentCount
				.setText(dataList.get(position).getCommentCount());
		holder.txtPopularityCount.setText(dataList.get(position)
				.getPopularityCount());
		holder.txtStoreName.setText("at "+dataList.get(position).getStore());

		imageLoader.DisplayImage(dataList.get(position).getPosted_UImage(),
				holder.imgProduct);

		return convertView;
	}

	public class ViewHolder {
		TextView txtOldPrice, txtTitle, txtCurrentPrice, txtCommentCount,
				txtPopularityCount, txtStoreName;
		ImageView imgProduct;
	}
}