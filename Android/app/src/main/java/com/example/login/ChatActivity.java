package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {
    RecyclerView list;
    EditText edtContent;
    Button btnAdd;
    FirebaseDatabase db;
    DatabaseReference ref;
    ArrayList<ChatVO> array;
    ChatAdapter adapter;
    String userid,chatwith;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String toUserToken,fromUser;
    ChatVO vo;
    String strDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setTitle("채팅");

        list=findViewById(R.id.list);
        edtContent=findViewById(R.id.edtContent);
        btnAdd=findViewById(R.id.btnAdd);

        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
        chatwith=intent.getStringExtra("chatwith");
        //Toast.makeText(this, "userid?"+userid+"chatwith?????"+chatwith, Toast.LENGTH_SHORT).show();

        list.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        list.setLayoutManager(manager);

        array=new ArrayList<ChatVO>();
        adapter=new ChatAdapter(this,array,userid);
        list.setAdapter(adapter);


//        db = FirebaseDatabase.getInstance();
//        ref = db.getReference("chat");
//
//        //입력한 대화내용 출력
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String strDate=dataSnapshot.getKey();
//                ChatVO vo=dataSnapshot.getValue(ChatVO.class);
//                vo.setWdate(strDate);
//                array.add(vo);
//
//                list.scrollToPosition(array.size()-1);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("01000010001").child(chatwith);
        //입력한 대화내용 출력
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String keyUser=dataSnapshot.getKey();
                ChatVO vo=dataSnapshot.getValue(ChatVO.class);
                //vo.setWdate(strDate);
                System.out.println("...................."+vo.toString()+".//////////"+keyUser);
                    vo.setWdate(strDate);
                    vo.setUserid(keyUser);
                    //System.out.println("내용??"+dataSnapshot.child(strDate).child("content").toString());
                    System.out.println("vo에서 출력되는내용??????????/"+vo.toString());

                array.add(vo);

                list.scrollToPosition(array.size()-1);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //대화 내용 입력
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String strContent=edtContent.getText().toString().trim();
//                if(strContent.equals("")){
//                    Toast.makeText(ChatActivity.this, "내용을 입력하세요!", Toast.LENGTH_SHORT).show();
//                }else {
//                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String strDate=sdf.format(new Date());
//                    ref=db.getReference("chat").child(strDate);
//                    ChatVO vo=new ChatVO();
//                    vo.setContent(strContent);
//                    vo.setUserid(userid);
//                    ref.setValue(vo);
//                }
//                getToken();
//                edtContent.setText("");
//            }
//        });
//

        //test
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strContent=edtContent.getText().toString().trim();
                if(strContent.equals("")){
                    Toast.makeText(ChatActivity.this, "내용을 입력하세요!", Toast.LENGTH_SHORT).show();
                }else {
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    strDate=sdf.format(new Date());
                    ref=db.getReference("01000010001").child(chatwith).child(strDate);
                    ChatVO vo=new ChatVO();
                    vo.setContent(strContent);
                    vo.setUserid(userid);
                    vo.setWdate(strDate);
                    ref.setValue(vo);
                }
                getToken();
                edtContent.setText("");
            }
        });
/////////////////////////////////
    }

    //토큰 받기
    void getToken(){
        //알림을 보내기위해 받을 유저의 토큰값 받아오기
        ref = db.getReference("userstoken").child("/01090397724");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ChatVO vo=dataSnapshot.getValue(ChatVO.class);
                toUserToken="ciKlhrsmIcY:APA91bHHa6ujp3lMFGmGYoUlUcNGVr1NH7jPHNeEWHOkOwFqQZDP2nKJOBtNcBI2B-_nG5KSkTtS7zd1pzxjZjQz_OzZmt-_5K3xaeeoUNlQMg3vs5e7gQY6x4fkxx-3oh3RiDHg73B4";
                //toUserToken=vo.getToken();
                sendGcm();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    //푸시알람
    void sendGcm(){
        Gson gson=new Gson();
        NotificationVO notification=new NotificationVO();
        notification.setTo(toUserToken);
        notification.notification.title="새로운 알림";
        notification.notification.text="새로운 메세지가 있습니다.";
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf8"),gson.toJson(notification));

        Request request=new Request.Builder()
                .header("Content-Type","application/json")
                .addHeader("Authorization","key=AIzaSyCOGljuFf47ROFYUougsZqozUOwt0NMeYM")
                .url("https://gcm-http.googleapis.com/gcm/send")
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }


    //액션메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
