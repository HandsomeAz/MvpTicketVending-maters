package com.geo.mvpframe_maters.bean;

import java.util.List;

public class OrderInfo {

    private String orderId;
    private String cipherOrderId;
    private String getonStation;
    private String getonStationCode;
    private String getonTime;
    private String shiftCode;
    private String takeoffStation;
    private String takeoffStationCode;
    private String ticketDate;
    private String createtime;
    private int totalPrice;
    private List<TicketListBean> ticketList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCipherOrderId() {
        return cipherOrderId;
    }

    public void setCipherOrderId(String cipherOrderId) {
        this.cipherOrderId = cipherOrderId;
    }

    public String getGetonStation() {
        return getonStation;
    }

    public void setGetonStation(String getonStation) {
        this.getonStation = getonStation;
    }

    public String getGetonStationCode() {
        return getonStationCode;
    }

    public void setGetonStationCode(String getonStationCode) {
        this.getonStationCode = getonStationCode;
    }

    public String getGetonTime() {
        return getonTime;
    }

    public void setGetonTime(String getonTime) {
        this.getonTime = getonTime;
    }

    public String getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
    }

    public String getTakeoffStation() {
        return takeoffStation;
    }

    public void setTakeoffStation(String takeoffStation) {
        this.takeoffStation = takeoffStation;
    }

    public String getTakeoffStationCode() {
        return takeoffStationCode;
    }

    public void setTakeoffStationCode(String takeoffStationCode) {
        this.takeoffStationCode = takeoffStationCode;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<TicketListBean> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketListBean> ticketList) {
        this.ticketList = ticketList;
    }

    public static class TicketListBean {
        private String orderId;
        private String idCard;
        private String name;
        private String phone;
        private int price;
        private int insFee;
        private String saleObj;
        private int saleObjId;
        private int seat;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getInsFee() {
            return insFee;
        }

        public void setInsFee(int insFee) {
            this.insFee = insFee;
        }

        public String getSaleObj() {
            return saleObj;
        }

        public void setSaleObj(String saleObj) {
            this.saleObj = saleObj;
        }

        public int getSaleObjId() {
            return saleObjId;
        }

        public void setSaleObjId(int saleObjId) {
            this.saleObjId = saleObjId;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        @Override
        public String toString() {
            return "TicketListBean{" +
                    "orderId='" + orderId + '\'' +
                    ", idCard='" + idCard + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", price=" + price +
                    ", insFee=" + insFee +
                    ", saleObj='" + saleObj + '\'' +
                    ", saleObjId=" + saleObjId +
                    ", seat=" + seat +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", cipherOrderId='" + cipherOrderId + '\'' +
                ", getonStation='" + getonStation + '\'' +
                ", getonStationCode='" + getonStationCode + '\'' +
                ", getonTime='" + getonTime + '\'' +
                ", shiftCode='" + shiftCode + '\'' +
                ", takeoffStation='" + takeoffStation + '\'' +
                ", takeoffStationCode='" + takeoffStationCode + '\'' +
                ", ticketDate='" + ticketDate + '\'' +
                ", createtime='" + createtime + '\'' +
                ", totalPrice=" + totalPrice +
                ", ticketList=" + ticketList +
                '}';
    }
}
