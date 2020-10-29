package com.haitao.www.myformer.ui.ui_common.component.filepicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.haitao.www.myformer.R;

import java.util.List;

import droidninja.filepicker.FilePickerBuilder;

public class FilePickerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final int result_01 = 10000;
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"选择图片","选择文件"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s) {
            case "选择图片":
                FilePickerBuilder.getInstance()
                        .setMaxCount(5) //optional
//                        .setSelectedFiles(filePaths) //optional
                        .setActivityTheme(R.style.LibAppTheme) //optional
                        .pickPhoto(this, 10000);
                break;
            case "选择文件":
                FilePickerBuilder.getInstance()
                        .setMaxCount(5) //optional
//                        .setSelectedFiles(filePaths) //optional
                        .setActivityTheme(R.style.LibAppTheme) //optional
                        .pickFile(this, 10001);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == result_01) {
                //如果是文件选择模式，需要获取选择的所有文件的路径集合
                //List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);//Constant.RESULT_INFO == "paths"
                List<String> list = data.getStringArrayListExtra("paths");
                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
                //如果是文件夹选择模式，需要获取选择的文件夹路径
                String path = data.getStringExtra("path");
                Toast.makeText(getApplicationContext(), "选中的路径为" + path, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
