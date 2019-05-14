package com.example.todayeat;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;

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

        mViewPager.addOnPageChangeListener(viewListener);
        Button b = (Button)findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        MyTwo.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
        Button b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        MyCharacter.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });



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
