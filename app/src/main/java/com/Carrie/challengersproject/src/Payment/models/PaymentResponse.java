
package com.Carrie.challengersproject.src.Payment.models;

import com.google.gson.annotations.SerializedName;

public class PaymentResponse {

    @SerializedName("code")
    private int mCode;
    @SerializedName("isSuccess")
    private boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public boolean getIsSuccess() {
        return mIsSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

}
