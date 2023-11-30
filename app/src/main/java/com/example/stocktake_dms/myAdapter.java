package com.example.stocktake_dms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    Context context;
    FirebaseDatabase db;

    ArrayList<Stock> stockArrayList;

    public myAdapter(Context context, ArrayList<Stock> stockArrayList) {
        this.context = context;
        this.stockArrayList = stockArrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.stockentry, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Stock stock = stockArrayList.get(position);
        holder.date.setText(stock.getDate());
        holder.category.setText(stock.getCategory());
        holder.wasteStock.setText(stock.getWasteStock());
        holder.actualStock.setText(stock.getActualStock());
        holder.totalPrice.setText(stock.getTotalPrice());

        holder.txt_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.txt_option);
                // Inflating menu from xml resource
                popup.inflate(R.menu.option_menu);
                // Adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(android.view.MenuItem item) {
                        if (item.getItemId() == R.id.menu_edit) {
                            // handle menu_edit click
                            Intent intent = new Intent(context, updatedata.class);
                            // add intent extras
                            context.startActivity(intent);
                            return true;
                        } else if (item.getItemId() == R.id.menu_delete) {
                            // Delete stock entry from the firebase
                            // handle menu_delete click
                            db = FirebaseDatabase.getInstance();
                            DatabaseReference reference = db.getReference("Stock");
                            reference.child(stock.getDate()).removeValue();
                            reference.child(stock.getCategory()).removeValue();
                            reference.child(stock.getWasteStock()).removeValue();
                            reference.child(stock.getActualStock()).removeValue();
                            reference.child(stock.getTotalPrice()).removeValue();
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, stockArrayList.size());
                            stockArrayList.remove(position);

                            return true;
                        }
                        return false;
                    }
                });
                // Displaying the popup
                popup.show();
            }
        });







    }

    @Override
    public int getItemCount() {
        return stockArrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView date, category, wasteStock, actualStock, totalPrice, txt_option;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            category = itemView.findViewById(R.id.category);
            wasteStock = itemView.findViewById(R.id.wastestock);
            actualStock = itemView.findViewById(R.id.actualStock);
            txt_option  = itemView.findViewById(R.id.txt_option);


        }
    }
}
