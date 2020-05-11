package com.haitao.www.myformer.ui.ui_common.bitmap;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitao.www.myformer.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用bitmap将图片放大
 * Created by Administrator on 2018/3/15 0015.
 */

public class BitmapActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */
    Bitmap bp = null;
    ImageView imageview, imageview02, imageview03, imageview04, imageview05, imageview06;
    TextView tv_image_desc_02, tv_image_desc_03, tv_image_desc_04, tv_image_desc_05, tv_image_desc_06;
    float scaleWidth;
    float scaleHeight;

    int h;
    boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        intViews();
        zoom(); //缩放图片
    }

    private void zoom() {
        DisplayMetrics dm = new DisplayMetrics();//创建矩阵
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        bp = BitmapFactory.decodeResource(getResources(), R.drawable.lunbotu_04);
        int width = bp.getWidth();
        int height = bp.getHeight();
        int w = dm.widthPixels; //得到屏幕的宽度
        int h = dm.heightPixels; //得到屏幕的高度
        scaleWidth = ((float) w) / width;
        scaleHeight = ((float) h) / height;
        imageview.setImageBitmap(bp);
    }

    private void intViews() {
        imageview = (ImageView) findViewById(R.id.iv_bitmap_01);
        imageview02 = (ImageView) findViewById(R.id.iv_bitmap_02);
        tv_image_desc_02 = (TextView) findViewById(R.id.tv_image_desc_02);
        imageview03 = (ImageView) findViewById(R.id.iv_bitmap_03);
        tv_image_desc_03 = (TextView) findViewById(R.id.tv_image_desc_03);
        imageview04 = (ImageView) findViewById(R.id.iv_bitmap_04);
        tv_image_desc_04 = (TextView) findViewById(R.id.tv_image_desc_04);
        imageview05 = (ImageView) findViewById(R.id.iv_bitmap_05);
        tv_image_desc_05 = (TextView) findViewById(R.id.tv_image_desc_05);
        imageview06 = (ImageView) findViewById(R.id.iv_bitmap_06);
        tv_image_desc_06 = (TextView) findViewById(R.id.tv_image_desc_06);
        //----------------加载图片----------------
        loadImage("不同大小的图片", R.drawable.meimei01, imageview02, tv_image_desc_02);
        loadImage("不同大小的图片", R.drawable.meimei01, imageview03, tv_image_desc_03);
        loadImage("drawable-hdpi的图片", R.drawable.papa, imageview04, tv_image_desc_04);
//        loadImage("drawable-xhdpi的图片", R.drawable.papa,imageview05, tv_image_desc_05);
        loadImage("drawable-xhdpi的图片", "meihua_01.png", imageview06, tv_image_desc_06);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:  //当屏幕检测到第一个触点按下之后就会触发到这个事件。
                if (isClick == true) {
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleWidth);
                    Bitmap newBitmap = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
                    imageview.setImageBitmap(newBitmap);
                    isClick = false;
                } else {
                    Matrix matrix = new Matrix();
                    matrix.postScale(1.0f, 1.0f);
                    Bitmap newBitmap = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
                    imageview.setImageBitmap(newBitmap);
                    isClick = true;
                }
                break;
            case MotionEvent.ACTION_SCROLL:
                break;
        }

        return super.onTouchEvent(event);
    }


    //测算图片的属性：内存大小，图片尺寸等等
    public void loadImage(String address, int bitmapResources, ImageView imageView, TextView tv_image_desc) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bitmapResources, options);
        setImages(address, imageView, tv_image_desc, options, bitmap);
    }

    //测算图片的属性：内存大小，图片尺寸等等
    public void loadImage(String address, String imageName, ImageView imageView, TextView tv_image_desc) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap =null;
        AssetManager am = getAssets();
        InputStream is = null;
        try {
            is = am.open("image/" + imageName);//得到图片流
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImages(address, imageView, tv_image_desc, options, bitmap);
    }

    public void setImages(String address, ImageView imageView, TextView tv_image_desc, BitmapFactory.Options options, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        String des = address + " ，getByteCount大小=" + bitmap.getByteCount() + " bit，getAllocationByteCount大小=" + bitmap.getAllocationByteCount() + " bit，inDensity=" + options.inDensity + "，inTargetDensity=" + options.inTargetDensity + "，\nbitmap的宽 x 高 = " + bitmap.getWidth() + " * " + bitmap.getHeight() + "，\nimageView的宽 x 高 = " + imageView.getWidth() + " * " + imageView.getHeight();
        tv_image_desc.setText(des);
        Log.d("图片", "loadImage: getByteCount大小=" + bitmap.getByteCount()); //得到当前图片在内存中的大小
        Log.d("图片", "loadImage: getAllocationByteCount大小=" + bitmap.getAllocationByteCount()); //新的API，得到当前图片在内存中的大小
        Log.d("图片", "loadImage: inDensity=" + options.inDensity + "，inTargetDensity=" + options.inTargetDensity);  //inDensity:像素密度用来表示。inTargetDensity:将绘制到的目标的像素密度。
        Log.d("图片", "loadImage: bitmap的宽=" + bitmap.getWidth() + "，高=" + bitmap.getHeight());
        Log.d("图片", "loadImage: imageView的宽=" + imageView.getWidth() + "，高=" + imageView.getHeight());
    }
}
