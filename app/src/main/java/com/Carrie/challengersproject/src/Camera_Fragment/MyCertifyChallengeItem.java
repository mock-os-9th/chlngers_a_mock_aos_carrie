package com.Carrie.challengersproject.src.Camera_Fragment;

public class MyCertifyChallengeItem {

    String title;
    String period;
    String viewName;
    String possibleTime;
    String achivementRate;
    boolean ISCertiFied;

    public MyCertifyChallengeItem(String title, String period, String viewName, String possibleTime, String achivementRate,boolean ISCertiFied) {
        this.title = title;
        this.period = period;
        this.viewName = viewName;
        this.possibleTime = possibleTime;
        this.achivementRate = achivementRate;
        this.ISCertiFied = ISCertiFied;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getPossibleTime() {
        return possibleTime;
    }

    public void setPossibleTime(String possibleTime) {
        this.possibleTime = possibleTime;
    }

    public String getAchivementRate() {
        return achivementRate;
    }

    public void setAchivementRate(String achivementRate) {
        this.achivementRate = achivementRate;
    }

    public boolean getISCertiFied() {
        return ISCertiFied;
    }

    public void setISCertiFied(boolean ISCertiFied) {
        this.ISCertiFied = ISCertiFied;
    }
}
