package com.hacktiv8.prepare_fp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CimbNiaga extends AppCompatActivity {
    TextView harga_total_bayar4a, harga_total_bayar4b;
    Button btn_verify3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cimb_niaga);

        Bundle data = getIntent().getExtras();
        int hargaTotalBayar = data.getInt("hargaTotal");
        System.out.println("harga total: "+hargaTotalBayar);

        harga_total_bayar4a = findViewById(R.id.harga_total_bayar4);
        harga_total_bayar4a.setText(Integer.toString(hargaTotalBayar));
        harga_total_bayar4b = findViewById(R.id.harga_bayar_total2);
        harga_total_bayar4b.setText(Integer.toString(hargaTotalBayar));

        btn_verify3 = findViewById(R.id.btn_verifyCimb);
        btn_verify3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
            }
        });
    }
}