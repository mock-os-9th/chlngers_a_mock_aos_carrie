package com.Carrie.challengersproject.src.main.search;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.main.search.Challenge_Adapter;
import com.Carrie.challengersproject.src.main.search.Challenge_Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SearchFragment extends Fragment {
    ViewGroup viewGroup;
    FloatingActionButton fab;

    a_MainActivity mainActivity;
    private ArrayList<Challenge_Item> mArrayList;
    private Challenge_Adapter mAdapter;

    ViewPager viewPager;
    int images[] ={R.drawable.banner01, R.drawable.banner02};
    BannerAdpater bannerAdpater;
    int currentPage = 0;
    Timer timer;
    int NumPages = images.length;
    long Delay_MS = 3000; // 오토 플립용 타이머 시작 후 해당 시간에 작동
    long Period_Ms = 5000; // 5초 주기로 작동동

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

        viewGroup = (ViewGroup) inflater.inflate(R.layout.search_fragment,container,false);

        RecyclerView recyclerView = viewGroup.findViewById(R.id.search_fm_recyclerview_recommand_item);
        RecyclerView recyclerView1 = viewGroup.findViewById(R.id.search_fm_recyclerview_exercise_item);
        RecyclerView recyclerView2 = viewGroup.findViewById(R.id.search_fm_recyclerview_study_item);
        RecyclerView recyclerView3 = viewGroup.findViewById(R.id.search_fm_recyclerview_know_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView3.setLayoutManager(linearLayoutManager3);

        mArrayList = new ArrayList<>();
        //dummy
        for(int i = 0; i<2; i++)
        {
            Challenge_Item challenge_item = new Challenge_Item("https://cdn.pixabay.com/photo/2019/08/01/12/36/illustration-4377408_960_720.png","경제기사 읽기","4.98"
                    ,"현재 106명 신청"," 8/31/월 - 9/13/일"," 2주 ","주 3일");
            Challenge_Item challenge_item1 = new Challenge_Item("https://cdn.pixabay.com/photo/2019/08/06/02/16/mountains-4387209_960_720.jpg","아침 8시 일어나기","4.98"
                    ,"현재 1002명 신청"," 8/31/월 - 9/27/일"," 4주 ","주 7일");

            mArrayList.add(challenge_item);
            mArrayList.add(challenge_item1);
        }

        mAdapter = new Challenge_Adapter(mArrayList);
        recyclerView.setAdapter(mAdapter);
        recyclerView1.setAdapter(mAdapter);
        recyclerView2.setAdapter(mAdapter);
        recyclerView3.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        viewPager = viewGroup.findViewById(R.id.search_fm_banner);
        BannerAdpater bannerAdpater = new BannerAdpater(getContext(),images);
        viewPager.setAdapter(bannerAdpater);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter.setOnItemClickListener(new Challenge_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //아이템 클릭시
            }
        });
        return viewGroup;
    }

    @Override
    public void onResume() {
        super.onResume();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                currentPage = viewPager.getCurrentItem();
                int nextPage = currentPage+1;

                if(nextPage >= NumPages)
                {
                    nextPage = 0;
                }
                viewPager.setCurrentItem(nextPage,true);
                currentPage = nextPage;
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },Delay_MS,Period_Ms);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(timer!=null)
        {
            timer.cancel();
            timer = null;
        }
    }

    class BannerAdpater extends PagerAdapter
    {

        int[] datas;
        LayoutInflater inflater;
        Context mContext;

        public BannerAdpater(Context mContext, int[] datas) {
            this.mContext = mContext;
            this.datas = datas;
        }

        // 총 페이지 수 리턴
        @Override
        public int getCount() {
            return datas.length;
        }

        //아답터가 만들어낼 Page(View)객체를
        //생성하는 코드를 작성하는 메소드 (page를 만들어야 할때 자동으로 호출됨)
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //container는 ViewPager를 참조하는 매개변수
            //instantiateItem 한번이 한 페이지를 만듬.
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View page= inflater.inflate(R.layout.viewpager_item, null);
            ImageView iv= page.findViewById(R.id.viewpager_item_img);
            iv.setImageResource(datas[position]);
            //기존 ListView는 return View를 해줬었다. 하지만 PagerView는 다름
            //만들어진 page(View)를 ViewPager에 붙이기...
            container.addView(page);
            // 여기선 리턴한 View객체가 저 아래 isViewFromObject()메소드에 전달됨.
            return page;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }


}
