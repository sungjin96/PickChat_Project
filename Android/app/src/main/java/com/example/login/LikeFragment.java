package com.example.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class LikeFragment extends Fragment {

    TabLayout tab;
    ViewPager pager;
    PagerAdapter adapter;
    ArrayList<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_like,container,false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //액션바(뒤로가기 버튼) 설정
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
//        getSupportActionBar().setTitle("좋아요");

        //탭 설정
        tab = view.findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText(""));
        tab.addTab(tab.newTab().setText(""));
        tab.getTabAt(0).setIcon(R.drawable.heart);
        tab.getTabAt(1).setIcon(R.drawable.heart);

        pager = view.findViewById(R.id.pager);
        fragments = new ArrayList<Fragment>();

        fragments.add(new HWJ_Like1Fragment());
        fragments.add(new HWJ_Like2Fragment());
        adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    //프래그먼트 페이지 붙이기
    private class PagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    //뒤로가기 버튼 활성화
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home :
//                finish();
//        }
//        return true;
//    }
    }

