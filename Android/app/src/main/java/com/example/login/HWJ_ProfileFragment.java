package com.example.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_ProfileFragment extends Fragment {
    TextView usernickname, userage, localname, usercomment, userjob;
    RecyclerView list_mytype, list_userhobby, list_liketype;
    HWJ_MyTypeAdapter mtAdapter;
    HWJ_LikeTypeAdapter ltAdapter;
    HWJ_HobbyAdapter hAdapter;
    List<HWJ_TypeVO> mtArray, ltArray;
    List<HWJ_HobbyVO> hArray;
    UserProfileVO vo_profile;
    Retrofit retrofit;
    RemoteService rs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hwj_other, container, false);

        //아이디 값 찾기
        usernickname = root.findViewById(R.id.usernickname);
        userage = root.findViewById(R.id.userage);
        userjob = root.findViewById(R.id.userjob);
        usercomment = root.findViewById(R.id.usercomment);
        localname = root.findViewById(R.id.localname);
        list_mytype = root.findViewById(R.id.list_mytype);
        list_userhobby = root.findViewById(R.id.list_userhobby);
        list_liketype = root.findViewById(R.id.list_liketype);

        //매력, 취미, 이상형 리스트 연결
        mtArray = new ArrayList<HWJ_TypeVO>();
        hArray = new ArrayList<HWJ_HobbyVO>();
        ltArray = new ArrayList<HWJ_TypeVO>();

        //한줄에 3개씩 나오게 함
        StaggeredGridLayoutManager staggeredGridLayoutManager1
                = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        list_mytype.setLayoutManager(staggeredGridLayoutManager1);

        StaggeredGridLayoutManager staggeredGridLayoutManager2
                = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        list_userhobby.setLayoutManager(staggeredGridLayoutManager2);

        StaggeredGridLayoutManager staggeredGridLayoutManager3
                = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        list_liketype.setLayoutManager(staggeredGridLayoutManager3);

        //서버 연결
        vo_profile = new UserProfileVO();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //상대방 프로필 리스트 출력
        profileCall();
        //내매력 리스트 출력
        myTypeCall();
        //취미 리스트 출력
        hobbyCall();
        //이상형 리스트 출력
        likeTypeCall();
    }

    //프로필 리스트 출력
    private void profileCall(){
        Call<UserProfileVO> call = rs.listProfile("01000020002");
        call.enqueue(new Callback<UserProfileVO>() {
            @Override
            public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                vo_profile = response.body();

                //이름 적용
                usernickname.setText(vo_profile.getUsernickname().toString());
                userjob.setText(vo_profile.getUserjob().toString());
                userage.setText(vo_profile.getUserage().toString());
                usercomment.setText(vo_profile.getUsercomment().toString());
                localname.setText(vo_profile.getLocalname().toString());
            }
            @Override
            public void onFailure(Call<UserProfileVO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //내매력 리스트 출력
    private void myTypeCall(){
        Call<List<HWJ_TypeVO>> call = rs.listMytype("01000020002");
        call.enqueue(new Callback<List<HWJ_TypeVO>>() {
            @Override
            public void onResponse(Call<List<HWJ_TypeVO>> call, Response<List<HWJ_TypeVO>> response) {
                mtArray = response.body();
                mtAdapter = new HWJ_MyTypeAdapter(getContext(), mtArray);
                list_mytype.setAdapter(mtAdapter);
                mtAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<HWJ_TypeVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //취미 리스트 출력
    private void hobbyCall(){
        Call<List<HWJ_HobbyVO>> call = rs.listUserhobby("01000020002");
        call.enqueue(new Callback<List<HWJ_HobbyVO>>() {
            @Override
            public void onResponse(Call<List<HWJ_HobbyVO>> call, Response<List<HWJ_HobbyVO>> response) {
                hArray = response.body();
                hAdapter = new HWJ_HobbyAdapter(getContext(), hArray);
                list_userhobby.setAdapter(hAdapter);
                hAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<HWJ_HobbyVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //이상형 리스트 출력
    private void likeTypeCall(){
        Call<List<HWJ_TypeVO>> call = rs.listLiketype("01000020002");
        call.enqueue(new Callback<List<HWJ_TypeVO>>() {
            @Override
            public void onResponse(Call<List<HWJ_TypeVO>> call, Response<List<HWJ_TypeVO>> response) {
                ltArray = response.body();
                ltAdapter = new HWJ_LikeTypeAdapter(getContext(), ltArray);
                list_liketype.setAdapter(ltAdapter);
                ltAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<HWJ_TypeVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}