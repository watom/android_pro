package com.haitao.www.myformer.ui.ui_common.component.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.SampleActivity;

public class NotificationActivity extends Activity {
    private Context context;
    Button buttonSendNotification;
    Button buttonSendNotification02;
    Button buttonSendNotification03;
    Button buttonSendNotification04;
    Button buttonSendNotification05;

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
        buttonSendNotification04 = findViewById(R.id.button_send_notification05);
    }

    private void initEvent() {
        buttonSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //创建通知渠道实例,并为它设置属性
                //通知渠道的ID,随便写
                String id = "channel_01";
                //用户可以看到的通知渠道的名字，R.string.app_name就是strings.xml文件的参数，自定义一个就好了
                CharSequence name = getString(R.string.app_name);
                //用户可看到的通知描述
                String description = getString(R.string.app_name);
                //构建NotificationChannel实例
                NotificationChannel notificationChannel = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notificationChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
                    //配置通知渠道的属性
                    notificationChannel.setDescription(description);
                    //设置通知出现时的闪光灯
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    //设置通知出现时的震动
                    notificationChannel.enableVibration(true);
                    notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100});
                    //在notificationManager中创建通知渠道
                    manager.createNotificationChannel(notificationChannel);
                }

                Notification notification = new NotificationCompat.Builder(NotificationActivity.this, id)
                        .setContentTitle("加油！")
                        .setContentText("不要被任何人打乱自己的脚步，因为没有谁会像你一样清楚和在乎自己的梦想。")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher) //定义图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//定义自己的图标
                        .build();
                manager.notify(1, notification);
            }
        });

        buttonSendNotification02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///// 第一步：获取NotificationManager
                NotificationManager manager = (NotificationManager)
                        getSystemService(NotificationActivity.this.NOTIFICATION_SERVICE);

                ///// 第二步：定义Notification
//                Intent intent = new Intent(NotificationActivity.this, SampleActivity.class);
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.sgcc.companychat");
                //PendingIntent是待执行的Intent
//                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this, 0, intent,
//                        PendingIntent.FLAG_CANCEL_CURRENT);
                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this, 0, intent,
                        0);
                Notification notification = new NotificationCompat.Builder(NotificationActivity.this, "0002")
                        .setContentTitle("title")
                        .setContentText("text")
                        .setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pi)
                        .build();
                notification.flags = Notification.FLAG_NO_CLEAR;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    manager.createNotificationChannel(new NotificationChannel("0002", "普通通知", manager.IMPORTANCE_HIGH));
                }

                /////第三步：启动通知栏，第一个参数是一个通知的唯一标识
                manager.notify(9, notification);
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
    }

}
