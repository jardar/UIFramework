package com.example.testdrawer.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdrawer.DIFController;
import com.example.testdrawer.DIFragment;
import com.example.testdrawer.R;
import com.example.testdrawer.dialogs.DProgressDialog;
import com.example.testdrawer.dialogs.EditNameDialog;
import com.example.testdrawer.dialogs.EditNameDialog.EditNameDialogListener;

public class DMusicFragment extends Fragment implements DIFragment,
		EditNameDialogListener {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String TAG = "DMusicFragment";
	private TextView textView;
	private Button btn;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static DMusicFragment newInstance(int sectionNumber) {
		DMusicFragment fragment = new DMusicFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public DMusicFragment() {
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
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "***7.onPause");
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
		setHasOptionsMenu(false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "***3.onCreateView");
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText(Integer.toString(getArguments().getInt(
				ARG_SECTION_NUMBER)));

		btn = (Button) rootView.findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((DIFController) getActivity()).addFragment(DFragment
						.newInstance(77777));

			}
		});
		return rootView;
	}

	private void showEditDialog() {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		EditNameDialog editNameDialog = EditNameDialog
				.newInstance("Some Title");
		editNameDialog.setTargetFragment(this, 123);

		editNameDialog.show(fm, "fragment_edit_name");
	}

	private void showAlertDialog() {

		FragmentManager fm = getActivity().getSupportFragmentManager();
		DProgressDialog editNameDialog = DProgressDialog
				.newInstance("Some Title");
		editNameDialog.setTargetFragment(this, 123);

		editNameDialog.show(fm, "fragment_alert_dlg");
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(TAG, "***1.onAttach");
	}


	@Override
	public void onFinishEditDialog(String inputText) {
		// TODO Auto-generated method stub
		textView.setText(inputText);
		showAlertDialog();
	}

	@Override
	public boolean onOptionsItemSelected(int menuItemId) {
		// TODO Auto-generated method stub
		if (menuItemId == R.id.action_example) {
			showEditDialog();
			return true;
		} else if (menuItemId == R.id.action_settings) {
			Toast.makeText(getActivity(), "fragment setting action.",
					Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	@Override
	public boolean setupActionBar(Menu menu) {
		getActivity().getMenuInflater().inflate(R.menu.main, menu);
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Music " + textView.getText().toString());
		return false;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		Log.i(TAG, "***999.onCreateOptionsMenu(DMusicFragment)");

		super.onCreateOptionsMenu(menu, inflater);
	}
}
