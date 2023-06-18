package com.hacktiv8.prepare_fp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Payment extends AppCompatActivity {

    TextView harga_total_bayar;
    CardView bank_transfer, credit_card, retail_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Bundle data = getIntent().getExtras();
        int hargaTotalBayar = data.getInt("hargaTotal");
        System.out.println("harga total: "+hargaTotalBayar);

        SharedPreferences sharedPreferences = getSharedPreferences("PrefsHargaTotal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("hargaTotalBayar", hargaTotalBayar);
        editor.apply();

        harga_total_bayar = findViewById(R.id.harga_total_bayar);
        harga_total_bayar.setText(Integer.toString(hargaTotalBayar));

        credit_card = findViewById(R.id.creditCardBtn);
        credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreditCard.class);
                intent.putExtra("hargaTotal", hargaTotalBayar);
                System.out.println("harga total: "+hargaTotalBayar);
                startActivity(intent);
            }
        });

        bank_transfer = findViewById(R.id.bankTransferBtn);
        bank_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BankTransfer.class);
                intent.putExtra("hargaTotal", hargaTotalBayar);
                System.out.println("harga total: "+hargaTotalBayar);
                startActivity(intent);
            }
        });

        retail_payment = findViewById(R.id.retailPaymentBtn);
        retail_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RetailPayment.class);
                intent.putExtra("hargaTotal", hargaTotalBayar);
                System.out.println("harga total: "+hargaTotalBayar);
                startActivity(intent);
            }
        });
    }
}