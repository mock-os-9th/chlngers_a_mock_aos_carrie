package com.Carrie.challengersproject.src.main.mypage.setting.profile.introduce;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.introduce.interfaces.IntroduceChangeView;

public class IntroduceFragment extends Fragment implements IntroduceChangeView {

    ViewGroup viewGroup;
    a_MainActivity mainActivity;
    final IntroduceChangeService introduceChangeService = new IntroduceChangeService(this);

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
        viewGroup = (ViewGroup)inflater.inflate(R.layout.introduce_fragment,container,false);

        // 뒤로가기 버튼
        ImageButton back_btn = viewGroup.findViewById(R.id.change_introduce_ib_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(7);
            }
        });

        // 에딧 텍스트
        final EditText introduce_edit = viewGroup.findViewById(R.id.change_introduce_et);


        // 완료 텍스트 뷰 버튼
        TextView finish_btn = viewGroup.findViewById(R.id.change_introduce_tv_complete_btn);
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = introduce_edit.getText().toString();
                tryPatchIntroduceChangeTest(result);
            }
        });



        return viewGroup;
    }

    private void tryPatchIntroduceChangeTest(String introduce)
    {
        introduceChangeService.PatchIntroduceIn(introduce);
    }

    @Override
    public void IntroduceChangeSuccess(String text) {
        Log.d("소개 수정::",text);
        mainActivity.onChangeFragment(7);
    }

    @Override
    public void IntroduceChangeFailure(String message) {
        Log.d("소개 수정::",message);
    }
}
