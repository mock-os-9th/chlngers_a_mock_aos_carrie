package com.Carrie.challengersproject.src.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Carrie.challengersproject.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        /*
        * 1) 결제하기 버튼을 누르면 -> 다이얼로그 뜨고 결제 및 자신의 참가 목록에 포함된다.
        * 2) 라디오 버튼
        * 3) 참가비 입력시 커서 위치 :  https://www.it-swarm.dev/ko/android/edittext%EC%97%90%EC%84%9C-%EC%BB%A4%EC%84%9C-%EC%9C%84%EC%B9%98%EB%A5%BC-%EC%84%A4%EC%A0%95%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95/941756875/
        * 4) 세개 버튼 하나 클릭시 다른거 제한 바꾸는 것도 구현
        * */
    }
}
