package com.example.classup.classup.classup.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText etEmail = binding.etEmail;
        EditText etName = binding.etName;
        EditText etPw = binding.etPw;
        EditText etId = binding.etId;
        Button bt = binding.bt;

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String name = etName.getText().toString();
                String password = etPw.getText().toString();
                String uid = etId.getText().toString();


                if (email.isEmpty() || name.isEmpty() || password.isEmpty() || uid.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                    return; // Exit method if any field is empty
                }

                Users users = new Users(email,name,password,uid);




                   databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                   databaseReference.child(uid).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void unused) {
                           Toast.makeText(MainActivity.this,"Sign Up Successfully",Toast.LENGTH_SHORT).show();
                           etEmail.setText("");
                           etName.setText("");
                           etPw.setText("");
                           etId.setText("");
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                       }
                   });



            }
        });


    }
}