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

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.Circle_ViewHolder> {

    private ArrayList<String> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    //전달된 객체 저장할 변수
    private CircleAdapter.OnItemClickListener mListener = null;
    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(CircleAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class Circle_ViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView text;
        protected ImageView image;

        public Circle_ViewHolder(View view)
        {
            super(view);
            this.text = view.findViewById(R.id.circle_item_tv);
            this.image = view.findViewById(R.id.circle_item_iv);
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
        }
    }

    public CircleAdapter(ArrayList<String> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public CircleAdapter.Circle_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_circle_item,parent,false);
        CircleAdapter.Circle_ViewHolder viewHolder = new CircleAdapter.Circle_ViewHolder(view);
        this.context =parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CircleAdapter.Circle_ViewHolder holder, int position) {

        holder.text.setText(mList.get(position));
        if(position % 5 == 0)
        {
            Drawable drawable = context.getResources().getDrawable(R.drawable.circle_dum5);
            holder.image.setImageDrawable(drawable);
        }else if(position % 5 ==1)
        {
            Drawable drawable = context.getResources().getDrawable(R.drawable.circle_dum4);
            holder.image.setImageDrawable(drawable);
        }else if(position % 5 ==2)
        {
            Drawable drawable = context.getResources().getDrawable(R.drawable.circle_dum3);
            holder.image.setImageDrawable(drawable);
        }else if(position % 5 ==3)
        {
            Drawable drawable = context.getResources().getDrawable(R.drawable.circle_dum2);
            holder.image.setImageDrawable(drawable);
        }else if(position % 5 == 4)
        {
            Drawable drawable = context.getResources().getDrawable(R.drawable.circle_dum1);
            holder.image.setImageDrawable(drawable);
        }

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size():0);
    }
}
