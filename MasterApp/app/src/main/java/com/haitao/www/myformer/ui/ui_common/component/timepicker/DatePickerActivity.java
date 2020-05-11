package com.haitao.www.myformer.ui.ui_common.component.timepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.ToastUtils;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView pickDateNo;
    private TextView pickDateOk;
    private DatePicker datePicker01;
    private TextView showDatePicker01;
    private int year, monthOfYear, dayOfMonth;
    private String stringDatePicker01;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        findViews();
        setOnClickEvent();
    }


    private void findViews() {
        pickDateNo = (TextView) findViewById(R.id.pick_date_no);
        pickDateOk = (TextView) findViewById(R.id.pick_date_ok);
        datePicker01 = (DatePicker) findViewById(R.id.datePicker_01);
        showDatePicker01 = (TextView) findViewById(R.id.show_datePicker_01);
        pickDateNo.setOnClickListener(this);
        pickDateOk.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pick_date_no:

                break;
            case R.id.pick_date_ok:
                if("选择的时间：".equals(showDatePicker01.getText())) {
                    showDatePicker01.append(stringDatePicker01);
                }else{
                    showDatePicker01.setText("选择的时间：");
                    showDatePicker01.append(stringDatePicker01);
                }
                break;
        }
    }

    private void setOnClickEvent() {
        Calendar nowCalendar = Calendar.getInstance();
        year = nowCalendar.get(Calendar.YEAR);
        monthOfYear = nowCalendar.get(Calendar.MONTH);
        dayOfMonth = nowCalendar.get(Calendar.DAY_OF_MONTH);

        datePicker01(year, monthOfYear, dayOfMonth);
        //在下面添加需要测试的逻辑
    }

    private void datePicker01(int year, int monthOfYear, int dayOfMonth) {
        stringDatePicker01 = year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
        showDatePicker01.append(stringDatePicker01);
        Calendar maxDate = Calendar.getInstance();
        Calendar minDate = Calendar.getInstance();
        maxDate.set(year, monthOfYear, dayOfMonth);
        minDate.set(year - 3, monthOfYear, dayOfMonth);
        datePicker01.setMaxDate(maxDate.getTimeInMillis());
        datePicker01.setMinDate(minDate.getTimeInMillis());
        datePicker01.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                ToastUtils.showToast(DatePickerActivity.this, "您选择的日期是：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日!");
                String str_month = monthOfYear + 1 <= 9 ? "0" + (monthOfYear + 1) : Integer.toString(monthOfYear+1);
                String str_day = dayOfMonth <= 9 ? "0" + dayOfMonth : Integer.toString(dayOfMonth);
                stringDatePicker01 = year + "年" + str_month + "月" + str_day + "日";
            }
        });
    }
}

