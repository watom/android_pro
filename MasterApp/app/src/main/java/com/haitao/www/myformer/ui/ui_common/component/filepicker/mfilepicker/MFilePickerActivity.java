package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.composewidget.TitleBar;
import com.haitao.www.myformer.utils.DataUtil;
import com.haitao.www.myformer.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.haitao.www.myformer.utils.Util.getPath;

public class MFilePickerActivity extends AppCompatActivity {
    private static final String TAG = "文件管理器";
    public static final int RESULT_01 = 1;
    public static final int RESULT_02 = 2;
    public static final boolean isMultiple = true; //false:单选 true:多选
    private TitleBar titleBar;
    private Button openNatureFileBrowser, openFilePicker, refreshMediaDatabase, refreshMediaScanner;
    private TextView natureFileInfo, fileInfo;
    private String storePath;
    private SearchView searchBar;
    private RecyclerView searchResultList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this); //在setContentView之前初始化Fresco
        setContentView(R.layout.activity_filepicker);
        initView();
        initEvent();
    }

    private void initView() {
        searchBar = findViewById(R.id.search_bar);
        searchResultList = findViewById(R.id.search_result_list);
        openNatureFileBrowser = findViewById(R.id.open_nature_file_browser);
        natureFileInfo = findViewById(R.id.nature_file_info);
        openFilePicker = findViewById(R.id.open_file_picker);
        fileInfo = findViewById(R.id.file_info);
        refreshMediaDatabase = findViewById(R.id.refresh_media_database);
        refreshMediaScanner = findViewById(R.id.refresh_media_scanner);
    }

    private void initEvent() {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (DataUtil.isEmpty(query)) {
                    ToastUtils.showToast(MFilePickerActivity.this, "请输入查找内容！");
                } else {
                    findList.clear();
                    for (int i = 0; i < list.size(); i++) {
                        iconInformation information = list.get(i);
                        if (information.getName().equals(query)) {
                            findList.add(information);
                            break;
                        }
                    }
                    if (findList.size() == 0) {
                        ToastUtils.showToast(MFilePickerActivity.this, "查找的商品不在列表中");
                    } else {
                        ToastUtils.showToast(MFilePickerActivity.this, "查找成功");
                        findAdapter = new listViewAdapter(MFilePickerActivity.this, findList);
                        searchResultList.setAdapter(findAdapter);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //打开原生的文件选择器
        openNatureFileBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, isMultiple);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, RESULT_02);
            }
        });

        //打开自定义的文件管理器
        openFilePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MFilePickerActivity.this)
                        .setTitle("选择存储区域")
                        .setIcon(R.drawable.ic_open_file)
                        .setSingleChoiceItems(
                                new String[]{"内置sd卡", "外部sd卡"}, 0,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MFilePickerActivity.this, FileBrowserActivity.class);
                                        intent.putExtra("area", which != 0);
                                        startActivityForResult(intent, RESULT_01);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("取消", null).show();
            }
        });

        refreshMediaDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
//                        Uri.parse("file://" + Environment.getExternalStorageDirectory())));
                Uri mUri = Uri.parse("content://media/external/images/media");
                Uri mImageUri = null;
                Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null,
                        null, MediaStore.Images.Media.DEFAULT_SORT_ORDER);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
                    int ringtoneID = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
                    mImageUri = Uri.withAppendedPath(mUri, "" + ringtoneID);
                    Log.i(TAG, "路径: " + mImageUri.toString());
                    cursor.moveToNext();
                }
            }
        });

        refreshMediaScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!DataUtil.isEmpty(storePath)) {
                    Uri data = Uri.parse("file:///" + storePath);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, data));
                }
            }
        });
    }

    private void setFileListView(String filePath,  File[] fileList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        FileListAdapter adapter = new FileListAdapter(this, fileList);
        searchResultList.setLayoutManager(layoutManager);
        searchResultList.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_01) {
            Bundle bundle = null;
            if (data != null && (bundle = data.getExtras()) != null) {
                String path = bundle.getString("file");
                Log.d(TAG, "onActivityResult: " + path);
                fileInfo.setText("文件信息:\n" + path);
            }
        } else if (requestCode == RESULT_02) {
            if (data.getData() != null) {
                try {
                    Uri uri = data.getData();
                    String path = getPath(getApplicationContext(), uri);//对获得的uri做解析
                    //获得正式路径后，进行业务操作
                    Log.i(TAG, "onActivityResult11: " + path);
                    natureFileInfo.setText(path);
                } catch (Exception e) {
                    Log.i(TAG, "异常: " + e.getMessage());
                }
            } else {
                //长按使用多选的情况
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    List<String> pathList = new ArrayList<>();
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Uri uri = item.getUri();
                        String path = getPath(getApplicationContext(), uri);//对获得的uri做解析
                        pathList.add(path);
                    }
                    //获得正式路径后，进行业务操作
                    Log.i(TAG, "onActivityResult22: " + pathList);
                    natureFileInfo.setText(pathList.toString());
                }
            }

        }
    }
}
