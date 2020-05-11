package com.haitao.www.myformer.ui.ui_common.listview.XListView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.haitao.www.myformer.R;

/**
 * Created by watom_Thinkpad on 2017/11/16.
 */

public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        Button listBrn = (Button) findViewById(R.id.list_view_btn);
        listBrn.setOnClickListener(this);

        Button scrollBrn = (Button) findViewById(R.id.scroll_view_btn);
        scrollBrn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the MenuDefine; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_view_btn:
                XListViewActivity.launch(this);
                break;

            case R.id.scroll_view_btn:
                XScrollViewActivity.launch(this);
                break;
        }
    }
}
