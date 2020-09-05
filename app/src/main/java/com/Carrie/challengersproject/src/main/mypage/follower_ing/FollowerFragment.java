package com.Carrie.challengersproject.src.main.mypage.follower_ing;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.after_login.a_MainActivity;

import java.util.ArrayList;

public class FollowerFragment extends Fragment {
    ViewGroup viewGroup;
    a_MainActivity mainActivity;
    private ArrayList<Follow_ing_Item> mArrayList;
    private Follow_ing_Adapter mAdapter;

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
        viewGroup = (ViewGroup) inflater.inflate(R.layout.follower_fragment, container, false);
        ImageButton back_btn = viewGroup.findViewById(R.id.follower_ib_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(6);
            }
        });

        RecyclerView recyclerView = viewGroup.findViewById(R.id.follower_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        recyclerView.setLayoutManager(linearLayoutManager);

        mArrayList = new ArrayList<>();
        //dummy
        for(int i = 0; i<2; i++)
        {
            Follow_ing_Item follow_ing_item = new Follow_ing_Item("https://cdn.pixabay.com/photo/2016/10/09/15/21/business-man-1725976_960_720.png","가나다");
            Follow_ing_Item follow_ing_item2 = new Follow_ing_Item("https://imagescdn.gettyimagesbank.com/500/201806/jv11157747.jpg","라마바");

            mArrayList.add(follow_ing_item);
            mArrayList.add(follow_ing_item2);
        }

        mAdapter = new Follow_ing_Adapter(mArrayList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter.setOnItemClickListener(new Follow_ing_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //클릭시 아무일도 없음
            }
        });



        return viewGroup;
    }
}
