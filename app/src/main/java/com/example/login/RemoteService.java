package com.example.login;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RemoteService {
    public static final String BASE_URL="http://sungjin5891.cafe24.com/";

    @GET("/user/list_user")
    Call<List<UserProfileVO>> listUser();


}
