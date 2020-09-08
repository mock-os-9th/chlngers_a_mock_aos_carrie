package com.Carrie.challengersproject.src.Detail_Challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.Payment.PaymentActivity;

public class Detail_Challenge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__challenge);

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
                startActivity(intent);
                finish();
            }
        });


        // 사용자가 클릭한 챌린지 정보에 따라 setText 등이 필요하다.
    }
}
