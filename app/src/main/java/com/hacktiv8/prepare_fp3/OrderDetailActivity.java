package com.hacktiv8.prepare_fp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktiv8.prepare_fp3.R;

public class OrderDetailActivity extends AppCompatActivity {

    TextView tglOrder, waktuOrder, noBookingDetail, namaPenumpang, ratingPerjalanan, phoneNumber, jumlahSeat, statuspembayaranDetail, waktuPerjalanan;
    TextView bus, nopol, departure_time, kotaAwal, terminalAwal, tglBerangkat, arrival_time, kotaTujuan, terminalTujuan, waktuTiba, noSeat, hargaTotal;
    ImageView backBtn;
    FirebaseFirestore dbDetailOrder = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Bundle data = getIntent().getExtras();
        String namaBus = data.getString("namaBus");


//        SharedPreferences sharedPreferences = getSharedPreferences("PrefsNamaBus", Context.MODE_PRIVATE);
//        String namaBus = sharedPreferences.getString("namaBus", "");
       System.out.println("namaBus: "+namaBus);

        backBtn = findViewById(R.id.backBtn);

        tglOrder = findViewById(R.id.tglOrder);
        waktuOrder = findViewById(R.id.waktuOrder);
        noBookingDetail = findViewById(R.id.noBookingDetail);
        namaPenumpang = findViewById(R.id.namaPenumpang);
        ratingPerjalanan = findViewById(R.id.ratingPerjalanan);
        phoneNumber = findViewById(R.id.phoneNumber);
        jumlahSeat = findViewById(R.id.jumlahSeat);
        statuspembayaranDetail = findViewById(R.id.statuspembayaranDetail);
        waktuPerjalanan = findViewById(R.id.waktuPerjalanan);

        bus = findViewById(R.id.detailBus);
        nopol = findViewById(R.id.detailNopol);
        departure_time = findViewById(R.id.departure_time);
        kotaAwal = findViewById(R.id.detailKotaAwal);
        terminalAwal = findViewById(R.id.detailTerminalAwal);
        tglBerangkat = findViewById(R.id.tglBerangkat);
        arrival_time = findViewById(R.id.arrival_time);
        kotaTujuan = findViewById(R.id.kotaTujuan);
        terminalTujuan = findViewById(R.id.terminalTujuan);
        waktuTiba = findViewById(R.id.waktuTiba);
        noSeat = findViewById(R.id.noSeat);
        hargaTotal = findViewById(R.id.hargaTotal);

        SharedPreferences sharedPreferences = getSharedPreferences("PrefsHargaTotal", Context.MODE_PRIVATE);
        int hargaTotalBayar = sharedPreferences.getInt("hargaTotalBayar", -1);
        System.out.println("hargaTotalBayar "+hargaTotalBayar);
        hargaTotal.setText(Integer.toString(hargaTotalBayar));

        System.out.println("FIREBASE STARTED");


        dbDetailOrder.collection("schedule")
                .whereEqualTo("bus",namaBus)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        System.out.println("AMBIL DATA BIS "+task.isSuccessful());
                        if (task.isSuccessful()){
                            System.out.println("Total Data "+task.getResult().size());
                            for (QueryDocumentSnapshot document : task.getResult()){
                                System.out.println("Contoh Data Bis: "+document.get("bus").toString());
                                bus.setText(document.get("bus").toString());
                                kotaAwal.setText(document.getString("departure_city"));
                                kotaTujuan.setText(document.getString("arrival_city"));
                                departure_time.setText(document.getString("departure_time"));
                                arrival_time.setText(document.getString("arrival_time"));
                                waktuPerjalanan.setText(document.getString("duration"));
                                nopol.setText(document.getString("nopol"));
                                terminalAwal.setText(document.getString("departure_cityTerminal"));
                                terminalTujuan.setText(document.getString("arrival_cityTerminal"));
//                                harga1.setText(document.get("harga").toString());
//                                hargaLong = (long) document.get("harga");
//                                hargaInt = (int) hargaLong;
//                                hargaTotal = jumlahPenumpang*hargaInt;
//                                harga2.setText(Integer.toString(hargaTotal));
//                                simpanHargaTotal.setHargaTotal(hargaTotal);
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Data gagal diambil", Toast.LENGTH_SHORT).show();
                            System.out.println("Data gagal diambil");
                        }
                    }
                });


        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}