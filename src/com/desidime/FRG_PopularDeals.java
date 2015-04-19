package com.desidime;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.adapter.DEALSAdapter;
import com.hori.util.KEY;

public class FRG_PopularDeals extends Fragment {

	private ListView listProducts;
	private View parentView;
	private Handler mHandler = new Handler();
	private DEALSAdapter dealsAdapter;

	public static Fragment newInstance(int position) {
		FRG_PopularDeals fragment = new FRG_PopularDeals();
		Bundle b = new Bundle();
		b.putInt(KEY.ARG_POSITION, position);
		fragment.setArguments(b);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		parentView = inflater.inflate(R.layout.layout_home, container, false);
		setUpView(parentView);
	
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				dealsAdapter = new DEALSAdapter(
						getActivity(), R.layout.item_home,
						ACT_Splash.dataPopular);
				listProducts.setAdapter(dealsAdapter);
				listProducts
						.setCacheColorHint(Color.TRANSPARENT);
			}
		});
		
		return parentView;
	}


	private void setUpView(View parentView2) {
		listProducts = (ListView) parentView2.findViewById(R.id.listProducts);

	}

	
}
