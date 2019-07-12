package food.com.example.projectchatting;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static food.com.example.projectchatting.RemoteService.BASE_URL;

public class ChatRoomActivity extends AppCompatActivity {
    DrawerLayout drawer;
    ImageView gallery;
    RecyclerView list;
    EditText edtContent;
    Button btnAdd;
    FirebaseDatabase db;
    DatabaseReference ref;
    List<ChatVO> array;
    List<UserVO> users;
    ChatAdapter adapter;
    Retrofit retrofit;
    RemoteService rs;
    String uid = "user02";
    String strUser = "";
    UserVO ObUser;
    ChatVO vo;
    String id;
    String id2;

//        public String asdasd() {
//            int a = (int) (Math.random()*2);
//
//            String[] arr =  new  String[2];
//            arr[0]= "user0";
//            arr[1] = "user02";
//
//            System.out.println("     " +a);
//
//            return arr[a];
//        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Test t = Test.getTest();
        id = t.getId();
        id2 = t.getId2();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi);

        //툴바설정
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.chatroom_title);

        //뒤로가기버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_black);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);
        users = new ArrayList<UserVO>();


        Intent intent = getIntent();
        id2 = intent.getStringExtra("number");
        System.out.println(id2);
        //System.out.println("onCreate......................... "+strUser);


        // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //strUser = user.getEmail();
        //getSupportActionBar().setSubtitle(strUser);


//        id = asdasd();
//
//        if (id.equals(("user02"))){
//            id = "user0";
//        }else {
//            id2 ="user02";
//        }
        strUser = id2;

        int temp1 = Integer.parseInt(id);
        int temp2 = Integer.parseInt(id2);
        if (temp1 < temp2) {
            String temp = id2;
            id2 = id;
            id = temp;
        }
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("chat");

        System.out.println("id...."+id);
        System.out.println("id2...."+id2);
        ref.child(id).child(id2).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String strDate = dataSnapshot.getKey();
                ChatVO vo = dataSnapshot.getValue(ChatVO.class);
                vo.setWdate(strDate);
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


//        Call<UserVO> listCall=rs.listUser(uid);
//        listCall.enqueue(new Callback<UserVO>() {
//            @Override
//            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
//                ObUser = response.body();
//                strUser = id;
//                vo = new ChatVO();
//                vo.setUserName(strUser);
//                System.out.println("log =....... " + vo.getUserName());


        // String aa = ref.child(id2).push().getParent().toString();
        //  String result = aa.substring(aa.length()-11, aa.length());
        //   System.out.println("키........"+result);
        //   System.out.println(ref.child(id).push().getParent().toString());
        //파이어베이스 출력
//    }
//            @Override
//            public void onFailure(Call<UserVO> call, Throwable t) {
//                System.out.println(".............................error" + t.toString());
//            }
//        });


        list = findViewById(R.id.listChat);
        list.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);

        array = new ArrayList<ChatVO>();
        adapter = new ChatAdapter(array, this,strUser);
        list.setAdapter(adapter);


        edtContent = findViewById(R.id.edtContent);

        //파이어베이스 저장
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strContent = edtContent.getText().toString();
                if (strContent.equals("")) {
                    Toast.makeText(ChatRoomActivity.this, "내용을 입력하세요!", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = sdf.format(new Date());
                    ref = db.getReference("chat").child(id).child(id2).child(strDate);
                    vo = new ChatVO();
                    vo.setContent(strContent);
                    vo.setUserName(strUser);

                    ref.setValue(vo);
//
//                    ref = db.getReference("chat").child(id2).child(id).child(strDate);
//                    vo = new ChatVO();
//                    vo.setContent(strContent);
//                    vo.setUserName(strUser);
//
//                    ref.setValue(vo);
                }
                edtContent.setText("");
            }
        });


        //바텀시트
        ImageView plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.getInstance();
                bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
            }
        });

        //네이게이션뷰
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    //툴바 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chatroom_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //툴바 메뉴 기능
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu:
           drawer.openDrawer(GravityCompat.END);
                return true;
            case android.R.id.home:
               finish();
                //Toast.makeText(this, "눌리니?", Toast.LENGTH_SHORT).show();
               break;
        }

        return super.onOptionsItemSelected(item);
    }
}
