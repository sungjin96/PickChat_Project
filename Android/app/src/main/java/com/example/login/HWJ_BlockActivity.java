package com.example.login;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_BlockActivity extends AppCompatActivity {
    ArrayList<HWJ_BlockVO> array;
    ListView list;
    Retrofit retrofit;
    RemoteService rs;
    TextView blockAll;
    MyAdapter adapter;
    String strUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_block);

        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        strUser = sharedPreferences.getString("userid","");

        //상태바 아이콘색상변경
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));
            }
        }else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.BLACK);
        }

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("내친구차단");

        //주소록 읽기
        readContacts();

        //"모두차단" 받아오기
        blockAll = findViewById(R.id.blockAll);

        //리스트 적용
        list = findViewById(R.id.list);
        adapter = new MyAdapter();
        list.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);
    }

    //주소록 읽어오기
    private void readContacts() {
        array = new ArrayList<HWJ_BlockVO>();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);

        int intId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int intName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        while (cursor.moveToNext()) {
            String id = cursor.getString(intId);
            String number = "";
            Cursor cursor2 = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id}, null);
            while (cursor2.moveToNext()) {
                number = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            cursor2.close();

            //vo에 주소록 값 넣기
            HWJ_BlockVO vo = new HWJ_BlockVO();
            vo.setBlocker(strUser); //유저아이디 받는곳
            vo.setBlockno(intId);
            vo.setBlockedname(cursor.getString(intName));
            vo.setBlocked(number.replace("-",""));
            array.add(vo);
        }
        cursor.close();
    }

    //어댑터 설정
    class MyAdapter extends BaseAdapter {
        TextView name, phone;

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
                convertView = getLayoutInflater().inflate(R.layout.item_hwj_block, parent, false);
            }
            name = convertView.findViewById(R.id.name);
            phone = convertView.findViewById(R.id.phone);

            final String strName = array.get(position).getBlockedname();
            final String strPhone = array.get(position).getBlocked();

            name.setText(strName);
            phone.setText(strPhone);

            //한사람 차단
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder box = new AlertDialog.Builder(HWJ_BlockActivity.this);
                    box.setTitle("차단");
                    box.setMessage(strName + " 차단하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //개인 차단하기
                            Call<Void> call = rs.insertBlock(array.get(position));
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Toast.makeText(HWJ_BlockActivity.this, strName + " 차단완료", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });
                        }
                    });
                    box.setNegativeButton("아니오", null);
                    box.show();
                }
            });

            //모두차단
            blockAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder box = new AlertDialog.Builder(HWJ_BlockActivity.this);
                    box.setTitle("모두차단");
                    box.setMessage("모두 차단하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i = 0; i < array.size(); i++) {
                                //모두 차단하기
                                Call<Void> call = rs.insertBlock(array.get(i));
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Toast.makeText(HWJ_BlockActivity.this, "차단완료", Toast.LENGTH_SHORT).show();
                                    }
                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }
                        }
                    });
                    box.setNegativeButton("아니오", null);
                    box.show();
                }
            });
            return convertView;
        }
    }

    //뒤로가기 버튼 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}