package com.haitao.www.myformer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.haitao.www.myformer.h5.h5_dome.WebViewPlatform;
import com.haitao.www.myformer.ui.ui_common.dialog.bottomsheet_dialog.BottomSheetDialog;
import com.haitao.www.myformer.utils.ToastUtils;
import com.haitao.www.myformer.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程教程导航
 */
public class CourseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<String> bookmarkList;
    private List<String> markUrlList;
    private FloatingActionButton fab_increase;
    private ListView listview;
    private int lastX, lastY;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_floatbtn);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.list_view_enter);
        fab_increase = (FloatingActionButton) findViewById(R.id.fab_increase);
    }

    private void initData() {
        bookmarkList = new ArrayList<>();
        bookmarkList.add("runoob");
        bookmarkList.add("W3school");
        bookmarkList.add("慕课网");
        bookmarkList.add("网易云课堂");
        bookmarkList.add("可可英语");
        markUrlList = new ArrayList<>();
        markUrlList.add("http://www.runoob.com/");
        markUrlList.add("http://www.w3school.com.cn/");
        markUrlList.add("https://study.163.com/category/400000001316004");
        markUrlList.add("https://www.imooc.com/");
        markUrlList.add("http://m.kekenet.com/");

        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookmarkList);
        listview.setAdapter(stringArrayAdapter);
        listview.setOnItemClickListener(this);
    }

    public void initEvent() {
        fab_increase.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int screenWidth = dm.widthPixels;
                int screenHeight = dm.heightPixels;
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        int offsetX = (int) event.getRawX() - lastX;
                        int offsetY = (int) event.getRawY() - lastY;
                        int l = view.getLeft() + offsetX;
                        int t = view.getTop() + offsetY;
                        int r = view.getRight() + offsetX;
                        int b = view.getBottom() + offsetY;
                        if (l < 0) {
                            l = 0;
                            r = view.getWidth();
                        }
                        if (t < 0) {
                            t = 0;
                            b = view.getHeight();
                        }
                        if (r > screenWidth) {
                            r = screenWidth;
                            l = r - view.getWidth();
                        }
                        if (b > screenHeight) {
                            b = screenHeight;
                            t = b - view.getHeight();
                        }
                        view.layout(l, t, r, b);
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        view.postInvalidate();
                        break;
                }
                return false;
            }
        });

        fab_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

    }

    private BottomSheetDialog bottomSheetDialog;

    private void showBottomSheetDialog() {
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog();
        }
        bottomSheetDialog.show(this.getSupportFragmentManager(), "ecologyTourDialogFragment");
        bottomSheetDialog.setOnSignUpClickListener(new BottomSheetDialog.OnSignUpClickListener() {
            @Override
            public void onClick(Bundle bundle) {
                String tv_label_name = bundle.getString("tv_label_name");
                String tv_label_url = bundle.getString("tv_label_url");
                if (Util.isEmpty(tv_label_name)) {
                    ToastUtils.showToast(CourseActivity.this, "标签名不能为空");
                    return;
                } else if (!Util.isEmail(tv_label_url)) {
                    ToastUtils.showToast(CourseActivity.this, "请输入正确的地址");
                    return;
                } else {
                    ToastUtils.showToast(CourseActivity.this, "标签名:" + tv_label_name + "--地址:" + tv_label_url);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, WebViewPlatform.class);
        intent.putExtra("bookmarkUrl",markUrlList.get(position));
        startActivity(intent);
    }
}
