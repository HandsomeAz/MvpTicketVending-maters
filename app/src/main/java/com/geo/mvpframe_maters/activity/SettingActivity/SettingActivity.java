package com.geo.mvpframe_maters.activity.SettingActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.activity.LoginActivity.LoginActivity;
import com.geo.mvpframe_maters.activity.StationActivity.StationActivity;
import com.geo.mvpframe_maters.bean.ConfigInfo;
import com.geo.mvpframe_maters.db.DBManager;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.utils.DialogClickListener;
import com.geo.mvpframe_maters.utils.DialogManage;
import com.geo.mvpframe_maters.widget.SelectView;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_setting)
public class SettingActivity extends BaseMvpActivity {


    @BindView(R.id.tv_tips)
    TextView tv_tips;

    @BindView(R.id.ll_login_out)
    LinearLayout ll_login_out;

    @BindView(R.id.ll_res_station)
    LinearLayout ll_res_station;

    @BindView(R.id.ll_above)
    LinearLayout ll_above;

    @BindView(R.id.ll_video)
    LinearLayout ll_video;

    @BindView(R.id.ll_below)
    LinearLayout ll_below;

    @BindView(R.id.sv_main_model)
    SelectView sv_main_model;

    @BindView(R.id.et_above_time)
    EditText et_above_time;

    @BindView(R.id.et_below_time)
    EditText et_below_time;

    @BindView(R.id.bt_setting)
    Button bt_setting;

    @BindView(R.id.sw_voice)
    SwitchCompat sw_voice;

    private  ConfigInfo configInfo;
    private  ArrayList<String> models = new ArrayList<>();
    @Override
    public void afterBindView() {

        tv_tips.setText("设 置");
        ll_login_out.setOnClickListener(v -> {

            DialogManage.getInstance().TipDialog("确定退出登录吗？", new DialogClickListener() {
                @Override
                public void Cancel() {

                }

                @Override
                public void Submit() {
                    sp.setLogin(false);
                    sp.putBoolean("save_station",false);
                    sp.putString("get_station_name",null);
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        });

        ll_res_station.setOnClickListener(v -> {
            DialogManage.getInstance().TipDialog("是要重新选择上车站点吗？", new DialogClickListener() {
                @Override
                public void Cancel() {

                }

                @Override
                public void Submit() {
                    sp.putBoolean("save_station",false);
                    Intent intent = new Intent(mContext, StationActivity.class);
                    intent.putExtra("stationType",0);
                    startActivity(intent);
                    finish();
                }
            });
        });

        bt_setting.setOnClickListener(v -> {
            configInfo.setAbovePlayTime(Integer.parseInt(et_above_time.getText().toString()));
            configInfo.setBelowPlayTime(Integer.parseInt(et_below_time.getText().toString()));
            DBManager.getManager(mContext).addConfigInfo(configInfo);
            LiveEventBus.get("banner_setting").post(configInfo);
            finish();
        });
    }

    @Override
    public void processData() {
        configInfo = DBManager.getManager(mContext).queryConfig();
        if (configInfo.getMainModel() ==null){
            configInfo = new ConfigInfo().initConfigInfo();
        }
        Log.d("配置信息",configInfo.toString());
        models.add("上下图片");
        models.add("上频下图");
        models.add("全屏图片");
        models.add("全屏视频");
        sv_main_model.setItemsData(models,sv_main_model);
        sv_main_model.setmItemOnClickListener(new SelectView.ItemOnClickListener() {
            @Override
            public void itemOnClickListener(String text) {
                switch (text){
                    case "上下图片":
                        ll_above.setVisibility(View.VISIBLE);
                        ll_below.setVisibility(View.VISIBLE);
                        ll_video.setVisibility(View.GONE);
                        configInfo.setMainModel("0");
                        break;
                    case "上频下图":
                        ll_above.setVisibility(View.GONE);
                        ll_below.setVisibility(View.VISIBLE);
                        ll_video.setVisibility(View.VISIBLE);
                        configInfo.setMainModel("1");
                        break;
                    case "全屏图片":
                        ll_above.setVisibility(View.GONE);
                        ll_below.setVisibility(View.VISIBLE);
                        ll_video.setVisibility(View.GONE);
                        configInfo.setMainModel("2");
                        break;
                    case "全屏视频":
                        ll_above.setVisibility(View.GONE);
                        ll_below.setVisibility(View.GONE);
                        ll_video.setVisibility(View.VISIBLE);
                        configInfo.setMainModel("3");
                        break;
                }
            }
        });

        sw_voice.setChecked(configInfo.getVideoVoice().equals("1"));
        sw_voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    configInfo.setVideoVoice("1");
                }else {
                    configInfo.setVideoVoice("0");
                }
            }
        });

        String mainModel = configInfo.getMainModel();
        if (mainModel ==null){
            mainModel ="0";
        }
        switch (mainModel){
            case "0":
                ll_above.setVisibility(View.VISIBLE);
                ll_below.setVisibility(View.VISIBLE);
                ll_video.setVisibility(View.GONE);
                break;
            case "1":
                ll_above.setVisibility(View.GONE);
                ll_below.setVisibility(View.VISIBLE);
                ll_video.setVisibility(View.VISIBLE);
                break;
            case "2":
                ll_above.setVisibility(View.GONE);
                ll_below.setVisibility(View.VISIBLE);
                ll_video.setVisibility(View.GONE);
                break;
            case "3":
                ll_above.setVisibility(View.GONE);
                ll_below.setVisibility(View.GONE);
                ll_video.setVisibility(View.VISIBLE);
                break;
        }
        sv_main_model.getTvLeft().setText(models.get(Integer.parseInt(mainModel)));

        int abovePlayTime = configInfo.getAbovePlayTime();
        et_above_time.setText(String.valueOf(abovePlayTime));

        int belowPlayTime = configInfo.getBelowPlayTime();
        et_below_time.setText(String.valueOf(belowPlayTime));
    }
}