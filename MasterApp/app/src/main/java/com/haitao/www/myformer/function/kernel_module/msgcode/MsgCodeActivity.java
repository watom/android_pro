package com.haitao.www.myformer.function.kernel_module.msgcode;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.Touch;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.CountDownTimerUtils;
import com.haitao.www.myformer.utils.ToastUtils;
import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MsgCodeActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etPhoneNum;
    private TextView btnSendPhoneNum;
    private EditText etInputCode;
    private TextView btnSendCode;
    private TextView tvCheckoutResult;
    private FrameLayout moveVideoView;
    private LinearLayout llMsgcode;
    private int lastX, lastY;

    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                            ToastUtils.showToast(MsgCodeActivity.this, "发送成功");
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            ToastUtils.showToast(MsgCodeActivity.this, "验证通过");
                            tvCheckoutResult.setText("验证通过");
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                            tvCheckoutResult.setText(data.toString());
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgcode);
        findViews();
        MobSDK.init(this);
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void findViews() {
        etPhoneNum = findViewById(R.id.et_phone_num);
        btnSendPhoneNum = findViewById(R.id.btn_send_phoneNum);
        etInputCode = findViewById(R.id.et_input_code);
        btnSendCode = findViewById(R.id.btn_send_code);
        tvCheckoutResult = findViewById(R.id.tv_checkout_result);
        llMsgcode = findViewById(R.id.ll_msgcode);
        moveVideoView = findViewById(R.id.move_video_view);

        btnSendPhoneNum.setOnClickListener(this);
        btnSendCode.setOnClickListener(this);
        moveVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int screenWidth = dm.widthPixels;
                int screenHeight = dm.heightPixels;
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        //暂存按下的点的坐标
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        //计算出需要移动的距离=最新的坐标-之前的坐标
                        int offsetX = (int) event.getRawX() - lastX;
                        int offsetY = (int) event.getRawY() - lastY;
                        //视图的最新位置=视图本身的边框的位置坐标 +移动距离
                        int l = view.getLeft() + offsetX;
                        int t = view.getTop() + offsetY;
                        int r = view.getRight() + offsetX;
                        int b = view.getBottom() + offsetY;
                        //下面判断移动是否超出屏幕
                        if (l < 0) {
                            l = 0;
                            r = view.getWidth();
                        }
                        if (t < 0) {
                            t = 0;
                            b = view.getHeight();
                        }
                        if (r > screenWidth) {
                            r = screenWidth;
                            l = r - view.getWidth();
                        }
                        if (b > screenHeight) {
                            b = screenHeight;
                            t = b - view.getHeight();
                        }
                        view.layout(l, t, r, b);
                        //记录最后一次移动的位置
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        view.postInvalidate();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_phoneNum:
                // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                SMSSDK.getVerificationCode("86", getCheckCode(etPhoneNum));
                setCountDownTimer(btnSendPhoneNum, 5000);
                break;
            case R.id.btn_send_code:
                // 提交验证码，其中的code表示验证码，如“1357”
                SMSSDK.submitVerificationCode("86", getCheckCode(etPhoneNum), getCheckCode(etInputCode));
                break;
        }
    }

    private void setCountDownTimer(TextView view, int millisUntilFinished) {
        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(view, millisUntilFinished, 1000);
        mCountDownTimerUtils.start();
        mCountDownTimerUtils.setCountDownListener(new CountDownTimerUtils.OnCountDownListener() {
            @Override
            public void onTickDispose(View view, long millisUntilFinished) {
                TextView tv = (TextView) view;
                tv.setClickable(false); //设置不可点击

                SpannableString text = new SpannableString("剩余0秒");
                text.setSpan(new ForegroundColorSpan(Color.RED), 2, 3, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                tv.setText("剩余" + Long.toString(millisUntilFinished / 1000) + "秒");
            }

            @Override
            public void onFinishDispose(View view) {
                TextView tv = (TextView) view;
                tv.setText("获取验证码");
                tv.setClickable(true);//重新获得点击
            }
        });
    }

    public String getCheckCode(EditText view) {
        String phoneNumber = view.getText().toString().trim();
        //发送短信，传入国家号和电话号码
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.showToast(this, "输入不能为空！");
        }
        return phoneNumber;
    }

    // 使用完EventHandler需注销，否则可能出现内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }


}
