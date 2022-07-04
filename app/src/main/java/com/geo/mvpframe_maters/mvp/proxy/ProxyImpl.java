package com.geo.mvpframe_maters.mvp.proxy;

import android.util.Log;

import com.geo.mvpframe_maters.mvp.BaseMvpPresenter;
import com.geo.mvpframe_maters.mvp.IBaseView;
import com.geo.mvpframe_maters.mvp.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProxyImpl implements IProxy {

    private IBaseView mView;
    private List<BaseMvpPresenter> mInjectPresenters;

    public ProxyImpl(IBaseView view) {
        this.mView = view;
        mInjectPresenters = new ArrayList<>();
    }


    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    public void bindPresenter() {
        //获得已经申明的变量，包括私有的
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            //获取变量上面的注解类型
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                try {
                    Class<? extends BaseMvpPresenter> type = (Class<? extends BaseMvpPresenter>) field.getType();
                    BaseMvpPresenter mInjectPresenter = type.newInstance();
                    mInjectPresenter.onAttach(mView);
                    field.setAccessible(true);
                    field.set(mView, mInjectPresenter);
                    mInjectPresenters.add(mInjectPresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    throw new RuntimeException("SubClass must extends Class:BaseMvpPresenter");
                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        for (BaseMvpPresenter presenter : mInjectPresenters) {
            presenter.onDetach();
        }
        mInjectPresenters.clear();
        mInjectPresenters = null;
    }
}

