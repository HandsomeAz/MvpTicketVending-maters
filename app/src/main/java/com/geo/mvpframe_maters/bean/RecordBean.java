package com.geo.mvpframe_maters.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 路线信息
 * 创建人： created by zlj
 * 时间：2022/06/25 15
 */
public class RecordBean implements Serializable {
    /**
     * parkingSpaceCode : JC_PL06
     * code : T2011
     * getonStationCode : 001
     * studentPrice : 46
     * needIdCard : 1
     * takeoffStationCode : 047
     * routeName : 机场-唐家
     * busInfoId : 616
     * routeId : 241
     * ticketNumSold : 0
     * price : 75
     * freeChildrenTicketNum : 1
     * ticketNum : 5
     * id : 132302036
     * useNewDesign : 1
     * driver1Phone : 15019939536
     * ticketDate : 2022-06-25
     * adultTicketNum : 5
     * shiftId : 0
     * takeoffStationName : 北理工
     * businessPropertyName : 商务车
     * routeProperty : 0
     * stations : [{"id":1263,"routeId":241,"stationId":1,"stationCode":"001","stationType":1,"getonType":1,"reportType":null,"reachTime":0,"reachDistance":0,"sequence":1,"serviceSpotId":1,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"珠海机场","cityId":2,"description":"珠海机场客运站","coordinate":"POINT(113.383557,22.015435)"},{"id":1264,"routeId":241,"stationId":93,"stationCode":"922","stationType":2,"getonType":3,"reportType":null,"reachTime":5,"reachDistance":5,"sequence":2,"serviceSpotId":23,"ticketEntranceId":0,"parkingSpaceId":16,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"珠海科技学院南二门","cityId":2,"description":"原吉林大学南二门","coordinate":"POINT(113.406685,22.047607)"},{"id":1265,"routeId":241,"stationId":163,"stationCode":"212","stationType":2,"getonType":2,"reportType":null,"reachTime":40,"reachDistance":40,"sequence":3,"serviceSpotId":0,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"南屏大桥公交站下","cityId":2,"description":"","coordinate":"POINT(113.5133145084724,22.23313362793392)"},{"id":1266,"routeId":241,"stationId":13,"stationCode":"013","stationType":2,"getonType":2,"reportType":null,"reachTime":65,"reachDistance":47,"sequence":4,"serviceSpotId":8,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"城轨明珠站","cityId":2,"description":"站点描述：无人售票，请提前10分钟到候机楼门外公交车道闸口处候车","coordinate":"POINT(113.52146246612453,22.274866574141406)"},{"id":1267,"routeId":241,"stationId":104,"stationCode":"046","stationType":2,"getonType":2,"reportType":null,"reachTime":80,"reachDistance":58,"sequence":5,"serviceSpotId":59,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"北师大","cityId":2,"description":"北师大校门口长南迳古道北公交站,站点电话:","coordinate":"POINT(113.54891117910888,22.352521920723735)"},{"id":1268,"routeId":241,"stationId":105,"stationCode":"047","stationType":2,"getonType":2,"reportType":null,"reachTime":85,"reachDistance":60,"sequence":6,"serviceSpotId":59,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"北理工","cityId":2,"description":"北理工校门口东门公交站","coordinate":"POINT(113.556334,22.367991)"},{"id":1269,"routeId":241,"stationId":16,"stationCode":"017","stationType":2,"getonType":2,"reportType":null,"reachTime":90,"reachDistance":67,"sequence":7,"serviceSpotId":56,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-08-31 11:39:30","stationName":"唐邑酒店","cityId":2,"description":"珠海唐家金唐路港湾1号科创园（在酒店门口的港湾一号公交站候车）,站点电话:0756-3911111","coordinate":"POINT(113.60674735684574,22.37051595314538)"},{"id":2201,"routeId":241,"stationId":106,"stationCode":"052","stationType":3,"getonType":2,"reportType":null,"reachTime":100,"reachDistance":70,"sequence":8,"serviceSpotId":0,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":0,"status":0,"createtime":"2021-08-31 11:39:15","updatetime":"2021-08-31 11:39:20","stationName":"中山大学东公交站下","cityId":2,"description":"","coordinate":"POINT(113.60186036008822,22.349276468434535)"}]
     * plateNum : 粤C72H85
     * getonTime : 21:11
     * routeShortName : 全部站点
     * overtime : 0
     * reservedNum : 0
     * childPrice : 38
     * getonStationName : 珠海机场
     * status : 1
     * driver1 : 钟文杰
     */

    private String parkingSpaceCode;
    private String code;
    private String getonStationCode;
    private int studentPrice;
    private int needIdCard;
    private String takeoffStationCode;
    private String routeName;
    private int busInfoId;
    private int routeId;
    private int ticketNumSold;
    private int price;
    private int freeChildrenTicketNum;
    private int ticketNum;
    private int id;
    private int useNewDesign;
    private String driver1Phone;
    private String ticketDate;
    private int adultTicketNum;
    private int shiftId;
    private String takeoffStationName;
    private String businessPropertyName;
    private int routeProperty;
    private String plateNum;
    private String getonTime;
    private String routeShortName;
    private int overtime;
    private int reservedNum;
    private int childPrice;
    private String getonStationName;
    private int status;
    private String driver1;
    private List<PassStationBean> stations;

