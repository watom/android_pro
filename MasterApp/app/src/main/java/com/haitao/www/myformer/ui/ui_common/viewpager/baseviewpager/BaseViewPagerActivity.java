package com.haitao.www.myformer.ui.ui_common.viewpager.baseviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haitao.www.myformer.R;

/**
 * 最基本的ViewPager
 * Created by Administrator on 2018/3/6 0006.
 */

public class BaseViewPagerActivity extends AppCompatActivity{
    private ViewPager baseViewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_viewpager);
        findViews();
        BaseViewpagerAdapter baseViewpagerAdapter = new BaseViewpagerAdapter();
        baseViewpager.setAdapter(baseViewpagerAdapter);
    }

    private void findViews() {
        baseViewpager = (ViewPager) findViewById(R.id.base_viewpager);
    }


    class BaseViewpagerAdapter extends PagerAdapter{

        /**
         * 返回可用的视图数量
         * 即：总共需要创建多少个页面
         *
         * @return
         */
        @Override
        public int getCount() {
            return 4;
        }

        /**
         * 确定容器中的一个view和object关联上，此方法是PagerAdapter正常工作所必需的。
         * view：容器中的一个页面
         * object：从ViewGroup返回的指定“key”的对象
         *
         * @return true view和oobject已经关联上了；false 未关联上
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 为指定位置创建子页面
         * 即：在相应的位置创建页面布局和数据
         *
         * @param container 容器
         * @param position  要实例化的页面位置。
         * @return 返回代表新页的对象。这不需要是视图，但可以是页面的其他容器。
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 准备显示的数据，一个简单的TextView
            TextView tv = new TextView(BaseViewPagerActivity.this);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(20);
            tv.setText("我是" + position + "号页面");

            // 添加到ViewPager容器
            container.addView(tv);

            // 返回填充的View对象
            return tv;
        }

        /**
         * 删除给定位置的页面。
         * 适配器负责从其容器中移除视图，但它必须确保在它从ViewGroup返回时完成该任务。
         *
         * @param container
         * @param position  要删除的页位置。
         * @param object    返回的相同对象。
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
