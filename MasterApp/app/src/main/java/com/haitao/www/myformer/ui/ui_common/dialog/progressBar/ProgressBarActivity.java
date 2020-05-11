package com.haitao.www.myformer.ui.ui_common.dialog.progressBar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;

public class ProgressBarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"转圈进度条","圆圈内数字进度条","原生圆圈进度条"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s) {
            case "转圈进度条":
                new LoadingDialog(this).show();
                break;
            case "圆圈内数字进度条":
                startActivity(new Intent(this, CircleProgressActivity.class));
                break;
            case "原生圆圈进度条":
                startActivity(new Intent(this, PrimevalProgressActivity.class));
                break;
        }
    }
}
