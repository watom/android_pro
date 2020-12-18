package com.haitao.www.myformer.log;

public class LogIntent {
    public static final int MaxTimes = 3;
    public static final String DIR = "Recorder";
    public static final String LOG_PATH = "Recorder.txt";
    public static final String[] TAGS = new String[]{Log.TAG, "AndroidRuntime"};

    public static final String TAG = "LOG_PACKAGE_TAG";
    public static final String SERVICE_TAG = "service_tag";
    public static final String FILE_PATH = "file_path";
    public static final String START = "com.log.action.LOG_START";
    public static final String CLEAR = "com.log.action.LOG_CLEAR";
    public static final String STOP = "com.log.action.LOG_STOP";
    public static final String SERVICE = "com.log.action.GET_LOG_SERVICE";
    public static final String OPEN_LOG_STATE = "open_log_state";

}
