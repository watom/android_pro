package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.composewidget.TitleBar;

import java.io.File;
import java.util.List;

public class SpreadActivity extends AppCompatActivity {
    private TitleBar titleBar;
    private List<File> localImage;
    private RecyclerView recyclerView;
    private SpreadListAdapter fileListAdapter;
    public static final int RESULT_01 = 0x1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RESULT_01:
                    setFileListView(localImage);
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
        titleBar = findViewById(R.id.title_bar);
        recyclerView = findViewById(R.id.recycler_spread_list);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FetchFileData fetchFileData = new FetchFileData();
                localImage = fetchFileData.getFilesByType(SpreadActivity.this);
                Message msg = new Message();
                msg.what = RESULT_01;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private void setFileListView(List<File> fileList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fileListAdapter = new SpreadListAdapter(this, fileList);
        recyclerView.setAdapter(fileListAdapter);
        fileListAdapter.setOnItemClickListener(new SpreadListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder mHolder, int position, File file) {
            }
        });
    }

    private void initEvent() {

    }
}
