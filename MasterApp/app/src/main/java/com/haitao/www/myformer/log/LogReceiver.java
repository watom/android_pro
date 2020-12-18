package com.haitao.www.myformer.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LogReceiver extends BroadcastReceiver {

    private Intent service;
    private Context mContext;
    private File file = LogBuilder.getCacheFile(LogIntent.LOG_PATH);
    private List<Intent> services = new ArrayList<Intent>();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LogIntent.TAG, intent.getAction());
        mContext = context;
        if (intent.getAction().equals(LogIntent.START)) {
            startLogService();
        } else if (intent.getAction().equals(LogIntent.CLEAR)) {
            clearLog();
        }

    }

    private void startLogService() {
        android.util.Log.i(LogIntent.TAG, "startLogService");
        service = new Intent();
        services.add(service);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        service.setAction(LogIntent.SERVICE);
        service.putExtra(LogIntent.FILE_PATH, file);

        mContext.startService(service);
    }

    private void clearLog() {
        if (file.exists()) {
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream(file, false));
                pw.println();
                pw.println("================" + LogBuilder.getTime() + " start get log" + "================");
                pw.println();
                pw.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (pw != null)
                    pw.close();
            }
        }
    }
}
