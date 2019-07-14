package com.example.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HWJ_QuitActivity extends AppCompatActivity {
    Button btn_point;
    TextView quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_quit);

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("회원탈퇴");

        //포인트 버튼 설정
        btn_point = findViewById(R.id.btn_point);
        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HWJ_QuitActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                Toast.makeText(HWJ_QuitActivity.this, "100p가 적립되었어요!", Toast.LENGTH_SHORT).show();
            }
        });

        //탈퇴하기 버튼 설정
        quit = findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder box = new AlertDialog.Builder(HWJ_QuitActivity.this);
                box.setMessage("정말 탈퇴하실건가요 ㅠㅠ?");
                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(HWJ_QuitActivity.this, "로그인화면으로 넘어감 ㅎ", Toast.LENGTH_SHORT).show();
                    }
                });
                box.setNegativeButton("아니오", null);
                box.show();
            }
        });
    }

    //뒤로가기 버튼 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
