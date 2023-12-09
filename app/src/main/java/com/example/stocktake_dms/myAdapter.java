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
        double totalPrice = stock.getActualStock() * stock.getPrice();
        double totalBuyingPrice = stock.getBuyingPrice() * stock.getOpeningStock();
        holder.date.setText(stock.getDate());
        holder.totalStock.setText(String.valueOf(stock.getOpeningStock()));
        holder.totalWaste.setText(String.valueOf(stock.getTotalWaste()));
        holder.actualStock.setText(String.valueOf(stock.getActualStock()));
        holder.expenses.setText(String.format("%.2f", stock.getExpenses()));
        holder.closingStock.setText(String.valueOf(stock.getClosingStock()));
        holder.saleForTheDay.setText(String.format("%.2f", totalPrice));
        holder.buying_price.setText(String.format("%.2f", totalBuyingPrice));


       double sellingPrice = totalPrice;
        double buyingPrice = stock.getBuyingPrice() * stock.getOpeningStock();
        double profitOrLoss = sellingPrice - buyingPrice - stock.getExpenses();

        // Using an if statement to check if the total price is greater than the buying price return a profit else return a loss status

        if (sellingPrice > buyingPrice) {
            holder.profitorlossstatus.setText("Profit");
            holder.NetchangeValue.setText(String.format("%.2f", profitOrLoss));
        } else {
            holder.profitorlossstatus.setText("Loss");
            holder.NetchangeValue.setText(profitOrLoss < 0 ? "-" + String.format("%.2f", Math.abs(profitOrLoss)) : String.format("%.2f", profitOrLoss));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, saleForTheDay, totalStock, totalWaste,buying_price,actualStock,profitorlossstatus , NetchangeValue, expenses, closingStock, netpercantage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            totalStock = itemView.findViewById(R.id.totstock);
            totalWaste = itemView.findViewById(R.id.stockwasted);
            actualStock = itemView.findViewById(R.id.actualstock);
            buying_price = itemView.findViewById(R.id.ttpurchase);
            saleForTheDay = itemView.findViewById(R.id.ttprice);
            profitorlossstatus = itemView.findViewById(R.id.status);
            NetchangeValue = itemView.findViewById(R.id.profitorlossvalue);
            expenses = itemView.findViewById(R.id.expenses);
            closingStock = itemView.findViewById(R.id.closingstock);
        }
    }
}
