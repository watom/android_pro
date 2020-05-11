package com.haitao.www.myformer.ui.ui_common.viewpager.adsalternate;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 封装一个ViewPager的数据适配器。
 * 当前页为第一页/最后一页的位置，及时调整设置当前页为实际对应的页面来达到无限循环。
 * Created by Administrator on 2018/3/7 0007.
 */
public abstract class LoopPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private int currentPosition = 0; // 当前页面

    protected ArrayList<View> views;
    protected Context mContext;
    protected ViewPager mViewPager;
    private boolean isContinue = false;
    private Runnable mRunnable;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
//                // 让viewPager 滑动到下一页
//                if (mViewPager != null && isContinue == true) {
//                int currentItem = mViewPager.getCurrentItem();
//                int itemsCount = getCount();
//                if ((currentItem + 1) < itemsCount) {
//                    mViewPager.setCurrentItem(currentItem + 1, true);
////                    roundPointGroup.getChildAt(currentItem+1).setEnabled(true);
////                    roundPointGroup.getChildAt(currentItem).setEnabled(false);
//                } else {
//                    mViewPager.setCurrentItem(0, false);
//                }
//                    onPageScrollStateChanged(1);
//                } else {
//
//                }
                scrollPage();
            }
        }
    };




    /**
     * 构造初始化数据
     *
     * @param context   上下文
     * @param datas     需要加载的资源的集合
     * @param viewPager ViewPager控件
     */
    public LoopPagerAdapter(Context context, ArrayList<String> datas, ViewPager viewPager) {
        this.views = new ArrayList<>(); //新建的集合，用于存放View数据
        this.mContext = context;
        this.mViewPager = viewPager;

        // 对原有的数据结构进行调整，增加两条：123-->31231
        if (datas.size() > 1) {
            // 在第一页位置添加原来数据的最后一页数据
            datas.add(0, datas.get(datas.size() - 1));
            // 在最后一页位置添加原来数据的第一页(经过上行的添加已经是第二页了)数据
            datas.add(datas.size(), datas.get(1));//最好使用默认的添加：datas.add(datas.get(1))
        }
        for (String data : datas) {
            views.add(getItemView(data));
        }

        mViewPager.setAdapter(this);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(1, true); //设置当前页为第二页，实质为需要展示第一个页面
        startScroll();
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * 记录当前页面
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
    }

    /**
     * 根据记录的当前页面位置，跳转至实质对应的页面
     *
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state != ViewPager.SCROLL_STATE_IDLE) {
            //若viewpager滑动未停止,取消时间定时器
            stopScroll();
        }else{
            startScroll();
        }
    }

    private void scrollPage() {
        //若当前为第一张，设置页面为倒数第二张
//        if (currentPosition == 0) {
//            mViewPager.setCurrentItem(views.size() - 2, true);
//        } else if (currentPosition == views.size() - 1) {
//            // 若当前为倒数第一张，设置页面为第二张
//            mViewPager.setCurrentItem(1, true);
//        } else {
//            int i = mViewPager.getCurrentItem();
//            mViewPager.setCurrentItem(++i, true);
//        }
        currentPosition=mViewPager.getCurrentItem();
        if (currentPosition == views.size() - 1) {
            // 若当前为倒数第一张，设置页面为第二张
            mViewPager.setCurrentItem(0, true);
        } else {
            mViewPager.setCurrentItem(++currentPosition, true);
        }
    }

    /**
     * 新建一个抽象方法，来获取每个页面需要的数据。
     *
     * @param data 如：获取图片资源的网络地址等等
     * @return 返回一个带有数据的View
     */
    protected abstract View getItemView(String data);

    ///////////////////////////////////////////////////
    ///////////////     基于定时器Timer    /////////////
    ///////////////////////////////////////////////////
    private Timer timer = new Timer();
    private TimerTask timerTask;

    public void startScroll() {
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        timer.schedule(this.timerTask, 3000, 3000);
    }

    public void stopScroll() {
        timerTask.cancel();
    }
}
