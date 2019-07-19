package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_ProfileActivity extends AppCompatActivity {
    RelativeLayout soloimg, purchase;
    ImageView favorite;
    HWJ_OtherFragment otherFragment;
    TextView usernickname, userage, localname, usercomment, userjob, userfragment;
    RecyclerView list_mytype, list_userhobby, list_liketype;
    UserProfileVO vo_profile;
    Retrofit retrofit;
    RemoteService rs;
    Bitmap bitmap;
    Intent intent;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_profile);

        //상태바 아이콘색상변경
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));
            }
        }else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.BLACK);
        }

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#878789'>프로필 </font>"));

        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");

        //userid = "01000020002";

        //아이디값 찾기
        soloimg = findViewById(R.id.soloimg);
        favorite = findViewById(R.id.favorite);
        purchase = findViewById(R.id.purchase);
        userfragment = findViewById(R.id.userfragment);
        userfragment.setText(userid); //아이디 값 받아오는 구간

        //포인트 구매 버튼
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HWJ_BottomSheetDialog bottomSheetDialog = HWJ_BottomSheetDialog.getInstance(vo_profile.getUserpoint());
                bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
            }
        });

        //서버 연결
        vo_profile = new UserProfileVO();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        //내정보(프래그먼트) 확인
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();

        Fragment fragment = fm.findFragmentById(R.id.frame);
        otherFragment = new HWJ_OtherFragment();

        tr.add(R.id.frame, otherFragment, "profile");
        tr.commit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //내 프로필 리스트 출력
        profileCall();
    }

    //뒤로가기 버튼 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    //프로필 리스트 출력
    private void profileCall(){
        Call<UserProfileVO> call = rs.listProfile(userid);
        call.enqueue(new Callback<UserProfileVO>() {
            @Override
            public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                vo_profile = response.body();

                //이미지 적용
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try{
                            URL url = new URL(vo_profile.getSoloimg());
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.setDoInput(true);
                            conn.connect();

                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try{
                    thread.join();
                    Drawable drawable= new BitmapDrawable(bitmap);
                    soloimg.setBackground(drawable);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<UserProfileVO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}