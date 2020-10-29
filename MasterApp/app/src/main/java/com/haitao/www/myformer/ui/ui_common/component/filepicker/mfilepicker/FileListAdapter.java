package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.Context;
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

public class FileListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private File[] fileList;
    private OnItemClickListener onItemClickListener;

    public FileListAdapter(Context context, File[] fileList) {
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
        return new FileListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FileListHolder mHolder = (FileListHolder) holder;
        File file = fileList[position];
        if (file.isDirectory()) {
            int length = file.list().length;
            if (length < 1) {
                mHolder.fileThumbnail.setImageResource(R.drawable.directory_empty);
                mHolder.ivOpen.setVisibility(View.GONE);
            } else {
                mHolder.fileThumbnail.setImageResource(R.drawable.directory);
                mHolder.ivOpen.setVisibility(View.VISIBLE);
            }
            mHolder.cbChoose.setVisibility(View.GONE);
            mHolder.tvFileInfo.setText(DateFormat.getDateByStamp(file.lastModified()) + " - " + length + "项");
        } else {
            mHolder.fileThumbnail.setImageResource(FilePickerUtil.getResIdByName(file.getName()));
            mHolder.tvFileInfo.setText(DateFormat.getDateByStamp(file.lastModified()) + " - " + DataUtil.getFileSize(file.length()));
            mHolder.cbChoose.setVisibility(View.VISIBLE);
            mHolder.ivOpen.setVisibility(View.GONE);
        }
        mHolder.tvFileName.setText(file.getName());
        initEvent(mHolder, position, file);
    }

    private void initEvent(FileListHolder mHolder, int position, File file) {
        mHolder.rlFileItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(mHolder, position, file);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.length;
    }

    class FileListHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlFileItem;
        LinearLayout llFileDesc;
        SimpleDraweeView fileThumbnail;
        TextView tvFileName, tvFileInfo;
        ImageView ivOpen;
        CheckBox cbChoose;

        public FileListHolder(View itemView) {
            super(itemView);
            rlFileItem = itemView.findViewById(R.id.rl_file_item);
            fileThumbnail = itemView.findViewById(R.id.file_thumbnail);
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
