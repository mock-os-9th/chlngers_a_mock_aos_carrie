package com.Carrie.challengersproject.src.Setting_Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;

public class SettingFragment extends Fragment {
    ViewGroup viewGroup;
    a_MainActivity mainActivity;

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
        viewGroup = (ViewGroup) inflater.inflate(R.layout.setting_fragment, container, false);
        ImageButton back_btn = viewGroup.findViewById(R.id.setting_ib_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(6);
            }
        });

        Button change_profile_btn = viewGroup.findViewById(R.id.setting_btn_change_profile);
        change_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(7);
            }
        });
        return viewGroup;
    }
}
