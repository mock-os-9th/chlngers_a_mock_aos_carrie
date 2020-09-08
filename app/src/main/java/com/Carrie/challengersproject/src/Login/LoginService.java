package com.Carrie.challengersproject.src.Login;

import com.Carrie.challengersproject.src.Login.interfaces.LoginActivityView;
import com.Carrie.challengersproject.src.Login.models.LoginResponse;
import com.Carrie.challengersproject.src.Login.interfaces.LoginRetrofitInterface;
import com.Carrie.challengersproject.src.Login.models.LoginBody;
import com.Carrie.challengersproject.src.Login.models.SnsLoginBody;
import com.Carrie.challengersproject.src.Login.models.SnsLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    public LoginService(LoginActivityView mLoginActivityView) {
        this.mLoginActivityView = mLoginActivityView;
    }

    // 로그인 통신
    void postLogIn(String email, String password ) {
        final LoginRetrofitInterface mainRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        mainRetrofitInterface.signInTest(new LoginBody(email,password)).enqueue(new Callback<LoginResponse>() {

            // 필요할 때 복붙해서 쓰세요요 - 비동기 호출 (물 흐르듯 위에서 아래로 흐르지 않음) - 비동기 오류를 겪을 수도 있다.
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                // 성공시에 돈다.
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    // 이게 어디로 갈까? mainactivtiy에 있는 validateFailure로 간다.
                    mLoginActivityView.LoginFailure(null);
                    return;
                }
                // 성공시 LigInSyccess 안에 이 jwt 토큰 넣어서 들어간다.
                mLoginActivityView.LogInSuccess(loginResponse.getJwt(), loginResponse.getUserInfo().getId());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.LoginFailure(null);
            }
        });
    }


    // sns 로그인 통신
    void postSnsLogIn(String accessToken , String which_sns) {
        final LoginRetrofitInterface mainRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        mainRetrofitInterface.snsSignInTest(new SnsLoginBody(accessToken),which_sns).enqueue(new Callback<SnsLoginResponse>() {

            // 필요할 때 복붙해서 쓰세요요 - 비동기 호출 (물 흐르듯 위에서 아래로 흐르지 않음) - 비동기 오류를 겪을 수도 있다.
            @Override
            public void onResponse(Call<SnsLoginResponse> call, Response<SnsLoginResponse> response) {
                // 성공시에 돈다.
                final SnsLoginResponse snsLoginResponse = response.body();
                if (snsLoginResponse == null) {
                    // 이게 어디로 갈까? mainactivtiy에 있는 validateFailure로 간다.
                    mLoginActivityView.SNSLoginFailure(null);
                    return;
                }
                // 성공시 LigInSyccess 안에 이 jwt 토큰 넣어서 들어간다.
                mLoginActivityView.SNSLogInSuccess(snsLoginResponse.getJwt(),snsLoginResponse.getSnsloginUserInfo().getId());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<SnsLoginResponse> call, Throwable t) {
                mLoginActivityView.SNSLoginFailure(null);
            }
        });
    }
}
