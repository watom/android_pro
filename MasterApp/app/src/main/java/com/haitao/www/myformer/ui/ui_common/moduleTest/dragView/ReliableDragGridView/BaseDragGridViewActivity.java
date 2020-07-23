package com.haitao.www.myformer.ui.ui_common.moduleTest.dragView.ReliableDragGridView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BaseDragGridViewActivity extends Activity {
    private Context context;
    private List<String> strList;
    private BaseDragGridView baseDragGridView;
    private List<HashMap<String, Object>> dataSourceList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_draggridview);
        this.context=BaseDragGridViewActivity.this;
        initView();
        initEvent();
    }

    private void initView() {
        baseDragGridView = findViewById(R.id.ReliableDragGridView);
        for (int i = 0; i < 8; i++) {
            HashMap<String, Object> itemHashMap = new HashMap<>();
            itemHashMap.put("item_image", R.mipmap.ic_browse);
            itemHashMap.put("item_text", "拖拽 " + i);
            dataSourceList.add(itemHashMap);
        }

        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, dataSourceList,
                R.layout.item_drag_gridview, new String[]{"item_image", "item_text"},
                new int[]{R.id.grid_item_image, R.id.grid_item_title});

        baseDragGridView.setAdapter(mSimpleAdapter);

        baseDragGridView.setOnChangeListener(new BaseDragGridView.OnChanageListener() {
            @Override
            public void onChange(int from, int to) {
                HashMap<String, Object> temp = dataSourceList.get(from);
                //这里的处理需要注意下
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(dataSourceList, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(dataSourceList, i, i - 1);
                    }
                }

                dataSourceList.set(to, temp);
                mSimpleAdapter.notifyDataSetChanged();
            }
        });

    }


    private void initEvent() {
        baseDragGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(context,"点击了"+position);
            }
        });
    }
}
