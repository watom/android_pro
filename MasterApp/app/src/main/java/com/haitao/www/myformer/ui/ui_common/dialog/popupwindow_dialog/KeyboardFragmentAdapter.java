package com.haitao.www.myformer.ui.ui_common.dialog.popupwindow_dialog;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.ToastUtils;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class KeyboardFragmentAdapter extends FragmentPagerAdapter {
    private Activity activity;
    private int lastPosition;
    private int type;
    private int pageCount;
    String[] tabs = {"Emoji", "表情", "斗图", "自定义", "表情", "斗图", "自定义", "表情", "斗图", "自定义", "表情"};

    public KeyboardFragmentAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        ArrayList<String[]> pagerList = getEmojiPagerList(getEmojis());
        if (position < pagerList.size()) {
            String[] pageEmojis = pagerList.get(position);
            return KeyboardFragment.getInstance(position, pageEmojis, 0); //type=0 Emoji类型
        } else {
            //加载新的表情页面
            return KeyboardFragment.getInstance(position, null, 1); //type=1 图片类型
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    //根据页面的个数动态添加圆点指示器
    public void setupWithPagerPoint(ViewPager keyboardContainerViewpager, final LinearLayout roundPointGroup) {
        ImageView point = null;
        for (int i = 0; i < getCount(); i++) {
            // 添加指示点
            point = new ImageView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 20;
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.lunbotu_round_point_selector);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            roundPointGroup.addView(point);
        }

        keyboardContainerViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                roundPointGroup.getChildAt(position).setEnabled(true);
                roundPointGroup.getChildAt(lastPosition).setEnabled(false); // 把上一个点设为false
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////  上面是设置每个页面，同时给每个页面发送它的数据 /////////////////
    //////////////////  下面的为上面提供是需要展示的数据             /////////////////
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * 从assets目录下获取所有表情
     *
     * @return
     */
    public String[] getEmojis() {
        BufferedReader br = null;
        String emojis[] = null;
        try {
            InputStream is = activity.getAssets().open("Emoji.json");
            StringBuffer sb = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (null != (line = br.readLine())) {
                sb.append(line).append("\r\n");
            }
            JSONArray emojiArray = new JSONArray(sb.toString());
            if (null != emojiArray && emojiArray.length() > 0) {
                emojis = new String[emojiArray.length()];
                for (int i = 0; i < emojiArray.length(); i++) {
                    emojis[i] = emojiArray.optString(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return emojis;
    }

    /**
     * 获取所有表情GridView页面的集合
     * @param emojis 所有Emoji表情的Unicode数组
     * @return 分成20个一组的List
     */
    public ArrayList<String[]> getEmojiPagerList(String[] emojis) {
        ArrayList<String[]> pageEmojisList = null;
        String[] eachPageEmojis = null;
        if (null != emojis && emojis.length > 0) {
            pageCount = emojis.length / 20; //数组总数/每页要显示的数量=共n页表情
            pageEmojisList = new ArrayList();
            for (int i = 0; i < pageCount; i++) {
                //每次复制取出20个元素,索引变化为:0-19,20-39,40-59...
                //注意：每页最后一个eachPageEmojis[20],也就是第21个是删除按钮,用特殊字符串表示
                eachPageEmojis = Arrays.copyOfRange(emojis, i * 20, (i + 1) * 20 + 1); //copyOfRange：to这个值不包含
                pageEmojisList.add(eachPageEmojis);
            }
        }
        return pageEmojisList;
    }

    /**
     * 获取所有表情GridView页面的集合
     * @param emojis 所有Emoji表情的Unicode数组
     * @return 分成20个一组的List
     */
    public ArrayList<String[]> getImagePagerList(String[] emojis) {
        ArrayList<String[]> pageEmojisList = null;
        String[] eachPageEmojis = null;
        if (null != emojis && emojis.length > 0) {
            pageCount = emojis.length / 20; //数组总数/每页要显示的数量=共n页表情
            pageEmojisList = new ArrayList();
            for (int i = 0; i < pageCount; i++) {
                //每次复制取出20个元素,索引变化为:0-19,20-39,40-59...
                //注意：每页最后一个eachPageEmojis[20],也就是第21个是删除按钮,用特殊字符串表示
                eachPageEmojis = Arrays.copyOfRange(emojis, i * 20, (i + 1) * 20 + 1); //copyOfRange：to这个值不包含
                pageEmojisList.add(eachPageEmojis);
            }
        }
        return pageEmojisList;
    }
}
