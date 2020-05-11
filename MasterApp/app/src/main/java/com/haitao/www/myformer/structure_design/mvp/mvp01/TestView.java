package com.haitao.www.myformer.structure_design.mvp.mvp01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haitao.www.myformer.R;

public class TestView extends Activity implements TestContract.View, View.OnClickListener {
    private Button btnTest;
    private TextView tvTest;
    private TestPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViews();
        presenter = new TestPresenter(TestModel.getInstance(), this);
    }

    private void findViews() {
        btnTest = (Button) findViewById(R.id.btn_test);
        tvTest = (TextView) findViewById(R.id.tv_test);

        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnTest) {
            presenter.getData();
        }
    }


    @Override
    public void showData(String str) {
        tvTest.append(str);
    }
}
