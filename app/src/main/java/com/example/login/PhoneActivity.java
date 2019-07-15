package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import retrofit2.Retrofit;

public class PhoneActivity extends AppCompatActivity {
    Button btnadd, btnnext;
    Retrofit retrofit;
    NaverService ns;
    EditText txtnum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        txtnum= findViewById(R.id.txtnum);
        btnadd = findViewById(R.id.btnadd);
        btnnext = findViewById(R.id.btnnext);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*
                String number = txtnum.getText().toString();
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("x-ncp-auth-key", "uiV7hHbu3DyAt2g3jVT2");
                header.put("X-NCP-service-secret", "30af487ac92546429cb03d8a21436746");
                naverVO vo = new naverVO();
                vo.setType("SMS");
                vo.setFrom("01055067909");
                vo.setContentType("COMM");
                vo.setCountryCode("82");
                String confirmnumber ="";
                for(int i=0;i<=5;i++){
                    confirmnumber+=(int)(Math.random()*10);
                }
                vo.setContent("인증번호는"+confirmnumber+"입니다.");
                String[] numberlist = new String[1];
                numberlist[0]= number;
                vo.setTo(numberlist);
                System.out.println(vo.toString());
                retrofit = new Retrofit.Builder().baseUrl(NAVER_URL).addConverterFactory(GsonConverterFactory.create()).build();

                ns = retrofit.create(NaverService.class);

                Call<ResponseBody> call = ns.sendSMS(header,vo);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("승리", response.isSuccessful() + ""+response.headers().toString());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("패배", t.getMessage());

                    }
                });
                */

                LinearLayout linearLayout =findViewById(R.id.Linear);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PhoneActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}