package com.hacktiv8.prepare_fp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktiv8.prepare_fp3.model.HargaTotal;

public class BusDetail extends AppCompatActivity {
    TextView nama_bus, kotaAsal, kotaSampai, jamAsal, jamSampai, durasi, nopol, jumlah_penumpang, harga1, harga2;
    Button btn_bookNow;
    ImageView btn_back;
    FirebaseFirestore db3 = FirebaseFirestore.getInstance();
    String terminalAsal, terminalSampe;
    long hargaLong;
    int hargaInt, hargaTotal;
    HargaTotal simpanHargaTotal = new HargaTotal();

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);

        //ambil data extras bus yg diklik dari schedule adapter
        Bundle data = getIntent().getExtras();
        String namaBus = data.getString("namaBus");

        nama_bus = findViewById(R.id.nama_bus);
        kotaAsal = findViewById(R.id.kota_asal);
        kotaSampai = findViewById(R.id.kota_sampai);
        jamAsal = findViewById(R.id.jam_asal);
        jamSampai = findViewById(R.id.jam_sampai);
        durasi = findViewById(R.id.durasi);
        nopol = findViewById(R.id.nopol);

        jumlah_penumpang = findViewById(R.id.jumlah_penumpang);
        harga1 = findViewById(R.id.harga_bus);
        harga2 = findViewById(R.id.harga_bus2);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), ScheduleList.class);
//            startActivity(intent);
        });

        btn_bookNow = findViewById(R.id.btn_bookNow);
        btn_bookNow.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChooseSeat.class);
            intent.putExtra("namaBus", namaBus);
            startActivity(intent);
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int jumlahPenumpang = sharedPreferences.getInt("totalPassengers", -1);
        jumlah_penumpang.setText(Integer.toString(jumlahPenumpang));

        System.out.println("FIREBASE STARTED");
        db3.collection("schedule")
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
                                nama_bus.setText(document.get("bus").toString());
                                kotaAsal.setText(document.getString("departure_city"));
                                kotaSampai.setText(document.getString("arrival_city"));
                                jamAsal.setText(document.getString("departure_time"));
                                jamSampai.setText(document.getString("arrival_time"));
                                durasi.setText(document.getString("duration"));
                                nopol.setText(document.getString("nopol"));
                                harga1.setText(document.get("harga").toString());
                                hargaLong = (long) document.get("harga");
                                hargaInt = (int) hargaLong;
                                hargaTotal = jumlahPenumpang*hargaInt;
                                harga2.setText(Integer.toString(hargaTotal));
                                simpanHargaTotal.setHargaTotal(hargaTotal);
                                terminalAsal = document.get("departure_cityTerminal").toString();
                                terminalSampe = document.get("arrival_cityTerminal").toString();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Data gagal diambil", Toast.LENGTH_SHORT).show();
                            System.out.println("Data gagal diambil");
                        }
                    }
                });
    }
}