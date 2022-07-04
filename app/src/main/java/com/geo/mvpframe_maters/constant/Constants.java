package com.geo.mvpframe_maters.constant;


public class Constants {

    /**
     * 连接socket的 ip地址
     */
//    public static String REMOTE_IP = "192.168.4.10";//机器同一IP
    public static String REMOTE_IP = "rjb.geoelectron.com";
    /**
     * socket 服务端暴露的端口
     */
    public static String getUpdateUrl="http://"+REMOTE_IP+":15050"+"/version-data/update?versionCode=";

    public static String getNewUpdateUrl="http://"+REMOTE_IP+":15050"+"/version-data/NewUpdate?versionCode=";

    public static String AppClient="";
    public static final int REMOTE_PORT = 9000;
//    public static final int REMOTE_PORT = 9991;


    public static String BaseUrl = "http://sp.zhjckx.com/";

    //登录账号
    public static String ACCOUNT_KEY ="";
    //登录密码
    public static String PASSWORD_KEY ="";


    public static String departStationCode ="001";
}
