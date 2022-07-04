package com.geo.mvpframe_maters.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.geo.mvpframe_maters.R;

public class LoadDialog extends Dialog {

    public LoadDialog(Context context) {
        super(context, R.style.loading_dialog);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(LoadDialog.this.isShowing())
                    LoadDialog.this.dismiss();
                break;
        }
        return true;
    }


    private void initView(){
        setContentView(R.layout.dialog_loading);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.load_animation);
        animation.setInterpolator(new LinearInterpolator());
        findViewById(R.id.loading_dialog_img).startAnimation(animation);

        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha=0.8f;
        getWindow().setAttributes(attributes);
        setCancelable(false);
    }
}