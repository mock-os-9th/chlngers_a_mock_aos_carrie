package com.Carrie.challengersproject.src.Mypage_Fragment;

import android.util.Log;

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
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                // 성공시에 돈다.
                final MypageResponse mypageResponse = response.body();
                if (mypageResponse == null) {
                    mMypageFragmentView.MyPageGetFailure("0");
                    Log.d("마이페이지 통신 실패"," 결과 값 null (통신은 함)");
                    return;
                }
                // 성공시 mypafeResponse 의 Info 정보 부분
                mMypageFragmentView.MyPageGetSuccess(mypageResponse);
                Log.d("마이페이지 통싱 성공","성공!");
            }

            // API 통신이 실패했을 때 오는 곳 - 비동기 호출
            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                mMypageFragmentView.MyPageGetFailure("실패실패");
                Log.d("마이페이지 통신 실패"," 통신 자체를 못함");
            }
        });
    }
}
