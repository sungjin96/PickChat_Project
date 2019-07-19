package com.example.login;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.login.RemoteService.BASE_URL;

public class BBSUpdateActivity extends AppCompatActivity {
    private static final int CALL_CAMERA = 0, CALL_GALLERY = 1;
    TextView updateTitel, updateContent;
    ImageView updateImg;
    String strFile;
    File file;
    Uri uriImage;
    Retrofit retrofit;
    RemoteService rs;
    List<BBSVO> arraylist;
    BBSImageAdapter imageAdapter;
    BBSVO vo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbsupdate);
        permissionCheck();

        updateImg = findViewById(R.id.updateImg);
        updateTitel = findViewById(R.id.updateTitel);
        updateContent = findViewById(R.id.updateContent);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

        final Intent intent = getIntent();
        int bno = intent.getExtras().getInt("bno");
        Toast.makeText(BBSUpdateActivity.this, "BNO" + bno, LENGTH_LONG).show();
        Call<BBSVO> bbsUpRead = rs.bbsUpRead(bno);
        bbsUpRead.enqueue(new Callback<BBSVO>() {
            @Override
            public void onResponse(Call<BBSVO> call, Response<BBSVO> response) {
                BBSVO vo = response.body();
                updateTitel.setText(vo.getTitle());
                updateContent.setText(vo.getContent());
                Glide.with(BBSUpdateActivity.this).load(vo.getImgpath()).into(updateImg);
            }

            @Override
            public void onFailure(Call<BBSVO> call, Throwable t) {
            }
        });

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
                    uriImage = FileProvider.getUriForFile(BBSUpdateActivity.this,
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

    //사진촬영 OR 갤러리에서 사진선택 후 돌아왔을 때 파일형식을 비트맵으로 바꿔서 이미지뷰에 저정하기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CALL_CAMERA:
                    updateImg.setImageBitmap(BitmapFactory.decodeFile(strFile));
                    break;
                case CALL_GALLERY:
                    try {
                        String[] projection = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);
                        cursor.moveToFirst();
                        strFile = cursor.getString(cursor.getColumnIndex(projection[0]));
                        cursor.close();
                        updateImg.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
                        Log.d("사진선택 이미지", "+++++++" + data.getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    //액션바 삽입
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bbs_insert_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        final int bno = intent.getExtras().getInt("bno");
        switch (item.getItemId()) {
            case R.id.save:
                file = new File(strFile);
                RequestBody mFile = RequestBody.create(MediaType.parse("file/*"), file);
                MultipartBody.Part uploadfile = MultipartBody.Part.createFormData("uploadfile", file.getName(), mFile);
                Call<ResponseBody> image = rs.bbsimg(uploadfile);

                image.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String location = "";
                        try {
                            location = response.body().string();
                            Log.d("최종선택한 이미지", "++++" + location.toString());
                        } catch (Exception e) {
                            AlertDialog.Builder pbox = new AlertDialog.Builder(BBSUpdateActivity.this);
                            pbox.setTitle("알림");
                            pbox.setMessage("사진파일 형식을 확인해주세요");
                            pbox.setPositiveButton("확인", null);
                            pbox.show();

                            e.printStackTrace();
                        }
                        vo = new BBSVO();
                        vo.setWriter("원빈");
                        vo.setContent(updateContent.getText().toString());
                        vo.setTitle(updateTitel.getText().toString());
                        vo.setImgpath(location);
                        vo.setBno(bno);
                        Call<Void> bbsUpdate = rs.bbsUpdate(vo);
                        bbsUpdate.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                                Toast.makeText(BBSUpdateActivity.this, "수정완료", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void permissionCheck() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

}
