package com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname;

import com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.interfaces.NicknameChangeRetrofitInterface;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.interfaces.NicknameChangeView;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.models.NicknameChangeBody;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.models.NicknameChangeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class ChangeNicknameService {

    private final NicknameChangeView nicknameChangeView;

    public ChangeNicknameService(NicknameChangeView nicknameChangeView) {
        this.nicknameChangeView = nicknameChangeView;
    }

    void PatchChangeNickname(String nick_name) {
        final NicknameChangeRetrofitInterface nicknameChangeRetrofitInterface = getRetrofit().create(NicknameChangeRetrofitInterface.class);
        nicknameChangeRetrofitInterface.GetNicknamgeChangeTest(new NicknameChangeBody(nick_name)).enqueue(new Callback<NicknameChangeResponse>() {

            // 필요할 때 복붙해서 쓰세요요 - 비동기 호출 (물 흐르듯 위에서 아래로 흐르지 않음) - 비동기 오류를 겪을 수도 있다.
            @Override
            public void onResponse(Call<NicknameChangeResponse> call, Response<NicknameChangeResponse> response) {
                // 성공시에 돈다.
                final NicknameChangeResponse signInResponse = response.body();
                if (signInResponse == null) {
                    // 이게 어디로 갈까? mainactivtiy에 있는 validateFailure로 간다.
                    nicknameChangeView.NicknameChangeFailure("0");
                    return;
                }

                nicknameChangeView.NicknameChangeSuccess(signInResponse.getMessage());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<NicknameChangeResponse> call, Throwable t) {
                nicknameChangeView.NicknameChangeFailure("00");
            }
        });
    }
}
