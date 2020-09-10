package com.Carrie.challengersproject.src.HowCertify;

import android.util.Log;

import com.Carrie.challengersproject.src.HowCertify.interfaces.HowCertifyRetrofitInterface;
import com.Carrie.challengersproject.src.HowCertify.interfaces.HowCertifyView;
import com.Carrie.challengersproject.src.HowCertify.models.HowCertifyBody;
import com.Carrie.challengersproject.src.HowCertify.models.HowCertifyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class HowCertifyService {

    private final HowCertifyView howCertifyView;

    public HowCertifyService(HowCertifyView howCertifyView) {
        this.howCertifyView = howCertifyView;
    }

    void postCertifyIn(int chllenge_id, String photo_url) {
        final HowCertifyRetrofitInterface howCertifyRetrofitInterface = getRetrofit().create(HowCertifyRetrofitInterface.class);
        howCertifyRetrofitInterface.GetChallengeCertifyTest(chllenge_id, new HowCertifyBody(photo_url)).enqueue(new Callback<HowCertifyResponse>() {

            @Override
            public void onResponse(Call<HowCertifyResponse> call, Response<HowCertifyResponse> response) {
                // 성공시에 돈다.
                final HowCertifyResponse howCertifyResponse = response.body();
                if (howCertifyResponse == null) {
                    howCertifyView.HowCertifyFailure("0");
                    Log.d("CERTYFY 통신 실패::","통신은 됨");
                    return;
                }

                howCertifyView.HowCertifySuccess(howCertifyResponse.getMessage());
                Log.d("CERTYFY 통신 성공::",howCertifyResponse.getMessage());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<HowCertifyResponse> call, Throwable t) {
                howCertifyView.HowCertifyFailure("0");
                Log.d("CERTYFY 통신 실패::","XXX");
            }
        });
    }
}
