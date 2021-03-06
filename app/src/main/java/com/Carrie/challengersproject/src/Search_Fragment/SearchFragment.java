package com.Carrie.challengersproject.src.Search_Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Detail_Challenge.Detail_Challenge;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.Mypage_Fragment.MypageService;
import com.Carrie.challengersproject.src.Mypage_Fragment.interfaces.MypageFragmentView;
import com.Carrie.challengersproject.src.Mypage_Fragment.models.MypageResponse;
import com.Carrie.challengersproject.src.Search_Fragment.interfaces.SearchActivityView;
import com.Carrie.challengersproject.src.Search_Fragment.models.SearchResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;
import static com.Carrie.challengersproject.ApplicationClass.X_ACCESS_TOKEN;
import static com.Carrie.challengersproject.ApplicationClass.sSharedPreferences;
import static com.Carrie.challengersproject.ApplicationClass.TAG;


public class SearchFragment extends Fragment implements SearchActivityView, MypageFragmentView {

    ViewGroup viewGroup;
    FloatingActionButton fab;
    a_MainActivity mainActivity;

    private Challenge_Adapter mAdapter;
    private Challenge_Adapter mAdapter1;
    private Challenge_Adapter mAdapter2;
    private Challenge_Adapter mAdapter3;

    private PopularAdapter popularAdapter;
    private PopularAdapter popularAdapter1;
    private PopularAdapter popularAdapter2;
    private PopularAdapter popularAdapter3;

    private CircleAdapter circleAdapter;


    boolean IS_10s= true;
    boolean IS_20s = false;
    boolean IS_30s = false;
    boolean IS_40s = false;


    ViewPager viewPager;
    int images[] ={R.drawable.banner01, R.drawable.banner02};
    BannerAdpater bannerAdpater;
    int currentPage = 0;
    Timer timer;
    int NumPages = images.length;
    long Delay_MS = 3000; // 오토 플립용 타이머 시작 후 해당 시간에 작동
    long Period_Ms = 5000; // 5초 주기로 작동

    SearchService searchService = new SearchService(this);
    MypageService mypageService = new MypageService(this);

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;

    RecyclerView popular_recyclerview;
    ArrayList tens_popular_list;
    ArrayList twenties_popular_list;
    ArrayList thries_popular_list;
    ArrayList forties_popular_list;

    RecyclerView circle_recyclerview;
    ArrayList circle_list;


    ArrayList recommend_list;
    ArrayList exercise_list;
    ArrayList study_list;
    ArrayList strength_list;

    ArrayList mArrayList;

    TextView nickname;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (a_MainActivity) getActivity();
        sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
        int id=sSharedPreferences.getInt("ID",0);

