package com.example.todayeat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class MyCharacter extends AppCompatActivity {
    ImageView myChar;
    Button tbtn1,tbtn2,tbtn3,tbtn4;
    Button re_btn;
    int coooin;
    public int coin=0;


    private SQLiteDatabase  db;
    DBHelper helper;
    String dbName = "coinValue.db";
    int dbVersion = 1;
    String tag = "SQLite";


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_character);

        myChar = (ImageView)findViewById(R.id.mychar);
        tbtn1=(Button)findViewById(R.id.tbtn1);
        tbtn2=(Button)findViewById(R.id.tbtn2);
        tbtn3=(Button)findViewById(R.id.tbtn3);
        tbtn4=(Button)findViewById(R.id.tbtn4);
        re_btn=(Button)findViewById(R.id.reset_btn);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김




        Intent intent = getIntent(); //이 액티비티를 부른 인텐트를 받는다.
        String ch1 = intent.getStringExtra("Chingho1"); //"jizard"문자 받아옴

        TextView level_name = findViewById(R.id.textView4);

        TextView coinvalue= (TextView)findViewById(R.id.textView5); // coin값

        Intent intent1 = getIntent();
        int ch2 = intent1.getIntExtra("Coinvalue",0);
        coinvalue.setText(""+ch2);
        coooin=ch2;

        if(ch2<=200){
            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(myChar);
            Glide.with(this).load(R.drawable.char_real00).into(gifImage);
            tbtn1.setText("강아지");
            level_name.setText("결정장애 말기..@");
        }
        else if(ch2<=400){
            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(myChar);
            Glide.with(this).load(R.drawable.amuguna_char).into(gifImage);
            tbtn1.setText("강아지");
            tbtn2.setText("고양이");
            level_name.setText("결정장애 중기");
        }
        else if(ch2<=600){
            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(myChar);
            Glide.with(this).load(R.drawable.amuguna_char2).into(gifImage);
            tbtn1.setText("강아지");
            tbtn2.setText("고양이");
            tbtn3.setText("감자");
            level_name.setText("결정장애 초기");
        }
        else{
            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(myChar);
            Glide.with(this).load(R.drawable.char_real3_2).into(gifImage);
            tbtn1.setText("강아지");
            tbtn2.setText("고양이");
            tbtn3.setText("감자");
            tbtn4.setText("양파");
            level_name.setText("결정장애 탈출!");
        }



    }

    public void onClick1(View view){

        Intent intent = new Intent(this, MyCharacter.class);
        intent.putExtra("Chingho1","tbtn1"); //키 - 보낼 값(밸류)
        intent.putExtra("Coinvalue", coooin);

        //액티비티 시작!
        startActivity(intent);
        Toast.makeText(this,"아이템이 변경되었습니다.",Toast.LENGTH_LONG).show();
        finish();
    }
    public void onClick2(View view){
        if(coooin<=400){
            Toast.makeText(getApplicationContext(),"레벨이 부족합니다.",Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this, MyCharacter.class);
            intent.putExtra("Chingho1","tbtn2"); //키 - 보낼 값(밸류)
            intent.putExtra("Coinvalue", coooin);

            //액티비티 시작!
            startActivity(intent);
            Toast.makeText(this,"아이템이 변경되었습니다.",Toast.LENGTH_LONG).show();
            finish();
        }
    }
    public void onClick3(View view){
        if(coooin<=600){
            Toast.makeText(getApplicationContext(),"레벨이 부족합니다.",Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this, MyCharacter.class);
            intent.putExtra("Chingho1","tbtn3"); //키 - 보낼 값(밸류)
            intent.putExtra("Coinvalue", coooin);

            //액티비티 시작!
            startActivity(intent);
            Toast.makeText(this,"아이템이 변경되었습니다.",Toast.LENGTH_LONG).show();
            finish();
        }

    }
    public void onClick4(View view){
        if(coooin<=800){
            Toast.makeText(getApplicationContext(),"레벨이 부족합니다.",Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this, MyCharacter.class);
            intent.putExtra("Chingho1","tbtn4"); //키 - 보낼 값(밸류)
            intent.putExtra("Coinvalue", coooin);

            //액티비티 시작!
            startActivity(intent);
            Toast.makeText(this,"아이템이 변경되었습니다.",Toast.LENGTH_LONG).show();
            finish();
        }

    }
    public void onClick5(View view){
        Toast.makeText(this,"준비중입니다.",Toast.LENGTH_LONG).show();
    }

    public void onClick6(View view){
        VisitCheck(view); // coin값 증가
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
        db.execSQL("update coin_table set Coin = 0;"); // coin값 0
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
}
