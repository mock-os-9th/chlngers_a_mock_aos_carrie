
package com.Carrie.challengersproject.src.HowCertify.models;

import com.google.gson.annotations.SerializedName;

public class HowCertifyBody {

    @SerializedName("photoUrl")
    private String mPhotoUrl;

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public HowCertifyBody(String mPhotoUrl) {
        this.mPhotoUrl = mPhotoUrl;
    }
}
