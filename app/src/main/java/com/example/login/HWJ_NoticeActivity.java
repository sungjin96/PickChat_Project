package com.example.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_NoticeActivity extends AppCompatActivity {
    RecyclerView list;
    Retrofit retrofit;
    RemoteService rs;
    List<HWJ_NoticeVO> array;
    HWJ_NoticeAdapter nAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_notice);

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("공지사항");

        //공지사항 리스트 출력
        array = new ArrayList<HWJ_NoticeVO>();

        list = findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);
    }

    //공지사항 리스트 출력
    @Override
    protected void onPostResume() {
        super.onPostResume();
        Call<List<HWJ_NoticeVO>> call = rs.listNotice();
        call.enqueue(new Callback<List<HWJ_NoticeVO>>() {
            @Override
            public void onResponse(Call<List<HWJ_NoticeVO>> call, Response<List<HWJ_NoticeVO>> response) {
                array = response.body();
                nAdapter = new HWJ_NoticeAdapter(HWJ_NoticeActivity.this, array);
                list.setAdapter(nAdapter);
                nAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HWJ_NoticeVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //뒤로가기 버튼 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
        }
        return true;
    }
}
