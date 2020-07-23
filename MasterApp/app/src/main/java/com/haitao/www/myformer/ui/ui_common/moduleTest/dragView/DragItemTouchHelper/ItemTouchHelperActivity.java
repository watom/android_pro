package com.haitao.www.myformer.ui.ui_common.moduleTest.dragView.DragItemTouchHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.R;

import java.util.ArrayList;
import java.util.List;

public class ItemTouchHelperActivity extends AppCompatActivity {
    private RecyclerView dragItemtouch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_itemtouch);
        initView();
    }

    private void initView() {
        final List<ItemBean> itemBeanList = new ArrayList<>();
        dragItemtouch =  findViewById(R.id.drag_itemtouch);
        final List<ItemBean> items = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setName("频道" + i);
            itemBeanList.add(itemBean);
        }
        GridLayoutManager manager = new GridLayoutManager(this, 5);
        dragItemtouch.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(dragItemtouch);

        final DragViewAdapter adapter = new DragViewAdapter(this, helper, itemBeanList);
        dragItemtouch.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new DragViewAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ItemTouchHelperActivity.this, itemBeanList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
