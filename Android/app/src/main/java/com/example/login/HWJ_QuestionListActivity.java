package com.example.login;

import android.content.SharedPreferences;
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

public class HWJ_QuestionListActivity extends AppCompatActivity {
    RecyclerView list;
    Retrofit retrofit;
    RemoteService rs;
    List<HWJ_QuestionListVO> array;
    HWJ_QuestionListAdapter qAdapter;
    String strUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_question_list);

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("공지사항");

        //로그인부터 유저값 받아오기
        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        strUser = sharedPreferences.getString("userid","");

        //문의사항 리스트 출력
        array = new ArrayList<HWJ_QuestionListVO>();
        list = findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);

        //서버 연결
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        Call<List<HWJ_QuestionListVO>> call = rs.listQuestion(strUser); //유저 아이디 받아오는 곳
        call.enqueue(new Callback<List<HWJ_QuestionListVO>>() {
            @Override
            public void onResponse(Call<List<HWJ_QuestionListVO>> call, Response<List<HWJ_QuestionListVO>> response) {
                array = response.body();
                qAdapter = new HWJ_QuestionListAdapter(HWJ_QuestionListActivity.this, array);
                list.setAdapter(qAdapter);
                qAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HWJ_QuestionListVO>> call, Throwable t) {

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