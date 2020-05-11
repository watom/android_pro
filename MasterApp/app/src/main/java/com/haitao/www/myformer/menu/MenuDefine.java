package com.haitao.www.myformer.menu;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.listview.XListView.ListViewActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21 0021.
 */

public final class MenuDefine {
    public static final String ListView = "ListView";
    public static final String Dialog = "Dialog";
    public static final String Fragment = "Fragment";
    public static final String Animation = "AnimationActivity";
    public static final String 网络请求 = "NettyWork";
    public static final String 数据库 = "DataBase";
    public static final String UI = "DataBase";
    public static final String Data = "DataBase";
    public static final String 设计模式 = "DataBase";
    public static final String 架构设计 = "DataBase";
    public static final String 第三方jar包 = "DataBase";
    public static final String 模块测试 = "DataBase";

//    public static Map<Integer,Object> demoMain(){
//        Map<Integer, Object> map = new HashMap<>();
//        map.put(1,)
//        return map;
//    }

    public static Map<String, MenuImp> init() {
        Map<String, MenuImp> map = new HashMap<>();
        map.put(ListView, new MenuImp(ListViewActivity.class, R.mipmap.ic_launcher));
        map.put(Dialog, new MenuImp(ListViewActivity.class, R.mipmap.ic_launcher));
        map.put(Fragment, new MenuImp(ListViewActivity.class, R.mipmap.ic_launcher));
        map.put(Animation, new MenuImp(ListViewActivity.class, R.mipmap.ic_launcher));
        map.put(网络请求, new MenuImp(ListViewActivity.class, R.mipmap.ic_launcher));
        map.put(数据库, new MenuImp(ListViewActivity.class, R.mipmap.ic_launcher));
        return map;
    }

}
