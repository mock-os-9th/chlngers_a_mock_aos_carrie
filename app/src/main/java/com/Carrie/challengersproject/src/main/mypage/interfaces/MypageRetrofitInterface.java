package com.Carrie.challengersproject.src.main.mypage.interfaces;

import com.Carrie.challengersproject.src.main.mypage.models.MypageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MypageRetrofitInterface {

    // /user/1/mypage로 해도 안됬음

    @GET("/user/{userId}/mypage")
    Call<MypageResponse> GetMypageTest
            (@Path("userId") int userId);
}
