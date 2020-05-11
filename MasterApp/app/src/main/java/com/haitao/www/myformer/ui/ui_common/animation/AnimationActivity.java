package com.haitao.www.myformer.ui.ui_common.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;

/**
 * Created by Administrator on 2017/11/21 0021.
 */

public class AnimationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = (ListView) findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"Material","风速动画","漂浮动画"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s) {
            case "Material":
                startActivity(new Intent(this, MaterialThemeActivity.class));
                break;
            case "风速动画":
                startActivity(new Intent(this, WindViewActivity.class));
                break;
            case "漂浮动画":
                startActivity(new Intent(this, MaterialThemeActivity.class));
                break;
        }
    }
}
