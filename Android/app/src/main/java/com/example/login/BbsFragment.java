package com.example.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class BbsFragment extends Fragment {
    FloatingActionButton FlotInsert;
    ArrayList<BBSVO> arraylist;
    BBSImageAdapter imageAdapter;
    RecyclerView recyclerView;
    Retrofit retrofit;
    RemoteService rs;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bbs, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);


        recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);

        FlotInsert = view.findViewById(R.id.FlotInsert);
        FlotInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BBSInsertActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<ArrayList<BBSVO>> call = rs.list();
        call.enqueue(new Callback<ArrayList<BBSVO>>() {
            @Override
            public void onResponse(Call<ArrayList<BBSVO>> call, Response<ArrayList<BBSVO>> response) {
                arraylist = response.body();
                imageAdapter = new BBSImageAdapter(getContext(), (ArrayList<BBSVO>) arraylist);
                recyclerView.setAdapter(imageAdapter);

                Call<ArrayList<BBSVO>> callinsert = rs.list();
                callinsert.enqueue(new Callback<ArrayList<BBSVO>>() {
                    @Override
                    public void onResponse(Call<ArrayList<BBSVO>> call, Response<ArrayList<BBSVO>> response) {
                        arraylist = response.body();
                        imageAdapter = new BBSImageAdapter(getContext(), (ArrayList<BBSVO>) arraylist);
                        recyclerView.setAdapter(imageAdapter);
                        Log.i("게시글수", "======" + arraylist.size());
                        if (arraylist.size() <= 0) {
                            AlertDialog.Builder box = new AlertDialog.Builder(getContext());
                            box.setTitle("알림");
                            box.setMessage("게시물이 없습니다.");
                            box.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<BBSVO>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                BBSItemClickSupport.addTo(recyclerView).setOnItemClickListener(new BBSItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(getContext(), BBSReadActivity.class);
                        intent.putExtra("bno", arraylist.get(position).getBno());
                        startActivityForResult(intent, 3000);
                    }

                    @Override
                    public void onItemClick(View v, int pos) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<BBSVO>> call, Throwable t) {
                Log.e("error", t.toString());
                t.printStackTrace();
            }
        });
    }


    //액션바 삽입
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.top, menu);
//        SearchView sv = (SearchView) menu.findItem(R.id.topSarch).getActionView();
//        sv.setQueryHint("해시태그");
//        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
////                Toast.makeText(BBSListActivity.this, "검색어=========" + query, Toast.LENGTH_SHORT).show();
//                Call<List<BBSVO>> callTagRead = rs.bbsTagRead(query);
//                callTagRead.enqueue(new Callback<List<BBSVO>>() {
//                    @Override
//                    public void onResponse(Call<List<BBSVO>> call, Response<List<BBSVO>> response) {
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<BBSVO>> call, Throwable t) {
//
//                    }
//                });
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getContext(), "검색어=========" + newText, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                break;
            case R.id.topSarch:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface ClickListener {
        void onClick(View v, int position);

        void onLongClick(View v, int position);
    }
}
