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
                    //홈버튼
                    case R.id.home:
                        getSupportActionBar().setTitle("PickChat");

                        HomeFragment homefragment=new HomeFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, homefragment, "home");
                            tr.commit();
                        }
                        return true;
                    //게시판버튼
                    case R.id.bbs:
                        getSupportActionBar().setTitle("게시판");

                        BbsFragment bbsfragment=new BbsFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, bbsfragment, "bbs");
                            tr.commit();
                        }
                        return true;
                    //채팅버튼
                    case R.id.chat:
                        getSupportActionBar().setTitle("채팅");

                        ChatFragment chatfragment=new ChatFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, chatfragment, "chat");
                            tr.commit();
                        }
                        return true;
                    //좋아요버튼
                    case R.id.like:
                        getSupportActionBar().setTitle("좋아요");

                        LikeFragment likefragment=new LikeFragment();
                        if(fragment!=null) {
                            tr.replace(R.id.frame, likefragment, "like");
                            tr.commit();
                        }
                        return true;
                    //내정보버튼
                    case R.id.account:
                        //액션바 이름 설정
                        getSupportActionBar().setTitle("내정보");

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
