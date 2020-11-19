package com.example.battery_checker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class Result_when_neg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_when_neg);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.redback));

    }
}
