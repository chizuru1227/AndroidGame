package com.example.kadai06_nh_ih14b_28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_btn = findViewById(R.id.start_btn);
        start_btn.setOnClickListener( v->{
            Intent intent = new Intent(getApplication(), GameActivity.class);
            startActivity(intent);
        });
    }
}