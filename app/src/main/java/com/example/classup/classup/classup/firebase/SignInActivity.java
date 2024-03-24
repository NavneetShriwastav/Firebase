package com.example.classup.classup.classup.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classup.classup.classup.firebase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DatabaseReference databaseReference;
    private EditText uid;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in2);

        uid = findViewById(R.id.etId);
        btn = findViewById(R.id.bt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = uid.getText().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(userId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Toast.makeText(SignInActivity.this,"User Exist",Toast.LENGTH_SHORT).show();
                            String s = dataSnapshot.child("name").getValue().toString();
                            Intent intent = new Intent(SignInActivity.this,WelcomeActivity.class);
                            intent.putExtra("key",s);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SignInActivity.this,"User doesn't Exist",Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this,"couldn't fetch the data..",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}