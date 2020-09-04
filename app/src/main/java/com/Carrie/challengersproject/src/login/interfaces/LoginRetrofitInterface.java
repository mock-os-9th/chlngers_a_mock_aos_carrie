package com.Carrie.challengersproject.src.login.interfaces;

import com.Carrie.challengersproject.src.login.models.LoginBody;
import com.Carrie.challengersproject.src.login.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    //로그인
    @POST("/login")
    Call<LoginResponse> signInTest(@Body LoginBody params);
}
