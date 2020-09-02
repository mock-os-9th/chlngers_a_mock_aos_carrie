package com.Carrie.challengersproject.src.login;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.a_MainActivity;
import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

public class KakaoCallback extends AppCompatActivity implements ISessionCallback {

    Context context;

    public KakaoCallback(Context context) {
        this.context = context;
    }

    /*
    onSessionOpened() : 로그인 세션 열렸을 때.
    onSewwionOpenFailed() : 로그인 세션 정상적으로 열리지 않았을 때.
    * */
    @Override
    public void onSessionOpened() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {

            @Override
            public void onFailure(ErrorResult errorResult) {
                // 로그인 실패시 (네트워크 문제 포함)
                int result = errorResult.getErrorCode();
                if(result == ApiErrorCode.CLIENT_ERROR_CODE)
                {
                    Toast.makeText(context, R.string.network_error,Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(context,R.string.login_error,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                // 로그인 세샨 도중 비정상적 이유로 닫힐때
                Toast.makeText(context, R.string.session_error,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                // 로그인 성공 시 -> 기존 콘텍스에서 로그인 후의 액티비티로 간다.
                Intent intent = new Intent(context, a_MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Toast.makeText(context,R.string.login_error+exception.toString(),Toast.LENGTH_SHORT).show();
    }
}
