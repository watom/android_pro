package com.haitao.www.myformer.ui.ui_common.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.listview.recyclerview.RecyclerViewTestActivity;
import com.haitao.www.myformer.ui.ui_common.listview.XListView.ListViewActivity;
import com.haitao.www.myformer.ui.ui_common.listview.expandablelistView.MyExpandableListView;
import com.haitao.www.myformer.ui.ui_common.listview.scrollviewlist.ScrollViewTestActivity;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class ListViewCategory extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"ListView","XListView","ExpandableListView","RecyclerView","ScrollView"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s) {
            case "ListView":
//                startActivity(new Intent(this, CommonListViewActivity.class));
                break;
            case "XListView":
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case "ExpandableListView":
                startActivity(new Intent(this, MyExpandableListView.class));
                break;
            case "RecyclerView":
                startActivity(new Intent(this, RecyclerViewTestActivity.class));
                break;
            case "ScrollView":
                startActivity(new Intent(this, ScrollViewTestActivity.class));
                break;
        }
    }
}
