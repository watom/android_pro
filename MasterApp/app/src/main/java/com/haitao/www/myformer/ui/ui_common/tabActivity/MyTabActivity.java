package com.haitao.www.myformer.ui.ui_common.tabActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.tabActivity.activityGroupGridView.ActivityGroupGridView;
import com.haitao.www.myformer.ui.ui_common.tabActivity.tabActivityTabHost.TabActivityTabHost;
import com.haitao.www.myformer.ui.ui_common.tabActivity.tabFragmentActivity.TabFragmentActivity;
import com.haitao.www.myformer.ui.ui_common.tabActivity.tablayout.TabLayoutActivity;
import com.haitao.www.myformer.ui.ui_common.tabActivity.viewpage.MyViewPagerActivity;

/**
 * Created by Administrator on 2018/1/25 0025.
 */

public class MyTabActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview = (ListView) findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"TabActivity+TabHost实现分页(不常试用)","ActivityGroup+GridView实现分页","FragmentActivity实现分页","ViewPager+Fragment实现滑动切换","TabLayout"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "TabActivity+TabHost实现分页(不常试用)":
                showSingleChoiceDialog();
                break;
            case "ActivityGroup+GridView实现分页":
                startActivity(new Intent(this, ActivityGroupGridView.class));
                break;
            case "FragmentActivity实现分页":
                startActivity(new Intent(this, TabFragmentActivity.class));
                break;
            case "ViewPager+Fragment实现滑动切换":
                startActivity(new Intent(this, MyViewPagerActivity.class));
                break;
            case "TabLayout":
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
        }
    }

    private int yourChoice=-1;
    private void showSingleChoiceDialog() {
        yourChoice = 0;//默认值
        String[] items = {"Tab分页切换_View布局", "Tab分页切换_Activity", "Tab分页移到下面", "列表4"};
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(MyTabActivity.this);
        singleChoiceDialog.setIcon(R.mipmap.ic_launcher);
        singleChoiceDialog.setTitle("选择Tab分页");
        // 这个是监听选择的列表，第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            Intent intent = new Intent(MyTabActivity.this, TabActivityTabHost.class);
                            intent.putExtra("whichType", yourChoice);
                            startActivity(intent);
                        }
                    }
                });
        singleChoiceDialog.show();
    }
}
