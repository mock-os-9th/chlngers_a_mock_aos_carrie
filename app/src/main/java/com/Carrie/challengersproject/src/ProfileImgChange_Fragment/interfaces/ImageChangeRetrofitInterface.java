package com.Carrie.challengersproject.src.ProfileImgChange_Fragment.interfaces;

import com.Carrie.challengersproject.src.ProfileImgChange_Fragment.models.ImageChangeBody;
import com.Carrie.challengersproject.src.ProfileImgChange_Fragment.models.ImageChangeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;

public interface ImageChangeRetrofitInterface {


    @PATCH("/user/image")
    Call<ImageChangeResponse> ChangeImgTest(@Body ImageChangeBody params);

}
