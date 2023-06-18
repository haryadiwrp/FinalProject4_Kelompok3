package com.hacktiv8.prepare_fp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktiv8.prepare_fp3.model.HargaTotal;

public class CompleteBooking extends AppCompatActivity {

    FirebaseFirestore db4 = FirebaseFirestore.getInstance();
    Button continueBtn;
    TextView nama_user, no_hp, nama_bis;
    TextView no_pol, jam_asal2, kota_asal2, terminal_asal, durasi_waktu, jam_sampai2, kota_sampai2, terminal_sampe2;
    TextView jumlah_pnp, harga_bis, harga_total;
    int hargaTotal;
    int kodeUnik = 333;
    long hargaLong;
    int hargaInt;
    HargaTotal simpanHargaTotal = new HargaTotal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_booking);

        //dapetin nama user dari class login
//        SharedPreferences sharedPreferences1 = getSharedPreferences("PrefsNamaLogin", Context.MODE_PRIVATE);
//        String displayName = sharedPreferences1.getString("displayName", "");
//        nama_user = findViewById(R.id.nama_user);
//        nama_user.setText(displayName);
//
//        //dapetin no hp dari class set up phone number
//        SharedPreferences sharedPreferences2 = getSharedPreferences("PrefsNoHp", Context.MODE_PRIVATE);
//        String phone = sharedPreferences2.getString("phone", "");
//        no_hp = findViewById(R.id.no_hp);
//        no_hp.setText(phone);

        //ambil extras nama bus dan jumlah penumpang dari choose seat
        Bundle dataExtras = getIntent().getExtras();
        String namaBus = dataExtras.getString("namaBus");
        nama_bis = findViewById(R.id.nama_bis);
        nama_bis.setText(namaBus);
        int jumlahPenumpang = dataExtras.getInt("totalPassengers");
        jumlah_pnp = findViewById(R.id.jumlah_pnp);
        jumlah_pnp.setText(Integer.toString(jumlahPenumpang));

        no_pol = findViewById(R.id.no_pol);
        jam_asal2 = findViewById(R.id.jam_asal2);
        kota_asal2 = findViewById(R.id.kota_asal2);
        terminal_asal = findViewById(R.id.terminal_asal);
        durasi_waktu = findViewById(R.id.durasi_waktu);
        jam_sampai2 = findViewById(R.id.jam_sampai2);
        kota_sampai2 = findViewById(R.id.kota_sampai2);
        terminal_sampe2 = findViewById(R.id.terminal_sampe2);
        harga_bis = findViewById(R.id.harga_bis);

        //dapetin nopol, jam&kota&terminal asal, durasi, jam&kota&terminal sampe, harga
        System.out.println("FIREBASE STARTED");
        db4.collection("schedule")
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
                                no_pol.setText(document.get("nopol").toString());
                                jam_asal2.setText(document.getString("departure_time"));
                                kota_asal2.setText(document.getString("departure_city"));
                                terminal_asal.setText(document.getString("departure_cityTerminal"));
                                durasi_waktu.setText(document.getString("duration"));
                                jam_sampai2.setText(document.get("arrival_time").toString());
                                kota_sampai2.setText(document.get("arrival_city").toString());
                                terminal_sampe2.setText(document.get("arrival_cityTerminal").toString());
                                harga_bis.setText(document.get("harga").toString());
                                hargaLong = (long) document.get("harga");
                                hargaInt = (int) hargaLong;
                                hargaTotal = jumlahPenumpang*hargaInt+kodeUnik;
                                harga_total = findViewById(R.id.harga_total);
                                harga_total.setText(Integer.toString(hargaTotal));
                                simpanHargaTotal.setHargaTotal(hargaTotal);
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Data gagal diambil", Toast.LENGTH_SHORT).show();
                            System.out.println("Data gagal diambil");
                        }
                    }
                });

        continueBtn = findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;
                intent = new Intent(CompleteBooking.this, Payment.class);
                intent.putExtra("hargaTotal", simpanHargaTotal.getHargaTotal());
                System.out.println("harga total: "+simpanHargaTotal.getHargaTotal());
                startActivity(intent);
            }
        });
    }
}