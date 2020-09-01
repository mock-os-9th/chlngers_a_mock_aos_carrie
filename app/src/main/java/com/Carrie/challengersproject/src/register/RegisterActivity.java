package com.Carrie.challengersproject.src.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Carrie.challengersproject.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class RegisterActivity extends AppCompatActivity {

    private EditText user_email;
    private EditText user_pw;
    private EditText confirm_user_pw;

    private Button next;
    private Button facebook_login;
    private Button naver_login;
    private Button kakao_login;

    private TextView email_login;
    private ExtendedFloatingActionButton company_or_not;

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
        company_or_not = findViewById(R.id.Register_fb_company_or_not);


    }
}
