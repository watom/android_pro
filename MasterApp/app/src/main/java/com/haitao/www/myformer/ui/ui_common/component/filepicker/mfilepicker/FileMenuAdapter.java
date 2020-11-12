package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.model.global.SimpleBean;

import java.util.List;

public class FileMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SimpleBean> menuList;
    private OnClickItemListener onClickItemListener;

    public interface OnClickItemListener {
        void onClick(SimpleBean bean);
    }

    public FileMenuAdapter(Context context, List<SimpleBean> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_simple, parent, false);
        return new FileMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SimpleBean simpleBean = menuList.get(position);
        FileMenuViewHolder mViewHolder = (FileMenuViewHolder) holder;

        mViewHolder.imageviewItem.setImageResource(simpleBean.getImageResId());
        mViewHolder.textviewItem.setText(simpleBean.getImageText());
        initEvent(mViewHolder.llItem, simpleBean);
    }

    private void initEvent(LinearLayout llBottomItem, SimpleBean bean) {
        llBottomItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemListener != null) {
                    onClickItemListener.onClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class FileMenuViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llItem;
        ImageView imageviewItem;
        TextView textviewItem, textviewItemNum;

        public FileMenuViewHolder(View itemView) {
            super(itemView);
            llItem = itemView.findViewById(R.id.ll_item);
            imageviewItem = itemView.findViewById(R.id.imageview_item);
            textviewItem = itemView.findViewById(R.id.textview_item_title);
            textviewItemNum = itemView.findViewById(R.id.textview_item_num);
        }
    }
}
