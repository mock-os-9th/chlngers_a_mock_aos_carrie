package com.Carrie.challengersproject.src.HowCertify;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Camera_Fragment.MyCertifyService;
import com.Carrie.challengersproject.src.Camera_Fragment.interfaces.MyCertifyView;
import com.Carrie.challengersproject.src.Camera_Fragment.models.MyCertifyResponse;
import com.Carrie.challengersproject.src.HowCertify.interfaces.HowCertifyView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// 이 액티비티 안에 "인증샷 찍기" 버튼이 있다.
// 찍은 사진은 어디 이미지 뷰에 세팅 되고 그러는게 아니라, 바로 업로드 >>
public class HowCertifyActivity extends AppCompatActivity implements HowCertifyView {

    final HowCertifyService howCertifyService = new HowCertifyService(this);


    Intent intent= getIntent();
    int challenge_ID = intent.getIntExtra("CHALLENGE_ID",0);

    private File tempFile;
    private static final int PICK_FROM_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_certify);

        tedPermission(); // 권한 요청 실패 OR 성공

        TextView camera_btn = findViewById(R.id.how_certify_tv_btn_camera);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
    }

    private void tedPermission()
    {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                Toast.makeText(HowCertifyActivity.this, "권한 허가",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
                Toast.makeText(HowCertifyActivity.this, "권한 거부",Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedCloseButtonText(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
                .check();
    }

    @SuppressLint("LongLogTag")
    private void takePhoto()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            tempFile = createImageFile();
        }catch(IOException e)
        {
            Toast.makeText(this,"이미지 처리 오류! 다시 시도해 주세요",Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if(tempFile != null)
        {
            Uri photoUri = Uri.fromFile(tempFile); // tempFile 의 Uri 경로를 intent 에 추가. 이는 카메라에서 찍은 사진이 저장 될 주소를 의미한다.
            Log.d("tempFile_Uri 경로: 찍은 사진이 저장 될 주소",String.valueOf((photoUri)));

            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);  // Intent 를 통해 카메라 화면으로 이동.
            startActivityForResult(intent,PICK_FROM_CAMERA);
            //
        }
    }

   // 카메라에서 찍은 사진을 저장할 파일 만들기 ::  아래 주석과 같이 파일 명과 폴더 명을 직접 정할 수 있다. → 카메라에서 찍은 사진을 우리가 생성한 파일에 저장하고 받아올 수 있게 된다.
    private File createImageFile() throws IOException{

        // 이미지 파일 이름 (blackJin_{시간}_)
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "blackjin_"+timeStamp+"_";

        Log.d("이미지파일이름",imageFileName);

        // 이미지가 저장될 폴더 이름 (blackJin)
        File storageDir = new File(Environment.getExternalStorageDirectory()+"/blackJin");
        if(!storageDir.exists())
            storageDir.mkdirs();

        // 빈 파일 생성
        File image = File.createTempFile(imageFileName, ".jpg",storageDir);
        Log.d("파일이름",String.valueOf(image));
        return image;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.d("카메라 온애기비티 리절트", tempFile.getAbsolutePath() + "삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return;
        }


        if (requestCode == PICK_FROM_CAMERA) {
            setImage();
        }
    }

    private void setImage()
    {
//     예제
//     ImageView imageView =findViewById(R.id.imageView);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d("파일 절대 경로",tempFile.getAbsolutePath()); // 이 정보를 바탕으로 나중에 이미지 뷰에 set 하니까.

        tryPostCertifyTest(challenge_ID,tempFile.getAbsolutePath());


        //     예제
//     IimageView.setImageBitmap(originalBm);

    }


    private void tryPostCertifyTest(int challenge_id, String photo_url) {

        howCertifyService.postCertifyIn(challenge_id,photo_url);
    }


    @Override
    public void HowCertifySuccess(String text) {
        // 성공시 이미지 뷰에 띄우는 것과 확인 버튼있는 액티비티로.

    }

    @Override
    public void HowCertifyFailure(String message) {

    }
}
