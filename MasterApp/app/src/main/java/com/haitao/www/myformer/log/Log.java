package com.haitao.www.myformer.log;

import com.haitao.www.myformer.Config;

public class Log {
	public static final String TAG = "Recorder";

	/**
	 * log  info
	 */
	public static void i(String tag, String msg) {
		if (Config.isPrintLocalLog)
			android.util.Log.i(tag, "msg: " + msg);
	}
	/**
	 * log  debug
	 */
	public static void d(String tag, String msg) {
		if (Config.isPrintLocalLog)
			android.util.Log.d(tag, "msg: " + msg);
	}

	/**
	 * log  error
	 */
	public static void e(String tag, String msg) {
		if (Config.isPrintLocalLog)
			android.util.Log.e(tag, "msg: " + msg);
	}

	/**
	 * log  v
	 */
	public static void v(String tag, String msg) {
		if (Config.isPrintLocalLog)
			android.util.Log.v(tag, "msg: " + msg);
	}
}
