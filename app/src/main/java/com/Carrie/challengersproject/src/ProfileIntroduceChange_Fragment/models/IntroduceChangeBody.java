package com.Carrie.challengersproject.src.ProfileIntroduceChange_Fragment.models;

import com.google.gson.annotations.SerializedName;

public class IntroduceChangeBody {
    @SerializedName("introduction")
    private String introduction;

    public IntroduceChangeBody(String introduction) {
        this.introduction = introduction;
    }
}
