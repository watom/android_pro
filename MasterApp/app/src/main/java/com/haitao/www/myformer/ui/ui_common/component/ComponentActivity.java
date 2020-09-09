package com.haitao.www.myformer.ui.ui_common.component;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.haitao.www.myformer.Config;
import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.checkbox.CheckBoxActivity;
import com.haitao.www.myformer.ui.ui_common.component.customassemblewidget.CustomAssembleWidgetActivity;
import com.haitao.www.myformer.ui.ui_common.component.imageview.ImageViewCategory;
import com.haitao.www.myformer.ui.ui_common.component.keyboard.KeyBoardActivity;
import com.haitao.www.myformer.ui.ui_common.component.notification.NotificationActivity;
import com.haitao.www.myformer.ui.ui_common.moduleTest.ratingbarview.RatingBarActivity;
import com.haitao.www.myformer.ui.ui_common.component.textview.SpannableStringActivity;
import com.haitao.www.myformer.ui.ui_common.component.timepicker.DatePickerActivity;
import com.haitao.www.myformer.ui.ui_common.component.timepicker.TimePickerActivity;
import com.haitao.www.myformer.utils.Log;
import com.haitao.www.myformer.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个是使用SimpleAdapter适配器的方法的GridView.
 * 还有一种是自定义适配器的用法,非常灵活，有时间再切换
 * Created by Administrator on 2018/1/8 0008.
 */

public class ComponentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gridview;
    private ArrayList<Map<String, Object>> dataList;
    private int[] icon = {R.drawable.common_icon, R.drawable.common_icon};
    private String[] label = {"ImageView", "TextView", "时间选择器", "日期选择器", "自定义组合控件","评价条","CheckBox","跑马灯","通知栏","键盘高度"};

    /**
     * 关键代码SimpleAdapter的参数
     * SimpleAdapter(Context context, List<? extends Map<String, ?>> data,@LayoutRes int resource, String[] from, @IdRes int[] to)
     * context  上下文
     * data 数据来源，，类型为List<? extends Map<String, ?>>
     * resource GridView网格视图中的Item布局(R.layout.gridview_item_layout)
     * String[] from 给Item布局文件提供数据的动态数组
     * int[] to 需要放入Item布局文件中的元素的ID
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_activity);
        gridview = findViewById(R.id.gridview);
        dataList = getData(icon, label);
        gridview.setAdapter(new SimpleAdapter(this, dataList, R.layout.gridview_item_layout, new String[]{"icon", "label"}, new int[]{R.id.item_icon, R.id.item_label}));
        gridview.setOnItemClickListener(this);
    }

    private ArrayList<Map<String, Object>> getData(int[] icon, String[] label) {
        Map<String, Object> map = null;
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < label.length; i++) {
            map = new HashMap<>();
            map.put("label", label[i]);
            if (i >= icon.length) {
                map.put("icon", R.drawable.null_pic);
            } else {
                map.put("icon", icon[i]);
            }
            data.add(map);
        }
        return data;
    }


    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> itemAtPosition = (Map<String, Object>) parent.getItemAtPosition(position);
        String name = (String) itemAtPosition.get("label");
        switch (name) {
            case "TextView":
                startActivity(new Intent(this, SpannableStringActivity.class));
                break;
            case "ImageView":
                startActivity(new Intent(this, ImageViewCategory.class));
                break;
            case "时间选择器":
                startActivity(new Intent(this, TimePickerActivity.class));
                break;
            case "日期选择器":
                startActivity(new Intent(this, DatePickerActivity.class));
                break;
            case "自定义组合控件":
                startActivity(new Intent(this, CustomAssembleWidgetActivity.class));
                break;
            case "评价条":
                startActivity(new Intent(this, RatingBarActivity.class));
                break;
            case "CheckBox":
                startActivity(new Intent(this, CheckBoxActivity.class));
                break;
            case "跑马灯":
//                startActivity(new Intent(this, CheckBoxActivity.class));
                break;
            case "通知栏":
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case "键盘高度":
                startActivity(new Intent(this, KeyBoardActivity.class));
                break;
            default:
                ToastUtils.showToast(this, "暂时未开通\"" + name + "\"功能");
        }

        //打开测试
        if (Config.isTest) {
            TextView label = view.findViewById(R.id.item_label);
            Log.e("ComponentActivity", "测试：点击后替换文字");
            label.setText("西安");
            Log.e("ComponentActivity", "icon=" + itemAtPosition.get("icon") + "，name=" + itemAtPosition.get("label") + "，position=" + position + "，id=" + id);
        }
    }
}
