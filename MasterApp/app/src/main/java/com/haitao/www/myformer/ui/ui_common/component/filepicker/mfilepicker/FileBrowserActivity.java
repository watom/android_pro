package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.composewidget.TitleBar;
import com.haitao.www.myformer.utils.ToastUtils;

import java.io.File;
import java.util.List;

public class FileBrowserActivity extends AppCompatActivity {
    private static final String TAG = "文件管理器";
    public static final int RESULT_FILE_CODE = 1;

    private TitleBar titleBar;
    private TextView tvFilePath;
    private String rootPath;
    private String filePath;
    private RecyclerView recyclerView;

    private boolean isSDStorage;
    private List<String> pathList;
    private List<String> itemsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_bar);
        tvFilePath = findViewById(R.id.tv_file_path);
        recyclerView = findViewById(R.id.recycler_fileinfo_list);

        titleBar.setBackBtnText("内部存储");
        titleBar.setBackBtnTextColor(R.color.black);
        titleBar.setRightBtn1Icon(R.drawable.ic_sousuo);
    }

    private void initEvent() {
        //刷新MediaStore数据库

    }

    private void initData() {
        isSDStorage = getIntent().getBooleanExtra("area", false);
        rootPath = FilePickerUtil.getRootPath(isSDStorage);
        if (rootPath == null) {
            Toast.makeText(this, "所选SD卡为空！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            getFileDir(rootPath);
        }
    }

    private void getFileDir(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        if (fileList == null) {
            ToastUtils.showToast(FileBrowserActivity.this, "暂无文件");
            finish();
        }else{
            File[] fl = FilePickerUtil.sortFileListByName(fileList, "asc");
            setFileListView(filePath, fl);
        }
    }

    private void setFileListView(String filePath,  File[] fileList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        FileListAdapter adapter = new FileListAdapter(this, fileList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FileListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder mHolder, int position, File file) {
                if (file.isDirectory()) {
                    if (file.list().length > 0) {
                        getFileDir(file.getAbsolutePath());
                    }
                } else {
                    //todo查看详情
                    ToastUtils.showToast(FileBrowserActivity.this, "文件");
                }
            }
        });
        Log.i("路径", "filePath = " + filePath);
        tvFilePath.setText(FilePickerUtil.getFilePathByPath(filePath));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            File file = new File(filePath);
            String parentPath = file.getParent();
            if ("/storage/emulated/0".equals(filePath)) {
                finish();
            } else {
                getFileDir(parentPath);
            }
        }
        return true;
    }
}
