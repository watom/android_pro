package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;

import com.haitao.www.myformer.model.global.FileBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FetchMediaData {
    public static final String TAG = "FetchMediaData";

    public FetchMediaData() {
    }

    private static class Builder {
        private static FetchMediaData instance = new FetchMediaData();
    }

    public static FetchMediaData getInstance() {
        return FetchMediaData.Builder.instance;
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
        ParcelFileDescriptor parcelFileDescriptor = context.getContentResolver().openFileDescriptor( Uri.parse(""), "" );
        cursor.close();
        return list;
    }

}
