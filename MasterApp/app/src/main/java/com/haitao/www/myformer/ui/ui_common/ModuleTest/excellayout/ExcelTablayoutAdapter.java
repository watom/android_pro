package com.haitao.www.myformer.ui.ui_common.ModuleTest.excellayout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.data.adapter.IBaseAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public class ExcelTablayoutAdapter extends IBaseAdapter {

    public ExcelTablayoutAdapter(Activity context) {
        super(context);
    }

    @Override
    protected int getItemResource() {
        return 0;
    }

    @Override
    protected void setItemData(int position, IBaseAdapter.ViewHolds viewHolds) {

    }

    @Override
    protected void findViews(IBaseAdapter.ViewHolds viewHolds, View convertView) {

    }

}
