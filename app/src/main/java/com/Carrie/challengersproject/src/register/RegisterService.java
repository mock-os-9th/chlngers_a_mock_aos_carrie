package com.Carrie.challengersproject.src.register;

import com.Carrie.challengersproject.src.register.interfaces.RegisterActivityView;
import com.Carrie.challengersproject.src.register.interfaces.RegisterRetrofitInterface;
import com.Carrie.challengersproject.src.register.models.RegisterBody;
import com.Carrie.challengersproject.src.register.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;


public class RegisterService {
    private final RegisterActivityView mRegisterActivityView;

    public RegisterService(RegisterActivityView mRegisterActivityView) {
        this.mRegisterActivityView = mRegisterActivityView;
    }

    // 진짜 서버 통신이 들어가는 부분

    void getTest(String name, String email, String password, String nickname) {
        final RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);
        registerRetrofitInterface.RegisterTest(new RegisterBody(name, email,password, nickname )).enqueue(new Callback<RegisterResponse>() {

            // 필요할 때 복붙해서 쓰세요요 - 비동기 호출 (물 흐르듯 위에서 아래로 흐르지 않음) - 비동기 오류를 겪을 수도 있다.
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                // 성공시에 돈다.
                final RegisterResponse registerResponse = response.body();
                if (registerResponse == null) {
                    // 이게 어디로 갈까? mainactivtiy에 있는 validateFailure로 간다.
                    mRegisterActivityView.validateFailure(null);
                    return;
                }

                mRegisterActivityView.validateSuccess(registerResponse.getMessage());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                mRegisterActivityView.validateFailure(null);
            }
        });
    }
}
