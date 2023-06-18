package com.hacktiv8.prepare_fp3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.prepare_fp3.OrderDetailActivity;
import com.hacktiv8.prepare_fp3.R;
import com.hacktiv8.prepare_fp3.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private Context context;
    private List<Order> orderList;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tglPemesanan.setText(orderList.get(position).getTglPesan());
        holder.waktuPemesanan.setText(orderList.get(position).getWaktuPesan());
        holder.noBooking.setText(orderList.get(position).getNoBooking());
        holder.bus.setText(orderList.get(position).getBus());
        holder.nopol.setText(orderList.get(position).getNopol());
        holder.jamAsal.setText(orderList.get(position).getWaktuBus());
        holder.terminalAsal.setText(orderList.get(position).getTerminal());
        holder.kotaAwal.setText(orderList.get(position).getKotaAwal());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tglPemesanan, waktuPemesanan, noBooking, bus, nopol, jamAsal, terminalAsal, kotaAwal;
        RelativeLayout frameOrder;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tglPemesanan = itemView.findViewById(R.id.tglPemesanan);
            waktuPemesanan = itemView.findViewById(R.id.waktuPemesanan);
            noBooking = itemView.findViewById(R.id.noBooking);
            bus = itemView.findViewById(R.id.orderNamaBus);
            nopol = itemView.findViewById(R.id.rowNopol);
            jamAsal = itemView.findViewById(R.id.jamAsalOrder);
            terminalAsal = itemView.findViewById(R.id.terminalAwal);
            kotaAwal = itemView.findViewById(R.id.kotaAwal);

//            frameOrder = itemView.findViewById(R.id.frameOrder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Order order = orderList.get(getAdapterPosition());

                    if (view.getId() == R.id.frameOrder) {

                        System.out.println("Ke detail order" + order);
                    }

                    String namaBus = order.getBus();
                    System.out.println("Bus Diorder Adapter"+namaBus);
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    intent.putExtra("namaBus", namaBus);
                    view.getContext().startActivity(intent);

                    if (dialog != null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
