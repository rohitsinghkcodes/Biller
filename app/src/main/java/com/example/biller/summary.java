package com.example.biller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class summary extends AppCompatActivity {

    TextView sumry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        this.setTitle("Bill Summary >");

        sumry = findViewById(R.id.sumry);

        Intent intent = getIntent();
        String details = intent.getStringExtra("detailSummary");

        sumry.setText(details);
    }
}