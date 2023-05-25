package com.example.t8_blockchain_food_nutrition_freshwhile_20230525;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private Button registerButton;
    private Button viewRecordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        registerButton = findViewById(R.id.registerButton);
        viewRecordButton = findViewById(R.id.viewRecordButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the activity to register fresh produce
                Intent intent = new Intent(HomeActivity.this, RegisterProduce.class);
                startActivity(intent);
            }
        });

        viewRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the activity to view records
                Intent intent = new Intent(HomeActivity.this, ViewRecord.class);
                startActivity(intent);
            }
        });
    }
}
