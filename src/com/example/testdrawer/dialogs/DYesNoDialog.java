package com.example.testdrawer.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DYesNoDialog extends DialogFragment {

	private DialogInterface.OnClickListener listener_;

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText);
	}

	public DYesNoDialog() {
		// Empty constructor required for DialogFragment
	}

	public static DYesNoDialog newInstance(String title,
			DialogInterface.OnClickListener listener) {
		DYesNoDialog frag = new DYesNoDialog();
		frag.listener_ = listener;
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		frag.setCancelable(false); /*
									 * cannot close diaglog while press back //
									 * button
									 */

		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String title = getArguments().getString("title");
		Dialog dlg = createYesNoDlg(title);
		// dlg.show();

		return dlg;
	}

	private Dialog createYesNoDlg(String title) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setMessage("Are you sure?");
		// alertDialogBuilder.setPositiveButton("OK",
		// new DialogInterface.OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // on success
		// dialog.dismiss();
		// }
		// });
		alertDialogBuilder.setPositiveButton("OK", listener_);
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
