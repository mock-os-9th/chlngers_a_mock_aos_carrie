package com.Carrie.challengersproject.src.Login.models;

import com.google.gson.annotations.SerializedName;

public class SnsLoginBody {

    @SerializedName("accessToken")
    private String accessToken;

    public SnsLoginBody(String accessToken) {
        this.accessToken = accessToken;
    }
}
