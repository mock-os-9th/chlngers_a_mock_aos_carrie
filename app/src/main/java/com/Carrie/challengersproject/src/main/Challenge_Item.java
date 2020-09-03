package com.Carrie.challengersproject.src.main;

public class Challenge_Item {
    private String Img_url;
    private String Title;
    private String Star;
    private String Now_people;
    private String Date;
    private String Duration;
    private String Cycle;


    public Challenge_Item(String img_url, String title, String star, String now_people, String date, String duration, String cycle) {
        Img_url = img_url;
        Title = title;
        Star = star;
        Now_people = now_people;
        Date = date;
        Duration = duration;
        Cycle = cycle;
    }

    public String getImg_url() {
        return Img_url;
    }

    public void setImg_url(String img_url) {
        Img_url = img_url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStar() {
        return Star;
    }

    public void setStar(String star) {
        Star = star;
    }

    public String getNow_people() {
        return Now_people;
    }

    public void setNow_people(String now_people) {
        Now_people = now_people;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getCycle() {
        return Cycle;
    }

    public void setCycle(String cycle) {
        Cycle = cycle;
    }
}
