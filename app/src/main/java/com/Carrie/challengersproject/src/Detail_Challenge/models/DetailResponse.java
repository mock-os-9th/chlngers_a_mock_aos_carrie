
package com.Carrie.challengersproject.src.Detail_Challenge.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailResponse {

    @SerializedName("availableDayOfWeek")
    private String mAvailableDayOfWeek;
    @SerializedName("availableTime")
    private String mAvailableTime;
    @SerializedName("badPhotoDescription")
    private String mBadPhotoDescription;
    @SerializedName("badPhotoUrl")
    private String mBadPhotoUrl;
    @SerializedName("caution")
    private String mCaution;
    @SerializedName("certificationCountPerDay")
    private int mCertificationCountPerDay;
    @SerializedName("certificationInterval")
    private int mCertificationInterval;
    @SerializedName("certificationMethod")
    private String mCertificationMethod;
    @SerializedName("certificationsResult")
    private List<CertificationsResult> mCertificationsResult;
    @SerializedName("challengeId")
    private int mChallengeId;
    @SerializedName("challengerCount")
    private int mChallengerCount;
    @SerializedName("code")
    private int mCode;
    @SerializedName("endTime")
    private String mEndTime;
    @SerializedName("frequency")
    private String mFrequency;
    @SerializedName("galleryAvailable")
    private String mGalleryAvailable;
    @SerializedName("gatheredAmount")
    private String mGatheredAmount;
    @SerializedName("goodPhotoDescription")
    private String mGoodPhotoDescription;
    @SerializedName("goodPhotoUrl")
    private String mGoodPhotoUrl;
    @SerializedName("ImageUrl")
    private String mImageUrl;
    @SerializedName("impossiblesResult")
    private List<ImpossiblesResult> mImpossiblesResult;
    @SerializedName("interest")
    private int mInterest;
    @SerializedName("introduction")
    private String mIntroduction;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("maxFee")
    private int mMaxFee;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("minFee")
    private int mMinFee;
    @SerializedName("period")
    private String mPeriod;
    @SerializedName("reviewCount")
    private int mReviewCount;
    @SerializedName("reviewsResult")
    private List<ReviewsResult> mReviewsResult;
    @SerializedName("score")
    private String mScore;
    @SerializedName("startTime")
    private String mStartTime;
    @SerializedName("subjectName")
    private String mSubjectName;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("viewName")
    private String mViewName;
    @SerializedName("week")
    private String mWeek;

    public String getAvailableDayOfWeek() {
        return mAvailableDayOfWeek;
    }

    public String getAvailableTime() {
        return mAvailableTime;
    }

    public String getBadPhotoDescription() {
        return mBadPhotoDescription;
    }

    public String getBadPhotoUrl() {
        return mBadPhotoUrl;
    }

    public String getCaution() {
        return mCaution;
    }

    public int getCertificationCountPerDay() {
        return mCertificationCountPerDay;
    }

    public int getCertificationInterval() {
        return mCertificationInterval;
    }

    public String getCertificationMethod() {
        return mCertificationMethod;
    }

    public List<CertificationsResult> getCertificationsResult() {
        return mCertificationsResult;
    }

    public int getChallengeId() {
        return mChallengeId;
    }

    public int getChallengerCount() {
        return mChallengerCount;
    }

    public int getCode() {
        return mCode;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public String getFrequency() {
        return mFrequency;
    }

    public String getGalleryAvailable() {
        return mGalleryAvailable;
    }

    public String getGatheredAmount() {
        return mGatheredAmount;
    }

    public String getGoodPhotoDescription() {
        return mGoodPhotoDescription;
    }

    public String getGoodPhotoUrl() {
        return mGoodPhotoUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public List<ImpossiblesResult> getImpossiblesResult() {
        return mImpossiblesResult;
    }

    public int getInterest() {
        return mInterest;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public Boolean getIsSuccess() {
        return mIsSuccess;
    }

    public int getMaxFee() {
        return mMaxFee;
    }

    public String getMessage() {
        return mMessage;
    }

    public int getMinFee() {
        return mMinFee;
    }

    public String getPeriod() {
        return mPeriod;
    }

    public int getReviewCount() {
        return mReviewCount;
    }

    public List<ReviewsResult> getReviewsResult() {
        return mReviewsResult;
    }

    public String getScore() {
        return mScore;
    }

    public String getStartTime() {
        return mStartTime;
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

    public class CertificationsResult {

        @SerializedName("certificationId")
        private int mCertificationId;
        @SerializedName("photoUrl")
        private String mPhotoUrl;

        public int getCertificationId() {
            return mCertificationId;
        }

        public String getPhotoUrl() {
            return mPhotoUrl;
        }
    }

    public class ImpossiblesResult {

        @SerializedName("challengeId")
        private int mChallengeId;
        @SerializedName("ImageUrl")
        private String mImageUrl;
        @SerializedName("interest")
        private int mInterest;
        @SerializedName("participationCount")
        private int mParticipationCount;
        @SerializedName("period")
        private String mPeriod;
        @SerializedName("score")
        private String mScore;
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

        public int getInterest() {
            return mInterest;
        }

        public int getParticipationCount() {
            return mParticipationCount;
        }

        public String getPeriod() {
            return mPeriod;
        }

        public String getScore() {
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

    public class ReviewsResult {

        @SerializedName("challengeId")
        private int mChallengeId;
        @SerializedName("content")
        private String mContent;
        @SerializedName("createdAt")
        private String mCreatedAt;
        @SerializedName("score")
        private int mScore;
        @SerializedName("updatedAt")
        private String mUpdatedAt;
        @SerializedName("userId")
        private int mUserId;

        public int getChallengeId() {
            return mChallengeId;
        }

        public String getContent() {
            return mContent;
        }

        public String getCreatedAt() {
            return mCreatedAt;
        }

        public int getScore() {
            return mScore;
        }

        public String getUpdatedAt() {
            return mUpdatedAt;
        }

        public int getUserId() {
            return mUserId;
        }
    }

}
