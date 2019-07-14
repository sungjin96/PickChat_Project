package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class FirstPageActivity extends AppCompatActivity {
    Button loginButton, Button;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first_page);

        loginButton=findViewById(R.id.loginButton);
        Button=findViewById(R.id.Button);
    }
    public void mCLick(View v){
        switch (v.getId()){
            case R.id.loginButton:
                intent=new Intent(FirstPageActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.Button:
                intent=new Intent(FirstPageActivity.this, JoinActivity.class);
                startActivity(intent);
                break;

        }
    }
}
