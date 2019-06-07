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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.todayeat.ListActivity.arrayData;
import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    private DatabaseReference mPostReference;
    static ArrayList<String> arrayIndex =  new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();

    String sort = "idx";
    String ID;
    String name;
    String age;
    String gender = "";
    int po;


    public SliderAdapter(Context context){
        this.context = context;
        getFirebaseDatabase();
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
    public Object instantiateItem(ViewGroup container, final int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        slideHeading.setTextColor(Color.WHITE);
        Button btn = (Button) view.findViewById(R.id.button);

        po=position;

        switch (po){
            case 0:

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //0 >> 분류
                        //1 >> 가게이름
                        //2 >> 전화번호
                        //3 >> 대표메뉴

                        Intent intent = new Intent(v.getContext(), DetailActivity.class);
                        Random random = new Random();
                        String[] tempData = arrayData.get(random.nextInt(2)+2).split("\\s+");

                        intent.putExtra("name",tempData[1]);
                        intent.putExtra("number",tempData[2]);
                        intent.putExtra("menu",tempData[3]);

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 1:

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //0 >> 분류
                        //1 >> 가게이름
                        //2 >> 전화번호
                        //3 >> 대표메뉴

                        Intent intent = new Intent(v.getContext(), DetailActivity.class);
                        Random random = new Random();
                        String[] tempData = arrayData.get(random.nextInt(1)+4).split("\\s+");

                        intent.putExtra("name",tempData[1]);
                        intent.putExtra("number",tempData[2]);
                        intent.putExtra("menu",tempData[3]);

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 2:

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //0 >> 분류
                        //1 >> 가게이름
                        //2 >> 전화번호
                        //3 >> 대표메뉴

                        Intent intent = new Intent(v.getContext(), DetailActivity.class);
                        Random random = new Random();
                        String[] tempData = arrayData.get(random.nextInt(6)).split("\\s+");

                        intent.putExtra("name",tempData[1]);
                        intent.putExtra("number",tempData[2]);
                        intent.putExtra("menu",tempData[3]);

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 3:

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //0 >> 분류
                        //1 >> 가게이름
                        //2 >> 전화번호
                        //3 >> 대표메뉴

                        Intent intent = new Intent(v.getContext(), DetailActivity.class);
                        Random random = new Random();
                        String[] tempData = arrayData.get(random.nextInt(2)).split("\\s+");

                        intent.putExtra("name",tempData[1]);
                        intent.putExtra("number",tempData[2]);
                        intent.putExtra("menu",tempData[3]);

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 4:
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //0 >> 분류
                        //1 >> 가게이름
                        //2 >> 전화번호
                        //3 >> 대표메뉴

                        Intent intent = new Intent(v.getContext(), DetailActivity.class);
                        Random random = new Random();
                        String[] tempData = arrayData.get(random.nextInt(2)+5).split("\\s+");

                        intent.putExtra("name",tempData[1]);
                        intent.putExtra("number",tempData[2]);
                        intent.putExtra("menu",tempData[3]);

                        v.getContext().startActivity(intent);
                    }
                });

                break;

            default:
                break;
        }

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

    public boolean IsExistID(){
        boolean IsExist = arrayIndex.contains(ID);
        return IsExist;
    }

    public void postFirebaseDatabase(boolean add){
        mPostReference = getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if(add){
            FirebasePost post = new FirebasePost(ID, name, age, gender);
            postValues = post.toMap();
        }
        childUpdates.put("/id_list/" + ID, postValues);
        mPostReference.updateChildren(childUpdates);
    }

    public void getFirebaseDatabase(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("getFirebaseDatabase", "key: " + dataSnapshot.getChildrenCount());
                arrayData.clear();
                arrayIndex.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FirebasePost get = postSnapshot.getValue(FirebasePost.class);
                    String[] info = {get.id, get.name, String.valueOf(get.age), get.gender};
                    String Result = setTextLength(info[0],10) + setTextLength(info[1],10) + setTextLength(info[2],10) + setTextLength(info[3],10);
                    arrayData.add(Result);
                    arrayIndex.add(key);
                    Log.d("getFirebaseDatabase", "key: " + key);
                    Log.d("getFirebaseDatabase", "info: " + info[0] + info[1] + info[2] + info[3]);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getFirebaseDatabase","loadPost:onCancelled", databaseError.toException());
            }
        };
        Query sortbyAge = getInstance().getReference().child("id_list").orderByChild("idx");
        sortbyAge.addListenerForSingleValueEvent(postListener);
    }

    public String setTextLength(String text, int length){
        if(text.length()<length){
            int gap = length - text.length();
            for (int i=0; i<gap; i++){
                text = text + " ";
            }
        }
        return text;
    }

}
