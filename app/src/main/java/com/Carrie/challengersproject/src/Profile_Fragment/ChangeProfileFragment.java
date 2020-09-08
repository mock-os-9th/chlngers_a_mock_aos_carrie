package com.Carrie.challengersproject.src.Profile_Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.Mypage_Fragment.MypageService;
import com.Carrie.challengersproject.src.Mypage_Fragment.interfaces.MypageFragmentView;
import com.Carrie.challengersproject.src.Mypage_Fragment.models.MypageResponse;
import com.Carrie.challengersproject.src.Payment.PaymentActivity;
import com.Carrie.challengersproject.src.Profile_Fragment.interfaces.UserDeleteView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.Carrie.challengersproject.ApplicationClass.sSharedPreferences;
import static com.facebook.FacebookSdk.getApplicationContext;

public class ChangeProfileFragment extends Fragment implements UserDeleteView, MypageFragmentView {

    ViewGroup viewGroup;
    a_MainActivity mainActivity;

    Button user_delete_btn;

    ImageView imageView;
    TextView nickname;
    TextView interest;
    TextView introduce;
    TextView email;
    TextView name;
    TextView phonenum;

    MypageService mypageService = new MypageService(this);
    // onAttach 시에 Activity 에 대한 참조를 얻을 수 있다, 따라서 각각 Fragment 는 이벤트 시에 Activity 의 메소드를 호출 할 수 있다.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (a_MainActivity)getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity =null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.changeprofile_fragment,container,false);

        imageView =viewGroup.findViewById(R.id.change_profile_iv_profile);
        nickname=viewGroup.findViewById(R.id.change_profile_btn_change_nickname);
        interest=viewGroup.findViewById(R.id.change_profile_btn_change_favorite);
        introduce=viewGroup.findViewById(R.id.change_profile_btn_introduction);
        email=viewGroup.findViewById(R.id.change_profile_tv_email);
        name=viewGroup.findViewById(R.id.change_profile_tv_name);
        phonenum=viewGroup.findViewById(R.id.change_profile_btn_phone_number);

        // 값 셋팅.
        GetMypageInfo_o();

        ImageButton back_btn = viewGroup.findViewById(R.id.change_profile_ib_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(5);
            }
        });

        // 전체 마이페이지 정보 MypageService 를 통해 casting 해 놓아야 한다.

       // 회원탈퇴 버튼
        user_delete_btn = viewGroup.findViewById(R.id.change_profile_exit_btn);
        user_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mainActivity);
                alertDialog.setTitle("탈퇴화면")
                        .setMessage("정말로 탈퇴를 원하세요?")
                        .setCancelable(false)
                        .setPositiveButton("네",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // 통신 시작
                                        tryUserDelete();
                                    }
                                })
                        .setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(),"회원 탈퇴를 취소하셨습니다.",Toast.LENGTH_LONG).show();
                                    }
                                });

                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });

        // 닉네임 누를 때 -> 수정으로 이동
        Button change_nickname =viewGroup.findViewById(R.id.change_profile_btn_change_nickname);
        change_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 닉네임 변경 화면으로
                mainActivity.onChangeFragment(11);
            }
        });


        // 소개 누를 때 -> 소개 하로
        Button change_indroduce = viewGroup.findViewById(R.id.change_profile_btn_introduction);
        change_indroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(12);
            }
        });

        // 비밀 번호 누를 때 ->

        // 사진 누를 때 -> 프로필 사진 바꾸로
        ImageView change_Img = viewGroup.findViewById(R.id.change_profile_iv_profile);
        change_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(13);
            }
        });

        return viewGroup;
    }


    private void tryUserDelete()
    {
        UserDeleteService userDeleteService = new UserDeleteService(this);

        int id = sSharedPreferences.getInt("ID",0);
        Log.e("유저 삭제",String.valueOf(id));
        userDeleteService.getUserDeleteTest();
    }


    @Override
    public void UserDeleteSuccess(int code) {

        Log.d("유저 삭제 통신 성공::코드::", String.valueOf(code));
        // Intent 로 메인으로 넘기기기
        mainActivity.onChangeFragment(10);
    }
    @Override
    public void UserDeleteFailure(int code) {
        Log.d("유저 삭제 통신 실패::코드::", String.valueOf(code));
    }



    private void GetMypageInfo_o()
    {
        int id = sSharedPreferences.getInt("ID",0);
        mypageService.GetMyPageIn(id);
        Log.e("아이디(수정부분 로그)",String.valueOf(id));
        mypageService.GetMyPageIn(id);
    }


    @Override
    public void MyPageGetSuccess(MypageResponse mypageResponse) {

        String img_url = mypageResponse.getMyPageInfo().getProfileImageUrl();
        Glide.with(this).load(img_url).apply(new RequestOptions().circleCrop()).into(imageView);

        String n_nickname = mypageResponse.getMyPageInfo().getNickname();
        nickname.setText(n_nickname);
        name.setText(n_nickname);

        String total_field = "";
        for(int i = 0; i<mypageResponse.getMyPageInfo().getInterestFields().size(); i++)
        {
            total_field+="#";
            total_field+=mypageResponse.getMyPageInfo().getInterestFields().get(i);
            total_field+=", ";
        }
        interest.setText(total_field);

        String i_intro =mypageResponse.getMyPageInfo().getIntroduction();
        introduce.setText(i_intro);

        email.setText("yoon@gmail.com");

    }

    @Override
    public void MyPageGetFailure(String message) {

    }
}
