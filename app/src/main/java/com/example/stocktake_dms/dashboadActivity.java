package com.example.stocktake_dms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);

        // Finding the CardView for the "Add New Item" button
        View cardView = findViewById(R.id.addItems);
        // Setting an onClickListener for the CardView

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GO to the add Item
                startActivity(new Intent(dashboadActivity.this, additemActivity.class));
            }
        });
    }
}