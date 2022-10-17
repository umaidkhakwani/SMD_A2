package com.ass2.i190666_i192152;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    CheckBox bt_show;
    EditText pass,name,email;
    Button signin;
    Button signup_btn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        bt_show = findViewById(R.id.bt_show);
        pass = findViewById(R.id.pass);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        signin = findViewById(R.id.signin);
        signup_btn = findViewById(R.id.signup_btn);
        mAuth = FirebaseAuth.getInstance();

        bt_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity2.this, MainActivity3.class));
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(
                        email.getText().toString(),
                        pass.getText().toString()
                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(
                                            MainActivity2.this,
                                            "successfully added",
                                            Toast.LENGTH_LONG
                                    ).show();
                                    Toast.makeText(
                                            MainActivity2.this,
                                            mAuth.getCurrentUser().getUid(),
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            }
                        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(
                                MainActivity2.this,
                                "Failed to create user",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });

              //  startActivity(new Intent( MainActivity2.this, MainActivity5.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null)
        {
            Toast.makeText(
                    MainActivity2.this,
                    user.getUid() + "",
                    Toast.LENGTH_SHORT
            );
        }
    }
}