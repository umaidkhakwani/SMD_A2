package com.ass2.i190666_i192152;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity20 extends AppCompatActivity {

    CheckBox bt_show;
    Button bt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);

        bt_show = findViewById(R.id.bt_show);
        bt3 = findViewById(R.id.bt3);

        bt_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    bt3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    bt3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
}