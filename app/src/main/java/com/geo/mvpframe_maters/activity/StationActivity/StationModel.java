package com.geo.mvpframe_maters.activity.StationActivity;


import com.geo.mvpframe_maters.bean.CityInfo;
import com.geo.mvpframe_maters.bean.DivideStation;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.geo.mvpframe_maters.constant.Constants;
import com.geo.mvpframe_maters.mvp.BaseMvpModel;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.utils.DateUtil;
import com.geo.mvpframe_maters.utils.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.geo.mvpframe_maters.constant.Constants.ACCOUNT_KEY;
import static com.geo.mvpframe_maters.constant.Constants.PASSWORD_KEY;

public class StationModel extends BaseMvpModel implements StationContract.IModel {
    private final String HOT_CITY_TAG = "其他售票区域";

    @Override
    public void getCity(String business, Observer<RequestBean<List<CityInfo>>> observer) {
        String timestamp =String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(getRand());
        String str = ACCOUNT_KEY + timestamp + nonce + PASSWORD_KEY;
        String sign  = MD5Util.encode(str);
        Map<String, String> map = getHeader(timestamp, nonce,sign);

        setSubscribe(service.getCityInfo(map,business),observer);
    }

    @Override
    public void getStationList(int cityId,int type, Observer<RequestBean<List<StationInfo>>> observer) {
        String timestamp =String.valueOf(System.currentTimeMillis()/1000);
        String nonce=String.valueOf(getRand());
        JSONObject obj = new JSONObject();
        try {
            obj.put("departCityId",cityId);
            obj.put("getOnId",type);
            obj.put("onStatus",1);
            if (type != 0) {
                obj.put("departStationCode", Constants.departStationCode);
            }
            obj.put("ticketDate", DateUtil.formatYYYYMMDD(new Date()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = ACCOUNT_KEY + timestamp + nonce+ obj + PASSWORD_KEY;
        String sign  = MD5Util.encode(str);
        Map<String, String> map = getHeader(timestamp, nonce, sign);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody=RequestBody.create(JSON,obj.toString());
        setSubscribe(service.getStationList(map,formBody),observer);
    }

    @Override
    public List<DivideStation> divideStation(List<StationInfo> data) {
        List<DivideStation> divideStationList =new ArrayList<>();
        if (data ==null){
            return  null;
        }
        List<String> titleList = new ArrayList<>();
        titleList.add("机场");
        titleList.add("热门站点");
        for (int i = 0; i < data.size(); i++) {
            String district = data.get(i).getDistrict();
            if (district ==null){
                district =HOT_CITY_TAG;
            }
            if (!titleList.contains(district)){
                titleList.add(district);
            }
        }

        for (int i = 0; i < titleList.size(); i++) {
            String district = titleList.get(i);
            DivideStation divideStation = new DivideStation();
            List<StationInfo> stations = new ArrayList<>();
            divideStation.setTitleName(district);
            for (StationInfo stationInfo :data){
                if (district.equals("机场")){
                    if (stationInfo.getName().equals("珠海机场")){
                        stations.add(stationInfo);
                    }
                }else if (district.equals("热门站点")){
                    if (stationInfo.getHotDegree()>=200){
                        stations.add(stationInfo);
                    }
                }else if (district.equals(HOT_CITY_TAG)){
                    if (stationInfo.getDistrict()== null){
                        stations.add(stationInfo);
                    }
                }else {
                    if (district.equals(stationInfo.getDistrict())){
                        stations.add(stationInfo);
                    }
                }
            }
            if (stations !=null &&stations.size()>0){
                divideStation.setStationList(stations);
                divideStationList.add(divideStation);
            }

        }

        return divideStationList;
    }
}
