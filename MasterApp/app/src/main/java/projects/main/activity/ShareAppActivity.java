package projects.main.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.function.kernel_module.share.onekeyshare.OnekeyShare;
import com.haitao.www.myformer.utils.AlipayUtil;
import com.haitao.www.myformer.utils.ToastUtils;
import com.mob.MobSDK;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * 使用的平台：Mob，官网：http://dashboard.mob.com/#!/share/dashboard
 * label：超级测试  Appkey:247303d390ef8    App Secret:11bf0f3a364e10f1be29bc8bcf21dc91
 * Created by Administrator on 2018/3/1 0001.
 */
public class ShareAppActivity extends AppCompatActivity {
    private TextView aboutus;
    private ImageView barcode;
    private LinearLayout ll_share;
    private String urlStr = "https://fir.im/xlz5";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_share_barcode);
        initView();
        initData();
        initEvent();

        generateBarCode(barcode, urlStr);
        MobSDK.init(this);
        showShare(urlStr);
    }

    private void initView() {
        aboutus = findViewById(R.id.tv_aboutus);
        barcode = findViewById(R.id.iv_barcode);
        ll_share = findViewById(R.id.ll_share);
    }

    private void initEvent() {
        ll_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AlipayUtil.hasInstalledAlipayClient(ShareAppActivity.this)) {
                    AlipayUtil.startAlipayClient(ShareAppActivity.this, "HTTPS://QR.ALIPAY.COM/FKX07636ZRCCQH2VENC2E7");
                } else {
                    ToastUtils.showToast(ShareAppActivity.this, "未检测到支付宝，无法给伙计打赏，但是还是要谢谢您的支持");
                }
            }
        });
    }

    private void initData() {
        //设置文本的超链接功能
        SpannableString spannableString = new SpannableString("有问题给我说一下，可以发邮件，谢谢支持。");
        spannableString.setSpan(new URLSpan("mailto:1164973719@qq.com"),11,14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        aboutus.append(spannableString);
        aboutus.setMovementMethod(LinkMovementMethod.getInstance());  // 重要：设置文字为可点击状态
    }

    /**
     * 二维码生成
     */
    private void generateBarCode(ImageView view, String str) {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        Bitmap qrCodeBitmap = CodeUtils.createImage(str, width / 2, width / 2, null);
        view.setImageBitmap(qrCodeBitmap);
    }

    public void showShare(String url) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是王海涛，分享一个有趣的APP，，点击链接" + url + "下载,欢迎您畅游互联网~");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // 启动分享GUI
        oks.show(this);
    }
}
