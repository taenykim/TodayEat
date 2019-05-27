package com.example.todayeat;

import android.Manifest;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private Intent intent;
    private ImageView imageView;
    private TextView name;
    private TextView number;
    private TextView menu;
    private Button checkbtn;

    public int coin=0;



    private static final int START_TIME_IN_MILLIS = 20000;
    public static boolean mTimerRunning;
    public static long mTimerLeftInMillis = START_TIME_IN_MILLIS;
    private TextView mTextViewCountDown ;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);

        // MainActivity에서 보낸 imgRes를 받기위해 getIntent()로 초기화
        intent = getIntent();
        imageView = (ImageView) findViewById(R.id.imageDetail);

        name = (TextView) findViewById(R.id.name);
        number = (TextView) findViewById(R.id.number);
        menu = (TextView) findViewById(R.id.menu);

        String n = intent.getExtras().getString("name");
        String m = intent.getExtras().getString("number");
        String r = intent.getExtras().getString("menu");


        name.setText(n);
        number.setText(m);
        menu.setText(r);

        mTextViewCountDown = findViewById(R.id.textView6);
        if(mTimerRunning){
            startTimer();
        }
        else{
            mTextViewCountDown.setText("아직안묵었다");
        }

        checkbtn = findViewById(R.id.button3);
        checkbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mTimerRunning){
                    Toast.makeText(getApplicationContext(),"이미 눌렀습니다.",Toast.LENGTH_LONG).show();
                }
                else{
                    startTimer();
                }
            }
        });


    }

    public void VisitCheck(View v){



        coin = coin + 100;





    }

    public void startTimer(){
        mCountDownTimer= new CountDownTimer(mTimerLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning=false;
            }
        }.start();

        mTimerRunning = true;
    }

    public void updateCountDownText(){
        int minutes = (int) (mTimerLeftInMillis/1000)/60;
        int seconds = (int) (mTimerLeftInMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewCountDown.setText(timeLeftFormatted);

        if(minutes==0 && seconds==0){
            mTimerLeftInMillis = START_TIME_IN_MILLIS;
        }
    }
}









