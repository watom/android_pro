package com.haitao.www.myformer.ui.ui_common.ModuleTest.excellayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.haitao.www.myformer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class ExcelTablayout extends AppCompatActivity {
    String[][] a = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redbag_log);
        initView();
        ListView listView = findViewById(R.id.listview_redbag_log);
//        listView.setAdapter(new ExcelTablayoutAdapter(this,getData(),new ViewHolder()));
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    public static final class ViewHolder {
        private TextView redbagPhoneNum;
        private TextView redbagLotterySerialcode;
        private TextView redbagWhenLottery;
    }

    private void initView() {
        TextView redbagPhoneNum = (TextView) findViewById(R.id.redbag_phone_num);
        TextView redbagLotterySerialcode = (TextView) findViewById(R.id.redbag_lottery_serialcode);
        TextView redbagWhenLottery = (TextView) findViewById(R.id.redbag_when_lottery);
    }


    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("phoneNum", a[i][0]);
            map.put("seris", a[i][1]);
            map.put("time", a[i][2]);
            list.add(map);
        }
        return list;
    }

}
