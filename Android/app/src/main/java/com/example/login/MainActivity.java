package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnaView;
    boolean joincheck=false;
    Retrofit retrofit;
    RemoteService rs;
    UserProfileVO vo;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title);

        //로그인 한 유저 아이디값
        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");


        vo = new UserProfileVO();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        //가입 완료하고 넘어올 때
        Intent intent=getIntent();
        joincheck=intent.getBooleanExtra("join",false);
        if(joincheck){
            AlertDialog.Builder box=new AlertDialog.Builder(MainActivity.this);
            box.setTitle("회원 가입 성공!");
            box.setMessage("회원 가입을 축하드립니다. 100point 증정");
            box.setNegativeButton("확인",null);
            box.show();
        }

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

        //정보 리드하기
        Call<UserProfileVO> call=rs.listProfile(userid);
        call.enqueue(new Callback<UserProfileVO>() {
            @Override
            public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                vo = response.body();

            }

            @Override
            public void onFailure(Call<UserProfileVO> call, Throwable t) {
                System.out.println("정보리드 에러"+t.toString());
            }
        });

        bnaView=findViewById(R.id.bnaView);

        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.frame);
        FragmentTransaction tr = fm.beginTransaction();
        HomeFragment homefragment=new HomeFragment();
        tr.add(R.id.frame,homefragment,"home");
        tr.commit();

        //하단 액션바 버튼 클릭 시 Fragment변환
        bnaView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fm=getSupportFragmentManager();
                Fragment fragment=fm.findFragmentById(R.id.frame);
                FragmentTransaction tr = fm.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.home:
                        showcustom();
                        HomeFragment homefragment=new HomeFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, homefragment, "home");
                            tr.commit();
                        }
                        return true;
                    case R.id.bbs:
                        showcustom();
                        BbsFragment bbsfragment=new BbsFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, bbsfragment, "bbs");
                            tr.commit();
                        }
                        return true;
                    case R.id.chat:
                        showcustom();
                        ChatFragment chatfragment=new ChatFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, chatfragment, "chat");
                            tr.commit();
                        }
                        return true;
                    case R.id.like:
                        showcustom();
                        LikeFragment likefragment=new LikeFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, likefragment, "like");
                            tr.commit();
                        }
                        return true;
                    case R.id.account:
                        getSupportActionBar().hide();

                        AccountFragment accountfragment=new AccountFragment();
                        //accountfragment.setArguments(bundle);
                        if(fragment!=null) {
                            tr.replace(R.id.frame, accountfragment, "account");
                            tr.commit();
                        }
                        return true;
                }
                return true;
            }
        });
    }

    public void showcustom(){
        getSupportActionBar().show();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title);
    }
}
