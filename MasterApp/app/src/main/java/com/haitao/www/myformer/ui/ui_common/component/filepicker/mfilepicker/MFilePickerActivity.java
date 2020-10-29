package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.composewidget.TitleBar;

public class MFilePickerActivity extends AppCompatActivity {
    private static final String TAG = "文件管理器";
    public static final int RESULT_FILE_CODE = 1;
    private TitleBar titleBar;
    private Button openFilePicker;
    private TextView fileInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this); //在setContentView之前初始化Fresco
        setContentView(R.layout.activity_filepicker);
        initView();
        initEvent();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_bar);
        openFilePicker = findViewById(R.id.open_file_picker);
        fileInfo = (TextView) findViewById(R.id.file_info);

        titleBar.setTitle("文件管理器");
    }

    private void initEvent() {
        new AlertDialog.Builder(this)
                .setTitle("选择存储区域")
                .setIcon(R.drawable.ic_open_file)
                .setSingleChoiceItems(
                        new String[]{"内置sd卡", "外部sd卡"}, 0,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MFilePickerActivity.this, FileBrowserActivity.class);
                                intent.putExtra("area", which != 0);
                                startActivityForResult(intent, RESULT_FILE_CODE);
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", null).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_FILE_CODE == requestCode) {
            Bundle bundle = null;
            if (data != null && (bundle = data.getExtras()) != null) {
                String path = bundle.getString("file");
                Log.d(TAG, "onActivityResult: " + path);
                fileInfo.setText("文件信息:\n" + path);
            }
        }
    }
}
