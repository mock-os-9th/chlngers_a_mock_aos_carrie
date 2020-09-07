package com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment.interfaces;

import com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment.models.IntroduceChangeBody;
import com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment.models.IntroduceChangeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;

public interface IntroduceChangeRetrofitInterface {
    @PATCH("/user/introduction")
    Call<IntroduceChangeResponse> PatchIntroduceChangeTest(@Body IntroduceChangeBody params);
}
