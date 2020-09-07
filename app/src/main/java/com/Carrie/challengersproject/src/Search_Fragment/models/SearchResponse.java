
package com.Carrie.challengersproject.src.Search_Fragment.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("code")
    private int mCode;
    @SerializedName("cumulativeParticipation")
    private int mCumulativeParticipation;
    @SerializedName("cumulativeParticipationAmount")
    private String mCumulativeParticipationAmount;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("popular")
    private List<Popular> mPopular;
    @SerializedName("search")
    private List<Search> mSearch;

    public int getCode() {
        return mCode;
    }

    public int getCumulativeParticipation() {
        return mCumulativeParticipation;
    }

    public String getCumulativeParticipationAmount() {
        return mCumulativeParticipationAmount;
    }

    public Boolean getIsSuccess() {
        return mIsSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    public List<Popular> getPopular() {
        return mPopular;
    }

    public List<Search> getSearch() {
        return mSearch;
    }

    public class Challenge {

        @SerializedName("challengeId")
        private int mChallengeId;
        @SerializedName("ImageUrl")
        private String mImageUrl;
        @SerializedName("interest")
        private boolean mInterest;
        @SerializedName("participationCount")
        private int mParticipationCount;
        @SerializedName("period")
        private String mPeriod;
        @SerializedName("score")
        private double mScore;
        @SerializedName("subjectName")
        private String mSubjectName;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("viewName")
        private String mViewName;
        @SerializedName("week")
        private String mWeek;

        public int getChallengeId() {
            return mChallengeId;
        }

        public String getImageUrl() {
            return mImageUrl;
        }

        public boolean getInterest() {
            return mInterest;
        }

        public int getParticipationCount() {
            return mParticipationCount;
        }

        public String getPeriod() {
            return mPeriod;
        }

        public double getScore() {
            return mScore;
        }

        public String getSubjectName() {
            return mSubjectName;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getViewName() {
            return mViewName;
        }

        public String getWeek() {
            return mWeek;
        }
    }

    public class Challenges {

        @SerializedName("forties")
        private List<Forty> mForties;
        @SerializedName("tens")
        private List<Ten> mTens;
        @SerializedName("thirties")
        private List<Thirty> mThirties;
        @SerializedName("twenties")
        private List<Twenty> mTwenties;

        public List<Forty> getForties() {
            return mForties;
        }

        public List<Ten> getTens() {
            return mTens;
        }

        public List<Thirty> getThirties() {
            return mThirties;
        }

        public List<Twenty> getTwenties() {
            return mTwenties;
        }
    }

    public class Forty {

        @SerializedName("challengeId")
        private int mChallengeId;
        @SerializedName("ImageUrl")
        private String mImageUrl;
        @SerializedName("interest")
        private boolean mInterest;
        @SerializedName("participationCount")
        private int mParticipationCount;
        @SerializedName("period")
        private String mPeriod;
        @SerializedName("score")
        private double mScore;
        @SerializedName("subjectName")
        private String mSubjectName;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("viewName")
        private String mViewName;
        @SerializedName("week")
        private String mWeek;

        public int getChallengeId() {
            return mChallengeId;
        }

        public String getImageUrl() {
            return mImageUrl;
        }

        public boolean getInterest() {
            return mInterest;
        }

        public int getParticipationCount() {
            return mParticipationCount;
        }

        public String getPeriod() {
            return mPeriod;
        }

        public double getScore() {
            return mScore;
        }

        public String getSubjectName() {
            return mSubjectName;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getViewName() {
            return mViewName;
        }

        public String getWeek() {
            return mWeek;
        }
    }

    public class Popular {

        @SerializedName("challenges")
        private Challenges mChallenges;
        @SerializedName("gender")
        private String mGender;

        public Challenges getChallenges() {
            return mChallenges;
        }

        public String getGender() {
            return mGender;
        }
    }

    public class Search {

        @SerializedName("challenges")
        private List<Challenge> mChallenges;
        @SerializedName("subject")
        private String mSubject;

        public List<Challenge> getChallenges() {
            return mChallenges;
        }

        public String getSubject() {
            return mSubject;
        }
    }

    public class Ten {

        @SerializedName("challengeId")
        private int mChallengeId;
        @SerializedName("ImageUrl")
        private String mImageUrl;
        @SerializedName("interest")
        private boolean mInterest;
        @SerializedName("participationCount")
        private int mParticipationCount;
        @SerializedName("period")
        private String mPeriod;
        @SerializedName("score")
        private double mScore;
        @SerializedName("subjectName")
        private String mSubjectName;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("viewName")
        private String mViewName;
        @SerializedName("week")
        private String mWeek;

        public int getChallengeId() {
            return mChallengeId;
        }

        public String getImageUrl() {
            return mImageUrl;
        }

        public boolean getInterest() {
            return mInterest;
        }

        public int getParticipationCount() {
            return mParticipationCount;
        }

        public String getPeriod() {
            return mPeriod;
        }

        public double getScore() {
            return mScore;
        }

        public String getSubjectName() {
            return mSubjectName;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getViewName() {
            return mViewName;
        }

        public String getWeek() {
            return mWeek;
        }
    }

    public class Thirty {

        @SerializedName("challengeId")
        private int mChallengeId;
        @SerializedName("ImageUrl")
        private String mImageUrl;
        @SerializedName("interest")
        private boolean mInterest;
        @SerializedName("participationCount")
        private int mParticipationCount;
        @SerializedName("period")
        private String mPeriod;
        @SerializedName("score")
        private double mScore;
        @SerializedName("subjectName")
        private String mSubjectName;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("viewName")
        private String mViewName;
        @SerializedName("week")
        private String mWeek;

        public int getChallengeId() {
            return mChallengeId;
        }

        public String getImageUrl() {
            return mImageUrl;
        }

        public boolean getInterest() {
            return mInterest;
        }

        public int getParticipationCount() {
            return mParticipationCount;
        }

        public String getPeriod() {
            return mPeriod;
        }

        public double getScore() {
            return mScore;
        }

        public String getSubjectName() {
            return mSubjectName;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getViewName() {
            return mViewName;
        }

        public String getWeek() {
            return mWeek;
        }
    }

    public class Twenty {

        @SerializedName("challengeId")
        private int mChallengeId;
        @SerializedName("ImageUrl")
        private String mImageUrl;
        @SerializedName("interest")
        private boolean mInterest;
        @SerializedName("participationCount")
        private int mParticipationCount;
        @SerializedName("period")
        private String mPeriod;
        @SerializedName("score")
        private double mScore;
        @SerializedName("subjectName")
        private String mSubjectName;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("viewName")
        private String mViewName;
        @SerializedName("week")
        private String mWeek;

        public int getChallengeId() {
            return mChallengeId;
        }

        public String getImageUrl() {
            return mImageUrl;
        }

        public boolean getInterest() {
            return mInterest;
        }

        public int getParticipationCount() {
            return mParticipationCount;
        }

        public String getPeriod() {
            return mPeriod;
        }

        public double getScore() {
            return mScore;
        }

        public String getSubjectName() {
            return mSubjectName;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getViewName() {
            return mViewName;
        }

        public String getWeek() {
            return mWeek;
        }
    }
    }
