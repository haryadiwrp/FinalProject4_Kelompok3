package com.hacktiv8.prepare_fp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RetailPayment extends AppCompatActivity {
    TextView harga_total_bayar5;
    Button btn_verify3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_payment);

        Bundle data = getIntent().getExtras();
        int hargaTotalBayar = data.getInt("hargaTotal");
        System.out.println("harga total: "+hargaTotalBayar);

        harga_total_bayar5 = findViewById(R.id.harga_total_bayar5);
        harga_total_bayar5.setText(Integer.toString(hargaTotalBayar));

        btn_verify3 = findViewById(R.id.btn_verifyAlfa);
        btn_verify3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
            }
        });
    }
}