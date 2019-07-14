package com.example.login;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("알림설정");

        //"좋아요 알림" 버튼
        check1 = findViewById(R.id.check1);
        switchView1 = findViewById(R.id.switchView1);
        switchView1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    check1.setText("활성화");
                    check1.setTextColor(Color.parseColor("#A074FF"));
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
                    check2.setTextColor(Color.parseColor("#A074FF"));
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
