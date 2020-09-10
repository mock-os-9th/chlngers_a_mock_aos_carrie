package com.Carrie.challengersproject.src.Search_Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Carrie.challengersproject.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Popular_ViewHolder>  {

    private ArrayList<Challenge_Item> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    //전달된 객체 저장할 변수
    private PopularAdapter.OnItemClickListener mListener = null;
    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(PopularAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class Popular_ViewHolder extends RecyclerView.ViewHolder
    {
        protected ImageView CHALLENGE_IMG;
        protected TextView CHALLENGE_TITLE;
        protected TextView CHALLENGE_STAR;
        protected TextView CHALLENGE_NOW_PEOPLE;
        protected TextView CHALLENGE_NOW_PERIOD;
        protected TextView CHALLENGE_NOW_WEEK;
        protected TextView CHALLENGE_NOW_CYCLE;
        protected TextView CHALENGE_ORDER;
        protected ImageView CHECK_BOOKMARK;

        public Popular_ViewHolder(View view)
        {
            super(view);
            this.CHALLENGE_IMG = view.findViewById(R.id.popular_item_iv);

            this.CHALLENGE_TITLE = view.findViewById(R.id.popular_item_title);
            this.CHALLENGE_STAR = view.findViewById(R.id.popular_item_star);
            this.CHALLENGE_NOW_PEOPLE =view.findViewById(R.id.popular_item_now_people_count);
            this.CHALLENGE_NOW_PERIOD = view.findViewById(R.id.popular_item_period);
            this.CHALLENGE_NOW_WEEK = view.findViewById(R.id.popular_item_week);
            this.CHALLENGE_NOW_CYCLE = view.findViewById(R.id.popular_item_days);
            this.CHALENGE_ORDER = view.findViewById(R.id.popular_item_num);

            // 뷰홀더가 만들어지는 시점에 클릭 이벤트 처리리
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        mListener.onItemClick(v,pos);
                    }
                }
            });

//            CHECK_BOOKMARK.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // 북마크 처리
//                    Drawable drawable = context.getResources().getDrawable(R.drawable.a_bookmark);
//                    CHECK_BOOKMARK.setImageDrawable(drawable);
//                }
//            });
        }
    }
    // 생성자에서 List 객체를 전달
    public PopularAdapter(ArrayList<Challenge_Item> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public PopularAdapter.Popular_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_popular_item,parent,false);
        PopularAdapter.Popular_ViewHolder viewHolder = new PopularAdapter.Popular_ViewHolder(view);
        this.context =parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Popular_ViewHolder holder, int position) {

        String imgurl = mList.get(position).getImg_url();
        Glide.with(this.context).load(imgurl).into(holder.CHALLENGE_IMG);

        holder.CHALLENGE_TITLE.setText(mList.get(position).getTitle());
        holder.CHALLENGE_STAR.setText(mList.get(position).getStar());
        holder.CHALLENGE_NOW_PEOPLE.setText(mList.get(position).getNow_people());
        holder.CHALLENGE_NOW_PERIOD.setText(mList.get(position).getDate());
        holder.CHALLENGE_NOW_WEEK.setText(mList.get(position).getDuration());
        holder.CHALLENGE_NOW_CYCLE.setText(mList.get(position).getCycle());
//        holder.CHALENGE_ORDER.setText(String.valueOf(position));
        holder.CHALENGE_ORDER.setText(String.valueOf(mList.get(position).getNum()+1));
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size():0);
    }
}
