package com.hacktiv8.prepare_fp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BankTransfer extends AppCompatActivity {

    TextView harga_total_bayar;
    CardView bni, cimb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transfer);

        Bundle data = getIntent().getExtras();
        int hargaTotalBayar = data.getInt("hargaTotal");
        System.out.println("harga total: "+hargaTotalBayar);

        harga_total_bayar = findViewById(R.id.harga_total_bayar1);
        harga_total_bayar.setText(Integer.toString(hargaTotalBayar));

        bni = findViewById(R.id.bniBtn);
        bni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BankBni.class);
                intent.putExtra("hargaTotal", hargaTotalBayar);
                System.out.println("harga total: "+hargaTotalBayar);
                startActivity(intent);
            }
        });

        bni = findViewById(R.id.cimbBtn);
        bni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CimbNiaga.class);
                intent.putExtra("hargaTotal", hargaTotalBayar);
                System.out.println("harga total: "+hargaTotalBayar);
                startActivity(intent);
            }
        });
    }
}