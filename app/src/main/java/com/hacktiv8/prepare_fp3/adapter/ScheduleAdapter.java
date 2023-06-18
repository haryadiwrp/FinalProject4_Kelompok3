package com.hacktiv8.prepare_fp3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.prepare_fp3.BusDetail;
import com.hacktiv8.prepare_fp3.ChooseSeat;
import com.hacktiv8.prepare_fp3.R;
import com.hacktiv8.prepare_fp3.model.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder>{
    private Context context;
    private List<Schedule> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public ScheduleAdapter(Context context, List<Schedule> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_schedule, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nama_bus.setText(list.get(position).getBus());
        holder.nopol.setText(list.get(position).getNopol());
        holder.jam_berangkat.setText(list.get(position).getDeparture_time());
        holder.jam_sampe.setText(list.get(position).getArrival_time());
        holder.kota_berangkat.setText(list.get(position).getDeparture_city());
        holder.kota_sampe.setText(list.get(position).getArrival_city());
        holder.terminal_berangkat.setText(list.get(position).getDeparture_cityTerminal());
        holder.terminal_sampe.setText(list.get(position).getArrival_cityTerminal());
        holder.harga.setText(list.get(position).getHarga());
        holder.rating_bus.setText(list.get(position).getRating());
        holder.review.setText(list.get(position).getJumlah_review());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama_bus, nopol, jam_berangkat, jam_sampe;
        TextView kota_berangkat, kota_sampe, terminal_berangkat, terminal_sampe, harga;
        TextView rating_bus, review;
        Button btn_book_now;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_bus = itemView.findViewById(R.id.nama_bus);
            nopol = itemView.findViewById(R.id.nopol);
            jam_berangkat = itemView.findViewById(R.id.jam_berangkat);
            jam_sampe = itemView.findViewById(R.id.jam_sampe);
            kota_berangkat = itemView.findViewById(R.id.kota_berangkat);
            kota_sampe = itemView.findViewById(R.id.kota_sampe);
            terminal_berangkat = itemView.findViewById(R.id.terminal_berangkat);
            terminal_sampe = itemView.findViewById(R.id.terminal_sampe);
            harga = itemView.findViewById(R.id.harga);
            rating_bus = itemView.findViewById(R.id.rating_bus);
            review = itemView.findViewById(R.id.jml_review);

            btn_book_now = itemView.findViewById(R.id.btn_book_now);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Schedule jadwal_bis = list.get(getAdapterPosition());

                    if (v.getId()==R.id.btn_book_now){
//                        ScheduleAdapter.this.getItemId(getLayoutPosition());
//                        long posi = ScheduleAdapter.this.getItemId(getLayoutPosition());
                        System.out.println("Book now bus apa? "+jadwal_bis.getBus()); //gabisa
                    }

                    String namaBus = jadwal_bis.getBus();
                    System.out.println("yang dipencet bus apa? "+namaBus);
                    Intent intent = new Intent(context, BusDetail.class);
                    intent.putExtra("namaBus", namaBus);
                    v.getContext().startActivity(intent);

                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}