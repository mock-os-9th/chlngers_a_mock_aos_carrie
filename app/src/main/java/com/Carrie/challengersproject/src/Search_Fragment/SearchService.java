package com.Carrie.challengersproject.src.Search_Fragment;

import android.util.Log;

import com.Carrie.challengersproject.src.Search_Fragment.interfaces.SearchActivityView;
import com.Carrie.challengersproject.src.Search_Fragment.interfaces.SearchRetrofitInterface;
import com.Carrie.challengersproject.src.Search_Fragment.models.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class SearchService {
    private final SearchActivityView searchActivityView;

    public SearchService(SearchActivityView searchActivityView) {
        this.searchActivityView = searchActivityView;
    }

    public void getSearchTest() {
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        searchRetrofitInterface.getSearchTest().enqueue(new Callback<SearchResponse>() {

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                final SearchResponse searchResponse = response.body();
                if (searchResponse == null) {
                    searchActivityView.SearchFailure(null);
                    Log.d("탐색 뷰","null 통신은 됨.");
                    return;
                }
                searchActivityView.SearchSuccess(searchResponse);
            }
            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                searchActivityView.SearchFailure(null);
                Log.d("SearchActivityFail","FailFail");
            }
        });
    }
}
