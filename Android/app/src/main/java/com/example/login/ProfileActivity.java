package com.example.login;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.example.login.RemoteService.BASE_URL;

public class ProfileActivity extends AppCompatActivity {
    private static final int CALL_CAMERA = 0;
    private static final int CALL_GALLERY = 1;
    ImageView image1, image2;
    Retrofit retrofit;
    String img, code, img1, username, userid;
    Uri uriImage, uriImage2;
    Bitmap bitmap;
    RemoteService rs;
    List<User_listVO> array;
    User_listVO vo;
    int indexSelect = 0;
    int num = 0;
    RadioGroup Radio;
    TextView localcode, passtext, passTestText, userjob, nicknameText, nicknameText1, btncheck1,btncheck2,btncheck3;
    EditText userheight;
    Button insertbtn, btnCheck;
    RadioButton gendercode1, gendercode2;
    EditText userpassword,
            usernickname, userage, usercomment, userpasswordtest;
    boolean GenderisChecked;
    String[] lcode;
    String[] lname;

    String[] hcode;
    String[] hname;

    String[] jcode;
    String[] jname;
    String[] all;

    File file, file1;
    String apiresult;
    boolean facecheck=false;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        vo = new User_listVO();

        //이름, 전화번호(아이디값) 받아오기
        Intent intent = getIntent();
        SharedPreferences sharedPreferences = getSharedPreferences("userid", MODE_PRIVATE);
        userid = sharedPreferences.getString("userid", "");
        username = intent.getStringExtra("username");

        //각각 위치 찾기
        progressbar=findViewById(R.id.progcircle);
        userpassword = findViewById(R.id.userpassword);
        usernickname = findViewById(R.id.usernickname);
        userage = findViewById(R.id.userage);
        userjob = findViewById(R.id.userjob);
        userheight = findViewById(R.id.userheight);
        usercomment = findViewById(R.id.usercomment);
        gendercode1 = findViewById(R.id.gendercode1);
        gendercode2 = findViewById(R.id.gendercode2);
        btnCheck = findViewById(R.id.btnCheck);
        Radio = findViewById(R.id.Radio);
        passtext = findViewById(R.id.passtext);
        userpasswordtest = findViewById(R.id.userpasswordtest);
        passTestText = findViewById(R.id.passTestText);
        nicknameText = findViewById(R.id.nicknameText);
        nicknameText1 = findViewById(R.id.nicknameText1);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        localcode = findViewById(R.id.localcode);

