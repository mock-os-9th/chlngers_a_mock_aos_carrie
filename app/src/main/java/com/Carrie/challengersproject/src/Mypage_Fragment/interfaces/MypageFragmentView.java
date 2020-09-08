package com.Carrie.challengersproject.src.Mypage_Fragment.interfaces;

import com.Carrie.challengersproject.src.Mypage_Fragment.models.MypageResponse;

public interface MypageFragmentView {

    void MyPageGetSuccess(MypageResponse mypageResponse);

    void MyPageGetFailure(String message);
}
