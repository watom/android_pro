package com.haitao.www.myformer.ui.ui_common.listview.expandablelistView;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.Log;

/**
 * Created by Administrator on 2018/8/20 0020.
 */

public class MyExpandableListView extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelistview);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.elv_expandablelistview);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.e("wanghaitao222","groupPosition:"+groupPosition+"  childPosition:"+childPosition+"  id:"+id);
                return false;
            }
        });
    }
}
