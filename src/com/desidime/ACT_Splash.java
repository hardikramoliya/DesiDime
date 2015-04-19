package com.desidime;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.http.HttpCallback;
import com.android.http.HttpPostRequest;
import com.getsetdb.DEALS;
import com.hori.util.KEY;
import com.hori.util.Utils;

public class ACT_Splash extends Activity implements HttpCallback {

	private DEALS deals = DEALS.getInstance();
	public static ArrayList<DEALS> dataPopular = new ArrayList<DEALS>();
	public static ArrayList<DEALS> dataTop = new ArrayList<DEALS>();
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.layout_splash);
		mContext = this;
		if (Utils.isOnline(mContext)) {
			sendRequestForProducts();
		} else {
			Utils.showSimpleToast(mContext, Utils.INTERNET_NOT_AVAILABLE);
		}
	}

	private void sendRequestForProducts() {
		HttpPostRequest request = new HttpPostRequest(getResources().getString(
				R.string.base_url), null, 1, ACT_Splash.this);
		new Thread(request).start();
	}

	@Override
	public void onResponse(String response, int action) {
		if (response.equalsIgnoreCase("NO")) {
			Utils.showSimpleToast(mContext,
					"Something Wrong with Internet Connection. Please try again");
		} else {
			if (action == 1) {
				dataPopular.clear();
				dataTop.clear();
				Log.e("response", "" + response);
				try {
					final JSONObject jsonObWorkShopRes = new JSONObject(
							response);
					if (jsonObWorkShopRes.has(KEY.ERRORSTR)
							&& jsonObWorkShopRes.has(KEY.RESULT)) {
						if (jsonObWorkShopRes.getString(KEY.ERRORSTR)
								.equalsIgnoreCase("Success")) {

							JSONObject jobResult = jsonObWorkShopRes
									.getJSONObject(KEY.RESULT);
							JSONArray jarrayPopular = jobResult
									.getJSONArray(KEY.POPULAR);
							for (int i = 0; i < jarrayPopular.length(); i++) {
								JSONObject jobTop = jarrayPopular
										.getJSONObject(i);
								deals = new DEALS(
										jobTop.getString(KEY.TITLE),
										jobTop.getString(KEY.STORE),
										jobTop.getString(KEY.CURRNTPRICE),
										jobTop.getString(KEY.ORIGINALPRICE),
										jobTop.getString(KEY.SHIPPINGCHARGE),
										jobTop.getString(KEY.STOREURL),
										jobTop.getString(KEY.POSTED_USER_NAME),
										jobTop.getString(KEY.POSTED_USER_IMAGE),
										jobTop.getString(KEY.CATEGORY),
										jobTop.getString(KEY.POPULARITY_COUNT),
										jobTop.getString(KEY.COMMENT_COUNT),
										jobTop.getString(KEY.OFFPERCENT));
								dataPopular.add(deals);
							}
							// TODO
							JSONArray jarrayTop = jobResult
									.getJSONArray(KEY.TOP);

							for (int i = 0; i < jarrayTop.length(); i++) {
								JSONObject jobTop = jarrayTop.getJSONObject(i);
								deals = new DEALS(
										jobTop.getString(KEY.TITLE),
										jobTop.getString(KEY.STORE),
										jobTop.getString(KEY.CURRNTPRICE),
										jobTop.getString(KEY.ORIGINALPRICE),
										jobTop.getString(KEY.SHIPPINGCHARGE),
										jobTop.getString(KEY.STOREURL),
										jobTop.getString(KEY.POSTED_USER_NAME),
										jobTop.getString(KEY.POSTED_USER_IMAGE),
										jobTop.getString(KEY.CATEGORY),
										jobTop.getString(KEY.POPULARITY_COUNT),
										jobTop.getString(KEY.COMMENT_COUNT),
										jobTop.getString(KEY.OFFPERCENT));
								dataTop.add(deals);
							}
							startActivity(Utils.nextIntent(mContext,
									ACT_Deals.class));
							finish();
						} else {
							Utils.showSimpleToast(mContext,
									"Something wrong..!!");
						}
					} else {
						Utils.showSimpleToast(mContext, "Result Not Found..");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
