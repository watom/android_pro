package com.haitao.www.myformer.ui.ui_common.moduleTest.dragView.DragGridView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.watom.draggridviewlib.scrollrunner.OnItemMovedListener;
import com.watom.draggridviewlib.utils.DensityUtil;
import com.watom.draggridviewlib.widget.TagView;

import java.util.ArrayList;
import java.util.List;

public class DragGridViewAdapter extends BaseAdapter implements OnItemMovedListener, TagView.OnTagDeleteListener {
    private Context context;
    private List<String> mDatas = new ArrayList<>();

    public DragGridViewAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.mDatas.addAll(dataList);
    }

    private GridView mGridView;
    private boolean inEditMode = false;

    public void setData(List<String> dataList) {
        this.mDatas.clear();
        this.mDatas.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setInEditMode(boolean inEditMode) {
        this.inEditMode = inEditMode;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public String getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mGridView == null) {
            mGridView = (GridView) parent;
        }
        TagView textView;
        if (convertView == null) {
            textView = new TagView(context);
            convertView = textView;
            textView.setMaxLines(1);
            textView.setHeight(DensityUtil.dip2px(context, 40));
            int id = context.getResources().getIdentifier("selector_drag_gridview", "drawable", context.getPackageName());
            Drawable drawable = context.getResources().getDrawable(id);
            textView.setBackgroundDrawable(drawable);
            textView.setGravity(Gravity.CENTER);
        } else {
            textView = (TagView) convertView;
        }
        if (!isFixed(position)) {
            textView.showDeleteIcon(inEditMode);
        } else {
            textView.showDeleteIcon(false);
        }
        textView.setText(getItem(position));
        textView.setOnTagDeleteListener(this);
        return convertView;
    }

    @Override
    public void onItemMoved(int from, int to) {
        String s = mDatas.remove(from);
        mDatas.add(to, s);
    }

    @Override
    public boolean isFixed(int position) {
        //When postion==0,the item can not be dragged.
        if (position == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onDelete(View deleteView) {
        int index = mGridView.indexOfChild(deleteView);
        if (index <= 0) return;
        int position = index + mGridView.getFirstVisiblePosition();
        mDatas.remove(position);
        notifyDataSetChanged();
    }
}