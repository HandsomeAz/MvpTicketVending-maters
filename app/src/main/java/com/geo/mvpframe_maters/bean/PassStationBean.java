package com.geo.mvpframe_maters.bean;

import java.io.Serializable;

/**
 * 创建人： created by zlj
 * 时间：2022/06/25 15
 */
public class PassStationBean implements Serializable {
    /**
     * id : 1263
     * routeId : 241
     * stationId : 1
     * stationCode : 001
     * stationType : 1
     * getonType : 1
     * reportType : null
     * reachTime : 0
     * reachDistance : 0
     * sequence : 1
     * serviceSpotId : 1
     * ticketEntranceId : 0
     * parkingSpaceId : 0
     * managerId : 0
     * isWechat : 1
     * status : 1
     * createtime : 2021-03-26 15:52:28
     * updatetime : 2021-03-26 15:52:28
     * stationName : 珠海机场
     * cityId : 2
     * description : 珠海机场客运站
     * coordinate : POINT(113.383557,22.015435)
     */

    private int id;
    private int routeId;
    private int stationId;
    private String stationCode;
    private int stationType;
    private int getonType;
    private Object reportType;
    private int reachTime;
    private int reachDistance;
    private int sequence;
    private int serviceSpotId;
    private int ticketEntranceId;
    private int parkingSpaceId;
    private int managerId;
    private int isWechat;
    private int status;
    private String createtime;
    private String updatetime;
    private String stationName;
    private int cityId;
    private String description;
    private String coordinate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public int getStationType() {
        return stationType;
    }

    public void setStationType(int stationType) {
        this.stationType = stationType;
    }

    public int getGetonType() {
        return getonType;
    }

    public void setGetonType(int getonType) {
        this.getonType = getonType;
    }

    public Object getReportType() {
        return reportType;
    }

    public void setReportType(Object reportType) {
        this.reportType = reportType;
    }

    public int getReachTime() {
        return reachTime;
    }

    public void setReachTime(int reachTime) {
        this.reachTime = reachTime;
    }

    public int getReachDistance() {
        return reachDistance;
    }

    public void setReachDistance(int reachDistance) {
        this.reachDistance = reachDistance;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getServiceSpotId() {
        return serviceSpotId;
    }

    public void setServiceSpotId(int serviceSpotId) {
        this.serviceSpotId = serviceSpotId;
    }

    public int getTicketEntranceId() {
        return ticketEntranceId;
    }

    public void setTicketEntranceId(int ticketEntranceId) {
        this.ticketEntranceId = ticketEntranceId;
    }

    public int getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(int parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getIsWechat() {
        return isWechat;
    }

    public void setIsWechat(int isWechat) {
        this.isWechat = isWechat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "PassStationInfo{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", stationId=" + stationId +
                ", stationCode='" + stationCode + '\'' +
                ", stationType=" + stationType +
                ", getonType=" + getonType +
                ", reportType=" + reportType +
                ", reachTime=" + reachTime +
                ", reachDistance=" + reachDistance +
                ", sequence=" + sequence +
                ", serviceSpotId=" + serviceSpotId +
                ", ticketEntranceId=" + ticketEntranceId +
                ", parkingSpaceId=" + parkingSpaceId +
                ", managerId=" + managerId +
                ", isWechat=" + isWechat +
                ", status=" + status +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", stationName='" + stationName + '\'' +
                ", cityId=" + cityId +
                ", description='" + description + '\'' +
                ", coordinate='" + coordinate + '\'' +
                '}';
    }
}
