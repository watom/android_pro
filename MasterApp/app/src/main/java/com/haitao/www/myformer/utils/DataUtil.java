package com.haitao.www.myformer.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class DataUtil {
    private static Gson gson=new Gson();
    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return boolean
     */
    public static boolean isEmpty(Object obj) {

        return obj == null || obj.equals("");
    }
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str) {

        return str == null || str.equalsIgnoreCase("null") || str.trim().length() == 0 || str.equals("");
    }

    /**
     * 判断数组是否为空
     *
     * @param list
     * @return boolean
     */
    public static boolean isEmpty(int[] list) {
        return list == null || list.length == 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param list
     * @return boolean
     */
    public static boolean isEmpty(String[] list) {
        return list == null || list.length == 0;
    }

    /**
     * 判断List是否为空
     *
     * @param list
     * @return boolean
     */
    public static <T extends Object> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    /**
     * Gson fromJson工具
     */
    public static  <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * Gson toJson工具
     */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T extends Object> List<T> getArrayToList(T[] array) {
        List<T> list = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    public static String[] getListToArray(List list) {
        String[] strs = (String[]) list.toArray(new String[0]);
        return strs;
    }

    /**
     * 32位MD5加密
     */
    public static String md5Decode32(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().trim();
    }

    /**
     * 交换数组中的任意两个元素
     */
    public static String[] swarp(String[] t, int i, int j) {
        String temp = t[i];
        t[i] = t[j];
        t[j] = temp;
        return t;
    }

    public static int[] swarp(int[] t, int i, int j) {
        int temp = t[i];
        t[i] = t[j];
        t[j] = temp;
        return t;
    }


    /**
     * 读取assets本地json
     *
     * @param fileName 如：XXX.json
     */
    public static String getJsonStr(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String getDateStr() {
        long currentTime = System.currentTimeMillis();
        String timeNow = new SimpleDateFormat("MM月dd日").format(currentTime);
        return timeNow;
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     */
    public static String shiftSpecialStr(String keyword) {
        if (!DataUtil.isEmpty(keyword)) {
            String[] fbsArr = { "$", "*", "+", "[", "]", "^", "{", "}"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "");
                }
            }
        }
        return keyword;
    }
}
