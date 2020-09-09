package com.Carrie.challengersproject.src.HowCertify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Camera_Fragment.MyCertifyService;
import com.Carrie.challengersproject.src.Camera_Fragment.interfaces.MyCertifyView;
import com.Carrie.challengersproject.src.Camera_Fragment.models.MyCertifyResponse;

import org.w3c.dom.Text;

// 이 액티비티 안에 "인증샷 찍기" 버튼이 있다.
public class HowCertifyActivity extends AppCompatActivity implements MyCertifyView {

    final MyCertifyService myCertifyService = new MyCertifyService(this);

    TextView camera_btn = findViewById(R.id.how_certify_tv_btn_camera);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_certify);

        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void MyCertifySuccess(MyCertifyResponse myCertifyResponse) {

    }

    @Override
    public void MyCertifyFailure(String message) {

    }
}
