package com.hacktiv8.prepare_fp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktiv8.prepare_fp3.adapter.DestinationAdapter;
import com.hacktiv8.prepare_fp3.adapter.ScheduleAdapter;
import com.hacktiv8.prepare_fp3.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleList extends AppCompatActivity {
    ImageView btn_back;
    RelativeLayout departure, arrival;
    TextView txt_departure, txt_arrival, txt_tanggal;
    RecyclerView rv_schedule;
    List<Schedule> list = new ArrayList<>();
    ScheduleAdapter scheduleAdapter;
    FirebaseFirestore db2 = FirebaseFirestore.getInstance();
    private String arrivalId, departureId, arrivalCity, departureCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        //intent back ke home
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            //intent.putExtra("namaArrival", txtArrival.getText().toString());
            startActivity(intent);
        });

        //siapin extras buat departureid, arrivalid, departure (kota), arrival (kota)
        Intent intent = getIntent();
        Bundle dataExtras = intent.getExtras();

        //data yg disimpen di dataExtras (yg dibawa dari home) diuraiin jadi bbrp variabel:
        String departureId = dataExtras.getString("departureId");
        String arrivalId = dataExtras.getString("arrivalId");
        String departureCity = dataExtras.getString("departure"); //nama kota departure
        String arrivalCity = dataExtras.getString("arrival"); //nama kota arrival

        //buat set text di textview yg isinya kota departure (hasil extra dari home/inputan user)
        departure = findViewById(R.id.frame_departure);
        txt_departure = findViewById(R.id.kota_departure);
        String nama_departure;
        if (dataExtras!=null){
            System.out.println("departureId extra "+departureId);
            nama_departure = departureCity;
            txt_departure.setText(nama_departure);
        }
        //intent ke search destination kalo user mau ubah kota
        departure.setOnClickListener(v -> {
            Intent intentDeparture = new Intent(getApplicationContext(), SearchDestination.class);
            intentDeparture.putExtra("departure", txt_departure.getText().toString());
            startActivity(intentDeparture);
        });

        //buat set text di textview yg isinya kota arrival (hasil extra dari home/inputan user)
        arrival = findViewById(R.id.frame_arrival);
        txt_arrival = findViewById(R.id.kota_arrival);
        String nama_arrival;
        if (dataExtras!=null){
            System.out.println("arrivalId extra "+arrivalId);
            nama_arrival = arrivalCity;
            txt_arrival.setText(nama_arrival);
        }
        //intent ke search destination kalo user mau ubah kota
        arrival.setOnClickListener(v -> {
            Intent intentArrival = new Intent(getApplicationContext(), SearchDestination.class);
            intentArrival.putExtra("arrival", txt_arrival.getText().toString());
            startActivity(intentArrival);
        });

        txt_tanggal = findViewById(R.id.tanggal);

        rv_schedule = findViewById(R.id.rv_schedule);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        rv_schedule.setLayoutManager(layoutManager);
        rv_schedule.addItemDecoration(decoration);

        scheduleAdapter = new ScheduleAdapter(getApplicationContext(), list);
        rv_schedule.setAdapter(scheduleAdapter);

        System.out.println("Query Scheduler "+departureId+" --- "+arrivalId);
        System.out.println("Query Scheduler "+departureCity+" --- "+arrivalCity);

        db2.collection("schedule")
                .whereEqualTo("departure_id",departureId)//medan amplas xJNdXZGSADmhds5YhsbE departureId
                .whereEqualTo("arrival_id",arrivalId)//pekanbaru bandaraya h6uKAVEXPFDussi26qTj arrivalId
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        System.out.println("AMBIL SCHEDULE "+task.isSuccessful());
                        list.clear();
                        if (task.isSuccessful()) {
                            System.out.println("Total Data "+task.getResult().size());
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                System.out.println("Data Schedule: "+document.get("id").toString());

                                Schedule schedule = new Schedule(
                                        document.getString("id"),
                                        document.getString("bus"),
                                        document.getString("nopol"),
                                        document.getString("departure_time"),
                                        document.getString("arrival_time"),
                                        document.getString("departure_city"),
                                        document.getString("arrival_city"),
                                        document.getString("departure_cityTerminal"),
                                        document.getString("arrival_cityTerminal"),
                                        document.get("harga").toString(),
                                        document.get("rating").toString(),
                                        document.get("jumlah_review").toString());
                                list.add(schedule);
                            }
                            System.out.println("Data Schedule Result: "+list.size());
                            scheduleAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), "Data gagal diambil", Toast.LENGTH_SHORT).show();
                            System.out.println("Data gagal diambil");
                        }
                    }
                });
    }
}