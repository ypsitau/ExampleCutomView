package io.github.ypsitau.examplecutomview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	static Context contextCur;
	static EditText editText_log;
	static void init(Context contextCur, EditText editText_log) {
		Util.contextCur = contextCur;
		Util.editText_log = editText_log;
		if (editText_log != null) {
			editText_log.setBackgroundColor(Color.WHITE);
			editText_log.setFocusable(false);
			editText_log.setTextIsSelectable(true);
			editText_log.setGravity(Gravity.TOP);
			editText_log.setTextSize(10); // set font size 10sp
			editText_log.setTypeface(Typeface.MONOSPACE);
		}
	}
	static void Printf(String format, Object... args) {
		final DateFormat df = new SimpleDateFormat("HH:mm:ss.SS ");
		final Date date = new Date(System.currentTimeMillis());
		String msg = df.format(date) + String.format(format, args);
		if (contextCur == null) {
			Log.i("", msg);
		} else {
			Log.i(contextCur.getString(R.string.app_name), msg);
		}
		if (editText_log != null) {
			editText_log.append(msg);
			editText_log.setSelection(editText_log.getText().length());
		}
	}
}
