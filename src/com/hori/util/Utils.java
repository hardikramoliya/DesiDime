package com.hori.util;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.widget.Toast;

public class Utils {

	public static String INTERNET_NOT_AVAILABLE = "Internet not available";

	public static boolean isOnline(Context context) {

		try {
			if (context == null)
				return false;

			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			// mobile
			State mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
					.getState();

			// wifi
			State wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
					.getState();
			if (mobile == NetworkInfo.State.CONNECTED
					|| mobile == NetworkInfo.State.CONNECTING) {
				// mobile
				return true;
			} else if (wifi == NetworkInfo.State.CONNECTED
					|| wifi == NetworkInfo.State.CONNECTING) {
				// wifi
				return true;
			}
		} catch (Exception e) {
			// Log.error("Utils :: isonline() ", e);
			return false;
		}
		return false;
	}

	public static void createDirectoryAndSaveFileCerti(Bitmap imageToSave,
			String fileName) {

		File file = new File(fileName);
		if (file.exists())
			file.delete();
		try {
			FileOutputStream out = new FileOutputStream(file);
			imageToSave.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bitmap rotateBitmap(Bitmap b, float degrees) { // Rotate Image
		Matrix m = new Matrix();
		if (degrees != 0) {
			// clockwise
			m.postRotate(degrees, (float) b.getWidth() / 2,
					(float) b.getHeight() / 2);
		}
		try {
			Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
					b.getHeight(), m, true);
			if (b != b2) {
				b.recycle();
				b = b2;
			}
		} catch (OutOfMemoryError ex) {
			// We have no memory to rotate. Return the original bitmap.
		}
		return b;
	}

	public static Intent nextIntent(Context startIntent, Class<?> endIntent) {
		Intent intent = new Intent(startIntent, endIntent);
		return intent;
	}

	public static void showSimpleToast(Context context, final String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
