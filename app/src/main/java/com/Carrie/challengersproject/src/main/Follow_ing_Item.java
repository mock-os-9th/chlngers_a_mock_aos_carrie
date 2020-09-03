package com.Carrie.challengersproject.src.main;

public class Follow_ing_Item {
    private String profile_img_url;
    private String profile_nick_name;

    public Follow_ing_Item(String profile_img_url, String profile_nick_name) {
        this.profile_img_url = profile_img_url;
        this.profile_nick_name = profile_nick_name;
    }

    public String getProfile_img_url() {
        return profile_img_url;
    }

    public void setProfile_img_url(String profile_img_url) {
        this.profile_img_url = profile_img_url;
    }

    public String getProfile_nick_name() {
        return profile_nick_name;
    }

    public void setProfile_nick_name(String profile_nick_name) {
        this.profile_nick_name = profile_nick_name;
    }
}
