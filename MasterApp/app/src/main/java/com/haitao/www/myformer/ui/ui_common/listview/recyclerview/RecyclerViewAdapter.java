package com.haitao.www.myformer.ui.ui_common.listview.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitao.www.myformer.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<String> mData;

    //构造初始化数据
    public RecyclerViewAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.mData = data;
    }

    //更新View界面的数据
    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    //相当于getView方法中创建View和ViewHolder
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        // 实例化viewholder
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    //相当于getView绑定数据,根据位置得到对应的数据，然后绑定。
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
//        holder.itemRecyclerIvIcon.setImageDrawable(getDrawable(R.drawable.common_icon));//这里给item设置了一个固定的图片
        holder.itemRecyclerTvName.setText(mData.get(position));
    }

    //得到需要显示的总条数
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    // 添加数据
    public void addData(int position, String data) {
        if (data != null) return;
        mData = new ArrayList<>();
        mData.add(position, data);
        notifyItemInserted(position);
    }

    // 移除数据
    public void removeData(int position) {
        if (mData == null || mData.isEmpty()) return;
        mData.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemRecyclerIvIcon;
        private TextView itemRecyclerTvName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemRecyclerIvIcon = (ImageView) itemView.findViewById(R.id.item_recycler_iv_icon);
            itemRecyclerTvName = (TextView) itemView.findViewById(R.id.item_recycler_tv_name);

            //自定义设置每个Item中的任何控件的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        //getLayoutPosition()根据最新的布局传递返回ViewHolder的位置。
                        onItemClickListener.onItemClick(v, getLayoutPosition());
                        onItemClickListener.onItemLongClick(v, getLayoutPosition());
                    }
                }
            });
            itemRecyclerIvIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, getLayoutPosition());
                    onItemClickListener.onItemLongClick(v, getLayoutPosition());
                }
            });
        }
    }

    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener {

        /**
         * 当RecyclerView某个被点击的时候回调
         *
         * @param view     点击item的视图
         * @param position 点击得到的位置
         */
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
