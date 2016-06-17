package com.daijia.library;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * Created by flb on 16/6/15.
 */
public class FABBaseDialog implements BaseLayout.RevealCompleteListener {

    View mFloatActionButton;
    Activity mActivity;
    BaseLayout mBaseDialog;
    FrameLayout mBackgroundLayout;

    DialogDelegate mDelegate;

    public FABBaseDialog(Context context, View fab, int resId){

        mActivity = (Activity) context;
        mFloatActionButton = fab;

        View toolbar = mActivity.getLayoutInflater().inflate(resId,null);
        createToolbar(toolbar);
    }

    public FABBaseDialog(Context context, View fab, View toolbar){

        mActivity = (Activity) context;
        mFloatActionButton = fab;

        createToolbar(toolbar);
    }

    void createToolbar(View toolbar){
        mBackgroundLayout = new FrameLayout(mActivity);
        mBackgroundLayout.setBackgroundColor(0xff000000);
        mBackgroundLayout.setAlpha(0);
        mBackgroundLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mBackgroundLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mBaseDialog = new BaseLayout(mActivity);
        mBaseDialog.setRevealCompleteListener(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM;
        mBaseDialog.addView(toolbar,layoutParams);
        mBaseDialog.setLayoutParams(layoutParams);
    }

    public void setOnClickListener(int viewId,View.OnClickListener listener){
        View view = mBaseDialog.findViewById(viewId);
        setOnClickListener(view,listener);
    }

    public void setOnClickListener(int[] viewIds,View.OnClickListener listener){
        for (int viewId : viewIds){
            setOnClickListener(viewId,listener);
        }
    }

    private void setOnClickListener(View view, View.OnClickListener listener){
        if(view != null){
            view.setOnClickListener(listener);
        }
    }

    public int ANIMATION = 0;
    public static int NORMAL = 1;
    int STATE = NORMAL;


    void setState(int state){
        STATE = state;
    }

    boolean isAnimation(){
        return STATE == ANIMATION;
    }

    public void showAsToolBar(){
        setDelegate(new FABExpandBuilder.ToolbarImpl());

        show();
    }

    public void showAsDialog(){

        setDelegate(new FABExpandBuilder.DialogImpl());

        show();
    }

    protected void show(){

        if(isAnimation()){
            return;
        }

        ViewGroup contentView = ((ViewGroup) mFloatActionButton.getRootView().findViewById(android.R.id.content));
        if(mBaseDialog.getParent() == null){
            contentView.addView(mBackgroundLayout);
            contentView.addView(mBaseDialog);
        }

        if(mDelegate != null){
            mDelegate.onLayout();
        }


        setState(ANIMATION);

        if(mDelegate != null){
            mDelegate.onShow();
        }


    }


    public void dismiss(){

        if(isAnimation()){
            return;
        }
        setState(ANIMATION);

        if(mDelegate != null){
            mDelegate.onDismiss();
        }
    }







    @Override
    public void onComplete(boolean isExpand) {
        if(isExpand){
            setState(NORMAL);
        }
    }

    public void setDelegate(DialogDelegate mDelegate) {
        this.mDelegate = mDelegate;
        mDelegate.mBase = this;
        mDelegate.mBaseDialog = mBaseDialog;
        mDelegate.mBackgroundLayout = mBackgroundLayout;
        mDelegate.mFloatActionButton = mFloatActionButton;
        mDelegate.mActivity = mActivity;
    }

    public static abstract class DialogDelegate{
        View mFloatActionButton;
        BaseLayout mBaseDialog;
        FrameLayout mBackgroundLayout;
        FABBaseDialog mBase;
        Activity mActivity;
        abstract void onLayout();
        abstract void onShow();
        abstract void onDismiss();

    }
}
