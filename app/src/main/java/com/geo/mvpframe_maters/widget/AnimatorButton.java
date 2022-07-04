package com.geo.mvpframe_maters.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import com.geo.mvpframe_maters.R;


/**
 * Created by zlj
 */

public class AnimatorButton extends androidx.appcompat.widget.AppCompatButton {

    private int width;
    private int heigh;

    private GradientDrawable backDrawable;

    private boolean isMorphing;
    private int startAngle;

    private Paint paint;

    private ValueAnimator arcValueAnimator;

    public AnimatorButton(Context context) {
        super(context);
        init(context);
    }

    public AnimatorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimatorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        isMorphing=false;
        int[] colors = { 0xFF99FF33, 0xFF66FF99,
                0xFF66FF00 };
        backDrawable=new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,colors);
//        int colorDrawable=context.getResources().getColor(R.color.purple_700);
//        backDrawable.setColor(colorDrawable);
        backDrawable.setCornerRadius(120);
        setBackground(backDrawable);

        setText("登 录");

        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.white));
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode= MeasureSpec.getMode(widthMeasureSpec);
        int widthSize= MeasureSpec.getSize(widthMeasureSpec);
        int heighMode= MeasureSpec.getMode(heightMeasureSpec);
        int heighSize= MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode== MeasureSpec.EXACTLY){
            width=widthSize;
        }
        if (heighMode== MeasureSpec.EXACTLY){
            heigh=heighSize;
        }
    }

    public void startAnim(){
        isMorphing=true;
        this.setClickable(false);
        setText("");
        ValueAnimator valueAnimator= ValueAnimator.ofInt(width,heigh);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value= (int) animation.getAnimatedValue();
                int leftOffset=(width-value)/2;
                int rightOffset=width-leftOffset;

                backDrawable.setBounds(leftOffset,0,rightOffset,heigh);
            }
        });
        ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(backDrawable,"cornerRadius",120,heigh/2);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,objectAnimator);
        animatorSet.start();

        //画中间的白色圆圈

        showArc();
    }
    public void gotoNew(){
        isMorphing=false;
        this.setClickable(true);
        arcValueAnimator.cancel();
        setVisibility(GONE);

    }
    public void regainBackground(){
        setVisibility(VISIBLE);
        backDrawable.setBounds(0,0,width,heigh);
        backDrawable.setCornerRadius(120);
        setBackground(backDrawable);
        setText("登 录");
        isMorphing=false;

    }

    private void showArc() {
        arcValueAnimator= ValueAnimator.ofInt(0,1080);
        arcValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle= (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        arcValueAnimator.setInterpolator(new LinearInterpolator());
        arcValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        arcValueAnimator.setDuration(2000);
        arcValueAnimator.start();


    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

       if (isMorphing){
           final RectF rectF=new RectF(getWidth()*5/12,getHeight()/7,getWidth()*7/12,getHeight()-getHeight()/7);
           canvas.drawArc(rectF,startAngle,270,false,paint);
       }
    }
}
