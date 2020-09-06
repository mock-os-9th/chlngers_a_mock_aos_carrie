package com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.common.view.SimpleMessageDialog;
import com.Carrie.challengersproject.src.main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.nickname.interfaces.NicknameChangeView;

import org.w3c.dom.Text;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ChangeNicknameFragment extends Fragment implements NicknameChangeView {
    ViewGroup viewGroup;
    a_MainActivity mainActivity;
    boolean Can_Change = false;

    final ChangeNicknameService changeNicknameService = new ChangeNicknameService(this);

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (a_MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.changenickname_fragment, container, false);

        ImageButton back_btn = viewGroup.findViewById(R.id.change_nickname_ib_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(7);
            }
        });

        final TextView nickname_alert_msg = viewGroup.findViewById(R.id.change_nickname_alert_text);
        final EditText nickname_enter = viewGroup.findViewById(R.id.change_nickname_et);

        nickname_enter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(nickname_enter.length() <3)
                {
                    nickname_alert_msg.setText("닉네임을 3자 이상 입력해 주세요");
                    Can_Change = false;
                }
                else
                {
                    nickname_alert_msg.setText("사용가능한 닉네임입니다.");
                    Can_Change = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // Can_Change 가 true 면, 버튼을 클릭시 통신을 시도하고, 성공시 전 페이지로 넘어간다.
        Button confirm_btn = viewGroup.findViewById(R.id.change_nickname_change_confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Can_Change)
                {
                    tryPatchTest(nickname_enter.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "닉네임을 3자 이상 입력해 주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });

        return viewGroup;
    }
    private void tryPatchTest(String nick_name)
    {
        changeNicknameService.PatchChangeNickname(nick_name);
    }


    @Override
    public void NicknameChangeSuccess(String text) {
        // setText 같은 뭘 해줄지 쓰면 된다.
        Log.d("닉네임 수정 :: ", text);
        mainActivity.onChangeFragment(7);
    }

    @Override
    public void NicknameChangeFailure(String message) {
        Log.d("닉네임 수정 :: ", message);
    }
}
