package com.haitao.www.myformer.ui.ui_common.dialog.popupwindow_dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.haitao.www.myformer.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * emoji的Unicode编码
 * 网站：Emoji Unicode Tables，该网站上面给出了每个emoji表情的图片,描述,Unicode编码的对照表,点击表中每一项emoji就可以得到这个表情对应的Unicode，
 * 在java中的Unicode表示就是:”\ud83d\ude01”,该编码字符可以直接被Android的TextView和EditText控件识别成对应的emoji表情.
 * https://apps.timwhitlock.info/emoji/tables/unicode
 *
 * 添加表情
 * https://www.2cto.com/kf/201610/556687.html
 */
public class KeyboardFragment extends Fragment {
    private static final String Arguments = "position";
    private static final String Diverse = "Diverse";
    private static final String EmojiList = "emojiList";
    private Activity activity;
    private GridView gridview_emoji;
    private View view;
    private int type;

    /**
     * 创建页面并传入参数
     *
     * @param position 传入的参数
     * @param type
     * @return 为调用者返回一个fragment对象
     */
    public static KeyboardFragment getInstance(int position, String[] emojiList, int type) {
        KeyboardFragment fragment = new KeyboardFragment();
        Bundle args = new Bundle();
        args.putInt(Arguments, position);
        args.putInt(Diverse, type);
        args.putStringArray(EmojiList, emojiList);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_gridview_emoji, container, false);
        activity = getActivity();
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        gridview_emoji = view.findViewById(R.id.gridview_emoji);
        type = getArguments().getInt(Diverse);
        switch (type) {
            case 0:
                gridview_emoji.setNumColumns(7);
                break;
            case 1:
                gridview_emoji.setNumColumns(4);
                break;
        }
    }

    private void initData() {
        String[] emojiArray = getArguments().getStringArray(EmojiList);
        GridViewEmojiAdapter gridViewEmojiAdapter = new GridViewEmojiAdapter(activity, emojiArray, type);
        gridview_emoji.setAdapter(gridViewEmojiAdapter);
    }
}
