package com.geo.mvpframe_maters.mvp;

public interface IBasePresenter<V extends IBaseView> {
    /**
     * 创建视图
     * @param view 视图
     */
    void onAttach(V view);

    /**
     * 销毁视图
     */
    void onDetach();
}
