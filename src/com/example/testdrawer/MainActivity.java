package com.example.testdrawer;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testdrawer.dialogs.DYesNoDialog;
import com.example.testdrawer.fragments.DFragment;
import com.example.testdrawer.fragments.DPagerFragment;

public class MainActivity extends FragmentActivity implements
		LeftDrawerFragment.NavigationDrawerCallbacks, DIFController {

	private static final String TAG = "MainActivity";
	private int fragmentIdx = 0;
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private LeftDrawerFragment mLeftDrawerFragment_;
	private ImageView up;

	private DIFragment mCurrentContentFragment_;
	private int drawPostion;
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "***6.onDestroy(activity)");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "***4.onPause(activity)");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "***3.onResume(activity)");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "***2.onStart(activity)");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "***5.onStop(activity)");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "***1.onCreate(activity)");

		setContentView(R.layout.activity_main);

		mLeftDrawerFragment_ = (LeftDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mLeftDrawerFragment_.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		Log.i(TAG, "***1.onCreate(activity)-end");
	}

	@Override
	public void onFragmentResume(Fragment frg) {
		// this.mCurrentContentFragment_ = (DIFragment) frg;
		// invalidateOptionsMenu();
	}

	private String getFragmentIdxString() {
		++fragmentIdx;
		return "FRAGMENT:" + fragmentIdx;
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		Log.i(TAG, "***.444 Item Selected=" + position);
		drawPostion = position;
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
		Fragment fragment = null;
		if (position == 1) {
			// mCurrentContentFragment_ = null;
			// mCurrentContentFragment_ = new DPagerFragment();
			fragment = new DPagerFragment();

		} else {
			// mCurrentContentFragment_ = null;
			// mCurrentContentFragment_ = DFragment.newInstance(position + 1);
			fragment = DFragment.newInstance(position + 1);
		}
		// String frgTag = getFragmentIdxString();
		fragmentManager.beginTransaction()
				.replace(R.id.container, (Fragment) fragment)
				.addToBackStack(null).commit();

	}

	private void setActBarHomeBtn(boolean trueUp) {

		if (up == null) {
			int upId = Resources.getSystem().getIdentifier("up", "id",
					"android");
			up = (ImageView) this.findViewById(upId);
			if (up != null) {
				if (trueUp) {
					up.setPadding(30, 0, 30, 0);
					up.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Toast.makeText(MainActivity.this, "up clicked",
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		}
		up.setImageResource(trueUp ? R.drawable.title_back
				: R.drawable.ic_drawer);

	}

	// public void onSectionAttached(int number) {
	// switch (number) {
	// case 1:
	// mTitle = getString(R.string.title_section1);
	// break;
	// case 2:
	// mTitle = getString(R.string.title_section2);
	// break;
	// case 3:
	// mTitle = getString(R.string.title_section3);
	// break;
	// }
	// }

	// public void restoreActionBar() {
	// mCurrentContentFragment_.restoreActionBar();
	//
	// // ActionBar actionBar = getActionBar();
	// // if (drawPostion != 1)
	// // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	// // else
	// // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	// // actionBar.setDisplayShowTitleEnabled(true);
	// // actionBar.setTitle(mTitle);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i(TAG, "***999.onCreateOptionsMenu(Activity)");
		if (!mLeftDrawerFragment_.isDrawerOpen()) {
			// mCurrentContentFragment_.setupActionBar(menu);
			boolean isUp = ((DIFragment) getTopFragment()).setupActionBar(menu);
			setActBarHomeBtn(isUp);
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		DIFragment top = (DIFragment) getTopFragment();
		if (top != null && top.onOptionsItemSelected(id))
			return true;
		else
			return super.onOptionsItemSelected(item);
	}

	private Fragment getTopFragment() {
		Fragment f = getSupportFragmentManager().findFragmentById(
				R.id.container);
		if (f != null) {
			Log.e(TAG, "fragment=" + f.getClass().getSimpleName());
		} else {
			Log.e(TAG, "fragment=null");
		}
		return f;
	}

	@Override
	public void onBackPressed() {
		FragmentManager frgMgr = getSupportFragmentManager();
		Log.i(TAG,
				"***888.onBackPressed(Activity)="
						+ frgMgr.getBackStackEntryCount());
		if (frgMgr.getBackStackEntryCount() <= 1) {
			DYesNoDialog dlgDialog = DYesNoDialog.newInstance("Exit?",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();

						}

					});
			dlgDialog.show(frgMgr, "yes_no");
		} else {
			super.onBackPressed();
			if (getTopFragment() != null)
				invalidateOptionsMenu();

		}

		// getTopFragment();
	}

	@Override
	public void addFragment(Fragment frg) {
		int ok = getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, (Fragment) frg).addToBackStack(null)
				.commit();
		Log.i(TAG, "***7777.addFragment=" + ok);
		// TODO: don't affect action bar while add a new fragment on tab mode
		invalidateOptionsMenu();
	}

}
