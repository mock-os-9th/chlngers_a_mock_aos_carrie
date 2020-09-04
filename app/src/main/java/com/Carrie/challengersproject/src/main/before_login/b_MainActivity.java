package com.Carrie.challengersproject.src.main.before_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.common.view.PopupInfoDialog;
import com.Carrie.challengersproject.src.main.camera.CameraFragment;
import com.Carrie.challengersproject.src.main.mypage.ChangeProfileFragment;
import com.Carrie.challengersproject.src.main.feed.FeedFragment;
import com.Carrie.challengersproject.src.main.mypage.FollowerFragment;
import com.Carrie.challengersproject.src.main.mypage.FollowingFragment;
import com.Carrie.challengersproject.src.main.mypage.SettingFragment;
import com.Carrie.challengersproject.src.register.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


// 로그인 되어 있지 않을 때 넘어 오는 액티비티
// 액티비티 넘어 올때 로딩 등 있다. base activity 추후에 상속
public class b_MainActivity extends AppCompatActivity  {
    BottomNavigationView bottomNavigationView;
    b_SearchFragment searchFragment;
    CameraFragment cameraFragment;
    FeedFragment feedFragment;
    b_MypageFragment mypageFragment;
    FloatingActionButton fab;

    SettingFragment settingFragment;
    ChangeProfileFragment changeFragment;
    FollowerFragment followerFragment;
    FollowingFragment followingFragment;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_main);
        fab = findViewById(R.id.activity_b_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(b_MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });



        bottomNavigationView = findViewById(R.id.activity_b_bnv);
        bottomNavigationView.setItemIconTintList(null);

        searchFragment = new b_SearchFragment();
        cameraFragment = new CameraFragment();
        feedFragment = new FeedFragment();
        mypageFragment = new b_MypageFragment();
        settingFragment = new SettingFragment();
        changeFragment = new ChangeProfileFragment();
        followerFragment = new FollowerFragment();
        followingFragment = new FollowingFragment();

       // 팝업 - 이 팝업 안에서 다른 팝업으로 넘어간다.
        PopupInfoDialog popupInfoDialog = new PopupInfoDialog(b_MainActivity.this);
        popupInfoDialog.callFunction();

        // 제일 처음 띄어줄 뷰 세팅
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_b_fl, searchFragment).commitAllowingStateLoss();

        // 아이콘 선택시 원하는 프레그먼트 띄우기 - 리스너 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.b_searchItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_b_fl, searchFragment).commitAllowingStateLoss();

                        return true;
                    }
                    case R.id.b_cameraItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_b_fl, cameraFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.b_feedItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_b_fl, feedFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.b_mypageItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_b_fl, mypageFragment).commitAllowingStateLoss();
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
