package com.haitao.www.myformer.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.haitao.www.myformer.R;

/**
 * 通知栏消息操作工具类
 */
public class NotificationUtil {
    private Context context;
    private NotificationManagerCompat notificationManager;

    public NotificationUtil(Context context) {
        this.context = context;
        this.notificationManager = getNotificationManager(context);
    }

    /**
     * 1.获得通知栏管理工具
     */
    public NotificationManagerCompat getNotificationManager(Context context) {
        return NotificationManagerCompat.from(context);
    }

    public String createNotificationChannel(String channelId, String channelName, String description) {
        return createNotificationChannel(channelId, channelName, description, NotificationManager.IMPORTANCE_HIGH);
    }

    /**
     * 1.1 如果api大于android8.0就创建渠道返回渠道id，否则返回空。
     *
     * @param channelId  自定义值渠道ID，但必须是唯一的
     * @param channelName  自定义渠道名称，可重复
     * @param description
     * @param level  重要性 {@link NotificationCompat#PRIORITY_MAX}
     * @return
     */
    public String createNotificationChannel(String channelId, String channelName, String description, int level) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, level);
            channel.setDescription(description);
            channel.enableLights(true);         //设置通知出现时的闪光灯
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);      //设置通知出现时的震动
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100});
            notificationManager.createNotificationChannel(channel);

            return channelId;
        } else {
            return null;
        }
    }

    /**
     * 设置点击通知栏消息后，回调的响应
     * @param cls  打开某个类名
     * @return 返回意图
     */
    public PendingIntent setClickRespond(Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    public  NotificationCompat.Builder buildNotification(String channelId, String contentTitle, String contentText) {
        return buildNotification(null, channelId, contentTitle, contentText);
    }

    /**
     * 2.构建通知栏构造器,并给构造器设置参数
     */
    public  NotificationCompat.Builder buildNotification(PendingIntent pendingIntent, String channelId, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[]{0, 1000, 1000, 1000})       //设置振动， 需要添加权限  <uses-permission android:name="android.permission.VIBRATE"/>
                .setLights(Color.GREEN,1000,1000)    //设置前置LED灯进行闪烁， 第一个为颜色值  第二个为亮的时长  第三个为暗的时长
                .setDefaults(NotificationCompat.DEFAULT_ALL)       //使用默认效果， 会根据手机当前环境播放铃声， 是否振动
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setPriority(NotificationCompat.PRIORITY_HIGH)     //设置这条通知的重要程度， 有五个值可以选择
                .setAutoCancel(true);                              //true：会在点击通知后自动移除通知，false/默认：在点击后依然存在
        if (!DataUtil.isEmpty(pendingIntent)) {
            builder.setContentIntent(pendingIntent);
        }
        return builder;
    }

    /**
     * 2.1 封装长文本信息的
     * @param builder
     * @param text
     */
    public void setBigText(NotificationCompat.Builder builder,String text) {
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(text));
    }

    /**
     * 3.发送通知请求
     *
     * @param notification
     * @param id           自定义notification的ID，方便找到取消cancel某条通知
     */
    public void show(Notification notification, int id) {
        notificationManager.notify(id, notification);
    }

    /**
     * 取消某条通知栏消息
     *
     * @param id show方法中自定义的ID
     */
    public void cancel(int id) {
        notificationManager.cancel(id);
    }

    /**
     * 删除所有通知栏消息
     */
    public void cancelAll() {
        notificationManager.cancelAll();
    }
}
