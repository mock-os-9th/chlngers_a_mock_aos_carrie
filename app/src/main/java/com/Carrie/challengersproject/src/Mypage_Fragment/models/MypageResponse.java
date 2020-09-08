
package com.Carrie.challengersproject.src.Mypage_Fragment.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MypageResponse {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("myPageInfo")
    private MyPageInfo mMyPageInfo;

    public Long getCode() {
        return mCode;
    }

    public Boolean getIsSuccess() {
        return mIsSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    public MyPageInfo getMyPageInfo() {
        return mMyPageInfo;
    }

    public class MyPageInfo {

        @SerializedName("everydayRecords")
        private List<String> mEverydayRecords;
        @SerializedName("followerCount")
        private Long mFollowerCount;
        @SerializedName("followingCount")
        private Long mFollowingCount;
        @SerializedName("gradeId")
        private Long mGradeId;
        @SerializedName("gradeName")
        private String mGradeName;
        @SerializedName("interestFields")
        private List<String> mInterestFields;
        @SerializedName("introduction")
        private String mIntroduction;
        @SerializedName("nickname")
        private String mNickname;
        @SerializedName("profileImageUrl")
        private String mProfileImageUrl;
        @SerializedName("reward")
        private Long mReward;
        @SerializedName("todayChallenges")
        private List<TodayChallenge> mTodayChallenges;
        @SerializedName("userId")
        private Long mUserId;

        public List<String> getEverydayRecords() {
            return mEverydayRecords;
        }

        public Long getFollowerCount() {
            return mFollowerCount;
        }

        public Long getFollowingCount() {
            return mFollowingCount;
        }

        public Long getGradeId() {
            return mGradeId;
        }

        public String getGradeName() {
            return mGradeName;
        }

        public List<String> getInterestFields() {
            return mInterestFields;
        }

        public String getIntroduction() {
            return mIntroduction;
        }

        public String getNickname() {
            return mNickname;
        }

        public String getProfileImageUrl() {
            return mProfileImageUrl;
        }

        public Long getReward() {
            return mReward;
        }

        public List<TodayChallenge> getTodayChallenges() {
            return mTodayChallenges;
        }

        public Long getUserId() {
            return mUserId;
        }
    }

    public class TodayChallenge {
        @SerializedName("certificationId")
        private Long mCertificationId;
        @SerializedName("challengeId")
        private Long mChallengeId;
        @SerializedName("endDay")
        private String mEndDay;
        @SerializedName("participantCount")
        private Long mParticipantCount;
        @SerializedName("photoUrl")
        private String mPhotoUrl;
        @SerializedName("startDay")
        private String mStartDay;

        public Long getCertificationId() {
            return mCertificationId;
        }

        public Long getChallengeId() {
            return mChallengeId;
        }

        public String getEndDay() {
            return mEndDay;
        }

        public Long getParticipantCount() {
            return mParticipantCount;
        }

        public String getPhotoUrl() {
            return mPhotoUrl;
        }

        public String getStartDay() {
            return mStartDay;
        }
    }

}