    public String getParkingSpaceCode() {
        return parkingSpaceCode;
    }

    public void setParkingSpaceCode(String parkingSpaceCode) {
        this.parkingSpaceCode = parkingSpaceCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGetonStationCode() {
        return getonStationCode;
    }

    public void setGetonStationCode(String getonStationCode) {
        this.getonStationCode = getonStationCode;
    }

    public int getStudentPrice() {
        return studentPrice;
    }

    public void setStudentPrice(int studentPrice) {
        this.studentPrice = studentPrice;
    }

    public int getNeedIdCard() {
        return needIdCard;
    }

    public void setNeedIdCard(int needIdCard) {
        this.needIdCard = needIdCard;
    }

    public String getTakeoffStationCode() {
        return takeoffStationCode;
    }

    public void setTakeoffStationCode(String takeoffStationCode) {
        this.takeoffStationCode = takeoffStationCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getBusInfoId() {
        return busInfoId;
    }

    public void setBusInfoId(int busInfoId) {
        this.busInfoId = busInfoId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getTicketNumSold() {
        return ticketNumSold;
    }

    public void setTicketNumSold(int ticketNumSold) {
        this.ticketNumSold = ticketNumSold;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFreeChildrenTicketNum() {
        return freeChildrenTicketNum;
    }

    public void setFreeChildrenTicketNum(int freeChildrenTicketNum) {
        this.freeChildrenTicketNum = freeChildrenTicketNum;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUseNewDesign() {
        return useNewDesign;
    }

    public void setUseNewDesign(int useNewDesign) {
        this.useNewDesign = useNewDesign;
    }

    public String getDriver1Phone() {
        return driver1Phone;
    }

    public void setDriver1Phone(String driver1Phone) {
        this.driver1Phone = driver1Phone;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public int getAdultTicketNum() {
        return adultTicketNum;
    }

    public void setAdultTicketNum(int adultTicketNum) {
        this.adultTicketNum = adultTicketNum;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getTakeoffStationName() {
        return takeoffStationName;
    }

    public void setTakeoffStationName(String takeoffStationName) {
        this.takeoffStationName = takeoffStationName;
    }

    public String getBusinessPropertyName() {
        return businessPropertyName;
    }

    public void setBusinessPropertyName(String businessPropertyName) {
        this.businessPropertyName = businessPropertyName;
    }

    public int getRouteProperty() {
        return routeProperty;
    }

    public void setRouteProperty(int routeProperty) {
        this.routeProperty = routeProperty;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getGetonTime() {
        return getonTime;
    }

    public void setGetonTime(String getonTime) {
        this.getonTime = getonTime;
    }

    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public int getReservedNum() {
        return reservedNum;
    }

    public void setReservedNum(int reservedNum) {
        this.reservedNum = reservedNum;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public String getGetonStationName() {
        return getonStationName;
    }

    public void setGetonStationName(String getonStationName) {
        this.getonStationName = getonStationName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDriver1() {
        return driver1;
    }

    public void setDriver1(String driver1) {
        this.driver1 = driver1;
    }

    public List<PassStationBean> getStations() {
        return stations;
    }

    public void setStations(List<PassStationBean> stations) {
        this.stations = stations;
    }


    @Override
    public String toString() {
        return "RecordInfo{" +
                "parkingSpaceCode='" + parkingSpaceCode + '\'' +
                ", code='" + code + '\'' +
                ", getonStationCode='" + getonStationCode + '\'' +
                ", studentPrice=" + studentPrice +
                ", needIdCard=" + needIdCard +
                ", takeoffStationCode='" + takeoffStationCode + '\'' +
                ", routeName='" + routeName + '\'' +
                ", busInfoId=" + busInfoId +
                ", routeId=" + routeId +
                ", ticketNumSold=" + ticketNumSold +
                ", price=" + price +
                ", freeChildrenTicketNum=" + freeChildrenTicketNum +
                ", ticketNum=" + ticketNum +
                ", id=" + id +
                ", useNewDesign=" + useNewDesign +
                ", driver1Phone='" + driver1Phone + '\'' +
                ", ticketDate='" + ticketDate + '\'' +
                ", adultTicketNum=" + adultTicketNum +
                ", shiftId=" + shiftId +
                ", takeoffStationName='" + takeoffStationName + '\'' +
                ", businessPropertyName='" + businessPropertyName + '\'' +
                ", routeProperty=" + routeProperty +
                ", plateNum='" + plateNum + '\'' +
                ", getonTime='" + getonTime + '\'' +
                ", routeShortName='" + routeShortName + '\'' +
                ", overtime=" + overtime +
                ", reservedNum=" + reservedNum +
                ", childPrice=" + childPrice +
                ", getonStationName='" + getonStationName + '\'' +
                ", status=" + status +
                ", driver1='" + driver1 + '\'' +
                ", stations=" + stations +
                '}';
    }
}
