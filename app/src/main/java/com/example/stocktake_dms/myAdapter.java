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


//
//        double totalPrice = stock.getActualStock() * stock.getPrice();
        holder.date.setText(stock.getDate());
        holder.totalStock.setText(String.valueOf(stock.getTotalStock()));
        holder.totalWaste.setText(String.valueOf(stock.getTotalWaste()));
        holder.actualStock.setText(String.valueOf(stock.getActualStock()));
        holder.totalprice.setText(String.valueOf(stock.getPrice()));
        holder.buying_price.setText(String.valueOf(stock.getBuyingPrice()));

       double sellingPrice = stock.getPrice();
        double buyingPrice = stock.getBuyingPrice();
        double profitOrLoss = sellingPrice - buyingPrice;

        // Using an if statement to check if the total price is greater than the buying price return a profit else return a loss status

        if (sellingPrice > buyingPrice) {
            holder.profitorlossstatus.setText("Profit");
            holder.salesstatusvalue.setText(String.format("%.2f", profitOrLoss));
        } else {
            holder.profitorlossstatus.setText("Loss");
            holder.salesstatusvalue.setText(profitOrLoss < 0 ? "-" + String.format("%.2f", Math.abs(profitOrLoss)) : String.format("%.2f", profitOrLoss));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, totalprice, totalStock, totalWaste,buying_price,actualStock,profitorlossstatus ,salesstatusvalue;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            totalStock = itemView.findViewById(R.id.totstock);
            totalWaste = itemView.findViewById(R.id.stockwasted);
            actualStock = itemView.findViewById(R.id.actualstock);
            buying_price = itemView.findViewById(R.id.ttpurchase);
            totalprice = itemView.findViewById(R.id.ttprice);
            profitorlossstatus = itemView.findViewById(R.id.status);
            salesstatusvalue = itemView.findViewById(R.id.profitorlossvalue);
        }
    }
}
