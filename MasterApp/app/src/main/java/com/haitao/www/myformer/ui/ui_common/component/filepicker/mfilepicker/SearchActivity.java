package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.DataUtil;
import com.haitao.www.myformer.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ImageView imageBack;
    private View mSearchPlate;
    private SearchView searchBar;
    private TextView searchResultTitle;
    private RecyclerView searchResultList;
    private FileListAdapter fileListAdapter;
    private String filePath;
    private List<File> filteredFileList = new ArrayList<>();
    private File[] filteredFileArray;
    private Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    setFileListView(filteredFileList);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search_bar);
        initView();
        initEvent();
    }

    private void initView() {
        imageBack = findViewById(R.id.image_back);
        searchBar = findViewById(R.id.search_view);
        searchResultTitle = findViewById(R.id.search_result_title);
        searchResultList = findViewById(R.id.search_result_list);
        int mSearchPlateId = getResources().getIdentifier("android:id/search_plate", null, null);
        mSearchPlate = findViewById(mSearchPlateId);
        searchBar.setIconifiedByDefault(false);  //设置搜索图标是否在框内
        mSearchPlate.setBackground(null);        //去掉搜索输入的背景线条
    }

    private void initEvent() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (DataUtil.isEmpty(query)) {
                    //清空list
                    ToastUtils.showToast(SearchActivity.this, "请输入搜索名称！");
                } else {
                    //设置list过滤器
                    searchFileTimeConsuming(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchFileTimeConsuming(newText);
                return false;
            }
        });
    }

    private void searchFileTimeConsuming(String name) {
        clearListView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getFiltFile(name);
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private void getFiltFile(String name) {
        String rootPath = FilePickerUtil.getRootPath(false);
        getFile(new File(rootPath), name);
    }

    /**
     * 过滤文件
     *
     * @param file 数据入口
     * @param name 希望得到的名称
     */
    private void getFile(File file, String name) {
        if (file.getName().toLowerCase().contains(name)) {
            filteredFileList.add(file);
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File childFile : files) {
                getFile(childFile, name);
            }
        }
    }

    private void setFileListView(List<File> fileList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        searchResultList.setLayoutManager(layoutManager);
        fileListAdapter = new FileListAdapter(this, fileList);
        searchResultList.setAdapter(fileListAdapter);
        fileListAdapter.setOnItemClickListener(new FileListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder mHolder, int position, File file) {
                if (file.isDirectory()) {
                    if (file.list().length > 0) {
                        getFileDir(file.getAbsolutePath());
                    }
                } else {
                    //todo查看详情
                    ToastUtils.showToast(SearchActivity.this, "文件");
                }
            }
        });
        if (fileList.size() > 0) {
            searchResultTitle.setText("找到" + fileList.size() + "项文件");
        }
    }

    private void getFileDir(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        if (fileList == null) {
            ToastUtils.showToast(SearchActivity.this, "暂无文件");
            finish();
        } else {
            File[] fl = FilePickerUtil.sortFileListByName(fileList, "asc");
            List<File> list = new ArrayList<>(Arrays.asList(fl));
            setFileListView(list);
        }
    }

    private void clearListView() {
        if (searchResultList.getChildCount() > 0) {
            searchResultList.removeAllViews();
            fileListAdapter.updateData(null);
        }
    }
}
