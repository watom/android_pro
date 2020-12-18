package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.model.global.FileBean;
import com.haitao.www.myformer.utils.PermissionUtil;
import com.haitao.www.myformer.utils.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class FetchFileData {
    public static final String TAG = "FetchFileData文件数据源";
    private static final int EOF = -1;

    public FetchFileData() {
    }

    private static class Builder {
        private static FetchFileData instance = new FetchFileData();
    }

    public static FetchFileData getInstance() {
        return FetchFileData.Builder.instance;
    }

    public List<File> getFileInfoByType(Activity activity, FileType fileType) {
        List<File> fileList = null;
        switch (fileType) {
            case IMAGE:
                fileList = getImagesByType(activity);
                break;
            case VIDEO:
                fileList = getVideosByType(activity);
                break;
            case AUDIO:
                ToastUtils.showToast(activity, "打开音频");
                break;
            case FILE:
                fileList = getFilesByType(activity);
                break;
            case WINRAR:
                ToastUtils.showToast(activity, "打开压缩包");
                break;
            case APK:
                ToastUtils.showToast(activity, "打开APK");
                break;
            case OTHER:
                ToastUtils.showToast(activity, "打开其他");
                break;
            case RECENTLY:
                ToastUtils.showToast(activity, "打开最近");
                break;
        }
        return fileList;
    }

    /**
     * 获取本地所有文件
     */
    private static List<File> getFilesByType(Context context) {
        Cursor cursor = null;
        List<File> list = new ArrayList<>();
        try {
            cursor = context.getContentResolver().query(MediaStore.Files.getContentUri("external"),
                    null, null, null, null);
            if (cursor == null) {
                return list;
            }
            int columnIndexOrThrow_ID = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
            int columnIndexOrThrow_MIME_TYPE = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE);
            int columnIndexOrThrow_DATA = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
            int columnIndexOrThrow_SIZE = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE);
            int columnIndexOrThrow_DATE_MODIFIED = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED);

            while (cursor.moveToNext()) {
                FileBean fileBean = new FileBean();
                //获取图片的名称
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));

                //获取图片的生成日期
                byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                String path = new String(data, 0, data.length - 1);
                File file = new File(path);
                list.add(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
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
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String path = new String(data, 0, data.length - 1);
            File file = new File(path);
            list.add(file);
        }
        cursor.close();
        return list;
    }

    /**
     * 获取所有文件
     */
    private List<FileBean> getFilesByTypes(Context context) {
        List<FileBean> files = new ArrayList<>();
        // 扫描files文件库
        Cursor c = null;
        try {
            ContentResolver mContentResolver = context.getContentResolver();
            Uri external = MediaStore.Files.getContentUri("external");
            external.getAuthority();
            c = mContentResolver.query(external, null, null, null, null);
            int columnIndexOrThrow_ID = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
            int columnIndexOrThrow_MIME_TYPE = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE);
            int columnIndexOrThrow_DATA = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
            int columnIndexOrThrow_SIZE = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE);
            int columnIndexOrThrow_DATE_MODIFIED = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED);

            int tempId = 0;
            while (c.moveToNext()) {
                String path = c.getString(columnIndexOrThrow_DATA);
                String minType = c.getString(columnIndexOrThrow_MIME_TYPE);
                Log.i("FileManager", "path:" + path);
                int position_do = path.lastIndexOf(".");
                if (position_do == -1) {
                    continue;
                }
                int position_x = path.lastIndexOf(File.separator);
                if (position_x == -1) {
                    continue;
                }
                String displayName = path.substring(position_x + 1, path.length());
                long size = c.getLong(columnIndexOrThrow_SIZE);
                long modified_date = c.getLong(columnIndexOrThrow_DATE_MODIFIED);
                File file = new File(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return files;
    }


    /**
     * 获取本地所有的视频
     */
    public List<File> getVideosByType(Context context) {
        String[] projection = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            projection = new String[]{
                    MediaStore.Video.Media.DATA,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.DURATION,
                    MediaStore.Video.Media.SIZE
            };
        }
        //全部图片
        String where = MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                + MediaStore.Video.Media.MIME_TYPE + "=?";
        String[] whereArgs = {"video/mp4", "video/3gp", "video/aiv", "video/rmvb", "application/vnd.rn-realmedia", "aapplication/vnd.rn-realmedia", "video/quicktime",
                "video/vob", "video/flv", "video/mkv", "video/mov", "video/mpg", "video/x-ms-wmv", "video/x-msvideo", "video/3gpp", "video/x-matroska"};
        List<File> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection, where, whereArgs, MediaStore.Video.Media.DATE_ADDED + " DESC ");
        if (cursor == null) {
            return list;
        }
        try {
            while (cursor.moveToNext()) {
                list.clear();
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)); // 大小
                if (size < 600 * 1024 * 1024) {//<600M
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)); // 路径
                    long duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)); // 时长
