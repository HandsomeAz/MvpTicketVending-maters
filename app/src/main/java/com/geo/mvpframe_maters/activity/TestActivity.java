package com.geo.mvpframe_maters.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.TextView;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;

import java.util.ArrayList;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_test)
public class TestActivity extends BaseMvpActivity implements TestContract.IView{

    @InjectPresenter
    private TestPresenter mPresenter;

    @BindView(R.id.tv_test)
    TextView tv_test;
    @Override
    public void afterBindView() {
        tv_test.setOnClickListener(v -> {
            mPresenter.getTestData();
            showLoading();
        });
    }

    @Override
    public void processData() {
        requestPermissions(99);
    }
    public void requestPermissions(int requestCode) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                ArrayList<String> requestPerssionArr = new ArrayList<>();
                int hasCamrea = checkSelfPermission(Manifest.permission.CAMERA);
                if (hasCamrea != PackageManager.PERMISSION_GRANTED) {
                    requestPerssionArr.add(Manifest.permission.CAMERA);
                }

                int hasSdcardRead = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                if (hasSdcardRead != PackageManager.PERMISSION_GRANTED) {
                    requestPerssionArr.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                }

                int hasSdcardWrite = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (hasSdcardWrite != PackageManager.PERMISSION_GRANTED) {
                    requestPerssionArr.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                requestPerssionArr.add(Manifest.permission.BLUETOOTH);
                requestPerssionArr.add(Manifest.permission.BLUETOOTH_ADMIN);
                requestPerssionArr.add(Manifest.permission.ACCESS_WIFI_STATE);
                requestPerssionArr.add(Manifest.permission.CHANGE_WIFI_STATE);
                requestPerssionArr.add(Manifest.permission.INTERNET);
                requestPerssionArr.add(Manifest.permission.INSTALL_PACKAGES);
                requestPerssionArr.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                requestPerssionArr.add(Manifest.permission.ACCESS_FINE_LOCATION);
                requestPerssionArr.add(Manifest.permission.CHANGE_NETWORK_STATE);
                requestPerssionArr.add(Manifest.permission.ACCESS_FINE_LOCATION);
                requestPerssionArr.add(Manifest.permission.ACCESS_NETWORK_STATE);
                requestPerssionArr.add(Manifest.permission.READ_PHONE_STATE);



                // 是否应该显示权限请求
                if (requestPerssionArr.size() >= 1) {
                    String[] requestArray = new String[requestPerssionArr.size()];
                    for (int i = 0; i < requestArray.length; i++) {
                        requestArray[i] = requestPerssionArr.get(i);
                    }
                    requestPermissions(requestArray, requestCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTestData(String s) {
        tv_test.setText(s);
    }
}