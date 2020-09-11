package com.Carrie.challengersproject.src.Feed_Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Search_Fragment.SearchService;
import com.Carrie.challengersproject.src.Search_Fragment.interfaces.SearchActivityView;
import com.Carrie.challengersproject.src.Search_Fragment.models.SearchResponse;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.FeedItemViewHolder> {

    private ArrayList<String> mList;
    Context context;


    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    //전달된 객체 저장할 변수
    private FeedItemAdapter.OnItemClickListener mListener = null;
    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class FeedItemViewHolder extends RecyclerView.ViewHolder
    {
        protected ImageView IMG;

        public FeedItemViewHolder(View view)
        {
            super(view);
            this.IMG = (ImageView)view.findViewById(R.id.feed_item_iv);

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

    // 생성자에서 List 객체를 전달
    public FeedItemAdapter(ArrayList<String> mList) {
        this.mList = mList;
    }


    @NonNull
    @Override
    public FeedItemAdapter.FeedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item,parent,false);
        FeedItemAdapter.FeedItemViewHolder viewHolder = new FeedItemAdapter.FeedItemViewHolder(view);
        this.context =parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedItemAdapter.FeedItemViewHolder holder, int position) {
        String imgurl = mList.get(position);
        Glide.with(this.context).load(imgurl).into(holder.IMG);
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size():0);
    }
}
