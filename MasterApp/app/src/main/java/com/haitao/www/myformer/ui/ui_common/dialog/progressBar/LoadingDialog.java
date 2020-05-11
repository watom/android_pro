package com.haitao.www.myformer.ui.ui_common.dialog.progressBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import com.haitao.www.myformer.R;


/**
 *
 */
public class LoadingDialog extends ProgressDialog {

    private Activity context;
    public boolean isDismiss;

    public LoadingDialog(Context context) {
        super(context, R.style.Dialog_BackgroundTransparent);
        this.context = (Activity) context;
//		this.setCancelable(false);
        this.setCanceledOnTouchOutside(true);//true：点击进度条外的其他地方，可以取消进度条。
    }

    public LoadingDialog(Context context, boolean isDismiss) {
        this(context);
        this.isDismiss = isDismiss;
    }

    public void setCancerBack(boolean isCancerBack) {
        isDismiss = isCancerBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isDismiss) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.dismiss();
            context.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
