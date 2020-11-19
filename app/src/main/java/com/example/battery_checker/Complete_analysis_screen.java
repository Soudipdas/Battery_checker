package com.example.battery_checker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Complete_analysis_screen extends AppCompatActivity {

    TextView ovhealth,fintemp,finvolt;
    Intent intent;
    RelativeLayout temprel;
    RelativeLayout voltrel,mainback;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_analysis_screen);
        ovhealth=findViewById(R.id.ovhealth);
        fintemp=findViewById(R.id.fintemp);
        finvolt=findViewById(R.id.finvolt);
        intent=getIntent();
        temprel=findViewById(R.id.temprel);
        voltrel=findViewById(R.id.voltrel);
        int health=intent.getIntExtra("healthh",0);
        float temp=intent.getFloatExtra("temph",-1);
        Double volts=intent.getDoubleExtra("volth",0);

        imageView=findViewById(R.id.checkimg);
        if(health!=2){
            imageView.setImageResource(R.drawable.ic_warning_black_24dp);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.green));

        mainback=findViewById(R.id.mainback);
        if(health!=2){
            mainback.setBackgroundResource(R.color.redback);
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.redback));
        }
        //Toast.makeText(this,String.valueOf(health),Toast.LENGTH_SHORT).show();
        if(health==2){
            ovhealth.setText("GOOD");
        }
        else {
            ovhealth.setText("POOR");
        }
        fintemp.setText(""+temp);
        if(temp>50){
            temprel.setBackgroundResource(R.drawable.textbackred);
        }
        if(volts>5 || volts<1.5){
            voltrel.setBackgroundResource(R.drawable.textbackred);
        }
        finvolt.setText(""+volts);
    }
}
