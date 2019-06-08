package com.example.todayeat;

import android.Manifest;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.Contract;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;


    private Intent intent;
    private ImageView imageView;
    private TextView name;
    private TextView number;
    private TextView menu;
    private TextView category;
    private TextView test;
    private Button checkbtn;

    public int coin=0;



    private static final int START_TIME_IN_MILLIS = 20000;
    public static boolean mTimerRunning;
    public static long mTimerLeftInMillis = START_TIME_IN_MILLIS;
    private TextView mTextViewCountDown ;
    private CountDownTimer mCountDownTimer;


    private SQLiteDatabase  db;
    DBHelper helper;
    String dbName = "coinValue.db";
    int dbVersion = 1;
    String tag = "SQLite";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // 과거에는 GoogleMap 객체를 얻어오기 위해
        // mapFragment.getMap() 메소드를 사용했으나
        // 현재 getMap() 은 deprecate 되었습니다.
        // 지금은 위와 같이 getMapAsync() 를 하고
        // 아래의 OnMapReady() 에서 처리를 GoogleMap 객체관련
        // 처리를 합니다.

        // MainActivity에서 보낸 imgRes를 받기위해 getIntent()로 초기화
        intent = getIntent();


        name = (TextView) findViewById(R.id.name);
        number = (TextView) findViewById(R.id.number);
        menu = (TextView) findViewById(R.id.menu);
        category = (TextView) findViewById(R.id.category);
        test = (TextView) findViewById(R.id.test);


        String n = intent.getExtras().getString("name");
        String m = intent.getExtras().getString("number");
        String r = intent.getExtras().getString("menu");
        String c = intent.getExtras().getString("category");
        String t = intent.getExtras().getString("test");

        name.setText(n);
        number.setText(m);
        menu.setText(r);
        category.setText(c);
        test.setText(t);

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
                    VisitCheck(v); // coin값 증가
                    startTimer();
                }
            }
        });


    }


    public void VisitCheck(View view){

        helper = new DBHelper(this, dbName, null, dbVersion);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e(tag, "데이터베이스를 얻어올 수 없음");
            finish();
        }
        update();
        select();
    }

    public void update() {
        db.execSQL("insert into coin_table(Coin) values(0);");
        db.execSQL("update coin_table set Coin = coin+100;"); // coin값 100씩 증가
    }

   public void select(){
       db = helper.getReadableDatabase();
       Cursor cursor = db.rawQuery("SELECT * FROM coin_table",null);
       cursor.moveToNext();
       Log.d(tag, "" + cursor.getInt(0));

       coin = cursor.getInt(0);
       Intent coin_intent = new Intent(this, MyCharacter.class);
       coin_intent.putExtra("Coinvalue", coin); //키 - 보낼 값(밸류)

       startActivity(coin_intent);

       cursor.close();
       helper.close();
       finish();
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



    /**
     * OnMapReady 는 map이 사용가능하면 호출되는 콜백 메소드입니다
     * 여기서 marker 나 line, listener, camera 이동 등을 설정해두면 됩니다.
     * 이번 예제에서는 서울역 근처에 marker를 더하고 적절한 title과, zoom을 설정해둡니다
     * 이 시점에서, 만약 사용자 기기게 Google Play service가 설치되지 않으면
     * 설치하라고 메세지가 뜨게 되고,  설치후에 다시 이 앱으로 제어권이 넘어옵니다
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // ↑매개변수로 GoogleMap 객체가 넘어옵니다.

        // camera 좌쵸를 서울역 근처로 옮겨 봅니다.
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(
                new LatLng(37.555744, 126.970431)   // 위도, 경도
        ));

        // 구글지도(지구) 에서의 zoom 레벨은 1~23 까지 가능합니다.
        // 여러가지 zoom 레벨은 직접 테스트해보세요
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        googleMap.animateCamera(zoom);   // moveCamera 는 바로 변경하지만,
        // animateCamera() 는 근거리에선 부드럽게 변경합니다

        // marker 표시
        // market 의 위치, 타이틀, 짧은설명 추가 가능.
        MarkerOptions marker = new MarkerOptions();
        marker .position(new LatLng(37.555744, 126.970431))
                .title("서울역")
                .snippet("Seoul Station");
        googleMap.addMarker(marker).showInfoWindow(); // 마커추가,화면에출력

        // 마커클릭 이벤트 처리
        // GoogleMap 에 마커클릭 이벤트 설정 가능.
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 마커 클릭시 호출되는 콜백 메서드
                Toast.makeText(getApplicationContext(),
                        marker.getTitle() + " 클릭했음"
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}









