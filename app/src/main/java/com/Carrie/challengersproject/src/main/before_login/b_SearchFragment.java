package com.Carrie.challengersproject.src.main.before_login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.search.Challenge_Adapter;
import com.Carrie.challengersproject.src.main.search.Challenge_Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class b_SearchFragment extends Fragment {
    ViewGroup viewGroup;
    FloatingActionButton fab;

    b_MainActivity mainActivity;
    private ArrayList<Challenge_Item> mArrayList;
    private Challenge_Adapter mAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (b_MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.new_fragment,container,false);

//        RecyclerView recyclerView = viewGroup.findViewById(R.id.search_fm_recyclerview_recommand_item);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        mArrayList = new ArrayList<>();
//        //dummy
//        for(int i = 0; i<2; i++)
//        {
//            Challenge_Item challenge_item = new Challenge_Item("https://cdn.pixabay.com/photo/2019/08/01/12/36/illustration-4377408_960_720.png","경제기사 읽기","4.98"
//                    ,"현재 106명 신청"," 8/31/월 - 9/13/일"," 2주 ","주 3일");
//            Challenge_Item challenge_item1 = new Challenge_Item("https://cdn.pixabay.com/photo/2019/08/06/02/16/mountains-4387209_960_720.jpg","아침 8시 일어나기","4.98"
//                    ,"현재 1002명 신청"," 8/31/월 - 9/27/일"," 4주 ","주 7일");
//
//            mArrayList.add(challenge_item);
//            mArrayList.add(challenge_item1);
//        }
//
//        mAdapter = new Challenge_Adapter(mArrayList);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                linearLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//        mAdapter.setOnItemClickListener(new Challenge_Adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int pos) {
//                //아이템 클릭시
//            }
//        });

        return viewGroup;
    }
}
