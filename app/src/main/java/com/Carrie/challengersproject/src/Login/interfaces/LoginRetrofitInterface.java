package com.Carrie.challengersproject.src.Login.interfaces;

import com.Carrie.challengersproject.src.Login.models.LoginBody;
import com.Carrie.challengersproject.src.Login.models.LoginResponse;
import com.Carrie.challengersproject.src.Login.models.SnsLoginBody;
import com.Carrie.challengersproject.src.Login.models.SnsLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginRetrofitInterface {
    //로그인
    @POST("/login")
    Call<LoginResponse> signInTest(@Body LoginBody params);

    //SNS 로그인
    @POST("/login/{snsName}")
    Call<SnsLoginResponse> snsSignInTest(
            @Body SnsLoginBody params,
            @Path("snsName") String sns_name
    );


}
