package com.hacktiv8.prepare_fp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class AfterRegis extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button signOut;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_regis);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        signOut =  findViewById(R.id.btnHome);


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //FirebaseAuth.getInstance().signOut();
                //startActivity(new Intent(getApplicationContext(), Home.class));
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();
            }
        });




    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Logout berhasil
                        // Tambahkan kode di sini untuk tindakan setelah logout
                        Toast.makeText(AfterRegis.this, "Logged out", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}