package com.haitao.www.myformer.ui.ui_common.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.widget.LoadingView;

/**
 * 弹跳动画
 */
public class BounceActivity extends Activity {
    private LoadingView mShapeView;
    private View mShadowView;
    boolean isStopAnimation = false;
    float mTranslateDistance =150.0f;
    long mAnimateTime = 500;
    LoadingView loadingView;
    Button loadingStart;
    Button loadingEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog_two_test);
        initView();
        initEvent();
    }

    private void initView() {
        loadingStart = findViewById(R.id.loading_start);
        loadingEnd = findViewById(R.id.loading_end);
        loadingView = findViewById(R.id.loading_view);
    }

    private void initEvent() {
        loadingStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingView.showLoading(true);
            }
        });
        loadingEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingView.showLoading(false);
            }
        });
    }
}