        tryMypage(id);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.search_fragment,container,false);


        nickname = viewGroup.findViewById(R.id.search_fm_tv_nick_name);
        recyclerView = viewGroup.findViewById(R.id.search_fm_recyclerview_recommand_item);
        recyclerView1 = viewGroup.findViewById(R.id.search_fm_recyclerview_exercise_item);
        recyclerView2 = viewGroup.findViewById(R.id.search_fm_recyclerview_study_item);
        recyclerView3 = viewGroup.findViewById(R.id.search_fm_recyclerview_know_item);
        popular_recyclerview = viewGroup.findViewById(R.id.search_fm_recyclerview_popular);
        circle_recyclerview = viewGroup.findViewById(R.id.search_fm_recyclerview_circle_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager_popular = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager_circle = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        popular_recyclerview.setLayoutManager(linearLayoutManager_popular);
        circle_recyclerview.setLayoutManager(linearLayoutManager_circle);

        mArrayList = new ArrayList<>();
        String temp = sSharedPreferences.getString(X_ACCESS_TOKEN,"null");
        Log.d("searchPage_jwt",temp);

        // 통신
        tryGetSearchTest();

        viewPager = viewGroup.findViewById(R.id.search_fm_banner);
        BannerAdpater bannerAdpater = new BannerAdpater(getContext(),images);
        viewPager.setAdapter(bannerAdpater);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        final int color_gray_dark = getResources().getColor(R.color.colorPrimaryDark);
        final int color_gray_pressed = getResources().getColor(R.color.colorGrayPressed);


        TextView tens = viewGroup.findViewById(R.id.search_fm_10s);
        final TextView tens_line= viewGroup.findViewById(R.id.search_fm_10s_line);
        TextView twenty= viewGroup.findViewById(R.id.search_fm_20s);
        final TextView twenty_line= viewGroup.findViewById(R.id.search_fm_20s_line);
        TextView thirty= viewGroup.findViewById(R.id.search_fm_30s);
        final TextView thirty_line= viewGroup.findViewById(R.id.search_fm_30s_line);
        TextView forty= viewGroup.findViewById(R.id.search_fm_40s);
        final TextView forty_line= viewGroup.findViewById(R.id.search_fm_40s_line);

        tens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSearchTest();
                tens_line.setBackgroundColor(color_gray_dark);
                twenty_line.setBackgroundColor(color_gray_pressed);
                thirty_line.setBackgroundColor(color_gray_pressed);
                forty_line.setBackgroundColor(color_gray_pressed);
                IS_10s = true;
                IS_20s = false;
                IS_30s = false;
                IS_40s = false;
            }
        });

        twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSearchTest();
                tens_line.setBackgroundColor(color_gray_pressed);
                twenty_line.setBackgroundColor(color_gray_dark);
                thirty_line.setBackgroundColor(color_gray_pressed);
                forty_line.setBackgroundColor(color_gray_pressed);
                IS_10s = false;
                IS_20s = true;
                IS_30s = false;
                IS_40s = false;
            }
        });

        thirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSearchTest();
                tens_line.setBackgroundColor(color_gray_pressed);
                twenty_line.setBackgroundColor(color_gray_pressed);
                thirty_line.setBackgroundColor(color_gray_dark);
                forty_line.setBackgroundColor(color_gray_pressed);
                IS_10s = false;
                IS_20s = false;
                IS_30s = true;
                IS_40s = false;
            }
        });

        forty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSearchTest();
                tens_line.setBackgroundColor(color_gray_pressed);
                twenty_line.setBackgroundColor(color_gray_pressed);
                thirty_line.setBackgroundColor(color_gray_pressed);
                forty_line.setBackgroundColor(color_gray_dark);
                IS_10s = false;
                IS_20s = false;
                IS_30s = false;
                IS_40s = true;
            }
        });
        return viewGroup;
    }

    @Override
    public void onResume() {
        super.onResume();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                currentPage = viewPager.getCurrentItem();
                int nextPage = currentPage+1;

                if(nextPage >= NumPages)
                {
                    nextPage = 0;
                }
                viewPager.setCurrentItem(nextPage,true);
                currentPage = nextPage;
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },Delay_MS,Period_Ms);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(timer!=null)
        {
            timer.cancel();
            timer = null;
        }
    }


    private void tryGetSearchTest()
    {
        searchService.getSearchTest();
    }

    @Override
    public void SearchSuccess(final SearchResponse searchResponse) {
        Log.d("SearchSuccess",searchResponse.getMessage());

        recommend_list = new ArrayList<>();
        exercise_list = new ArrayList<>();
        study_list = new ArrayList<>();
        strength_list = new ArrayList<>();

        tens_popular_list = new ArrayList<>();
        twenties_popular_list = new ArrayList<>();
        thries_popular_list = new ArrayList<>();
        forties_popular_list = new ArrayList<>();


        for(int i = 0; i<5; i++)
        {
            String img="";
            if(i%4==0) { img ="https://cdn.pixabay.com/photo/2019/08/01/12/36/illustration-4377408_960_720.png";}
            else if(i%4 ==1){img ="https://cdn.pixabay.com/photo/2019/08/06/02/16/mountains-4387209_960_720.jpg";}
            else if(i%4 ==2){img="https://img.kr.news.samsung.com/kr/wp-content/uploads/2017/10/171019_%EC%84%B8%EC%83%81%EC%9D%84%EC%9E%87IT%EB%8A%94%EC%9D%B4%EC%95%BC%EA%B8%B0_%EB%8F%84%EB%B9%84%EB%9D%BC_%EC%88%98%EC%A0%95.jpg";}
            else if(i%4 ==3){img="https://www.abc.net.au/cm/rimage/12125456-16x9-xlarge.jpg?v=3";}
            String title = searchResponse.getPopular().get(0).getChallenges().getTens().get(i).getTitle();
            String score = String.valueOf(searchResponse.getPopular().get(0).getChallenges().getTens().get(i).getScore());
            String participationCount = "현재 "+ String.valueOf(searchResponse.getPopular().get(0).getChallenges().getTens().get(i).getParticipationCount()) +"명 신청";
            String period = searchResponse.getPopular().get(0).getChallenges().getTens().get(i).getPeriod();
            String week =searchResponse.getPopular().get(0).getChallenges().getTens().get(i).getWeek();
            String viewname =searchResponse.getPopular().get(0).getChallenges().getTens().get(i).getViewName();
            Challenge_Item challenge_item = new Challenge_Item(img,title,score,participationCount,period,week,viewname,i);
            tens_popular_list.add(challenge_item);
        }


        for(int i = 0; i<5; i++)
        {
            String img="";
            if(i%4==0) { img ="https://www.webanywhere.co.uk/blog/wp-content/uploads/shutterstock364158869.jpg";}
            else if(i%4 ==1){img ="https://economist-static-files.s3.us-west-1.amazonaws.com/2020-03/home-study.jpg";}
            else if(i%4 ==2){img="https://res.cloudinary.com/highereducation/image/upload/c_scale,w_750/f_auto,fl_lossy,q_auto:eco/v1532987738/TheBestColleges.org/images/study-tips.jpg";}
            else if(i%4 ==3){img="https://s3-ap-southeast-2.amazonaws.com/geg-gug-webapp/images/1557364192-work_while_you_study_banner.jpg";}
            String title = searchResponse.getPopular().get(0).getChallenges().getTwenties().get(i).getTitle();
            String score = String.valueOf(searchResponse.getPopular().get(0).getChallenges().getTwenties().get(i).getScore());
            String participationCount = "현재 "+ String.valueOf(searchResponse.getPopular().get(0).getChallenges().getTwenties().get(i).getParticipationCount()) +"명 신청";
            String period = searchResponse.getPopular().get(0).getChallenges().getTwenties().get(i).getPeriod();
            String week =searchResponse.getPopular().get(0).getChallenges().getTwenties().get(i).getWeek();
            String viewname =searchResponse.getPopular().get(0).getChallenges().getTwenties().get(i).getViewName();
            Challenge_Item challenge_item = new Challenge_Item(img,title,score,participationCount,period,week,viewname,i);
            twenties_popular_list.add(challenge_item);
        }

        for(int i = 0; i<5; i++)
        {
            String img="";
            if(i%4==0) { img ="https://www.webanywhere.co.uk/blog/wp-content/uploads/shutterstock364158869.jpg";}
            else if(i%4 ==1){img ="https://economist-static-files.s3.us-west-1.amazonaws.com/2020-03/home-study.jpg";}
            else if(i%4 ==2){img="https://res.cloudinary.com/highereducation/image/upload/c_scale,w_750/f_auto,fl_lossy,q_auto:eco/v1532987738/TheBestColleges.org/images/study-tips.jpg";}
            else if(i%4 ==3){img="https://s3-ap-southeast-2.amazonaws.com/geg-gug-webapp/images/1557364192-work_while_you_study_banner.jpg";}
            String title = searchResponse.getPopular().get(0).getChallenges().getThirties().get(i).getTitle();
            String score = String.valueOf(searchResponse.getPopular().get(0).getChallenges().getThirties().get(i).getScore());
            String participationCount = "현재 "+ String.valueOf(searchResponse.getPopular().get(0).getChallenges().getThirties().get(i).getParticipationCount()) +"명 신청";
            String period = searchResponse.getPopular().get(0).getChallenges().getThirties().get(i).getPeriod();
            String week =searchResponse.getPopular().get(0).getChallenges().getThirties().get(i).getWeek();
            String viewname =searchResponse.getPopular().get(0).getChallenges().getThirties().get(i).getViewName();

            Challenge_Item challenge_item = new Challenge_Item(img,title,score,participationCount,period,week,viewname,i);
            thries_popular_list.add(challenge_item);
        }

        for(int i = 0; i<5; i++)
        {
            String img="";
            if(i%4==0) { img ="https://www.precisionmovement.coach/wp-content/uploads/2018/01/wrist-sprain-exercises-for-mobility-and-strenth.jpg";}
            else if(i%4 ==1){img ="https://www.precisionmovement.coach/wp-content/uploads/2018/01/How-to-Treat-a-Sprained-Wrist-Hockey.jpg";}
            else if(i%4 ==2){img="https://res.cloudinary.com/highereducation/image/upload/c_scale,w_750/f_auto,fl_lossy,q_auto:eco/v1532987738/TheBestColleges.org/images/study-tips.jpg";}
            else if(i%4 ==3){img="https://liquidplanner-wpengine.netdna-ssl.com/wp-content/uploads/2019/05/team-strength.jpg";}
            String title = searchResponse.getPopular().get(0).getChallenges().getForties().get(i).getTitle();
            String score = String.valueOf(searchResponse.getPopular().get(0).getChallenges().getForties().get(i).getScore());
            String participationCount = "현재 "+ String.valueOf(searchResponse.getPopular().get(0).getChallenges().getForties().get(i).getParticipationCount()) +"명 신청";
            String period = searchResponse.getPopular().get(0).getChallenges().getForties().get(i).getPeriod();
            String week =searchResponse.getPopular().get(0).getChallenges().getForties().get(i).getWeek();
            String viewname =searchResponse.getPopular().get(0).getChallenges().getForties().get(i).getViewName();

            Challenge_Item challenge_item = new Challenge_Item(img,title,score,participationCount,period,week,viewname,i);
            strength_list.add(challenge_item);
        }

        popularAdapter = new PopularAdapter(tens_popular_list);
        popularAdapter1 = new PopularAdapter(twenties_popular_list);
        popularAdapter2 = new PopularAdapter(thries_popular_list);
        popularAdapter3 = new PopularAdapter(forties_popular_list);

        /*
        10이 선택되어 있으면 -> 10에 맞는 데이터 넣어주기
        20이 선택되어 있으면 -> 20에 맞는 데이터 넣어주기
        30이 선택되어 있으면 -> 30에 맞는 데이터 넣어주기
        40이 선택되어 있으면 -> 40에 맞는 데이터 넣어주기
        * */
        if(IS_10s && !IS_20s && !IS_30s && !IS_40s)
        {
            popular_recyclerview.setAdapter(popularAdapter);
        }else if(!IS_10s && IS_20s && !IS_30s && !IS_40s)
        {
            popular_recyclerview.setAdapter(popularAdapter1);
        }else if(!IS_10s && !IS_20s && IS_30s && !IS_40s)
        {
            popular_recyclerview.setAdapter(popularAdapter2);
        }else if(!IS_10s && !IS_20s && !IS_30s && IS_40s)
        {
            popular_recyclerview.setAdapter(popularAdapter3);
        }

        popularAdapter.setOnItemClickListener(new PopularAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });
        popularAdapter1.setOnItemClickListener(new PopularAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });
        popularAdapter2.setOnItemClickListener(new PopularAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });
        popularAdapter3.setOnItemClickListener(new PopularAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        popularAdapter.notifyDataSetChanged();
        popularAdapter1.notifyDataSetChanged();
        popularAdapter2.notifyDataSetChanged();
        popularAdapter3.notifyDataSetChanged();


        // list 별 add 처리
        // 추천
        for(int i =0; i<searchResponse.getSearch().get(7).getChallenges().size();i++)
        {
            String img="";
            if(i%4==0) { img ="https://cdn.pixabay.com/photo/2019/08/01/12/36/illustration-4377408_960_720.png";}
            else if(i%4 ==1){img ="https://cdn.pixabay.com/photo/2019/08/06/02/16/mountains-4387209_960_720.jpg";}
            else if(i%4 ==2){img="https://img.kr.news.samsung.com/kr/wp-content/uploads/2017/10/171019_%EC%84%B8%EC%83%81%EC%9D%84%EC%9E%87IT%EB%8A%94%EC%9D%B4%EC%95%BC%EA%B8%B0_%EB%8F%84%EB%B9%84%EB%9D%BC_%EC%88%98%EC%A0%95.jpg";}
            else if(i%4 ==3){img="https://www.abc.net.au/cm/rimage/12125456-16x9-xlarge.jpg?v=3";}
            String title = searchResponse.getSearch().get(7).getChallenges().get(i).getTitle();
            String score = String.valueOf(searchResponse.getSearch().get(7).getChallenges().get(i).getScore());
            String participationCount = "현재 "+ String.valueOf(searchResponse.getSearch().get(7).getChallenges().get(i).getParticipationCount()) +"명 신청";
            String period = searchResponse.getSearch().get(7).getChallenges().get(i).getPeriod();
            String week =searchResponse.getSearch().get(7).getChallenges().get(i).getWeek();
            String viewname =searchResponse.getSearch().get(7).getChallenges().get(i).getViewName();

            Challenge_Item challenge_item = new Challenge_Item(img,title,score,participationCount,period,week,viewname,i);
            recommend_list.add(challenge_item);
            }

        // 운동
        for(int i =0; i<searchResponse.getSearch().get(0).getChallenges().size();i++)
        {
            String img="";
            if(i%4==0) { img ="https://static.independent.co.uk/s3fs-public/thumbnails/image/2018/03/19/18/idoh-exercise.jpg?width=990";}
            else if(i%4 ==1){img ="https://img.healthyway.com/hwy/image/upload/c_limit,q_auto,w_728/i/Beach-Workouts.jpg";}
            else if(i%4 ==2){img="https://www.abc.net.au/cm/rimage/12125456-16x9-xlarge.jpg?v=3";}
            else if(i%4 ==3){img="https://imgk.timesnownews.com/story/push-ups.gif?tr=w-600,h-450,fo-auto";}
            String title = searchResponse.getSearch().get(0).getChallenges().get(i).getTitle();
            String score = String.valueOf(searchResponse.getSearch().get(0).getChallenges().get(i).getScore());
            String participationCount = "현재 "+ String.valueOf(searchResponse.getSearch().get(0).getChallenges().get(i).getParticipationCount()) +"명 신청";
            String period = searchResponse.getSearch().get(0).getChallenges().get(i).getPeriod();
            String week =searchResponse.getSearch().get(0).getChallenges().get(i).getWeek();
            String viewname =searchResponse.getSearch().get(0).getChallenges().get(i).getViewName();

            Challenge_Item challenge_item = new Challenge_Item(img,title,score,participationCount,period,week,viewname,i);
            exercise_list.add(challenge_item);
        }

        // 공부
        for(int i =0; i<searchResponse.getSearch().get(5).getChallenges().size();i++)
        {
            String img="";
            if(i%4==0) { img ="https://www.webanywhere.co.uk/blog/wp-content/uploads/shutterstock364158869.jpg";}
            else if(i%4 ==1){img ="https://economist-static-files.s3.us-west-1.amazonaws.com/2020-03/home-study.jpg";}
            else if(i%4 ==2){img="https://res.cloudinary.com/highereducation/image/upload/c_scale,w_750/f_auto,fl_lossy,q_auto:eco/v1532987738/TheBestColleges.org/images/study-tips.jpg";}
            else if(i%4 ==3){img="https://s3-ap-southeast-2.amazonaws.com/geg-gug-webapp/images/1557364192-work_while_you_study_banner.jpg";}
            String title = searchResponse.getSearch().get(5).getChallenges().get(i).getTitle();
            String score = String.valueOf(searchResponse.getSearch().get(5).getChallenges().get(i).getScore());
            String participationCount = "현재 "+ String.valueOf(searchResponse.getSearch().get(5).getChallenges().get(i).getParticipationCount()) +"명 신청";
            String period = searchResponse.getSearch().get(5).getChallenges().get(i).getPeriod();
            String week =searchResponse.getSearch().get(5).getChallenges().get(i).getWeek();
            String viewname =searchResponse.getSearch().get(5).getChallenges().get(i).getViewName();

            Challenge_Item challenge_item = new Challenge_Item(img,title,score,participationCount,period,week,viewname,i);
            study_list.add(challenge_item);
        }

        // 역량
        for(int i =0; i<searchResponse.getSearch().get(3).getChallenges().size();i++)
        {
            String img="";
            if(i%4==0) { img ="https://www.precisionmovement.coach/wp-content/uploads/2018/01/wrist-sprain-exercises-for-mobility-and-strenth.jpg";}
            else if(i%4 ==1){img ="https://www.precisionmovement.coach/wp-content/uploads/2018/01/How-to-Treat-a-Sprained-Wrist-Hockey.jpg";}
            else if(i%4 ==2){img="https://res.cloudinary.com/highereducation/image/upload/c_scale,w_750/f_auto,fl_lossy,q_auto:eco/v1532987738/TheBestColleges.org/images/study-tips.jpg";}
            else if(i%4 ==3){img="https://liquidplanner-wpengine.netdna-ssl.com/wp-content/uploads/2019/05/team-strength.jpg";}
            String title = searchResponse.getSearch().get(3).getChallenges().get(i).getTitle();
            String score = String.valueOf(searchResponse.getSearch().get(3).getChallenges().get(i).getScore());
            String participationCount = "현재 "+ String.valueOf(searchResponse.getSearch().get(3).getChallenges().get(i).getParticipationCount()) +"명 신청";
            String period = searchResponse.getSearch().get(3).getChallenges().get(i).getPeriod();
            String week =searchResponse.getSearch().get(3).getChallenges().get(i).getWeek();
            String viewname =searchResponse.getSearch().get(3).getChallenges().get(i).getViewName();

            Challenge_Item challenge_item = new Challenge_Item(img,title,score,participationCount,period,week,viewname,i);
            strength_list.add(challenge_item);
        }

        mAdapter = new Challenge_Adapter(recommend_list);
        mAdapter1 = new Challenge_Adapter(exercise_list);
        mAdapter2 = new Challenge_Adapter(study_list);
        mAdapter3 = new Challenge_Adapter(strength_list);

        recyclerView.setAdapter(mAdapter);
        recyclerView1.setAdapter(mAdapter1);
        recyclerView2.setAdapter(mAdapter2);
        recyclerView3.setAdapter(mAdapter3);

        mAdapter.setOnItemClickListener(new Challenge_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // 추천 클릭시 > 상세 페이지로
                Intent intent = new Intent(mainActivity, Detail_Challenge.class);
                int challenge_id = searchResponse.getSearch().get(7).getChallenges().get(pos).getChallengeId();
                // 상세 페이지로 갔을 때, 이 클릭한 아이템의 정보를 찾을 수 있어야 해서 일단 이
                // 어레이 에서 인덱스 정보를 넘기고 아마 상세 페이지에서도 이 API 를 호출해서 해당 챌린지 아이디를 찾고 아이디로 접근해서 찾아야겠다.
                intent.putExtra("챌린지아이디",challenge_id);
                startActivity(intent);
                mainActivity.finish();
            }
        });

        mAdapter1.setOnItemClickListener(new Challenge_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // 운동 클릭시
                Intent intent = new Intent(mainActivity, Detail_Challenge.class);
                int challenge_id = searchResponse.getSearch().get(0).getChallenges().get(pos).getChallengeId();
                intent.putExtra("챌린지아이디",challenge_id);
                startActivity(intent);
                mainActivity.finish();
            }
        });

        mAdapter2.setOnItemClickListener(new Challenge_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // 공부 클릭시
                Intent intent = new Intent(mainActivity, Detail_Challenge.class);
                int challenge_id = searchResponse.getSearch().get(5).getChallenges().get(pos).getChallengeId();
                intent.putExtra("챌린지아이디",challenge_id);
                startActivity(intent);
                mainActivity.finish();
            }
        });

        mAdapter3.setOnItemClickListener(new Challenge_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // 역량 클릭시
                Intent intent = new Intent(mainActivity, Detail_Challenge.class);
                int challenge_id = searchResponse.getSearch().get(3).getChallenges().get(pos).getChallengeId();
                intent.putExtra("챌린지아이디",challenge_id);
                startActivity(intent);
                mainActivity.finish();
            }
        });
        mAdapter.notifyDataSetChanged();
        mAdapter1.notifyDataSetChanged();
        mAdapter2.notifyDataSetChanged();
        mAdapter3.notifyDataSetChanged();

        circle_list = new ArrayList<>();

        for(int i = 0; i<searchResponse.getSearch().size(); i++)
        {
            String subject = searchResponse.getSearch().get(i).getSubject();
            circle_list.add(subject);
        }

        circleAdapter = new CircleAdapter(circle_list);
        circle_recyclerview.setAdapter(circleAdapter);
        circleAdapter.setOnItemClickListener(new CircleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });
        circleAdapter.notifyDataSetChanged();
    }

    @Override
    public void SearchFailure(SearchResponse searchResponse) {

    }

    void tryMypage(int id)
    {
        mypageService.GetMyPageIn(id);
    }

    @Override
    public void MyPageGetSuccess(MypageResponse mypageResponse) {
        String nick_name = mypageResponse.getMyPageInfo().getNickname();
        nickname.setText(nick_name);
    }

    @Override
    public void MyPageGetFailure(String message) {

    }

    class BannerAdpater extends PagerAdapter
    {

        int[] datas;
        LayoutInflater inflater;
        Context mContext;

        public BannerAdpater(Context mContext, int[] datas) {
            this.mContext = mContext;
            this.datas = datas;
        }

        // 총 페이지 수 리턴
        @Override
        public int getCount() {
            return datas.length;
        }

        //아답터가 만들어낼 Page(View)객체를
        //생성하는 코드를 작성하는 메소드 (page를 만들어야 할때 자동으로 호출됨)
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //container는 ViewPager를 참조하는 매개변수
            //instantiateItem 한번이 한 페이지를 만듬.
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View page= inflater.inflate(R.layout.viewpager_item, null);
            ImageView iv= page.findViewById(R.id.viewpager_item_img);
            iv.setImageResource(datas[position]);
            //기존 ListView는 return View를 해줬었다. 하지만 PagerView는 다름
            //만들어진 page(View)를 ViewPager에 붙이기...
            container.addView(page);
            // 여기선 리턴한 View객체가 저 아래 isViewFromObject()메소드에 전달됨.
            return page;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }


}
