package com.Carrie.challengersproject.src.main.mypage.interfaces;

import com.Carrie.challengersproject.src.main.mypage.models.MypageResponse;

public interface MypageFragmentView {

    void MyPageGetSuccess(MypageResponse.MypageInfo mypageInfo);

    void MyPageGetFailure(String message);
}
