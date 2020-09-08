package com.Carrie.challengersproject.src.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Detail_Challenge.DetailService;
import com.Carrie.challengersproject.src.Detail_Challenge.interfaces.DetailActivityView;
import com.Carrie.challengersproject.src.Detail_Challenge.models.DetailResponse;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.Payment.interfaces.PaymentView;
import com.bumptech.glide.Glide;

public class PaymentActivity extends AppCompatActivity implements PaymentView, DetailActivityView {

    final PaymentService paymentService = new PaymentService(this);
    final DetailService detailService = new DetailService(this);

    String final_money;
    int final_money_int;
    TextView Pay_btn;

    TextView title;
    TextView period;
    TextView week;
    TextView frequency;
    TextView challengercount;
    TextView gatheramount;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        final Context m_context = getApplicationContext();

        Intent intent = getIntent();
        final int get_challege_id_from_b_activity = intent.getIntExtra("챌린지아이디",0);

        TryDetail_In(get_challege_id_from_b_activity);
        /*
        * 1) [o] 결제하기 버튼을 누르면 -> 다이얼로그 뜨고 결제 및 자신의 참가 목록에 포함된다. -> 토스트 메세지
        * 2) 라디오 버튼 ? 따로 처리 x?
        * 3) [o] 참가비 입력시 커서 위치 :
        * 4) [o] 세개 버튼 하나 클릭시 다른거 제한 바꾸는 것도 구현
        * */

        // 3)
        final EditText money_et = findViewById(R.id.payment_et_enter_participate_money);


        money_et.requestFocus();
        money_et.setSelection(money_et.getText().length()); // 입력 커서를 끝으로.

        money_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                final_money_int = Integer.parseInt(money_et.getText().toString());
//                Log.d("et_parseint",String.valueOf(final_money_int));
//                money_et.setText(money_et.getText().toString()+"만원    ");
            }
        });



        // 1)
        Pay_btn = findViewById(R.id.payment_tv_pay_btn);
        Pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PaymentActivity.this);
                alertDialog.setTitle("결제창")
                        .setMessage("결제 하시겠어요?")
                        .setCancelable(false)
                        .setPositiveButton("네",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        tryPaymentTest(get_challege_id_from_b_activity,1000);
                                    }
                                })
                        .setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(),"결제 취소!",Toast.LENGTH_LONG).show();
                                    }
                                });

                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });

        final Button kakaopay = findViewById(R.id.payment_kakaopay);
        final Button credit=findViewById(R.id.payment_credit);
        final Button transfer = findViewById(R.id.payment_account_Transfer);

        final Drawable et_drawable = getResources().getDrawable(R.drawable.primary_edit_text);

        final int color_prim = getResources().getColor(R.color.colorPrimary);
        final int color_text =  getResources().getColor(R.color.colorAccent);

        kakaopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kakaopay.setBackgroundColor(color_prim);
                kakaopay.setTextColor(color_text);

                credit.setBackground(et_drawable);
                credit.setTextColor(color_prim);
                transfer.setTextColor(color_prim);
                transfer.setBackground(et_drawable);
            }
        });


        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credit.setBackgroundColor(color_prim);
                credit.setTextColor(color_text);
                kakaopay.setBackground(et_drawable);
                transfer.setBackground(et_drawable);
                kakaopay.setTextColor(color_prim);
                transfer.setTextColor(color_prim);
            }
        });


        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer.setBackgroundColor(color_prim);
                transfer.setTextColor(color_text);
                credit.setBackground(et_drawable);
                kakaopay.setBackground(et_drawable);
                credit.setTextColor(color_prim);
                kakaopay.setTextColor(color_prim);
            }
        });



         title = findViewById(R.id.payment_tv_challenge_title);
         period = findViewById(R.id.payment_tv_challenge_date);
         week =findViewById(R.id.payment_challenge_period);
         frequency =findViewById(R.id.payment_tv_challenge_days);
         challengercount = findViewById(R.id.payment_tv_challengers_real_num);
         gatheramount = findViewById(R.id.payment_tv_moneys_real_num);
         imageView = findViewById(R.id.payment_iv_challenge_img);
    }

    private void tryPaymentTest(int challenge_id, int m_money)
    {
        paymentService.postPaymentResult(challenge_id,m_money);
    }

    @Override
    public void PaymentSuccess(String message) {
        Toast.makeText(getApplicationContext(),"결제 완료!",Toast.LENGTH_LONG).show();

        // 결제 완료 == 내 챌린지로 추가 되어 있다.
        Intent intent = new Intent(PaymentActivity.this, a_MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void PaymentFailure(String message) {

    }


    private void  TryDetail_In(int challenge_id)
    {
        detailService.getDetailResult(challenge_id);
    }


    @Override
    public void DetailSuccess(DetailResponse detailResponse) {

        String t_title, p_period, w_week, f_frequency, c_challengercount, g_gatheramount;
        t_title = detailResponse.getTitle();
        p_period = detailResponse.getPeriod();
        w_week= detailResponse.getWeek();
        f_frequency= detailResponse.getFrequency();
        c_challengercount = String.valueOf(detailResponse.getChallengerCount());
        g_gatheramount = String.valueOf(detailResponse.getGatheredAmount());

//        String img_url = detailResponse.getImageUrl();

        title.setText(t_title);
        period.setText(p_period);
        week.setText(w_week);
        frequency.setText(f_frequency);
        challengercount.setText(c_challengercount);
        gatheramount.setText(g_gatheramount);
//        Glide.with(this).load(img_url).into(imageView);
    }

    @Override
    public void DetailFailure(String message) {

    }
}
