package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser,edtPassword;
    Button btnRegister;
    TextView btnLogin, btnCancel;
    String strUser,strPassword;
    Retrofit retrofit;
    RemoteService rs;

    FirebaseDatabase db;
    DatabaseReference ref;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser=findViewById(R.id.edtUser);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs=retrofit.create(RemoteService.class);

        btnCancel=findViewById(R.id.btnCancel);
        sharedPreferences=getSharedPreferences("userid",MODE_PRIVATE);
        strUser=sharedPreferences.getString("userid","");
        if(!strUser.equals("")){

            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            //intent.putExtra("user",user);
            startActivity(intent);
        }
        //사용자 로그인
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUser = edtUser.getText().toString();
                strPassword = edtPassword.getText().toString();
           if(strUser.equals("")||strPassword.equals("")){
               Toast.makeText(LoginActivity.this, "정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show();
           }else{
               Call<Integer> logincheck = rs.logincheck(strUser,strPassword);
               logincheck.enqueue(new Callback<Integer>() {
                   @Override
                   public void onResponse(Call<Integer> call, Response<Integer> response) {
                       int check = response.body();
                       if(check==1){
                           Toast.makeText(LoginActivity.this, "아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                       }else if(check==2){
                           Toast.makeText(LoginActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                       }else{
                           SharedPreferences.Editor editor = sharedPreferences.edit();
                           editor.putString("userid", strUser);
                           editor.commit();
                           loginUser(strUser);

                       }
                   }

                   @Override
                   public void onFailure(Call<Integer> call, Throwable t) {

                   }
               });


            }
            }
        });
    }

    //사용자 로그인
    private void loginUser(String user){
        updateProfile();
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        //intent.putExtra("user",user);
        startActivity(intent);


    }
    //토큰 값 받기
    private void updateProfile(){
        if(strUser != null){
            UserVO vo = new UserVO();
            vo.setFromUser(strUser);
            vo.setToken(FirebaseInstanceId.getInstance().getToken());
            //vo.setToken("c_EnELHmF4I:APA91bEqqIG10HiOFX1UpIuUccZkryNSNT_298ojt3m0i8sEaCv7r53vdLRRgkMTHcdWpvHakro4RJpKxTh3FEzGVVRHkXZKcFewgVwanqlN6ASge09PPN7n7cT8zcTMJlMvGusGj52Q");
            //System.out.println(vo.toString());
            db = FirebaseDatabase.getInstance();
            db.getReference("userstoken").child(vo.getFromUser()).setValue(vo);
        }
    }

}
