package com.example.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class BBSInsertActivity extends AppCompatActivity {
    private static final int CALL_CAMERA = 0, CALL_GALLERY = 1;
    EditText edtTitle, edtContent, edtHashtag;
    ImageView image;
    String strFile;
    File file;
    Uri uriImage;
    Retrofit retrofit;
    RemoteService rs;
    BBSVO vo;
    BBSImageAdapter imageAdapter;
    ArrayList<BBSVO> arraylist;
    TextView btnsave;
    String userid,usernickname;
    UserProfileVO uservo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbsinsert);

        arraylist = new ArrayList<BBSVO>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_24dp);


        //아이디 값 찾기
        image = findViewById(R.id.imgView);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        edtHashtag = findViewById(R.id.edtHashtag);
        btnsave=findViewById(R.id.btnsave);
        imageAdapter = new BBSImageAdapter(this, arraylist);

        //서버 연결
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

        //로그인 한 유저 정보가져오기
        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");

        //유저 정보 불러오기
        Call<UserProfileVO> call=rs.listProfile(userid);
        call.enqueue(new Callback<UserProfileVO>() {
            @Override
            public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                uservo=response.body();
                usernickname=uservo.getUsernickname();
            }

            @Override
            public void onFailure(Call<UserProfileVO> call, Throwable t) {

            }
        });

        //저장하기
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    file = new File(strFile);
                    RequestBody mFile = RequestBody.create(MediaType.parse("file/*"), file);
                    MultipartBody.Part uploadfile = MultipartBody.Part.createFormData("uploadfile", file.getName(), mFile);
                    //사진파일업로드
                    Call<ResponseBody> image = rs.bbsimg(uploadfile);
                    image.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String location = "";
                            try {
                                location = response.body().string();
                                Log.d("최종선택한 이미지", "++++" + location.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String strHashtag = edtHashtag.getText().toString();
                            String[] tagword = strHashtag.split("#");
                            vo = new BBSVO();
                            vo.setWriter(usernickname);
                            vo.setContent(edtContent.getText().toString());
                            vo.setTitle(edtTitle.getText().toString());
                            vo.setImgpath(location);
                            vo.setTagword(tagword);
                            Call<Void> call2 = rs.bbsinsert(vo);
                            call2.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {

                                    arraylist.add(vo);
                                    imageAdapter.notifyDataSetChanged();
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            if (t instanceof IOException) {
                                t.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    AlertDialog.Builder pbox = new AlertDialog.Builder(BBSInsertActivity.this);
                    pbox.setTitle("알림");
                    pbox.setMessage("사진을 저장하세요");
                    pbox.setPositiveButton("확인", null);
                    pbox.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    pbox.show();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ImageView img = findViewById(R.id.imgView);
        Log.w("Layout Width - ", String.valueOf(img.getWidth()));
        Log.w("Layout Height - ", String.valueOf(img.getHeight()));
    }

    //사진선택 방법
    public void mClick(View v) {
        AlertDialog.Builder box = new AlertDialog.Builder(this);
        box.setTitle("이미지 선택 방법을 결정하세요!");
        box.setPositiveButton("사진촬영", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                strFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/image.jpg";
                //디비에 사진 올리기위해 file객체 생성함
                file = new File(strFile);
                Log.d("이미지이름", "++++++" + strFile.toString());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  // API 24 이상 일경우..
                    uriImage = FileProvider.getUriForFile(BBSInsertActivity.this,
                            getApplicationContext().getPackageName() + ".provider", file);
                }
                // API 24 미만 일경우..
                else {
                    uriImage = Uri.fromFile(new File(strFile));
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                startActivityForResult(intent, CALL_CAMERA);
                Log.v("사진찍기 선택", "+++++++++" + strFile);
            }
        });
        box.setNeutralButton("앨범선택", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CALL_GALLERY);
            }
        });
        box.show();
    }

    //액션바 삽입
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bbs_insert_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save:

        }
        return super.onOptionsItemSelected(item);
    }

    //사진촬영 OR 갤러리에서 사진선택 후 돌아왔을 때 파일형식을 비트맵으로 바꿔서 이미지뷰에 저정하기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CALL_CAMERA:
                    image.setImageBitmap(BitmapFactory.decodeFile(strFile));
                    break;
                case CALL_GALLERY:
                    try {
                        String[] projection = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);
                        cursor.moveToFirst();
                        strFile = cursor.getString(cursor.getColumnIndex(projection[0]));
                        cursor.close();
                        image.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
                        Log.d("사진선택 이미지", "+++++++" + data.getData());
                    } catch (Exception e) {
                        AlertDialog.Builder pbox = new AlertDialog.Builder(this);
                        pbox.setTitle("알림");
                        pbox.setMessage("사진을 불러올수없습니다.");
                        pbox.setPositiveButton("확인", null);
                        pbox.show();
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}



