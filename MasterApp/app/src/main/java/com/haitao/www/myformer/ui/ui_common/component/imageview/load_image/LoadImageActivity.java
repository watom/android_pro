package com.haitao.www.myformer.ui.ui_common.component.imageview.load_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.haitao.www.myformer.R;

public class LoadImageActivity extends AppCompatActivity {
    private ImageView load_image_01;
    private TextView imageViewAttr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);
        initView();
        initData();
    }

    private void initView() {
        load_image_01 = findViewById(R.id.load_image_01);
        imageViewAttr = findViewById(R.id.image_view_attr);

        imageViewAttr.append("fitXY:对图像的横向与纵向进行独立缩放,使得该图片完全适应ImageView,但是图片的横纵比可能会发生改变\n" +
                "fitStart:保持纵横比缩放图片,知道较长的边与Image的编程相等,缩放完成后将图片放在ImageView的左上角\n" +
                "fitCenter:同上,缩放后放于中间;\n" +
                "fitEnd:同上,缩放后放于右下角;\n" +
                "center:保持原图的大小，显示在ImageView的中心。当原图的size大于ImageView的size，超过部分裁剪处理。\n" +
                "centerCrop:保持横纵比缩放图片,知道完全覆盖ImageView,可能会出现图片的显示不完全\n" +
                "centerInside:保持横纵比缩放图片,直到ImageView能够完全地显示图片\n" +
                "matrix:默认值，不改变原图的大小，从ImageView的左上角开始绘制原图，原图超过ImageView的部分作裁剪处理\n" +
                "ps：一般情况下可以用centerInside来设置缩放类型。");
    }

    private void initData() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Bitmap bmp = BitmapFactory.decodeFile(path+"/A_我的文件夹/愉悦资源/美图/meinv01.jpg",null);
        load_image_01.setImageBitmap(bmp);
    }
}
