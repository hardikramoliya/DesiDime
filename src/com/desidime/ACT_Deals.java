package com.desidime;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.viewpager.PagerSlidingTabStrip;
import com.hori.util.Utils;
import com.ldrawer.ActionBarDrawerToggle;
import com.ldrawer.DrawerArrowDrawable;

public class ACT_Deals extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerArrowDrawable drawerArrow;
	private ActionBar actionBar;
	private PagerSlidingTabStrip sliding_Tabs;
	private ViewPager view_pager;
	private MyPagerAdapter myPageradapter;
	private boolean doubleBackToExitPressedOnce = false;
	public static ArrayList<String> dataList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_deals);
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		setUpView();

		sliding_Tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		view_pager = (ViewPager) findViewById(R.id.pager);

		myPageradapter = new MyPagerAdapter(getSupportFragmentManager());
		view_pager.setAdapter(myPageradapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		view_pager.setPageMargin(pageMargin);

		sliding_Tabs.setViewPager(view_pager);

	}

	private void setUpView() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.navdrawer);

		drawerArrow = new DrawerArrowDrawable(this) {
			@Override
			public boolean isLayoutRtl() {
				return false;
			}
		};
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				drawerArrow, R.string.drawer_open, R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();

		String[] values = new String[] { "Refresh", "Apps" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);
		mDrawerList.setAdapter(adapter);
		mDrawerList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						switch (position) {
						case 0:
							mDrawerToggle.setAnimateEnabled(true);
							drawerArrow.setProgress(1f);
							startActivity(Utils.nextIntent(ACT_Deals.this,
									ACT_Splash.class));
							finish();
							break;
						case 1:
							goToView(Uri
									.parse("https://play.google.com/store/apps/details?id=app.desidime&hl=en"));
							break;
						default:
							break;
						}

					}
				});
	}

	private void goToView(Uri parse) {
		Intent nextIntent = new Intent(Intent.ACTION_VIEW);
		nextIntent.setData(parse);
		startActivity(nextIntent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {
		public MyPagerAdapter(
				android.support.v4.app.FragmentManager fragmentManager) {
			super(fragmentManager);
			dataList.clear();
			dataList.add("TOP");
			dataList.add("Popular");
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return dataList.get(position);
		}

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			if (position == 0) {
				return FRG_TopDeals.newInstance(position);
			} else {
				return FRG_PopularDeals.newInstance(position);

			}
		}
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			if (doubleBackToExitPressedOnce) {
				super.onBackPressed();
				return;
			}
			this.doubleBackToExitPressedOnce = true;
			Utils.showSimpleToast(ACT_Deals.this, "Press again to exit");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					doubleBackToExitPressedOnce = false;
				}
			}, 2000);
		}

	}
}
