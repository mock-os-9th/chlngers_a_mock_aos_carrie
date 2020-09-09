
package com.Carrie.challengersproject.src.Camera_Fragment.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MyCertifyResponse {

    @SerializedName("code")
    private int mCode;
    @SerializedName("isSuccess")
    private boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("possibleCertificationResult")
    private List<PossibleCertificationResult> mPossibleCertificationResult;

    public int getCode() {
        return mCode;
    }

    public boolean getIsSuccess() {
        return mIsSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    public List<PossibleCertificationResult> getPossibleCertificationResult() {
        return mPossibleCertificationResult;
    }

    public class PossibleCertificationResult {

        @SerializedName("achievementRate")
        private String mAchievementRate;
        @SerializedName("challengeId")
        private int mChallengeId;
        @SerializedName("period")
        private String mPeriod;
        @SerializedName("possibleTime")
        private String mPossibleTime;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("viewName")
        private String mViewName;

        public String getAchievementRate() {
            return mAchievementRate;
        }

        public int getChallengeId() {
            return mChallengeId;
        }

        public String getPeriod() {
            return mPeriod;
        }

        public String getPossibleTime() {
            return mPossibleTime;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getViewName() {
            return mViewName;
        }
    }
}
