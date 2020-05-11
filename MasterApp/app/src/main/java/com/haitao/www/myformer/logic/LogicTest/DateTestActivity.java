package com.haitao.www.myformer.logic.LogicTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.haitao.www.myformer.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/24 0024.
 */

public class DateTestActivity extends AppCompatActivity {
    private TextView currTime,startTime,endTime;
    private TextView result;
    Date stratTimeDate = null, endTimeDate = null,currentDate=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_test);
        findViews();
        initData();
    }

    private void initData() {
        boolean endActivity = isEndActivity();
        if (endActivity) {
            result.setText("成功了");
        } else {
            result.setText("失败");
        }
        currTime.setText(currentDate.toString());
        startTime.setText(stratTimeDate.toString());
        endTime.setText(endTimeDate.toString());
    }

    private void findViews() {
        currTime = (TextView) findViewById(R.id.currTime);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        result = (TextView) findViewById(R.id.result);
    }

    public boolean isEndActivity() {
        String stratTime = "2017-11-12 00:00:00";
        String endTime = "2017-12-31 23:59:59";

        SimpleDateFormat simpleFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            currentDate= simpleFormat.parse(simpleFormat.format(new Date()));
            stratTimeDate = simpleFormat.parse(stratTime);
            endTimeDate = simpleFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar currentTime = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        currentTime.setTime(currentDate);
        start.setTime(stratTimeDate);
        end.setTime(endTimeDate);
        boolean b = (currentTime.after(start) && currentTime.before(end)) ? true : false;
        return b;
    }
}
