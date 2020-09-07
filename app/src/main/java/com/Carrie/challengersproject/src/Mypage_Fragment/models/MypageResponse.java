package com.Carrie.challengersproject.src.Mypage_Fragment.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
        private List<String> interestFields;

        @SerializedName("everydayRecords")
        private List<String> everydayRecords;

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

        public List<String> getInterestFields() {
            return interestFields;
        }

        public List<String> getEverydayRecords() {
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
        private String startDay;

        @SerializedName("endDay")
        private String endDay;

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

        public String getStartDay() {
            return startDay;
        }

        public String getEndDay() {
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
