package projects.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.customassemblewidget.TitleBar;
import com.haitao.www.myformer.utils.SystemBarTintManager;

public class ZhenZhuMeiXueLog extends Activity {
    private TitleBar titlebar;
    private TextView tradeType;
    private TextView tradeJine;
    private TextView tradeTime;
    private TextView tradeId;
    private TextView tradeNo;
    private TextView orderId;
    private TextView remark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_log);

        setStatusBarColor(this,R.color.colorPrimaryDark);
        setAndroidNativeLightStatusBar(this,true);
        findViews();
        setView();
    }

    /**
     * 修改状态栏颜色，支持4.4以上版本
     * @param activity
     * @param colorId
     */
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
            transparencyBar(activity);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(colorId);
        }
    }

    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    private void setView() {
        Bundle bunde = this.getIntent().getExtras();
        titlebar.setTitle("交易记录");
        titlebar.setBackTextVisible(false);
        titlebar.setShowLine(false);
        titlebar.setTitleSize(17);
        tradeJine.setText(bunde.getString("tradeJine"));
        tradeType.setText(bunde.getString("tradeType"));
        tradeTime.setText(bunde.getString("tradeTime"));
        tradeId.setText(bunde.getString("tradeId"));
        tradeNo.setText(bunde.getString("tradeNo"));
        orderId.setText(bunde.getString("orderId"));
        remark.setText(bunde.getString("remark"));
    }


    private void findViews() {
        titlebar = (TitleBar)this.findViewById( R.id.titlebar );
        tradeJine = (TextView)this.findViewById( R.id.trade_jine );
        tradeType = (TextView)this.findViewById( R.id.trade_type );
        tradeTime = (TextView)this.findViewById( R.id.trade_time );
        tradeId = (TextView)this.findViewById( R.id.trade_id );
        tradeNo = (TextView)this.findViewById( R.id.trade_no );
        orderId = (TextView)this.findViewById( R.id.order_id );
        remark = (TextView)this.findViewById( R.id.remark );
    }

}
