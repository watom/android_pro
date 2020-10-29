package com.haitao.www.myformer.ui.ui_common.component.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.ComponentActivity;
import com.haitao.www.myformer.utils.NotificationUtil;

/**
 * 通知栏消息操作
 */
public class NotificationActivity extends Activity {
    private Context context;
    Button buttonSendNotification;
    Button buttonSendNotification02;
    Button buttonSendNotification03;
    Button buttonSendNotification04;
    Button buttonSendNotification05;
    Button cleanAllNotification;
    Button cleanOneNotification;
    private NotificationUtil notificationUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.context = this;
        initView();
        initEvent();
    }

    private void initView() {
        buttonSendNotification = findViewById(R.id.button_send_notification);
        buttonSendNotification02 = findViewById(R.id.button_send_notification02);
        buttonSendNotification03 = findViewById(R.id.button_send_notification03);
        buttonSendNotification04 = findViewById(R.id.button_send_notification04);
        buttonSendNotification05 = findViewById(R.id.button_send_notification05);
        cleanOneNotification = findViewById(R.id.clean_one_notification);
        cleanAllNotification = findViewById(R.id.clean_all_notification);
    }

    private void initEvent() {
        buttonSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationUtil = new NotificationUtil(context);
                String channelId = notificationUtil.createNotificationChannel("1111", "第一类消息", "00000");
                NotificationCompat.Builder builder = notificationUtil.buildNotification(channelId, "坚持", "锲而不舍，金石可镂！");
                Notification notification = builder.build();
                notificationUtil.show(notification, 502);
            }
        });

        buttonSendNotification02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationUtil = new NotificationUtil(context);
                String channelId = notificationUtil.createNotificationChannel("2222", "第二类消息", "00000");
                PendingIntent pendingIntent = notificationUtil.setClickRespond(ComponentActivity.class);
                NotificationCompat.Builder builder = notificationUtil.buildNotification(pendingIntent, channelId, "加油", null);
                notificationUtil.setBigText(builder, "不要被任何人打乱自己的脚步，因为没有谁会像你一样清楚和在乎自己的梦想。");
                Notification notification = builder.build();
                notificationUtil.show(notification, 100);
            }
        });


        buttonSendNotification03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("bx.companychat.BROADCAST_ACTION");
                sendBroadcast(intent);
                Log.i("watom666", "onClick: 广播已发送");
            }
        });

        buttonSendNotification04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.sgcc.companychat");
                if (intent != null) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

        buttonSendNotification05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE); 
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                intent.setClass(context, CrossAppActivity.class);
//                intent.setPackage("com.sgcc.companychat");
//                intent.setAction(Intent.ACTION_MAIN);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//
//                ComponentName name = new ComponentName(context.getPackageName(), context.getPackageName() + "." + context.getLocalClassName());
//                intent.setComponent(name);
            }
        });


        cleanAllNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationUtil.cancelAll();
            }
        });
        cleanOneNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationUtil.cancel(502);
            }
        });
    }
}
