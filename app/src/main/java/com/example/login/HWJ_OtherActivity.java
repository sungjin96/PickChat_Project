package com.example.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_OtherActivity extends AppCompatActivity {
    RelativeLayout soloimg;
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
    int favorite_check = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_other);

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("프로필");

        //상대방 아이디 받아오는 구간
        intent = getIntent();
        userid = intent.getStringExtra("id");

        //아이디값 찾기
        soloimg = findViewById(R.id.soloimg);
        favorite = findViewById(R.id.favorite);
        userfragment = findViewById(R.id.userfragment);
        userfragment.setText(userid);

        //좋아요 버튼 누를 때
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favorite_check == -1) {
                    AlertDialog.Builder box = new AlertDialog.Builder(HWJ_OtherActivity.this);
                    box.setTitle("좋아요");
                    box.setMessage("좋아요를 누를 시 10포인트 만큼 차감됩니다. 좋아요 하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        Call<Void> call = rs.updatePoint("01000020002", -10); //아이디 값 받아오는 구간
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                favorite.setImageResource(R.drawable.icon_favorite_full);
                                favorite_check = 0;
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                        }
                    });
                    box.setNegativeButton("아니오", null);
                    box.show();
                } else {
                    favorite.setImageResource(R.drawable.icon_favorite);
                    favorite_check = -1;
                }
            }
        });

        //서버 연결
        vo_profile = new UserProfileVO();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        //상대방정보(프래그먼트) 확인
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
        //상대방 프로필 리스트 출력
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
    private void profileCall() {
        Call<UserProfileVO> call = rs.listProfile(userid);
        call.enqueue(new Callback<UserProfileVO>() {
            @Override
            public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                vo_profile = response.body();

                //이미지 적용
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            URL url = new URL(vo_profile.getSoloimg());
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setDoInput(true);
                            conn.connect();

                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try {
                    thread.join();
                    Drawable drawable = new BitmapDrawable(bitmap);
                    soloimg.setBackground(drawable);
                } catch (InterruptedException e) {
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
