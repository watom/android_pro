package com.haitao.www.myformer.complex;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.ToastUtils;

/**
 * 新的新的技术簇，必须先弹出学习页面。
 */
public class NewTechniqueActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = (ListView) findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"AR技术", "JNI和JNA技术"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "AR技术":
                ToastUtils.showToast(NewTechniqueActivity.this,"AR技术");
                break;
            case "JNI和JNA技术":
                ToastUtils.showToast(NewTechniqueActivity.this,"JNI和JNA技术");
                break;
        }
    }
}
