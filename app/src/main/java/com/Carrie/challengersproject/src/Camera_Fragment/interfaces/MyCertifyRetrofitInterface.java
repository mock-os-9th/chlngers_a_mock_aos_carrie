package com.Carrie.challengersproject.src.Camera_Fragment.interfaces;

import com.Carrie.challengersproject.src.Camera_Fragment.models.MyCertifyResponse;
import com.Carrie.challengersproject.src.Search_Fragment.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyCertifyRetrofitInterface {

    @GET("/certifications")
    Call<MyCertifyResponse> getMyCertifyTest();
}
