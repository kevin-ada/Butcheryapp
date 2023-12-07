package com.example.stocktake_dms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.stocktake_dms.databinding.ActivityAdditemBinding;
import com.example.stocktake_dms.databinding.ActivityExpenseBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class expense extends AppCompatActivity {


    ActivityExpenseBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;

    double labourcost;
    double miscellaneous;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        binding = ActivityExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //set date and time

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());
        binding.dateentry.setText(currentDate);






    }
}