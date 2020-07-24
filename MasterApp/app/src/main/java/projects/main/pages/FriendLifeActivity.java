package projects.main.pages;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.haitao.www.myformer.R;

import cn.com.weather.cj.widget.view.HorizonView;
import cn.com.weather.cj.widget.view.LeftLargeView;
import cn.com.weather.cj.widget.view.RightLargeView;
import cn.com.weather.cj.widget.view.VerticalView;

public class FriendLifeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlife);
        initView();
    }

    private void initView() {
        //横向布局
        HorizonView horizonView = findViewById(R.id.horizon_view);
        //左侧大布局
        LeftLargeView llView = findViewById(R.id.ll_view);
        //右侧大布局
        RightLargeView rlView = findViewById(R.id.rl_view);
        //竖向布局
        VerticalView verticalView = findViewById(R.id.vertical_view);

        //设置取消默认背景
        horizonView.setDefaultBack(false);
        //设置文字颜色
        horizonView.setViewTextColor(Color.BLACK);

        //设置布局的背景圆角角度，颜色，边框宽度，边框颜色
        llView.setStroke(5, Color.parseColor("#313a44"), 1, Color.BLACK);


        //设置控件内边距
        horizonView.setViewPadding(0, 0, 0, 0);
        rlView.setViewPadding(0, 0, 0, 0);
        llView.setViewPadding(0, 0, 0, 0);
        verticalView.setViewPadding(0, 0, 0, 0);


        //设置布局相对位置，默认居中 可选：Gravity.LEFT | Gravity.CENTER_VERTICAL | Gravity.RIGHT | Gravity.CENTER;
        horizonView.setViewGravity("center");

        //添加地址文字到布局
        horizonView.addLocation(16, Color.WHITE);
        //添加天气文字到布局
        horizonView.addCond(16, Color.WHITE);
        //添加天气图标到布局
        horizonView.addWeatherIcon(20);
        //添加温度文字到布局
        horizonView.addTemp(16, Color.WHITE);
        //添加风力文字到布局
        horizonView.addWind(16, Color.WHITE);
        //添加风向图标到布局
        horizonView.addWindIcon(20);
        //添加AQI文字到布局
        horizonView.addAqiText(16, Color.WHITE);
        //添加空气质量文字到布局
        horizonView.addAqiQlty(16);
        //添加空气质量数字到布局
        horizonView.addAqiNum(16);
        //添加预警图标到布局
        horizonView.addAlarmIcon(20);
        //添加预警文字到布局
        horizonView.addAlarmTxt(16);


        /**
         * 左侧大布局和右侧大布局添加组件时，第一个参数需要传递想要把组件添加到的父布局，
         * 例如把地址文字添加到左上布局： rlView.addLocation(leftTopLayout, textSize, textColor);
         */

        //获取左侧大布局
        LinearLayout leftLayout = llView.getLeftLayout();
        //获取右上布局
        LinearLayout rightTopLayout = llView.getRightTopLayout();
        //获取右下布局
        LinearLayout rightBottomLayout = llView.getRightBottomLayout();

        //第一个参数为需要加入的布局
        //第二个参数为文字大小，单位：sp
        //第三个参数为文字颜色，默认白色
        //添加温度描述到左侧大布局
        llView.addTemp(leftLayout, 40, Color.WHITE);
        //添加温度图标到右上布局，第二个参数为图标宽高（宽高1：1，单位：dp）
        llView.addWeatherIcon(rightTopLayout, 14);
        //添加预警图标到右上布局
        llView.addAlarmIcon(rightTopLayout, 14);
        //添加预警描述到右上布局
        llView.addAlarmTxt(rightTopLayout, 14);
        //添加文字AQI到右上布局
        llView.addAqiText(rightTopLayout, 14);
        //添加空气质量到右上布局
        llView.addAqiQlty(rightTopLayout, 14);
        //添加空气质量数值到右上布局
        llView.addAqiNum(rightTopLayout, 14);
        //添加地址信息到右上布局
        llView.addLocation(rightTopLayout, 14, Color.WHITE);
        //添加天气描述到右下布局
        llView.addCond(rightBottomLayout, 14, Color.WHITE);
        //添加风向图标到右下布局
        llView.addWindIcon(rightBottomLayout, 14);
        //添加风力描述到右下布局
        llView.addWind(rightBottomLayout, 14, Color.WHITE);
        //设置控件的对齐方式，默认居中
        llView.setViewGravity("left");


        //获取右侧大布局
        LinearLayout rightLayout = rlView.getRightLayout();
        //获取左上布局
        LinearLayout leftTopLayout = rlView.getLeftTopLayout();
        //获取左下布局
        LinearLayout leftBottomLayout = rlView.getLeftBottomLayout();

        //第一个参数为需要加入的布局
        //第二个参数为文字大小，单位：sp
        //第三个参数为文字颜色，默认白色
        rlView.addLocation(leftTopLayout, 16,  Color.WHITE);
        rlView.addAqiText(leftTopLayout, 14);
        rlView.addAqiQlty(leftTopLayout, 14);
        rlView.addAqiNum(leftTopLayout, 14);
        rlView.addAlarmIcon(leftTopLayout, 14);
        rlView.addAlarmTxt(leftTopLayout, 14);
        rlView.addWeatherIcon(leftTopLayout, 14);
        rlView.addWindIcon(leftBottomLayout, 14);
        rlView.addWind(leftBottomLayout, 14, Color.WHITE);
        rlView.addCond(leftBottomLayout, 14, Color.WHITE);

        rlView.addTemp(rightLayout, 40, Color.WHITE);


        verticalView.addLocation(14, Color.WHITE);
        verticalView.addTemp(14, Color.WHITE);
        verticalView.addWeatherIcon(14);
        verticalView.addCond(14, Color.WHITE);
        verticalView.addWindIcon(14);
        verticalView.addWind(14, Color.WHITE);
        verticalView.addAqiText(14, Color.WHITE);
        verticalView.addAqiQlty(14);
        verticalView.addAqiNum(14);
        verticalView.addAlarmIcon(14);
        verticalView.addAlarmTxt(14);

        //显示控件
        horizonView.show();
        verticalView.show();
        rlView.show();
        llView.show();
    }
}
