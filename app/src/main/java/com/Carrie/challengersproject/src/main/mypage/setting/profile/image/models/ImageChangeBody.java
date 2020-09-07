package com.Carrie.challengersproject.src.main.mypage.setting.profile.image.models;

import com.google.gson.annotations.SerializedName;

public class ImageChangeBody {

    @SerializedName("profileImageUrl")
    private String profileImageUrl;

    public ImageChangeBody(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
