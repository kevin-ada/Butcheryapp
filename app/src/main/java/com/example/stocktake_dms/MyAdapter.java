package com.example.stocktake_dms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Stock> list;

    public MyAdapter(Context context, ArrayList<Stock> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.stockentry, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Stock stock = list.get(position);
        holder.itemname.setText(stock.getItemname());
        holder.itemcategory.setText(stock.getItemcategory());
        holder.itemprice.setText(stock.getItemprice());
        holder.itemquantity.setText(stock.getItemquantity());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemname, itemcategory, itemprice, itemquantity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.stockName);
            itemcategory = itemView.findViewById(R.id.stockCategory);
            itemprice = itemView.findViewById(R.id.stockPrice);
            itemquantity = itemView.findViewById(R.id.stockQuantity);
        }
    }
}
