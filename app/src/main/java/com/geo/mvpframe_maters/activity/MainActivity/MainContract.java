package com.geo.mvpframe_maters.activity.MainActivity;

import androidx.fragment.app.Fragment;

import com.geo.mvpframe_maters.mvp.IBaseView;

public interface MainContract {
    interface IModel {


    }

    interface IView extends IBaseView {


        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
    }

    interface IPresenter {


        void initHomeFragment();
    }


}
