package com.example.login;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_BlockListActivity extends AppCompatActivity {
    RecyclerView list;
    Retrofit retrofit;
    RemoteService rs;
    List<HWJ_BlockVO> array;
    HWJ_BlockListAdapter blAdapter;
    String strUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_block_list);

        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        strUser = sharedPreferences.getString("userid","");

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
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#878789'>차단친구목록(주소록)</font>"));

        //차단친구목록 리스트
        array = new ArrayList<HWJ_BlockVO>();

        list = findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);
    }

    //차단친구목록 리스트 출력
    @Override
    protected void onPostResume() {
        super.onPostResume();
        Call<List<HWJ_BlockVO>> call = rs.listBlock(strUser); //아이디 값 받아오는 구간
        call.enqueue(new Callback<List<HWJ_BlockVO>>() {
            @Override
            public void onResponse(Call<List<HWJ_BlockVO>> call, Response<List<HWJ_BlockVO>> response) {
                array = response.body();
                blAdapter = new HWJ_BlockListAdapter(HWJ_BlockListActivity.this, array);
                list.setAdapter(blAdapter);
                blAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HWJ_BlockVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
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
}
