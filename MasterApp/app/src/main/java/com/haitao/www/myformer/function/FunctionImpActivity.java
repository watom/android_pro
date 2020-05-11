package com.haitao.www.myformer.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.function.kernel_module.msgcode.MsgCodeActivity;
import com.haitao.www.myformer.function.kernel_module.speech_recognition.SpeechRecognitionActivity;
import com.haitao.www.myformer.function.kernel_module.speechvideo.SpeechVideoActivity;
import com.haitao.www.myformer.logic.LogicTest.PhotoAlbumActivity;
import com.haitao.www.myformer.function.kernel_module.barcode.activity.BarCodeMainActivity;
import com.haitao.www.myformer.function.kernel_module.map.MapTestActivity;

public class FunctionImpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String[] content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listview =  findViewById(R.id.list_view_enter);
        initData(listview);
    }

    private void initData(ListView view) {
        content = new String[]{"二维码","相机相册","地图","及时通信","语音/视频直播","移动支付","搜索引擎","短信验证码"};
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,content);
        view.setAdapter(stringArrayAdapter);
        view.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = content[position];
        switch (s){
            case "二维码":
                startActivity(new Intent(this, BarCodeMainActivity.class));
                break;
            case "地  图":
                startActivity(new Intent(this, MapTestActivity.class));
                break;
            case "相机相册":
                startActivity(new Intent(this, PhotoAlbumActivity.class));
                break;
            case "及时通信":
//                startActivity(new Intent(this, null));
                break;
            case "语音/视频直播":
                startActivity(new Intent(this, SpeechVideoActivity.class));
                break;
            case "移动支付":
//                startActivity(new Intent(this, null));
                break;
            case "搜索引擎":
//                startActivity(new Intent(this, null));
            case "语音识别":
                startActivity(new Intent(this, SpeechRecognitionActivity.class));
                break;
            case "短信验证码":
                startActivity(new Intent(this, MsgCodeActivity.class));
                break;
        }
    }
}
