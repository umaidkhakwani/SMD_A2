package com.umaidkhakwani.i190666;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity17 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity18.class));
            finish();
        }, 2000);
    }
}