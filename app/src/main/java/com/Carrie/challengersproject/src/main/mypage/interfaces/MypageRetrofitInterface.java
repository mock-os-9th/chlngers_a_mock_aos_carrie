package com.Carrie.challengersproject.src.main.mypage.interfaces;

import com.Carrie.challengersproject.src.main.mypage.models.MypageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MypageRetrofitInterface {

    @GET("/user/{userId}/mypage")
    Call<MypageResponse> GetMypageTest
            (@Path("userId") int userId);
}
