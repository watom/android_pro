package com.haitao.www.myformer.ui.ui_common.tabActivity.tabActivityTabHost;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.dialog.alert_dialog.AlertDialogDemo;
import com.haitao.www.myformer.ui.ui_common.dialog.popupwindow_dialog.PopupWindowDialog;

/**
 * 谷歌过时的API，不建议使用。
 */

public class TabActivityTabHost extends TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int whichType = getIntent().getIntExtra("whichType", 0);
        switch (whichType){
            case 0:
                firstWay();
                break;
            case 1:
                secondWay();
                break;
            case 2:
                thirdWay();
                break;
        }
    }

    /**
     * 切换的是View布局文件
     * setContent()设置每个标签页面的内容：布局（布局的名称id）、其他的Activity（用Intent跳转）
     */
    private void firstWay() {
        TabHost tabHost = this.getTabHost();//获得当前TabActivity的TabHost
        LayoutInflater.from(this).inflate(R.layout.tab_cut_view, tabHost.getTabContentView(), true);
        tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("科学").setContent(R.id.label_01));
        tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("推荐").setContent(R.id.label_02));
        tabHost.addTab(tabHost.newTabSpec("Tab3").setIndicator("新闻").setContent(R.id.label_03));
        tabHost.addTab(tabHost.newTabSpec("Tab4").setIndicator("直播").setContent(R.id.label_04));
        tabHost.setCurrentTabByTag("Tab2");
    }

    /**
     * 切换的是Activity
     */
    private void secondWay() {
        TabHost tabHost = this.getTabHost();//获得当前TabActivity的TabHost
        tabHost.addTab(tabHost.newTabSpec("gushi").setIndicator("故事",getResources().getDrawable(R.drawable.selector_tabmenu_browse)).setContent(new Intent().setClass(this, AlertDialogDemo.class)));
        tabHost.addTab(tabHost.newTabSpec("lishi").setIndicator("历史",getResources().getDrawable(R.drawable.selector_tabmenu_mine)).setContent(new Intent().setClass(this, PopupWindowDialog.class)));
        tabHost.setCurrentTab(1);
    }

    /**
     * 实现底部切换
     */
    private void thirdWay() {

    }
}
