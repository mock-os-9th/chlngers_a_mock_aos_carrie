package com.Carrie.challengersproject.src.Detail_Challenge.interfaces;

import com.Carrie.challengersproject.src.Detail_Challenge.models.DetailResponse;
import com.Carrie.challengersproject.src.Search_Fragment.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetialRetrofitInterface {

    @GET("/challenge/{challengeId}")
    Call<DetailResponse> getDetailChallengeTest
            (@Path("challengeId") int param);
}
