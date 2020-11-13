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
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.haitao.www.myformer.R;
import com.haitao.www.myformer.model.global.SimpleBean;
import com.haitao.www.myformer.ui.ui_common.component.composewidget.TitleBar;
import com.haitao.www.myformer.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

import static com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker.FetchFileData.getPath;

public class MFilePickerActivity extends AppCompatActivity {
    private static final String TAG = "文件管理器";
    public static final int RESULT_01 = 1;
    public static final int RESULT_02 = 2;
    public static final int RESULT_03 = 3;
    public static final boolean isMultiple = true; //false:单选 true:多选
    private TitleBar titleBar;
    private Button openNatureFileBrowser, openFilePicker, refreshMediaDatabase, refreshMediaScanner, openSpecificPath, openSpecificPathHandle;
    private TextView natureFileInfo, fileInfo;
    private String storePath;
    private SearchView searchBar;
    private RecyclerView fileMenu;
    private View mSearchPlate;
    private FileMenuAdapter fileMenuAdapter;
    private RecyclerView searchResultList;
    private EditText mSearchSrcTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this); //在setContentView之前初始化Fresco
        setContentView(R.layout.activity_filepicker);
        initView();
        initEvent();
    }

    private void initView() {
        searchBar = findViewById(R.id.view_search_bar);
        fileMenu = findViewById(R.id.file_menu);
        openNatureFileBrowser = findViewById(R.id.open_nature_file_browser);
        natureFileInfo = findViewById(R.id.nature_file_info);
        openFilePicker = findViewById(R.id.open_file_picker);
        fileInfo = findViewById(R.id.file_info);
        refreshMediaDatabase = findViewById(R.id.refresh_media_database);
        refreshMediaScanner = findViewById(R.id.refresh_media_scanner);
        openSpecificPath = findViewById(R.id.open_specific_path);
        openSpecificPathHandle = findViewById(R.id.open_specific_path_handle);

        int mSearchSrcTextViewId = getResources().getIdentifier("android:id/search_src_text", null, null);
        int mSearchPlateId = getResources().getIdentifier("android:id/search_plate", null, null);
        mSearchSrcTextView = findViewById(mSearchSrcTextViewId);
        mSearchPlate = findViewById(mSearchPlateId);
        mSearchSrcTextView.setFocusable(false);
        searchBar.setIconifiedByDefault(false);  //设置搜索图标是否在框内
        mSearchPlate.setBackground(null);        //去掉搜索输入的背景线条
        initType(fileMenu);
    }

    private void initType(RecyclerView fileMenu) {
        List<SimpleBean> menuList = new ArrayList<>();
        SimpleBean simpleBean0 = new SimpleBean(R.drawable.icon_menu_image, "图片", 0);
        SimpleBean simpleBean1 = new SimpleBean(R.drawable.icon_menu_video, "视频", 1);
        SimpleBean simpleBean2 = new SimpleBean(R.drawable.icon_menu_audio, "音频", 2);
        SimpleBean simpleBean3 = new SimpleBean(R.drawable.icon_menu_doc, "文档", 3);
        SimpleBean simpleBean4 = new SimpleBean(R.drawable.icon_menu_zip, "压缩包", 4);
        SimpleBean simpleBean5 = new SimpleBean(R.drawable.icon_menu_app, "应用", 5);
        menuList.add(simpleBean0);
        menuList.add(simpleBean1);
        menuList.add(simpleBean2);
        menuList.add(simpleBean3);
        menuList.add(simpleBean4);
        menuList.add(simpleBean5);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        fileMenu.setLayoutManager(gridLayoutManager);
        fileMenuAdapter = new FileMenuAdapter(this, menuList);
        fileMenu.setAdapter(fileMenuAdapter);
    }

    private void initEvent() {
        mSearchSrcTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MFilePickerActivity.this, SearchActivity.class));
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

        openSpecificPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, RESULT_03);
            }
        });

        openSpecificPathHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/zhihu");
                Log.i(TAG, "onClick: " + uri.toString());
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/zhihu"), "*/*");
                startActivity(intent);

//                Uri uri = Uri.parse("content://com.android.externalstorage.documents/document/primary:zhihu");
//                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("*/*");
//                intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri);
//                startActivity(intent);
            }
        });

        fileMenuAdapter.setOnClickItemListener(new FileMenuAdapter.OnClickItemListener() {
            @Override
            public void onClick(SimpleBean bean) {
                switch (bean.getTag()){
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                }
            }
        });
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
        } else if (requestCode == RESULT_03) {
            if (data != null) {
                Uri uri = data.getData();
                Log.i(TAG, "Uri: " + uri.toString() + "\nPath: ");
            }
        }
    }
}