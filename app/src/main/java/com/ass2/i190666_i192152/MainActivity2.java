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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    CheckBox bt_show;
    EditText editpass,editname,editemail;
    Button signin;
    Button signup_btn;
    FirebaseAuth mAuth;
    ImageView editmale,editfemale, editbinary;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        bt_show = findViewById(R.id.bt_show);
        editpass = findViewById(R.id.pass);
        editname = findViewById(R.id.name);
        editemail = findViewById(R.id.email);
        signin = findViewById(R.id.signin);
        signup_btn = findViewById(R.id.signup_btn);
        editmale = findViewById(R.id.male);
        editfemale = findViewById(R.id.female);
        editbinary = findViewById(R.id.binary);
        mAuth = FirebaseAuth.getInstance();

        editmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "male";
            }
        });
        editfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "female";
            }
        });
        editbinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "binary";
            }
        });









        bt_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    editpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
                registerUser();
            }
        });
    }

    private void registerUser() {
        String pass = editpass.getText().toString();
        String name = editname.getText().toString();
        String email = editemail.getText().toString();

        if(name.isEmpty())
        {
            editname.setError("name is required!");
            editname.requestFocus();
            return;
        }

        if(pass.isEmpty())
        {
            editpass.setError("password is required!");
            editpass.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            editemail.setError("email is required!");
            editemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editemail.setError("please enter valid email");
            editemail.requestFocus();
            return;
        }

        if(pass.isEmpty())
        {
            editpass.setError("Password is required");
            editpass.requestFocus();
            return;
        }

        if(pass.length() < 6)
        {
            editpass.setError("Min password length should be 6 characters");
            editpass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user = new User(name, email, pass, gender);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(MainActivity2.this,
                                                        "Successfully added",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(MainActivity2.this,
                                                        "Failed to add",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        }
                        else
                        {
                            Toast.makeText(MainActivity2.this,
                                    "Failed to add. Try Again",
                                    Toast.LENGTH_LONG).show();
                        }
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