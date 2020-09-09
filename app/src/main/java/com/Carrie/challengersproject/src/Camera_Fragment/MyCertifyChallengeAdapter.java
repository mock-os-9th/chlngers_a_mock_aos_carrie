package com.Carrie.challengersproject.src.Camera_Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Carrie.challengersproject.R;

import java.util.ArrayList;

public class MyCertifyChallengeAdapter extends RecyclerView.Adapter<MyCertifyChallengeAdapter.MyCertify_ViewHolder>  {

    private ArrayList<MyCertifyChallengeItem> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    //전달된 객체 저장할 변수
    private MyCertifyChallengeAdapter.OnItemClickListener mListener = null;
    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class MyCertify_ViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView TITLE;
        protected TextView PERIOD;
        protected TextView VIEWNAME;
        protected TextView POSSIBLETIME;
        protected TextView ACHIEVEMENTRATE;
        protected ImageButton CERTIFY_BTN;

        public MyCertify_ViewHolder(View view)
        {
            super(view);
            this.TITLE = view.findViewById(R.id.certify_item_title);
            this.PERIOD = view.findViewById(R.id.certify_item_period);
            this.VIEWNAME = view.findViewById(R.id.certify_item_viewname);
            this.POSSIBLETIME = view.findViewById(R.id.certify_item_possible_time);
            this.ACHIEVEMENTRATE = view.findViewById(R.id.certift_item_achievementRate);
            this.CERTIFY_BTN = view.findViewById(R.id.certify_item_btn_certify);

            // 뷰홀더가 만들어지는 시점에 클릭 이벤트 처리
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

            // ? 인증하기 버튼을 누를시에??
            CERTIFY_BTN.setOnClickListener(new View.OnClickListener() {
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

    // 생성자에서 전달
    public MyCertifyChallengeAdapter(ArrayList<MyCertifyChallengeItem> mList) {
        this.mList = mList; }

    @NonNull
    @Override
    public MyCertify_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.certifyitem,viewGroup,false);
        MyCertifyChallengeAdapter.MyCertify_ViewHolder viewHolder = new  MyCertifyChallengeAdapter.MyCertify_ViewHolder(view);
        this.context =viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCertify_ViewHolder holder, int position) {

        holder.TITLE.setText(mList.get(position).getTitle());
        holder.PERIOD.setText(mList.get(position).getPeriod());
        holder.VIEWNAME.setText(mList.get(position).getViewName());
        holder.POSSIBLETIME.setText(mList.get(position).getPossibleTime());
        holder.ACHIEVEMENTRATE.setText(mList.get(position).getAchivementRate());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size():0);
    }


}
