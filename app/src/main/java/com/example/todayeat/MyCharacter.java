package com.example.todayeat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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


        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김



        TextView chingho1= (TextView)findViewById(R.id.textView1);

        Intent intent = getIntent(); //이 액티비티를 부른 인텐트를 받는다.
        String ch1 = intent.getStringExtra("Chingho1"); //"jizard"문자 받아옴
        chingho1.setText(ch1);



        TextView coinvalue= (TextView)findViewById(R.id.textView5); // coin값

        Intent intent1 = getIntent();
        int ch2 = intent1.getIntExtra("Coinvalue",0);
        coinvalue.setText(""+ch2);

        if(ch2<=200){
            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(myChar);
            Glide.with(this).load(R.drawable.char_real00).into(gifImage);
        }
        else if(ch2<=400){
            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(myChar);
            Glide.with(this).load(R.drawable.amuguna_char).into(gifImage);
        }
        else if(ch2<=600){
            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(myChar);
            Glide.with(this).load(R.drawable.amuguna_char2).into(gifImage);
        }
        else{
            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(myChar);
            Glide.with(this).load(R.drawable.char_real3).into(gifImage);
        }



    }
//
//    public void onClick1(View view){
//        Intent intent = new Intent(this, MyCharacter.class);
//        intent.putExtra("Chingho1","알촌쟁이"); //키 - 보낼 값(밸류)
//
//        //액티비티 시작!
//        startActivity(intent);
//        Toast.makeText(this,"칭호가 변경되었습니다.",Toast.LENGTH_LONG).show();
//        finish();
//    }
//    public void onClick2(View view){
//        Intent intent = new Intent(this, MyCharacter.class);
//        intent.putExtra("Chingho1","밥버거없이못살아"); //키 - 보낼 값(밸류)
//
//        //액티비티 시작!
//        startActivity(intent);
//        Toast.makeText(this,"칭호가 변경되었습니다.",Toast.LENGTH_LONG).show();
//        finish();
//    }
//    public void onClick3(View view){
//        Intent intent = new Intent(this, MyCharacter.class);
//        intent.putExtra("Chingho1","고칸사장친구"); //키 - 보낼 값(밸류)
//
//        //액티비티 시작!
//        startActivity(intent);
//        Toast.makeText(this,"칭호가 변경되었습니다.",Toast.LENGTH_LONG).show();
//        finish();
//    }
//    public void onClick4(View view){
//        Intent intent = new Intent(this, MyCharacter.class);
//        intent.putExtra("Chingho1","햄최몇"); //키 - 보낼 값(밸류)
//
//        //액티비티 시작!
//        startActivity(intent);
//        Toast.makeText(this,"칭호가 변경되었습니다.",Toast.LENGTH_LONG).show();
//        finish();
//    }


}
