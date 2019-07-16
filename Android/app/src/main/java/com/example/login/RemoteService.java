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

    //유저리스트
    @GET("user/list_user")
    Call<List<UserProfileVO>> listUser();

    //프로필 리스트
    @GET("user/read/{userid}")
    Call<UserProfileVO> listProfile(@Path("userid") String userid);

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

    //내가 좋아요 한 사람
    @GET("user/user_sender_profile/{userid}")
    Call<List<HWJ_LikeVO>> listILike(@Path("userid") String userid);

    //주변친구차단 삽입
    @POST("user/insert_blockuser")
    Call<Void> insertBlock(@Body HWJ_BlockVO vo);

    //차단친구목록 리스트
    @GET("user/list_blockuser/{blocker}")
    Call<List<HWJ_BlockVO>> listBlock(@Path("blocker") String blocker);

    //차단친구목록 삭제
    @POST("user/delete_blockuser/{blockno}")
    Call<Void> deleteBlock(@Path("blockno") int blockno);

    //포인트 업데이트
    @PATCH("user/update_userpoint/{userid}/{userpoint}")
    Call<Void> updatePoint(@Path("userid") String userid, @Path("userpoint") int userpoint);

}
