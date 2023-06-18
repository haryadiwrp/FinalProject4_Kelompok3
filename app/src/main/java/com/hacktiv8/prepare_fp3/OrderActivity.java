package com.hacktiv8.prepare_fp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hacktiv8.prepare_fp3.R;
import com.hacktiv8.prepare_fp3.adapter.OrderAdapter;
import com.hacktiv8.prepare_fp3.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    ImageView backBtn;
    RecyclerView rv_order;
    List<Order> orderList = new ArrayList<>();
    OrderAdapter orderAdapter;
    FirebaseFirestore dbOrder = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);




        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        rv_order = findViewById(R.id.rv_order);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        rv_order.setLayoutManager(layoutManager);
        rv_order.addItemDecoration(decoration);


        orderAdapter = new OrderAdapter(getApplicationContext(), orderList);
        rv_order.setAdapter(orderAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("BusdariChooseSeat", Context.MODE_PRIVATE);
        String namaBusCS = sharedPreferences.getString("namaBusCS", "");
        System.out.println("Nama Bus Diorder Activity: "+namaBusCS);

        dbOrder.collection("order")
                .whereEqualTo("bus",namaBusCS)//namaBus "Sempati Star"
                // .whereEqualTo("scheduleId", schedule)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        System.out.println("AMBIL DATA ORDER" + task.isSuccessful());
                        orderList.clear();
                        if (task.isSuccessful()) {
                            System.out.println("TOTAL DATA" + task.getResult().size());
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                System.out.println("DATA ORDER" + documentSnapshot.get("id_order").toString());
                                System.out.println("Order ACtivity Bus" + documentSnapshot.getString("bus"));
                                Order order = new Order(
                                        documentSnapshot.getString("id_order"),
                                        documentSnapshot.getString("tglPesan"),
                                        documentSnapshot.getString("waktuPesan"),
                                        documentSnapshot.getString("noBooking"),
                                        documentSnapshot.getString("bus"),
                                        documentSnapshot.getString("nopol"),
                                        documentSnapshot.getString("waktuDatang"),
                                        documentSnapshot.getString("departure_cityTerminal"),
                                        documentSnapshot.getString("departure_city"));
                                System.out.println("DATA NOPOL = " + documentSnapshot.getString("nopol"));
                                orderList.add(order);
                            }
                            System.out.println("DATA ORDER RESULT" + orderList.size());
                            orderAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(OrderActivity.this, "Data gagal diambil", Toast.LENGTH_SHORT).show();
                            System.out.println("Data gagal");
                        }
                    }
                });

    }
}