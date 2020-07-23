package com.haitao.www.myformer.ui.ui_common.moduleTest.dragView.DragGridView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.haitao.www.myformer.R;
import com.watom.draggridviewlib.DragGridView;
import com.watom.draggridviewlib.listener.IDrawer;
import com.watom.draggridviewlib.listener.OnItemCapturedListener;
import com.watom.draggridviewlib.utils.DensityUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DragGridViewActivity extends Activity implements View.OnClickListener {
    private DragGridView mGridView;
    private List<String> strList;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private TextView enableSelectorTv, changeModeTv, addTagTv,recoveryTagTv;
    private DragGridViewAdapter adapter;
    private ViewGroup outLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_gridview);
        initData();
        initView();
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void initView() {
        enableSelectorTv = findViewById(R.id.text_enable_selector);
        changeModeTv = findViewById(R.id.text_change_mode);
        addTagTv = findViewById(R.id.text_add_tag);
        recoveryTagTv = findViewById(R.id.text_recovery_tag);
        outLayout = findViewById(R.id.out_layout);

        mGridView = findViewById(R.id.grid_tips);
        adapter = new DragGridViewAdapter(this, strList);
        mGridView.setAdapter(adapter);

        setMode(DragGridView.MODE.LONG_PRESS);
        mGridView.setAutoOptimize(false);
        //当gridview可以滚动并且被拖动的item位于gridview的顶部或者底部时，设置gridview滚屏的速度，
        // 每秒移动的像素点个数，默认750，可不设置。
        mGridView.setScrollSpeed(750);
//        adapter.notifyDataSetChanged();
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!mGridView.isTouchMode()&&!mGridView.isNoneMode() && !adapter.isFixed(position)) {//long press enter edit mode.
                    setMode(DragGridView.MODE.TOUCH);
                    return true;
                }
                return false;
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DragGridViewActivity.this, "click item at " + position, Toast.LENGTH_SHORT).show();
            }
        });
        mGridView.setOnItemCapturedListener(new OnItemCapturedListener() {
            @Override
            public void onItemCaptured(View v, int position) {
                v.setScaleX(1f);
                v.setScaleY(1f);
            }

            @Override
            public void onItemReleased(View v, int position) {
                v.setScaleX(1f);
                v.setScaleY(1f);
            }

        });
        mGridView.setDrawer(new IDrawer() {
            @Override
            public void onDraw(Canvas canvas, int width, int height) {
                if (!mGridView.isNoneMode()) {
                    int offsetX = -DensityUtil.dip2px(DragGridViewActivity.this, 10);
                    int offsetY = -DensityUtil.dip2px(DragGridViewActivity.this, 10);
                    //文字绘制于gridview的右下角，并向左，向上偏移10dp。
                    drawTips(canvas, width + offsetX, height + offsetY);
                }
            }
        },false);
        enableSelectorTv.setOnClickListener(this);
        //enableSelectorTv.performClick();
        changeModeTv.setOnClickListener(this);
        addTagTv.setOnClickListener(this);
        recoveryTagTv.setOnClickListener(this);
    }

    String paintText = "长按排序或删除";
    int textWidth;
    int textHeight;
    StaticLayout tipsLayout;
    private TextPaint mTextPaint;

    private void drawTips(Canvas canvas, int width, int height) {
        if (mTextPaint == null) {
            mTextPaint = new TextPaint();
            mTextPaint.setColor(Color.parseColor("#cccccc"));
            mTextPaint.setTextSize(DensityUtil.dip2px(DragGridViewActivity.this, 12));
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            textHeight = (int) (fontMetrics.bottom - fontMetrics.top) + 1;
            textWidth = (int) mTextPaint.measureText(paintText) + 1;
        }
        width = width - textWidth;
        height = height - textHeight;
        if (tipsLayout == null) {
            tipsLayout = new StaticLayout(paintText, mTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.5f, 0f, false);
        }
        canvas.translate(width, height);
        tipsLayout.draw(canvas);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.text_enable_selector:
                boolean isSelectorEnabled = mGridView.isSelectorEnabled();
                //建议传入false,不要开启selector,不然item移动以后，在item原来的位置会有残影逐渐消失的效果。
                //默认是false。
                mGridView.setSelectorEnabled(!isSelectorEnabled);
                enableSelectorTv.setText(!isSelectorEnabled ? "selector已开启" : "selector已关闭");
                break;
            case R.id.text_change_mode:
                DragGridView.MODE curMode = mGridView.getMode();
                int index = DragGridView.MODE.indexOf(curMode);
                index = (index + 1) % DragGridView.MODE.values().length;
                //有三种模式分别是点击拖动，长按拖动和不拖动，分别对应的是TOUCH,LONG_PRESS和NONE
                setMode(DragGridView.MODE.get(index));
                break;
            case R.id.text_add_tag:
                addTag();
                break;
            case R.id.text_recovery_tag:
//                moveTaskToBack(true);
                recoveryTag();
                break;
        }
    }

    private void recoveryTag() {
        for (Iterator<String> iter = strList.iterator(); iter.hasNext(); ) {
            String str = iter.next();
            if (str.contains("更多")) {
                iter.remove();
            }
        }
        setMode(DragGridView.MODE.LONG_PRESS);
        adapter.setData(strList);
        outLayout.setClipChildren(false);
    }

    private void addTag() {
        for (int i = 0; i < 4; i++) {
            strList.add("更多 "+(strList.size()-1));
        }
        adapter.setData(strList);
        outLayout.setClipChildren(true);
        mGridView.smoothScrollToPosition(adapter.getCount() - 1);
    }

    private void setMode(DragGridView.MODE mode) {
        mGridView.setMode(mode);
        changeModeTv.setText(mode.toString());
        adapter.setInEditMode(mode == DragGridView.MODE.TOUCH);
    }

    private void log(String msg) {
        Log.e(getClass().getCanonicalName(), msg);
    }

    public void initData() {
        strList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            strList.add("ITEM " + i);
//        }
        strList.add("头条");
        strList.add("轻松一刻");
        strList.add("视频");
        strList.add("娱乐");
        strList.add("段子");
        strList.add("跟帖");
        strList.add("活力学院");
        strList.add("北京");
        strList.add("社会");
        strList.add("军事");
        strList.add("热点");
        strList.add("直播");
        strList.add("网易号");
        strList.add("房产");
    }
}
