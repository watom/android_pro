package com.haitao.www.myformer.utils.nettools;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/1 0001.
 */

public class OkHttp {
//    OkHttpClient client = new OkHttpClient();
//    String run(String url) throws IOException {
//        Request request = new Request.Builder().url("https://www.baidu.com/").build();
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
//            return response.body().string();
//        } else {
//            throw new IOException("Unexpected code " + response);
//        }
//    }
//
//    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//    OkHttpClient client = new OkHttpClient();
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
//            return response.body().string();
//        } else {
//            throw new IOException("Unexpected code " + response);
//        }
//    }
}
