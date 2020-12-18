package com.haitao.www.myformer.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * 通过Receiver启动或停止该Service
 *
 * @author root
 */
public class LogService extends Service {

    private File file;
    private boolean running;
    private List<Process> processes = new ArrayList<Process>();

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.i(LogIntent.TAG, "logService   onCreate");
        registerReceiver(new StopReceiver(), new IntentFilter(LogIntent.STOP));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (file == null) {
            if (intent == null) {
                return super.onStartCommand(intent, Service.START_FLAG_REDELIVERY, startId);
            }
            file = (File) intent.getExtras().get(LogIntent.FILE_PATH);
        }
        for (String tag : LogIntent.TAGS) {
            addTag(tag);
        }
        running = true;
        return Service.START_FLAG_REDELIVERY;
    }

    public void addTag(final String tag) {
        new Thread() {
            public void run() {
                android.util.Log.i(LogIntent.TAG, "logService   addTag   " + tag);
                BufferedReader reader = null;
                PrintWriter pw = null;
                try {
                    Process mLogcatProc = null;
                    mLogcatProc = Runtime.getRuntime().exec(
                            new String[]{"logcat", tag + ":*   *:S"});
                    processes.add(mLogcatProc);
                    reader = new BufferedReader(new InputStreamReader(
                            mLogcatProc.getInputStream()));
                    pw = new PrintWriter(new FileOutputStream(file, true));
                    String line;
                    while (running && (line = reader.readLine()) != null) {
                        android.util.Log.d(LogIntent.TAG, line + "\n");
                        pw.println(LogBuilder.getTime() + "   " + line);
                        pw.flush();
                    }
                    pw.close();
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        for (Process p : processes) {
            if (p != null) {
                p.destroy();
            }
        }
        running = false;
        super.onDestroy();
    }

    class StopReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(LogIntent.STOP)) {
                Log.i(LogIntent.TAG, intent.getAction());
                stopSelf();
            }
        }
    }
}