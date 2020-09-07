package com.Carrie.challengersproject.src.main.mypage.setting.profile.image;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.main.mypage.setting.profile.image.interfaces.ImageChangeView;

import java.io.File;
import static android.app.Activity.RESULT_OK;

public class ImageChangeFragment extends Fragment implements ImageChangeView {

    ViewGroup viewGroup;
    a_MainActivity mainActivity;
    boolean Img_Changed = false;
    ImageView profile_img;
    TextView confirm_btn;
    private final int GET_GALLERY_IMAGE = 200;
    Uri selectedImageUri;

    final ImageChangeService imageChangeService = new ImageChangeService(this);

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (a_MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity =null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.imagechange_fragment,container,false);
        // 취소 버튼
        TextView cancel_btn = viewGroup.findViewById(R.id.change_profileimg_tv_cancle_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(7);
            }
        });

        // 프로필 이미지 뷰
        profile_img = viewGroup.findViewById(R.id.change_profileimg_iv_Img);

        // 삭제 텍스트 뷰 버튼 -> 기본 이미지로 바꿔준다.
        TextView delete_btn = viewGroup.findViewById(R.id.change_profileimg_tv_delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("프로필 이미지 삭제").setMessage("이미지를 삭제하시겠습니까?");

                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        profile_img.setImageResource(R.drawable.profile80);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        // 이미지를 가져오면 확인 버튼이 나오고 setText("확인") 해줘야 하고, 누르면 통신 및 프레그먼트 전환!
        confirm_btn = viewGroup.findViewById(R.id.change_profileimg_tv_confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 통신
                tryPatchChangeProfileImg("https://cdn.now.howstuffworks.com/media-content/0b7f4e9b-f59c-4024-9f06-b3dc12850ab7-1920-1080.jpg");
            }
        });


        // 사진첩 버튼 -> 사진첩으로 접근
        Button go_album_btn = viewGroup.findViewById(R.id.change_profileimg_btn_photo_album);
        go_album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                // 기기 기본 갤러리 접근
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setType("image/*");
                // 구글 갤러리 접근
                startActivityForResult(intent,GET_GALLERY_IMAGE);
            }
        });

        return viewGroup;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

//        if (resultCode == RESULT_OK)
//        {
//            if(requestCode == GET_GALLERY_IMAGE)
//            {
//                Uri selectedImageUri = data.getData();
//                if(selectedImageUri != null)
//                {
//                    Uri destinationUri =Uri.fromFile(File(cacheDir,"cropped"));
//                }
//            }else if(requestCode == Ucrop.REQUEST_CROP)
//            {
//                Uri resultUri = Ucrop.getOutput(data.getData());
//                if(resultUri != null)
//                {
//                    profile_img.setImageDrawable(null);
//                }
//            }
//            try {
//                InputStream in = getContentResolver().openInputStream(data.getData());
//                Bitmap img = BitmapFactory.decodeStream(in);
//                in.close();
//                profile_img.setImageBitmap(img);
//                Img_Changed = true;
//            }catch(Exception e)
//            {e.printStackTrace();}
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            // Uri 스키마를 content:/// 에서 file:///로 변경

            Log.d("Uri",String.valueOf(selectedImageUri));
            profile_img.setImageURI(selectedImageUri);
            Img_Changed = true;
        }

            if(Img_Changed)
            {
                confirm_btn.setText("확인");
            }

    }

    private void tryPatchChangeProfileImg(String url)
    {
        imageChangeService.patchImageChangeIn(url);
    }

    @Override
    public void ImageChangeSuccess(String text) {
        Log.e("프로필 사진 변경 완료",text);
        mainActivity.onChangeFragment(7);
    }

    @Override
    public void ImageChangeFailure(String message) {
        Log.e("프로필 사진 변경 실패",message);
    }
}
