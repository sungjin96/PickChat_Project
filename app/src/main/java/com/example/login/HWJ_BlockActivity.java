package com.example.login;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_block);

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("내친구차단");

        //퍼미션 체크
        checkAndRequestPermission();

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
            vo.setBlocker("01000020002"); //유저아이디 받는곳
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

    //권한을 확인하고 권한이 부여되어 있지 않다면 권한을 요청하고 요청되어 있으면 인덱스 액티비티로 이동한다.
    private void checkAndRequestPermission() {
        int intGranted = PackageManager.PERMISSION_GRANTED;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != intGranted) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 100);
        } else {
            readContacts();
        }
    }

    // 권한 요청 결과를 보고 모든 권한이 승인되었을 경우에는 메인 액티비티로 이동하고 아니면 현재 액티비티를 종료한다.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isAllGranted = true;
        switch (requestCode) {
            case 100:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        isAllGranted = false;
                    }
                }
                if (isAllGranted) {
                    readContacts();
                } else {
                    Toast.makeText(this, "퍼미션을 얻지 못했습니다!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
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
