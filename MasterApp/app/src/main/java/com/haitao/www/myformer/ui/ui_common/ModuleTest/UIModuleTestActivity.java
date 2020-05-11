package com.haitao.www.myformer.ui.ui_common.ModuleTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.ModuleTest.excellayout.ExcelTablayout;
import com.haitao.www.myformer.ui.ui_common.ModuleTest.ratingbarview.RatingBarActivity;
import com.haitao.www.myformer.ui.ui_common.ModuleTest.timeLine.TimeLineActivity;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class UIModuleTestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview =  findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"表格","时间轴","评价条"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "表格":
                startActivity(new Intent(this, ExcelTablayout.class));
                break;
            case "时间轴":
                startActivity(new Intent(this, TimeLineActivity.class));
                break;
            case "评价条":
                startActivity(new Intent(this, RatingBarActivity.class));
                break;
            case "网格排序":
                startActivity(new Intent(this, RatingBarActivity.class));
                break;
        }
    }
}
