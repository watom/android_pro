package com.haitao.www.myformer.ui.ui_common.dialog.alert_dialog;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.dialog.dialogUtils.DialogUtils;
import com.haitao.www.myformer.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class AlertDialogDemo extends AppCompatActivity implements View.OnClickListener {
    private Button dialog1;
    private Button dialog2;
    private Button dialog3;
    private Button dialog4;
    private Button dialog5;
    private Button dialog6;
    private Button dialog7;
    private Button dialog8;
    private Button dialog9;
    private Button dialog10;
    private Button dialog11;
    private Button dialog12;
    private Button dialog13;
    private Button dialog14;
    private Button dialog15;
    private Button dialog16;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog_activity);
        findViews();
    }

    private void findViews() {
        dialog1 = (Button) findViewById(R.id.dialog_1);
        dialog2 = (Button) findViewById(R.id.dialog_2);
        dialog3 = (Button) findViewById(R.id.dialog_3);
        dialog4 = (Button) findViewById(R.id.dialog_4);
        dialog5 = (Button) findViewById(R.id.dialog_5);
        dialog6 = (Button) findViewById(R.id.dialog_6);
        dialog7 = (Button) findViewById(R.id.dialog_7);
        dialog8 = (Button) findViewById(R.id.dialog_8);
        dialog9 = (Button) findViewById(R.id.dialog_9);
        dialog10 = (Button) findViewById(R.id.dialog_10);
        dialog11 = (Button) findViewById(R.id.dialog_11);
        dialog12 = (Button) findViewById(R.id.dialog_12);
        dialog13 = (Button) findViewById(R.id.dialog_13);
        dialog14 = (Button) findViewById(R.id.dialog_14);
        dialog15 = (Button) findViewById(R.id.dialog_15);
        dialog16 = (Button) findViewById(R.id.dialog_16);

        dialog1.setOnClickListener(this);
        dialog2.setOnClickListener(this);
        dialog3.setOnClickListener(this);
        dialog4.setOnClickListener(this);
        dialog5.setOnClickListener(this);
        dialog6.setOnClickListener(this);
        dialog7.setOnClickListener(this);
        dialog8.setOnClickListener(this);
        dialog9.setOnClickListener(this);
        dialog10.setOnClickListener(this);
        dialog11.setOnClickListener(this);
        dialog12.setOnClickListener(this);
        dialog13.setOnClickListener(this);
        dialog14.setOnClickListener(this);
        dialog15.setOnClickListener(this);
        dialog16.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == dialog1) {
            showNormalDialog();
        } else if (v == dialog2) {
            showSpecialDialog();
        } else if (v == dialog3) {
            showListDialog();
        } else if (v == dialog4) {
            showSingleChoiceDialog();
        } else if (v == dialog5) {
            showMultiChoiceDialog();
        } else if (v == dialog6) {
            showWaitingDialog();
        } else if (v == dialog7) {
            showProgressDialog();
        } else if (v == dialog8) {
            showInputDialog();
        } else if (v == dialog9) {
            showCustomizeDialog();
        } else if (v == dialog10) {
            showCustomTitleDialog();
        } else if (v == dialog11) {
            showOverrideListDialog();
        } else if (v == dialog12) {
            showfillCustomizeDialog();
        } else if (v == dialog13) {
            showCallPhoneDialog();
        } else if (v == dialog14) {
            showCustomizeWaitingDialog();
        } else if (v == dialog15) {
            showDialogTool();
        } else if (v == dialog16) {
            showWaitingNewDialog();
        }
    }

    private void showWaitingNewDialog() {

    }


    private void showNormalDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("通用Dialog");
        dialog.setMessage("这是一般通用的Dialog");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(AlertDialogDemo.this, "确认");
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(AlertDialogDemo.this, "取消");
            }
        });
        dialog.show();
    }

    private void showSpecialDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("通用Dialog");
        dialog.setMessage("这是只显示三个按钮的Dialog,一个是中立选择项目");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(AlertDialogDemo.this, "确认");
            }
        });
        dialog.setNeutralButton("选择1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(AlertDialogDemo.this, "选择1");
            }
        });
        dialog.setNeutralButton("选择2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(AlertDialogDemo.this, "选择2");
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(AlertDialogDemo.this, "取消");
            }
        });
        dialog.show();
    }

    private void showListDialog() {
        final String[] items = {"列表1", "列表2", "列表3", "列表4"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(AlertDialogDemo.this);
        listDialog.setIcon(R.mipmap.ic_launcher);
        listDialog.setTitle("我是一个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始

                ToastUtils.showToast(AlertDialogDemo.this, "Click: " + items[which]);
            }
        });
        listDialog.show();
    }

    /**
     * 注意下面两个Dialog：(单选+确认Dialog)showSingleChoiceDialog、(多选+确认Dialog)showMultiChoiceDialog，
     * 不能设置setMessage
     */
    private int yourChoice = -1;

    private void showSingleChoiceDialog() {
        yourChoice = 0;
        final String[] items = {"列表1", "列表2", "列表3", "列表4"};
        AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(AlertDialogDemo.this);
        singleChoiceDialog.setIcon(R.mipmap.ic_launcher);
        singleChoiceDialog.setTitle("单选+确认Dialog");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                yourChoice = which;
            }
        });
        singleChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (yourChoice != -1) {
                    ToastUtils.showToast(AlertDialogDemo.this, "确认: " + items[yourChoice]);
                }
            }
        });
        singleChoiceDialog.show();
    }

    ArrayList<Integer> yourChoices = new ArrayList<>();

    private void showMultiChoiceDialog() {
        final String[] items = {"列表1", "列表2", "列表3", "列表4"};
        // 设置默认选中的选项，全为false默认均未选中
        final boolean initChoiceSets[] = {false, false, false, false};
        yourChoices.clear();
        AlertDialog.Builder multiChoiceDialog = new AlertDialog.Builder(AlertDialogDemo.this);
        multiChoiceDialog.setIcon(R.mipmap.ic_launcher);
        multiChoiceDialog.setTitle("多选+确认Dialog");
//        multiChoiceDialog.setMessage("我是一个多选+确认Dialog");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    yourChoices.add(which);
                } else {
                    yourChoices.remove(which);
                }
            }
        });
        multiChoiceDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = yourChoices.size();
                String str = "";
                for (int i = 0; i < size; i++) {
                    str += items[yourChoices.get(i)] + " ";
                }
                ToastUtils.showToast(AlertDialogDemo.this, "确认: " + str);
            }
        });
        multiChoiceDialog.show();
    }

    /**
     * 注意：这个圆形的进度条不断显示进度数字时87/100，需要用handler手动实现。直线的进度条底层已经实现。
     * 等待Dialog具有屏蔽其他控件的交互能力
     *
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
    public int waitingTime = 6;
    ProgressDialog waitingDialog;

    private void showWaitingDialog() {
        waitingDialog = new ProgressDialog(AlertDialogDemo.this);
        waitingDialog.setIcon(R.mipmap.ic_launcher);
        waitingDialog.setTitle("等待Dialog");
        //更改此ProgressDialog的不确定模式。在不确定模式下，进程将被忽略，对话框将显示无限的动画。
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(true);//点击物理返回键时，设置Dialog是否能被取消
        waitingDialog.setCanceledOnTouchOutside(false);//点击Dialog的outSide时，设置Dialog是否能被取消
        waitingDialog.show();
        final Handler mWaitingHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                waitingDialog.setMessage("我是一个等待（" + waitingTime + "s）Dialog,等待中...");
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (waitingTime >= 0) {
                    try {
                        Thread.sleep(1000);
                        waitingTime--;
                        if (mWaitingHandler != null && !mWaitingHandler.hasMessages(0))
                            mWaitingHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                waitingDialog.cancel();
            }
        }).start();
    }

    /**
     * @setProgress 设置初始进度
     * @setProgressStyle 设置样式（水平进度条）
     * @setMax 设置进度最大值
     */
    private void showProgressDialog() {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog = new ProgressDialog(AlertDialogDemo.this);
        progressDialog.setProgress(0);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("进度条Dialog");
        progressDialog.setMessage("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
        /* 模拟进度增加的过程
         * 新开一个线程，每个100ms，进度增加1
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < MAX_PROGRESS) {
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.cancel();  // 进度达到最大值后，窗口消失
            }
        }).start();
    }

    /**
     * 输入Dialog
     */
    private void showInputDialog() {
        final EditText editText = new EditText(AlertDialogDemo.this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(AlertDialogDemo.this);
        inputDialog.setIcon(R.mipmap.ic_launcher);
        inputDialog.setMessage("这是一个输入Dialog");
        inputDialog.setTitle("输入Dialog").setView(editText);
        inputDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(AlertDialogDemo.this, "您输入的内容:  " + editText.getText().toString());
            }
        }).show();
    }

    /**
     * setView 装入自定义View
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
     */
    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(AlertDialogDemo.this);
        final View dialogView = LayoutInflater.from(AlertDialogDemo.this).inflate(R.layout.alert_dialog_layout, null);
        customizeDialog.setTitle("自定义Dialog");
        customizeDialog.setIcon(R.mipmap.ic_launcher);
        customizeDialog.setMessage("这是一个自定义的Dialog");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取EditView中的输入内容
                EditText edit_text = dialogView.findViewById(R.id.edit_text);

                ToastUtils.showToast(AlertDialogDemo.this, edit_text.getText().toString());
            }
        });
        customizeDialog.show();
    }

    /**
     * setCustomTitle 装入自定义title
     */
    private void showCustomTitleDialog() {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(AlertDialogDemo.this);
        final View dialogTitleView = LayoutInflater.from(AlertDialogDemo.this).inflate(R.layout.alert_dialog_title, null);
        customizeDialog.setCustomTitle(dialogTitleView);
        customizeDialog.setMessage("这是一个自定义的Dialog");
        customizeDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(AlertDialogDemo.this, "点击了确定");
            }
        });
        customizeDialog.show();
    }

    /**
     * 复写Builder的create和show函数，可以在Dialog显示前实现必要设置
     * 例如初始化列表、默认选项等
     *
     * @create 第一次创建时调用
     * @show 每次显示时调用
     */
    private void showOverrideListDialog() {
        final String[] items = {"我是列表1", "我是列表2", "我是列表3", "我是列表4"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(AlertDialogDemo.this) {
            @Override
            public AlertDialog create() {
                items[0] = "列表1-初始化时被修改";
                return super.create();
            }

            @Override
            public AlertDialog show() {
                items[1] = "列表2-初始化时被修改";
                return super.show();
            }
        };
        listDialog.setTitle("我是一个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(getApplicationContext(), "点击了" + items[which]);
            }
        });
        /**
         * @setOnDismissListener Dialog销毁时调用
         * @setOnCancelListener Dialog关闭时调用
         */
        listDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                ToastUtils.showToast(getApplicationContext(), "Dialog被销毁了");
            }
        });
        listDialog.show();
    }

    private void showfillCustomizeDialog() {
        final Dialog dialog = new AlertDialog.Builder(this).create();
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_card_remind, null);
        dialog.show();
        dialog.setCancelable(false);//物理返回时，false禁止。
        dialog.getWindow().setContentView(view);
        LinearLayout btn_sure = (LinearLayout) view.findViewById(R.id.btn_sure);
        ((TextView) view.findViewById(R.id.title_text)).setText("密码重置成功");
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                ToastUtils.showToast(AlertDialogDemo.this, "退出完全自定义Dialog");
            }
        });
    }


    protected void showCallPhoneDialog() {
        final Dialog dialog = new Dialog(this, R.style.mDialog_background);
        dialog.setContentView(R.layout.alert_dialog_common);
        Window window = dialog.getWindow();
        dialog.getWindow().setGravity(Gravity.CENTER);
        TextView content = (TextView) window.findViewById(R.id.dialogcontent);
        TextView cancel = (TextView) window.findViewById(R.id.btnleft);
        TextView ok = (TextView) window.findViewById(R.id.btnright);
        content.setText("我要给10000打电话");
        cancel.setText("取消");
        ok.setText("呼叫");
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 10000));
                if (ActivityCompat.checkSelfPermission(AlertDialogDemo.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }

    protected void showCustomizeWaitingDialog() {
//        AlertDialog.Builder customizeDialog =
//                new AlertDialog.Builder(AlertDialogDemo.this);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        TypedArray a = this.obtainStyledAttributes(null,
//                com.android.internal.R.styleable.AlertDialog,
//                com.android.internal.R.attr.alertDialogStyle, 0);
//        View view = inflater.inflate(R.layout.progress_dialog_layout,null);
//        ProgressBar mProgress = (ProgressBar) view.findViewById(R.id.progress);
//        TextView mMessageView = (TextView) view.findViewById(R.id.message);
//        waitingDialog.setView(view);
//        mProgress.setProgress(100);
//        waitingDialog.setIndeterminate(true);
//        waitingDialog.show();
    }

    private void showDialogTool() {
        DialogUtils.getInstance().call(this, "我封装的Dialog", "标题", new DialogUtils.OnClickRightButtonListen() {
            @Override
            public void onClick(Dialog dialog) {
                ToastUtils.showToast(AlertDialogDemo.this, "您点击了确认");
            }
        }, new DialogUtils.OnClickLeftButtonListen() {
            @Override
            public void onClick(Dialog dialog) {
                ToastUtils.showToast(AlertDialogDemo.this, "您点击了取消");
            }
        });
    }


}
