package com.Carrie.challengersproject.src.ProfileNicknameChange_Fragment.models;

import com.google.gson.annotations.SerializedName;

public class NicknameChangeBody {

    @SerializedName("nickname")
    private String nickname;

    public NicknameChangeBody(String nickname) {
        this.nickname = nickname;
    }
}
