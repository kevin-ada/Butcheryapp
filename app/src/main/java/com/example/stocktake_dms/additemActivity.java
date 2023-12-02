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
import java.util.Date;

public class additemActivity extends AppCompatActivity {

    ActivityAdditemBinding binding;

    String Date, TotalStock, WasteStock, actualStock, totalPrice;

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
                TotalStock = binding.TotalStock.getText().toString();
                WasteStock = binding.wastestock.getText().toString();
                actualStock = binding.actualStock.getText().toString();
                totalPrice = binding.price.getText().toString();
                Date = formattedDate;

                if (!TotalStock.isEmpty() && !WasteStock.isEmpty() && !actualStock.isEmpty()) {
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
                    binding.TotalStock.setError("Please enter category");
                    binding.wastestock.setError("Please enter waste stock");
                    binding.actualStock.setError("Please enter actual stock");
                    binding.price.setError("Please enter price");
                    Toast.makeText(additemActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }


}