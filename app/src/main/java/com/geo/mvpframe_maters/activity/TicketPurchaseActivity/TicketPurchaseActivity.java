package com.geo.mvpframe_maters.activity.TicketPurchaseActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.activity.TicketPurchaseActivity.adapter.PassengerAdapter;
import com.geo.mvpframe_maters.bean.PassStationBean;
import com.geo.mvpframe_maters.bean.PassengerInfo;
import com.geo.mvpframe_maters.bean.RecordBean;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.utils.DialogManage;
import com.geo.mvpframe_maters.utils.ReadIDUtil;
import com.geo.mvpframe_maters.utils.SharePreUtil;
import com.geo.mvpframe_maters.utils.SpacesItemDecoration;
import com.geo.mvpframe_maters.widget.SelectTicketView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_ticket_purchase)
public class TicketPurchaseActivity extends BaseMvpActivity implements TicketPurchaseContract.IView{

    @InjectPresenter
    TicketPresenter mPresenter;
    private RecordBean recordBean;

    @BindView(R.id.tv_get_onStationName)
    TextView tv_get_onStationName;

    @BindView(R.id.tv_takeoffStationName)
    TextView tv_takeoffStationName;

    @BindView(R.id.tv_bus_num)
    TextView tv_bus_num;

    @BindView(R.id.tv_bus_type)
    TextView tv_bus_type;

    @BindView(R.id.tv_start_date)
    TextView tv_start_date;

    @BindView(R.id.tv_start_time)
    TextView tv_start_time;



