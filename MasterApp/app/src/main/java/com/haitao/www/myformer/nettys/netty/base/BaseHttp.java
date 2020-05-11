package com.haitao.www.myformer.nettys.netty.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * 请求方式
 */
public interface BaseHttp {

    void get(String url, ResponseCallback responseCallback);

    void post(String url, String requestBody, String token, ResponseCallback responseCallback);

    void post(String url, Map<String, String> params, String token, ResponseCallback responseCallback);

    void post(String url, Map<String, String> params, String token, boolean isformdata, ResponseCallback responseCallback);

    void post(String url, Map<String, String> params, String token, ArrayList<File> files, final ResponseCallback responseCallback);
}
