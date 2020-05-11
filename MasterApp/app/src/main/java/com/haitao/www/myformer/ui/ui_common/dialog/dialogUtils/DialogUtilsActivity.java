package com.haitao.www.myformer.ui.ui_common.dialog.dialogUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haitao.www.myformer.R;

/**
 * Created by Administrator on 2018/8/23 0023.
 */

public class DialogUtilsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_common_01,btn_common_02;
    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private LinearLayout layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_button_layout);
        initView();
        initEvent();
    }

    private void initView() {
        btn_common_01 = findViewById(R.id.btn_common_01);
        btn_common_02 = findViewById(R.id.btn_common_02);
    }

    private void initEvent() {
        btn_common_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initChildView(v);
            }
        });
        btn_common_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initChildView(v);
            }
        });
    }



    private void initChildView(View v) {
        btn_take_photo = (Button) v.findViewById(R.id.btn_take_photo);
        btn_pick_photo = (Button) v.findViewById(R.id.btn_pick_photo);
        btn_cancel = (Button) v.findViewById(R.id.btn_cancel);

        //添加按钮监听
        btn_cancel.setOnClickListener(this);
        btn_pick_photo.setOnClickListener(this);
        btn_take_photo.setOnClickListener(this);
        //添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数执行其他操作
        layout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    //实现onTouchEvent触屏函数但点击屏幕时执行其他操作
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:
                break;
            case R.id.btn_pick_photo:
                break;
            case R.id.btn_cancel:
                break;
            default:
                break;
        }
        //可以隐藏
    }
}
