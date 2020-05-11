package com.haitao.www.myformer.design_pattern;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.nettys.netty.base.BaseActivity;
import com.haitao.www.myformer.utils.Log;

public class DesignPatternActivity extends BaseActivity {
    private ExpandableListView expandableListView;
    private TextView tv_content;

    @Override
    public int getContentView() {
        return R.layout.activity_expandablelistview;
    }

    @Override
    public void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.elv_expandablelistview);
        tv_content = (TextView) findViewById(R.id.tv_content);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        AdapterDesignPattern adapter = new AdapterDesignPattern(this);
        expandableListView.setAdapter(adapter);

        //设置点击事件
        SpannableString 请点击文字 = new SpannableString(">>> 点击查看详情 <<<");
        请点击文字.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(DesignPatternActivity.this, PatternContentShow.class));
            }
        }, 0, 请点击文字.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv_content.append(请点击文字);
        tv_content.setMovementMethod(LinkMovementMethod.getInstance()); // 重要：设置文字为可点击状态
    }

    @Override
    public void initEvent() {
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.e("显示参数值", "groupPosition:" + groupPosition + "  childPosition:" + childPosition + "  id:" + id);
                return false;
            }
        });
    }

    @Override
    protected void updateData(Message msg) {

    }
}