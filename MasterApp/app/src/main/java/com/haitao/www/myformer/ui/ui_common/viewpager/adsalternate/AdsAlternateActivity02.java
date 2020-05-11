package com.haitao.www.myformer.ui.ui_common.viewpager.adsalternate;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class AdsAlternateActivity02 extends AppCompatActivity {
    private ViewPager myviewpager;
    private TextView tvImageDesc;
    private LinearLayout roundPointGroup;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myviewpager);
        findViews();
        initDate();
    }

    private void initDate() {
        ArrayList<String> urls = new ArrayList<>();
        urls.add("http://seopic.699pic.com/photo/00005/5186.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50010/0719.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50009/9449.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50002/5923.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50001/9330.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50009/9191.jpg_wh1200.jpg");
        new MyLoopPagerAdapter(this,urls,myviewpager); //用构造方法设置数据适配器
    }

    private void findViews() {
        myviewpager = (ViewPager) findViewById(R.id.myviewpager);
        tvImageDesc = (TextView) findViewById(R.id.tv_image_desc);
        roundPointGroup = (LinearLayout) findViewById(R.id.round_point_group);
    }


    private class MyLoopPagerAdapter extends LoopPagerAdapter {
        private ViewGroup.LayoutParams layoutParams;

        public MyLoopPagerAdapter(Context context, ArrayList datas, ViewPager viewPager) {
            super(context, datas, viewPager);
        }

        @Override
        protected View getItemView(String data) {
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            ImageView imageView = new ImageView(mContext); //ViewPager需要展示页面资源
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageUtils.loadImage(mContext, data, imageView);
            return imageView;
        }
    }
}
