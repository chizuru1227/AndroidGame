package com.example.kadai06_nh_ih14b_28;

public class Ball {
    //ボールのサイズ
    int size = 30;
    // 表示座標
    float x, y;
    // 速度
    double vx, vy;
    // 画面の幅と高さ
    int view_w, view_h;
    // 弾の生死フラグ
    boolean isLive = true;

    //コンストラクタ
    public Ball( int _x, int _y, int width, int height ){
        //初期座標をセット
        x = (float)_x;
        y = (float)_y;
        //ビューの幅と高さをセット
        view_w = width;
        view_h = height;
        vx = 10;
        vy = 10;
    }
    public void move(){

        //玉を移動
        x = x + (float)vx;
        y = y + (float)vy;

        // 壁に当たった時の処理、速度を入れ替える
        if(x > view_w - size  ){
            vx = -vx;
            x = ( view_w - size );
        }else if( x < 0 ){
            vx = -vx;
            x = 0;
        }
        if( y < 0 ){
            vy = -vy;
            y = 0;
        }else if( y > view_h ){
            isLive = false;
        }
    }
}
