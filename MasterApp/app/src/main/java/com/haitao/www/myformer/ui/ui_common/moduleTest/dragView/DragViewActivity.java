package com.haitao.www.myformer.ui.ui_common.moduleTest.dragView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.moduleTest.dragView.DragRecyclerView.DragRecyclerViewActivity;
import com.haitao.www.myformer.ui.ui_common.moduleTest.dragView.ReliableDragGridView.BaseDragGridViewActivity;
import com.haitao.www.myformer.utils.ToastUtils;

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
        content = new String[]{"可拖拽RecyclerView","可拖拽排序GridView","可拖拽ItemTouchHelper"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "可拖拽RecyclerView":
                startActivity(new Intent(this, DragRecyclerViewActivity.class));
                break;
            case "可拖拽排序GridView":
                ToastUtils.showToast(this,"可拖拽排序GridView");
//                startActivity(new Intent(this, DragGridViewActivity.class)); //待开发
                break;
            case "可拖拽GridView":
                ToastUtils.showToast(this,"可拖拽排序GridView");
                startActivity(new Intent(this, BaseDragGridViewActivity.class));
                break;
            case "可拖拽ItemTouchHelper":
                ToastUtils.showToast(this,"可拖拽ItemTouchHelper");
//                startActivity(new Intent(this, ItemTouchHelperActivity.class));
                break;
        }
    }
}
