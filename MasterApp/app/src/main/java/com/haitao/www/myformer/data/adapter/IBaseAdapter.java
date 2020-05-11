package com.haitao.www.myformer.data.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.haitao.www.myformer.utils.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public abstract class IBaseAdapter<T> extends BaseAdapter {
    protected Activity context;
    public LayoutInflater mLayoutInflater;
    protected  List<T> dataList = new ArrayList<>();

    public IBaseAdapter(Activity context) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    //--------------------ListView的核心方法--------------------//
    /**
     * 根据设置数据的数量，决定要加载的Item的数量。即getView方法要循环绘制多少个Item
     * @return 返回需要在适配器中加载多少Item
     */
    @Override
    public int getCount() {
        if(dataList.isEmpty()){
            Log.d(context.getLocalClassName(),"填充的数据为空");
            return 0;
        }
        return dataList.size();
    }

    /**
     * 获取与数据集中指定位置关联的数据项。
     * @param position
     * @return
     */
    @Override
    public T getItem(int position) {
        if (getCount() <= 0 || position >= getCount())  {
            return null;
        }
        return dataList.get(position);
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 在界面上绘制一条Item的view，并显示一条数据.
     * @param position 要显示的item的当前位置position
     * @param convertView 能重复利用的Item对象
     * @param parent
     * @return 返回一条Item
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View viewHolds = setViewHold();
        ViewHolds viewHolds = new ViewHolds();
        if (convertView == null) {
            //把item的布局填充到ListView条目的convertView中
            convertView = mLayoutInflater.inflate(getItemResource(), null);
            findViews(viewHolds,convertView);
            convertView.setTag(viewHolds);
        } else {
            viewHolds = (ViewHolds) convertView.getTag();
        }
        setItemData(position, viewHolds);
        return convertView;
    }

    /**
     * 找到要填充的Item的布局文件的ID
     * @return 返回一个这个布局文件的在R文件中的ID号
     */
    protected abstract int getItemResource();

    /**
     * 给所有Item的其中一个布局的组件填充设置数据
     * @param position 就相当于某一条Item的位置
     * @param viewHolds 可以拿到的Item中的所有组件的对象
     */
    protected abstract void setItemData(int position, ViewHolds viewHolds);

    /**
     * 从已经填充进convertView的布局文件中拿到每个需要的组件，并把这些组件赋给viewHolds的每个组件变量
     * @param viewHolds
     * @param convertView
     */
    protected abstract void findViews(ViewHolds viewHolds, View convertView);

//    public abstract View setViewHold();
    public   class  ViewHolds {

    }
}
