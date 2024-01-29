package com.example.kadai06_nh_ih14b_28;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SerSurfaceView extends android.view.SurfaceView {

    private HolderCallBack cb;
    private Activity mActivity;

    public SerSurfaceView(Context context, Activity activity) {
        super(context);
        SurfaceHolder holder = getHolder();
        mActivity = activity;
        cb = new HolderCallBack(mActivity);
        holder.addCallback(cb);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(); // X座標を取得
        float y = event.getY(); // Y座標を取得
        cb.getTouchPoint( x , y );
        return true;
    }
}