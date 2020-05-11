package com.haitao.www.myformer.logic.LogicTest;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.haitao.www.myformer.R;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

public class CountDownTimerActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnCountdowntimer;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdowntimer);
        findViews();
        initData();
    }

    private void initData() {
        countDownTimer();
    }

    private void countDownTimer() {
        btnCountdowntimer.setEnabled(false);
        new Handler().postDelayed(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                btnCountdowntimer.setEnabled(true);
                btnCountdowntimer.setBackgroundResource(R.drawable.btn_focused);
            }
        },10000);
        CountDownTimer countDownTimer = new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                String str=millisUntilFinished+"";
                btnCountdowntimer.setText(str.substring(0,1)+"秒");
            }

            @Override
            public void onFinish() {
                btnCountdowntimer.setText("确定");
            }
        };
        countDownTimer.start();
    }


    private void findViews() {
        btnCountdowntimer = (Button)findViewById( R.id.btn_countdowntimer );
        webView = (WebView)findViewById( R.id.webView );

        btnCountdowntimer.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == btnCountdowntimer ) {

        }
    }

}
