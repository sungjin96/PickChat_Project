package com.example.login;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL="http://sungjin5891.cafe24.com/";

    @GET("user/list_user")
    Call<List<UserProfileVO>> listUser();

    //이상형/매력 호출
    @GET("user/list_category")
    Call<List<type_categoryVO>> listCategory();

    //취미호출
    @GET("user/list_hobbycategory")
    Call<List<hobby_categoryVO>> listhobby();

    //회원내용 저장
    @POST("user/insert_puser")
    Call<Void> userinsert(@Body User_listVO vo);

    //매력
    @POST("user/insert_mytype")
    Call<Void> typeinsert(@Body TypeInsertVO vo);


    //중복체크
    @GET("user/checknickname/{usernickname}")
    Call<Integer> checknickname(@Path("usernickname") String usernickname);

    // 유저 아이디 중복확인
    @GET("user/checkid/{userid}")
    Call<Integer> checkid(@Path("userid") String userid);

    @GET("user")
    Call<List<naverVO>> listuser();

    @GET("insert.jsp")
    Call<Void> insertUser(@Query("id") String id, @Query("name") String name, @Query("password") String password);

    @Multipart
    @POST("upload1")
    Call<ResponseBody> uploadImg(@Part MultipartBody.Part uploadfile,@Part MultipartBody.Part uploadfile1);

}
