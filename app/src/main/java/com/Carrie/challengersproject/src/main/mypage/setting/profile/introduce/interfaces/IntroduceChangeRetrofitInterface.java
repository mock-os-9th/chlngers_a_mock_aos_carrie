package com.Carrie.challengersproject.src.main.mypage.setting.profile.introduce.interfaces;

import com.Carrie.challengersproject.src.main.mypage.setting.profile.introduce.models.IntroduceChangeBody;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.introduce.models.IntroduceChangeResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface IntroduceChangeRetrofitInterface {
    @PATCH("/user/introduction")
    Call<IntroduceChangeResponse> PatchIntroduceChangeTest(@Body IntroduceChangeBody params);
}
