package com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.models;

import com.google.gson.annotations.SerializedName;

public class NicknameChangeBody {

    @SerializedName("nickname")
    private String nickname;

    public NicknameChangeBody(String nickname) {
        this.nickname = nickname;
    }
}
