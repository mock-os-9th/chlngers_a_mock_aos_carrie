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

    @Override
    public void onSessionOpened() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {

            @Override
            public void onFailure(ErrorResult errorResult) {
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
                Toast.makeText(context, R.string.session_error,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(MeV2Response result) {
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
