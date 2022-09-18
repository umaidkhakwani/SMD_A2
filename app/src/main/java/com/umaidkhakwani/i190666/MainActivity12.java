package com.umaidkhakwani.i190666;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity13.class));
            finish();
        }, 2000);
    }
}