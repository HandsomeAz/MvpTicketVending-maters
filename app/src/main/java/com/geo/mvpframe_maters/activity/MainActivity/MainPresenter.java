package com.geo.mvpframe_maters.activity.MainActivity;


import androidx.fragment.app.Fragment;

import com.geo.mvpframe_maters.activity.MainActivity.fragment.ImageFragment;
import com.geo.mvpframe_maters.mvp.BaseMvpPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainPresenter extends BaseMvpPresenter<MainContract.IView, MainModel> implements MainContract.IPresenter{
    private List<Fragment> fragmentList =new ArrayList<>();
    //当前fragment的角标
    private int mCurrentFragmentIndex =-1;

    @Override
    public void initHomeFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add( new ImageFragment());


        replaceFragment(0);
    }

    private void replaceFragment(int mCurrentFragmentIndex) {
        if (this.mCurrentFragmentIndex == mCurrentFragmentIndex){
            return;
        }
        for (int i = 0; i < fragmentList.size(); i++) {
            if (mCurrentFragmentIndex != i){
                if (fragmentList.get(i) != null){
                    hideFragment(fragmentList.get(i));
                }
            }
        }

        Fragment mFragment = fragmentList.get(mCurrentFragmentIndex);
        if (mFragment != null){
            addAndShowFragment(mFragment);
            setCurrentIndex(mCurrentFragmentIndex);
        }else {
            newCurrentFragment(mCurrentFragmentIndex);
        }

    }
    private void setCurrentIndex(int mCurrentFragmentIndex) {

        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
    }

    //重新创建
    private void newCurrentFragment(int mCurrentFragmentIndex) {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex){
            case 0:
                fragment = new ImageFragment();
                break;
            case 1:

                break;
            case 2:

                break;
        }
        addAndShowFragment(fragment);
    }

    //显示
    private void addAndShowFragment(Fragment mFragment) {

        if (mFragment.isAdded()){
            getView().showFragment(mFragment);
        }else {
            getView().addFragment(mFragment);
        }
    }

    //隐藏
    private void hideFragment(Fragment mFragment) {

        if (mFragment !=null && mFragment.isVisible()){
            getView().hideFragment(mFragment);
        }
    }

}
