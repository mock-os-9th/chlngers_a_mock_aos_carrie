package com.Carrie.challengersproject.src.Profile_Fragment;

import android.util.Log;

import com.Carrie.challengersproject.src.Profile_Fragment.interfaces.UserDeleteRetrofitInterface;
import com.Carrie.challengersproject.src.Profile_Fragment.interfaces.UserDeleteView;
import com.Carrie.challengersproject.src.Profile_Fragment.models.UserDeleteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class UserDeleteService {

    private final UserDeleteView mUserDeleteView;

    public UserDeleteService(UserDeleteView mUserDeleteView) {
        this.mUserDeleteView = mUserDeleteView;
    }

    public void getUserDeleteTest() {
        final UserDeleteRetrofitInterface userDeleteRetrofitInterface = getRetrofit().create(UserDeleteRetrofitInterface.class);
        userDeleteRetrofitInterface.getTestUserDelete().enqueue(new Callback<UserDeleteResponse>() {

            // 필요할 때 복붙해서 쓰세요요 - 비동기 호출 (물 흐르듯 위에서 아래로 흐르지 않음) - 비동기 오류를 겪을 수도 있다.
            @Override
            public void onResponse(Call<UserDeleteResponse> call, Response<UserDeleteResponse> response) {
                // 성공시에 돈다.
                final UserDeleteResponse userDeleteResponse = response.body();
                if (userDeleteResponse == null) {
                    // 이게 어디로 갈까? mainactivtiy에 있는 validateFailure로 간다.
                    mUserDeleteView.UserDeleteFailure(0);
                    return;
                }

                mUserDeleteView.UserDeleteSuccess(response.body().getCode());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<UserDeleteResponse> call, Throwable t) {
                mUserDeleteView.UserDeleteFailure(0);
                Log.e("유저 삭제 통신","자체가 안되고 있어요.");
            }
        });
    }
}
