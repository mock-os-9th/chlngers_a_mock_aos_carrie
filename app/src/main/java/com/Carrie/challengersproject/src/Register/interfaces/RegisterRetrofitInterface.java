package com.Carrie.challengersproject.src.Register.interfaces;

import com.Carrie.challengersproject.src.Register.models.RegisterBody;
import com.Carrie.challengersproject.src.Register.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterRetrofitInterface {

    @POST("/user")
    Call<RegisterResponse> RegisterTest(@Body RegisterBody params);
}
