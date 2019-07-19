package com.example.login;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface NaverService {
    public static final String NAVER_URL= "https://api-sens.ncloud.com/v1/sms/";


    @POST("services/ncp:sms:kr:256312159523:pickchat/messages")
    Call<ResponseBody> sendSMS(@HeaderMap HashMap<String, String> header, @Body naverVO body);




}
