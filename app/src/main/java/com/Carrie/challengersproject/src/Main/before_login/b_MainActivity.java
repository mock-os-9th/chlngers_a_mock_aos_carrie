package com.Carrie.challengersproject.src.Main.before_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.common.view.PopupInfoDialog;
import com.Carrie.challengersproject.src.Camera_Fragment.CameraFragment;
import com.Carrie.challengersproject.src.Profile_Fragment.ChangeProfileFragment;
import com.Carrie.challengersproject.src.Feed_Fragment.FeedFragment;
import com.Carrie.challengersproject.src.Mypage_Fragment.follower_ing.FollowerFragment;
import com.Carrie.challengersproject.src.Mypage_Fragment.follower_ing.FollowingFragment;
import com.Carrie.challengersproject.src.Setting_Fragment.SettingFragment;
import com.Carrie.challengersproject.src.Register.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


// 로그인 되어 있지 않을 때 넘어 오는 액티비티
// 액티비티 넘어 올때 로딩 등 있다. base activity 추후에 상속
public class b_MainActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    b_MypageFragment mypageFragment;
    FloatingActionButton fab;


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

        mypageFragment = new b_MypageFragment();


       // 팝업 - 이 팝업 안에서 다른 팝업으로 넘어간다.
        PopupInfoDialog popupInfoDialog = new PopupInfoDialog(b_MainActivity.this);
        popupInfoDialog.callFunction();

        // 제일 처음 띄어줄 뷰 세팅
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_b_fl, mypageFragment).commitAllowingStateLoss();

        // 아이콘 선택시 원하는 프레그먼트 띄우기 - 리스너 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.b_searchItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_b_fl, mypageFragment).commitAllowingStateLoss();

                        return true;
                    }
                    case R.id.b_cameraItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_b_fl, mypageFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.b_feedItem: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_b_fl, mypageFragment).commitAllowingStateLoss();
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
