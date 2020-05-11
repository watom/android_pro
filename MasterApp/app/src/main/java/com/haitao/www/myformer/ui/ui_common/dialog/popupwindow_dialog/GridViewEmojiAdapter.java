package com.haitao.www.myformer.ui.ui_common.dialog.popupwindow_dialog;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.Util;

/**
 *
 *
 *
 *
 */
public class GridViewEmojiAdapter extends BaseAdapter {
    private String[] emojiArray;
    private Activity activity;
    private int whichType;
    private LayoutInflater layoutInflater;

    public GridViewEmojiAdapter(Activity activity, String[] emojiArray, int whichType) {
        this.emojiArray = emojiArray;
        this.activity = activity;
        this.whichType = whichType;
    }

    @Override
    public int getCount() {
        return null == emojiArray ? 30 : emojiArray.length;
    }

    @Override
    public Object getItem(int position) {
        return null == emojiArray ? "" : emojiArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        layoutInflater = LayoutInflater.from(activity);
        if (convertView == null) {
            switch (whichType) {
                case 0:
                    convertView = layoutInflater.inflate(R.layout.item_gridview_textview, null);
                    break;
                case 1:
                    convertView = layoutInflater.inflate(R.layout.item_gridview_imageview, null);
                    break;
            }

            holder = new ViewHolder(convertView, whichType);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        setViewData(holder, position);
        return convertView;
    }

    private void setViewData(ViewHolder holder, int position) {
        if (emojiArray == null) return;
        if (position < emojiArray.length - 1) {
            String emjioUnicode = emojiArray[position];
            if (!Util.isEmpty(emjioUnicode)) {
                holder.tv_emoji.setText(emjioUnicode);
            }
        } else if (position == emojiArray.length - 1) {
            holder.tv_emoji.setBackgroundResource(R.drawable.delete);
        }
    }

    class ViewHolder {
        TextView tv_emoji;
        ImageView img_content;

        public ViewHolder(View view, int whichType) {
            switch (whichType) {
                case 0:
                    tv_emoji = view.findViewById(R.id.tv_content);
                    break;
                case 1:
                    img_content = view.findViewById(R.id.img_content);
                    break;
            }
        }

    }

}
