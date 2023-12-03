package com.example.stocktake_dms;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stocktake_dms.databinding.ActivityAdditemBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class additemActivity extends AppCompatActivity {

    ActivityAdditemBinding binding;

    String Date;
    double TotalStock;
    double WasteStock;
    double actualStock;
    double totalPrice;

    DatabaseReference reference;

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdditemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Get current date
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();

        // Format the date (optional)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);

        // Display the formatted date or use it as needed
        // set date on a TextView
        TextView textViewDate = findViewById(R.id.date);
        textViewDate.setText(formattedDate);

        binding.addStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalStock = Double.parseDouble(binding.TotalStock.getText().toString());
                WasteStock = Double.parseDouble(binding.wastestock.getText().toString());
                actualStock = Double.parseDouble(binding.actualStock.getText().toString());
                totalPrice = Double.parseDouble(binding.price.getText().toString());
                Date = formattedDate;
                /// Calculate actual stock
                /// Calculate total price
                actualStock = TotalStock - WasteStock;
                totalPrice = actualStock * Double.parseDouble(binding.price.getText().toString());

                if (TotalStock > 0.0 && WasteStock > 0.0 && actualStock > 0.0 && totalPrice > 0.0 ) {
                    Stock stock = new Stock(Date, TotalStock, WasteStock, actualStock, totalPrice);



                    db= FirebaseDatabase.getInstance();
                    reference = db.getReference("Stock");
                    reference.child(Date).setValue(stock).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.date.setText("");
                            binding.TotalStock.setText("");
                            binding.wastestock.setText("");
                            binding.actualStock.setText("");
                            binding.price.setText("");
                            Toast.makeText(additemActivity.this, "Stock Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    //binding.date.setError("Please enter date");
                    binding.TotalStock.setError("Please enter Total Stock");
                    binding.wastestock.setError("Please enter waste stock");
                    binding.actualStock.setError("Please enter actual stock");
                    binding.price.setError("Please enter price");
                    Toast.makeText(additemActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }


}