package com.hacktiv8.prepare_fp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChooseSeat extends AppCompatActivity {
    Button btn_book_now;
    ImageView a1,a2,a3,a4,a5,a6;
    ImageView b1,b2,b3,b4,b5,b6;
    ImageView c1,c2,c3,c4,c5,c6;
    ImageView d1,d2,d3,d4,d5,d6;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seat);

        //ambil inputan jumlah penumpang dari home
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int jumlahPenumpang = sharedPreferences.getInt("totalPassengers", -1);
        System.out.println("jumlah penumpangnya: "+jumlahPenumpang);

        //ambil extras nama bus yang diklik di bus detail
        Bundle data = getIntent().getExtras();
        String namaBus = data.getString("namaBus");

        SharedPreferences sharedPreferences2 = getSharedPreferences("BusdariChooseSeat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        editor.putString("namaBusCS", namaBus);
        editor.apply();

        a1 = findViewById(R.id.seat_a1);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!a1.isFocusable()){
                        counter++;
                        a1.setImageResource(R.drawable.img_seat_selected);
                        a1.setFocusable(true);
                    } else{
                        counter--;
                        System.out.println("counter a1 berkurang?: "+counter);
                        a1.setImageResource(R.drawable.img_seat);
                        a1.setFocusable(false);
                    }
                }
            }
        });

        a2 = findViewById(R.id.seat_a2);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!a2.isFocusable()){
                        counter++;
                        a2.setImageResource(R.drawable.img_seat_selected);
                        a2.setFocusable(true);
                    } else{
                        counter--;
                        a2.setImageResource(R.drawable.img_seat);
                        a2.setFocusable(false);
                    }
                }
            }
        });

        a3 = findViewById(R.id.seat_a3);
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!a3.isFocusable()){
                        counter++;
                        a3.setImageResource(R.drawable.img_seat_selected);
                        a3.setFocusable(true);
                    } else{
                        counter--;
                        a3.setImageResource(R.drawable.img_seat);
                        a3.setFocusable(false);
                    }
                }
            }
        });

        a4 = findViewById(R.id.seat_a4);
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!a4.isFocusable()){
                        counter++;
                        a4.setImageResource(R.drawable.img_seat_selected);
                        a4.setFocusable(true);
                    } else{
                        counter--;
                        a4.setImageResource(R.drawable.img_seat);
                        a4.setFocusable(false);
                    }
                }
            }
        });

        a5 = findViewById(R.id.seat_a5);
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!a5.isFocusable()){
                        counter++;
                        a5.setImageResource(R.drawable.img_seat_selected);
                        a5.setFocusable(true);
                    } else{
                        counter--;
                        a5.setImageResource(R.drawable.img_seat);
                        a5.setFocusable(false);
                    }
                }
            }
        });

        a6 = findViewById(R.id.seat_a6);
        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!a6.isFocusable()){
                        counter++;
                        a6.setImageResource(R.drawable.img_seat_selected);
                        a6.setFocusable(true);
                    } else{
                        counter--;
                        a6.setImageResource(R.drawable.img_seat);
                        a6.setFocusable(false);
                    }
                }
            }
        });

        b1 = findViewById(R.id.seat_b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!b1.isFocusable()){
                        counter++;
                        b1.setImageResource(R.drawable.img_seat_selected);
                        b1.setFocusable(true);
                    } else{
                        counter--;
                        b1.setImageResource(R.drawable.img_seat);
                        b1.setFocusable(false);
                    }
                }
            }
        });

        b2 = findViewById(R.id.seat_b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!b2.isFocusable()){
                        counter++;
                        b2.setImageResource(R.drawable.img_seat_selected);
                        b2.setFocusable(true);
                    } else{
                        counter--;
                        b2.setImageResource(R.drawable.img_seat);
                        b2.setFocusable(false);
                    }
                }
            }
        });

        b3 = findViewById(R.id.seat_b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!b3.isFocusable()){
                        counter++;
                        b3.setImageResource(R.drawable.img_seat_selected);
                        b3.setFocusable(true);
                    } else{
                        counter--;
                        b3.setImageResource(R.drawable.img_seat);
                        b3.setFocusable(false);
                    }
                }
            }
        });

        b4 = findViewById(R.id.seat_b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!b4.isFocusable()){
                        counter++;
                        b4.setImageResource(R.drawable.img_seat_selected);
                        b4.setFocusable(true);
                    } else{
                        counter--;
                        b4.setImageResource(R.drawable.img_seat);
                        b4.setFocusable(false);
                    }
                }
            }
        });

        b5 = findViewById(R.id.seat_b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!b5.isFocusable()){
                        counter++;
                        b5.setImageResource(R.drawable.img_seat_selected);
                        b5.setFocusable(true);
                    } else{
                        counter--;
                        b5.setImageResource(R.drawable.img_seat);
                        b5.setFocusable(false);
                    }
                }
            }
        });

        b6 = findViewById(R.id.seat_b6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!b6.isFocusable()){
                        counter++;
                        b6.setImageResource(R.drawable.img_seat_selected);
                        b6.setFocusable(true);
                    } else{
                        counter--;
                        b6.setImageResource(R.drawable.img_seat);
                        b6.setFocusable(false);
                    }
                }
            }
        });

        c1 = findViewById(R.id.seat_c1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!c1.isFocusable()){
                        counter++;
                        c1.setImageResource(R.drawable.img_seat_selected);
                        c1.setFocusable(true);
                    } else{
                        counter--;
                        c1.setImageResource(R.drawable.img_seat);
                        c1.setFocusable(false);
                    }
                }
            }
        });

        c2 = findViewById(R.id.seat_c2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!c2.isFocusable()){
                        counter++;
                        c2.setImageResource(R.drawable.img_seat_selected);
                        c2.setFocusable(true);
                    } else{
                        counter--;
                        c2.setImageResource(R.drawable.img_seat);
                        c2.setFocusable(false);
                    }
                }
            }
        });

        c3 = findViewById(R.id.seat_c3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!c3.isFocusable()){
                        counter++;
                        c3.setImageResource(R.drawable.img_seat_selected);
                        c3.setFocusable(true);
                    } else{
                        counter--;
                        c3.setImageResource(R.drawable.img_seat);
                        c3.setFocusable(false);
                    }
                }
            }
        });

        c4 = findViewById(R.id.seat_c4);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!c4.isFocusable()){
                        counter++;
                        c4.setImageResource(R.drawable.img_seat_selected);
                        c4.setFocusable(true);
                    } else{
                        counter--;
                        c4.setImageResource(R.drawable.img_seat);
                        c4.setFocusable(false);
                    }
                }
            }
        });

        c5 = findViewById(R.id.seat_c5);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!c5.isFocusable()){
                        counter++;
                        c5.setImageResource(R.drawable.img_seat_selected);
                        c5.setFocusable(true);
                    } else{
                        counter--;
                        c5.setImageResource(R.drawable.img_seat);
                        c5.setFocusable(false);
                    }
                }
            }
        });

        c6 = findViewById(R.id.seat_c6);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!c6.isFocusable()){
                        counter++;
                        c6.setImageResource(R.drawable.img_seat_selected);
                        c6.setFocusable(true);
                    } else{
                        counter--;
                        c6.setImageResource(R.drawable.img_seat);
                        c6.setFocusable(false);
                    }
                }
            }
        });

        d1 = findViewById(R.id.seat_d1);
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!d1.isFocusable()){
                        counter++;
                        d1.setImageResource(R.drawable.img_seat_selected);
                        d1.setFocusable(true);
                    } else{
                        counter--;
                        d1.setImageResource(R.drawable.img_seat);
                        d1.setFocusable(false);
                    }
                }
            }
        });

        d2 = findViewById(R.id.seat_d2);
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!d2.isFocusable()){
                        counter++;
                        d2.setImageResource(R.drawable.img_seat_selected);
                        d2.setFocusable(true);
                    } else{
                        counter--;
                        d2.setImageResource(R.drawable.img_seat);
                        d2.setFocusable(false);
                    }
                }
            }
        });

        d3 = findViewById(R.id.seat_d3);
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!d3.isFocusable()){
                        counter++;
                        d3.setImageResource(R.drawable.img_seat_selected);
                        d3.setFocusable(true);
                    } else{
                        counter--;
                        d3.setImageResource(R.drawable.img_seat);
                        d3.setFocusable(false);
                    }
                }
            }
        });

        d4 = findViewById(R.id.seat_d4);
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!d4.isFocusable()){
                        counter++;
                        d4.setImageResource(R.drawable.img_seat_selected);
                        d4.setFocusable(true);
                    } else{
                        counter--;
                        d4.setImageResource(R.drawable.img_seat);
                        d4.setFocusable(false);
                    }
                }
            }
        });

        d5 = findViewById(R.id.seat_d5);
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!d5.isFocusable()){
                        counter++;
                        d5.setImageResource(R.drawable.img_seat_selected);
                        d5.setFocusable(true);
                    } else{
                        counter--;
                        d5.setImageResource(R.drawable.img_seat);
                        d5.setFocusable(false);
                    }
                }
            }
        });

        d6 = findViewById(R.id.seat_d6);
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < jumlahPenumpang){
                    if (!d6.isFocusable()){
                        counter++;
                        d6.setImageResource(R.drawable.img_seat_selected);
                        d6.setFocusable(true);
                    } else{
                        counter--;
                        d6.setImageResource(R.drawable.img_seat);
                        d6.setFocusable(false);
                    }
                }
            }
        });

        btn_book_now = findViewById(R.id.btn_book_now2);
        btn_book_now.setOnClickListener(v -> {
            final Intent intent;
            intent = new Intent(ChooseSeat.this, CompleteBooking.class);
            Bundle dataExtras = new Bundle();
            dataExtras.putString("namaBus", namaBus);
            dataExtras.putInt("totalPassengers", jumlahPenumpang);
            intent.putExtras(dataExtras);
            startActivity(intent);
        });
    }
}