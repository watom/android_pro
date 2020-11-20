package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.haitao.www.myformer.BuildConfig;
import com.haitao.www.myformer.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilePickerUtil {
    private static final String TAG = "文件管理器";
    private static List<File> fileList = new ArrayList<>();

    /**
     * 判断是否是SD卡
     *
     * @return
     */
    public static boolean checkSDcard() {
        String sdStutusString = Environment.getExternalStorageState();
        if (sdStutusString.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取机身嵌入存储卡的根目录和插拔sd卡根目录
     * 若路径为/storage/emulated/0,代表机身嵌入的存储卡路径
     * 若路径为/storage/XXXX,代表插拔sd卡根目录
     *
     * @param storageType true内部 false外部
     * @return
     */
    public static String getRootPath(boolean storageType) {
        String rootDirectory = Environment.getExternalStorageDirectory().toString();
        if (storageType) {
            String secondaryStorage = System.getenv("SECONDARY_STORAGE");//可插拔SD卡
            if ((rootDirectory.equals(secondaryStorage))) {
                rootDirectory = null;
            }
        }
        return rootDirectory;
    }

//    public static List<File> geFileList(String filePath,String key) {
//        File destFile = new File(filePath);
//        if (destFile.exists()) {
//            File[] files = destFile.listFiles();
//            if (null != files) {
//                for (File file : files) {
//                    if(file.getName().equals(key)){
//                        fileList.add(file);
//                    }
//                    if (file.isDirectory()) {
////                        geFileList(file.getAbsolutePath(),key);
//                    }
//                }
//            }
//        }
//    }

    /**
     * 按照文件名称排序
     *
     * @param fileList
     * @param orderStr
     * @return
     */
    public static File[] sortFileListByName(File[] fileList, final String orderStr) {
        if (orderStr.equalsIgnoreCase("asc") || orderStr.equalsIgnoreCase("desc")) {
            Arrays.sort(fileList, (o1, o2) -> {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            });
        }
        return fileList;
    }


    /**
     * 按照文件修改日期排序
     *
     * @param fileList
     * @param orderStr
     * @return
     */
    public static File[] sortFileListByDate(File[] fileList, final String orderStr) {
        if (orderStr.equalsIgnoreCase("asc") || orderStr.equalsIgnoreCase("desc")) {
            Arrays.sort(fileList, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    long diff = f1.lastModified() - f2.lastModified();
                    if (diff > 0)
                        return 1;
                    else if (diff == 0)
                        return 0;
                    else
                        return -1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
                }

                public boolean equals(Object obj) {
                    return true;
                }
            });
        }
        return fileList;
    }

    /**
     * 按照文件大小排序
     *
     * @param fileList
     * @param orderStr
     * @return
     */
    public static File[] sortFileListByLength(File[] fileList, final String orderStr) {
        if (orderStr.equalsIgnoreCase("asc") || orderStr.equalsIgnoreCase("desc")) {
            Arrays.sort(fileList, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    long diff = f1.length() - f2.length();
                    if (diff > 0)
                        return 1;
                    else if (diff == 0)
                        return 0;
                    else
                        return -1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
                }

                public boolean equals(Object obj) {
                    return true;
                }
            });
        }
        return fileList;
    }

    /**
     * 判断是否是根路径
     * SD卡、内部存储
     */
    public static boolean isRootPath(boolean storageType) {
        String rootDirectory = Environment.getExternalStorageDirectory().toString();
        if (storageType) {
            String secondaryStorage = System.getenv("SECONDARY_STORAGE");//可插拔SD卡
            if ((rootDirectory.equals(secondaryStorage))) {
                rootDirectory = null;
            }
        }
        return false;
    }

    /**
     * 根据文件名获取对应文档的图片Uri
     */
    private Uri getUriByName(String filename) {
        Uri uri = null;
        String name = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        switch (name) {
            case ".docx":
            case ".doc":
                uri = Uri.parse("res:///" + R.drawable.icon_doc);
                break;
            case ".ppt":
                uri = Uri.parse("res:///" + R.drawable.icon_ppt);
                break;
            case ".pdf":
                uri = Uri.parse("res:///" + R.drawable.icon_pdf);
                break;
            case ".xlsx":
                uri = Uri.parse("res:///" + R.drawable.icon_execel);
                break;
            case ".xml":
                uri = Uri.parse("res:///" + R.drawable.icon_xml);
                break;
            case ".html":
                uri = Uri.parse("res:///" + R.drawable.icon_html);
                break;
            case ".rar":
                uri = Uri.parse("res:///" + R.drawable.icon_rar);
                break;
            case ".zip":
                uri = Uri.parse("res:///" + R.drawable.icon_zip);
                break;
            case ".txt":
                uri = Uri.parse("res:///" + R.drawable.icon_txt);
                break;
            case ".exe":
                uri = Uri.parse("res:///" + R.drawable.icon_exe);
                break;
            case ".mp4":
            case ".avi":
            case ".rmvb":
            case ".mov":
                uri = Uri.parse("res:///" + R.drawable.icon_videos);
                break;
            case ".mp3":
            case ".wav":
            case ".flac":
            case ".wma":
                uri = Uri.parse("res:///" + R.drawable.icon_audio);
                break;
            case ".lrc":
                uri = Uri.parse("res:///" + R.drawable.icon_lrc);
                break;
            default:
                uri = Uri.parse("res:///" + R.drawable.icon_other);
                break;
        }
        return uri;
    }

    /**
     * 根据路径获取文件名
     */
    public static String getEndFileNameByPath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    /**
     * 根据路径获取文件名
     */
    public static String getFilePathByPath(String filePath) {
        int index = filePath.indexOf("0");
        String substring = filePath.substring(index);
        String fp = substring.replaceFirst("0", "内部存储");
        return fp.replaceAll("/", ">");
    }


    /**
     * 根据文件名获取对应文档的图片Uri
     */
    public static int getResIdByName(String fileName) {
        int resId = 0;
        String suffixName = null;
        try {
            suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        } catch (Exception e) {
            suffixName = fileName;
            Log.i(TAG, "getResIdByName:[" + fileName + "] " + e.getMessage());
        }
        switch (suffixName) {
            case ".docx":
            case ".doc":
                resId = R.drawable.icon_doc;
                break;
            case ".ppt":
                resId = R.drawable.icon_ppt;
                break;
            case ".pdf":
                resId = R.drawable.icon_pdf;
                break;
            case ".xlsx":
                resId = R.drawable.icon_execel;
                break;
            case ".xml":
                resId = R.drawable.icon_xml;
                break;
            case ".html":
                resId = R.drawable.icon_html;
                break;
            case ".rar":
                resId = R.drawable.icon_rar;
                break;
            case ".zip":
                resId = R.drawable.icon_zip;
                break;
            case ".txt":
                resId = R.drawable.icon_txt;
                break;
            case ".exe":
                resId = R.drawable.icon_exe;
                break;
            case ".mp4":
            case ".avi":
            case ".rmvb":
            case ".mov":
                resId = R.drawable.icon_videos;
                break;
            case ".mp3":
                resId = R.drawable.icon_mp3;
                break;
            case ".wav":
                resId = R.drawable.icon_wav;
                break;
            case ".flac":
                resId = R.drawable.icon_flac;
                break;
            case ".wma":
                resId = R.drawable.icon_wma;
                break;
            case ".lrc":
                resId = R.drawable.icon_lrc;
                break;
            case ".dat":
                resId = R.drawable.icon_dat;
                break;
            default:
                resId = R.drawable.icon_other;
                break;
        }
        return resId;
    }

    /**
     * 更具文件类型打开对应的详情页面
     * 1.图片
     * 2.音频
     * 3.视频
     */
    public static void startActivityByName(String fileName) {
        String suffixName = null;
        try {
            suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        } catch (Exception e) {
            suffixName = fileName;
            Log.i(TAG, "getResIdByName:[" + fileName + "] " + e.getMessage());
        }
        switch (suffixName) {
            case ".png":
            case ".jpg":
            case ".bmp":
            case ".pic":
            case ".tif":
            case ".jpeg":
                //打开图片
                break;
            case ".wav":
            case ".aif":
            case ".au":
            case ".mp3":
            case ".ram":
            case ".mmf":
            case ".amr":
            case ".flac":
                //打开音频
                break;
            case ".mp4":
            case ".rmvb":
            case ".avi":
            case ".mov":
                //打开视频
                break;
            default:
                break;
        }
    }


    public static void openFile(Context context, String filePath) {
        File file = new File(filePath);
        openFile(context, file);
    }

    public static void openFile(Context context, File file) {
        if (!file.exists()) {
            Toast.makeText(context, "文件异常，无法打开！", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            //解决7.0以上的兼容性问题
            Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file);
            Log.i(TAG, "openFile: " + uri.toString());
            //获取文件file的MIME类型
            String fileType = getMIMEType(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, "application/msword");
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "未找到对应类型的应用，无法打开！", Toast.LENGTH_SHORT).show();
        }
    }

    public static void openFile01(Context context, File file) {
        if (!file.exists()) {
            Toast.makeText(context, "文件异常，无法打开！", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            //解决7.0以上的兼容性问题
            Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file);
            Log.i(TAG, "openFile: " + uri.toString());
            //获取文件file的MIME类型
            String type = getMIMEType(file);
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //设置intent的Action属性
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            //设置intent的data和Type属性
            intent.setDataAndType(uri, type);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "未找到对应类型的应用，无法打开！", Toast.LENGTH_SHORT).show();
        }
    }

    private static String getMIMEType(File file) {
        String type = "*/*";
        String fileName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fileName.substring(dotIndex, fileName.length()).toLowerCase();
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private static String[][] MIME_MapTable = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };
}
