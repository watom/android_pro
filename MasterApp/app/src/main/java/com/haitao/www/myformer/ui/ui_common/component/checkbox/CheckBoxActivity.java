package com.haitao.www.myformer.ui.ui_common.component.checkbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.haitao.www.myformer.R;

public class CheckBoxActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private String checkBoxText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        findViews();
        initEvent();

    }

    private void initEvent() {
        checkBox.setChecked(true);
        checkBoxText= checkBox.getText().toString();
    }

    private void findViews() {
        checkBox =(CheckBox) findViewById(R.id.checkBox);
    }
}
