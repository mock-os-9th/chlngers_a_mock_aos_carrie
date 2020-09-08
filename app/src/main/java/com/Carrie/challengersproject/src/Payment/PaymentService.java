package com.Carrie.challengersproject.src.Payment;

import android.util.Log;

import com.Carrie.challengersproject.src.Detail_Challenge.interfaces.DetialRetrofitInterface;
import com.Carrie.challengersproject.src.Detail_Challenge.models.DetailResponse;
import com.Carrie.challengersproject.src.Payment.interfaces.PaymentRetrofitInterface;
import com.Carrie.challengersproject.src.Payment.interfaces.PaymentView;
import com.Carrie.challengersproject.src.Payment.models.PaymentBody;
import com.Carrie.challengersproject.src.Payment.models.PaymentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class PaymentService {

    private final PaymentView paymentView;

    public PaymentService(PaymentView paymentView) {
        this.paymentView = paymentView;
    }

    void postPaymentResult(int challenge_id, int mMoney) {
        final PaymentRetrofitInterface paymentRetrofitInterface = getRetrofit().create(PaymentRetrofitInterface.class);
        paymentRetrofitInterface.getPaymentTest(challenge_id, new PaymentBody(mMoney)).enqueue(new Callback<PaymentResponse>() {

            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                final PaymentResponse paymentResponse = response.body();
                if (paymentResponse == null) {
                    paymentView.PaymentFailure("0");
                    Log.d("페이펀트 실패","통신은 성공, 값은 null");
                    return;
                }
                paymentView.PaymentSuccess(paymentResponse.getMessage());
                Log.d("페이펀트 통신  성공","성공");
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                paymentView.PaymentFailure("0");
                Log.d("페이펀트 실패","통신도 안됨");
            }
        });
    }
}
