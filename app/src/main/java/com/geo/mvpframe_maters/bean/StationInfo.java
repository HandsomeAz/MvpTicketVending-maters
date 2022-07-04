package com.geo.mvpframe_maters.bean;

/**
 * 创建人： created by zlj
 * 时间：2022/05/24 21
 */
public class StationInfo {

    /**
     * id : 84
     * code : 903
     * name : 横琴湾酒店(航展)
     * coordinate :
     * description : 上车地点：横琴湾酒店内大巴站台
     * hotDegree : 0
     * district : 航展酒店专线
     * districtId : 31
     */

    private String id;
    private String code;
    private String name;
    private String coordinate;
    private String description;
    private int hotDegree;
    private String district;
    private String districtId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHotDegree() {
        return hotDegree;
    }

    public void setHotDegree(int hotDegree) {
        this.hotDegree = hotDegree;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return "StationInfo{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", description='" + description + '\'' +
                ", hotDegree=" + hotDegree +
                ", district='" + district + '\'' +
                ", districtId='" + districtId + '\'' +
                '}';
    }
}
