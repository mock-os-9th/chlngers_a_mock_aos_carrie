package com.Carrie.challengersproject.src.login.interfaces;

import com.Carrie.challengersproject.src.login.models.LoginResponse;

public interface LoginActivityView {

//    void validateSuccess(String text);

    void LoginFailure(String message);

    //로그인 시 jwt 토큰 값 넘겨주는 거
    void LogInSuccess(String jwt);
}
