package com.haitao.www.myformer.menu;

import android.app.Activity;

/**
 * Created by Administrator on 2017/11/21 0021.
 */

public final class MenuImp {
    public Class<? extends Activity> mClass;
    public int menuIcon;
    public boolean needLogin;

    public MenuImp(Class<? extends Activity> mClass, int menuIcon) {
        this.mClass = mClass;
        this.menuIcon = menuIcon;
        this.needLogin=true;
    }

    public MenuImp(Class<? extends Activity> mClass, int menuIcon, boolean needLogin) {
        this.mClass = mClass;
        this.menuIcon = menuIcon;
        this.needLogin = needLogin;
    }

    @Override
    public String toString() {
        return mClass.getClass().getName();
    }
}
