package com.Carrie.challengersproject.src.Camera_Fragment;

import android.util.Log;

import com.Carrie.challengersproject.src.Camera_Fragment.interfaces.MyCertifyRetrofitInterface;
import com.Carrie.challengersproject.src.Camera_Fragment.interfaces.MyCertifyView;
import com.Carrie.challengersproject.src.Camera_Fragment.models.MyCertifyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class MyCertifyService {

    private final MyCertifyView myCertifyView;

    public MyCertifyService(MyCertifyView myCertifyView) {
        this.myCertifyView = myCertifyView;
    }

    void getCertifyTest() {
        final MyCertifyRetrofitInterface myCertifyRetrofitInterface = getRetrofit().create(MyCertifyRetrofitInterface.class);
        myCertifyRetrofitInterface.getMyCertifyTest().enqueue(new Callback<MyCertifyResponse>() {

            @Override
            public void onResponse(Call<MyCertifyResponse> call, Response<MyCertifyResponse> response) {

                final MyCertifyResponse myCertifyResponse = response.body();
                if (myCertifyResponse == null) {
                    myCertifyView.MyCertifyFailure("0");
                    Log.d("내 인증 챌린지 통신", "통신은 되었으나, 들어 오는 값이 NULL");
                    return;
                }

                myCertifyView.MyCertifySuccess(myCertifyResponse);
                Log.d("내 인증 챌린지 통신", "성공");
            }

            // API 통신이 실패했을 때 오는 곳
            @Override
            public void onFailure(Call<MyCertifyResponse> call, Throwable t) {
                myCertifyView.MyCertifyFailure("0");
                Log.d("내 인증 챌린지 통신", "통신은 자체 실패");
            }
        });
    }
}
