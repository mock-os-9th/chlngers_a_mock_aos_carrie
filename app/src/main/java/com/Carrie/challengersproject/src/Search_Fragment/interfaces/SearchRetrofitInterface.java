package com.Carrie.challengersproject.src.Search_Fragment.interfaces;

import com.Carrie.challengersproject.src.Search_Fragment.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchRetrofitInterface {

    @GET("/challengeSearch")
    Call<SearchResponse> getSearchTest();
}
