package com.example.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL="http://sungjin5891.cafe24.com/";

    //유저 리스트
    @GET("user/list_user")
    Call<List<UserProfileVO>> listUser();

    //프로필 리스트
    @GET("user/read/{userid}")
    Call<UserProfileVO> listProfile(@Path("userid") String userid);

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
