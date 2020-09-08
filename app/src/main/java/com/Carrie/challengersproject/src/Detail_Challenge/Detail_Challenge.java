package com.Carrie.challengersproject.src.Detail_Challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Detail_Challenge.interfaces.DetailActivityView;
import com.Carrie.challengersproject.src.Detail_Challenge.models.DetailResponse;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.Payment.PaymentActivity;
import com.bumptech.glide.Glide;

public class Detail_Challenge extends AppCompatActivity implements DetailActivityView {

    final DetailService detailService = new DetailService(this);
    TextView title;
    TextView period;
    TextView week;
    TextView frequency;
    TextView score;
    TextView challengercount;
    TextView gatheramount;
    ImageView topimg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__challenge);

        Intent intent = getIntent();
        final int get_challege_id_from_before_activity = intent.getIntExtra("챌린지아이디",0);
        Log.d("넘어온 챌린지 아이디",String.valueOf(get_challege_id_from_before_activity));
        tryDetailIn(get_challege_id_from_before_activity); // 통신 후 성공하면, 뷰에 필요 값들 넣어 준다.

        ImageButton back_btn = findViewById(R.id.detail_challenge_ib_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Search Fragment 로 넘겨 줘야한다. ( 바로 넘겨 줬을 때, search tab 이니까?)
                Intent intent = new Intent(Detail_Challenge.this, a_MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView participate_btn = findViewById(R.id.detail_challenge_tv_go_to_pop_and_money);
        participate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 결제 화면으로 넘어가야 한다.
                Intent intent = new Intent(Detail_Challenge.this, PaymentActivity.class);
                intent.putExtra("챌린지아이디",get_challege_id_from_before_activity);
                startActivity(intent);
                finish();
            }
        });


        title = findViewById(R.id.dc_tv_challenge_title);
        period = findViewById(R.id.dc_tv_challenge_period);
        week = findViewById(R.id.dc_tv_challenge_days); // 앞 뒤로  ( ) 추가해야한다.
        frequency = findViewById(R.id.dc_tv_challenge_days);

        score= findViewById(R.id.star_num);
        challengercount= findViewById(R.id.dc_tv_challengers_real_num);
        gatheramount = findViewById(R.id.dc_tv_moneys_real_num);
        topimg = findViewById(R.id.dc_iv_6pic);
        // 사용자가 클릭한 챌린지 정보에 따라 setText 등이 필요하다.
    }

    private void tryDetailIn(int challenge_id)
    {
        detailService.getDetailResult(challenge_id);

    }

    @Override
    public void DetailSuccess(DetailResponse detailResponse) {
        String t_title, p_period, w_week, f_frequncy, s_score, c_challengercount, g_gatheramount;
        t_title = detailResponse.getTitle();
        p_period = detailResponse.getPeriod();
        w_week  ="("+detailResponse.getWeek()+")";
        f_frequncy = detailResponse.getFrequency();
        s_score = detailResponse.getScore();
        c_challengercount = String.valueOf(detailResponse.getChallengerCount());
        g_gatheramount = String.valueOf(detailResponse.getGatheredAmount());

//        String img_url = detailResponse.getImageUrl();
        //        Glide.with(this).load(img_url).into(topimg);

        title.setText(t_title);
        period.setText(p_period);
        week.setText(w_week);
        frequency.setText(f_frequncy);
        score.setText(s_score);
        challengercount.setText(c_challengercount);
        gatheramount.setText(g_gatheramount);

    }

    @Override
    public void DetailFailure(String message) {

    }
}
