package com.ass2.i190666_i192152;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_password extends AppCompatActivity {

    EditText textEmail;
    Button resetPass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        textEmail = findViewById(R.id.email_text);
        resetPass = findViewById(R.id.reset_btn);
        mAuth = FirebaseAuth.getInstance();

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    void resetPassword(){
        String email = textEmail.getText().toString().trim();

        if(email.isEmpty()){
            textEmail.setError("Email is required");
            textEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            textEmail.setError("Please Provide valid email");
            textEmail.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Forget_password.this, "check your email to reset password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Forget_password.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}