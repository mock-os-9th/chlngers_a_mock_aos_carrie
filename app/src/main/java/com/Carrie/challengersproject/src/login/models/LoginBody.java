package com.Carrie.challengersproject.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    // 로그인 에 필요한 body

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
