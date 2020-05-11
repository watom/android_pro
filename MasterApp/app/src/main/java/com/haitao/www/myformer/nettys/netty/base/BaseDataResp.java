package com.haitao.www.myformer.nettys.netty.base;

/**
 *
 */
public class BaseDataResp {
    public static final int RESCODE_EXCEPTION = 1001;//请求抛出异常
    public static final int RESCODE_NOLOGIN = 1002;	//未登陆状态
    public static final int RESCODE_NOAUTH = 1003;//无操作权限
    public static final int RESCODE_LOGINEXPIRE = 1004;//登录过期
    public static final int JWT_ERRCODE_EXPIRE = 1005;//Token过期
    public static final int JWT_ERRCODE_FAIL = 1006;//验证不通过

    private String code; //返回的状态码 200:成功 400：奔溃
    private String message;  //短消息内容
    private String data;  //返回的数据包

    public BaseDataResp() {
    }

    public BaseDataResp(String data, String message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
