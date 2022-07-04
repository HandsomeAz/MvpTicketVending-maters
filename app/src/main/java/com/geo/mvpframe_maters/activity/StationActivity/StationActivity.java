package com.geo.mvpframe_maters.activity.StationActivity;



import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geo.mvpframe_maters.R;

import com.geo.mvpframe_maters.activity.MainActivity.MainActivity;
import com.geo.mvpframe_maters.activity.SelectActivity.SelectActivity;
import com.geo.mvpframe_maters.activity.ShiftActivity.ShiftActivity;
import com.geo.mvpframe_maters.activity.StationActivity.adapter.CityAdapter;
import com.geo.mvpframe_maters.activity.StationActivity.adapter.DivideAdapter;
import com.geo.mvpframe_maters.bean.CityInfo;
import com.geo.mvpframe_maters.bean.DivideStation;
import com.geo.mvpframe_maters.bean.StationInfo;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.utils.DateUtil;
import com.geo.mvpframe_maters.utils.DialogClickListener;
import com.geo.mvpframe_maters.utils.DialogManage;
import com.geo.mvpframe_maters.utils.ToastUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_station)
public class StationActivity extends BaseMvpActivity implements StationContract.IView{
    @InjectPresenter
    private StationPresenter mPresenter;

    @BindView(R.id.tv_tips)
    TextView tv_tips;
    @BindView(R.id.rv_city)
    RecyclerView rv_city;
    @BindView(R.id.rv_divide)
    RecyclerView rv_divide;


    private CityAdapter mAdapter;
    private DivideAdapter mDivideAdapter;
    private int stationType =0;
    private String curCityName;
    @Override
    public void afterBindView() {


        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);//第二个参数为网格的列数
        rv_city.setLayoutManager(layoutManager);
        mAdapter =new CityAdapter(mContext);
        rv_city.setAdapter(mAdapter);
        mAdapter.setCityListener(new CityAdapter.CityListener() {
            @Override
            public void CityInfo(int cityId, String cityName ) {
                Log.d("城市Id"+cityId,"所选条目"+cityName);
                curCityName = cityName;
                showLoading();
                mPresenter.getStationInfo(cityId,stationType);
            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_divide.setLayoutManager(manager);
        mDivideAdapter =new DivideAdapter(mContext);
        rv_divide.setAdapter(mDivideAdapter);

    }

    @Override
    public void processData() {
        Intent intent = getIntent();
        stationType = intent.getIntExtra("stationType",0);
        if (stationType ==0){
            tv_tips.setText("选择出发站点");
        }else {
            tv_tips.setText("选择到达站点");
        }
        showLoading();
        mPresenter.getCityInfo();

        //站点点击回应事件
        LiveEventBus.get("station_info",StationInfo.class)
                .observe(this, new Observer<StationInfo>() {
                    @Override
                    public void onChanged(StationInfo stationInfo) {
                        if (stationInfo !=null){
                            if (stationType ==0){
                                DialogManage.getInstance().TipDialog("选择“"+stationInfo.getName()+"”为上车站点吗？",new DialogClickListener() {
                                    @Override
                                    public void Cancel() {

                                    }

                                    @Override
                                    public void Submit() {
                                        sp.putBoolean("save_station",true);
                                        sp.putString("get_station_name",stationInfo.getName());
                                        sp.putString("get_station_code",stationInfo.getCode());
                                        startActivity(new Intent(mContext, MainActivity.class));
                                        finish();
                                    }
                                });

                            }else {
                                sp.putString("person_address",curCityName+"市"+stationInfo.getName());
                                Intent intent = new Intent(mContext, ShiftActivity.class);
                                intent.putExtra("off_station_code",stationInfo.getCode());
                                intent.putExtra("off_station_name",stationInfo.getName());
                                intent.putExtra("departure_time", DateUtil.formatYYYYMMDD(new Date()));
                                startActivity(intent);
//                                finish();
//                                Intent intent = new Intent(mContext, SelectActivity.class);
//                                intent.putExtra("off_station_code",stationInfo.getCode());
//                                intent.putExtra("off_station_name",stationInfo.getName());
//                                setResult(RESULT_OK,intent);
//                                finish();

                            }
                        }
                    }
                });


    }

    @Override
    public void getCityFailure(String message) {
        ToastUtils.toast(mContext,message);
    }

    @Override
    public void getCitySuccess(List<CityInfo> data) {
        curCityName = data.get(0).getName();
        mAdapter.setCityData(data);
        mPresenter.getStationInfo(data.get(0).getId(),stationType);

    }

    @Override
    public void getStationFailure(String message) {
        hideLoading();
        ToastUtils.toast(mContext,message);
    }

    @Override
    public void getStationSuccess(List<StationInfo> data) {

        mPresenter.divideStation(data); //站点分类
    }

    @Override
    public void getDivideStations(List<DivideStation> divideStationList) {
        hideLoading();
        if (divideStationList == null){
            return;
        }
        mDivideAdapter.setDivideStationData(divideStationList,stationType);

    }
}