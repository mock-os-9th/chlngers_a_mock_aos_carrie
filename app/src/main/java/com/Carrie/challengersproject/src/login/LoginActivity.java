package com.Carrie.challengersproject.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.common.view.SimpleMessageDialog;
import com.Carrie.challengersproject.src.main.b_MainActivity;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;

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
    private KakaoCallback kakaoCallback;
    private CallbackManager mCallbackManager;

    boolean Isfalse;

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

        final String user_pw_str = user_pw.getText().toString();
        final String email_empty_alert = getString(R.string.login_empty_email);
        final String confirm_text = getString(R.string.confirm);
        final String emial_wrong_alert = getString(R.string.login_wrong_email_form);

        // 입력받은 로그인 정보 처리
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email_str = user_email.getText().toString();
                // 이메일 입력해주세요
                if(user_email_str.equals(""))
                {
                    SimpleMessageDialog simpleMessageDialog = new SimpleMessageDialog.Builder(LoginActivity.this)
                            .setMessage(email_empty_alert)
                            .setButtonText(confirm_text)
                            .setOnClickListener(new SimpleMessageDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog) {
                                    dialog.dismiss();
                                }
                            }).build();
                    simpleMessageDialog.show();
                }
                // 이메일 형식이 올바르지 않습니다
                else if(!(user_email_str.contains("@")))
                {
                    SimpleMessageDialog simpleMessageDialog = new SimpleMessageDialog.Builder(LoginActivity.this)
                            .setMessage(emial_wrong_alert)
                            .setButtonText(confirm_text)
                            .setOnClickListener(new SimpleMessageDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog) {
                                    dialog.dismiss();
                                }
                            }).build();
                    simpleMessageDialog.show();
                }

                // 해당 이메일로 가이된 유저가~~~
                else if(!(user_email_str.equals("")) && !(user_pw_str.equals("")))
                {
                    // 해당 이메일과 비밀번호 회원 정보에서 조회 후 있으면 after main activity 로,
                    // 없으면 SimpleMessageDialog 띄우기기
                }
           }
        });

        // 뒤로가기 버튼
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 화면에서 백 버튼이니까 무조건 로그인 안 한 화면으로 간다.
                Intent intent = new Intent(LoginActivity.this, b_MainActivity.class );
                startActivity(intent);
                finish();
            }
        });

        // 페이스북 로그인 - 성공시 바로 메인 엑티비티로 사용자 정보와 함꼐 가져간다.
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

        // [미완!!] 카카오 로그인 -
        kakaoCallback = new KakaoCallback(getApplicationContext());
        kakao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.getCurrentSession().addCallback(kakaoCallback);
                Session.getCurrentSession().checkAndImplicitOpen();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data))
        {
            super.onActivityResult(requestCode,resultCode,data);
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(kakaoCallback);
    }
}
