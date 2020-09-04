package com.Carrie.challengersproject.src.register.interfaces;

import com.Carrie.challengersproject.src.register.models.RegisterBody;
import com.Carrie.challengersproject.src.register.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterRetrofitInterface {

    @POST("/user")
    Call<RegisterResponse> RegisterTest(@Body RegisterBody params);
}
