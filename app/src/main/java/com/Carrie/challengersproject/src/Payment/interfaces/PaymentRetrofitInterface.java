package com.Carrie.challengersproject.src.Payment.interfaces;

import com.Carrie.challengersproject.src.Payment.models.PaymentBody;
import com.Carrie.challengersproject.src.Payment.models.PaymentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaymentRetrofitInterface {

    @POST("/challenge/{challengeId}/participation")
    Call<PaymentResponse> getPaymentTest
            (@Path("challengeId") int challenge_id,
             @Body PaymentBody payment_body);
}
