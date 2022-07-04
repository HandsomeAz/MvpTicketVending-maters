package com.geo.mvpframe_maters.bean;

import java.util.List;

/**
 * 班次信息
 * 创建人： created by zlj
 * 时间：2022/06/25 15
 */
public class ShiftInfo {

    /**
     * records : [{"parkingSpaceCode":"JC_PL06","code":"T2011","getonStationCode":"001","studentPrice":46,"needIdCard":1,"takeoffStationCode":"047","routeName":"机场-唐家","busInfoId":616,"routeId":241,"ticketNumSold":0,"price":75,"freeChildrenTicketNum":1,"ticketNum":5,"id":132302036,"useNewDesign":1,"driver1Phone":"15019939536","ticketDate":"2022-06-25","adultTicketNum":5,"shiftId":0,"takeoffStationName":"北理工","businessPropertyName":"商务车","routeProperty":0,"stations":[{"id":1263,"routeId":241,"stationId":1,"stationCode":"001","stationType":1,"getonType":1,"reportType":null,"reachTime":0,"reachDistance":0,"sequence":1,"serviceSpotId":1,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"珠海机场","cityId":2,"description":"珠海机场客运站","coordinate":"POINT(113.383557,22.015435)"},{"id":1264,"routeId":241,"stationId":93,"stationCode":"922","stationType":2,"getonType":3,"reportType":null,"reachTime":5,"reachDistance":5,"sequence":2,"serviceSpotId":23,"ticketEntranceId":0,"parkingSpaceId":16,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"珠海科技学院南二门","cityId":2,"description":"原吉林大学南二门","coordinate":"POINT(113.406685,22.047607)"},{"id":1265,"routeId":241,"stationId":163,"stationCode":"212","stationType":2,"getonType":2,"reportType":null,"reachTime":40,"reachDistance":40,"sequence":3,"serviceSpotId":0,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"南屏大桥公交站下","cityId":2,"description":"","coordinate":"POINT(113.5133145084724,22.23313362793392)"},{"id":1266,"routeId":241,"stationId":13,"stationCode":"013","stationType":2,"getonType":2,"reportType":null,"reachTime":65,"reachDistance":47,"sequence":4,"serviceSpotId":8,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"城轨明珠站","cityId":2,"description":"站点描述：无人售票，请提前10分钟到候机楼门外公交车道闸口处候车","coordinate":"POINT(113.52146246612453,22.274866574141406)"},{"id":1267,"routeId":241,"stationId":104,"stationCode":"046","stationType":2,"getonType":2,"reportType":null,"reachTime":80,"reachDistance":58,"sequence":5,"serviceSpotId":59,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"北师大","cityId":2,"description":"北师大校门口长南迳古道北公交站,站点电话:","coordinate":"POINT(113.54891117910888,22.352521920723735)"},{"id":1268,"routeId":241,"stationId":105,"stationCode":"047","stationType":2,"getonType":2,"reportType":null,"reachTime":85,"reachDistance":60,"sequence":6,"serviceSpotId":59,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-03-26 15:52:28","stationName":"北理工","cityId":2,"description":"北理工校门口东门公交站","coordinate":"POINT(113.556334,22.367991)"},{"id":1269,"routeId":241,"stationId":16,"stationCode":"017","stationType":2,"getonType":2,"reportType":null,"reachTime":90,"reachDistance":67,"sequence":7,"serviceSpotId":56,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":1,"status":1,"createtime":"2021-03-26 15:52:28","updatetime":"2021-08-31 11:39:30","stationName":"唐邑酒店","cityId":2,"description":"珠海唐家金唐路港湾1号科创园（在酒店门口的港湾一号公交站候车）,站点电话:0756-3911111","coordinate":"POINT(113.60674735684574,22.37051595314538)"},{"id":2201,"routeId":241,"stationId":106,"stationCode":"052","stationType":3,"getonType":2,"reportType":null,"reachTime":100,"reachDistance":70,"sequence":8,"serviceSpotId":0,"ticketEntranceId":0,"parkingSpaceId":0,"managerId":0,"isWechat":0,"status":0,"createtime":"2021-08-31 11:39:15","updatetime":"2021-08-31 11:39:20","stationName":"中山大学东公交站下","cityId":2,"description":"","coordinate":"POINT(113.60186036008822,22.349276468434535)"}],"plateNum":"粤C72H85","getonTime":"21:11","routeShortName":"全部站点","overtime":0,"reservedNum":0,"childPrice":38,"getonStationName":"珠海机场","status":1,"driver1":"钟文杰"}]
     * total : 7
     * size : 15
     * current : 1
     * orders : []
     * optimizeCountSql : true
     * hitCount : false
     * countId : null
     * maxLimit : null
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean optimizeCountSql;
    private boolean hitCount;
    private Object countId;
    private Object maxLimit;
    private boolean searchCount;
    private int pages;
    private List<RecordBean> records;
    private List<?> orders;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public boolean isHitCount() {
        return hitCount;
    }

    public void setHitCount(boolean hitCount) {
        this.hitCount = hitCount;
    }

    public Object getCountId() {
        return countId;
    }

    public void setCountId(Object countId) {
        this.countId = countId;
    }

    public Object getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Object maxLimit) {
        this.maxLimit = maxLimit;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordBean> records) {
        this.records = records;
    }

    public List<?> getOrders() {
        return orders;
    }

    public void setOrders(List<?> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ShiftInfo{" +
                "total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", optimizeCountSql=" + optimizeCountSql +
                ", hitCount=" + hitCount +
                ", countId=" + countId +
                ", maxLimit=" + maxLimit +
                ", searchCount=" + searchCount +
                ", pages=" + pages +
                ", records=" + records +
                ", orders=" + orders +
                '}';
    }
}
