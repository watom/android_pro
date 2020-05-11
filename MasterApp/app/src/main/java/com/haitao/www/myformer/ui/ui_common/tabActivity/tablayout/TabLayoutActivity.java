package com.haitao.www.myformer.ui.ui_common.tabActivity.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.haitao.www.myformer.R;

/**
 * TabLayout使用
 * 参考： https://blog.csdn.net/weixin_39251617/article/details/79032641
 * 今日头条分类效果：https://blog.csdn.net/qq_30000411/article/details/53340192
 */
public class TabLayoutActivity extends AppCompatActivity {
    private String[] function = {"最基本的用法", "与ViewPager联合使用"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        findViews();
    }

    /**
     * 调用setupWithViewPager()方法，则使用TabLayout.addtab()方法无效，TabLayout会清除之前添加的所有tabs，并将根据Viewpager的页数添加Tab，Tab标题为对应页通过getPageTitle()返回的文本
     */
    private void findViews() {
        /**
         * 最基本的用法
         */
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("全部"));
        tabLayout.addTab(tabLayout.newTab().setText("待付款"));
        tabLayout.addTab(tabLayout.newTab().setText("待收货"));
        tabLayout.addTab(tabLayout.newTab().setText("已收货"));
        tabLayout.addTab(tabLayout.newTab().setText("已取消"));

        /**
         * 与ViewPager联合使用
         */
        TabLayout tabLayout02 = (TabLayout) findViewById(R.id.tab_layout02);
        ViewPager container_viewpager = (ViewPager) findViewById(R.id.container_viewpager);
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        container_viewpager.setAdapter(myFragmentAdapter);
        tabLayout02.setupWithViewPager(container_viewpager);
    }
}
