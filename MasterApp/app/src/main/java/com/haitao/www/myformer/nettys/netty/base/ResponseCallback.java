package com.haitao.www.myformer.nettys.netty.base;

/**
 * Created by quliang on 18-10-13.
 */

public abstract class ResponseCallback {

    public void onStart() {
    }

    public void onEnd() {
    }

    public void onSuccess(String result, String token) {
    }

    public abstract void onFailure(String arg0, int error);

    public abstract void onSuccess(String result);

}
