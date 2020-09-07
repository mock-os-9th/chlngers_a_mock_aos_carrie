package com.Carrie.challengersproject.src.ProfileNicknameChange_Fragment.interfaces;

import com.Carrie.challengersproject.src.ProfileNicknameChange_Fragment.models.NicknameChangeBody;
import com.Carrie.challengersproject.src.ProfileNicknameChange_Fragment.models.NicknameChangeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;

public interface NicknameChangeRetrofitInterface {

    @PATCH("/user/nickname")
    Call<NicknameChangeResponse> GetNicknamgeChangeTest(@Body NicknameChangeBody params);

}
