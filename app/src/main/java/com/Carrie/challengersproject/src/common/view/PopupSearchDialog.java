package com.Carrie.challengersproject.src.common.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.Carrie.challengersproject.R;

public class PopupSearchDialog {
    private Context context;

    public PopupSearchDialog(Context context) {
        this.context = context;
    }

    //호출할 다이얼로그 함수
    public void callFunction()
    {
        final Dialog dig = new Dialog(context);
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dig.setContentView(R.layout.dialog_popup_search);
        dig.show();

        // 커스텀 다이얼로그 위젯 정의
        Button gotoSearchBtn = dig.findViewById(R.id.dialog_popup_search_gotoSearchTab_btn);


        TextView title1 = dig.findViewById(R.id.dialog_popup_Search_title);

        TextView contents1 = dig.findViewById(R.id.dialog_popup_search_contents_line1);
        TextView contents2 = dig.findViewById(R.id.dialog_popup_search_contents_line2);
        TextView contents3 = dig.findViewById(R.id.dialog_popup_search_contents_line3);
        TextView contents4 = dig.findViewById(R.id.dialog_popup_search_contents_line4);
        TextView contents5 = dig.findViewById(R.id.dialog_popup_search_contents_line5);
        TextView contents6 = dig.findViewById(R.id.dialog_popup_search_contents_line6);


        title1.setText(R.string.popupSearchTab_line1);

        contents1.setText(R.string.popupSearchTabContents_line1);
        contents2.setText(R.string.popupSearchTabContents_line2);
        contents3.setText(R.string.popupSearchTabContents_line3);
        contents4.setText(R.string.popupSearchTabContents_line4);
        contents5.setText(R.string.popupSearchTabContents_line5);
        contents6.setText(R.string.popupSearchTabContents_line6);


        gotoSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.dismiss();
            }
        });


    }

}
