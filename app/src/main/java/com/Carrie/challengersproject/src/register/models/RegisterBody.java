package com.Carrie.challengersproject.src.register.models;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("nickname")
    private String nickname;

    public RegisterBody(String name, String email, String password, String nickname) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
