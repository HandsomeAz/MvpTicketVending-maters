package com.geo.mvpframe_maters.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreUtil {
    private static final String TAG="TAG";
    private static final String KEY_LOGIN="KEY_LOGIN";

    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private static SharePreUtil mSharePreUtil;
    private final Context context;
    public SharePreUtil(Context context){
        this.context=context.getApplicationContext();
        mPreferences=this.context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        mEditor=mPreferences.edit();
    }
    //单例简单实现
    public static SharePreUtil getInstance(Context context){
        if (mSharePreUtil ==null){
            mSharePreUtil =new SharePreUtil(context);
        }
        return mSharePreUtil;
    }
    //是否登录
//    public boolean isLogin(){ return  getBoolean(KEY_LOGIN,defaultValue:false);}

    public  boolean isLogin(){return getBoolean(KEY_LOGIN,false);}


    //更改登录状态
    public void setLogin(boolean value){putBoolean(KEY_LOGIN,value);}
    private void put(String key, String value){
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key,value);
        mEditor.commit();
    }
//    private String get(String key){return mPreferences.getString(key,defValue:" ");}
    private String get(String key){return mPreferences.getString(key,"");}
    public boolean getBoolean(String key, boolean defaultValue){
        return mPreferences.getBoolean(key, defaultValue);
    }



    public void putString( String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getString( String key,String strDefault) {
        return mPreferences.getString(key, strDefault);
    }

    public void putInt(Context context, String key, int id) {
        mEditor.putInt(key,id);
        mEditor.commit();
    }

    public int getInt( String key,int intDefault) {
        return mPreferences.getInt(key,intDefault);
    }
}
