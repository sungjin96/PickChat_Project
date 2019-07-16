package com.example.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_Like1Fragment extends Fragment{
    RemoteService rs;
    List<HWJ_LikeVO> array;
    Retrofit retrofit;
    HWJ_Like1Adapter lAdapter;
    RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hwj__like1, container, false);

        array = new ArrayList<HWJ_LikeVO>();

        list = view.findViewById(R.id.list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        list.setLayoutManager(manager);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        return view;
    }

    //나를 좋아요 한 사람 리스트
    @Override
    public void onResume() {
        super.onResume();
        Call<List<HWJ_LikeVO>> call = rs.listWhoLike("01000020002"); //아이디 값 받아오는 구간
        call.enqueue(new Callback<List<HWJ_LikeVO>>() {
            @Override
            public void onResponse(Call<List<HWJ_LikeVO>> call, Response<List<HWJ_LikeVO>> response) {
                array = response.body();
                lAdapter = new HWJ_Like1Adapter(getContext(), array);
                list.setAdapter(lAdapter);
                lAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HWJ_LikeVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}