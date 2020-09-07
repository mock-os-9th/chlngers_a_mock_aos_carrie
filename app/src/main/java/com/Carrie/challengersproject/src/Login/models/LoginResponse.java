
package com.Carrie.challengersproject.src.Login.models;

import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("jwt")
    private String mJwt;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("userInfo")
    private UserInfo mUserInfo;

    public Long getCode() {
        return mCode;
    }

    public Boolean getIsSuccess() {
        return mIsSuccess;
    }

    public String getJwt() {
        return mJwt;
    }

    public String getMessage() {
        return mMessage;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public class UserInfo {

        @SerializedName("email")
        private String mEmail;
        @SerializedName("id")
        private int mId;
        @SerializedName("introduction")
        private String mIntroduction;
        @SerializedName("isDeleted")
        private String mIsDeleted;
        @SerializedName("nickname")
        private String mNickname;
        @SerializedName("phoneNumber")
        private String mPhoneNumber;
        @SerializedName("profileImageUrl")
        private String mProfileImageUrl;

        public String getEmail() {
            return mEmail;
        }

        public int getId() {
            return mId;
        }

        public String getIntroduction() {
            return mIntroduction;
        }

        public String getIsDeleted() {
            return mIsDeleted;
        }

        public String getNickname() {
            return mNickname;
        }

        public String getPhoneNumber() {
            return mPhoneNumber;
        }

        public String getProfileImageUrl() {
            return mProfileImageUrl;
        }


    }

}
