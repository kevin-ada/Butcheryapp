package com.example.stocktake_dms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class additemActivity extends AppCompatActivity {

    /// Bind Views

    EditText itemName;
    EditText itemCategory;
    EditText itemPrice;
    EditText itemQuantity;
    DatabaseReference databaseStock;
    Button addBtnItem, ViewBtnItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        /// Initialize Views
        itemName = findViewById(R.id.edititemname);
        itemCategory = findViewById(R.id.editcategory);
        itemPrice = findViewById(R.id.editprice);
        itemQuantity = findViewById(R.id.editstock);
        addBtnItem = findViewById(R.id.additembuttontodatabase);
        ViewBtnItem = findViewById(R.id.view_item);
        databaseStock = FirebaseDatabase.getInstance().getReference();


        addBtnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///Get Values from EditText
                String itemnamevalue = itemName.getText().toString();
                String itemcategoryvalue = itemCategory.getText().toString();
                String itempricevalue = itemPrice.getText().toString();
                String itemquantityvalue = itemQuantity.getText().toString();
                String id = databaseStock.push().getKey();

                /// Check If Empty
                if (itemnamevalue.isEmpty() || itemcategoryvalue.isEmpty() || itempricevalue.isEmpty() || itemquantityvalue.isEmpty()) {
                    /// Show Error
                    itemName.setError("Please Fill All Fields");
                    itemCategory.setError("Please Fill All Fields");
                    itemPrice.setError("Please Fill All Fields");
                    itemQuantity.setError("Please Fill All Fields");
                } else {
                    /// Add Item to Database
                    Stock stock = new Stock(itemnamevalue, itemcategoryvalue, itempricevalue, itemquantityvalue);
                    databaseStock.child("Stock").child(id).setValue(stock).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            /// Show Message if task Successful
                            if (task.isSuccessful()){
                                Toast.makeText(additemActivity.this, "Items Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        ViewBtnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(additemActivity.this, viewStockActivity.class));
                finish();
            }
        });
    }

}