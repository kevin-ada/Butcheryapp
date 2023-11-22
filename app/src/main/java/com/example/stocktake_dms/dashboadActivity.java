package com.example.stocktake_dms;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboadActivity extends AppCompatActivity {
    TextView firebasenameview;
    private FirebaseAuth  firebasAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);


        firebasenameview = findViewById(R.id.firebasename);
        // this is for username to appearing on dashboard after login
        firebasAuth = FirebaseAuth.getInstance();

        final FirebaseUser users = firebasAuth.getCurrentUser();
        String  finaluser = users.getEmail();
        String result = finaluser.substring(0,finaluser.indexOf("@"));
        String resultemail = result.replace(".", "");
        firebasenameview.setTextColor(Color.parseColor("#80A5D9"));
        firebasenameview.setText("Welcome " + resultemail);



        // Finding the CardView for the "Add New Item" button
        View cardView = findViewById(R.id.addItems);
        View cardView2 = findViewById(R.id.viewInventory);

        // Setting an onClickListener for the CardView

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GO to the add Item
                startActivity(new Intent(dashboadActivity.this, additemActivity.class));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GO to the add Item
                startActivity(new Intent(dashboadActivity.this, viewStockActivity.class));
            }
        });



    }

    //logout
    public void logout(){
        firebasAuth.signOut();
        finish();
        startActivity(new Intent(dashboadActivity.this, LoginActivity.class));
        Toast.makeText(dashboadActivity.this, "LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logoutMenu) {
            logout();
            return true; // Indicate that the item selection is handled
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}