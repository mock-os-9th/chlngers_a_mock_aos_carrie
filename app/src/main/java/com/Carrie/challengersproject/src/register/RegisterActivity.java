package com.Carrie.challengersproject.src.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.common.view.SimpleMessageDialog;
import com.Carrie.challengersproject.src.login.LoginActivity;
import com.Carrie.challengersproject.src.main.b_MainActivity;
import com.facebook.CallbackManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.kakao.usermgmt.LoginButton;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class RegisterActivity extends AppCompatActivity {

    private EditText user_email;
    private EditText user_pw;
    private EditText confirm_user_pw;

    private Button next;
    private Button facebook_login;
    private Button naver_login;
    private Button kakao_login;
    private ImageButton back_btn;

    private TextView email_login;



    private com.facebook.login.widget.LoginButton facebook_invisible_btn;
    private LoginButton kakao_invisible_btn;

    private LoginActivity.FacebookCallback mFacebookCallback;
//    private LoginActivity.KakaoCallback kakaoCallback;
    private CallbackManager mCallbackManager;

    static Context context;
//    private ExtendedFloatingActionButton company_or_not;
    boolean IsCompany =false;

    // 회원가입 및 로그인 성공시 a_ㅡMainActivity로
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_email = findViewById(R.id.Register_et_email);
        user_pw = findViewById(R.id.Register_et_pw);
        confirm_user_pw = findViewById(R.id.Register_et_pw_confirm);

        next = findViewById(R.id.Register_btn_start);
        facebook_login = findViewById(R.id.Register_btn_facebook);
        naver_login = findViewById(R.id.Register_btn_naver);
        kakao_login = findViewById(R.id.Register_btn_kakao);

        email_login = findViewById(R.id.Register_tv_btn_emial);


       final String user_pw_str = user_pw.getText().toString();
       final String user_confirm_pw_str = user_pw.getText().toString();
       final String email_empty_alert = getString(R.string.login_empty_email);
       final String confirm_text = getString(R.string.confirm);
       final String emial_wrong_alert = getString(R.string.login_wrong_email_form);
       final String pw_lenth_alert = getString(R.string.login_password_length);

       // 입력받은 로그인 정보 처리
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String user_email_str = user_email.getText().toString();
               if(user_pw_str.length() < 8)
               {
                   SimpleMessageDialog simpleMessageDialog = new SimpleMessageDialog.Builder(RegisterActivity.this)
                           .setMessage(pw_lenth_alert)
                           .setButtonText(confirm_text)
                           .setOnClickListener(new SimpleMessageDialog.OnClickListener() {
                               @Override
                               public void onClick(Dialog dialog) {
                                   dialog.dismiss();
                               }
                           }).build();
                   simpleMessageDialog.show();
               }
               // 이메일 입력해주세요
               if (user_email_str.equals("")) {
                   SimpleMessageDialog simpleMessageDialog = new SimpleMessageDialog.Builder(RegisterActivity.this)
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
               else if (!(user_email_str.contains("@"))) {
                   SimpleMessageDialog simpleMessageDialog = new SimpleMessageDialog.Builder(RegisterActivity.this)
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
               else if (!(user_email_str.equals("")) && !(user_pw_str.equals(""))) {
                   // 해당 이메일과 비밀번호 회원 정보에서 조회 후 있으면 after main activity 로,
                   // 없으면 SimpleMessageDialog 띄우기기
               }
           }
       });



       back_btn= findViewById(R.id.Register_Ib_back);
       back_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent  = new Intent(RegisterActivity.this , b_MainActivity.class);
               startActivity(intent);
               finish();
           }
       });
//        company_or_not = findViewById(R.id.Register_fb_company_or_not);

//        company_or_not.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (IsCompany == false)
//                {
//                    IsCompany=true;
//                    company_or_not.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
//                }
//                else
//                {
//                    IsCompany=false;
//                }
//            }
//        });
    }
}
