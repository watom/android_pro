package com.haitao.www.myformer.ui.ui_common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.haitao.www.myformer.R;

public class LoadingView extends LinearLayout {
    private ShapeView mShapeView;
    private View mShadowView;
    boolean isStopAnimation;
    float mTranslateDistance = 150.0f;
    long mAnimateTime = 500;
    public Shape mCurrentShape = Shape.CIRCLE;
    private Paint mPaint;
    private Path mPath;

    public enum Shape {
        CIRCLE,
        RECTANGLE,
        TRIANGLE,
    }

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_two, this, true);
        mShapeView = view.findViewById(R.id.shapeview);
        mShadowView = view.findViewById(R.id.shadowview);
    }

    public void showLoading(boolean isShow) {
//        this.setVisibility(isShow ? VISIBLE : GONE);
        this.isStopAnimation = !isShow;
        if (isShow) {
//            mShadowView.post(new Runnable() {
//                @Override
//                public void run() {
                    startDropAnimation();
//                }
//            });
        }
    }

    /**
     * 定义 下落动画 和 阴影缩小动画
     */
    ObjectAnimator mDropTranslateAnimation;
    ObjectAnimator mDropScaleAnimation;
    AnimatorSet mDropAnimatorSet;

    private void startDropAnimation() {
        if (isStopAnimation) {
            mDropTranslateAnimation = null;
            mDropScaleAnimation = null;
            mDropAnimatorSet = null;
            return;
        }

        //下落的动画
        mDropTranslateAnimation = ObjectAnimator.ofFloat(mShapeView, "translationY", 0, mTranslateDistance);
        mDropTranslateAnimation.setInterpolator(new AccelerateInterpolator());
        //阴影缩小动画
        mDropScaleAnimation = ObjectAnimator.ofFloat(mShadowView, "scaleX", 1f, 0.4f);
        mDropAnimatorSet = new AnimatorSet();
        mDropAnimatorSet.playTogether(mDropTranslateAnimation, mDropScaleAnimation);
        mDropAnimatorSet.setDuration(mAnimateTime);
        mDropAnimatorSet.setInterpolator(new AccelerateInterpolator());
        mDropAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mShapeView.changeShape();
                startUpAnimation();
            }
        });
        mDropAnimatorSet.start();
    }

    /**
     * 定义 弹起动画 和 阴影放大动画
     */
    ObjectAnimator mUpTranslateAnimation;
    ObjectAnimator mUpScaleAnimation;
    AnimatorSet mUpAnimatorSet;

    private void startUpAnimation() {
        if (isStopAnimation) {
            mUpTranslateAnimation = null;
            mUpScaleAnimation = null;
            mUpAnimatorSet = null;
            return;
        }

        //弹起的动画
        mUpTranslateAnimation = ObjectAnimator.ofFloat(mShapeView, "translationY", mTranslateDistance, 0);
        //阴影放大的动画
        mUpScaleAnimation = ObjectAnimator.ofFloat(mShadowView, "scaleX", 0.4f, 1f);
        mUpAnimatorSet = new AnimatorSet();
        mUpAnimatorSet.playTogether(mUpTranslateAnimation, mUpScaleAnimation);
        mUpAnimatorSet.setDuration(mAnimateTime);
        mUpAnimatorSet.setInterpolator(new DecelerateInterpolator());
        mUpAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                //弹起时旋转
                startRotateAnimation();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startDropAnimation();
            }
        });
        mUpAnimatorSet.start();
    }

    /**
     * 旋转动画
     */
    ObjectAnimator mRotateAnimation;
    private void startRotateAnimation() {
        if (isStopAnimation) {
            mRotateAnimation = null;
            return;
        }

        switch (mShapeView.getCurrentShape()) {
            case CIRCLE:
                break;
            case RECTANGLE:
                mRotateAnimation = ObjectAnimator.ofFloat(mShapeView, "rotation", 0, 180);
                break;
            case TRIANGLE:
                mRotateAnimation = ObjectAnimator.ofFloat(mShapeView, "rotation", 0, -120);
                break;
        }
        mRotateAnimation.setDuration(mAnimateTime);
        mRotateAnimation.start();
    }
}
