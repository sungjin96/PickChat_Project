package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.NaverService.NAVER_URL;

public class PhoneActivity extends AppCompatActivity {
    Button btnadd, btnnext,btnreturn;
    Retrofit retrofit;
    NaverService ns;
    EditText txtnum,txtname,confirmnum;
    boolean confirm = false;
    String confirmnumber="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        txtnum= findViewById(R.id.txtnum);
        txtname=findViewById(R.id.txtname);
        btnadd = findViewById(R.id.btnadd);
        btnnext = findViewById(R.id.btnnext);
        btnreturn=findViewById(R.id.btnreturn);
        confirmnum=findViewById(R.id.confirmnum);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String number = txtnum.getText().toString();
//                HashMap<String, String> header = new HashMap<String, String>();
//                header.put("Content-Type", "application/json; charset=utf-8");
//                header.put("x-ncp-auth-key", "uiV7hHbu3DyAt2g3jVT2");
//                header.put("X-NCP-service-secret", "30af487ac92546429cb03d8a21436746");
//                naverVO vo = new naverVO();
//                vo.setType("SMS");
//                vo.setFrom("01055067909");
//                vo.setContentType("COMM");
//                vo.setCountryCode("82");
//                 confirmnumber ="";
//                for(int i=0;i<=5;i++){
//                    confirmnumber+=(int)(Math.random()*10);
//                }
//                btnreturn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                       String num= confirmnum.getText().toString();
//                       if(num.equals(confirmnumber)){
//                            confirm=true;
//                            btnnext.setVisibility(View.VISIBLE);
//                           Toast.makeText(PhoneActivity.this, "인증 확인되었습니다.", Toast.LENGTH_SHORT).show();
//                       }else{
//                           Toast.makeText(PhoneActivity.this, "인증번호가 다릅니다.", Toast.LENGTH_SHORT).show();
//                       }
//                    }
//                });
//                vo.setContent("인증번호는"+confirmnumber+"입니다.");
//                String[] numberlist = new String[1];
//                numberlist[0]= number;
//                vo.setTo(numberlist);
//                System.out.println(vo.toString());
//                retrofit = new Retrofit.Builder().baseUrl(NAVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
//                ns = retrofit.create(NaverService.class);
//                Call<ResponseBody> call = ns.sendSMS(header,vo);
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        Log.d("승리", response.isSuccessful() + ""+response.headers().toString());
//                    }
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.d("패배", t.getMessage());
//                    }
//                });

                LinearLayout linearLayout =findViewById(R.id.Linear);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(confirm) {
                    Intent intent = new Intent(PhoneActivity.this, ProfileActivity.class);
                    String userid=txtnum.getText().toString();
                    String username=txtname.getText().toString();
                    //intent.putExtra("userid",userid);
                    intent.putExtra("username",username);
                SharedPreferences sharedPreferences = getSharedPreferences("userid", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userid", userid);
                editor.commit();
                    startActivity(intent);
               // }else{
                    Toast.makeText(PhoneActivity.this, "인증을 완료해주세요", Toast.LENGTH_SHORT).show();
               // }
            }
        });
    }
}