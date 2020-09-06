package com.Carrie.challengersproject.src.main.mypage.setting.profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.interfaces.UserDeleteView;

import static com.Carrie.challengersproject.ApplicationClass.sSharedPreferences;

public class ChangeProfileFragment extends Fragment implements UserDeleteView {

    ViewGroup viewGroup;
    a_MainActivity mainActivity;

    Button user_delete_btn;

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

        ImageButton back_btn = viewGroup.findViewById(R.id.change_profile_ib_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(5);
            }
        });

        // 회원탈퇴 버튼
        user_delete_btn = viewGroup.findViewById(R.id.change_profile_exit_btn);
        user_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 통신 시작
                 tryUserDelete();
            }
        });

        // 닉네임 누를 때 -> 수정으로 이동


        // 소개 누를 때 -> 소개 하로

        // 비밀 번호 누를 때 ->

        // 사진 누를 때 -> 프로필 사진 바꾸로

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
}
