package com.Carrie.challengersproject.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.b_MainActivity;
import com.Carrie.challengersproject.src.social.FacebookCallback;
import com.facebook.CallbackManager;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private ImageButton back_btn;
    private EditText user_email;
    private EditText user_pw;
    private Button start_btn;
    private Button facebook_btn;
    private Button naver_btn;
    private Button kakao_btn;

    private FacebookCallback mFacebookCallback;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        back_btn = findViewById(R.id.login_Ib_back);
        user_email = findViewById(R.id.login_et_email);
        user_pw = findViewById(R.id.login_et_pw);
        start_btn = findViewById(R.id.login_btn_start);
        facebook_btn = findViewById(R.id.login_btn_facebook);
        naver_btn = findViewById(R.id.login_btn_naver);
        kakao_btn = findViewById(R.id.login_btn_kakao);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 화면에서 백 버튼이니까 무조건 로그인 안 한 화면으로 간다.
                Intent intent = new Intent(LoginActivity.this, b_MainActivity.class );
                startActivity(intent);
                finish();
            }
        });

        mCallbackManager = CallbackManager.Factory.create();
        mFacebookCallback = new FacebookCallback();
        facebook_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(LoginActivity.this,
                        Arrays.asList("public_profile","email"));
                loginManager.registerCallback(mCallbackManager,mFacebookCallback);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
