package com.example.login;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnaView=findViewById(R.id.bnaView);

        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.frame);
        FragmentTransaction tr = fm.beginTransaction();
        HomeFragment homefragment=new HomeFragment();
        tr.add(R.id.frame,homefragment,"home");
        tr.commit();

        //하단 액션바 버튼 클릭 시 Fragment변환
        bnaView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fm=getSupportFragmentManager();
                Fragment fragment=fm.findFragmentById(R.id.frame);
                FragmentTransaction tr = fm.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.home:
                        getSupportActionBar().setTitle("메인화면");
                        HomeFragment homefragment=new HomeFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, homefragment, "home");
                            tr.commit();
                        }
                        return true;
                    case R.id.bbs:
                        getSupportActionBar().setTitle("게시판");
                        BbsFragment bbsfragment=new BbsFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, bbsfragment, "bbs");
                            tr.commit();
                        }
                        return true;
                    case R.id.chat:
                        getSupportActionBar().setTitle("채팅 가능 사람");
                        ChatFragment chatfragment=new ChatFragment();
                        if(fragment!=null) {

                            tr.replace(R.id.frame, chatfragment, "chat");
                            tr.commit();
                        }
                        return true;
                    case R.id.like:
                        LikeFragment likefragment=new LikeFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, likefragment, "like");
                            tr.commit();
                        }
                        return true;
                    case R.id.account:
                        AccountFragment accountfragment=new AccountFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, accountfragment, "account");
                            tr.commit();
                        }
                        return true;
                }
                return true;
            }
        });

    }
}
