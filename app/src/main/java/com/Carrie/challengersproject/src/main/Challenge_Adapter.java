package com.Carrie.challengersproject.src.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Carrie.challengersproject.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Challenge_Adapter extends RecyclerView.Adapter<Challenge_Adapter.Challenge_ViewHolder> {

    private ArrayList<Challenge_Item> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    //전달된 객체 저장할 변수
    private Challenge_Adapter.OnItemClickListener mListener = null;
    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(Challenge_Adapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class Challenge_ViewHolder extends RecyclerView.ViewHolder
    {
        protected ImageView CHALLENGE_IMG;
        protected ImageView CHECK_BOOKMARK;

        protected TextView CHALLENGE_NAME;
        protected TextView CHALLENGE_STAR;
        protected TextView CHALLENGE_NOW_PEOPLE;
        protected TextView CHALLENGE_NOW_DATE;
        protected TextView CHALLENGE_NOW_DURATION;
        protected TextView CHALLENGE_NOW_CYCLE;

        public Challenge_ViewHolder(View view)
        {
            super(view);
            this.CHALLENGE_IMG = (ImageView)view.findViewById(R.id.search_recommand_item_iv);
            this.CHECK_BOOKMARK = (ImageView)view.findViewById(R.id.search_recommand_item_bookmark);
            this.CHALLENGE_NAME = view.findViewById(R.id.search_recommand_item_tv_title);
            this.CHALLENGE_STAR = view.findViewById(R.id.search_recommand_item_tv_start);
            this.CHALLENGE_NOW_PEOPLE =view.findViewById(R.id.search_recommand_item_tv_now_people);
            this.CHALLENGE_NOW_DATE = view.findViewById(R.id.search_recommand_item_tv_date);
            this.CHALLENGE_NOW_DURATION = view.findViewById(R.id.search_recommand_item_tv_duration);
            this.CHALLENGE_NOW_CYCLE = view.findViewById(R.id.search_recommand_item_tv_cycle);

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

            CHECK_BOOKMARK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 북마크 처리
                }
            });
        }
    }

    // 생성자에서 List 객체를 전달
    public Challenge_Adapter(ArrayList<Challenge_Item> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public Challenge_Adapter.Challenge_ViewHolder  onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_recommand_item,viewGroup,false);
        Challenge_Adapter.Challenge_ViewHolder viewHolder = new Challenge_Adapter.Challenge_ViewHolder(view);
        this.context =viewGroup.getContext();
        return viewHolder;
    }

    // position 에 해당하는 데이터를 뷰홀더의 아이템 뷰에 표시한다.
    @Override
    public void onBindViewHolder(@NonNull Challenge_Adapter.Challenge_ViewHolder viewholder, int position) {
        String imgurl = mList.get(position).getImg_url();
        Glide.with(this.context).load(imgurl).into(viewholder.CHALLENGE_IMG);

        viewholder.CHALLENGE_NAME.setText(mList.get(position).getTitle());
        viewholder.CHALLENGE_STAR.setText(mList.get(position).getStar());
        viewholder.CHALLENGE_NOW_PEOPLE.setText(mList.get(position).getNow_people());
        viewholder.CHALLENGE_NOW_DATE.setText(mList.get(position).getDate());
        viewholder.CHALLENGE_NOW_DURATION.setText(mList.get(position).getDuration());
        viewholder.CHALLENGE_NOW_CYCLE.setText(mList.get(position).getCycle());

    }

    @Override
    public int getItemCount() {
        // mList 비어 있으면 0리턴, 아니면 mList 사이즈 리턴
        return (null != mList ? mList.size():0);

    }
}
