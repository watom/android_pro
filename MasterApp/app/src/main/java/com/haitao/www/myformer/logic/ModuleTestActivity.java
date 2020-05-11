package com.haitao.www.myformer.logic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.logic.LogicTest.CountDownTimerActivity;
import com.haitao.www.myformer.logic.LogicTest.DateTestActivity;
import com.haitao.www.myformer.logic.LogicTest.GsonApplyActivity;
import com.haitao.www.myformer.logic.LogicTest.TimerTestActivity;
import com.haitao.www.myformer.logic.config.LocalPropertiesActivity;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class ModuleTestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview =  findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"时间段内测试","定时","倒计时","Local","Gson"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "时间段内测试":
                startActivity(new Intent(this, DateTestActivity.class));
                break;
            case "定时":
                startActivity(new Intent(this, TimerTestActivity.class));
                break;
            case "倒计时":
                startActivity(new Intent(this, CountDownTimerActivity.class));
                break;
            case "Local":
                startActivity(new Intent(this, LocalPropertiesActivity.class));
                break;
            case "Gson":
                startActivity(new Intent(this, GsonApplyActivity.class));
                break;
        }
    }
}
