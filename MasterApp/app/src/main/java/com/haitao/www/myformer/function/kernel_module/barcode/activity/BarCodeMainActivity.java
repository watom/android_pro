package com.haitao.www.myformer.function.kernel_module.barcode.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.function.kernel_module.barcode.BarCodeTestActivity;
import com.haitao.www.myformer.function.kernel_module.barcode.encode.EncodingUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class BarCodeMainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private CheckBox mCheckBox;
    private Button scanBarCodeButton;
    private Button generateQRCodeButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_main);

        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        mCheckBox = (CheckBox) findViewById(R.id.logo);

        scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getCameraPermission();

            }
        });

        generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
        generateQRCodeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String contentString = qrStrEditText.getText().toString();
                if (!contentString.equals("")) {
                    //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 450, 450, mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(), R.drawable.barcode_launcher_icon) : null);
                    qrImgImageView.setImageBitmap(qrCodeBitmap);
                } else {
                    Toast.makeText(BarCodeMainActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 打开扫描界面扫描条形码或二维码
     */
    private void startScan() {
        Intent openCameraIntent = new Intent(BarCodeMainActivity.this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            resultTextView.setText(scanResult);
        }
    }

    /**
     * 获取摄像头权限
     */
    private void getCameraPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, Manifest.permission.CAPTURE_VIDEO_OUTPUT}, 0);
            } else {
                //已经获取到摄像头权限了,继续业务代码
                startScan();
            }
        } else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
            startScan();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //已经获取到摄像头权限了,继续业务代码
                    Toast.makeText(BarCodeMainActivity.this, "您已打开摄像头权限，请放心使用", Toast.LENGTH_SHORT).show();
                    startScan();
                } else {
                    //这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以。
                    Toast.makeText(BarCodeMainActivity.this, "对不起，由于您拒绝打开相机权限，所以无法使用摄像头", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
}