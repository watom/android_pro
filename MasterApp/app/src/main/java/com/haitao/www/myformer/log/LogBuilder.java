package com.haitao.www.myformer.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

public class LogBuilder {

    public static void showToast(final Context context, final String text) {
        if (context == null) return;
        Handler mHandler = new Handler(context.getMainLooper()) {
        };

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                Log.i(LogIntent.TAG, "Toast  :  " + text);
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTime() {
        String date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date());
        return date;
    }

    public static File getCacheFile(String fileName) {
        File cacheFile = null;
        try {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File sdCardDir = Environment.getExternalStorageDirectory();
                File dir = new File(sdCardDir.getCanonicalPath() + "/" + LogIntent.DIR + "/");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                cacheFile = new File(dir, fileName);
                android.util.Log.i(LogIntent.TAG, "exists:" + cacheFile.exists() + ",dir:" + dir + ",file:" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            android.util.Log.e(LogIntent.TAG, "getCacheFileError:" + e.getMessage());
        }
        return cacheFile;
    }
}