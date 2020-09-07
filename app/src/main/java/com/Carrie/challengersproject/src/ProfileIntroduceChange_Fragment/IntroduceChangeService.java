package com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment;

import com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment.interfaces.IntroduceChangeRetrofitInterface;
import com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment.interfaces.IntroduceChangeView;
import com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment.models.IntroduceChangeBody;
import com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment.models.IntroduceChangeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class IntroduceChangeService {
    private final IntroduceChangeView introduceChangeView;

    public IntroduceChangeService(IntroduceChangeView introduceChangeView) {
        this.introduceChangeView = introduceChangeView;
    }

    void PatchIntroduceIn(String introduce ) {
        final IntroduceChangeRetrofitInterface introduceChangeRetrofitInterface = getRetrofit().create(IntroduceChangeRetrofitInterface.class);
        introduceChangeRetrofitInterface.PatchIntroduceChangeTest(new IntroduceChangeBody(introduce)).enqueue(new Callback<IntroduceChangeResponse>() {

            // 필요할 때 복붙해서 쓰세요요 - 비동기 호출 (물 흐르듯 위에서 아래로 흐르지 않음) - 비동기 오류를 겪을 수도 있다.
            @Override
            public void onResponse(Call<IntroduceChangeResponse> call, Response<IntroduceChangeResponse> response) {
                // 성공시에 돈다.
                final IntroduceChangeResponse introduceChangeResponse = response.body();
                if (introduceChangeResponse == null) {
                    // 이게 어디로 갈까? mainactivtiy에 있는 validateFailure로 간다.
                    introduceChangeView.IntroduceChangeFailure("0");
                    return;
                }

                introduceChangeView.IntroduceChangeSuccess(response.body().getMessage());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<IntroduceChangeResponse> call, Throwable t) {
                introduceChangeView.IntroduceChangeFailure("00");
            }
        });
    }
}
