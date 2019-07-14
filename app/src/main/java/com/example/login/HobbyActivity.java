package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HobbyActivity extends AppCompatActivity {
    Retrofit retrofit;
    RemoteService rs;
    List<hobby_categoryVO> hobby_category;
    RecyclerView list;
    hobbyAdapter adapter;
    TextView category;
    TextView nextbtn;
    TypeInsertVO vo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        list = findViewById(R.id.list);
        nextbtn = findViewById(R.id.nextbtn);
        category = findViewById(R.id.category);

        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);


        list.setLayoutManager(staggeredGridLayoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo = new TypeInsertVO();
                ArrayList<Integer> array = new ArrayList<>();
                for(int i =0;i<hobby_category.size();i++){
                    if(hobby_category.get(i).isCheck()){
                        array.add(hobby_category.get(i).getHobbyid());
                    }
                }
                System.out.println(array.toString());
                Integer[] tempint= new Integer[array.size()];
                for(int i=0; i<tempint.length;i++){
                    tempint[i]= array.get(i);
                    System.out.println(tempint[i]);
                }

                vo.setTypeid(tempint);
                vo.setUserid("01000010001");
                Call<Void> call = rs.typeinsert(vo);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // System.out.println(vo.toString());
                        onPostResume();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("오류", t.getMessage());
                    }
                });
                Intent intent=new Intent(HobbyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPostResume() {
        Call<List<hobby_categoryVO>> call = rs.listhobby();
        call.enqueue(new Callback<List<hobby_categoryVO>>() {
            @Override
            public void onResponse(Call<List<hobby_categoryVO>> call, Response<List<hobby_categoryVO>> response) {
                hobby_category = response.body();
                adapter = new hobbyAdapter(HobbyActivity.this, hobby_category);



                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<hobby_categoryVO>> call, Throwable t) {

            }
        });
        super.onPostResume();
    }
}
