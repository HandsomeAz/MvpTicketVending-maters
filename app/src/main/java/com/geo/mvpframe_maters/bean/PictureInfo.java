package com.geo.mvpframe_maters.bean;

/**
 * 图片信息
 * 创建人： created by zlj
 * 时间：2022/06/21 21
 */
public class PictureInfo {

    /**
     * pictureNo : 1482632838657888256
     * pictureUrl : https://jt-cwxt.oss-cn-shenzhen.aliyuncs.com/test/files/迎虎年（竖版）-1642322187765.jpg
     * rotationNo : 101
     * fileType : 1
     */

    private String pictureNo;
    private String pictureUrl;
    private String rotationNo;
    private int fileType;

    public String getPictureNo() {
        return pictureNo;
    }

    public void setPictureNo(String pictureNo) {
        this.pictureNo = pictureNo;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRotationNo() {
        return rotationNo;
    }

    public void setRotationNo(String rotationNo) {
        this.rotationNo = rotationNo;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "PictureInfo{" +
                "pictureNo='" + pictureNo + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", rotationNo='" + rotationNo + '\'' +
                ", fileType=" + fileType +
                '}';
    }
}
