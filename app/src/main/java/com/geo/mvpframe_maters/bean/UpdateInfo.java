package com.geo.mvpframe_maters.bean;

/**
 * @package: com.geo.mvpframe_maters.bean
 * 创建人： created by zlj
 * 时间：2022/05/23 23
 */
public class UpdateInfo {

    /**
     * downloadUrl :
     * status : 1
     */

    private String downloadUrl;
    private int status;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "downloadUrl='" + downloadUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
