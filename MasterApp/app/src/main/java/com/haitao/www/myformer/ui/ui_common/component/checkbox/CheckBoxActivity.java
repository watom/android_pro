package com.haitao.www.myformer.ui.ui_common.component.checkbox;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
