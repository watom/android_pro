package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.DataUtil;

public class FilePickerUtil {
    private static final String TAG = "文件管理器";

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
     * 得到根路径
     *
     * @param storageType true内部 false外部
     * @return
     */
    public static String getRootPath(boolean storageType) {
        String rootDirectory = Environment.getExternalStorageDirectory().toString();
        if (storageType) {
            String secondaryStorage = System.getenv("SECONDARY_STORAGE");
            if ((rootDirectory.equals(secondaryStorage))) {
                rootDirectory = null;
            }
        }
        return rootDirectory;
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
        return filePath.substring(filePath.lastIndexOf("/")+1);
    }

    /**
     * 根据路径获取文件名
     */
    public static String getFilePathByPath(String filePath) {
        int index = filePath.indexOf("0");
        String substring = filePath.substring(index);
        String fp = substring.replaceFirst("0", "内部存储");
        return fp.replaceAll("/",">");
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

}
