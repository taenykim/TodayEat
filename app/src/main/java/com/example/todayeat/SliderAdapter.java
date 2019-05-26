package com.example.todayeat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.todayeat.ListActivity.arrayData;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    private DatabaseReference mPostReference;
    ArrayList<String> arl = new ArrayList<>();


    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.spaguetti,
            R.drawable.brochettes,
            R.drawable.shuffle,
            R.drawable.rice,
            R.drawable.fries
    };

    public String[] slide_headings = {
            "일식",
            "중식",
            "아무거나",
            "한식",
            "패스트푸드"
    };

    @Override
    public int getCount(){
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o){
        return view == (RelativeLayout)o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        slideHeading.setTextColor(Color.WHITE);
        Button btn = (Button) view.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //0 >> 분류
                //1 >> 가게이름
                //2 >> 전화번호
                //3 >> 대표메뉴
                Intent intent = new Intent(v.getContext(), DetailActivity.class);

                intent.putExtra("name","이름");
                intent.putExtra("number","넘버");
                intent.putExtra("menu","메뉴");

                v.getContext().startActivity(intent);
            }
        });

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideHeading.setTextColor(Color.BLACK);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }

}
