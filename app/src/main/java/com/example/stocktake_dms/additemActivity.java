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

    /// Bind Views

    String itemName;
    String itemCategory;
    String itemPrice;
    String itemQuantity;
    DatabaseReference databaseStock;
    Button addBtnItem, ViewBtnItem;
    ActivityAdditemBinding binding;
    FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdditemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.additembuttontodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemName = binding.stocknameuid.getText().toString();
                itemCategory = binding.stockcategory.getText().toString();
                itemPrice = binding.stockprice.getText().toString();
                itemQuantity = binding.stockqnty.getText().toString();

                if (itemName.isEmpty() && itemCategory.isEmpty() && itemPrice.isEmpty() && itemQuantity.isEmpty()) {
                    binding.stockqnty.setError("Please enter all fields");
                    binding.stockprice.setError("Please enter all fields");
                    binding.stocknameuid.setError("Please enter all fields");
                    binding.stockcategory.setError("Please enter all fields");
                } else {
                    Stock stock = new Stock(itemName, itemCategory, itemPrice, itemQuantity);
                    db = FirebaseDatabase.getInstance();
                    databaseStock = db.getReference("Stock");
                    databaseStock.child(itemName).setValue(stock).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.stocknameuid.setText("");
                            binding.stockcategory.setText("");
                            binding.stockprice.setText("");
                            binding.stockqnty.setText("");
                            Toast.makeText(additemActivity.this, "Stock has been Successfully Stored", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });




    }

}