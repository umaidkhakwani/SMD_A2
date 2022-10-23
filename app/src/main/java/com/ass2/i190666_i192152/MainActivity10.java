package com.ass2.i190666_i192152;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity10 extends AppCompatActivity {

    DrawerLayout drawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
//        new Handler().postDelayed(() -> {
//            startActivity(new Intent(this, MainActivity11.class));
//            finish();
//        }, 2000);

        drawerlayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        MainActivity5.openDrawer(drawerlayout);
    }

    public void ClickLogo(View view){
        MainActivity5.closeDrawer(drawerlayout);
    }

    public void ClickHome(View view){
        MainActivity5.redirectActivity(this, MainActivity5.class);
    }

    public void ClickLogout(View view){
        MainActivity5.logout(this);
    }

    public void ClickProfile(View view){
        MainActivity5.redirectActivity(this, MainActivity20.class);
    }

    public void ClickMessage(View view){
        MainActivity5.redirectActivity(this, MainActivity20.class);
    }


}