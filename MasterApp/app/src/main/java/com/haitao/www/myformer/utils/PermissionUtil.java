package com.haitao.www.myformer.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.haitao.www.myformer.BuildConfig;

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
        String appName = PackageUtil.getAppName(activity);
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("提示")
                .setIcon(PackageUtil.getAppLogoDrawable(activity))
                .setMessage("请前往设置->应用->" + appName + "->权限中打开相关权限，否则功能无法正常运行！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings(activity);
                    }
                }).show();
    }

    private static void startAppSettings(Activity activity) {
        Intent intent = getLocalIntent(activity);
        activity.startActivity(intent);
    }

    /**
     * 只能兼容到4大品牌的，包括：华为，魅族，小米，Nexus。
     * 注意：暂未找到oppon，vivo的相关意图。
     */
    public static Intent getLocalIntent(Activity context) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String deviceBrand = SystemUtil.getDeviceBrand();
        if (deviceBrand.equalsIgnoreCase("HUAWEI")) {
            ComponentName componentName =  new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
            intent.setComponent(componentName);
        }else if(deviceBrand.equalsIgnoreCase("MEIZU")){
            intent.setAction("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("packageName", context.getPackageName());
        }else if(deviceBrand.equalsIgnoreCase("XIAOMI")){
            intent.setAction("miui.intent.action.APP_PERM_EDITOR");
            intent.putExtra("extra_pkgname", context.getPackageName());
            ComponentName componentName =  new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent.setComponent(componentName);
        }else{
            if (Build.VERSION.SDK_INT >= 9) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package",context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        }
        return intent;
    }

    public interface Callback {
        void todoAfter();
    }
}
