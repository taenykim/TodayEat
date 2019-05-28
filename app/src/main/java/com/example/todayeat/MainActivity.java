package com.example.todayeat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private DetailActivity detailActivity;

    private SQLiteDatabase db;
    DBHelper helper;
    String dbName = "coinValue.db";
    int dbVersion = 1;
    String tag = "SQLite";
    int coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);

        mViewPager.setAdapter(sliderAdapter);
        mViewPager.setCurrentItem(2);

        addDotsIndicator(2);

        helper = new DBHelper(this, dbName, null, dbVersion); // cointable 관련
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e(tag, "데이터베이스를 얻어올 수 없음");
            finish();
        }

        mViewPager.addOnPageChangeListener(viewListener);
        Button b = (Button)findViewById(R.id.listbtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ListActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
        Button b2 = (Button)findViewById(R.id.charbtn);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });


    }

    public void select(){
        // cointable에 저장된 값 보여주기
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
    }

    public void addDotsIndicator(int position){
        mDotLayout.removeAllViews();
        mDots = new TextView[5];

        for(int i=0;i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(Color.rgb(170,170,170));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0 ){
            mDots[position].setTextColor(Color.BLACK);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
