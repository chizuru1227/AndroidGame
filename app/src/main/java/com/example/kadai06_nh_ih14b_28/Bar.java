package com.example.kadai06_nh_ih14b_28;

import android.util.Log;

public class Bar {
    //表示座標
    public float x, y;
    //バーのサイズ
    public float half_size = 100;
    //コンストラクタ
    public Bar( int _x, int _y ){
        //初期座標をセット
        x = (float)_x;
        y = (float)_y - 40;
    }
    // 右移動
    public void right(float touch_x){
        x  = touch_x - half_size;
    }
    // 左移動
    public void left(float touch_x){
        x =  touch_x - half_size;
    }
}