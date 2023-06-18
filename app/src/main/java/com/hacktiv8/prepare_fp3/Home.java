package com.hacktiv8.prepare_fp3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.slider.Slider;

public class Home extends AppCompatActivity {
    LinearLayout frameDepartures, frameArrival, framePassengers, frameDate;
    TextView txtDeparture, txtArrival, txtPassengers, txtDate;
    Button btnSearchBus;
    private String arrivalId, departureId, arrival, departure;
    private String totalPassengers;
    private int totalPassengersInt;
    private static  final int REQUEST_CODE_DEPARTURE = 12;
    private static  final int REQUEST_CODE_ARRIVAL = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameDepartures = findViewById(R.id.select_departures);
        txtDeparture = findViewById(R.id.tv_departure);
        frameDepartures.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SearchDestination.class);
            Bundle extrasDeparture = new Bundle();
            extrasDeparture.putString("namaDeparture", txtDeparture.getText().toString());
            extrasDeparture.putInt("typeCity", 1);
            intent.putExtras(extrasDeparture);
            startActivityForResult(intent, REQUEST_CODE_DEPARTURE);
        });

        frameArrival = findViewById(R.id.select_arrival);
        txtArrival = findViewById(R.id.tv_arrival);
        frameArrival.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SearchDestination.class);
            Bundle extrasArrival = new Bundle();
            extrasArrival.putString("namaArrival", txtArrival.getText().toString());
            extrasArrival.putInt("typeCity", 2);
            intent.putExtras(extrasArrival);
            startActivityForResult(intent, REQUEST_CODE_ARRIVAL);
        });

        framePassengers = findViewById(R.id.select_passengers);
        txtPassengers = findViewById(R.id.tv_passengers);
        framePassengers.setOnClickListener(v -> {
            showPassengersDialog();
        });

        frameDate = findViewById(R.id.select_date);
        txtDate = findViewById(R.id.tv_date);
        frameDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        btnSearchBus = findViewById(R.id.btn_searchbus);
        btnSearchBus.setOnClickListener(v -> {


            Log.i("departure: ", departure);
            Log.i("arrival: ", arrival);
            Log.i("passengers: ", totalPassengers);
            Log.i("date: ", "");

            Intent intent = new Intent(getApplicationContext(), ScheduleList.class);
            Bundle dataExtras = new Bundle();
            dataExtras.putString("departureId", departureId);
            dataExtras.putString("arrivalId", arrivalId);
            dataExtras.putString("departure", departure);
            dataExtras.putString("arrival", arrival);
//            dataExtras.putInt("passengers", totalPassengersInt);
            intent.putExtras(dataExtras);
            startActivity(intent);
            finish();
        });
    }

    private void showDatePicker() {
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                txtDate.setText(""+materialDatePicker.getHeaderText());
            }
        });

        materialDatePicker.show(getSupportFragmentManager(), "TAG");
    }

    private void showPassengersDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_passengers);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Button btnSelect = dialog.findViewById(R.id.btn_select);
        TextView jumlahPnp = dialog.findViewById(R.id.value);
        Slider slider = dialog.findViewById(R.id.slider);
        final int[] valueInt = new int[1];

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                valueInt[0] = (int) value;
                jumlahPnp.setText(Integer.toString(valueInt[0]));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPassengers.setText(Integer.toString(valueInt[0])+" ppl");
                totalPassengers = Integer.toString(valueInt[0]);
                totalPassengersInt = valueInt[0];

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("totalPassengers", totalPassengersInt);
                editor.apply();

                dialog.hide();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Activity Result, resultCode: ", String.valueOf(resultCode));
        Log.i("Activity Result, requestCode: ", String.valueOf(requestCode));

        if(resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_CODE_DEPARTURE){
                String txtId1 = data.getStringExtra("id1");
                String txtKota1 = data.getStringExtra("kota1");
                String txtTerminal1 = data.getStringExtra("terminal1");

                departureId = txtId1;
                departure = txtKota1;
                txtDeparture.setText(txtKota1 + ", " + txtTerminal1);
            }else if (requestCode == REQUEST_CODE_ARRIVAL){
                String txtId2 = data.getStringExtra("id2");
                String txtKota2 = data.getStringExtra("kota2");
                String txtTerminal2 = data.getStringExtra("terminal2");

                arrivalId = txtId2;
                arrival = txtKota2;
                txtArrival.setText(txtKota2 + ", " + txtTerminal2);
            }
        }
    }
}