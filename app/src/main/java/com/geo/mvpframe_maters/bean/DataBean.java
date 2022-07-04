package com.geo.mvpframe_maters.bean;


import android.annotation.SuppressLint;
import android.content.Context;


import com.geo.mvpframe_maters.db.DBManager;
import com.geo.mvpframe_maters.utils.MyFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DataBean {

    private static DataBean mDataBean =null;

    public static  DataBean getManager() {
        if (mDataBean == null) {
            synchronized (DataBean.class) {
                mDataBean = new DataBean();
            }
        }
        return mDataBean;
    }
    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }

    public  List<String> getPicture2() {
        File batchImportDir;
        List<String> list = new ArrayList<>();
        File[] picFiles;
        // 1、获取导入目录 /sdcard/Face-Import
        batchImportDir = MyFileUtils.getPictureDirectory2();
        // 2、遍历该目录下的所有文件
        picFiles = batchImportDir.listFiles();
        if (picFiles!=null){
            if (picFiles.length>0){
                for (File file:picFiles){
                    if (checkIsImageFile(file.getPath())){
                        list.add(file.getPath());
                    }
                }
            }
        }
        return list;
    }

    public  List<String> getPicture() {
        File batchImportDir;
        List<String> list = new ArrayList<>();
        File[] picFiles;
        // 1、获取导入目录 /sdcard/Face-Import
        batchImportDir = MyFileUtils.getPictureDirectory();
        // 2、遍历该目录下的所有文件
        picFiles = batchImportDir.listFiles();
        if (picFiles!=null){
            if (picFiles.length>0){
                for (File file:picFiles){
                    if (checkIsImageFile(file.getPath())){
                        list.add(file.getPath());
                    }
                }
            }
        }
        return list;
    }
    /**
     * 检查扩展名，得到图片格式的文件
     * @param fName  文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    private static boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg")|| FileEnd.equals("bmp") ) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    public  List<String> getVideoList() {

        List<String> list = new ArrayList<>();
        File[] picFiles;
        // 1、获取导入目录 /sdcard/Face-Import
        File batchImportDir = MyFileUtils.getVideoDirectory();
        // 2、遍历该目录下的所有文件
        picFiles = batchImportDir.listFiles();
        if (picFiles!=null){
            if (picFiles.length>0){
                for (File file:picFiles){
                    if (checkVideoFile(file.getPath())){
                        list.add(file.getPath());
                    }
                }
            }
        }
        return list;
    }
    /**
     * 检查扩展名，得到图片格式的文件
     * @param fName  文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    private  boolean checkVideoFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("mp4") ) {
            isImageFile = true;
        }
        return isImageFile;
    }
}