        ////////////////////////////////////서버 접속
        retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        ///////////////////////////////얼굴인식 결과확인
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                Call<ResponseBody> call=rs.faceapi();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            progressbar.setVisibility(View.INVISIBLE);
                            apiresult = response.body().string();
                            //System.out.println("얼굴인식 결과는?????????????????????????" + apiresult);
                        }catch (Exception e){
                            System.out.println("얼굴인식 에러............"+e.toString());
                        }
                        if(apiresult.equals("0")){
                            //성공
                            //Toast.makeText(ProfileActivity.this, "성공", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder box=new AlertDialog.Builder(ProfileActivity.this);
                            box.setTitle("인증 성공 입니다.");
                            box.setNegativeButton("확인",null);
                            box.show();
                            facecheck=true;
                        }else if(apiresult.equals("-1")){
                            //에러
                            //Toast.makeText(ProfileActivity.this, "에러", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder box=new AlertDialog.Builder(ProfileActivity.this);
                            box.setTitle("인물 사진이 아닙니다.");
                            box.setNegativeButton("확인",null);
                            box.show();
                            facecheck=false;
                        }else{
                            //얼굴이 다름
                            //Toast.makeText(ProfileActivity.this, "얼굴이 다름", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder box=new AlertDialog.Builder(ProfileActivity.this);
                            box.setTitle("인증 실패");
                            box.setMessage("본인 사진을 등록해주세요");
                            box.setNegativeButton("확인",null);
                            box.show();
                            facecheck=false;
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println("에러메시지?????????????????"+t.toString());
                    }
                });
            }
        });


        //////////////////////지역 선택
        localcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all = ProfileActivity.this.getResources().getStringArray(R.array.home);
                lcode = new String[all.length];
                lname = new String[all.length];
                for (int i = 0; i < all.length; i++) {
                    String[] g = all[i].split(",");
                    lcode[i] = g[0];
                    lname[i] = g[1];
                }
                new AlertDialog.Builder(ProfileActivity.this)
                        .setTitle("지역을 선택해주세요.")
                        .setSingleChoiceItems(lname, indexSelect,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        indexSelect = which;
                                    }
                                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                TextView text = (TextView) findViewById(R.id.localcode);
                                text.setText("" + lname[indexSelect]);

                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });


        ////////////////////////////////////직업선택
        userjob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] all = ProfileActivity.this.getResources().getStringArray(R.array.job);
                jcode = new String[all.length];
                jname = new String[all.length];
                for (int i = 0; i < all.length; i++) {
                    String[] g = all[i].split(",");
                    jcode[i] = g[0];
                    jname[i] = g[1];
                }
                new AlertDialog.Builder(ProfileActivity.this)
                        .setTitle("직업을 선택해주세요.")
                        .setSingleChoiceItems(jname, indexSelect,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        indexSelect = which;
                                    }
                                })

                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                TextView text = (TextView) findViewById(R.id.userjob);
                                text.setText("" + jname[indexSelect]);

                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });

        /////////////////////////////닉네임중복체크
        usernickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Call<Integer> call = rs.checknickname(usernickname.getText().toString());
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Integer check;
                        if (response.body() == null) {
                            check = -1;
                            nicknameText1.setVisibility(View.GONE);

                            return;
                        } else {
                            check = Integer.parseInt(response.body().toString());
                        }
                        if (check == 0) {
                            nicknameText1.setVisibility(View.VISIBLE);
                            nicknameText.setVisibility(View.GONE);
                        } else {
                            nicknameText1.setVisibility(View.GONE);
                            nicknameText.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                    }
                });

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        int degree = getExifOrientation(img);
        bitmap = BitmapFactory.decodeFile(img);
        bitmap = getRotatedBitmap(bitmap, degree);
        permissionCheck();

        /////////////////////////////////////라디오 버튼 남여
        Radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                GenderisChecked = checkedRadioButton.isChecked();
                if (checkedRadioButton == group.findViewById(R.id.gendercode1)) {
                    vo.setGendercode(0);
                } else {
                    vo.setGendercode(1);
                }

            }
        });

        /////////////////////////////////////////비밀번호 체크
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 4) {
                    passtext.setVisibility(View.VISIBLE);
                } else {
                    passtext.setVisibility(View.GONE);
                }
            }
        });

        ///////////////////////////////////////비밀번호재입력 체크
        userpasswordtest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userpassword.getText().toString().equals(userpasswordtest.getText().toString())) {
                    passTestText.setVisibility(View.GONE);
                } else {
                    passTestText.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        //////////////////////////////////////정보 입력 후 저장
        insertbtn = findViewById(R.id.insertbtn);
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strUserpassword = userpassword.getText().toString();
                final String strUsernickname = usernickname.getText().toString();
                final int strUserage = Integer.parseInt(userage.getText().toString());
                final int strUserheight = Integer.parseInt(userheight.getText().toString());
                final String strUserjob = userjob.getText().toString();
                final String strUsercomment = usercomment.getText().toString();

                for (int i = 0; i < all.length; i++) {
                    String name = localcode.getText().toString();
                    if (lname[i].equals(name)) {
                        code = lcode[i];
                    }
                }

                final String strLocalcode = code;
                vo.setUserpassword(strUserpassword);
                vo.setUsernickname(strUsernickname);
                vo.setUserage(strUserage);
                vo.setUserjob(strUserjob);
                vo.setUserheight(strUserheight);
                vo.setUsercomment(strUsercomment);
                vo.setLocalcode(strLocalcode);
                vo.setUserid(userid);
                vo.setUsername(username);
                //System.out.println("회원가입 정보.................."+vo.toString());
                ////////////////////////////////////////////////////회원 정보 입력(회원가입)
                /////////////////////빈 칸 이거나 사진 인증이 실패하면 가입 못 함
                if(facecheck) {
                    Call<Void> call = rs.userinsert(vo);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Intent intent = new Intent(ProfileActivity.this, DetailsActivity.class);
                            startActivity(intent);
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("오류", t.getMessage());
                        }
                    });
                }else {
                    AlertDialog.Builder box=new AlertDialog.Builder(ProfileActivity.this);
                    box.setTitle("가입 실패");
                    box.setMessage("본인 인증을 완료해 주세요");
                    box.show();
                }
            }


        });
    }

    /////////////////////////////////////////////사진촬영
    public void mClick(View view) {
        switch (view.getId()) {
            case R.id.image1:
                AlertDialog.Builder box = new AlertDialog.Builder(ProfileActivity.this);
                box.setTitle("실물 인증용 사진입니다.");
                box.setPositiveButton("사진촬영", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        num = 0;
                        img = Environment.getExternalStorageDirectory().getAbsolutePath() + "/checkimg.png";
                        file = new File(img);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  // API 24 이상 일경우..
                            uriImage = FileProvider.getUriForFile(ProfileActivity.this,
                                    getApplicationContext().getPackageName() + ".provider", file);
                        }
                        // API 24 미만 일경우..
                        else {
                            uriImage = Uri.fromFile(file);
                        }
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                        startActivityForResult(intent, CALL_CAMERA);
                    }
                });
                box.show();
                break;
            case R.id.image2:
                box = new AlertDialog.Builder(ProfileActivity.this);
                num = 1;
                box.setTitle("프로필용 사진입니다.");
                box.setMessage("이미지 선택 방법을 결정하세요");
                box.setPositiveButton("사진촬영", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        img1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/attachimage3.png";
                        file1 = new File(img1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  // API 24 이상 일경우..
                            uriImage2 = FileProvider.getUriForFile(ProfileActivity.this,
                                    getApplicationContext().getPackageName() + ".provider", file1);
                        }
                        // API 24 미만 일경우..
                        else {
                            uriImage2 = Uri.fromFile(file1);
                        }
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage2);
                        startActivityForResult(intent, CALL_CAMERA);
                    }
                });
                box.setNeutralButton("앨범선택", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        img1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/attachimage2.png";
                        file1 = new File(img1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  // API 24 이상 일경우..
                            uriImage2 = FileProvider.getUriForFile(ProfileActivity.this,
                                    getApplicationContext().getPackageName() + ".provider", file1);
                        }
                        // API 24 미만 일경우..
                        else {
                            uriImage2 = Uri.fromFile(file1);
                        }
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage2);
                        startActivityForResult(intent, CALL_GALLERY);
                    }
                });
                box.show();
                break;
        }

    }

