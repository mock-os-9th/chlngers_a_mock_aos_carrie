package com.Carrie.challengersproject.src.Profile_Fragment.interfaces;

import com.Carrie.challengersproject.src.Profile_Fragment.models.UserDeleteResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;

public interface UserDeleteRetrofitInterface {

    @DELETE("/user")
    Call<UserDeleteResponse> getTestUserDelete(
    );
}
