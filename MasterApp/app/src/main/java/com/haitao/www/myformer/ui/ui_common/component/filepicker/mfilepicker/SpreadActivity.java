package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.BuildConfig;
import com.haitao.www.myformer.R;
import com.haitao.www.myformer.model.global.SimpleBean;
import com.haitao.www.myformer.ui.ui_common.component.composewidget.TitleBar;
import com.haitao.www.myformer.utils.DataUtil;
import com.haitao.www.myformer.utils.PackageUtil;
import com.haitao.www.myformer.utils.PermissionUtil;
import com.haitao.www.myformer.utils.ToastUtils;

import java.io.File;
import java.util.List;

public class SpreadActivity extends AppCompatActivity {
    private TitleBar titleBar;
    private SimpleBean bean;
    private List<File> fileInfoList;
    private RecyclerView recyclerView;
    private SpreadListAdapter fileListAdapter;
    public static final int RESULT_01 = 0x1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RESULT_01:
                    setFileListView(fileInfoList);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_spread);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        Intent intent = getIntent();
        bean = (SimpleBean) intent.getSerializableExtra("SpreadList");

        titleBar = findViewById(R.id.title_bar);
        recyclerView = findViewById(R.id.recycler_spread_list);
        titleBar.setTitle(bean.getImageText());
    }

    private void initData() {
        String[] list = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionUtil.getInstance().setCheck(SpreadActivity.this, list, 100);
    }

    private void setFileListView(List<File> fileInfoList) {
        if (DataUtil.isEmpty(fileInfoList)) {
            ToastUtils.showToast(this, "没有找到" + bean.getImageText());
            this.finish();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fileListAdapter = new SpreadListAdapter(this, fileInfoList);
        recyclerView.setAdapter(fileListAdapter);
        fileListAdapter.setOnItemClickListener(new SpreadListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder mHolder, int position, File file) {

            }
        });
    }

    private void initEvent() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.getInstance().setRequestResult(this, permissions, grantResults, new PermissionUtil.Callback() {
            @Override
            public void todoAfter(Object object) {
                getFileList();
            }
        });
    }

    private void getFileList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileType fileType = bean.getFileType();
                fileInfoList = FetchFileData.getInstance().getFileInfoByType(SpreadActivity.this, fileType);
                if (!DataUtil.isEmpty(fileInfoList)) {
                    Message msg = new Message();
                    msg.what = RESULT_01;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
}
