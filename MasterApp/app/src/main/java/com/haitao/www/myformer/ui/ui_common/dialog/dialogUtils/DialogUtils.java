package com.haitao.www.myformer.ui.ui_common.dialog.dialogUtils;

import android.app.Dialog;
import android.content.Context;
import androidx.appcompat.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.log.Log;
import com.haitao.www.myformer.utils.Util;


/**
 * ╭-------------------------╮
 * │          标 题          │
 * │------------------------ │
 * │   提示 **************** │
 * │_________________________│
 * │     取消    │    确认    │
 * ╰-------------┴-----------╯
 * Created by Administrator on 2018/8/23 0023.
 */

public class DialogUtils {
    private Context context;
    private TextView dialogTitleText, dialogContentText, dialogLeftBtn, dialogRightorokBtn, cancleButton, okButton;
    private LinearLayout ll_dialog_content_text;
    private Dialog dialog;
    private Boolean isCenter;
    private OnClickLeftButtonListen left;

    public DialogUtils() {
    }

    private static DialogUtils dialogUtils = new DialogUtils();

    public static final DialogUtils getInstance() {
        return dialogUtils;
    }

    /**
     * 只有一个按钮
     * @param context 上下文
     * @param content 正文
     * @param title 标题
     * @param right 按钮
     */
    public void call(Context context, String content,Boolean contentIsCenter, String title, OnClickRightButtonListen right) {
        callDilog(context, content, contentIsCenter,null, null, title, right, null,false);
    }
    /**
     * 只有一个按钮
     * @param context 上下文
     * @param content 正文
     * @param title 标题
     * @param right 按钮
     */
    public void call(Context context, String content, String title, OnClickRightButtonListen right) {
        callDilog(context, content, false,null, null, title, right, null,false);
    }

    /**
     *两个按钮 取消按钮有业务
     * @param context 上下文
     * @param content 正文
     * @param title 标题
     * @param right 右按钮
     * @param left 左按钮
     */
    public void call(Context context, String content, String title, OnClickRightButtonListen right, OnClickLeftButtonListen left) {
        callDilog(context, content, false,null, null, title, right, left,false);
    }
    /**
     *两个按钮 取消按钮无业务
     * @param context 上下文
     * @param content 正文
     * @param title 标题
     * @param right 右按钮
     * @param uselessCancle 左按钮无业务
     */
//    public void call(Context context, String content, String title, OnClickRightButtonListen right,Boolean uselessCancle) {
//        callDilog(context, content, false,null, null, title, right, left,uselessCancle);
//    }

    /**
     *两个按钮 正文居中
     * @param context 上下文
     * @param content 正文
     * @param isCenter 正文是否居中 true：居中
     * @param title 标题
     * @param right 右按钮
     * @param left 左按钮
     */
    public void call(Context context, String content, Boolean isCenter, String title, OnClickRightButtonListen right, OnClickLeftButtonListen left) {
        callDilog(context, content, isCenter,null, null, title, right, left,false);
    }

    /**
     *两个按钮 自定义按钮的名字
     * @param context 上下文
     * @param content 正文
     * @param okButtonOfName 确认按钮的名字
     * @param cancleButtonOfName 确认按钮的名字
     * @param title 标题
     * @param right 右按钮
     * @param left 左按钮
     */
    public void call(Context context, String content, String okButtonOfName, String cancleButtonOfName, String title, OnClickRightButtonListen right, OnClickLeftButtonListen left) {
        callDilog(context, content,false, okButtonOfName, cancleButtonOfName, title, right, left,false);
    }

    private void callDilog(Context context, String content, Boolean isCenter,String okButtonOfName, String cancleButtonOfName, String title, OnClickRightButtonListen right, OnClickLeftButtonListen left,Boolean uselessCancle) {
        this.context = context;
        this.isCenter = isCenter;
        dialog = new AlertDialog.Builder(context, R.style.mDialog_background).create();
        View view = layoutInflater(content, title, right, left);
        if (Util.isEmpty(okButtonOfName) && Util.isEmpty(cancleButtonOfName)) {
            initView(view, content, title, null, null);
        } else {
            initView(view, content, title, okButtonOfName, cancleButtonOfName);
        }
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(view);
        addListen(right, left);
    }

    private View layoutInflater(String content, String title, OnClickRightButtonListen right, OnClickLeftButtonListen left) {
        View view = null;
        if (right != null && left != null && !Util.isEmpty(content) && !Util.isEmpty(title)) {
            view = LayoutInflater.from(context).inflate(R.layout.xa_dialog_custom, null);    //有title，有2个btn
        } else if (right != null && left != null && !Util.isEmpty(content)) {
            view = LayoutInflater.from(context).inflate(R.layout.xa_dialog_custom_03, null); //无title，有2个btn
        } else if (right != null && !Util.isEmpty(title)) {
            view = LayoutInflater.from(context).inflate(R.layout.xa_dialog_custom_04, null); //有title，有1个btn
        } else if (right != null) {
            view = LayoutInflater.from(context).inflate(R.layout.xa_dialog_custom_02, null); //无title，有1个btn
        } else {
            Log.v("DialogUtils", "可以填充自定义布局的Dilog");
        }
        return view;
    }

    private void addListen(final OnClickRightButtonListen right, final OnClickLeftButtonListen left) {
        if (dialogLeftBtn != null) {
            dialogLeftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (left != null) {
                        left.onClick(dialog);
                        dialog.dismiss();
                    }
                }
            });
        }

        if (dialogRightorokBtn != null) {
            dialogRightorokBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (right != null) {
                        right.onClick(dialog);
                        dialog.dismiss();
                    }
                }
            });
        } else {
            Log.v("DialogUtils", "其他类型的Dilog");
        }
    }

    private void initView(View view, String content, String title, String okButtonName, String cancleButton) {
        dialogTitleText = (TextView) view.findViewById(R.id.dialog_title_text);
        dialogContentText = (TextView) view.findViewById(R.id.dialog_content_text);
        ll_dialog_content_text = (LinearLayout) view.findViewById(R.id.ll_dialog_content_text);
        dialogLeftBtn = (TextView) view.findViewById(R.id.dialog_left_btn);
        dialogRightorokBtn = (TextView) view.findViewById(R.id.dialog_rightorok_btn);
        if (!Util.isEmpty(content)) dialogContentText.setText(content);
        if (isCenter) ll_dialog_content_text.setGravity(Gravity.CENTER);
        if (!Util.isEmpty(title)) dialogTitleText.setText(title);
        if (!Util.isEmpty(okButtonName)) dialogTitleText.setText(okButtonName);
        if (!Util.isEmpty(cancleButton)) dialogTitleText.setText(cancleButton);
    }

    public interface OnClickLeftButtonListen {
        void onClick(Dialog dialog);
    }

    public interface OnClickRightButtonListen {
        void onClick(Dialog dialog);
    }
}
