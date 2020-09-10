package com.Carrie.challengersproject.src.ProfileImgChange_Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Main.after_login.a_MainActivity;
import com.Carrie.challengersproject.src.ProfileImgChange_Fragment.interfaces.ImageChangeView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ImageChangeFragment extends Fragment implements ImageChangeView {

    ViewGroup viewGroup;
    a_MainActivity mainActivity;
    boolean Img_Changed = false;
    ImageView profile_img;
    TextView confirm_btn;
    private final int PICK_FROM_ALBUM = 1;
    private File tempFile;
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
                tryPatchChangeProfileImg("https://i.pinimg.com/564x/c7/fc/b0/c7fcb078cd1f4b46ba2edfc362cd1eec.jpg");
            }
        });

        tedPermission();
        // 사진첩 버튼 -> 사진첩으로 접근
        Button go_album_btn = viewGroup.findViewById(R.id.change_profileimg_btn_photo_album);
        go_album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gotoAlbum();
                //사진 첩에서 가져오기
            }
        });

        return viewGroup;
    }


    private void tedPermission()
    {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
            }
        };
        TedPermission.with(mainActivity)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setRationaleMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
                .check();
    }

    private void gotoAlbum()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent,PICK_FROM_ALBUM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_FROM_ALBUM)
        {
            Uri photoUri = data.getData();
            Cursor cursor = null;
            try {
                String[] proj = {MediaStore.Images.Media.DATA};
                assert photoUri != null;
                cursor = mainActivity.getContentResolver().query(photoUri,proj,null,null,null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();
                tempFile = new File(cursor.getString(column_index));
            }finally {
                if(cursor!= null){
                    cursor.close();
                }
            }
            setImage();
            confirm_btn.setText("확인");
        }
    }

    private void setImage()
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap orginalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(),options);
        profile_img.setImageBitmap(orginalBm);
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
        mainActivity.onChangeFragment(7);
    }
}
