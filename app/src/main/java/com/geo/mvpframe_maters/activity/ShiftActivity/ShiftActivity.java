package com.geo.mvpframe_maters.activity.ShiftActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.activity.ShiftActivity.adapter.RecordAdapter;
import com.geo.mvpframe_maters.activity.TicketPurchaseActivity.TicketPurchaseActivity;
import com.geo.mvpframe_maters.bean.RecordBean;
import com.geo.mvpframe_maters.bean.ShiftInfo;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.utils.DateUtil;
import com.geo.mvpframe_maters.utils.SpacesItemDecoration;
import com.geo.mvpframe_maters.widget.DateView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_shift)
public class ShiftActivity extends BaseMvpActivity implements ShiftContract.IView{
    @BindView(R.id.tv_get_station)
    TextView tv_get_station;
    @BindView(R.id.tv_off_station)
    TextView tv_off_station;
    @BindView(R.id.rv_shift)
    RecyclerView rv_shift;
    @InjectPresenter
    ShiftPresenter mPresenter;
    private String offStationCode;
    private String offStationName="",getOnStationName="";
    private String departure_time; //出发时间

    private RecordAdapter recordAdapter;

    @BindView(R.id.my_dateView)
    DateView my_dateView;
    @Override
    public void afterBindView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_shift.setLayoutManager(manager);
        rv_shift .addItemDecoration(new SpacesItemDecoration(20));
        recordAdapter = new RecordAdapter(mContext);

        my_dateView.setDateChangeListener(new DateView.DateChangeListener() {
            @Override
            public void add(int day) {
                long timeSpan = day * DateView.DAY_SECEND;
                Date date = new Date(System.currentTimeMillis() + timeSpan);
                mPresenter.getShiftInfo(DateUtil.formatYYYYMMDD(date),0,offStationCode);
            }

            @Override
            public void minus(int day) {
                long timeSpan = day * DateView.DAY_SECEND;
                Date date = new Date(System.currentTimeMillis() + timeSpan);
                mPresenter.getShiftInfo(DateUtil.formatYYYYMMDD(date),0,offStationCode);
            }
        });
    }

    @Override
    public void processData() {
        Intent intent = getIntent();
        offStationCode =intent.getStringExtra("off_station_code");
        offStationName =intent.getStringExtra("off_station_name");
        departure_time = intent.getStringExtra("departure_time");
        getOnStationName =sp.getString("get_station_name", "未选上车站点");

        if (offStationCode !=null){
            tv_get_station.setText(getOnStationName);
            tv_off_station.setText(offStationName);
            mPresenter.getShiftInfo(departure_time,0,offStationCode);
        }
    }

    @Override
    public void setRecordBean(List<RecordBean> recordBeanList) {
        hideLoading();
        if (recordBeanList ==null){
            showToast("当天没有乘车班次信息");
            recordBeanList =new ArrayList<>();
        }
        recordAdapter.setRecordData(recordBeanList);
        rv_shift.setAdapter(recordAdapter);
        recordAdapter.setClickAdapterListener(recordBean -> {
            Intent intent = new Intent(mContext, TicketPurchaseActivity.class);
            intent.putExtra("recordBean",recordBean);
            startActivity(intent);
        });
    }

    @Override
    public void RecordFailure(int i) {
        hideLoading();
        if (i ==0){
            showToast("当天没有乘车班次信息");
        }else {
            showToast("班次获取失败");
        }
    }
}