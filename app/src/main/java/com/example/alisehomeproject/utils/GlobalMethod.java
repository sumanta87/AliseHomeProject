package com.example.alisehomeproject.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.alisehomeproject.R;

public class GlobalMethod {

	public static ProgressDialog pDialog;
	public static ProgressDialog progressDialog;

	public static void DialogEnd() {
		if (pDialog != null) {
			if (pDialog.isShowing()) {

				//get the Context object that was used to great the dialog
				Context context = ((ContextWrapper) pDialog.getContext()).getBaseContext();

				// if the Context used here was an activity AND it hasn't been finished or destroyed
				// then dismiss it
				if (context instanceof Activity) {

					// Api >=17
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
						if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
							dismissWithTryCatch(pDialog);
						}
					} else {

						// Api < 17. Unfortunately cannot check for isDestroyed()
						if (!((Activity) context).isFinishing()) {
							dismissWithTryCatch(pDialog);
						}
					}
				} else
					// if the Context used wasn't an Activity, then dismiss it too
					dismissWithTryCatch(pDialog);
			}
			pDialog = null;
		}
	}
	public static boolean checkNetworkState(Context context)
	{
		ConnectivityManager conMgr =  (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
		if (netInfo == null){
			return false;

		}else{
			return true;
		}
	}

	public static void dismissWithTryCatch(Dialog dialog) {
		try {
			dialog.dismiss();
		} catch (final IllegalArgumentException e) {
			// Do nothing.
		} catch (final Exception e) {
			// Do nothing.
		} finally {
			dialog = null;
		}
	}




	public static void getAlertErrorCode(final Context con, String title, String msg) {
		LayoutInflater factory = LayoutInflater.from(con);
		final View view = factory.inflate(R.layout.custom_dialog_errorcode, null);
		final androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(con).create();
		dialog.setView(view);
		TextView txt_Tile = (TextView) view.findViewById(R.id.txt_Title);
		TextView txt_link = (TextView) view.findViewById(R.id.txt_Link);
		txt_Tile.setText("Oops !");
		view.findViewById(R.id.btn_Ok).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}












	public static void DialogShow_Spin(Context context, String message) {
		pDialog = new ProgressDialog(context);
		pDialog.setMessage(message);
		pDialog.setIndeterminate(false);
		pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pDialog.setCancelable(false);
		pDialog.show();

	}



	public static void showSettingsAlert(Context context) {

		android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		// Setting Dialog Title
		alertDialog.setTitle("Network settings");
		alertDialog.setMessage("Please connect to mobile data or wifi.");

		// Setting Dialog Message


		// On pressing Settings button
		alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				dialog.dismiss();


				Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
				context.startActivity(intent);
			}
		});
		alertDialog.setCancelable(false);
		alertDialog.show();
	}

	public static void ProgressEnd() {

		////////////
		if (progressDialog.isShowing()&&progressDialog != null) {
			progressDialog.dismiss();

		}

	}

}
