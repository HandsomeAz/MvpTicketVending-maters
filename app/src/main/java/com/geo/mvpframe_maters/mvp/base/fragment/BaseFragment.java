package com.geo.mvpframe_maters.mvp.base.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.mvp.inject.ViewInject;
import com.geo.mvpframe_maters.utils.ToastUtils;
import com.geo.mvpframe_maters.widget.LoadDialog;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements IBaseView {

    private Dialog loadingDialog;
    protected Context mContext;




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    @Override
    public void showToast(String message) {
        ToastUtils.toast(mContext,message);
    }

    @Override
    public void showLoading() {
        hideLoading();
        loadingDialog = new LoadDialog(mContext);
        loadingDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
            }
        },15000);
    }

    @Override
    public void showLoading(String message) {

        hideLoading();
        loadingDialog = new LoadDialog(mContext);
        loadingDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
            }
        },15000);
    }

    @Override
    public void hideLoading() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
            attributes.alpha=1.0f;
            getActivity().getWindow().setAttributes(attributes);
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }
}

