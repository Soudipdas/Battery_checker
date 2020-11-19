package com.example.battery_checker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.htextview.HTextView;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.DecimalFormat;

public class Home_Screen extends AppCompatActivity {

    mBatInfoReceiver myBatInfoReceiver;
    Button check;
    int volageValue;
    float temp;
    float fullVoltage;
    Double decimalVoltage;
    DecimalFormat decimalformat;
    IntentFilter intentfilter;
    int health1;

    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);

        check=findViewById(R.id.check);
        myBatInfoReceiver = new mBatInfoReceiver();
        avi=findViewById(R.id.avi);
        //avi.hide();
        avi.setVisibility(View.GONE);

        //sample.setVisibility(View.INVISIBLE);

        this.registerReceiver(this.myBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        final float temp1 =myBatInfoReceiver.get_temp();
        Home_Screen.this.registerReceiver(broadcastreceiver,intentfilter);




        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(Home_Screen.this,String.valueOf(temp),Toast.LENGTH_SHORT).show();
                avi.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent;
                        if(health1==2) {
                            intent = new Intent(Home_Screen.this, Result_screen.class);
                            intent.putExtra("health",health1);
                            intent.putExtra("temp",temp);
                            intent.putExtra("volt",decimalVoltage);
                        }
                        else {
                            intent = new Intent(Home_Screen.this, Result_when_neg.class);
                            intent.putExtra("health",health1);
                            intent.putExtra("temp",temp);
                            intent.putExtra("volt",decimalVoltage);
                        }
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Home_Screen.this);
                        Home_Screen.this.startActivity(intent,options.toBundle());
                        avi.hide();

                    }
                },2000);


            }
        });

    }

    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            volageValue = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
            fullVoltage = (float) (volageValue * 0.001);
            decimalformat = new DecimalFormat("#.#");
            decimalVoltage = Double.valueOf(decimalformat.format(fullVoltage));

            temp=(float) (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0))/10;
            health1 = intent.getIntExtra("health", 0);
        }
    };
}
