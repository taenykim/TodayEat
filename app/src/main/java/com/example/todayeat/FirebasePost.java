package com.example.todayeat;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DowonYoon on 2017-07-11.
 */

@IgnoreExtraProperties
public class FirebasePost {
    public String id;
    public String name;
    public String age;
    public String gender;
    public String open;
    public String close;
    public String latitude;
    public String longitude;
    public String price;




    public FirebasePost(){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public FirebasePost(String id, String name, String age, String gender , String open , String close, String latitude, String longitude, String price) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.open = open;
        this.close = close;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("age", age);
        result.put("gender", gender);
        result.put("open",open);
        result.put("close",close);
        result.put("latitude",latitude);
        result.put("longitude",longitude);
        result.put("price",price);
        return result;
    }
}