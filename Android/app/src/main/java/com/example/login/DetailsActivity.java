package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class DetailsActivity extends AppCompatActivity {
    Retrofit retrofit;
    RemoteService rs;
    List<type_categoryVO> type_category;
    RecyclerView list;
    type_categoryAdapter adapter;
    TextView category;
    Button btnnext;
    TypeInsertVO vo;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        list = findViewById(R.id.list);
        category = findViewById(R.id.category);
        btnnext=findViewById(R.id.btnnext);
        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");

        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        list.setLayoutManager(staggeredGridLayoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);



        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo = new TypeInsertVO();
                ArrayList<Integer> array = new ArrayList<>();
                for (int i = 0; i < type_category.size(); i++) {
                    if (type_category.get(i).isCheck()) {
                        array.add(type_category.get(i).getTypeid());
                    }
                }
                //System.out.println(array.toString());
                Integer[] tempint = new Integer[array.size()];
                for (int i = 0; i < tempint.length; i++) {
                    tempint[i] = array.get(i);
                    //System.out.println(tempint[i]);
                }
                vo.setTypeid(tempint);
                vo.setUserid(userid);
                Call<Void> call = rs.typeinsert(vo);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                         System.out.println(".............입력완료"+vo.toString());
                        onPostResume();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("오류", t.getMessage());
                    }
                });

                Intent intent=new Intent(DetailsActivity.this,TypeActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onPostResume() {
        Call<List<type_categoryVO>> call = rs.listCategory();
        call.enqueue(new Callback<List<type_categoryVO>>() {
            @Override
            public void onResponse(Call<List<type_categoryVO>> call, Response<List<type_categoryVO>> response) {
                type_category = response.body();
                adapter = new type_categoryAdapter(DetailsActivity.this, type_category);
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<type_categoryVO>> call, Throwable t) {

            }
        });
        super.onPostResume();
    }

}
