package com.haitao.www.myformer.ui.ui_common.listview.scrollviewlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewTestActivity extends AppCompatActivity {
    private LinearLayout ll_root;
    private LinearLayout ll_child;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        initView();
        initData();
    }

    private void initData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("数据= " + i);
        }
        for (String dataItem : dataList) {
            TextView textView = new TextView(this);
            textView.setText(dataItem);
            textView.setBackgroundColor(this.getResources().getColor(R.color.yellow));
            ll_root.addView(textView);
        }
        ll_child.removeView(ll_root);
        for (String dataItem01 : dataList) {
            TextView textView = new TextView(this);
            textView.setBackgroundColor(this.getResources().getColor(R.color.red));
            textView.setText(dataItem01);
            ll_child.addView(textView);
        }
    }

    private void initView() {
        ll_root = (LinearLayout) this.findViewById(R.id.ll_root);
        ll_child = (LinearLayout) this.findViewById(R.id.ll_child);
    }
}
