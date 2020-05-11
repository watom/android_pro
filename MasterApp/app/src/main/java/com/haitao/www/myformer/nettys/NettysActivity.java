package com.haitao.www.myformer.nettys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.nettys.baseNetInfo.BaseNetInfoActivity;
import com.haitao.www.myformer.nettys.comnettys.HttpURLConnectionActivity;
import com.haitao.www.myformer.nettys.netprogramme.NetProgrammeActivity;
import com.haitao.www.myformer.nettys.netty.MyNettyActivity;
import com.haitao.www.myformer.nettys.okhttp.OkHttpTestActivity;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class NettysActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview =  findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"网络编程","HttpURLConnection","OKhttp","封装网络框架","本机网络基本信息"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s) {
            case "网络编程":
                startActivity(new Intent(this, NetProgrammeActivity.class));
                break;
            case "HttpURLConnection":
                startActivity(new Intent(this, HttpURLConnectionActivity.class));
                break;
            case "OKhttp":
                startActivity(new Intent(this, OkHttpTestActivity.class));
                break;
            case "封装网络框架":
                startActivity(new Intent(this, MyNettyActivity.class));
                break;
            case "本机网络基本信息":
                startActivity(new Intent(this, BaseNetInfoActivity.class));
                break;
        }
    }
}
