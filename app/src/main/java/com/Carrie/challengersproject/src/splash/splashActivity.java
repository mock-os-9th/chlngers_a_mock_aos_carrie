package com.Carrie.challengersproject.src.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.a_MainActivity;
import com.Carrie.challengersproject.src.main.b_MainActivity;
import java.security.MessageDigest;



public class splashActivity extends AppCompatActivity {
    boolean IsLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // 5초 후에 화면이 변함함
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
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
        },5000);

    }

}
