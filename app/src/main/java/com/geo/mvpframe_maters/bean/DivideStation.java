package com.geo.mvpframe_maters.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人： created by zlj
 * 时间：2022/05/24 21
 */
public class DivideStation {
    private String titleName;
    private List<StationInfo> stationList =new ArrayList<>();

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public List<StationInfo> getStationList() {
        return stationList;
    }

    public void setStationList(List<StationInfo> stationList) {
        this.stationList = stationList;
    }

    @Override
    public String toString() {
        return "DivideStation{" +
                "titleName='" + titleName + '\'' +
                ", stationList=" + stationList +
                '}';
    }
}
