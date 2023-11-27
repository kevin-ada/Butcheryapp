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

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ItemName = binding.stocknametobeupdated.getText().toString();
                String ItemQuantity = binding.stockquantity.getText().toString();
                String ItemPrice = binding.StockPrice.getText().toString();
                String Category = binding.StockCategory.getText().toString();
                String StockName = binding.StockUpdateName.getText().toString();

                UpdateData(ItemName,ItemQuantity,ItemPrice,Category, StockName);

            }
        });
    }

    private void UpdateData(String itemName, String itemQuantity, String itemPrice, String category, String stockName) {
        HashMap<String, Object> Stock = new HashMap<>();
        Stock.put("ItemName",itemName);
        Stock.put("StockName",stockName);
        Stock.put("ItemQuantity",itemQuantity);
        Stock.put("ItemPrice",itemPrice);
        Stock.put("Category",category);


        reference = FirebaseDatabase.getInstance().getReference("Stock"); //database reference
        reference.child(itemName).updateChildren(Stock).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()){
                    binding.StockUpdateName.setText("");
                    binding.StockCategory.setText("");
                    binding.StockPrice.setText("");
                    binding.stocknametobeupdated.setText("");
                    binding.stockquantity.setText("");
                    Toast.makeText(updatedata.this, "Stock has been Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(updatedata.this, "Failed to Update Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}