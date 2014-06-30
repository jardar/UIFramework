package com.example.testdrawer.fragments;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testdrawer.DIFragment;
import com.example.testdrawer.R;

public class DPagerFragment extends Fragment implements ActionBar.TabListener,
		DIFragment {
	private static final String TAG = "DPagerFragment";

	AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	ViewPager mViewPager;
	int mCurrentTabItem;

	public DPagerFragment() {
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "***5.onStart");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "***6.onResume");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "***8.onStop");
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.i(TAG, "***9.onDestroyView");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "***10.onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(TAG, "***11.onDetach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "***2.onCreate");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "***4.onActivityCreated");
		// Indicate that this fragment would like to influence the set of
		// actions in the action bar.
		setHasOptionsMenu(false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "***3.onCreateView");
		View rootView = inflater.inflate(R.layout.frag_pager, container, false);

		// set up page viewer
		final ActionBar actionBar = getActivity().getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getActivity()
				.getSupportFragmentManager());
		mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between different app sections,
						// select
						// the corresponding tab.
						// We can also use ActionBar.Tab#select() to do this
						// if
						// we have a reference to the
						// Tab.
						mCurrentTabItem = position;
						actionBar.setSelectedNavigationItem(position);
					}
				});
		actionBar.removeAllTabs();
		for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title
			// defined by
			// the adapter.
			// Also specify this Activity object, which implements the
			// TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
					.setIcon(R.drawable.music_albums).setTabListener(this));
		}
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(TAG, "***1.onAttach");
	}

	@Override
	public void onPause() {
		Log.i(TAG, "***7.onPause");
		setHasOptionsMenu(false);
		super.onPause();
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mCurrentTabItem = tab.getPosition();
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}


	@Override
	public boolean onOptionsItemSelected(int menuItemId) {
		if (menuItemId == R.id.action_example) {
			Fragment frg = getCurrentFragment();
			if (frg != null)
				if (((DIFragment) frg).onOptionsItemSelected(menuItemId))
					return true;
		} else if (menuItemId == R.id.action_settings) {
			Toast.makeText(getActivity(), "pager setting action.",
					Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	private Fragment getCurrentFragment() {
		Fragment fragment = (Fragment) getActivity()
				.getSupportFragmentManager().findFragmentByTag(
						"android:switcher:" + R.id.pager + ":"
								+ mCurrentTabItem);

		return fragment;

	}

	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		public AppSectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int i) {
			switch (i) {
			case 0:

				return DMusicFragment.newInstance(100);

			default:

				return DMusicFragment.newInstance(i + 1);
			}
		}

		@Override
		public int getCount() {
			return 6;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Section " + (position + 1);
		}
	}

	@Override
	public boolean setupActionBar(Menu menu) {
		getActivity().getMenuInflater().inflate(R.menu.pager, menu);
		ActionBar actionBar = getActivity().getActionBar();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Page Fragment");
		return false;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		Log.i(TAG, "***999.onCreateOptionsMenu(DPager)");

		super.onCreateOptionsMenu(menu, inflater);
	}
}
