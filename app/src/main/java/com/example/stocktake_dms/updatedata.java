package com.example.stocktake_dms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.stocktake_dms.databinding.ActivityUpdatedataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class updatedata extends AppCompatActivity {

    ActivityUpdatedataBinding binding;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatedataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Stock stock = (Stock)getIntent().getSerializableExtra("Stock");

        if (stock != null){
            binding.date.setText(stock.getDate());
            binding.price.setText(stock.getTotalPrice());
            binding.category.setText(stock.getCategory());
            binding.wastestock.setText(stock.getWasteStock());
        }

        binding.addStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = binding.date.getText().toString();
                String category = binding.category.getText().toString();
                String wastestock = binding.wastestock.getText().toString();
                String actualstock = binding.actualStock.getText().toString();
                String price = binding.price.getText().toString();

                HashMap<String, Object> map = new HashMap<>();
                map.put("date", date);
                map.put("category", category);
                map.put("wasteStock", wastestock);
                map.put("actualStock", actualstock);
                map.put("totalPrice", price);

                reference = FirebaseDatabase.getInstance().getReference().child("Stock");
                reference.child(date).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(updatedata.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }

}