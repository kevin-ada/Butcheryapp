package com.example.stocktake_dms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class additemActivity extends AppCompatActivity {

    /// Bind Views

    EditText itemName;
    EditText itemCategory;
    EditText itemPrice;
    EditText itemQuantity;

    Button addBtnItem;


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


        addBtnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///Get Values from EditText
                String itemnamevalue = itemName.getText().toString();
                String itemcategoryvalue = itemCategory.getText().toString();
                String itempricevalue = itemPrice.getText().toString();
                String itemquantityvalue = itemQuantity.getText().toString();

                /// Check If Empty
                if (itemnamevalue.isEmpty() || itemcategoryvalue.isEmpty() || itempricevalue.isEmpty() || itemquantityvalue.isEmpty()) {
                    /// Show Error
                    itemName.setError("Please Fill All Fields");
                    itemCategory.setError("Please Fill All Fields");
                    itemPrice.setError("Please Fill All Fields");
                    itemQuantity.setError("Please Fill All Fields");
                } else {
                    /// Add Item to Database
                    addItemsToDB(itemnamevalue, itemcategoryvalue, itempricevalue, itemquantityvalue);
                }
            }
        });
    }

    private void addItemsToDB(String itemnamevalue, String itemcategoryvalue, String itempricevalue, String itemquantityvalue) {
        // Create a HashMap with values
        HashMap<String, Object> stockHashMap = new HashMap<>();
        stockHashMap.put("itemname", itemnamevalue);
        stockHashMap.put("itemcategory", itemcategoryvalue);
        stockHashMap.put("itemprice", itempricevalue);
        stockHashMap.put("itemquantity", itemquantityvalue);

        // Initialize Firebase DB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Add Item to DB
        DatabaseReference stockRef = database.getReference("stocktake-dms-default-rtdb");

        String key = stockRef.push().getKey();
        stockHashMap.put("key", key);

        try {
            stockRef.child(key).setValue(stockHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(additemActivity.this, "Stock Added Successfully", Toast.LENGTH_SHORT).show();
                    itemName.getText().clear();
                    itemCategory.getText().clear();
                    itemPrice.getText().clear();
                    itemQuantity.getText().clear();
                }
            });
        } catch (Exception e) {
            Toast.makeText(additemActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}