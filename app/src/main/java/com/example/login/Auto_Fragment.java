package com.example.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class Auto_Fragment extends Fragment {
    AutoScrollViewPager autoViewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.auto_fragment,container,false);

        ArrayList<String> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        data.add("https://image.mycelebs.com/celeb/new/ve/658_ve_1450088333.jpg");
        data.add("https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/1hYk/image/AqQJUQkNYKq2MEC18kF2ml8wLBA");
        data.add("https://post-phinf.pstatic.net/MjAxNzExMDlfMTY2/MDAxNTEwMjAzMzYxNjAy.cUt0FVHH3_lyy2-qNWY1j7D22afFE9nmFvjOuTrxPJIg.xV0NdrI2nUpBx9j4NX5FV0Ltyqs5_GqgRLTJIKUOGsAg.JPEG/20150710221630_cdmmkdwc.jpg?type=w1200");
        data.add("https://t1.daumcdn.net/cfile/tistory/2750F33757D4F3B314");

        autoViewPager = (AutoScrollViewPager)root.findViewById(R.id.autoViewPager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(getContext(), data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(2500); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작

        return root;
    }
}
