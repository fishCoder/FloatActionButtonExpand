package com.daijia.library;

import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by flb on 16/6/16.
 */
public class FABExpandBuilder {



    public  static class ToolbarImpl extends FABBaseDialog.DialogDelegate{


        public void onLayout() {
            mBackgroundLayout.setOnClickListener(null);
        }


        public void onShow() {
            mBaseDialog.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mBaseDialog.getViewTreeObserver().removeOnPreDrawListener(this);
                    startAnimation();
                    return false;
                }
            });
        }


        public void onDismiss() {
            dismissAnimation();
        }

        protected void startAnimation(){
            int measuredHeight = mBaseDialog.getMeasuredHeight();
            int measureWidth = mBaseDialog.getMeasuredWidth();

            int fabHeight = mFloatActionButton.getMeasuredWidth();
            int fabWidth  = mFloatActionButton.getMeasuredHeight();

            int[] loc = new int[2];
            mBaseDialog.getLocationInWindow(loc);
            int[] centerLocation = new int[]{
                    loc[0]+measureWidth/2-fabWidth/2,
                    loc[1]+measuredHeight/2-fabHeight/2
            };

            int[] mFabLocation = new int[2];
            mFloatActionButton.getLocationInWindow(mFabLocation);

            ViewCompat.
                    animate(mFloatActionButton).
                    x(centerLocation[0]).
                    y(centerLocation[1]).
                    alpha(0.0f).
                    setDuration(200).
                    setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {

                        }

                        @Override
                        public void onAnimationEnd(View view) {
                            mFloatActionButton.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(View view) {

                        }
                    }).
                    start();

            mBaseDialog.setVisibility(View.GONE);
            mFloatActionButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBaseDialog.startRevealAnimation();
                }
            },150);



        }


        protected void dismissAnimation(){
            int[] loc = new int[2];
            mBaseDialog.getLocationInWindow(loc);

            mBaseDialog.dismissRevealAnimation();

            mBaseDialog.postDelayed(new Runnable() {
                @Override
                public void run() {

                    mBaseDialog.setVisibility(View.GONE);
                    mFloatActionButton.setVisibility(View.VISIBLE);
                    ViewCompat.animate(mFloatActionButton).
                            translationX(0).translationY(0).
                            alpha(1.0f).
                            setDuration(200).
                            setListener(new ViewPropertyAnimatorListener() {
                                @Override
                                public void onAnimationStart(View view) {

                                }

                                @Override
                                public void onAnimationEnd(View view) {
                                    mBase.setState(FABBaseDialog.NORMAL);
                                    mBackgroundLayout.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationCancel(View view) {

                                }
                            }).
                            start();
                }
            },380);
        }
    }


    public static class DialogImpl extends FABBaseDialog.DialogDelegate{

        float mAlpha = 0.3f;


        public void onLayout() {
            int[] loc = new int[2];
            mFloatActionButton.getLocationOnScreen(loc);

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;

            Point size = new Point();
            mActivity.getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;
            int height = size.y;
            layoutParams.rightMargin = width-mFloatActionButton.getRight();
            layoutParams.bottomMargin = height-mFloatActionButton.getBottom();
            mBaseDialog.setLayoutParams(layoutParams);
        }


        public void onShow() {
            mBaseDialog.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mBaseDialog.getViewTreeObserver().removeOnPreDrawListener(this);
                    startAnimation();
                    return false;
                }
            });
        }


        public void onDismiss() {
            dismissAnimation();
        }

        protected void startAnimation(){
            int measuredHeight = mBaseDialog.getMeasuredHeight();
            int measureWidth = mBaseDialog.getMeasuredWidth();

            int fabHeight = mFloatActionButton.getMeasuredWidth();
            int fabWidth  = mFloatActionButton.getMeasuredHeight();

            int[] loc = new int[2];
            mBaseDialog.getLocationInWindow(loc);
            int[] centerLocation = new int[]{
                    loc[0]+measureWidth/2-fabWidth/2,
                    loc[1]+measuredHeight-fabHeight
            };

            int[] mFabLocation = new int[2];
            mFloatActionButton.getLocationInWindow(mFabLocation);

            ViewCompat.
                    animate(mFloatActionButton).
                    x(centerLocation[0]).
                    y(centerLocation[1]).
                    alpha(0.0f).
                    setDuration(200).
                    setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {

                        }

                        @Override
                        public void onAnimationEnd(View view) {
                            mFloatActionButton.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(View view) {

                        }
                    }).
                    start();

            mBaseDialog.setVisibility(View.GONE);
            mFloatActionButton.postDelayed(new Runnable() {
                @Override
                public void run() {

                    mBackgroundLayout.setVisibility(View.VISIBLE);
                    ViewCompat.
                            animate(mBackgroundLayout).
                            alpha(mAlpha).
                            setDuration(200).
                            start();

                    mBaseDialog.startRevealAnimation(new int[]{mBaseDialog.getMeasuredWidth()/2,mBaseDialog.getMeasuredHeight()});
                }
            },150);


        }


        protected void dismissAnimation(){
            int[] loc = new int[2];
            mBaseDialog.getLocationInWindow(loc);

            mBaseDialog.dismissRevealAnimation(new int[]{mBaseDialog.getMeasuredWidth()/2,mBaseDialog.getMeasuredHeight()});

            mBaseDialog.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ViewCompat.animate(mBackgroundLayout).alpha(0f).setDuration(200).start();
                }
            },200);

            mBaseDialog.postDelayed(new Runnable() {
                @Override
                public void run() {

                    mBaseDialog.setVisibility(View.GONE);
                    mFloatActionButton.setVisibility(View.VISIBLE);
                    ViewCompat.animate(mFloatActionButton).
                            translationX(0).translationY(0).
                            alpha(1.0f).
                            setDuration(200).
                            setListener(new ViewPropertyAnimatorListener() {
                                @Override
                                public void onAnimationStart(View view) {

                                }

                                @Override
                                public void onAnimationEnd(View view) {
                                    mBackgroundLayout.setVisibility(View.GONE);
                                    mBase.setState(FABBaseDialog.NORMAL);
                                }

                                @Override
                                public void onAnimationCancel(View view) {

                                }
                            }).
                            start();
                }
            },380);
        }
    }

}
