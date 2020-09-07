package com.Carrie.challengersproject.src.Register;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Carrie.challengersproject.BaseActivity;
import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.common.view.SimpleMessageDialog;
import com.Carrie.challengersproject.src.Login.LoginActivity;
import com.Carrie.challengersproject.src.Main.before_login.b_MainActivity;
import com.Carrie.challengersproject.src.Register.interfaces.RegisterActivityView;
import com.facebook.CallbackManager;
import com.kakao.usermgmt.LoginButton;

import static com.Carrie.challengersproject.ApplicationClass.X_ACCESS_TOKEN;
import static com.Carrie.challengersproject.ApplicationClass.sSharedPreferences;
import static com.Carrie.challengersproject.ApplicationClass.usertempToken;
import static com.Carrie.challengersproject.ApplicationClass.TAG;


public class RegisterActivity extends BaseActivity implements RegisterActivityView {

    final RegisterService registerService = new RegisterService(this);
    private EditText user_email;
    private EditText user_pw;
    private EditText confirm_user_pw;

    private TextView user_email_alert;
    private TextView user_pw_alert;
    private TextView user_pw_confirm_alert;

    private Button next;
    private Button facebook_login;
    private Button naver_login;
    private Button kakao_login;
    private ImageButton back_btn;

    private TextView email_login;

    boolean Email_OK = false;
    boolean PW_OK = false;
    boolean PW_CONFIRM_OK = false;

    private com.facebook.login.widget.LoginButton facebook_invisible_btn;
    private LoginButton kakao_invisible_btn;

    private LoginActivity.FacebookCallback mFacebookCallback;

    private CallbackManager mCallbackManager;

    static Context context;

    String user_email_input, user_pw_input, user_pw_confirm_input;

    // 회원가입 및 로그인 성공시 a_ㅡMainActivity로
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getViewObject();

        // alert_message
        final String email_form_alert = getString(R.string.login_wrong_email_form);
        final String login_form_ok_alert = getString(R.string.login_success_email);
        final String pw_length_alert = getString(R.string.login_password_length);
        final String pw_confirm_alert = getString(R.string.login_password_confirm);

        user_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                user_email_alert.setText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(user_email.getText().toString().contains("@"))) {
                    user_email_alert.setText(email_form_alert);
                    Email_OK = false;
                } else if (!(user_email.getText().toString().contains("."))) {
                    user_email_alert.setText(email_form_alert);
                    Email_OK = false;
                } else {
                    user_email_alert.setText(login_form_ok_alert);
                    Email_OK = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                user_email_input = s.toString();
            }
        });

        user_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 회원가입 PW 입력시 처리
                if (user_pw.getText().toString().length() < 8) {
                    user_pw_alert.setText(pw_length_alert);
                    PW_OK = false;
                } else {
                    user_pw_alert.setText("");
                    PW_OK = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                user_pw_input = s.toString();
            }
        });

        confirm_user_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 회원가입 PW 확인 처리
                if (!(user_pw.getText().toString().equals(confirm_user_pw.getText().toString()))) {
                    user_pw_confirm_alert.setText(pw_confirm_alert);
                    PW_CONFIRM_OK = false;
                } else {
                    user_pw_confirm_alert.setText("");
                    PW_CONFIRM_OK = true;
                }
                if ((Email_OK) && (PW_OK) && (PW_CONFIRM_OK)) {
                    // 버튼 색 활성화
                    Drawable drawable = getResources().getDrawable(R.drawable.primarycolor_selector);
                    next.setBackground(drawable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                user_pw_confirm_input = s.toString();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PW_CONFIRM_OK && Email_OK) {
                    String pw_confirm_alert = getString(R.string.login_password_confirm);
                    String confirm_text = getString(R.string.confirm);
                    SimpleMessageDialog simpleMessageDialog = new SimpleMessageDialog.Builder(RegisterActivity.this)
                            .setMessage(pw_confirm_alert)
                            .setButtonText(confirm_text)
                            .setOnClickListener(new SimpleMessageDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog) {
                                    dialog.dismiss();
                                }
                            }).build();
                    simpleMessageDialog.show();
                } else if (!Email_OK) {
                    String emial_wrong_alert = getString(R.string.login_wrong_email_form);
                    String confirm_text = getString(R.string.confirm);
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
                } else {
                    // 해당 정보들로 실제 유저 생성.
                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString(X_ACCESS_TOKEN,String.valueOf(usertempToken));
                    editor.commit();
                    tryPostRegister("사용자", user_email_input, user_pw_input, "닉네임");
                    usertempToken +=1;
                }
            }
        });

        kakao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카카오 로그인 처리
            }
        });

        email_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        back_btn = findViewById(R.id.Register_Ib_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, b_MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }




    private void getViewObject()
    {

        user_email = findViewById(R.id.Register_et_email);
        user_pw = findViewById(R.id.Register_et_pw);
        confirm_user_pw = findViewById(R.id.Register_et_pw_confirm);

        user_email_alert = findViewById(R.id.Register_tv_alert_email);
        user_pw_alert = findViewById(R.id.Register_tv_alert_pw);
        user_pw_confirm_alert = findViewById(R.id.Register_tv_alert_pw_confirm);

        next = findViewById(R.id.Register_btn_start);
        facebook_login = findViewById(R.id.Register_btn_facebook);
        naver_login = findViewById(R.id.Register_btn_naver);
        kakao_login = findViewById(R.id.Register_btn_kakao);
        email_login = findViewById(R.id.Register_tv_btn_emial);
    }


    private void tryPostRegister(String name, String email, String pw, String nickname)
    {
        showProgressDialog();
        registerService.getTest(name,email,pw,nickname);
    }


    @Override
    public void validateSuccess(String text) {
       // 통신 끝났을 때 이 text에
         hideProgressDialog();
        //회원가입 완료 토스트
        Toast.makeText(this,"회원가입이 완료 되었습니다.",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateFailure(String message) {
       // 통신 끝났을 때
        hideProgressDialog();
    }
}
