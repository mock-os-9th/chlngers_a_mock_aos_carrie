package com.Carrie.challengersproject.src.ProfileImgChange_Fragment.models;

import com.google.gson.annotations.SerializedName;

public class ImageChangeBody {

    @SerializedName("profileImageUrl")
    private String profileImageUrl;

    public ImageChangeBody(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
