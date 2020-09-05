package com.Carrie.challengersproject.src.login.models;

import com.google.gson.annotations.SerializedName;

public class SnsLoginResponse {

    public class SnsLoginUserInfo {
        @SerializedName("id")
        private int id;

        @SerializedName("email")
        private String email;

        @SerializedName("nickname")
        private String nickname;

        @SerializedName("profileImageUrl")
        private String profileImageUrl;

        @SerializedName("introduction")
        private String introduction;

        @SerializedName("phoneNumber")
        private String phoneNumber;

        @SerializedName("isDeleted")
        private String isDeleted;

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getNickname() {
            return nickname;
        }

        public String getProfileImageUrl() {
            return profileImageUrl;
        }

        public String getIntroduction() {
            return introduction;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getIsDeleted() {
            return isDeleted;
        }
    }

    @SerializedName("jwt")
    private String jwt;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userInfo")
    private SnsLoginUserInfo snsloginUserInfo;

    public String getJwt() {
        return jwt;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public SnsLoginUserInfo getSnsloginUserInfo() {
        return snsloginUserInfo;
    }
}
