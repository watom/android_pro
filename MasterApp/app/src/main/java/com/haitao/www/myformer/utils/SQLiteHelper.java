package com.haitao.www.myformer.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.haitao.www.myformer.sqLite.ListBean;


public class SQLiteHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "BxLibrary";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "app_manage";
    private String sql = "CREATE TABLE " + TABLE_NAME
            + "(_id INTEGER PRIMARY KEY,"
            + " name VARCHAR(255)  NOT NULL,"
            + " title VARCHAR(255),"
            + " icon VARCHAR(255),"
            + " link VARCHAR(255),"
            + " provideUser VARCHAR(255),"
            + " resourceId VARCHAR(255),"
            + " grade VARCHAR(255),"
            + " resourceType VARCHAR(255),"
            + " ext1 VARCHAR(255),"
            + " ext2 VARCHAR(255),"
            + " ext3 VARCHAR(255),"
            + " sort VARCHAR(255),"
            + " sortSNow VARCHAR(255),"
            + " sortAfter VARCHAR(255),"
            + " sortAfter VARCHAR(255))";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    //获取游标
    public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }

    //插入一条记录
    public long insert(ListBean bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", bean.getName());
        cv.put("title", bean.getTitle());
        cv.put("icon", bean.getIcon());
        cv.put("link", bean.getLink());
        cv.put("provideUser", bean.getProvideUser());
        cv.put("resourceId", bean.getResourceId());
        cv.put("grade", bean.getGrade());
        cv.put("resourceType", bean.getResourceType());
        cv.put("ext1", bean.getExt1());
        cv.put("ext2", bean.getExt2());
        cv.put("ext3", bean.getExt3());
        cv.put("sort", bean.getSort());
        cv.put("sortSNow", bean.getSortSNow());
        cv.put("sortAfter", bean.getSortAfter());
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
    }

    //根据条件查询
    public Cursor query(String key, String value) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + key + " LIKE ?", new String[]{value});
        return cursor;
    }

    //删除记录
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id = ?";
        String[] whereValue = {Integer.toString(id)};
        db.delete(TABLE_NAME, where, whereValue);
    }

    //更新记录
    public void update(int id, String msg1, String msg2, String msg3) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id = ?";
        String[] whereValue = {Integer.toString(id)};
        ContentValues cv = new ContentValues();
        cv.put("Msgone", msg1);
        cv.put("Msgtwo", msg2);
        cv.put("Msgthree", msg3);
        db.update(TABLE_NAME, cv, where, whereValue);
    }
}
