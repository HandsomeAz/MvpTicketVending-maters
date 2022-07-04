package com.geo.mvpframe_maters.bean;

/**
* 配置信息
* @author zengliangji
* @created 2022/6/24 10:16
*/

public class ConfigInfo {
    private int id;
    private String mainModel; //0上下轮播图  1上视频下图 2只显示下屏图
    private int abovePlayTime; //上屏轮播时间
    private int belowPlayTime; //下屏轮播时间
    private String aboveAutoLoop; //上屏是否轮播 默认0位轮播
    private String belowAutoLoop; //下屏是否轮播 默认0位轮播
    private String videoVoice; //上屏视频是否能播放声音

    public ConfigInfo initConfigInfo(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setMainModel("0");
        configInfo.setAbovePlayTime(5);
        configInfo.setBelowPlayTime(5);
        configInfo.setAboveAutoLoop("0");
        configInfo.setBelowAutoLoop("0");
        configInfo.setVideoVoice("0");
        return configInfo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainModel() {
        return mainModel;
    }

    public void setMainModel(String mainModel) {
        this.mainModel = mainModel;
    }

    public int getAbovePlayTime() {
        return abovePlayTime;
    }

    public void setAbovePlayTime(int abovePlayTime) {
        this.abovePlayTime = abovePlayTime;
    }

    public int getBelowPlayTime() {
        return belowPlayTime;
    }

    public void setBelowPlayTime(int belowPlayTime) {
        this.belowPlayTime = belowPlayTime;
    }

    public String getAboveAutoLoop() {
        return aboveAutoLoop;
    }

    public void setAboveAutoLoop(String aboveAutoLoop) {
        this.aboveAutoLoop = aboveAutoLoop;
    }

    public String getBelowAutoLoop() {
        return belowAutoLoop;
    }

    public void setBelowAutoLoop(String belowAutoLoop) {
        this.belowAutoLoop = belowAutoLoop;
    }

    public String getVideoVoice() {
        return videoVoice;
    }

    public void setVideoVoice(String videoVoice) {
        this.videoVoice = videoVoice;
    }

    @Override
    public String toString() {
        return "ConfigInfo{" +
                "id=" + id +
                ", mainModel='" + mainModel + '\'' +
                ", abovePlayTime='" + abovePlayTime + '\'' +
                ", belowPlayTime='" + belowPlayTime + '\'' +
                ", aboveAutoLoop='" + aboveAutoLoop + '\'' +
                ", belowAutoLoop='" + belowAutoLoop + '\'' +
                ", videoVoice='" + videoVoice + '\'' +
                '}';
    }
}
