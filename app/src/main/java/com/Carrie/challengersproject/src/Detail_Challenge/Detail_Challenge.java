package com.Carrie.challengersproject.src.Detail_Challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Carrie.challengersproject.R;

public class Detail_Challenge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__challenge);

        TextView participate_btn = findViewById(R.id.detail_challenge_tv_go_to_pop_and_money);
        participate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 결제 화면으로 넘어가야 한다.
            }
        });
    }
}
