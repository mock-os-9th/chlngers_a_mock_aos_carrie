package com.Carrie.challengersproject.src.Mypage_Fragment;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.Mypage_Fragment.interfaces.MypageFragmentView;
import com.Carrie.challengersproject.src.Mypage_Fragment.models.MypageResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;

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
    MaterialCalendarView calendarView;

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
    public void MyPageGetSuccess(MypageResponse mypageResponse) {

        // 아래 내용들 리스폰스에서 받아 온 값으로 넣어주기
        String img_url = mypageResponse.getMyPageInfo().getProfileImageUrl();
        if (img_url.startsWith("h")) {
            Glide.with(this).load(img_url).apply(new RequestOptions().circleCrop()).into(profileImg);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap originalBm = BitmapFactory.decodeFile(img_url, options);
            profileImg.setImageBitmap(originalBm);
        }


        String n_nickname = mypageResponse.getMyPageInfo().getNickname();
        nickname.setText(n_nickname);

        String level_color = mypageResponse.getMyPageInfo().getGradeName();
        if (level_color.equals("Red")) {
            grade.setHighlightColor(Color.RED);
        }

        String r_reward = String.valueOf(mypageResponse.getMyPageInfo().getReward());
        reward.setText(r_reward);

        String fer_count = String.valueOf(mypageResponse.getMyPageInfo().getFollowerCount());
        follower_count.setText(fer_count);

        String fng_count = String.valueOf(mypageResponse.getMyPageInfo().getFollowingCount());
        follwing_count.setText(fng_count);

        String total_field = "";
        for (int i = 0; i < mypageResponse.getMyPageInfo().getInterestFields().size(); i++) {
            total_field += "#";
            total_field += mypageResponse.getMyPageInfo().getInterestFields().get(i);
            total_field += ", ";

        }
        interest_field.setText(total_field);

        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

        for(int i = 0; i<mypageResponse.getMyPageInfo().getEverydayRecords().size(); i++)
        {
            int day;

            day= Integer.parseInt(mypageResponse.getMyPageInfo().getEverydayRecords().get(i).substring(8,10));

            Log.d("day",String.valueOf(day));
            calendarView.setDateSelected(CalendarDay.from(2020,9,day),true);
        }

//        calendarView.setDateSelected(CalendarDay.from(2020,9,8),true);
//        calendarView.setDateSelected(CalendarDay.from(2020,9,7),true);
//        calendarView.setDateSelected(CalendarDay.from(2020,9,6),true);


    }

    @Override
    public void MyPageGetFailure(String message) {

    }
}
