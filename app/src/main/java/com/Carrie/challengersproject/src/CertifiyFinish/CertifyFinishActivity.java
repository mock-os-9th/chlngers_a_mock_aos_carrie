package com.Carrie.challengersproject.src.CertifiyFinish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Camera_Fragment.CameraFragment;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;

import static android.text.format.DateUtils.LENGTH_LONG;

public class CertifyFinishActivity extends AppCompatActivity {

    ImageView user_piced_img;
    TextView confirm_btn;
    int challenge_id;
    String photo_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certify_finish);

        Intent intent = getIntent();
        // 이전 액티비티에서 넘겨 받은 것.
        challenge_id = intent.getIntExtra("CHALLENGE_ID",0);
        Log.d("챌린지 아이디 피니쉬", String.valueOf(challenge_id));

        photo_url =intent.getStringExtra("PHOTO_URL");

        user_piced_img = findViewById(R.id.certify_finish_iv); // setbackground 식으로 넣어주기
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(photo_url,options);
        user_piced_img.setImageBitmap(originalBm);

        /*
        * //     예제
//     ImageView imageView =findViewById(R.id.imageView);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        //     예제
//     IimageView.setImageBitmap(originalBm);
        * */

        confirm_btn = findViewById(R.id.certify_finish_confirm_btn);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 확인 버튼을 누를시에 인증이 되는 거기 때문에
                // CameraFragment 화면으로 바꾸어 주어야 함. Toast.makeText(getApplicationContext(), "인증 완료 하였습니다! ", Toast.LENGTH_LONG).show();
               Intent intent = new Intent(CertifyFinishActivity.this, a_MainActivity.class);
                SharedPreferences sp = getSharedPreferences("TEMPLATE_APP",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("ChallengeID",challenge_id);
                editor.commit();

                startActivity(intent);
                finish();
            }
        });
    }
}
