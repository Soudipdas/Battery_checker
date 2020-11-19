package com.example.battery_checker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Result_screen extends AppCompatActivity {

    Intent intent;
    Button poscheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        intent=getIntent();
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.green));

        final int health=intent.getIntExtra("health",0);
        final float temp=intent.getFloatExtra("temp",-1);
        final Double volts=intent.getDoubleExtra("volt",0);

        //Toast.makeText(this,String.valueOf(health),Toast.LENGTH_SHORT).show();
        poscheck=findViewById(R.id.poscheck);
        poscheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Result_screen.this,Complete_analysis_screen.class);
                intent1.putExtra("healthh",health);
                intent1.putExtra("temph",temp);
                intent1.putExtra("volth",volts);
                startActivity(intent1);
            }
        });


    }
}
