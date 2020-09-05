package com.Carrie.challengersproject.src.main.mypage.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class MypageResponse {

    public class MypageInfo
    {
        @SerializedName("userId")
        private int userId;

        @SerializedName("nickname")
        private String nickname;

        @SerializedName("profileImageUrl")
        private String profileImageUrl;

        @SerializedName("introduction")
        private String introduction;

        @SerializedName("gradeId")
        private int gradeId;

        @SerializedName("gradeName")
        private String gradeName;

        @SerializedName("reward")
        private int reward;

        @SerializedName("followerCount")
        private int followerCount;

        @SerializedName("followingCount")
        private int followingCount;

        @SerializedName("interestFields")
        private ArrayList interestFields;

        @SerializedName("everydayRecords")
        private ArrayList everydayRecords;

        @SerializedName("todayChallenges")
        private Challenge todayChallenges;

        public int getUserId() {
            return userId;
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

        public int getGradeId() {
            return gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public int getReward() {
            return reward;
        }

        public int getFollowerCount() {
            return followerCount;
        }

        public int getFollowingCount() {
            return followingCount;
        }

        public ArrayList getInterestFields() {
            return interestFields;
        }

        public ArrayList getEverydayRecords() {
            return everydayRecords;
        }

        public Challenge getTodayChallenges() {
            return todayChallenges;
        }
    }

    class Challenge
    {
        @SerializedName("challengeId")
        private int challengeId;

        @SerializedName("certificationId")
        private int certificationId;

        @SerializedName("photoUrl")
        private String photoUrl;

        @SerializedName("startDay")
        private Date startDay;

        @SerializedName("endDay")
        private Date endDay;

        @SerializedName("participantCount")
        private int participantCount;

        public int getChallengeId() {
            return challengeId;
        }

        public int getCertificationId() {
            return certificationId;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public Date getStartDay() {
            return startDay;
        }

        public Date getEndDay() {
            return endDay;
        }

        public int getParticipantCount() {
            return participantCount;
        }
    }


    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("myPageInfo")
    private MypageInfo myPageInfo;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public MypageInfo getMyPageInfo() {
        return myPageInfo;
    }
}