    @BindView(R.id.tv_departure_time)
    TextView tv_departure_time;

    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.sv_adult)
    SelectTicketView sv_adult;

    @BindView(R.id.sv_child)
    SelectTicketView sv_child;

    @BindView(R.id.sv_baby)
    SelectTicketView sv_baby;

    @BindView(R.id.rv_person)
    RecyclerView rv_person;
    private PassengerAdapter mPassengerAdapter =null;
    private int adultNum =0,childNum =0, babyNum =0;
    @BindView(R.id.tv_total_num)
    TextView tv_total_num;

    @BindView(R.id.bt_sure)
    Button bt_sure;


    private List<PassengerInfo> passengerInfoList =new ArrayList<>();
    private String mOrderId ="";

    @Override
    public void afterBindView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_person.setLayoutManager(manager);
        rv_person .addItemDecoration(new SpacesItemDecoration(20));
        if (mPassengerAdapter ==null){
            mPassengerAdapter = new PassengerAdapter(mContext);
        }
        rv_person.setAdapter(mPassengerAdapter);
    }

    @Override
    public void processData() {
        //????????????
        Intent intent = getIntent();
        recordBean = (RecordBean)intent.getSerializableExtra("recordBean");
        bt_sure.setVisibility(recordBean!=null? View.VISIBLE:View.GONE);
        if (recordBean !=null){
            tv_get_onStationName.setText(recordBean.getGetonStationName());
            tv_takeoffStationName.setText(recordBean.getTakeoffStationName());
            tv_bus_num.setText("????????????:"+recordBean.getPlateNum());
            tv_bus_type.setText("????????????:"+recordBean.getBusinessPropertyName());
            tv_start_date.setText("????????????:"+recordBean.getTicketDate());
            tv_start_time.setText("????????????:"+recordBean.getTicketNum());

            tv_departure_time.setText(recordBean.getGetonTime());

            List<PassStationBean> stations = recordBean.getStations();
            String stationContents ="";
            if (stations !=null &&stations.size()>0){
                for (PassStationBean bean :
                        stations) {
                    stationContents+=bean.getStationName()+"  ";
                }
            }


            tv_content.setText("?????? "+stationContents);
            sv_adult.setNeedRead(true);
            sv_adult.getTv_name_type().setText("?????????(" + recordBean.getPrice() + "???/???)");
            sv_child.setNeedRead(true);
            sv_child.getTv_name_type().setText("?????????((" + recordBean.getChildPrice() + "???/???)");
            sv_baby.getTv_name_type().setText("????????????(0???/???)"+"\n"+"???:"+recordBean.getFreeChildrenTicketNum()+"???");

            sv_adult.setMaxNum(recordBean.getAdultTicketNum());
            sv_child.setMaxNum(recordBean.getTicketNum());
            sv_baby.setMaxNum(recordBean.getFreeChildrenTicketNum());
        }
    }

    @Override
    protected void addListeners() {
        super.addListeners();
        sv_adult.setSelectTicketListener(new SelectTicketView.onSelectTicketListener() {
            @Override
            public void addPassenger(String name, String cardNum, String address, String phone, int ticketNum) {
                adultNum =ticketNum;
                AddPassenger(name,cardNum,address,phone,1);
                sv_baby.setTakeFreeChild(adultNum > 0);
            }

            @Override
            public void minusPassenger(int ticketNum) {
                adultNum =ticketNum;
                MinusPassenger(1);
                sv_baby.setTakeFreeChild(adultNum > 0);
            }

        });

        sv_child.setSelectTicketListener(new SelectTicketView.onSelectTicketListener() {
            @Override
            public void addPassenger(String name, String cardNum, String address, String phone, int ticketNum) {
                childNum =ticketNum;
                AddPassenger(name,cardNum,address,phone,2);
            }

            @Override
            public void minusPassenger(int ticketNum) {
                childNum =ticketNum;
                MinusPassenger(2);
            }
        });

        sv_baby.setSelectTicketListener(new SelectTicketView.onSelectTicketListener() {
            @Override
            public void addPassenger(String name, String cardNum, String address, String phone, int ticketNum) {
                babyNum =ticketNum;
                AddPassenger(name,cardNum,address,phone,4);
            }

            @Override
            public void minusPassenger(int ticketNum) {
                babyNum =ticketNum;
                MinusPassenger(4);
            }
        });

        mPassengerAdapter.setClickAdapterListener(new PassengerAdapter.onClickAdapterListener() {
            @Override
            public void onDelete(PassengerInfo passengerInfo) {
                passengerInfoList.remove(passengerInfo);
                switch (passengerInfo.getSaleObjId()){
                    case 1:
                        adultNum --;
                        sv_adult.setNumber(adultNum);
                        sv_baby.setTakeFreeChild(adultNum > 0);
                        break;
                    case 2:
                        childNum--;
                        sv_child.setNumber(childNum);
                        break;
                    case 4:
                        babyNum--;
                        sv_baby.setNumber(babyNum);
                        break;
                }
                mPassengerAdapter.setPassengerInfo(passengerInfoList);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_total_num.setText("????????????:"+(adultNum+childNum+babyNum)+"???"+"/???("+(adultNum*recordBean.getPrice()+childNum*recordBean.getChildPrice())+")???");
                    }
                });
            }
        });
        bt_sure.setOnClickListener(v -> {
            int totalNum =adultNum + childNum + babyNum;
            if (totalNum <=0){
                showToast("?????????????????????");
                return;
            }
            showLoading();
            mPresenter.createOrder(recordBean,passengerInfoList);
        });
    }

    //????????????
    private void MinusPassenger(int saleObjId) {
        if (passengerInfoList.size()==0){
            return;
        }
        for (int i = passengerInfoList.size()-1; i >=0; i--) {
            if (passengerInfoList.get(i).getSaleObjId() == saleObjId){
                passengerInfoList.remove(passengerInfoList.get(i));
                break;
            }
        }
        mPassengerAdapter.setPassengerInfo(passengerInfoList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_total_num.setText("????????????:"+(adultNum+childNum+babyNum)+"???"+"/???("+(adultNum*recordBean.getPrice()+childNum*recordBean.getChildPrice())+")???");
            }
        });
    }

    private void AddPassenger(String name, String cardNum, String address, String phone, int saleObjId) {

        PassengerInfo passengerInfo = new PassengerInfo();
        passengerInfo.setName(name);
        passengerInfo.setIdCard(cardNum);
        passengerInfo.setAddress(address);
        passengerInfo.setPhone(phone);
        passengerInfo.setSaleObjId(saleObjId);
        passengerInfoList.add(passengerInfo);
        mPassengerAdapter.setPassengerInfo(passengerInfoList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_total_num.setText("????????????:"+(adultNum+childNum+babyNum)+"???"+"/???("+(adultNum*recordBean.getPrice()+childNum*recordBean.getChildPrice())+")???");
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        ReadIDUtil.getInstance().initCardReader(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ReadIDUtil.getInstance().releaseCardReader();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void createSuccess(String orderId) {
        hideLoading();
        mOrderId = orderId;
    }

    @Override
    public void createFailure(String message) {
        hideLoading();
    }
}