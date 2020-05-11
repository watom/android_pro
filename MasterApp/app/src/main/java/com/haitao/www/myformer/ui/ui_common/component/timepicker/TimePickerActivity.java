package com.haitao.www.myformer.ui.ui_common.component.timepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.haitao.www.myformer.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/2/23 0023.
 */

public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener, Chronometer.OnChronometerTickListener, DatePicker.OnDateChangedListener {
    private TimePicker timePicker;
    private DatePicker datePicker;
    private TextView showTimePicker;
    private TextView showDatePicker;
    private TextView showDatePicker01;
    private Button btnDialog;
    private DatePickerDialog datePickerDialog;
    private Chronometer chronometer;
    private Button btn_start, btn_stop, btn_base, btn_format;
    private DatePicker datePicker02;
    EditText et_string2date;
    Button btn_string2date;
    TextView tv_string2date;

    // 定义5个记录当前时间的变量
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
        findViews();
        setCurrentSystemTime();
    }

    private void setCurrentSystemTime() {
        // 获取当前的年、月、日、小时、分钟
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TimePickerActivity.this.year = year;
                TimePickerActivity.this.month = monthOfYear;
                TimePickerActivity.this.day = dayOfMonth;
                // 显示当前日期、时间
                showDate(1, year, month, day, hour, minute);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                TimePickerActivity.this.hour = hourOfDay;
                TimePickerActivity.this.minute = minute;
                // 显示当前日期、时间
                showDate(2, year, month, day, hour, minute);
            }
        });
    }

    private void showDate(int which, int year, int month, int day, int hour, int minute) {
        if (which == 1) {
            showDatePicker.setText("您datePicker选择的时间：" + year + "年" + (month + 1) + "月" + day + "日  "
                    + hour + "时" + minute + "分");
        } else if (which == 2) {
            showTimePicker.setText("您timePicker选择的时间：" + year + "年" + (month + 1) + "月" + day + "日  "
                    + hour + "时" + minute + "分");
        } else if (which == 3) {
            showDatePicker01.setText("您timePicker选择的时间：" + year + "年" + (month + 1) + "月" + day + "日  "
                    + hour + "时" + minute + "分");
        }
    }

    private void findViews() {
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);
        showTimePicker = findViewById(R.id.show_timePicker);
        showDatePicker = findViewById(R.id.show_datePicker);
        showDatePicker01 = findViewById(R.id.show_datePicker01);
        btnDialog = findViewById(R.id.btn_dialog);
        btnDialog.setOnClickListener(this);
        datePicker02 = findViewById(R.id.datePicker_02);
        et_string2date = (EditText) findViewById(R.id.et_string2date);
        btn_string2date = (Button) findViewById(R.id.btn_string2date);
        tv_string2date = (TextView) findViewById(R.id.tv_string2date);


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker02.init(year, monthOfYear, dayOfMonth, this);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        btn_start = (Button) findViewById(R.id.btnStart);
        btn_stop = (Button) findViewById(R.id.btnStop);
        btn_base = (Button) findViewById(R.id.btnReset);
        btn_format = (Button) findViewById(R.id.btn_format);

        chronometer.setOnChronometerTickListener(this);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_base.setOnClickListener(this);
        btn_format.setOnClickListener(this);
        btn_string2date.setOnClickListener(this);


        TimePicker time_Picker = findViewById(R.id.time_Picker);
        time_Picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(TimePickerActivity.this, "您选择的时间是：" + hourOfDay + "时" + minute + "分!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // 显示当前日期、时间
                        showDate(3, year, month, dayOfMonth, 0, 0);
                    }
                };

                datePickerDialog = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, dateSetListener, 1949, 10, 1);
                datePickerDialog.show();
                break;
            case R.id.btnStart:
                chronometer.start();// 开始计时
                break;
            case R.id.btnStop:
                chronometer.stop();// 停止计时
                break;
            case R.id.btnReset:
                chronometer.setBase(SystemClock.elapsedRealtime());// 复位
                break;
            case R.id.btn_format:
                chronometer.setFormat("Time：%s");// 更改时间显示格式
                break;
            case R.id.btn_string2date:
                stringToDate();
                break;
        }
    }

    private void stringToDate() {
        String dateStr = et_string2date.getText().toString();
//        Date date=null;
//        // 将字符串转换为date
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            date = sdf.parse(str);
//            Log.e("stringToDate",""+date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        //将date转换为calendar
//        Calendar calendar= Calendar.getInstance();
//        calendar.setTime(date);
////
//        calendar.add(Calendar.YEAR, 1);
//        calendar.get(Calendar.DAY_OF_WEEK);
////       将calendar 转换为date
//        date= calengril.getTime();
////       将date转换为字符串
//        System.out.println(sdf.format(date)+"星期"+calendar.get(Calendar.DAY_OF_WEEK-1));

        //输入日期，转化为毫秒数，用DATE方法()
        /**
         * 先用SimpleDateFormat.parse() 方法将日期字符串转化为Date格式
         * 通过Date.getTime()方法，将其转化为毫秒数
         */
        long timeInMillis = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");//24小时制
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//24小时制
//      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");//12小时制
        try {
            timeInMillis = simpleDateFormat.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_string2date.setText("" + timeInMillis);
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        String time = chronometer.getText().toString();
        if (time.equals("00:00")) {
            Toast.makeText(TimePickerActivity.this, "时间到了~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(this, "您选择的日期是：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日!", Toast.LENGTH_SHORT).show();
    }
}
