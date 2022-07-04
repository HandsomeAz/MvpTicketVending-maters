package com.geo.mvpframe_maters.activity.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.activity.LoginActivity.LoginActivity;
import com.geo.mvpframe_maters.activity.MainActivity.MainActivity;
import com.geo.mvpframe_maters.activity.StationActivity.StationActivity;
import com.geo.mvpframe_maters.activity.TestActivity;
import com.geo.mvpframe_maters.activity.TestContract;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;

import java.util.ArrayList;

import static com.geo.mvpframe_maters.constant.Constants.ACCOUNT_KEY;
import static com.geo.mvpframe_maters.constant.Constants.PASSWORD_KEY;

@ViewInject(mainLayoutId = R.layout.activity_splash)
public class SplashActivity extends BaseMvpActivity implements SplashContract.IView{

    @InjectPresenter
    private SplashPresenter mPresenter;

    @Override
    public void afterBindView() {

    }

    @Override
    public void processData() {
        requestPermissions(99);
    }

    private void requestPermissions(int requestCode) {
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
                requestPerssionArr.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                requestPerssionArr.add(Manifest.permission.ACCESS_FINE_LOCATION);
                requestPerssionArr.add(Manifest.permission.ACCESS_FINE_LOCATION);
                requestPerssionArr.add(Manifest.permission.BLUETOOTH);
                requestPerssionArr.add(Manifest.permission.BLUETOOTH_ADMIN);
                requestPerssionArr.add(Manifest.permission.ACCESS_WIFI_STATE);
                requestPerssionArr.add(Manifest.permission.CHANGE_WIFI_STATE);
                requestPerssionArr.add(Manifest.permission.INTERNET);
                requestPerssionArr.add(Manifest.permission.INSTALL_PACKAGES);
                requestPerssionArr.add(Manifest.permission.CHANGE_NETWORK_STATE);
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
            }else {
                nextActivity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 99) {
            if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED&&
                        ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    nextActivity();
                }
            } else {
                Toast.makeText(getApplicationContext(), "授权不通过", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void nextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=null;
                if (sp.isLogin()){
                    ACCOUNT_KEY = sp.getString("accessKey","");
                    PASSWORD_KEY = sp.getString("secretAccessKey","");
                    //已经登录跳转主页
                    boolean save_station = sp.getBoolean("save_station", false);
                    if (save_station){
                        intent = new Intent(mContext, MainActivity.class);
                    }else {
                        intent = new Intent(mContext, StationActivity.class);
                        intent.putExtra("stationType",0);
                    }

                }else {
                    //跳转登录界面
                    intent = new Intent(mContext, LoginActivity.class);
                    //关闭界面
                }
                startActivity(intent);
                finish();
            }
        },1500);


    }
    /**
     * 屏蔽物理返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}