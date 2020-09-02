package com.Carrie.challengersproject.src.main;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import com.github.mikephil.charting.charts.RadarChart;

public class MypageFragment extends Fragment  {

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
        viewGroup = (ViewGroup) inflater.inflate(R.layout.mypage_fragment,container,false);
        ImageButton setting_btn = viewGroup.findViewById(R.id.myPage_fragment_ib_setting);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(5);
            }
        });

        Button follower_btn = viewGroup.findViewById(R.id.myPage_fragment_btn_follower);
        follower_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(8);
            }
        });

        Button following_btn = viewGroup.findViewById(R.id.myPage_fragment_btn_following);
        following_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(9);
            }
        });



        return viewGroup;
    }
}
