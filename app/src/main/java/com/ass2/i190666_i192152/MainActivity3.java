package com.ass2.i190666_i192152;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity3 extends AppCompatActivity {

    CheckBox bt_show;
    Button signup_btn, forget_pass, signin_btn ;
    EditText editEmail, editPass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        bt_show =findViewById(R.id.bt_show);
        signup_btn = findViewById(R.id.signup_btn);
        signin_btn = findViewById(R.id.signin_btn);
        editEmail = findViewById(R.id.email_signin);
        editPass = findViewById(R.id.pass_signin);
        forget_pass = findViewById(R.id.forget_pass);
        mAuth = FirebaseAuth.getInstance();

        bt_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity3.this, MainActivity2.class));
            }
        });
        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity3.this, Forget_password.class));
            }
        });
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


    }

    void userLogin()
    {
        String email = editEmail.getText().toString().trim();
        String password = editPass.getText().toString().trim();

        if(email.isEmpty())
        {
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editEmail.setError("please enter a valid email");
            editEmail.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            editPass.setError("password required");
            editPass.requestFocus();
            return;
        }

        if(password.length() < 6)
        {
            editPass.setError("Min password length is 6 charachters");
            editPass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            Toast.makeText(MainActivity3.this, "Logging in", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity3.this, MainActivity5.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity3.this, "Failed to login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}