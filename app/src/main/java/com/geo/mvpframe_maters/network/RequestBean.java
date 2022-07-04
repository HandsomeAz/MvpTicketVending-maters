package com.geo.mvpframe_maters.network;

public class RequestBean<T> {


    /**
     * code : 200000
     * serviceTime : 2022-05-23 23:37:09
     * message : SUCCESS
     * data : {"downloadUrl":"","status":1}
     */

    private int code;
    private String serviceTime;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestBean{" +
                "code=" + code +
                ", serviceTime='" + serviceTime + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
