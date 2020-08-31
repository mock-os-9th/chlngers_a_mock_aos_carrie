package com.Carrie.challengersproject.src.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.common.view.PopupInfoDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;


// 로그인 되어 있지 않을 때 넘어 오는 액티비티
// 액티비티 넘어 올때 로딩 등 있다. base activity 추후에 상속
public class b_MainActivity extends AppCompatActivity  {
    BottomNavigationView bottomNavigationView;
    SearchFragment searchFragment;
    CameraFragment cameraFragment;
    FeedFragment feedFragment;
    MypageFragment mypageFragment;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_main);
        bottomNavigationView = findViewById(R.id.activity_b_bnv);
        bottomNavigationView.setItemIconTintList(null);

        searchFragment = new SearchFragment();
        cameraFragment = new CameraFragment();
        feedFragment = new FeedFragment();
        mypageFragment = new MypageFragment();

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
}
