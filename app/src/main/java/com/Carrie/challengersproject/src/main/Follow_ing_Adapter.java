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

import de.hdodenhof.circleimageview.CircleImageView;

public class Follow_ing_Adapter extends RecyclerView.Adapter<Follow_ing_Adapter.Follow_ing_ViewHolder>
{
    private ArrayList<Follow_ing_Item> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    //전달된 객체 저장할 변수
    private OnItemClickListener mListener = null;
    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class Follow_ing_ViewHolder extends RecyclerView.ViewHolder
    {
        protected ImageView PROFILE_IMG;
        protected TextView PROFILE_NAME;
        protected Button FOLLOW_BTN;
        protected ImageButton MORE_BTN;

        public Follow_ing_ViewHolder(View view)
        {
            super(view);
            this.PROFILE_IMG =(ImageView)view.findViewById(R.id.follow_item_iv);
            this.PROFILE_NAME=(TextView)view.findViewById(R.id.follow_item_tv_name);
            this.FOLLOW_BTN =(Button)view.findViewById(R.id.follow_item_follow_btn);
            this.MORE_BTN = (ImageButton)view.findViewById(R.id.follow_item_more_btn);

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

           FOLLOW_BTN.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   // 팔로우 처리
               }
           });

           MORE_BTN.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   // more 처리
               }
           });
        }
    }

    // 생성자에서 List 객체를 전달
    public Follow_ing_Adapter(ArrayList<Follow_ing_Item> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public Follow_ing_Adapter.Follow_ing_ViewHolder  onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.follow_item,viewGroup,false);
        Follow_ing_ViewHolder viewHolder = new Follow_ing_ViewHolder(view);
        this.context =viewGroup.getContext();
        return viewHolder;
    }

    // position 에 해당하는 데이터를 뷰홀더의 아이템 뷰에 표시한다.
    @Override
    public void onBindViewHolder(@NonNull Follow_ing_Adapter.Follow_ing_ViewHolder viewholder, int position) {
        String imgurl = mList.get(position).getProfile_img_url();
        Glide.with(this.context).load(imgurl).circleCrop().into(viewholder.PROFILE_IMG);

        viewholder.PROFILE_NAME.setText(mList.get(position).getProfile_nick_name());

    }

    @Override
    public int getItemCount() {
        // mList 비어 있으면 0리턴, 아니면 mList 사이즈 리턴
        return (null != mList ? mList.size():0);

    }

}
