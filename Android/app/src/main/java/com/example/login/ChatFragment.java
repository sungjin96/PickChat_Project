package com.example.login;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.login.RemoteService.BASE_URL;

public class ChatFragment extends Fragment {
    Retrofit retrofit;
    RemoteService rs;
    ListView list;
    List<UserProfileVO> users;
    MyAdapter myadapter;
    String myId;
    JHJ_Test test = JHJ_Test.getTest();
    Intent intent;
    FirebaseDatabase db;
    DatabaseReference ref;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_chat,container,false);
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //유저 아이디값 받기
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("userid",MODE_PRIVATE);
        myId = sharedPreferences.getString("userid","");

        list = view.findViewById(R.id.list);
        intent=new Intent(getContext(),JHJ_ChatRoomActivity.class);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);


        //채팅 가능한 사람 리스트 출력
        Call<List<UserProfileVO>> call = rs.eachlikeUser(myId);
        call.enqueue(new Callback<List<UserProfileVO>>() {
            @Override
            public void onResponse(Call<List<UserProfileVO>> call, Response<List<UserProfileVO>> response) {
                users = response.body();
                myadapter = new ChatFragment.MyAdapter(getContext(), R.layout.item_chat_user, users);
                list.setAdapter(myadapter);

            }

            @Override
            public void onFailure(Call<List<UserProfileVO>> call, Throwable t) { }
        });

        //로그인한 사람 읽어오기
        Call<UserProfileVO> Myidcall = rs.listProfile(myId);
        Myidcall.enqueue(new Callback<UserProfileVO>() {
            @Override
            public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                String nickName = response.body().getUsernickname();
                String myImg=response.body().getSoloimg();
                test.setId(response.body().getUserid());
                intent.putExtra("myImage", myImg);
                intent.putExtra("myNickname",nickName);
            }

            @Override
            public void onFailure(Call<UserProfileVO> call, Throwable t) {

            }
        });

        //로그인한사람 토근 저장
        UserProfileVO uservo=new UserProfileVO();
        uservo.setUserid(myId);
        uservo.setToken(FirebaseInstanceId.getInstance().getToken());
        Call<Void> updateToken =rs.tokenUpdate(uservo);
        //System.out.println(uservo.getToken());
       // System.out.println(uservo.getUserid());
        updateToken.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }




    //어댑터
    class MyAdapter extends BaseAdapter {
        Context context;
        List<UserProfileVO> array;
        int layout;
        Bitmap bitmap;
        TextView btnchat;

        public MyAdapter(Context context, int layout, List<UserProfileVO> array) {
            this.context = context;
            this.array = array;
            this.layout = layout;
        }

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView =getLayoutInflater().inflate(R.layout.item_chat_user,parent,false);

                ImageView img=convertView.findViewById(R.id.img);
                TextView name=convertView.findViewById(R.id.name);
                TextView age=convertView.findViewById(R.id.age);
                TextView local=convertView.findViewById(R.id.local);
                TextView comment=convertView.findViewById(R.id.comment);
                TextView btnchat=convertView.findViewById(R.id.btnchat);

                //채팅하기 버튼
                btnchat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db = FirebaseDatabase.getInstance();
                        ref = db.getReference("chat");
                        userId=array.get(position).getUserid();
                        String img=array.get(position).getSoloimg();
                        String usernick=array.get(position).getUsernickname();
                        test.setId2(array.get(position).getUserid());
                        intent.putExtra("myId", myId);
                        intent.putExtra("userId",userId);
                        intent.putExtra("userNickName", usernick);
                        intent.putExtra("img", img);
                        startActivity(intent);
                    }
                });

                name.setText(array.get(position).getUsername());
                age.setText(String.valueOf(array.get(position).getUserage()));
                local.setText(array.get(position).getLocalname());
                Thread thread=new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try{
                            URL url=new URL(array.get(position).getSoloimg());
                            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                            conn.setDoInput(true);
                            conn.connect();

                            InputStream is=conn.getInputStream();
                            bitmap= BitmapFactory.decodeStream(is);

                        }catch (MalformedURLException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try{
                    thread.join();
                    img.setImageBitmap(bitmap);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            return convertView;
        }
    }


}