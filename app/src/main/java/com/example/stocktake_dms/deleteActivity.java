package com.example.stocktake_dms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.stocktake_dms.databinding.ActivityDeleteBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deleteActivity extends AppCompatActivity {
    ActivityDeleteBinding binding;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.deletedataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Stockname = binding.etstockname.getText().toString();
                if (!Stockname.isEmpty()) {
                    deleteStock(Stockname);
                } else {
                    Toast.makeText(deleteActivity.this, "Please enter stock name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteStock(String stockname) {
         reference = FirebaseDatabase.getInstance().getReference("Stock");
         reference.child(stockname).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 if (task.isSuccessful()){
                     Toast.makeText(deleteActivity.this, "Stock deleted successfully", Toast.LENGTH_SHORT).show();
                     binding.etstockname.setText("");
                 }else {
                     Toast.makeText(deleteActivity.this, "Failed to delete stock", Toast.LENGTH_SHORT).show();
                 }

             }
         });
    }
}