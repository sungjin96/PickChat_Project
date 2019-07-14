package com.example.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RemoteService {
    public static final String BASE_URL="http://192.168.0.26/user/";

    @GET("list_user")
    Call<List<UserProfileVO>> listUser();

    @GET("user_eachlike/{userid}")
    Call<List<UserProfileVO>> eachlikeUser(@Path("userid")String userid);
}
