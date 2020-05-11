package com.haitao.www.myformer.ui.ui_common.tabActivity.activityGroupGridView;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.dialog.alert_dialog.AlertDialogDemo;
import com.haitao.www.myformer.ui.ui_common.dialog.popupwindow_dialog.PopupWindowDialog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/25 0025.
 * Tab头部采用GridView来实现，子页面采用LinearLayout容器来实现。
 * 然后把每个子Activity作为一个View视图，装载到LinearLayout容器里面。
 * http://blog.csdn.net/yangy_/article/details/6607815
 * https://www.cnblogs.com/cpcpc/archive/2011/06/23/2123012.html
 * http://blog.csdn.net/lyl_studio/article/details/7830487
 * http://blog.csdn.net/hello2me/article/details/8757095
 */

public class ActivityGroupGridView extends ActivityGroup {
    int which;
    LinearLayout tabcontent;
    GridView tablabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitygroup_gridview_layout);
        tablabel = findViewById(R.id.tab_gridview);
        tabcontent = findViewById(R.id.tab_linearlayout);
        ArrayList<HashMap<String, Object>> tabWidgetData = new ArrayList<>();
        String[] label = {"首页", "通信", "探索", "我的"};
        int[] icon = {R.mipmap.sy_have2x, R.mipmap.yd_hove2x, R.mipmap.kf_have2x, R.mipmap.wd_have2x};
        for (int i = 0; i < label.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("tabWidgetLabel", label[i]);
            map.put("tabWidgetIcon", icon[i]);
            tabWidgetData.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, tabWidgetData,
                R.layout.tab_indicator_view, new String[]{"tabWidgetLabel", "tabWidgetIcon"},
                new int[]{R.id.tab_label, R.id.tab_icon});
        tablabel.setAdapter(adapter);
        tablabel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                which = position;
                switchView(which);
            }
        });
    }

    private void switchView(int which) {
        tabcontent.removeAllViews();
        Intent intent = null;
        String tag="";
        switch (which) {
            case 0:
                intent = new Intent(this, AlertDialogDemo.class);
                tag = "tabActivity01";
                break;
            case 1:
                intent = new Intent(this, PopupWindowDialog.class);
                tag = "tabActivity02";
                break;
            case 2:
                intent = new Intent(this, AlertDialogDemo.class);
                tag = "tabActivity03";
                break;
            case 3:
                intent = new Intent(this, PopupWindowDialog.class);
                tag = "tabActivity04";
                break;
        }
        Window subActivity = getLocalActivityManager().startActivity(tag, intent);
        tabcontent.addView(subActivity.getDecorView(),ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
