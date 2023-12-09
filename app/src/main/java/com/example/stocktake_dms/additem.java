package com.example.stocktake_dms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.stocktake_dms.databinding.ActivityAdditemBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class additem extends AppCompatActivity {

    ActivityAdditemBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;

    double salesperkg;
    double openingStock;
    String date;
    double totalWaste;
    double actualStock;

    double closingStock;

    double expenses;

    double sales;

    double buyingpriceperkg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdditemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());
        binding.dateentry.setText(currentDate);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// Getting the values from the fields
                salesperkg = Double.parseDouble(binding.priceentry.getText().toString());
                buyingpriceperkg = Double.parseDouble(binding.buyingprice.getText().toString());
                openingStock = Double.parseDouble(binding.openStock.getText().toString());
                closingStock = Double.parseDouble(binding.closingStock.getText().toString());
                expenses = Double.parseDouble(binding.expense.getText().toString());
                date = binding.dateentry.getText().toString();
                totalWaste = Double.parseDouble(binding.waste.getText().toString());
                /// Calculating the actual stock
                actualStock = openingStock - closingStock - totalWaste;
                // Calculating the total price
                sales = actualStock *  Double.parseDouble(binding.priceentry.getText().toString());

                /// validating the fields are empty
                if (!date.isEmpty() && !binding.priceentry.getText().toString().isEmpty() &&
                        !binding.openStock.getText().toString().isEmpty() &&
                        !binding.closingStock.getText().toString().isEmpty() &&
                        !binding.expense.getText().toString().isEmpty() &&
                        !binding.buyingprice.getText().toString().isEmpty() &&
                        !binding.waste.getText().toString().isEmpty()
                ) {
                    /// Creating a new stock object
                    Stock stock = new Stock(salesperkg, openingStock, date, totalWaste, actualStock, buyingpriceperkg, closingStock, expenses);

                    /// Adding the stock object to the database
                    db = FirebaseDatabase.getInstance();

                    /// Getting the reference to the database
                    reference = db.getReference("Stock");

                    /// Adding the stock object to the database
                    reference.child(date).setValue(stock).addOnCompleteListener(new OnCompleteListener<Void>() {
                        /// On complete listener to check if the data has been added
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            /// Clearing the fields after the data has been added
                            binding.priceentry.setText("");
                            binding.openStock.setText("");
                            binding.dateentry.setText("");
                            binding.waste.setText("");
                            binding.closingStock.setText("");
                            binding.expense.setText("");
                            binding.buyingprice.setText("");
                            Toast.makeText(additem.this, "Stock has been added Successfully", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
                else{
                    /// Setting the necessary error messages
                    binding.priceentry.setError("Please enter the price");
                    binding.openStock.setError("Please enter the total stock");
                    binding.dateentry.setError("Please enter the date");
                    binding.waste.setError("Please enter the total waste");
                    binding.closingStock.setError("Please enter the closing stock");
                    binding.expense.setError("Please enter the expenses");
                    binding.buyingprice.setError("Please enter the buying price");
                    Toast.makeText(additem.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}