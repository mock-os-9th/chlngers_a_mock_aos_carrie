package com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.interfaces;

import com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.models.NicknameChangeBody;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.models.NicknameChangeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;

public interface NicknameChangeRetrofitInterface {

    @PATCH("/user/nickname")
    Call<NicknameChangeResponse> GetNicknamgeChangeTest(@Body NicknameChangeBody params);

}
