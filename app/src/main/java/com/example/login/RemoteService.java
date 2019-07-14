package com.example.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL="http://192.168.0.26/user/";

    @GET("/user/list_user")
    Call<List<UserProfileVO>> listUser();

    //이상형/매력 호출
    @GET("list_category")
    Call<List<type_categoryVO>> listCategory();

    //취미호출
    @GET("list_hobbycategory")
    Call<List<hobby_categoryVO>> listhobby();

    //회원내용 저장
    @POST("insert_puser")
    Call<Void> userinsert(@Body User_listVO vo);

    //매력, 이상형 저장
    @POST("insert_mytype")
    Call<Void> typeinsert(@Body TypeInsertVO vo);


    //중복체크
    @GET("checknickname/{usernickname}")
    Call<Integer> checknickname(@Path("usernickname") String usernickname);

    // 유저 아이디 중복확인
    @GET("checkid/{userid}")
    Call<Integer> checkid(@Path("userid") String userid);

    @GET("user")
    Call<List<naverVO>> listuser();

    @GET("insert.jsp")
    Call<Void> insertUser(@Query("id") String id, @Query("name") String name, @Query("password") String password);

}
