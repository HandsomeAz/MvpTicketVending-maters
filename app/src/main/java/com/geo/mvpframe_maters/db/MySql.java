package com.geo.mvpframe_maters.db;


/**
* 数据库
* @author zengliangji
* @created 2022/6/24 10:01
*/

public class MySql {

    public static final String DATABASE_NAME = "ticket_vend.db";
    public static final String ConfigTable = "configuration";



    public static final int DATABASE_VERSION = 1;


    /**
     * 创建配置表
     */
    public static final String createConfigTable = "create table if not exists " + ConfigTable +
            "(id integer primary key autoincrement," +
            "mainModel varchar(8)," +
            "abovePlayTime int(8)," +
            "belowPlayTime int(8)," +
            "aboveAutoLoop varchar(8)," +
            "belowAutoLoop varchar(8)," +
            "videoVoice varchar(8))";


}
