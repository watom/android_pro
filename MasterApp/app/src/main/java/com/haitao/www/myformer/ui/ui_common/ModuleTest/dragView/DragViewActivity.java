package com.haitao.www.myformer.ui.ui_common.ModuleTest.dragView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.ModuleTest.excellayout.ExcelTablayout;
import com.haitao.www.myformer.ui.ui_common.ModuleTest.ratingbarview.RatingBarActivity;
import com.haitao.www.myformer.ui.ui_common.ModuleTest.timeLine.TimeLineActivity;

public class DragViewActivity extends Activity implements AdapterView.OnItemClickListener{
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview =  findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"可拖拽GridView","可拖拽排序GridView"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "可拖拽GridView":
                startActivity(new Intent(this, ExcelTablayout.class));
                break;
            case "可拖拽排序GridView":
                startActivity(new Intent(this, TimeLineActivity.class));
                break;
        }
    }
}
