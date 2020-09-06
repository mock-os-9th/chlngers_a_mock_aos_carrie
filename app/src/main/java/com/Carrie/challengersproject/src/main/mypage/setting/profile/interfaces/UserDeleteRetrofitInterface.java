package com.Carrie.challengersproject.src.main.mypage.setting.profile.interfaces;

import com.Carrie.challengersproject.src.main.mypage.setting.profile.models.UserDeleteResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface UserDeleteRetrofitInterface {

    @DELETE("/user")
    Call<UserDeleteResponse> getTestUserDelete(
    );
}
