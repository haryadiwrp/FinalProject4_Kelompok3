package com.hacktiv8.prepare_fp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BankBni extends AppCompatActivity {
    TextView harga_total_bayar3a, harga_total_bayar3b;
    Button btn_verify2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_bni);

        Bundle data = getIntent().getExtras();
        int hargaTotalBayar = data.getInt("hargaTotal");
        System.out.println("harga total: "+hargaTotalBayar);

        harga_total_bayar3a = findViewById(R.id.harga_total_bayar3);
        harga_total_bayar3a.setText(Integer.toString(hargaTotalBayar));
        harga_total_bayar3b = findViewById(R.id.harga_bayar_total);
        harga_total_bayar3b.setText(Integer.toString(hargaTotalBayar));

        btn_verify2 = findViewById(R.id.btn_verifyBni);
        btn_verify2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
            }
        });
    }
}