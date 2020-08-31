package com.Carrie.challengersproject.src.social;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

// 로그인 요청 후 결과를 받을 콜백 클래스
public class FacebookCallback implements com.facebook.FacebookCallback<LoginResult> {

    // 로그인 성공시 호출 & Access Token 발급 성공.
    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.e("facebook Callback::","onSuccenss");
        requestMe(loginResult.getAccessToken());

    }

    // 로그인 창을 닫을 경우 호출.
    @Override
    public void onCancel() {
        Log.e("facebook Callback::","onCancel");
    }

    // 로그인 실패 시 호출
    @Override
    public void onError(FacebookException error) {
        Log.e("facebook Callback::","on Error:"+error.getMessage());
    }

    // 사용자 정보 요청 - Bundle 로 원하는 필드 값들을 파라미터로 넘겨 준 후, Json 형태로 결과를 받을 수 있다.
    public void requestMe(AccessToken token)
    {
        GraphRequest graphRequest = GraphRequest.newMeRequest(token,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("result",object.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("field","id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

}
