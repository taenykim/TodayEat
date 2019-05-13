package com.example.todayeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChingHo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ching_ho);



    }

    public void onClick1(View view){
        Intent intent = new Intent(this, MyCharacter.class);
        intent.putExtra("Chingho1","결정장애 초기"); //키 - 보낼 값(밸류)

        //액티비티 시작!
        startActivity(intent);




        Toast.makeText(this,"칭호가 변경되었습니다.",Toast.LENGTH_LONG).show();
    }
}
