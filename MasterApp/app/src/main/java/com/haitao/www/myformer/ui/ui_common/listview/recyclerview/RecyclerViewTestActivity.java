package com.haitao.www.myformer.ui.ui_common.listview.recyclerview;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.dialog.alert_dialog.AlertDialogDemo;
import com.haitao.www.myformer.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/1 0001.
 * LayoutManager设置布局样式：（1）LinerLayoutManager-ListView（2）GridLayoutManager-GridView （3）StaggeredGridLayoutManager-瀑布流
 */

public class RecyclerViewTestActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnDelete;
    private Button btnListview;
    private Button btnGridview;
    private Button btnFlowview;
    private Button btnSplit01;
    private Button btnSplit02;
    private Button btnSplit03;
    private RecyclerView recyclerview;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int orientation; //设置默认值

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        findViews();
        initEvent();
    }

    private void findViews() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnListview = (Button) findViewById(R.id.btn_listview);
        btnGridview = (Button) findViewById(R.id.btn_gridview);
        btnFlowview = (Button) findViewById(R.id.btn_flowview);
        btnSplit01 = (Button) findViewById(R.id.btn_split_01);
        btnSplit02 = (Button) findViewById(R.id.btn_split_02);
        btnSplit03 = (Button) findViewById(R.id.btn_split_03);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    private void initEvent() {
        btnListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleChoiceDialog(view);
            }
        });
        btnGridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleChoiceDialog(view);
            }
        });
        btnFlowview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleChoiceDialog(view);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 增加item
                if (recyclerViewAdapter != null) {
                    recyclerViewAdapter.addData(0, "New_Content");
                    recyclerview.scrollToPosition(0);  //滑动到某个位置
                } else {
                    ToastUtils.showToast(RecyclerViewTestActivity.this, "请先选择视图");
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 删除item
                if (recyclerViewAdapter != null) {
                    recyclerViewAdapter.removeData(0);
                } else {
                    ToastUtils.showToast(RecyclerViewTestActivity.this, "请先选择视图");
                }
            }
        });
        btnSplit01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(RecyclerViewTestActivity.this, "暂未开发");
            }
        });
        btnSplit02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(RecyclerViewTestActivity.this, "主要针对网格布局");
                int spanCount = 2;//设置列数
                recyclerview.setLayoutManager(new GridLayoutManager(RecyclerViewTestActivity.this, spanCount, orientation, false));
                recyclerview.addItemDecoration(new SpaceItemDecoration(10, spanCount));
            }
        });
        btnSplit03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(RecyclerViewTestActivity.this, "暂未开发");
            }
        });
    }

    /**
     * Dialog选择滑动方向
     * 返回0: LinearLayoutManager.HORIZONTAL
     * 1: LinearLayoutManager.VERTICAL
     *
     * @param view
     */
    private void showSingleChoiceDialog(final View view) {
        orientation = 0;
        final String[] items = {"横向", "纵向"};
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(this);
        singleChoiceDialog.setIcon(R.mipmap.ic_launcher);
        singleChoiceDialog.setTitle("请选择滑动方向");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        orientation = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == -1) {
                            switch (view.getId()) {
                                case R.id.btn_listview:
                                    recyclerview.setLayoutManager(new LinearLayoutManager(RecyclerViewTestActivity.this, orientation, false));
                                    break;
                                case R.id.btn_gridview:
                                    recyclerview.setLayoutManager(new GridLayoutManager(RecyclerViewTestActivity.this, 3, orientation, false));
                                    break;
                                case R.id.btn_flowview:
                                    recyclerview.setLayoutManager(new StaggeredGridLayoutManager(4, orientation));
                                    break;
                            }

                            setAdapter();

                            ToastUtils.showToast(RecyclerViewTestActivity.this,
                                    items[orientation] + "滑动");
                        }
                    }
                });
        singleChoiceDialog.show();
    }

    private void setAdapter() {
        //设置数据适配器RecyclerViewAdapter
        recyclerViewAdapter = new RecyclerViewAdapter(this, getData());
        recyclerview.setAdapter(recyclerViewAdapter);
        //设置默认动画，也可以自定义动画。
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //设置item的点击事件
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast(RecyclerViewTestActivity.this, "短按点击了-" + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtils.showToast(RecyclerViewTestActivity.this, "长按点击了-" + position);
            }
        });
    }

    /**
     * 设置两个item之间的间隔均等
     * 说明：暂时只涉及了每行2列Item的情况，其他的数量暂时未做，待后续补充算法。
     */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space; //设置间距
        private int spanCount; //每行能显示的列数

        public SpaceItemDecoration(int space, int spanCount) {
            this.space = space;
            this.spanCount = spanCount;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int childLayoutPosition = parent.getChildLayoutPosition(view);//从0开始计数
            int halfSpace = space / 2;
            if (childLayoutPosition != 0) {
                if (childLayoutPosition % 2 == 0) {
                    outRect.left = halfSpace;
                    outRect.right = space;
                    outRect.bottom = space;
                } else {
                    outRect.left = space;
                    outRect.right = halfSpace;
                    outRect.bottom = space;
                }
            }
        }
    }

    /**
     * 制造假数据
     *
     * @return
     */
    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "item ☛ ";
        for (int i = 0; i < 100; i++) {
            data.add(temp + i);
        }
        return data;
    }
}
