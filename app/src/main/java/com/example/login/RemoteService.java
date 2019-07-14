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
    public static final String BASE_URL = "http://sungjin5891.cafe24.com/";

    @GET("/user/list_user")
    Call<List<UserProfileVO>> listUser();

    /*이미지 리스트*/
    @GET("board/bbslist")
    Call<ArrayList<BBSimgVO>> list();

    /*상세페이지-해당게시글*/
    @GET("board/bbsread/{bno}")
    Call<BBSimgVO> bbsRead(
            @Path("bno") int bno);

    /*추가*/
    @POST("board/bbsinsert/")
    Call<Void> bbsinsert(
            @Body() BBSimgVO vo,
            @Part MultipartBody.Part imgpath);

    /*게시물 삭제*/
    @DELETE("board/bbsdelete/{bno}")
    Call<Void> bbsDelete(
            @Path("bno") int bno);

    /*  댓글리스트*/
    @GET("board/reply/list/{bno}")
    Call<List<BBSimgVO>> replyList(
            @Path("bno") int bno);

    /*댓글달기*/
    @POST("board/reply/insert")
    Call<Void> replyInsert(
            @Body() BBSReplyVO vo);

    /*게시물 신고하기*/
    @POST("board/police/insert/{bno}")
    Call<Void> bbsPolice(
            @Body() BBSReplyVO vo);

    /*좋아요클릭*/
    @POST("board/bbslikeinsert")
    Call<Void> lickInsert(
            @Body() BBSimgVO vo);

    @POST("board/bbslikedelete/{bno}/{liker}")
    Call<Void> lickDelete(
            @Path("bno") int bno,
            @Path("liker") String liker
    );






}
