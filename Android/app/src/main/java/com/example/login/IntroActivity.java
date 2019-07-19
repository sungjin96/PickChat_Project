package com.example.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getSupportActionBar().setTitle("");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //퍼미션 체크
        String[] required_permission= {Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS};
        int permissioncamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permissionlocation  = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionread = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionwrite = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permssioncontact = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS);

        if(permissioncamera== PackageManager.PERMISSION_GRANTED&&permissionlocation==PackageManager.PERMISSION_GRANTED&&permissionread==PackageManager.PERMISSION_GRANTED
                &&permissionwrite==PackageManager.PERMISSION_GRANTED&&permssioncontact==PackageManager.PERMISSION_GRANTED){
            //딜레이 2초
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override

                public void run() {
                    Intent intent = new Intent(IntroActivity.this, FirstPageActivity.class);
                    startActivity(intent);
                    finish();
                }
            },3000);
        }else{
            ActivityCompat.requestPermissions(this,required_permission,100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        startActivity(new Intent(this, FirstPageActivity.class));
    }
}