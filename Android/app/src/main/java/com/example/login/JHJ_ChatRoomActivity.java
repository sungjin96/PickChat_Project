package com.example.login;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;


public class JHJ_ChatRoomActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    Retrofit retrofit;
    RemoteService rs;

    LinearLayout makerLayout;
    DrawerLayout drawer;
    RecyclerView list;
    TextView myName, youName, txtTime, txtDate, txtAddress, txtMap;
    EditText edtContent;
    Button btnAdd;
    List<JHJ_FireBaseChatVO> array;
    JHJ_ChatAdapter adapter;
    String strUser, myNick, youNick;
    Intent intent;
    String toUserToken, fromUser, fromUserToken, toUser;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    TextView toolbar_title;
    HashMap<String, String> nicks;
    JHJ_Test t = JHJ_Test.getTest();
    String myId = t.getId();
    String youId = t.getId2();
    String myImage, youImage, strTime, strDate, strAddress, strLatitude, strLongitude;
    RoundedImageView myImg, youImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jhj_navi);

        array = new ArrayList<JHJ_FireBaseChatVO>();

        //툴바설정
        getSupportActionBar().setTitle(myNick);

        //뒤로가기버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.jhj_left_black);

        //chatFragment에서 값 받아오기
        Intent intent = getIntent();
        myNick = intent.getStringExtra("myNickname");
        youNick = intent.getStringExtra("userNickName");
        youImage = intent.getStringExtra("img");
        myImage = intent.getStringExtra("myImage");

        System.out.println("chatroom.................."+myNick+"//////////"+youNick);
        //Adapter에 보낼 HashMap
        nicks = new HashMap<String, String>();
        nicks.put("myNick", myNick);
        nicks.put("youNick", youNick);


        //네이게이션뷰
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        myName = view.findViewById(R.id.myName);
        youName = view.findViewById(R.id.youName);
        myImg = view.findViewById(R.id.Img);
        youImg = view.findViewById(R.id.Img1);
        txtTime = view.findViewById(R.id.time);
        txtDate = view.findViewById(R.id.date);
        txtAddress = view.findViewById(R.id.address);
        makerLayout=view.findViewById(R.id.makerLayout);
        txtMap=view.findViewById(R.id.map);

        myName.setText(myNick);
        youName.setText(youNick);
        Picasso.with(this)
                .load(myImage)
                .into(myImg);
        Picasso.with(this)
                .load(youImage)
                .into(youImg);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);


        //내 토큰 읽어오기
        fromUser=myNick;
        Call<JHJ_AppointmentVO> Myidcall = rs.read(myId);
        Myidcall.enqueue(new Callback<JHJ_AppointmentVO>() {
            @Override
            public void onResponse(Call<JHJ_AppointmentVO> call, Response<JHJ_AppointmentVO> response) {
                fromUserToken=response.body().getToken();
            }

            @Override
            public void onFailure(Call<JHJ_AppointmentVO> call, Throwable t) {
            }
        });

        //상대방 토큰 읽어오기
        toUser=youNick;
        Call<JHJ_AppointmentVO> youidcall = rs.read(youId);
        youidcall.enqueue(new Callback<JHJ_AppointmentVO>() {
            @Override
            public void onResponse(Call<JHJ_AppointmentVO> call, Response<JHJ_AppointmentVO> response) {
                toUserToken=response.body().getToken();

            }

            @Override
            public void onFailure(Call<JHJ_AppointmentVO> call, Throwable t) {
            }
        });


        // xml 아이디 받기
        list = findViewById(R.id.listChat);
        edtContent = findViewById(R.id.edtContent);
        btnAdd = findViewById(R.id.btnAdd);

        //아이디 크기 비교하기
        int temp1 = Integer.parseInt(myId);
        int temp2 = Integer.parseInt(youId);
        if (temp1 < temp2) {
            String temp = myId;
            myId = youId;
            youId = temp;
        }

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("chat");

        //파이어베이스 출력
        ref.child(myId).child(youId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String strDate = dataSnapshot.getKey();
                JHJ_FireBaseChatVO vo = dataSnapshot.getValue(JHJ_FireBaseChatVO.class);
                vo.setWdate(strDate);
                vo.setImage(youImage);
                array.add(vo);
                list.scrollToPosition(array.size() - 1);
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

        //리싸이클러뷰, Adapter 에 넣기
        list.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);
        adapter = new JHJ_ChatAdapter(array, JHJ_ChatRoomActivity.this, nicks);
        list.setAdapter(adapter);


        //파이어베이스 저장
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strContent = edtContent.getText().toString();
                //System.out.println(strContent);
                if (strContent.equals("")) {
                    Toast.makeText(JHJ_ChatRoomActivity.this, "내용을 입력하세요!", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = sdf.format(new Date());
                    ref = db.getReference("chat").child(myId).child(youId).child(strDate);
                    JHJ_FireBaseChatVO vo = new JHJ_FireBaseChatVO();
                    vo.setContent(strContent);
                    vo.setUserName(getUserID());
                    String key = ref.child(myId).push().getKey().toString();

                    ref.setValue(vo);
                }

                sendGcm();
                edtContent.setText("");
            }
        });


        //바텀시트
        ImageView plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JHJ_BottomSheetDialog bottomSheetDialog = JHJ_BottomSheetDialog.getInstance();
                bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
            }

        });

    }


    //툴바 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.jhj_chatroom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //툴바 메뉴 기능
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                drawer.openDrawer(GravityCompat.END);
                //약속잡힌 정보 읽어오기
                Call<JHJ_AppointmentVO> read = rs.read(myId);
                read.enqueue(new Callback<JHJ_AppointmentVO>() {
                    @Override
                    public void onResponse(Call<JHJ_AppointmentVO> call, Response<JHJ_AppointmentVO> response) {
                        strTime = response.body().getAptime();
                        strDate = response.body().getApdate();
                        strAddress = response.body().getApaddress();
                        strLatitude= response.body().getLatitude();
                        strLongitude =response.body().getLongitude();
                        txtMap.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1=new Intent(JHJ_ChatRoomActivity.this, MapsActivity2.class);
                                intent1.putExtra("latitude2", strLatitude);
                                intent1.putExtra("longitude2",strLongitude);
                                startActivity(intent1);
                            }
                        });
                        txtTime.setText(strTime);
                        txtAddress.setText(strAddress);
                        txtDate.setText(strDate);

                        if (response.body()==null){
                            makerLayout.setVisibility(View.GONE);
                        }else {
                            makerLayout.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onFailure(Call<JHJ_AppointmentVO> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                return true;
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostResume() {

        super.onPostResume();

        //약속잡기
        intent = getIntent();
        String id = intent.getStringExtra("id");

        String read = intent.getStringExtra("read");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        if (read == null && date == null && time == null) {
            edtContent.setText("");
        } else {
            edtContent.setText("* 약속잡기 *" + "\n날짜 : " + date + "\n시간 : " + time + "\n장소 : " + read);
        }

    }


    //푸시알람
    void sendGcm() {
        Gson gson = new Gson();
        JHJ_NotificationVO notification = new JHJ_NotificationVO();
        notification.setTo(toUserToken);

        // notification.notification.title = ;
        notification.notification.text = fromUser+"님으로 부터 메세지가 도착했습니다.";


        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf8"), gson.toJson(notification));

        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .addHeader("Authorization", "key=AIzaSyCOGljuFf47ROFYUougsZqozUOwt0NMeYM")
                .url("https://gcm-http.googleapis.com/gcm/send")
                .post(requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
            }
        });
    }


    //vo에 넣을 닉네임 바꿔주기
    public String getUserID() {
        if (myNick.equals(nicks.get("myNick"))) {
            strUser = nicks.get("myNick");
        } else {
            strUser = nicks.get("youNick");
        }
        return strUser;
    }

}
