package com.geo.mvpframe_maters.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String encode(String str ) {
//        String str = id + num;
        byte[] digest = null;
        try {
            digest = MessageDigest.getInstance("MD5").digest(
                    str.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder md5 = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            if ((b & 0xFF) < 0x10)
                md5.append("0");
            md5.append(Integer.toHexString(b & 0xFF));
        }
        return md5.toString();
    }

    public static void main(String[] args){  
        String passwd =  "123456";  
        System.out.println(passwd + " 加密后为： " + encode(passwd)) ;

        String code = "rvGD3tVzO7saOCa6h0xgfQ==";
        System.out.println(code + " 加密后为： " + encode(code)) ;
    }  
}
