package com.geo.mvpframe_maters.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.geo.mvpframe_maters.R;
import com.geo.mvpframe_maters.application.MyApplication;
import com.geo.mvpframe_maters.utils.ActivityManageUtil;

public class MyBottomButton extends LinearLayout {
    private Context mContext;
    public MyBottomButton(Context context) {
        this(context,null);
    }

    public MyBottomButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyBottomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.bottom_button_layout, this);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> {
            MyApplication.getCurrentActivity().finish();
        });
        ImageView iv_home = findViewById(R.id.iv_home);
        iv_home.setOnClickListener(v -> {
            ActivityManageUtil.getInstance().exit();
        });
        RelativeLayout rl_bottom = findViewById(R.id.rl_bottom);
        rl_bottom.setAlpha(0.8f);
    }
}
