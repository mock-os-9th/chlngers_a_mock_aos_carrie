package com.Carrie.challengersproject.src.Main.before_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Login.LoginActivity;
import com.Carrie.challengersproject.src.Register.RegisterActivity;
import com.Carrie.challengersproject.src.common.view.PopupSearchDialog;
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

    public class PopupInfoDialog {
        private Context context;

        public PopupInfoDialog(Context context) {
            this.context = context;
        }

        //호출할 다이얼로그 함수
        public void callFunction()
        {
            final Dialog dig = new Dialog(context);
            dig.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dig.setContentView(R.layout.dialog_popup_info);
            dig.show();

            // 커스텀 다이얼로그 위젯 정의
            Button exitBtn = dig.findViewById(R.id.dialog_popup_info_exit_btn);
            TextView loginBtn = dig.findViewById(R.id.dialog_popup_info_login_tv_btn);

            TextView title1 = dig.findViewById(R.id.dialog_popup_info_title_line1);
            TextView title2 = dig.findViewById(R.id.dialog_popup_info_title_line2);
            TextView contents1 = dig.findViewById(R.id.dialog_popup_info_contents_line1);
            TextView contents2 = dig.findViewById(R.id.dialog_popup_info_contents_line2);
            TextView contents3 = dig.findViewById(R.id.dialog_popup_info_contents_line3);
            TextView contents4 = dig.findViewById(R.id.dialog_popup_info_contents_line4);
            TextView contents5 = dig.findViewById(R.id.dialog_popup_info_contents_line5);
            TextView login_ask = dig.findViewById(R.id.dialog_popup_info_login_ask);
            TextView login_btn = dig.findViewById(R.id.dialog_popup_info_login_tv_btn);

            title1.setText(R.string.popupTitle_line1);
            title2.setText(R.string.popupTitle_line2);
            contents1.setText(R.string.popupContents_line1);
            contents2.setText(R.string.popupContents_line2);
            contents3.setText(R.string.popupContents_line3);
            contents4.setText(R.string.popupContents_line4);
            contents5.setText(R.string.popupContents_line5);
            login_ask.setText(R.string.popupLogin_ask);
            login_btn.setText(R.string.popupLogin_ask_btn);





            exitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dig.dismiss();
                    PopupSearchDialog popupSearchDialog = new PopupSearchDialog(context);
                    popupSearchDialog.callFunction();
                }
            });

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dig.dismiss();
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    finish();

                }
            });
        }

    }

}
