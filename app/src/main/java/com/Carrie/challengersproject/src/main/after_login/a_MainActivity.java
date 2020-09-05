package com.Carrie.challengersproject.src.main.after_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.camera.CameraFragment;
import com.Carrie.challengersproject.src.main.mypage.interfaces.MypageFragmentView;
import com.Carrie.challengersproject.src.main.mypage.models.MypageResponse;
import com.Carrie.challengersproject.src.main.mypage.setting.ChangeProfileFragment;
import com.Carrie.challengersproject.src.main.feed.FeedFragment;
import com.Carrie.challengersproject.src.main.new_.NewFragment;
import com.Carrie.challengersproject.src.main.search.SearchFragment;
import com.Carrie.challengersproject.src.main.mypage.follower_ing.FollowerFragment;
import com.Carrie.challengersproject.src.main.mypage.follower_ing.FollowingFragment;
import com.Carrie.challengersproject.src.main.mypage.MypageFragment;
import com.Carrie.challengersproject.src.main.mypage.setting.SettingFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

// 로그인 되어 있을 때 넘어 오는 액티비티
// 액티비티 넘어 올때 로딩 등 있다. base activity 추후에 상속
public class a_MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    SearchFragment searchFragment;
    CameraFragment cameraFragment;
    FeedFragment feedFragment;
    MypageFragment mypageFragment;
    NewFragment newFragment;
    SettingFragment settingFragment;
    ChangeProfileFragment changeFragment;
    FloatingActionButton fab;
    FollowerFragment followerFragment;
    FollowingFragment followingFragment;

    // my_page fragment
    Fragment radarChart;
    ImageButton mypage_settingbtn  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_main);

        fab =findViewById(R.id.activity_a_fab);
        bottomNavigationView = findViewById(R.id.activity_a_bnv);
        bottomNavigationView.setItemIconTintList(null);

        searchFragment = new SearchFragment();
        cameraFragment = new CameraFragment();
        feedFragment = new FeedFragment();
        mypageFragment = new MypageFragment();
        newFragment = new NewFragment();
        settingFragment = new SettingFragment();
        changeFragment = new ChangeProfileFragment();
        followerFragment = new FollowerFragment();
        followingFragment = new FollowingFragment();


        // 제일 처음 띄어줄 뷰 세팅
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_a_fl,cameraFragment).commitAllowingStateLoss();

        // 아이콘 선택시 원하는 프레그먼트 띄우기 - 리스너 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.a_searchItem: {
                        fab.setVisibility(View.VISIBLE);
                        fab.setImageResource(R.drawable.a_fab_search);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, searchFragment).commitAllowingStateLoss();

                        return true;
                    }
                    case R.id.a_cameraItem: {
                        fab.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, cameraFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.a_feedItem: {
                        fab.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, feedFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.a_mypageItem: {
                        fab.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, mypageFragment).commitAllowingStateLoss();
                        // manageMypage();
                        return true;
                    }
                    case R.id.a_newItem: {
                        fab.setVisibility(View.VISIBLE);
                        fab.setImageResource(R.drawable.a_fab_new);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, newFragment).commitAllowingStateLoss();

                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
    }
    public void onChangeFragment(int index)
    {
        if(index == 5)
        {
            // 5번일 때는 setting
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_a_fl,settingFragment).commitAllowingStateLoss();
        }
        if(index == 6)
        {
            // 6번일 때는  Mypage로
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_a_fl,mypageFragment).commitAllowingStateLoss();
        }
        if(index == 7)
        {
            // 7번일 때는 changeprofile로
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_a_fl,changeFragment).commitAllowingStateLoss();
        }
        if(index == 8)
        {
            // 7번일 때는 follow 프레그 먼트
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_a_fl,followerFragment).commitAllowingStateLoss();
        }
        if(index == 9)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_a_fl,followingFragment).commitAllowingStateLoss();
        }
    }


}
