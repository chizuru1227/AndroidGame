package com.example.kadai06_nh_ih14b_28;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.SurfaceHolder;


public class HolderCallBack implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder holder = null;
    private Thread thread = null;
    private boolean isAttached = true;

    private float dx = 10, dy = 10;
    private float width, height;
    private int   circle_x, circle_y;

    private long t1 = 0, t2 = 0; // スリープ用変数

    private float touch_x; // タッチされたx座標
    int view_w = 480, view_h = 733;    // SurfaveViewの幅と高さ
    Ball ball;             //ボール
    Bar  bar;              //バー

    private int bar_m_size = 20;

    private Activity mActivity;

    public static int bar_count = 0;

    public HolderCallBack(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
        // TODO 自動生成されたメソッド・スタブ
        this.width = width;
        this.height = height;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO 自動生成されたメソッド・スタブ
        bar_count = 0;
        Log.i("view_h", String.valueOf(view_h));
        this.holder = holder;
        thread = new Thread(this);
        thread.start(); //スレッドを開始

        touch_x = view_w/2;
        // ボールとバーを生成
        bar  = new Bar( view_w/2 , view_h-100 );
        ball = new Ball( view_w/2, view_h/3 ,  view_w, view_h );
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO 自動生成されたメソッド・スタブ
        isAttached = false;
        thread = null; //スレッドを終了
    }

    @Override
    public void run() {
        // TODO 自動生成されたメソッド・スタブ
        // メインループ（無限ループ）
        while( isAttached ) {
            t1 = System.currentTimeMillis();

            // バー移動
            if (bar.x + bar.half_size < touch_x) {
                bar.right(touch_x);
            } else {
                bar.left(touch_x);
            }
            //ボール移動
            if (ball.isLive) {
                ball.move();
            }

            //衝突判定
            if (ball.y + ball.size > bar.y && ball.y < bar.y + 20) {
                if (ball.x > bar.x && ball.x < bar.x + bar.half_size * 2) {
                    ball.vy = -ball.vy;
                    ball.y = bar.y - ball.size;
                    bar_count = bar_count + 1;
                    if (bar_count % 5 == 0) {
                        if (bar_m_size < 100) {
                            bar.half_size = 100 - bar_m_size;
                            bar_m_size += 20;
                        } else {
                            bar_count = 9999;
                            isAttached = false;
                        }
                    }
                }
            }

                //描画処理を開始
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);

                if (ball.isLive) {
                    canvas.drawCircle(ball.x, ball.y, ball.size, paint);
                    paint.setColor(Color.WHITE);
                    canvas.drawRect(bar.x, bar.y, bar.x + bar.half_size * 2, bar.y + 20, paint);

                    String character = "SCORE:" + bar_count; //描画したい文字列
                    paint.setTextSize(30);
                    float textWidth = paint.measureText(character); //文字列の幅を取得
                    canvas.drawText(character,
                            view_w - textWidth,
                            30,
                            paint);
                } else {
                    Log.i("states", "game over");
                    isAttached = false;
                }

                //描画処理を終了
                holder.unlockCanvasAndPost(canvas);

                // スリープ
                t2 = System.currentTimeMillis();
                if (t2 - t1 < 16) { // 1000 / 60 = 16.6666
                    try {
                        Thread.sleep(16 - (t2 - t1));
                    } catch (InterruptedException e) {
                    }
            }
        }
        Intent intent = new Intent(mActivity, RetryActivity.class);
        intent.putExtra("SCORE", bar_count);
        mActivity.startActivity(intent);
    }

    public void getTouchPoint( float x, float y ){
        touch_x = x;
    }

}