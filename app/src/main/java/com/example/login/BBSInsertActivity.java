package com.example.login;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;


public class BBSInsertActivity extends AppCompatActivity {
    private static final int CALL_CAMERA = 0, CALL_GALLERY = 1;
    EditText edtTitle, txtContent, edtHashtag;
    ImageView image;
    String strFile;
    File file;
    Uri uriImage;
    Retrofit retrofit;
    RemoteService rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_insert);
        permissionCheck();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        image = findViewById(R.id.imgView);
        edtTitle = findViewById(R.id.edtTitle);
        txtContent = findViewById(R.id.txtContent);
        edtHashtag = findViewById(R.id.edtHashtag);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  // API 24 이상 일경우..
                    uriImage = FileProvider.getUriForFile(BBSInsertActivity.this,
                            getApplicationContext().getPackageName() + ".provider", new File(strFile));
                }
                // API 24 미만 일경우..
                else {
                    uriImage = Uri.fromFile(new File(strFile));
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                startActivityForResult(intent, CALL_CAMERA);
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


    //사진촬영 OR 갤러리에서 사진선택 후 돌아왔을 때
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CALL_CAMERA:
                    image.setImageBitmap(BitmapFactory.decodeFile(strFile));
                    break;
                case CALL_GALLERY:
                    try {
                        image.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
                    } catch (Exception e) {
                    }
                    break;
            }
        }
    }

    //액션바 삽입
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.insert_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.save:
                file = new File(strFile);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), mFile);

                String strHashtag = edtHashtag.getText().toString();
                String tagword[] = strHashtag.split("#");

                BBSimgVO vo = new BBSimgVO();
                vo.setContent(txtContent.getText().toString());
                vo.setTitle(edtTitle.getText().toString());
                vo.setTagword(tagword);
                RemoteService rs = retrofit.create(RemoteService.class);
                Call<Void> call = rs.bbsinsert(vo, fileToUpload);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(BBSInsertActivity.this, "저장되였습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                finish();


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



