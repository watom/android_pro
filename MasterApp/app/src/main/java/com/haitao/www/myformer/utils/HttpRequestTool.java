package com.haitao.www.myformer.utils;


import android.content.Context;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by Administrator on 2018/7/9 0009.
 */
public class HttpRequestTool {

    //从流中读取数据，将流转化为二进制数组
    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    // GET  定义一个获取网络图片数据的方法:
    public static byte[] getImage(Context context, String path) throws Exception {
        byte[] bt=null;
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);//连接5秒超时
        conn.setReadTimeout(5000);// 读取5秒超时
        String userAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C)";
        conn.setRequestProperty("User-Agent", userAgent);
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(inStream);
            bt = HttpRequestTool.read(inStream);
            inStream.close();
        } else {
            Toast.makeText(context, "请求失败", Toast.LENGTH_LONG).show();
        }
        return bt;
    }

    // GET 获取网页的html源代码
    public static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream in = conn.getInputStream();
            byte[] data = HttpRequestTool.read(in);
            String html = new String(data, "UTF-8");
            return html;
        } else {
            Log.e("getHtml","请求失败"+conn.getResponseCode());
        }
        return null;
    }

    //POST
    public static String post(String path, String number, String passwd) {
        String fetchDate = null;
        URL url;
        HttpURLConnection conn;
        String data = null;

        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式,请求超时信息
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            //设置运行输入,输出:
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //Post方式不能缓存,需手动设置为false
            conn.setUseCaches(false);
            //我们请求的数据:
            data = "passwd=" + URLEncoder.encode(passwd, "UTF-8") +
                    "&number=" + URLEncoder.encode(number, "UTF-8");
            //这里可以写一些请求头的东东...
            //获取输出流
            OutputStream out = null;
            out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();

            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                fetchDate = new String(message.toByteArray());
            }
            return fetchDate;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fetchDate;
    }
}
