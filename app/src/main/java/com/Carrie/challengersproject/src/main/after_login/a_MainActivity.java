package com.Carrie.challengersproject.src.main.after_login;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.Carrie.challengersproject.BaseActivity;
import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.login.LoginActivity;
import com.Carrie.challengersproject.src.main.camera.CameraFragment;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.ChangeProfileFragment;
import com.Carrie.challengersproject.src.main.feed.FeedFragment;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.UserDeleteService;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.interfaces.UserDeleteView;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.introduce.IntroduceFragment;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.ChangeNicknameFragment;
import com.Carrie.challengersproject.src.main.new_.NewFragment;
import com.Carrie.challengersproject.src.main.search.SearchFragment;
import com.Carrie.challengersproject.src.main.mypage.follower_ing.FollowerFragment;
import com.Carrie.challengersproject.src.main.mypage.follower_ing.FollowingFragment;
import com.Carrie.challengersproject.src.main.mypage.MypageFragment;
import com.Carrie.challengersproject.src.main.mypage.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.Carrie.challengersproject.ApplicationClass.sSharedPreferences;

// 로그인 되어 있을 때 넘어 오는 액티비티
// 액티비티 넘어 올때 로딩 등 있다. base activity 추후에 상속
public class a_MainActivity extends BaseActivity  {
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
    ChangeNicknameFragment changeNicknameFragment;
    IntroduceFragment introduceFragment;

    // my_page fragment
    Fragment radarChart;
    ImageButton mypage_settingbtn  ;


//    // mypage 통신 서비스

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
        // param 1 : fragment 삽입할 layout id , param2 : 삽입할 fragment
        // getSupportFragmentManager().beginTransaction().add(R.id.activity_a_fl,mypageFragment);
        //액티비티에서 통신하고 프래그 먼트에 속한 특정 UI 변화를 주는 방법으로
        newFragment = new NewFragment();


        settingFragment = new SettingFragment();
        changeFragment = new ChangeProfileFragment();
        followerFragment = new FollowerFragment();
        followingFragment = new FollowingFragment();
        changeNicknameFragment = new ChangeNicknameFragment();
        introduceFragment = new IntroduceFragment();


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
//            1)  Activity 클래스의 getFragmentManager() 함수를 사용하여 FragmentManager 에 대한 참조를 획득한 다음
//            2) FragmentManager 의 beginTransaction() 함수를 호출하여 FragmentTransaction 을 시작합니다.
//            3) 그런 다음 FragmentTransaction 의 add() 함수를 이용하여 Fragment 를 Activity 의 ViewGroup(FrameLayout)에 추가
//            4) Fragment와 관련된 모든 작업이 완료되면 FragmentTransaction의 commit() 함수를 호출하여 Fragment와 관련된 작업이 완료되었음을 알려줍니다.
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
        if(index == 10)
        {
            Intent intent = new Intent(a_MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if(index == 11)
        {
            // 프로필 닉네임 바꾸는 프레그먼트로
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_a_fl,changeNicknameFragment).commitAllowingStateLoss();
        }
        if(index == 12)
        {
            // 프로필 소개 바꾸는 프레그먼트로
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_a_fl,introduceFragment).commitAllowingStateLoss();
        }
    }

}
