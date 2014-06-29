package com.example.testdrawer.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.example.testdrawer.R;

public class EditNameDialog extends DialogFragment implements
		OnEditorActionListener {

	private static final String TAG = "EditNameDialog";
	private EditText mEditText;

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText);
	}

	public EditNameDialog() {
		// Empty constructor required for DialogFragment
	}

	public static EditNameDialog newInstance(String title) {
		EditNameDialog frag = new EditNameDialog();
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		// frag.setCancelable(false); /*cannot close diaglog while press back
		// button*/

		return frag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_edit_name, container);
		mEditText = (EditText) view.findViewById(R.id.txt_your_name);
		String title = getArguments().getString("title", "Enter Name");
		getDialog().setTitle(title);
		mEditText.setOnEditorActionListener(this);
		// Show soft keyboard automatically
		mEditText.requestFocus();
		getDialog().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

		getDialog().setCanceledOnTouchOutside(false);
		// setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo);
		return view;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_NONE != actionId) {
			// Return input text to activity
			// EditNameDialogListener listener = (EditNameDialogListener)
			// getActivity();
			EditNameDialogListener listener = (EditNameDialogListener) getTargetFragment();
			listener.onFinishEditDialog(mEditText.getText().toString());
			showAlertDialog();
			dismiss();
			return true;
		}
		return false;
	}

	private void showAlertDialog() {

		FragmentManager fm = getActivity().getSupportFragmentManager();
		// Log.i(TAG,
		// "***666.BackStack(dlg) before=" + fm.getBackStackEntryCount());

		DProgressDialog progressDialog = DProgressDialog
				.newInstance("Some Title");
		progressDialog.setTargetFragment(this, 123);

		progressDialog.show(fm, "fragment_alert_dlg");
		// Log.i(TAG, "***777.BackStack(dlg) after=" +
		// fm.getBackStackEntryCount());
	}

}
