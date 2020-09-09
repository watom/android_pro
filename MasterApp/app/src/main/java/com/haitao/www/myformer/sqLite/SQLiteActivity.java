package com.haitao.www.myformer.sqLite;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.haitao.www.myformer.R;

/**
 * SQLite数据库只是一个文件，并不像Oracle、MySQL数据库那样需要安装、启动进程等。但是其具有绝大部分的SQL92的语法。
 * 以下情况不能使用SQLite：
 * （1）大量数据
 * （2）大量用户并发存储
 * 原因：手机的存储能力和计算能力不足，不足以充当服务器的角色。
 */
public class SQLiteActivity extends Activity implements View.OnClickListener {
    private Button sqliteCreatUserInfoTable;
    private Button sqliteInstertData;
    private Button queryInstertData;
    private Button sqliteUpdateData;
    private Button sqliteDeleteData;
    private SQLiteDatabase db;
    private String tableName = "userInfo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        //创建或打开数据库。CursorFactory为null，即使用默认的工厂
        //返回SQLiteDatabase对象，该对象的execSQL可执行任意的SQL语句。
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "TestSQLite.db3", null);
        //创建表
        String sql = "creat table " + tableName + "(_id integer primary key autoincrement,user_name varchar(255),user_sex varchar(255))";
        //执行SQL语句
        db.execSQL(sql);
    }

    private void findViews() {
        sqliteCreatUserInfoTable = (Button) findViewById(R.id.sqlite_create_userInfoTable);
        sqliteInstertData = (Button) findViewById(R.id.sqlite_instert_data);
        queryInstertData = (Button) findViewById(R.id.query_instert_data);
        sqliteUpdateData = (Button) findViewById(R.id.sqlite_update_data);
        sqliteDeleteData = (Button) findViewById(R.id.sqlite_delete_data);

        sqliteCreatUserInfoTable.setOnClickListener(this);
        sqliteInstertData.setOnClickListener(this);
        queryInstertData.setOnClickListener(this);
        sqliteUpdateData.setOnClickListener(this);
        sqliteDeleteData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == sqliteCreatUserInfoTable) {
        } else if (v == sqliteInstertData) {
            try {
                insertData("watom", "男", "180", "true");
                Cursor cursor = db.rawQuery("select * from " + tableName, null);
            }catch (SQLException e){
                db.execSQL("creat table " + tableName + "(_id integer primary key autoincrement,name varchar(255),sex varchar(255),high varchar(255),isChinese varchar(255))");
                insertData("watom", "男", "180", "true");
            }
        } else if (v == queryInstertData) {

        } else if (v == sqliteUpdateData) {

        } else if (v == sqliteDeleteData) {

        }
    }

    private void insertData(String name, String sex, String high, String isChinese) {
        db.execSQL("insert into " + tableName + " values(null,?,?,?,?)", new String[]{name, sex, high, isChinese});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
