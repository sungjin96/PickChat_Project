package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {
    CheckBox btn1,btn2,btn3,btn4,btn5,allcheck;
    Button btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btnnext= findViewById(R.id.btnnext);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        allcheck = findViewById(R.id.allcheck);

        allcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.allcheck:
                        if (allcheck.isChecked() == true) {
                            btn1.setChecked(true);
                            btn2.setChecked(true);
                            btn3.setChecked(true);
                            btn4.setChecked(true);
                            btn5.setChecked(true);
                        } else {
                            btn1.setChecked(false);
                            btn2.setChecked(false);
                            btn3.setChecked(false);
                            btn4.setChecked(false);
                            btn5.setChecked(false);
                        }
                }
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn1.isChecked()&&btn2.isChecked()&&btn3.isChecked()) {
                    Intent intent = new Intent(JoinActivity.this, PhoneActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(JoinActivity.this,"필수 항목을 체크해 주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