//////////////////////////////사진 붙일 때 돌아가지 않게하기
    private int getExifOrientation(String file) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        return 90;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        return 180;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        return 270;
                }
            }
        }

        return 0;
    }
    private Bitmap getRotatedBitmap(Bitmap bitmap, int degree) {
        if (degree != 0 && bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.setRotate(degree, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
            try {
                Bitmap tmpBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                if (bitmap != tmpBitmap) {
                    bitmap.recycle();
                    bitmap = tmpBitmap;
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }
    ///////////////////////////////////////////////////////화면에 사진 출력&서버에 사진 업로드시키기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CALL_CAMERA:
                    if (num == 0) {
                        image1.setImageBitmap(getRotatedBitmap(BitmapFactory.decodeFile(img), getExifOrientation(img)));
                    } else {
                        image2.setImageBitmap(getRotatedBitmap(BitmapFactory.decodeFile(img1), getExifOrientation(img1)));
                    }
                    final RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                    final MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("uploadfile", file.getName(), mFile);
                    //인증용 사진 업로드
                    final Call<ResponseBody> image = rs.uploadImg(fileToUpload);
                    image.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String result = response.body().string();
                                String[] arr = {result};
                                vo.setImgpath(arr);
                                //System.out.println("사진 경로는???????/from 카메라"+arr+"///"+vo.getImgpath()[0]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("error", t.getMessage());
                        }
                    });
                    break;
                case CALL_GALLERY:
                    try {
                        String[] projection = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);
                        cursor.moveToFirst();
                        img1 = cursor.getString(cursor.getColumnIndex(projection[0]));
                        cursor.close();
                        Bitmap bit =getRotatedBitmap(BitmapFactory.decodeFile(img1), getExifOrientation(img1));
                        image2.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
                        image2.setImageBitmap(getRotatedBitmap(BitmapFactory.decodeFile(img1), getExifOrientation(img1)));
                        image2.setImageBitmap(bit);
                        file1=new File(img1);
                        final RequestBody mFile1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        final MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("uploadfile", file1.getName(), mFile1);
                        //프로필용 사진 업로드
                        final Call<ResponseBody> image2 = rs.uploadImg(fileToUpload1);
                        image2.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String result = response.body().string();
                                    String[] arr = {result};
                                    vo.setImgpath(arr);
                                    //System.out.println("사진 경로는???????/from 갤러리"+arr+"///"+arr[0]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d("error", t.getMessage());
                            }
                        });
                    } catch (Exception e) {
                    }

                    break;
            }
        }
    }



////////////////////퍼미션체크
    public void permissionCheck() {
        if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }
}