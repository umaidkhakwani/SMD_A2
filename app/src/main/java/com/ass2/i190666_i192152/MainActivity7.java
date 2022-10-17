package com.ass2.i190666_i192152;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity8.class));
            finish();
        }, 2000);
    }
}