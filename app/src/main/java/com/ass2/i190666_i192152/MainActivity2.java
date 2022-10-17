package com.ass2.i190666_i192152;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    CheckBox bt_show;
    Button bt3;
    Button btn0;
    Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        bt_show = findViewById(R.id.bt_show);
        bt3 = findViewById(R.id.bt3);
        btn0 = findViewById(R.id.btn0);
        signup_btn = findViewById(R.id.signup_btn);



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

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity2.this, MainActivity3.class));
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity2.this, MainActivity5.class));
            }
        });
    }
}