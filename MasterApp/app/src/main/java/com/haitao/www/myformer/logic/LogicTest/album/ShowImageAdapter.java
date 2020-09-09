package com.haitao.www.myformer.logic.LogicTest.album;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haitao.www.myformer.R;

import java.util.ArrayList;
import java.util.List;

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.ViewHolder>{
    /**
     * 图片路径url
     */
    private List<String> mImageUrlList=new ArrayList<>();
    private Context mContext;
    /**
     * 最大选择图片的数量
     */
    public static final int MAX_NUMBER = 9;
    private OnPickerListener mOnPickerListener;

    public ShowImageAdapter(Context context) {
        this.mContext = context;
    }

    public void setImageUrlList(List<String> imageUrlList){
        this.mImageUrlList=imageUrlList;
        this.notifyDataSetChanged();
    }

    public void setOnPickerListener(OnPickerListener onPickerListener) {
        mOnPickerListener = onPickerListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_item, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //判断位置显示的内容
        // 如果当前图片的数量是0，或者大于所选择图片的数量则显示一个带选择图片的image，同时隐藏删除按钮
        if (mImageUrlList.size() == 0 || mImageUrlList.size() == position) {
            holder.mImageViewDel.setVisibility(View.GONE);
            holder.mImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.ic_launcher_01));
        } else {
            //否则每个图片直接显示删除按钮，并加载显示图片
            holder.mImageViewDel.setVisibility(View.VISIBLE);
            //glide加载图片
            Glide.with(mContext)
                    .load(mImageUrlList.get(position))
                    .placeholder(R.mipmap.ic_launcher_01)
                    .into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mImageUrlList.size() < MAX_NUMBER ? mImageUrlList.size() + 1 : mImageUrlList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView, mImageViewDel;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewDel = (ImageView) itemView.findViewById(R.id.iv_del);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_pic);
            mImageViewDel.setOnClickListener(this);
            mImageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_pic:
                    mOnPickerListener.onPicker(getLayoutPosition());
                    break;
                case R.id.iv_del:
                    mImageUrlList.remove(getLayoutPosition());
                    notifyItemRemoved(getLayoutPosition());
                    break;
                default:
                    break;
            }

        }

    }

    /**
     * recyclerView设置的监听接口
     */
    public interface OnPickerListener{
        void onPicker(int position);
    }
}
