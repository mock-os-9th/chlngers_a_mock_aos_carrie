package com.Carrie.challengersproject.src.common.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.Carrie.challengersproject.R;
import com.Carrie.challengersproject.src.Login.LoginActivity;

public class PopupInfoDialog {
    private Context context;

    public PopupInfoDialog(Context context) {
        this.context = context;
    }

    //호출할 다이얼로그 함수
    public void callFunction()
    {
        final Dialog dig = new Dialog(context);
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dig.setContentView(R.layout.dialog_popup_info);
        dig.show();

        // 커스텀 다이얼로그 위젯 정의
        Button exitBtn = dig.findViewById(R.id.dialog_popup_info_exit_btn);
        TextView loginBtn = dig.findViewById(R.id.dialog_popup_info_login_tv_btn);

        TextView title1 = dig.findViewById(R.id.dialog_popup_info_title_line1);
        TextView title2 = dig.findViewById(R.id.dialog_popup_info_title_line2);
        TextView contents1 = dig.findViewById(R.id.dialog_popup_info_contents_line1);
        TextView contents2 = dig.findViewById(R.id.dialog_popup_info_contents_line2);
        TextView contents3 = dig.findViewById(R.id.dialog_popup_info_contents_line3);
        TextView contents4 = dig.findViewById(R.id.dialog_popup_info_contents_line4);
        TextView contents5 = dig.findViewById(R.id.dialog_popup_info_contents_line5);
        TextView login_ask = dig.findViewById(R.id.dialog_popup_info_login_ask);
        TextView login_btn = dig.findViewById(R.id.dialog_popup_info_login_tv_btn);

        title1.setText(R.string.popupTitle_line1);
        title2.setText(R.string.popupTitle_line2);
        contents1.setText(R.string.popupContents_line1);
        contents2.setText(R.string.popupContents_line2);
        contents3.setText(R.string.popupContents_line3);
        contents4.setText(R.string.popupContents_line4);
        contents5.setText(R.string.popupContents_line5);
        login_ask.setText(R.string.popupLogin_ask);
        login_btn.setText(R.string.popupLogin_ask_btn);





        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.dismiss();
                PopupSearchDialog popupSearchDialog = new PopupSearchDialog(context);
                popupSearchDialog.callFunction();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.dismiss();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
    }

}
