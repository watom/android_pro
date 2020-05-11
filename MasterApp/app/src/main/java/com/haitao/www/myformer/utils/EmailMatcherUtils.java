package com.haitao.www.myformer.utils;

import android.text.TextUtils;
import android.util.Log;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱验证正则表达式
 */
public class EmailMatcherUtils {
    public static String reg="[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";
    private static String TAG="EmailMatcherUtils";
    //TODO parse query result of emails
    public static String startParseElectronicInvoiceEmail(String invoiceMsg) {
        Matcher matcher = getMatcher(invoiceMsg);
        String temp="";
        while(matcher.find()){
            if (TextUtils.isEmpty(temp)){
                temp = matcher.group(0);
            }
            Log.e(TAG,"find email:"+matcher.group(0));
        }
        return temp;
    }

    /**
     * 匹配是否是邮箱
     * @param emailStr 邮箱
     * @return
     */
    public static boolean isMatchEmailReg(String emailStr){
        Matcher matcher = getMatcher(emailStr);
        return matcher.matches();
    }

    private static Matcher getMatcher(String str){
        Pattern pattern= Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

}

