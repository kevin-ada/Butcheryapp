package com.example.stocktake_dms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {


    Context context;

    public myAdapter(Context context, ArrayList<Stock> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<Stock> list;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.stockentry, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Stock stock = list.get(position);
        holder.date.setText(stock.getDate());
        holder.totalStock.setText(String.valueOf(stock.getTotalStock()));
        holder.totalWaste.setText(String.valueOf(stock.getTotalWaste()));
        holder.actualStock.setText(String.valueOf(stock.getActualStock()));
        holder.price.setText(String.valueOf(stock.getPrice()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, price, totalStock, totalWaste, actualStock;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            totalStock = itemView.findViewById(R.id.totstock);
            totalWaste = itemView.findViewById(R.id.stockwasted);
            actualStock = itemView.findViewById(R.id.actualstock);
            price = itemView.findViewById(R.id.ttprice);
        }
    }
}
