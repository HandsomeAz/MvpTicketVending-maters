package com.geo.mvpframe_maters.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.geo.mvpframe_maters.bean.ConfigInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 单例模式
 */
public class DBManager {

    private static DBManager singleton;
    private DBHelper mHelper;
    private SQLiteDatabase mSQLiteDatabase;

    /**
     * 私有构造器
     *
     * @param context
     */
    private DBManager(Context context) {
        mHelper = new DBHelper(context);
        mSQLiteDatabase = mHelper.getWritableDatabase();

    }

    public static DBManager getManager(Context context) {
        if (singleton == null) {
            synchronized (DBManager.class) {
                singleton = new DBManager(context);
            }
        }
        return singleton;
    }

    public void CrateTable()
    {
        mSQLiteDatabase.execSQL(MySql.createConfigTable);

    }


    /**
     * 删除所有配置
     */
    public void removeAllConfig() {
        mSQLiteDatabase.beginTransaction();
        mSQLiteDatabase.execSQL("delete from " + MySql.ConfigTable + "");
        mSQLiteDatabase.setTransactionSuccessful();
        mSQLiteDatabase.endTransaction();
    }


    /**
     * 查询配置信息
     */
    public ConfigInfo queryConfig() {
        ConfigInfo configBean = new ConfigInfo();
        Cursor cursor = mSQLiteDatabase.rawQuery("select * from " + MySql.ConfigTable,
                new String[]{});
        if (cursor.getCount() == 0) {
            return configBean;
        }
        while (cursor != null && cursor.moveToNext()) {
            configBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
            configBean.setMainModel(cursor.getString(cursor.getColumnIndex("mainModel")));
            configBean.setAbovePlayTime(cursor.getInt(cursor.getColumnIndex("abovePlayTime")));
            configBean.setBelowPlayTime(cursor.getInt(cursor.getColumnIndex("belowPlayTime")));
            configBean.setAboveAutoLoop(cursor.getString(cursor.getColumnIndex("aboveAutoLoop")));
            configBean.setBelowAutoLoop(cursor.getString(cursor.getColumnIndex("belowAutoLoop")));
            configBean.setVideoVoice(cursor.getString(cursor.getColumnIndex("videoVoice")));
        }

        return configBean;
    }





    /**
     * 添加配置信息
     *
     * @param bean
     */
    public void addConfigInfo(ConfigInfo bean){
        mSQLiteDatabase.beginTransaction();
        mSQLiteDatabase.execSQL("delete from " + MySql.ConfigTable + "");
        mSQLiteDatabase.execSQL("insert into " + MySql.ConfigTable + " values(null,?,?,?,?,?,?)",
                new Object[]{
                        bean.getMainModel(),
                        bean.getAbovePlayTime(),
                        bean.getBelowPlayTime(),
                        bean.getAboveAutoLoop(),
                        bean.getBelowAutoLoop(),
                        bean.getVideoVoice()
        });
        mSQLiteDatabase.setTransactionSuccessful();
        mSQLiteDatabase.endTransaction();



    }
}
