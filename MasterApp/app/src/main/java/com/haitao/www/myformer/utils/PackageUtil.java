package com.haitao.www.myformer.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.util.List;

public class PackageUtil {

    /**
     * 获取App的名称
     */
    public static String getAppName(Context context) {
        String appName = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            int labelRes = applicationInfo.labelRes;
            appName = context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appName;
    }

    /**
     * 获取正在运行App的版本号
     */
    public static Long getRunVersionCode(Context context) {
        if (context != null) {
            try {
                long longVersionCode = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    longVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode();
                }
                return longVersionCode;
            } catch (PackageManager.NameNotFoundException ignored) {
                Log.i("企信", "没有找到版本文件");
            }
        }
        return new Long((long) 0);
    }

    /**
     * 检查包是否存在
     */
    private boolean checkPackInfo(Context context, String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 通过包名拉起其他的APP
     */
    private void launchOtherApp(Context context, String packname) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packname);
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 判断其他的APP是否在后台运行，如果在后台运行中，直接打开APP
     *
     * @param context
     * @param packageName
     */
    public static boolean openPackage(Context context, String packageName) {
        Context pkgContext = getPackageContext(context, packageName);
        Intent intent = getAppOpenIntentByPackageName(context, packageName);
        if (pkgContext != null && intent != null) {
            pkgContext.startActivity(intent);
            return true;
        }
        return false;
    }

    public static Intent getAppOpenIntentByPackageName(Context context, String packageName) {
        //Activity完整名
        String mainAct = null;
        //根据包名寻找
        PackageManager pkgMag = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);

        List<ResolveInfo> list = pkgMag.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
        for (int i = 0; i < list.size(); i++) {
            ResolveInfo info = list.get(i);
            if (info.activityInfo.packageName.equals(packageName)) {
                mainAct = info.activityInfo.name;
                break;
            }
        }
        if (DataUtil.isEmpty(mainAct)) {
            return null;
        }
        intent.setComponent(new ComponentName(packageName, mainAct));
        return intent;
    }

    /**
     * 获取本地未安装的App版本号
     */
    public static long getLocalPackageVersion(Context context, String filePath) {
        PackageInfo localPackageInfo = null;
        PackageManager pm = context.getPackageManager();
        String localAppName = getLocalFileName(filePath);
        localPackageInfo = pm.getPackageArchiveInfo(filePath + "/" + localAppName, PackageManager.GET_ACTIVITIES);
        if (!DataUtil.isEmpty(localPackageInfo)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                long longVersionCode = localPackageInfo.getLongVersionCode();
                return longVersionCode;
            }
        }
        return 0;
    }

    public static boolean hasLocalInstallPackage(String path) {
        return !DataUtil.isEmpty(getLocalFileName(path));
    }

    /**
     * 获取本地安装包的名称
     */
    public static String getLocalFileName(String path) {
        String appName = null;
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null) {
            Log.i("企信", "暂无企信安装包");
        } else {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (suffix.equals("apk")) {
                    appName = fileName;
                }
            }
        }
        return appName;
    }

    public static Context getPackageContext(Context context, String packageName) {
        Context pkgContext = null;
        if (context.getPackageName().equals(packageName)) {
            pkgContext = context;
        } else {
            // 创建第三方应用的上下文环境
            try {
                pkgContext = context.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return pkgContext;
    }

    /**
     * 获取PackageInfo
     * 包名也可以通过：BuildConfig.APPLICATION_ID获取
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 获取ApplicationInfo
     */
    public static ApplicationInfo getAppInfo(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        return applicationInfo;
    }

    /**
     * 获取App应用图标资源ID
     */
    public static int getAppLogoResourceId(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        int icon = packageInfo.applicationInfo.icon;
        return icon;
    }

    /**
     * 获取App应用图标 bitmap
     */
    public static Bitmap getAppLogoBitmap(Context context) {
        Drawable appLogoDrawable = getAppLogoDrawable(context);
        BitmapDrawable bd = (BitmapDrawable) appLogoDrawable;
        Bitmap bitmap = bd.getBitmap();
        return bitmap;
    }

    /**
     * 获取App应用图标 bitmap
     */
    public static Drawable getAppLogoDrawable(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        Drawable drawable = packageManager.getApplicationIcon(applicationInfo);
        return drawable;
    }
}
