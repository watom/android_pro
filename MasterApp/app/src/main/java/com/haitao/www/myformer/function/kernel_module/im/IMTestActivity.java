package com.haitao.www.myformer.function.kernel_module.im;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.function.kernel_module.im.socket.SocketActivity;
import com.haitao.www.myformer.function.kernel_module.im.xmpp.XMPPActivity;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class IMTestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview =  findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"xmpp","socket"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "xmpp":
                startActivity(new Intent(this, XMPPActivity.class));
                break;
            case "socket":
                startActivity(new Intent(this, SocketActivity.class));
                break;
        }
    }
}
