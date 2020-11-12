package com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import java.io.File;

public class FetchFileData {
    static final String TAG = "FetchFileData文件数据源";
    private static final int EOF = -1;

    public static File getFileByPath(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        }
    }
}
