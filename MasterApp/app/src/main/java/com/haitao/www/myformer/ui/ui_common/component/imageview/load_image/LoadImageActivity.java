package com.haitao.www.myformer.ui.ui_common.component.imageview.load_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.haitao.www.myformer.R;

public class LoadImageActivity extends AppCompatActivity {
    private ImageView load_image_01;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);
        initView();
        initData();
    }

    private void initView() {
        load_image_01 = findViewById(R.id.load_image_01);
    }

    private void initData() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Bitmap bmp = BitmapFactory.decodeFile(path+"/A_我的文件夹/愉悦资源/美图/meinv01.jpg",null);
        load_image_01.setImageBitmap(bmp);
    }
}
