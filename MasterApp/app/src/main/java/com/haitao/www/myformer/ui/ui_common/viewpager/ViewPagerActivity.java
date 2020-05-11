package com.haitao.www.myformer.ui.ui_common.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.viewpager.adsalternate.AdsAlternateActivity01;
import com.haitao.www.myformer.ui.ui_common.viewpager.adsalternate.AdsAlternateActivity02;
import com.haitao.www.myformer.ui.ui_common.viewpager.baseviewpager.BaseViewPagerActivity;
import com.haitao.www.myformer.ui.ui_common.viewpager.baseviewpager.ViewPagerTitleStripActivity;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class ViewPagerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = (ListView) findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"简单的ViewPager","简单的ViewPager带标题栏","广告轮播-1","广告轮播-2"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "简单的ViewPager":
                startActivity(new Intent(this, BaseViewPagerActivity.class));
                break;
            case "简单的ViewPager带标题栏":
                startActivity(new Intent(this, ViewPagerTitleStripActivity.class));
                break;
            case "广告轮播-1":
                startActivity(new Intent(this, AdsAlternateActivity01.class));
                break;
            case "广告轮播-2":
                startActivity(new Intent(this, AdsAlternateActivity02.class));
                break;
        }
    }
}
