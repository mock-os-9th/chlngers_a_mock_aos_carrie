package com.Carrie.challengersproject.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.common.view.SimpleMessageDialog;
import com.Carrie.challengersproject.src.main.b_MainActivity;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private ImageButton back_btn;
    private EditText user_email;
    private EditText user_pw;
    private Button start_btn;
    private Button facebook_btn;
    private Button naver_btn;
    private Button kakao_btn;
    private LoginButton kakao_invisible_btn;
    private OAuthLoginButton naver_invisible_Button;

    private FacebookCallback mFacebookCallback;
    private KakaoCallback kakaoCallback;
    private CallbackManager mCallbackManager;

    // naver - clinet 정보
    private static String OAUTH_CLIENT_ID = "FELJOtj1y_Pu0ZmPhtbc";
    private static String OAUTH_CLIENT_SECRET = "qYYKACWzSu";
    private static String OAUTH_CLIENT_NAME = "CARRIE";
    private static OAuthLogin naverLoginInstance;
    static Context context;
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
        kakao_invisible_btn = findViewById(R.id.login_btn_kakao_gone);


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
                if (user_email_str.equals("")) {
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
                else if (!(user_email_str.contains("@"))) {
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
                else if (!(user_email_str.equals("")) && !(user_pw_str.equals(""))) {
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
                Intent intent = new Intent(LoginActivity.this, b_MainActivity.class);
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
                        Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(mCallbackManager, mFacebookCallback);
            }
        });

        // 카카오 로그인
        kakaoCallback = new KakaoCallback(getApplicationContext());
        kakao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kakao_invisible_btn.callOnClick();
                Session.getCurrentSession().addCallback(kakaoCallback);
                Session.getCurrentSession().checkAndImplicitOpen();
            }
        });

        //네이버 로그인
//        naver_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //네이버 로그인
//                new RequestApiTask().execute();
//                init();
//                init_View();
//            }
//        });

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

    //초기화
    private void init()
    {
        context = this;
        naverLoginInstance = OAuthLogin.getInstance();
        naverLoginInstance.init(this,OAUTH_CLIENT_ID,OAUTH_CLIENT_SECRET,OAUTH_CLIENT_NAME);
    }
    //변수 붙이기
    private void init_View()
    {
        naver_invisible_Button = (OAuthLoginButton)findViewById(R.id.login_btn_naver_oauth);
        //로그인 핸들러
        OAuthLoginHandler naverLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) { // 로그인 성공
                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show();
                } else {
                    // 로그인 실패
                    String errorCode = naverLoginInstance.getLastErrorCode(context).getCode();
                    String errorDesc = naverLoginInstance.getLastErrorDesc(context);
                    Toast.makeText(context, "errorCode:" + errorCode +
                            ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                }
            }
        };
        naver_invisible_Button.setOAuthLoginHandler(naverLoginHandler);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(kakaoCallback);
    }


    private class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {//작업이 실행되기 전에 먼저 실행.
        }

        @Override
        protected String doInBackground(Void... params) {//네트워크에 연결하는 과정이 있으므로 다른 스레드에서 실행되어야 한다.
            String url = "https://openapi.naver.com/v1/nid/me";
            String at = naverLoginInstance.getAccessToken(context);
            return naverLoginInstance.requestApi(context, at, url);//url, 토큰을 넘겨서 값을 받아온다.json 타입으로 받아진다.
        }

        protected void onPostExecute(String content) {//doInBackground 에서 리턴된 값이 여기로 들어온다.
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONObject response = jsonObject.getJSONObject("response");
                String email = response.getString("email");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
