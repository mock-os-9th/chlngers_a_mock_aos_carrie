
package com.Carrie.challengersproject.src.Payment.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PaymentBody {

    @SerializedName("money")
    private int mMoney;

    public PaymentBody(int mMoney) {
        this.mMoney = mMoney;
    }

    public int getMoney() {
        return mMoney;
    }

}
