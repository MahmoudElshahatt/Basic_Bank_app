package com.example.basicbankappfversion.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.basicbankappfversion.R;

public class HomeActivity extends AppCompatActivity {
    Button CustomersButton;
    Button TransfersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CustomersButton = findViewById(R.id.button_users);
        TransfersButton = findViewById(R.id.button_transfers);

        CustomersButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UsersActivity.class);
            startActivity(intent);
        });

        TransfersButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TransfersActivity.class);
            startActivity(intent);
        });

    }
}