package com.haitao.www.myformer.utils;

import android.os.CountDownTimer;
import android.view.View;

public class CountDownTimerUtils extends CountDownTimer {
    private View view;
    private OnCountDownListener onCountDownListener;

    public CountDownTimerUtils(View view, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.view = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        onCountDownListener.onTickDispose(view, millisUntilFinished);

    }

    @Override
    public void onFinish() {
        onCountDownListener.onFinishDispose(view);

    }


    public void setCountDownListener(OnCountDownListener onCountDownListener) {
        this.onCountDownListener = onCountDownListener;
    }

    public interface OnCountDownListener {
        void onTickDispose(View view, long millisUntilFinished);

        void onFinishDispose(View view);
    }
}
