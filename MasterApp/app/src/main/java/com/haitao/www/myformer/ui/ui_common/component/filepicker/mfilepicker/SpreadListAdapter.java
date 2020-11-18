package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.DataUtil;
import com.haitao.www.myformer.utils.DateFormat;

import java.io.File;
import java.util.List;

public class SpreadListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<File> fileList;
    private OnItemClickListener onItemClickListener;

    public SpreadListAdapter(Context context, List<File> fileList) {
        this.context = context;
        this.fileList = fileList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_file_item, parent, false);
        return new SpreadListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SpreadListHolder mHolder = (SpreadListHolder) holder;
        File file = fileList.get(position);
        mHolder.fileThumbnail.setImageURI(FetchFileData.getImageContentUri(context,file));
        mHolder.tvFileInfo.setText(DateFormat.getDateByStamp(file.lastModified()) + " - " + DataUtil.getFileSize(file.length()));
        mHolder.cbChoose.setVisibility(View.VISIBLE);
        mHolder.ivOpen.setVisibility(View.GONE);
        mHolder.tvFileName.setText(file.getName());
        initEvent(mHolder, position, file);
    }

    private void initEvent(SpreadListHolder mHolder, int position, File file) {
        mHolder.llFileTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(mHolder, position, file);
            }
        });
    }

    public void updateData(List<File> fileList) {
        if (DataUtil.isEmpty(fileList)) {
            this.fileList.clear();
        } else {
            this.fileList = fileList;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class SpreadListHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlFileItem;
        LinearLayout llFileTitle, llFileDesc;
        SimpleDraweeView fileThumbnail;
        TextView tvFileName, tvFileInfo;
        ImageView ivOpen;
        CheckBox cbChoose;

        public SpreadListHolder(View itemView) {
            super(itemView);
            rlFileItem = itemView.findViewById(R.id.rl_file_item);
            fileThumbnail = itemView.findViewById(R.id.file_thumbnail);
            llFileTitle = itemView.findViewById(R.id.ll_file_title);
            llFileDesc = itemView.findViewById(R.id.ll_file_desc);
            tvFileName = itemView.findViewById(R.id.tv_file_name);
            tvFileInfo = itemView.findViewById(R.id.tv_file_info);
            ivOpen = itemView.findViewById(R.id.iv_open);
            cbChoose = itemView.findViewById(R.id.cb_choose);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder mHolder, int position, File file);
    }
}
