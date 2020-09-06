package com.Carrie.challengersproject.src.main.mypage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.main.mypage.interfaces.MypageFragmentView;
import com.Carrie.challengersproject.src.main.mypage.models.MypageResponse;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;

import static com.Carrie.challengersproject.ApplicationClass.sSharedPreferences;

public class MypageFragment extends Fragment implements MypageFragmentView {


    ViewGroup viewGroup;
    a_MainActivity mainActivity;

    final MypageService mypageService = new MypageService(this);

    ImageView profileImg;
    TextView nickname;
    Button grade;
    TextView reward;
    TextView follower_count;
    TextView follwing_count;
    TextView interest_field;
    CalendarView calendarView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (a_MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.mypage_fragment, container, false);

        // 설정 버튼
        ImageButton setting_btn = viewGroup.findViewById(R.id.myPage_fragment_ib_setting);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(5);
            }
        });

        // 팔로워 버튼
        final TextView follwer_text_num = viewGroup.findViewById(R.id.myPage_fragment_tv_follower_num);
        final TextView follower_text = viewGroup.findViewById(R.id.myPage_fragment_tv_follower);
        follower_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // color는 int 값
                int color = getResources().getColor(R.color.colorGrayPressed);
                follower_text.setTextColor(color);
                follwer_text_num.setTextColor(color);
                mainActivity.onChangeFragment(8);
            }
        });
        follwer_text_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = getResources().getColor(R.color.colorGrayPressed);
                follower_text.setTextColor(color);
                follwer_text_num.setTextColor(color);
                mainActivity.onChangeFragment(8);
            }
        });

        // 팔로잉 버튼
        final TextView following_text_num = viewGroup.findViewById(R.id.myPage_fragment_tv_following_num);
        final TextView following_text = viewGroup.findViewById(R.id.myPage_fragment_tv_following);
        following_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = getResources().getColor(R.color.colorGrayPressed);
                following_text.setTextColor(color);
                following_text_num.setTextColor(color);
                mainActivity.onChangeFragment(9);
            }
        });
        following_text_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = getResources().getColor(R.color.colorGrayPressed);
                following_text.setTextColor(color);
                following_text_num.setTextColor(color);
                mainActivity.onChangeFragment(9);
            }
        });

        RadarChart radarChart;
        radarChart = viewGroup.findViewById(R.id.myPage_fragment_radarChart);

        //차트 생성
        RadarDataSet dataSet = new RadarDataSet(dataValue(), "DATA");
        int color = getResources().getColor(R.color.colorPrimary);
        dataSet.setColor(color);
        dataSet.setFormLineWidth(3f);
        int graycolor = getResources().getColor(R.color.colorGray);
        RadarData radarData = new RadarData();
        radarData.addDataSet(dataSet);
        String[] labels = {"역량", "건강", "관계", "자산", "생활", "취미"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarChart.setRotationAngle(60);
        radarChart.setData(radarData);
        radarChart.setWebLineWidth(1f);
        radarChart.setWebLineWidthInner(1f);
        radarChart.setWebColor(graycolor);
        radarChart.setDescription(null);
        YAxis yAxis = radarChart.getYAxis();
        yAxis.setTextSize(0.01f);


        profileImg = viewGroup.findViewById(R.id.myPage_fragment_iv_profile);
        nickname = viewGroup.findViewById(R.id.myPage_fragment_tv_nickname);
        grade = viewGroup.findViewById(R.id.myPage_fragment_btn_level);
        reward = viewGroup.findViewById(R.id.myPage_fragment_tv_wincash);
        follower_count = viewGroup.findViewById(R.id.myPage_fragment_tv_follower_num);
        follwing_count = viewGroup.findViewById(R.id.myPage_fragment_tv_following_num);
        interest_field = viewGroup.findViewById(R.id.myPage_fragment_tv_list_favorite);
        calendarView = viewGroup.findViewById(R.id.myPage_fragment_cv_calender);

        TryGetMypage();
        return viewGroup;
    }

    private ArrayList<RadarEntry> dataValue() {
        //차트 데이터 생성
        ArrayList<RadarEntry> dataVals = new ArrayList<>();
        dataVals.add(new RadarEntry(100));
        dataVals.add(new RadarEntry(100));
        dataVals.add(new RadarEntry(100));
        dataVals.add(new RadarEntry(100));
        dataVals.add(new RadarEntry(100));
        dataVals.add(new RadarEntry(100));
        return dataVals;
    }



//    private int userId;
//    private String nickname;
//    private String profileImageUrl;
//    private String introduction;
//    private int gradeId;
//    private String gradeName;
//    private int reward;
//    private int followerCount;
//    private int followingCount;
//    private ArrayList interestFields;

//  달력날짜  private ArrayList everydayRecords;
//  챌린지 정보  private Challenge todayChallenges;
    public void TryGetMypage()
    {
        int id = sSharedPreferences.getInt("ID",0);
        mypageService.GetMyPageIn(id);
        Log.e("아이디",String.valueOf(id));
    }
    @Override
    public void MyPageGetSuccess(MypageResponse.MypageInfo mypageInfo) {
        // 성공시
        String profileUrl = mypageInfo.getProfileImageUrl();
        Glide.with(this).load(profileUrl).into(profileImg);

        nickname.setText(mypageInfo.getNickname());
        if(mypageInfo.getGradeName() == "Red"){grade.setHighlightColor(Color.RED);}
        else if(mypageInfo.getGradeName() == "Orange"){grade.setHighlightColor(Color.RED);}
        else if(mypageInfo.getGradeName() == "Yellow"){grade.setHighlightColor(Color.YELLOW);}
        else if(mypageInfo.getGradeName() == "Green"){grade.setHighlightColor(Color.GREEN);}
        else if(mypageInfo.getGradeName() == "Sky"){grade.setHighlightColor(Color.BLUE);}
        else if(mypageInfo.getGradeName() == "Blue"){grade.setHighlightColor(Color.BLUE);}
        else if(mypageInfo.getGradeName() == "Purple"){grade.setHighlightColor(Color.BLACK);}
        else if(mypageInfo.getGradeName() == "Black"){grade.setHighlightColor(Color.BLACK);}

        reward.setText(String.valueOf(mypageInfo.getReward()));
        follower_count.setText(String.valueOf(mypageInfo.getFollowerCount()));
        follwing_count.setText(String.valueOf(mypageInfo.getFollowingCount()));

        String setT="";

        for(int i = 0; i<mypageInfo.getInterestFields().size(); i++)
        {
            if(i ==0)
            {
                setT += ("#"+mypageInfo.getInterestFields().get(i));
                Log.e("관심분야",setT);
            }
            else
            {
                setT += (", #" + mypageInfo.getInterestFields().get(i));
                Log.e("관심분야",setT);
            }
        }

        Log.e("관심분야",setT);
        interest_field.setText(setT);


        Calendar cal = Calendar.getInstance();
        Log.d("마이페이지 조회 성공","성공");

//        for(int i = 0; i<mypageInfo.getEverydayRecords().size();i++)
//        {
//
//        }
//        DATE_FORMAT;
    }

    @Override
    public void MyPageGetFailure(String message) {
        // 실패시
        Log.d("마이페이지 조회 실패",message);
    }
}
