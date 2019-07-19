package com.example.login;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL="http://sungjin5891.cafe24.com/";
//    public static final String BASE_URL="http://192.168.0.26/";
    @GET("user/login")
    Call<Integer> logincheck(@Query("userid")String userid,@Query("userpass")String userpass);
    //유저리스트
    @GET("user/list_user")
    Call<List<UserProfileVO>> listUser();

    //한 명 프로필 리스트
    @GET("user/read/{userid}")
    Call<UserProfileVO> listProfile(@Path("userid") String userid);

    //이상형/매력 리스트
    @GET("user/list_category")
    Call<List<type_categoryVO>> listCategory();

    //취미 리스트
    @GET("user/list_hobbycategory")
    Call<List<hobby_categoryVO>> listhobby();

    //중복체크
    @GET("user/checknickname/{usernickname}")
    Call<Integer> checknickname(@Path("usernickname") String usernickname);

    // 유저 아이디 중복확인
    @GET("user/checkid/{userid}")
    Call<Integer> checkid(@Path("userid") String userid);

//    @GET("user")
//    Call<List<naverVO>> listuser();
//
//    @GET("insert.jsp")
//    Call<Void> insertUser(@Query("id") String id, @Query("name") String name, @Query("password") String password);

    //유저 매력 리스트
    @GET("user/list_mytype/{userid}")
    Call<List<HWJ_TypeVO>> listMytype(@Path("userid") String userid);

    // 유저 취미 리스트
    @GET("user/list_userhobby/{userid}")
    Call<List<HWJ_HobbyVO>> listUserhobby(@Path("userid") String userid);

    // 유저 이상형 리스트
    @GET("user/list_liketype/{userid}")
    Call<List<HWJ_TypeVO>> listLiketype(@Path("userid") String userid);

    //공지사항 리스트
    @GET("notice/list")
    Call<List<HWJ_NoticeVO>> listNotice();

    //나를 좋아요 한 사람 리스트
    @GET("user/user_receiver_profile/{userid}")
    Call<List<HWJ_LikeVO>> listWhoLike(@Path("userid") String userid);

    //차단친구목록 리스트
    @GET("user/list_blockuser/{blocker}")
    Call<List<HWJ_BlockVO>> listBlock(@Path("blocker") String blocker);

    //내가 좋아요 한 사람 리스트
    @GET("user/user_sender_profile/{userid}")
    Call<List<HWJ_LikeVO>> listILike(@Path("userid") String userid);

    //주변친구차단 입력
    @POST("user/insert_blockuser")
    Call<Void> insertBlock(@Body HWJ_BlockVO vo);

    //회원가입 (기본정보)
    @POST("user/insert_puser")
    Call<Void> userinsert(@Body User_listVO vo);

    //내 매력 입력
    @POST("user/insert_mytype")
    Call<Void> typeinsert(@Body TypeInsertVO vo);

    //이상형 입력
    @POST("user/insert_liketype")
    Call<Void> liketypeinsert(@Body TypeInsertVO vo);

    //취미 입력
    @POST("user/insert_userhobby")
    Call<Void> hobbyinsert(@Body TypeInsertVO vo);

    //사진업로드
    @Multipart
    @POST("upload1")
    Call<ResponseBody> uploadImg(@Part MultipartBody.Part uploadfile);

    //차단친구목록 삭제
    @POST("user/delete_blockuser/{blockno}")
    Call<Void> deleteBlock(@Path("blockno") int blockno);

    //포인트 업데이트
    @PATCH("user/update_userpoint/{userid}/{userpoint}")
    Call<Void> updatePoint(@Path("userid") String userid, @Path("userpoint") int userpoint);

    //서로 좋아요 한 사람 리스트
    @GET("user/user_eachlike/{userid}")
    Call<List<UserProfileVO>> eachlikeUser(@Path("userid")String userid);

    //api호출
    @GET("api")
    Call<ResponseBody> faceapi();


}
