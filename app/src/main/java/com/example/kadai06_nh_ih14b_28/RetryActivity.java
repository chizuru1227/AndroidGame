package com.example.kadai06_nh_ih14b_28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RetryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retry);
        String str = String.valueOf(HolderCallBack.bar_count);
        TextView title_txt = findViewById(R.id.title_txt);
        TextView score_txt = findViewById(R.id.score_txt);

        if(HolderCallBack.bar_count == 9999){
            title_txt.setText(R.string.clear);
            score_txt.setText("おめでとう");
        }else{
        score_txt.setText("score:" + str);
        }

        Button retry_btn = findViewById(R.id.retry_btn);
        retry_btn.setOnClickListener( v->{
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        });
    }
}