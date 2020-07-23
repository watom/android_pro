package com.haitao.www.myformer.data;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;

/***********************************************
 *@Copyright: 2017(C), 国电通__期
 *@Author&Email: wanghaitao 1164973719@qq.com
 *@Function: 1、
 *@Description: 1、       
 *@CreatedDate: 2017/11/19 18:12
 *@UpDate: 1、
 ***********************************************/

public class DataBaseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = (ListView) findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"SQLite数据库","Json数据","数据缓存","数据加/解密","数据压缩"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s) {
            case "SQLite数据库":
//                startActivity(new Intent(this, UIActivity.class));
                break;
            case "Json数据":
                break;
            case "数据加/解密":
                break;
            case "数据压缩":
                break;
            case "数据缓存":
                break;
        }
    }
}
