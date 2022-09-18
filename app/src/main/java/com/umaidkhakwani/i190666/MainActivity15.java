package com.umaidkhakwani.i190666;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity15 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity16.class));
            finish();
        }, 2000);
    }
}