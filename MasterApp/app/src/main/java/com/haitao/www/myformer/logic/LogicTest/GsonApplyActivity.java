package com.haitao.www.myformer.logic.LogicTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haitao.www.myformer.R;

/**
 * Created by Administrator on 2018/8/20 0020.
 */

public class GsonApplyActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvJson;
    private Button btnSss;
    private TextView tvClass;
    private GsonBean gsonBean;
    private String jsonString="{\n" +
            "\t\"data\": {\n" +
            "\t\t\"returnCode\": \"0000\",\n" +
            "\t\t\"requestCode\": \"JS12547\",\n" +
            "\t\t\"returnMsg\": \"查询成功\",\n" +
            "\t\t\"rteLists\": [{\n" +
            "\t\t\t\"rteId\": \"1001\",\n" +
            "\t\t\t\"rteName\": \"汇款账号名称\",\n" +
            "\t\t\t\"rteNo\": \"28906578878889\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"rteId\": \"1002\",\n" +
            "\t\t\t\"rteName\": \"汇款账号名称\",\n" +
            "\t\t\t\"rteNo\": \"22222222\"\n" +
            "\t\t}]\n" +
            "\t}\n" +
            "}";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_apply);
        intView();
        intData();
    }

    private void intData() {

        gsonBean = new Gson().fromJson(jsonString, GsonBean.class);
        Log.d("wanghaitao", "gsonBean == "+gsonBean.toString());
    }

    private void intView() {
        tvJson = (TextView)findViewById( R.id.tv_json );
        btnSss = (Button)findViewById( R.id.btn_sss );
        tvClass = (TextView)findViewById( R.id.tv_class );
        tvJson.setText(jsonString);
        btnSss.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == btnSss ) {
            tvClass.setText(gsonBean.toString());
        }
    }
}
