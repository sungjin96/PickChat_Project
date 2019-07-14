package com.example.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.login.RemoteService.BASE_URL;


public class BBSListActivity extends AppCompatActivity {
    FloatingActionButton FlotInsert;
    ArrayList<BBSimgVO> array;
    BBSImageAdapter adapter;
    RecyclerView list;
    Retrofit retrofit;
    RemoteService rs;
    Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_list);
        permissionCheck();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle("게시판");

        list = findViewById(R.id.View);
        list.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        //불규칙한 레이아웃
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        list.setLayoutManager(manager);


        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);


        FlotInsert = findViewById(R.id.FlotInsert);
        FlotInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BBSListActivity.this, "입력", LENGTH_LONG).show();
                Intent intent = new Intent(BBSListActivity.this, BBSInsertActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Call<ArrayList<BBSimgVO>> call = rs.list();
        call.enqueue(new Callback<ArrayList<BBSimgVO>>() {
            @Override
            public void onResponse(Call<ArrayList<BBSimgVO>> call, Response<ArrayList<BBSimgVO>> response) {
                array = response.body();

                adapter = new BBSImageAdapter(BBSListActivity.this, (ArrayList<BBSimgVO>) array);
                list.setAdapter(adapter);
                BBSItemClickSupport.addTo(list).setOnItemClickListener(new BBSItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(BBSListActivity.this, BBSReadActivity.class);
                        intent.putExtra("bno", array.get(position).getBno());
//                        intent.putExtra("imgPath", array.get(position).getImgpath());
//                        Log.d("이미지패스", array.get(position).getImgpath());
                        startActivity(intent);


                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<BBSimgVO>> call, Throwable t) {
                Log.e("error", t.toString());

            }
        });

    }

    //액션바 삽입
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.topSarch:
                Toast.makeText(this, "검색", LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    //권한 체크
    public void permissionCheck() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    public interface ClickListener {
        void onClick(View v, int position);

        void onLongClick(View v, int position);
    }


}