//                    fileBean.setTitle(title);
//                    fileBean.setIcon(path);
//                    fileBean.setFilePath(path);
//                    fileBean.setChecked(false);
//                    fileBean.setTime(System.currentTimeMillis() + "");
//                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
//                    format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
//                    fileBean.setDuration(format.format(duration));
                    File file = new File(path);
                    list.add(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return list;
    }

    /**
     * 4.4版本后，SAF提供统一的选择器界面，以便用户浏览其他应用提供的所有文件。
     *
     * @param activity
     * @param accessType  创建的文件类型。如"image/*"
     * @param requestCode  {@link Activity#onActivityResult}
     */
    public static void openFileByPrimeval(Activity activity, String accessType, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType(accessType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 4.4版本后，SAF提供由系统控制的创建文件。
     * @param activity
     * @param accessType 创建的文件类型。如"image/*"
     * @param fileName   创建的文件名称
     * @param requestCode {@link Activity#onActivityResult}
     */
    public static void createFileByPrimeval(Activity activity, String accessType, String fileName, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.setType(accessType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     * @author paulburke
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     * @author paulburke
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     * @author paulburke
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     * @author paulburke
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } catch (IllegalArgumentException ex) {
            Log.i(TAG, String.format(Locale.getDefault(), "getDataColumn: _data - [%s]", ex.getMessage()));
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.<br>
     * <br>
     * Callers should check whether the path is local before assuming it
     * represents a local file.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(id)) {
                    try {
                        final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                        return getDataColumn(context, contentUri, null, null);
                    } catch (NumberFormatException e) {
                        Log.i(TAG, e.getMessage());
                        return null;
                    }
                }
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Copies one file into the other with the given paths.
     * In the event that the paths are the same, trying to copy one file to the other
     * will cause both files to become null.
     * Simply skipping this step if the paths are identical.
     */
    public static boolean copyFile(FileInputStream fileInputStream, String outFilePath) throws IOException {
        if (fileInputStream == null) {
            return false;
        }
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = fileInputStream.getChannel();
            outputChannel = new FileOutputStream(new File(outFilePath)).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (fileInputStream != null) fileInputStream.close();
            if (inputChannel != null) inputChannel.close();
            if (outputChannel != null) outputChannel.close();
        }
    }

    /**
     * Copies one file into the other with the given paths.
     * In the event that the paths are the same, trying to copy one file to the other
     * will cause both files to become null.
     * Simply skipping this step if the paths are identical.
     */
    public static void copyFile(@NonNull String pathFrom, @NonNull String pathTo) throws IOException {
        if (pathFrom.equalsIgnoreCase(pathTo)) {
            return;
        }

        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        try {
            inputChannel = new FileInputStream(new File(pathFrom)).getChannel();
            outputChannel = new FileOutputStream(new File(pathTo)).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
        } finally {
            if (inputChannel != null) inputChannel.close();
            if (outputChannel != null) outputChannel.close();
        }
    }


    private static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HHmmssSS");

    /**
     * 根据时间戳创建文件名
     */
    public static String getCreateFileName(String prefix) {
        long millis = System.currentTimeMillis();
        return prefix + sf.format(millis);
    }

    /**
     * 根据时间戳创建文件名
     */
    public static String getCreateFileName() {
        long millis = System.currentTimeMillis();
        return sf.format(millis);
    }


    /**
     * 重命名相册拍照
     */
    public static String rename(String fileName) {
        String temp = fileName.substring(0, fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return new StringBuffer().append(temp).append("_").append(getCreateFileName()).append(suffix).toString();
    }

    /**
     * 图片类文件
     * 绝对路径转Uri
     *
     * @param context   this
     * @param imageFile file:///storage/emulated/0/Android/data/com.zn_android.zn/cache/PostPicture/20170905193015.jpg
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath},
                null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * 图片类文件
     * Uri转绝对路径
     *
     * @param context          this
     * @param selectedVideoUri content://media/external/images/media/212304
     */
    public static String getFilePathFromContentUri(Context context, Uri selectedVideoUri) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }
}
