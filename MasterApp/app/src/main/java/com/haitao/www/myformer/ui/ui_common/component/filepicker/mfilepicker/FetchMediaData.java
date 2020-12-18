package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.haitao.www.myformer.model.global.FileBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 多媒体表位于：/data/data/com.android.providers.media/databases/
 */
public class FetchMediaData {
    public static final String TAG = "FetchMediaData";

    private static FetchMediaData fetchMediaData;
    private static final FetchMediaData instance = new FetchMediaData();
    private MediaScannerConnection mediaScannerConnection = null;
    private SannerClient sannerClient = null;

    public static FetchMediaData getInstance() {
        return instance;
    }

    private FetchMediaData() {
    }

    /**
     * 获取本地所有的图片
     */
    private static List<File> getImagesByType(Context context) {
        List<File> list = new ArrayList<>();
        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE
        };
        String where = MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=?";
        String[] whereArgs = {"image/jpeg", "image/png", "image/jpg"};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, where, whereArgs, MediaStore.Images.Media.DATE_MODIFIED + " desc ");
        if (cursor == null) {
            return list;
        }
        while (cursor.moveToNext()) {
            FileBean fileBean = new FileBean();
            //获取图片名称
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            //获取图片大小
            long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
            //获取图片的绝对路径。已经过期，用openFileDescriptor替代。
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

            String path = new String(data, 0, data.length - 1);
            File file = new File(path);
            list.add(file);
        }

        cursor.close();
        return list;
    }


    /**
     * 发送广播刷新媒体库MediaStore
     * 使用条件：
     * 1.媒体库中有增加新文件，不能用于删除文件
     * 2.不会阻塞当前程序进程。当扫描大量媒体文件且实时性要求不高的情况下，适合使用该扫描方式。
     *
     * @param context  上下文
     * @param filePath 指定刷新的文件
     */
    public void scanMediaStoreByBroadcast(Context context, String filePath) {
        Uri uri = Uri.fromFile(new File(filePath));
        // 或者String uri = Uri.parse(path);
        // 或者String uri = new File(path).toURI().toString;
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(intent);
    }

    /**
     * 同步刷新媒体库MediaStore
     * 使用条件：
     * 1.媒体库中有增加新文件，不能用于删除文件
     * 2.将会阻塞当前的程序进程。当扫描少量文件，且要求立即获取扫描结果的情况下，适合使用该扫描方式。
     *
     * @param context      上下文
     * @param absolutePath 指定刷新的文件的绝对路径
     */
    public void scanMediaStoreByService(Context context, String absolutePath) {
        if (sannerClient == null) {
            sannerClient = new SannerClient(absolutePath);
        }
        if (mediaScannerConnection == null) {
            mediaScannerConnection = new MediaScannerConnection(context, sannerClient);
        }
        if (!mediaScannerConnection.isConnected()) {
            mediaScannerConnection.connect();
        }
    }

    class SannerClient implements MediaScannerConnection.MediaScannerConnectionClient {
        private String absolutePath;

        public SannerClient(String absolutePath) {
            this.absolutePath = absolutePath;
        }

        @Override
        public void onMediaScannerConnected() {
            if (absolutePath != null) {
                scanning(absolutePath, null);
            }
        }

        @Override
        public void onScanCompleted(String path, Uri uri) {
            Log.i(TAG, "扫描出来的最新文件: " + path);
            mediaScannerConnection.disconnect();
        }

        private void scanning(String absolutePath, String type) {
            Log.i(TAG, "scan " + absolutePath);
            File file = new File(absolutePath);
            if (file.isFile()) {
                mediaScannerConnection.scanFile(absolutePath, null);
                return;
            }
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File childFile : Objects.requireNonNull(file.listFiles())) {
                scanning(childFile.getAbsolutePath(), type);
            }
        }
    }
}
