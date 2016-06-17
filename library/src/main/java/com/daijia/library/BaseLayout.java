package com.daijia.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

/**
 * Created by flb on 16/6/15.
 */
public class BaseLayout extends FrameLayout {

    public static int STATE_START = 0;
    public static int STATE_END = 1;

    public BaseLayout(Context context) {
        super(context);
    }

    public BaseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public BaseLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Interpolator INTERPOLATOR = new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            return input;
        }
    };
    int FILL_TIME = 400;


    public class RevealInfo {
        int startX;
        int startY;
    }

    RevealInfo mRevealInfo = new RevealInfo();
    Path mClipPath = new Path();
    ObjectAnimator revealAnimator;

    RevealCompleteListener revealCompleteListener;

    private int currentRadius = 0;

    private int STATE = 1;

    void setState(int state){
        STATE = state;
    }

    public void startRevealAnimation(){
        revealAnimation(null,FILL_TIME,true);
    }

    public void startRevealAnimation(int[] locations){
        revealAnimation(locations,FILL_TIME,true);
    }

    public void dismissRevealAnimation(){
        revealAnimation(null,FILL_TIME,false);
    }

    public void dismissRevealAnimation(int[] locations){
        revealAnimation(locations,FILL_TIME,false);
    }

    public void setCurrentRadius(int radius){
        currentRadius = radius;
        invalidate();
    }

    public void revealAnimation(int[] locations, int duration, final boolean expand){
        setState(STATE_START);
        if(expand){
            setVisibility(VISIBLE);
        }

        if(locations == null){
            mRevealInfo.startX = getMeasuredWidth()/2;
            mRevealInfo.startY = getMeasuredHeight()/2;
        }else {
            mRevealInfo.startX = locations[0];
            mRevealInfo.startY = locations[1];
        }

        int endRadius = getMeasuredHeight() + getMeasuredWidth();
        revealAnimator = ObjectAnimator.ofInt(this, "currentRadius", expand?0:endRadius, expand?endRadius:0).setDuration(duration);
        revealAnimator.setInterpolator(INTERPOLATOR);
        revealAnimator.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                setState(STATE_END);

                if(!expand){
                    setVisibility(GONE);
                }

                if(revealCompleteListener != null){
                    revealCompleteListener.onComplete(expand);
                }
            }
        });
        revealAnimator.start();
    }


    public interface RevealCompleteListener{
        void onComplete(boolean isExpand);
    }


    public void setRevealCompleteListener(RevealCompleteListener revealCompleteListener) {
        this.revealCompleteListener = revealCompleteListener;
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if(STATE == STATE_START){
            mClipPath.reset();
            mClipPath.addCircle(mRevealInfo.startX,mRevealInfo.startY,currentRadius, Path.Direction.CW);
            canvas.clipPath(mClipPath);
        }
        return super.drawChild(canvas, child, drawingTime);
    }
}
