package com.Carrie.challengersproject.src.Feed_Fragment;

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
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.Search_Fragment.SearchService;
import com.Carrie.challengersproject.src.Search_Fragment.interfaces.SearchActivityView;
import com.Carrie.challengersproject.src.Search_Fragment.models.SearchResponse;

import java.util.ArrayList;

public class FeedFragment extends Fragment implements SearchActivityView {
    ViewGroup viewGroup;
    a_MainActivity mainActivity;

    RecyclerView challenge_1;
    RecyclerView every_challenge;
    RecyclerView follower;

    FeedItemAdapter challenge_1_Adapter;
    FeedItemAdapter every_challenge_Adapter;
    FeedItemAdapter follower_Adapter;

    ArrayList challenge_1_list;
    ArrayList every_challenge_list;
    ArrayList follower_list;


    SearchService searchService = new SearchService(this);


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (a_MainActivity) getActivity();
        trySearch();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.feed_fragment,container,false);

        challenge_1 = viewGroup.findViewById(R.id.feed_fm_recycler_view);
        every_challenge = viewGroup.findViewById(R.id.feed_fm_recycler_view_every);
        follower = viewGroup.findViewById(R.id.feed_fm_recycler_view_follow);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);


        challenge_1.setLayoutManager(linearLayoutManager);
        every_challenge.setLayoutManager(linearLayoutManager1);
        follower.setLayoutManager(linearLayoutManager2);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(challenge_1.getContext(),
                linearLayoutManager.getOrientation());
        challenge_1.addItemDecoration(dividerItemDecoration);
        return viewGroup;
    }

    void trySearch()
    {
        searchService.getSearchTest();
    }

    @Override
    public void SearchSuccess(SearchResponse searchResponse) {

        challenge_1_list = new ArrayList<>();
        for(int i = 0; i<searchResponse.getSearch().get(0).getChallenges().size(); i++)
        {
            String url= searchResponse.getSearch().get(0).getChallenges().get(i).getImageUrl();
            challenge_1_list.add(url);
        }

        every_challenge_list = new ArrayList<>();
        for(int i = 0; i<searchResponse.getSearch().get(3).getChallenges().size(); i++)
        {
            String url= searchResponse.getSearch().get(3).getChallenges().get(i).getImageUrl();
            every_challenge_list.add(url);
        }

        follower_list = new ArrayList<>();
        for(int i = 0; i<searchResponse.getSearch().get(2).getChallenges().size(); i++)
        {
            String url= (searchResponse.getSearch().get(2).getChallenges().get(i).getImageUrl());
            follower_list.add(url);
        }

        challenge_1_Adapter = new FeedItemAdapter(challenge_1_list);
        every_challenge_Adapter = new FeedItemAdapter(every_challenge_list);
        follower_Adapter = new FeedItemAdapter(follower_list);

        challenge_1_Adapter.setOnItemClickListener(new FeedItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        every_challenge_Adapter.setOnItemClickListener(new FeedItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        follower_Adapter.setOnItemClickListener(new FeedItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        challenge_1_Adapter.notifyDataSetChanged();
        every_challenge_Adapter.notifyDataSetChanged();
        follower_Adapter.notifyDataSetChanged();

        challenge_1.setAdapter(challenge_1_Adapter);
        every_challenge.setAdapter(every_challenge_Adapter);
        follower.setAdapter(follower_Adapter);

    }

    @Override
    public void SearchFailure(SearchResponse searchResponse) {

    }
}
