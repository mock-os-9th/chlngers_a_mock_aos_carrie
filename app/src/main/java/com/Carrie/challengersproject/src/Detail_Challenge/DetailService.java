package com.Carrie.challengersproject.src.Detail_Challenge;

import android.util.Log;

import com.Carrie.challengersproject.src.Detail_Challenge.interfaces.DetailActivityView;
import com.Carrie.challengersproject.src.Detail_Challenge.interfaces.DetialRetrofitInterface;
import com.Carrie.challengersproject.src.Detail_Challenge.models.DetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class DetailService {

    private final DetailActivityView detailActivityView;

    public DetailService(DetailActivityView detailActivityView) {
        this.detailActivityView = detailActivityView;
    }

    public void getDetailResult(int challenge_id) {
        final DetialRetrofitInterface detialRetrofitInterface = getRetrofit().create(DetialRetrofitInterface.class);
        detialRetrofitInterface.getDetailChallengeTest(challenge_id).enqueue(new Callback<DetailResponse>() {

            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                // 성공시에 돈다.
                final DetailResponse detailResponse = response.body();
                if (detailResponse == null) {
                    detailActivityView.DetailFailure(null);
                    Log.d("디테일 실패","통신은 성공, 값은 null");
                    return;
                }
                detailActivityView.DetailSuccess(detailResponse);
                Log.d("디테일 성공","성공");
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                detailActivityView.DetailFailure(null);
                Log.d("디테일 실패","통신도 안됨");
            }
        });
    }
}
