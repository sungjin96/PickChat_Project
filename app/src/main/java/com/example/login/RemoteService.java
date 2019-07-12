package com.example.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RemoteService {
    public static final String BASE_URL="http://sungjin5891.cafe24.com/";

    @GET("/user/list_user")
    Call<List<UserProfileVO>> listUser();

}
