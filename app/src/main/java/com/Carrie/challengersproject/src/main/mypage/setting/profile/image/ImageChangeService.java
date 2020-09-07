package com.Carrie.challengersproject.src.main.mypage.setting.profile.image;

import android.media.Image;

import com.Carrie.challengersproject.src.main.mypage.setting.profile.image.interfaces.ImageChangeRetrofitInterface;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.image.interfaces.ImageChangeView;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.image.models.ImageChangeBody;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.image.models.ImageChangeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class ImageChangeService {

    private final ImageChangeView imageChangeView;

    public ImageChangeService(ImageChangeView imageChangeView) {
        this.imageChangeView = imageChangeView;
    }

    void patchImageChangeIn(String url) {
        final ImageChangeRetrofitInterface imageChangeRetrofitInterface = getRetrofit().create(ImageChangeRetrofitInterface.class);
        imageChangeRetrofitInterface.ChangeImgTest(new ImageChangeBody(url)).enqueue(new Callback<ImageChangeResponse>() {

            // 필요할 때 복붙해서 쓰세요요 - 비동기 호출 (물 흐르듯 위에서 아래로 흐르지 않음) - 비동기 오류를 겪을 수도 있다.
            @Override
            public void onResponse(Call<ImageChangeResponse> call, Response<ImageChangeResponse> response) {
                // 성공시에 돈다.
                final ImageChangeResponse imageChangeResponse = response.body();

                if (imageChangeResponse == null) {
                    // 이게 어디로 갈까? mainactivtiy에 있는 validateFailure로 간다.
                    imageChangeView.ImageChangeFailure(response.body().getMessage());
                    return;
                }

                imageChangeView.ImageChangeSuccess(response.body().getMessage());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<ImageChangeResponse> call, Throwable t) {
                imageChangeView.ImageChangeFailure("fail_fail");
            }
        });
    }
}
