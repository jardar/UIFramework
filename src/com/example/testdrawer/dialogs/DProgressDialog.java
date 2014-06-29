package com.example.testdrawer.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class DProgressDialog extends DialogFragment {

	private EditText mEditText;

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText);
	}

	public DProgressDialog() {
		// Empty constructor required for DialogFragment
	}

	public static DProgressDialog newInstance(String title) {
		DProgressDialog frag = new DProgressDialog();
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		// frag.setCancelable(false); /*cannot close diaglog while press back
		// button*/

		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String title = getArguments().getString("title");
		ProgressDialog dlg = new ProgressDialog(getActivity());

		dlg.setTitle("Downloading Image ...");
		dlg.setMessage("Download in progress ...");
		dlg.setCancelable(true);

		// dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// dlg.setProgress(0);
		// dlg.setMax(20);
		dlg.show();

		return dlg;
	}

	private Dialog createYesNoDlg(String title) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setMessage("Are you sure?");
		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// on success
						dialog.dismiss();
					}
				});
		alertDialogBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		return alertDialogBuilder.create();
	}

}
