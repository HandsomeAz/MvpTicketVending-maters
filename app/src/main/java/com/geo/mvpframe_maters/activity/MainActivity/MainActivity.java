package com.geo.mvpframe_maters.activity.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.activity.SettingActivity.SettingActivity;
import com.geo.mvpframe_maters.activity.TestPresenter;
import com.geo.mvpframe_maters.application.MyApplication;
import com.geo.mvpframe_maters.constant.Constants;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.utils.SharePreUtil;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseMvpActivity implements MainContract.IView {
    @InjectPresenter
    private MainPresenter mPresenter;

    @BindView(R.id.tv_tips)
    TextView tv_tips;
    @BindView(R.id.tv_version)
    TextView tv_version;
    @BindView(R.id.iv_setting)
    ImageView iv_setting;
    /**
     * 时间单位。这里默认设置1000毫秒，判断是否是在这1000毫秒之内用户双击或者三击了
     */
    private static final int TIME_UNIT = 1000;

    long[] mHits = new long[3];
    @Override
    public void processData() {
        mPresenter.initHomeFragment();
    }

    @Override
    public void afterBindView() {
        String station_name = sp.getString("get_station_name", "未选上车站点");
        Constants.departStationCode = sp.getString("get_station_code", "001");
        tv_tips.setText(station_name);
        tv_version.setText("设备号："+ Constants.ACCOUNT_KEY+"(" +MyApplication.getAppVersionName(mContext)+")");
    }

    @Override
    protected void addListeners() {
        super.addListeners();
        iv_setting.setOnClickListener(v -> {
            System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
            mHits[mHits.length - 1] = SystemClock.uptimeMillis();
            if (mHits[0] >= (SystemClock.uptimeMillis() - TIME_UNIT)) {
                Intent intent = new Intent(mContext, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().replace( R.id.fl_main_layout,mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharePreUtil.getInstance(mContext).putString("person_address","");
        SharePreUtil.getInstance(mContext).putString("person_phone","");
    }
}