package com.ass2.i190666_i192152;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class MainActivity20 extends AppCompatActivity {

    CheckBox bt_show;
    EditText pass_update, email_update;
    ImageView dp;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;
    String user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);

        bt_show = findViewById(R.id.bt_show);
        pass_update = findViewById(R.id.pass_update);
        email_update = findViewById(R.id.email_update);
        dp = findViewById(R.id.dp);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");

        bt_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    pass_update.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    pass_update.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });








        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Dp").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            if(String.valueOf(task.getResult().getValue())!="null"){
                                Picasso.get().load(String.valueOf(task.getResult().getValue())).into(dp);
                            }

                        }
                    }
                });





        mAuth = FirebaseAuth.getInstance();

        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,20);

                user_id = mAuth.getInstance().getCurrentUser().getUid();
                ref = database.getReference("Users").child(user_id);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==20 & resultCode==RESULT_OK){
            Uri img =data.getData();
//            dp.setImageURI(image);

            Calendar c = Calendar.getInstance();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference ref = storage.getReference().child("dp/" +c.getTimeInMillis() + ".jpg");
            ref.putFile(img)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
//                                    tv.setText(uri.toString());
                                    Picasso.get().load(uri.toString()).into(dp);
                                    Log.d("imageurl" , uri.toString());





                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Dp")
                                            .setValue(uri.toString());

                                }
                            });
                        }
                    })



















                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity20.this, "Failed to upload ", Toast.LENGTH_LONG).show();
                        }
                    });


        }
    }
}