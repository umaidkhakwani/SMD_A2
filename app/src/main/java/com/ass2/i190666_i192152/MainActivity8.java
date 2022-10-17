package com.ass2.i190666_i192152;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity9.class));
            finish();
        }, 2000);
    }
}