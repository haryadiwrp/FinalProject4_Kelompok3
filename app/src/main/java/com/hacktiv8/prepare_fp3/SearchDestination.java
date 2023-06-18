package com.hacktiv8.prepare_fp3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktiv8.prepare_fp3.adapter.DestinationAdapter;
import com.hacktiv8.prepare_fp3.model.Destination;

import java.util.ArrayList;
import java.util.List;

public class SearchDestination extends AppCompatActivity implements DestinationAdapter.DepartureListener, DestinationAdapter.ArrivalListener{
    TextView txtPencarianKota, btnCancel;
    RecyclerView rv_destinasi;
    List<Destination> list = new ArrayList<>();
    DestinationAdapter destinationAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_destination);

        txtPencarianKota = findViewById(R.id.tv_pencarian_kota);

        //intent button cancel
        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            finish();
        });

        //siapin extras buat departure (kota departure, typecity) dan arrival (kota arrival, typecity)
        Intent intent = getIntent();
        Bundle extrasDeparture = intent.getExtras();
        Bundle extrasArrival = intent.getExtras();

        //kalo user udh input kota departure di home, cara biar kotanya tampil:
        String dataDeparture = extrasDeparture.getString("namaDeparture");
        if (dataDeparture!=null){
            txtPencarianKota.setText(dataDeparture);
        }

        //kalo user udh input kota arrival di home, cara biar kotanya tampil:
        String dataArrival = extrasArrival.getString("namaArrival");
        if (dataArrival!=null){
            txtPencarianKota.setText(dataArrival);
        }

        //set layout buat recycler view
        rv_destinasi = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv_destinasi.setLayoutManager(layoutManager);

        //siapin extras buat passing typeCity ke adapter
        int typeCityDeparture = extrasDeparture.getInt("typeCity");
        int typeCityArrival = extrasArrival.getInt("typeCity");
        int[] typeCity = new int[] {typeCityDeparture, typeCityArrival};

        destinationAdapter = new DestinationAdapter(this, list, typeCity, this, this);
        rv_destinasi.setAdapter(destinationAdapter);

        db.collection("destination")
                .orderBy("kota", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Destination destination = new Destination(document.getString("id"),
                                        document.getString("kota"),
                                        document.getString("terminal"),
                                        document.getString("terminal_short"));
                                list.add(destination);
                            }
                            destinationAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), "Data gagal diambil", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onSelectDeparture(Destination departureCity) {
        System.out.println("id1: "+departureCity.getId());
        System.out.println("kota1: "+departureCity.getKota());

        Intent resultData = new Intent();
        resultData.putExtra("id1", departureCity.getId());
        resultData.putExtra("kota1", departureCity.getKota());
        resultData.putExtra("terminal1", departureCity.getTerminal_short());
        setResult(Activity.RESULT_OK, resultData);
        finish();
    }

    @Override
    public void onSelectArrival(Destination arrivalCity) {
        System.out.println("id2: "+arrivalCity.getId());
        System.out.println("kota2: "+arrivalCity.getKota());

        Intent resultData = new Intent();
        resultData.putExtra("id2", arrivalCity.getId());
        resultData.putExtra("kota2", arrivalCity.getKota());
        resultData.putExtra("terminal2", arrivalCity.getTerminal_short());
        setResult(Activity.RESULT_OK, resultData);
        finish();
    }
}