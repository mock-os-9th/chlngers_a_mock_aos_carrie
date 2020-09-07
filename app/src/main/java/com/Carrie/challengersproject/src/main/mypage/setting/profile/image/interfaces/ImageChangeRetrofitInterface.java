package com.Carrie.challengersproject.src.main.mypage.setting.profile.image.interfaces;

import com.Carrie.challengersproject.src.main.mypage.setting.profile.image.models.ImageChangeBody;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.image.models.ImageChangeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ImageChangeRetrofitInterface {


    @PATCH("user/profileImageUrl")
    Call<ImageChangeResponse> ChangeImgTest(@Body ImageChangeBody params);

}
