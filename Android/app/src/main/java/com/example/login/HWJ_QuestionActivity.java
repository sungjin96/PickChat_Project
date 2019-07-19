package com.example.login;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_QuestionActivity extends AppCompatActivity {
    EditText qtitle, qcontent;
    TextView qwriter, send;
    Retrofit retrofit;
    RemoteService rs;
    String strUser, usernickname;
    UserProfileVO uservo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_question);

        //로그인부터 유저값 받아오기
        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        strUser = sharedPreferences.getString("userid","");

        //아이디값 받아오기
        qtitle = findViewById(R.id.qtitle);
        qcontent = findViewById(R.id.qcontent);
        qwriter = findViewById(R.id.qwriter);
        send = findViewById(R.id.send);

        //서버 연결
        uservo = new UserProfileVO();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        //유저 정보 불러오기
        Call<UserProfileVO> call=rs.listProfile(strUser);
        call.enqueue(new Callback<UserProfileVO>() {
            @Override
            public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                uservo = response.body();
                usernickname = uservo.getUsernickname();

                //작성자 닉네임 세팅
                qwriter.setText(usernickname);
            }

            @Override
            public void onFailure(Call<UserProfileVO> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //입력된 정보 insert
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //입력 된 값 가져오기
                String strQtitle = qtitle.getText().toString();
                String strQcontent = qcontent.getText().toString();
                String strQwriter = qwriter.getText().toString();

                HWJ_QuestionListVO vo = new HWJ_QuestionListVO();
                vo.setUserid(strUser);
                vo.setQtitle(strQtitle);
                vo.setQcontent(strQcontent);
                vo.setQwriter(strQwriter);
                vo.setQuestiontype(1);
                System.out.println(vo.toString());

                Call<Void> call = rs.insertQuestion(vo);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        finish();
                        Toast.makeText(HWJ_QuestionActivity.this, "문의완료", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}