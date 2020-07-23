package com.haitao.www.myformer.ui.ui_common.moduleTest.dragView.DragItemTouchHelper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.R;

import java.util.List;

/**
 * 拖拽排序 + 增删
 */
public class DragViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemMoveListener {
    // 控制
    public static final int TYPE_CONTROL = 0;
    // 内容
    public static final int TYPE_CONTENT = 1;

    private static final long ANIM_TIME = 360L;

    // touch 点击开始时间
    private long startTime;
    // touch 间隔时间  用于分辨是否是 "点击"
    private static final long SPACE_TIME = 100;

    private LayoutInflater mInflater;
    private ItemTouchHelper mItemTouchHelper;

    // 是否为 编辑 模式
    private boolean isEditMode;

    private List<ItemBean> dataList;

    // 我的频道点击事件
    private OnMyChannelItemClickListener mChannelItemClickListener;

    public DragViewAdapter(Context context, ItemTouchHelper helper, List<ItemBean> dataList) {
        this.mInflater = LayoutInflater.from(context);
        this.mItemTouchHelper = helper;
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == dataList.size() - 1) {
            return TYPE_CONTROL;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view;
        view = mInflater.inflate(R.layout.item_drag_view, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        switch (viewType) {
            case TYPE_CONTROL:
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isEditMode) {
                            startEditMode((RecyclerView) parent);
                            holder.textView.setText("完成");
                        } else {
                            cancelEditMode((RecyclerView) parent);
                            holder.textView.setText("编辑");
                        }
                        holder.imgEdit.setVisibility(View.GONE);
                    }
                });
                return holder;

            case TYPE_CONTENT:
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        int position = holder.getAdapterPosition();
                        Log.i("测试", "onClick: " + position);
                        if (isEditMode) {
                        }
                    }
                });

                holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(final View v) {
                        if (!isEditMode) {
                            RecyclerView recyclerView = ((RecyclerView) parent);
                            startEditMode(recyclerView);

                            // header 按钮文字 改成 "完成"
                            View view = recyclerView.getChildAt(dataList.size()-1);
                            if (view == recyclerView.getLayoutManager().findViewByPosition(dataList.size()-1)) {
                                TextView tvBtnEdit =  view.findViewById(R.id.tv_drag_itemtouch);
                                tvBtnEdit.setText("完成");
                            }
                        }

                        mItemTouchHelper.startDrag(holder);
                        return true;
                    }
                });

                holder.textView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (isEditMode) {
                            switch (MotionEventCompat.getActionMasked(event)) {
                                case MotionEvent.ACTION_DOWN:
                                    startTime = System.currentTimeMillis();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (System.currentTimeMillis() - startTime > SPACE_TIME) {
                                        mItemTouchHelper.startDrag(holder);
                                    }
                                    break;
                                case MotionEvent.ACTION_CANCEL:
                                case MotionEvent.ACTION_UP:
                                    startTime = 0;
                                    break;
                            }

                        }
                        return false;
                    }
                });
                return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

            MyViewHolder myHolder = (MyViewHolder) holder;
            myHolder.textView.setText(dataList.get(position).getName());
            if (isEditMode) {
                myHolder.imgEdit.setVisibility(View.VISIBLE);
            } else {
                myHolder.imgEdit.setVisibility(View.INVISIBLE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
//        ItemBean item = mMyChannelItems.get(fromPosition - COUNT_PRE_MY_HEADER);
//        dataList.remove(fromPosition - COUNT_PRE_MY_HEADER);
//        dataList.add(toPosition - COUNT_PRE_MY_HEADER, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 开启编辑模式
     *
     * @param parent
     */
    private void startEditMode(RecyclerView parent) {
        isEditMode = true;

        int visibleChildCount = parent.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = parent.getChildAt(i);
            ImageView imgEdit =  view.findViewById(R.id.image_drag_itemtouch);
            if (imgEdit != null) {
                imgEdit.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 完成编辑模式
     *
     * @param parent
     */
    private void cancelEditMode(RecyclerView parent) {
        isEditMode = false;

        int visibleChildCount = parent.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = parent.getChildAt(i);
            ImageView imgEdit =  view.findViewById(R.id.image_drag_itemtouch);
            if (imgEdit != null) {
                imgEdit.setVisibility(View.INVISIBLE);
            }
        }
    }


    interface OnMyChannelItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnMyChannelItemClickListener(OnMyChannelItemClickListener listener) {
        this.mChannelItemClickListener = listener;
    }

    /**
     * 我的频道
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener {
        private TextView textView;
        private ImageView imgEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.tv_drag_itemtouch);
            imgEdit =  itemView.findViewById(R.id.image_drag_itemtouch);
        }

        /**
         * item 被选中时
         */
        @Override
        public void onItemSelected() {
            Log.i("www", "onItemSelected:" + textView.getText());
        }

        /**
         * item 取消选中时
         */
        @Override
        public void onItemFinish() {
            Log.i("www", "onItemSelected:" + textView.getText());
        }
    }

}
