package com.hacktiv8.prepare_fp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class SetUpPhoneNumber extends AppCompatActivity {

    EditText txtphoneNumber;
    Button verifPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_phone_number);

        txtphoneNumber = findViewById(R.id.txtPhoneNumber);
        verifPhone = findViewById(R.id.buttonPhoneVerif);


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        verifPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser != null) {
                    String phone = txtphoneNumber.getText().toString();

//                    SharedPreferences sharedPreferences = getSharedPreferences("PrefsNoHp", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("phone", phone);
//                    editor.apply();

                    Map<String, String> userData = new HashMap<>();
                    userData.put("PhoneNumber", phone);

                    firebaseFirestore.collection("users").document(currentUser.getUid())
                            .set(userData, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SetUpPhoneNumber.this, "Your phone number has been saved", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), AfterRegis.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SetUpPhoneNumber.this, "Failed to save phone number", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });



    }
}

