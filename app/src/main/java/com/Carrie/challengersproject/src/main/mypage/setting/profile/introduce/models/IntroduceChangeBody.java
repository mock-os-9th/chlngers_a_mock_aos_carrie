package com.Carrie.challengersproject.src.main.mypage.setting.profile.introduce.models;

import com.google.gson.annotations.SerializedName;

public class IntroduceChangeBody {
    @SerializedName("introduction")
    private String introduction;

    public IntroduceChangeBody(String introduction) {
        this.introduction = introduction;
    }
}
