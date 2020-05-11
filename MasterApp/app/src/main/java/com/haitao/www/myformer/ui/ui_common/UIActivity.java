package com.haitao.www.myformer.ui.ui_common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.nettys.netty.base.TestBaseAcitvity;
import com.haitao.www.myformer.ui.ui_common.ModuleTest.UIModuleTestActivity;
import com.haitao.www.myformer.ui.ui_common.ModuleTest.measureScreen.MeasureScreenActivity;
import com.haitao.www.myformer.ui.ui_common.animation.AnimationActivity;
import com.haitao.www.myformer.ui.ui_common.bitmap.BitmapActivity;
import com.haitao.www.myformer.ui.ui_common.component.ComponentActivity;
import com.haitao.www.myformer.ui.ui_common.dialog.DialogActivity;
import com.haitao.www.myformer.ui.ui_common.fragment.FragmentCategory;
import com.haitao.www.myformer.ui.ui_common.listview.ListViewCategory;
import com.haitao.www.myformer.ui.ui_common.tabActivity.MyTabActivity;
import com.haitao.www.myformer.ui.ui_common.viewpager.ViewPagerActivity;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class UIActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = (ListView) findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"ListView", "Dialog", "Fragment","ViewPager", "Animation","Component","分页","侧边栏","模块测试","测量屏幕","Bitmap","测试基类Activity"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "ListView":
                startActivity(new Intent(this, ListViewCategory.class));
                break;
            case "Dialog":
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case "Fragment":
                startActivity(new Intent(this, FragmentCategory.class));
                break;
            case "ViewPager":
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case "Animation":
                startActivity(new Intent(this, AnimationActivity.class));
                break;
            case "Component":
                startActivity(new Intent(this, ComponentActivity.class));
                break;
            case "分页":
                startActivity(new Intent(this, MyTabActivity.class));
                break;
            case "侧边栏":
                break;
            case "模块测试":
                startActivity(new Intent(this, UIModuleTestActivity.class));
                break;
            case "测量屏幕":
                startActivity(new Intent(this, MeasureScreenActivity.class));
                break;
            case "Bitmap":
                startActivity(new Intent(this, BitmapActivity.class));
                break;
            case "测试基类Activity":
                startActivity(new Intent(this, TestBaseAcitvity.class));
                break;
        }
    }
}
