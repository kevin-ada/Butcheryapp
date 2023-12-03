package com.example.stocktake_dms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    Context context;

    ArrayList<Stock> stockArrayList;


    public myAdapter(Context context, ArrayList<Stock> stockArrayList) {
        this.context = context;
        this.stockArrayList = stockArrayList;
    }


    @NonNull
    @Override
    public myAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.stockentry, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.myViewHolder holder, int position) {

        Stock stock = stockArrayList.get(position);
        holder.date.setText(stock.getDate());
        holder.wasteStock.setText(String.valueOf(stock.getWasteStock()));
        holder.actualStock.setText(String.valueOf(stock.getActualStock()));
        holder.totalPrice.setText(String.valueOf(stock.getTotalPrice()));

    }

    @Override
    public int getItemCount() {
        return stockArrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView date, category, wasteStock, actualStock, totalPrice;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            category = itemView.findViewById(R.id.category);
            wasteStock = itemView.findViewById(R.id.wastestock);
            actualStock = itemView.findViewById(R.id.actualStock);



        }





    }


}
