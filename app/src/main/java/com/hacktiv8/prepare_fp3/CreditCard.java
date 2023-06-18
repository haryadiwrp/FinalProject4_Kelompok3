package com.hacktiv8.prepare_fp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreditCard extends AppCompatActivity {
    TextView harga_total_bayar2;
    Button btn_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        Bundle data = getIntent().getExtras();
        int hargaTotalBayar = data.getInt("hargaTotal");
        System.out.println("harga total: "+hargaTotalBayar);

        harga_total_bayar2 = findViewById(R.id.harga_total_bayar2);
        harga_total_bayar2.setText(Integer.toString(hargaTotalBayar));

        btn_verify = findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
               startActivity(intent);
            }
        });
    }
}