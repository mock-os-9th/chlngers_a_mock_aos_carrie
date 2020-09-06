package com.Carrie.challengersproject.src.main.mypage.setting.profile.introduce.models;

import com.google.gson.annotations.SerializedName;

public class IntroduceChangeResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
