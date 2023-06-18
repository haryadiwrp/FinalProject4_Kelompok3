package com.hacktiv8.prepare_fp3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.prepare_fp3.R;
import com.hacktiv8.prepare_fp3.model.Destination;

import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.MyViewHolder>{
    private Context context;
    private int[] type;
    private List<Destination> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public DestinationAdapter(Context context, List<Destination> list){
        this.context = context;
        this.list = list;
    }

    public DestinationAdapter(Context context, List<Destination> list, int[] type, DepartureListener departureListener, ArrivalListener arrivalListener){
        this.context = context;
        this.list = list;
        this.type = type;
        this.departureListener = departureListener;
        this.arrivalListener = arrivalListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_destination, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nama_kota.setText(list.get(position).getKota());
        holder.nama_terminal.setText(list.get(position).getTerminal());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama_kota, nama_terminal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_kota = itemView.findViewById(R.id.nama_kota);
            nama_terminal = itemView.findViewById(R.id.nama_terminal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Destination city = list.get(getAdapterPosition());
                    System.out.println("Select City: "+type+" - "+city.getKota()+" - "+city.getTerminal());
                    for (int i=0; i<type.length; i++){
                        if(type[i] == 1){
                            //1 = departure
                            departureListener.onSelectDeparture(city);
                        }else  if(type[i] == 2){
                            //2 =  arrival
                            arrivalListener.onSelectArrival(city);
                        }
                    }

                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface DepartureListener{
        void onSelectDeparture(Destination departureCity);
    }

    private DepartureListener departureListener;


    public interface ArrivalListener{
        void onSelectArrival(Destination arrivalCity);
    }

    private ArrivalListener arrivalListener;
}
