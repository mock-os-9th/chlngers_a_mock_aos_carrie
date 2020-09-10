package com.Carrie.challengersproject.src.HowCertify.interfaces;

import com.Carrie.challengersproject.src.HowCertify.models.HowCertifyBody;
import com.Carrie.challengersproject.src.HowCertify.models.HowCertifyResponse;
import com.Carrie.challengersproject.src.Mypage_Fragment.models.MypageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HowCertifyRetrofitInterface {

    @POST("/challenge/{challengeId}/certification")
    Call<HowCertifyResponse> GetChallengeCertifyTest(
            @Path("challengeId") int id,
            @Body HowCertifyBody body
    );
}
