package com.haitao.www.myformer.utils;

import com.haitao.www.myformer.Config;

public class Log {
	/**
	 * log  info
	 * @param tag
	 * @param msg
	 */
	public static void i(String tag, Object msg) {
		if (Config.isDebug)
			android.util.Log.i(tag, "msg: " + msg);
	}
	/**
	 * log  debug
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, Object msg) {
		if (Config.isDebug)
			// Log.d(tag, msg);
			android.util.Log.d(tag, "msg: " + msg);
	}

	/**
	 * log  error
	 * @param tag
	 * @param msg
	 */
	public static void e(String tag, Object msg) {
		if (Config.isDebug)
			android.util.Log.e(tag, "msg: " + msg);
	}

	/**
	 * log  v
	 * @param tag
	 * @param msg
	 */
	public static void v(String tag, Object msg) {
		if (Config.isDebug)
			android.util.Log.v(tag, "msg: " + msg);
	}



}
