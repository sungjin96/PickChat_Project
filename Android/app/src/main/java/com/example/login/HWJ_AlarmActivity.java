package com.example.login;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class HWJ_AlarmActivity extends AppCompatActivity {
    Switch switchView1, switchView2;
    TextView check1, check2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_alarm);

        //상태바 아이콘색상변경
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));
            }
        }else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.BLACK);
        }

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#878789'>알림설정 </font>"));

        //"좋아요 알림" 버튼
        check1 = findViewById(R.id.check1);
        switchView1 = findViewById(R.id.switchView1);
        switchView1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check1.setText("활성화");
                    check1.setTextColor(Color.parseColor("#ff006c"));
                    switchView1.setChecked(true);
                }else{
                    check1.setText("비활성화");
                    check1.setTextColor(Color.parseColor("#818181"));
                    switchView1.setChecked(false);
                }
            }
        });

        //"채팅방 알림" 버튼
        check2 = findViewById(R.id.check2);
        switchView2 = findViewById(R.id.switchView2);
        switchView2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check2.setText("활성화");
                    check2.setTextColor(Color.parseColor("#ff006c"));
                    switchView2.setChecked(true);
                }else{
                    check2.setText("비활성화");
                    check2.setTextColor(Color.parseColor("#818181"));
                    switchView2.setChecked(false);
                }
            }
        });
    }

    //뒤로가기 버튼 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
        }
        return true;
    }
}