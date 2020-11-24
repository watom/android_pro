package com.haitao.www.myformer.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {
    public static final String TAG = "权限申请";

    public static void setCheck(@NonNull Activity activity, @NonNull String permissionName, @IntRange(from = 0) int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.checkSelfPermission(activity, permissionName);
            ActivityCompat.requestPermissions(activity, new String[]{permissionName}, requestCode);
        } else {
            Log.i(TAG, permissionName + "已经获取权限");
        }
    }

    public static void setCheck(@NonNull Activity activity, @NonNull String[] permissionList, @IntRange(from = 0) int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> list = new ArrayList<>();
            for (String permission : permissionList) {
                int state = ContextCompat.checkSelfPermission(activity, permission);
                if (state != PackageManager.PERMISSION_GRANTED) {
                    list.add(permission);
                }
            }
            if (!DataUtil.isEmpty(list)) {
                String[] pList = new String[list.size()];
                list.toArray(pList);
                ActivityCompat.requestPermissions(activity, pList, requestCode);
            } else {
                ActivityCompat.requestPermissions(activity, permissionList, requestCode);
            }
        } else {
            Log.i(TAG, "通过清单文件AndroidManifest.xml获取权限：/n" + permissionList);
        }
    }

    public static void setRequestResult(@NonNull Activity activity, @NonNull String[] permissions, @NonNull int[] grantResults, Callback callback) {
        boolean can = true;
        boolean todo = true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                can = false;
                todo = ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i]);
            }
        }

        if (can) {
            callback.todoAfter();
        } else if (!todo) {
            showWaringDialog(activity);
        }
    }

    private static void showWaringDialog(Activity activity) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("提示")
                .setMessage("请前往设置->应用->" + PackageUtil.getAppName(activity) + "->权限中打开相关权限，否则功能无法正常运行！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings(activity);
                    }
                }).show();
    }

    private static void startAppSettings(Activity activity) {
        ToastUtils.showToast(activity, "打开权限界面");
    }

    public interface Callback {
        void todoAfter();
    }
}
