package com.geo.mvpframe_maters.mvp;

import android.content.Context;

public interface IBaseView {
    Context getContext();

    void showLoading(String message);

    void showLoading();

    void hideLoading();

    void showToast(String message);
}
