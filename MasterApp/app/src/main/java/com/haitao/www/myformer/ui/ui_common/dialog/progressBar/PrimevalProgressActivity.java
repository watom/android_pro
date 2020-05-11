package com.haitao.www.myformer.ui.ui_common.dialog.progressBar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.Log;

public class PrimevalProgressActivity extends Activity{
    private ProgressBar bar1;
    private ProgressBar bar2;
    private ProgressBar bar3;
    private ProgressBar bar4;
    private ProgressBar bar5;
    private ProgressBar bar6;
    private ProgressBar bar7;
    private ProgressBar bar8;
    private ProgressBar bar9;
    private Button progressBar_btn;
    private int i = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
    }

    private void findViews() {
        bar1 = (ProgressBar) findViewById(R.id.bar_1);
        bar2 = (ProgressBar) findViewById(R.id.bar_2);
        bar3 = (ProgressBar) findViewById(R.id.bar_3);
        bar4 = (ProgressBar) findViewById(R.id.bar_4);
        bar5 = (ProgressBar) findViewById(R.id.bar_5);
        bar6 = (ProgressBar) findViewById(R.id.bar_6);
        bar7 = (ProgressBar) findViewById(R.id.bar_7);
        bar8 = (ProgressBar) findViewById(R.id.bar_8);
        bar9 = (ProgressBar) findViewById(R.id.bar_9);
        progressBar_btn = (Button) findViewById(R.id.progressBar_btn);
        progressBar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("1111111111111111","wahwhwhahwhaww");
            }
        });
    }
}
