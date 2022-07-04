package com.geo.mvpframe_maters.activity.LoginActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import com.bumptech.glide.request.RequestOptions;
import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.activity.SplashActivity.SplashActivity;
import com.geo.mvpframe_maters.activity.StationActivity.StationActivity;
import com.geo.mvpframe_maters.mvp.base.activity.BaseMvpActivity;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.utils.BlurTransformation;
import com.geo.mvpframe_maters.utils.ToastUtils;
import com.geo.mvpframe_maters.widget.AnimatorButton;

import java.util.Objects;

import butterknife.BindView;


@ViewInject(mainLayoutId = R.layout.activity_login)
public class LoginActivity extends BaseMvpActivity implements LoginContract.IView{
    @BindView(R.id.iv_background)
    ImageView iv_background;
    @BindView(R.id.et_accountNum)
    EditText et_accountNum;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.bt_login)
    AnimatorButton bt_login;

    @InjectPresenter
    private LoginPresenter mPresenter;
    @Override
    public void afterBindView() {

        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation(mContext, 25,3));
        Glide.with(mContext).load(R.drawable.icon_splash)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(this,25,3)))
                .into(iv_background);

    }

    @Override
    public void processData() {

        bt_login.setOnClickListener(v -> {
            if (!IS_USER_NETWORK){
                ToastUtils.toast(mContext,"当前网络不可用");
                return;
            }
            String accountNum = et_accountNum.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if (accountNum.isEmpty()){
                ToastUtils.toast(mContext,"账号不能为空");
                return;
            }
            if (password.isEmpty()){
                ToastUtils.toast(mContext,"密码不能为空");
                return;
            }
            mPresenter.Login(accountNum,password);
            showLoading();
            bt_login.startAnim();
        });
    }

    @Override
    public void LoginSuccess() {
        hideLoading();
        bt_login.gotoNew();
        ToastUtils.toast(mContext,"登录成功");
        sp.setLogin(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bt_login.regainBackground();
            }
        },500);
        Intent intent = new Intent(mContext, StationActivity.class);
        intent.putExtra("stationType",0);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoginFailure() {
        hideLoading();
        bt_login.gotoNew();
        ToastUtils.toast(mContext,"登录失败");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bt_login.regainBackground();
            }
        },500);
        sp.setLogin(false);

    }
}