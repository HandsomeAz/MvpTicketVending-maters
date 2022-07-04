package com.geo.mvpframe_maters.bean;

/**
 * 创建人： created by zlj
 * 时间：2022/07/03 00
 */
public class PassengerInfo {
    private String name;
    private String idCard;
    private String address;
    private String phone;
    private int saleObjId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSaleObjId() {
        return saleObjId;
    }

    public void setSaleObjId(int saleObjId) {
        this.saleObjId = saleObjId;
    }

    @Override
    public String toString() {
        return "PassengerInfo{" +
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", saleObjId=" + saleObjId +
                '}';
    }
}
