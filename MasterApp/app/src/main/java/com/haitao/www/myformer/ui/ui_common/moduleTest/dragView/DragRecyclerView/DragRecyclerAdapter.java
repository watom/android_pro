package com.haitao.www.myformer.ui.ui_common.moduleTest.dragView.DragRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.imageview.circle_imageview.CircularBeadImageView;

import java.util.List;

public class DragRecyclerAdapter extends RecyclerView.Adapter<DragRecyclerAdapter.ViewHolder> {

    private List<Subject> datas;
    private Context mContext;
    private LayoutInflater mLiLayoutInflater;

    public DragRecyclerAdapter(List<Subject> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
        this.mLiLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public DragRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLiLayoutInflater.inflate(R.layout.item_drag_gridview, parent, false));
    }

    @Override
    public void onBindViewHolder(DragRecyclerAdapter.ViewHolder holder, int position) {
        holder.grid_item_image.setImageResource(datas.get(position).getImg());
        holder.grid_item_title.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_drag_gridView;
        CircularBeadImageView grid_item_image;
        TextView grid_item_title;
//        LinearLayout ll_item, ll_hidden;

        public ViewHolder(View itemView) {
            super(itemView);
            ll_drag_gridView = itemView.findViewById(R.id.ll_drag_gridView);
            grid_item_image = itemView.findViewById(R.id.grid_item_image);
            grid_item_title = itemView.findViewById(R.id.grid_item_title);
//
//            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
//            ll_hidden = (LinearLayout) itemView.findViewById(R.id.ll_hidden);
        }
    }
}
