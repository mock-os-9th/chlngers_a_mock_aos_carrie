package com.Carrie.challengersproject.src.Mypage_Fragment;

import com.Carrie.challengersproject.src.Mypage_Fragment.interfaces.MypageFragmentView;
import com.Carrie.challengersproject.src.Mypage_Fragment.interfaces.MypageRetrofitInterface;
import com.Carrie.challengersproject.src.Mypage_Fragment.models.MypageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Carrie.challengersproject.ApplicationClass.getRetrofit;

public class MypageService {

    private final MypageFragmentView mMypageFragmentView;

    MypageService(final MypageFragmentView mMypageFragmentView) {
        this.mMypageFragmentView = mMypageFragmentView;
    }

    // 마이페이지 정보 통신 부분
    public void GetMyPageIn(int id) {
        final MypageRetrofitInterface mypageRetrofitInterface = getRetrofit().create(MypageRetrofitInterface.class);
        mypageRetrofitInterface.GetMypageTest(id).enqueue(new Callback<MypageResponse>() {

            // 필요할 때 복붙해서 쓰세요요 - 비동기 호출 (물 흐르듯 위에서 아래로 흐르지 않음) - 비동기 오류를 겪을 수도 있다.
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                // 성공시에 돈다.
                final MypageResponse mypageResponse = response.body();
                if (mypageResponse == null) {
                    // 이게 어디로 갈까? mainactivtiy에 있는 validateFailure로 간다.
                    mMypageFragmentView.MyPageGetFailure("실패-null");
                    return;
                }
                // 성공시 mypafeResponse 의 Info 정보 부분
                mMypageFragmentView.MyPageGetSuccess(mypageResponse.getMyPageInfo());
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                mMypageFragmentView.MyPageGetFailure("실패실패");
            }
        });
    }
}
