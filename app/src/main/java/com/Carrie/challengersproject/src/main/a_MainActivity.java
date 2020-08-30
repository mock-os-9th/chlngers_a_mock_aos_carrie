package com.Carrie.challengersproject.src.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.Carrie.challengersproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// 액티비티 넘어 올때 로딩 등 있다. base activity 추후에 상속
public class a_MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    SearchFragment searchFragment;
    CameraFragment cameraFragment;
    FeedFragment feedFragment;
    MypageFragment mypageFragment;
    NewFragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_main);
        bottomNavigationView = findViewById(R.id.activity_a_bnv);
        bottomNavigationView.setItemIconTintList(null);

        searchFragment = new SearchFragment();
        cameraFragment = new CameraFragment();
        feedFragment = new FeedFragment();
        mypageFragment = new MypageFragment();
        newFragment = new NewFragment();

        // 제일 처음 띄어줄 뷰 세팅
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_b_fl,cameraFragment).commitAllowingStateLoss();

        // 아이콘 선택시 원하는 프레그먼트 띄우기 - 리스너 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.a_searchItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, searchFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.a_cameraItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, cameraFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.a_feedItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, feedFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.a_mypageItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_a_fl, mypageFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.a_newItem: {
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
}
