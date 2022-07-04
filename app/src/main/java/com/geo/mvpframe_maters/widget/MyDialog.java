package com.geo.mvpframe_maters.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.geo.mvpframe_maters.R;


public class MyDialog extends Dialog {
    //    style引用style样式
    public MyDialog(Context context, int width, int height, View layout, int style) {

        super(context, style);
        setContentView(layout);
        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height= height;
        layout.setLayoutParams(layoutParams);
        this.getWindow().getAttributes().y=10;
        this.getWindow().setGravity(Gravity.BOTTOM);
        this.getWindow().setWindowAnimations(R.style.MyDialog_Animation);

    }
    public MyDialog(Context context, int width, int height, View layout, int style, int gravity) {

        super(context, style);
        setContentView(layout);
        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        layout.setLayoutParams(layoutParams);
        this.getWindow().setGravity(gravity);
        this.getWindow().setWindowAnimations(R.style.MyDialog_Animation);


    }


}
