package com.Carrie.challengersproject.src.login.interfaces;

import com.Carrie.challengersproject.src.login.models.LoginBody;
import com.Carrie.challengersproject.src.login.models.LoginResponse;
import com.Carrie.challengersproject.src.login.models.SnsLoginBody;
import com.Carrie.challengersproject.src.login.models.SnsLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginRetrofitInterface {
    //로그인
    @POST("/login")
    Call<LoginResponse> signInTest(@Body LoginBody params);

    //SNS 로그인
    @POST("/snsLogin/{snsName}")
    Call<SnsLoginResponse> snsSignInTest(
            @Body SnsLoginBody params,
            @Path("snsName") String sns_name
    );


}
