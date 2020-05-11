package com.haitao.www.myformer.ui.ui_common.ModuleTest.timeLine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.haitao.www.myformer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class TimeLineActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TraceData> traceList = new ArrayList<>(10);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);
        findView();
        initData();
    }
    private void findView() {
        recyclerView = findViewById(R.id.rvTrace);
    }
    private void initData() {
        // 模拟一些假的数据
        traceList.add(new TraceData("2016-05-25 17:48:00", "[西安市] [西安和平五部]的派件已签收 感谢使用中通快递,期待再次为您服务!"));
        traceList.add(new TraceData("2016-05-25 14:13:00", "[西安市] [西安和平五部]的交通大学代理点正在派件 电话:18040xxxxxx 请保持电话畅通、耐心等待"));
        traceList.add(new TraceData("2016-05-25 13:01:04", "[西安市] 快件到达 [西安和平五部]"));
        traceList.add(new TraceData("2016-05-25 12:19:47", "[西安市] 快件离开 [西安中转]已发往[西安和平五部]"));
        traceList.add(new TraceData("2016-05-25 11:12:44", "[西安市] 快件到达 [西安中转]"));
        traceList.add(new TraceData("2016-05-24 03:12:12", "[嘉兴市] 快件离开 [杭州中转部]已发往[西安中转]"));
        traceList.add(new TraceData("2016-05-23 21:06:46", "[杭州市] 快件到达 [杭州汽运部]"));
        traceList.add(new TraceData("2016-05-23 18:59:41", "[杭州市] 快件离开 [杭州乔司区]已发往[西安]"));
        traceList.add(new TraceData("2016-05-23 18:35:32", "[杭州市] [杭州乔司区]的市场部已收件 电话:18358xxxxxx"));
        traceList.add(new TraceData("2016-05-22 03:12:12", "[嘉兴市] 快件离开 [杭州中转部]已发往[西安中转]"));
        traceList.add(new TraceData("2016-05-21 21:06:46", "[杭州市] 快件到达 [杭州汽运部]"));
        traceList.add(new TraceData("2016-05-21 18:59:41", "[杭州市] 快件离开 [杭州乔司区]已发往[西安]"));
        traceList.add(new TraceData("2016-05-20 18:35:32", "[杭州市] [杭州乔司区]的市场部已收件 电话:18358xxxxxx"));
        TimeLineAdapter adapter = new TimeLineAdapter(this, traceList);
        //布局管理器用于确定RecyclerView中Item的展示方式以及决定何时复用已经不可见的Item，避免重复创建以及执行高成本的findViewById()方法。
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
