package com.haitao.www.myformer.nettys.network;

public class RequestModeFactory extends AbstractFactory {
    @Override
    public RequestMode getRequestMode(String specificMode) {
        if (specificMode == null) {
            return null;
        }
        if (specificMode.equalsIgnoreCase("OkHttp")) {
            return new OkHttpRequest();
        } else if (specificMode.equalsIgnoreCase("XUtils")) {
            return new XUtilsRequest();
        }
        return null;
    }
}
