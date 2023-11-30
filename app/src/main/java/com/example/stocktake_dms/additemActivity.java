package com.example.stocktake_dms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.stocktake_dms.databinding.ActivityAdditemBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class additemActivity extends AppCompatActivity {

    ActivityAdditemBinding binding;

    String Date, Category, WasteStock, actualStock, totalPrice;

    DatabaseReference reference;

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdditemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date = binding.date.getText().toString();
                Category = binding.category.getText().toString();
                WasteStock = binding.wastestock.getText().toString();
                actualStock = binding.actualStock.getText().toString();

                if (!Date.isEmpty() &&!Category.isEmpty() && !WasteStock.isEmpty() && !actualStock.isEmpty()) {
                    Stock stock = new Stock(Date, Category, WasteStock, actualStock, totalPrice);
                    db= FirebaseDatabase.getInstance();
                    reference = db.getReference("Stock");
                    reference.child(Date).setValue(stock).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.date.setText("");
                            binding.category.setText("");
                            binding.wastestock.setText("");
                            binding.actualStock.setText("");
                            binding.price.setText("");
                            Toast.makeText(additemActivity.this, "Stock Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    binding.date.setError("Please enter date");
                    binding.category.setError("Please enter category");
                    binding.wastestock.setError("Please enter waste stock");
                    binding.actualStock.setError("Please enter actual stock");
                    binding .price.setError("Please enter price");
                    Toast.makeText(additemActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}