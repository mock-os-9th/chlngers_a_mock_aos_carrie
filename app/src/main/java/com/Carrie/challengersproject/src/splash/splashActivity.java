package com.Carrie.challengersproject.src.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.a_MainActivity;
import com.Carrie.challengersproject.src.main.b_MainActivity;

public class splashActivity extends AppCompatActivity {

    boolean IsLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(IsLogin)
        {
            // login 되어 있을때 하단 탭 아이콘 5개인 a_MainActivity 로
            Intent intent = new Intent(splashActivity.this, a_MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            // login 안 되어 있을 때 탭 아이콘 4개인 b_MainActivity 로
            Intent intent = new Intent(splashActivity.this, b_MainActivity.class);
            startActivity(intent);
            finish();
        }



    }
}
