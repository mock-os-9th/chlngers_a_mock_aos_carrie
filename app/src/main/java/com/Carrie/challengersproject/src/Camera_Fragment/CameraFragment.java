package com.Carrie.challengersproject.src.Camera_Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Camera_Fragment.interfaces.MyCertifyView;
import com.Carrie.challengersproject.src.Camera_Fragment.models.MyCertifyResponse;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;

import java.util.ArrayList;

public class CameraFragment extends Fragment implements MyCertifyView {
    ViewGroup viewGroup;
    a_MainActivity mainActivity;

    TextView tv_possible_certify;
    TextView tv_all;


    RecyclerView recyclerView;
    MyCertifyChallengeAdapter myCertifyChallengeAdapter;
    ArrayList mArrayList;

    final MyCertifyService myCertifyService = new MyCertifyService(this);

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
        viewGroup = (ViewGroup) inflater.inflate(R.layout.camera_fragment,container,false);

        tv_possible_certify = viewGroup.findViewById(R.id.certify_tv_possible);
        tv_all = viewGroup.findViewById(R.id.certify_tv_all);


        final int color_gray_dark = getResources().getColor(R.color.colorPrimaryDark);
        final int color_primary_pink =  getResources().getColor(R.color.colorPrimary);

        tv_possible_certify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_possible_certify.setTextColor(color_primary_pink);
                tv_all.setTextColor(color_gray_dark);
            }
        });

        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_all.setTextColor(color_primary_pink);
                tv_possible_certify.setTextColor(color_gray_dark);
            }
        });

        // 리사이클러 뷰에 연결
        // 통신해서 받아 오는 얘들을 리스트에 넣고 어댑터에 연결해야 한다.
        // 어댑터에 연ㄹ결하기 위해서 한


        recyclerView = viewGroup.findViewById(R.id.certify_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        mArrayList = new ArrayList<>();

        tryMyCertifyTest();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        return viewGroup;
    }

    private void tryMyCertifyTest() {
        myCertifyService.getCertifyTest();
    }

    @Override
    public void MyCertifySuccess(MyCertifyResponse myCertifyResponse) {

        String t_title, p_period, v_viewname, p_possibletime, a_achievementrate;

        for(int i = 0; i< myCertifyResponse.getPossibleCertificationResult().size(); i++)
        {
            t_title = myCertifyResponse.getPossibleCertificationResult().get(i).getTitle();
            p_period = myCertifyResponse.getPossibleCertificationResult().get(i).getPeriod();
            v_viewname = myCertifyResponse.getPossibleCertificationResult().get(i).getViewName();
            p_possibletime = myCertifyResponse.getPossibleCertificationResult().get(i).getPossibleTime();
            a_achievementrate = myCertifyResponse.getPossibleCertificationResult().get(i).getAchievementRate();

            MyCertifyChallengeItem myCertifyChallengeItem = new MyCertifyChallengeItem(t_title,p_period,v_viewname,p_possibletime,a_achievementrate);
            mArrayList.add(myCertifyChallengeItem);
        }

        myCertifyChallengeAdapter = new MyCertifyChallengeAdapter(mArrayList);
        recyclerView.setAdapter(myCertifyChallengeAdapter);

        myCertifyChallengeAdapter.setOnItemClickListener(new MyCertifyChallengeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // 챌린지 정보로 이동.
            }
        });

        myCertifyChallengeAdapter.notifyDataSetChanged();

    }

    @Override
    public void MyCertifyFailure(String message) {

    }
}
