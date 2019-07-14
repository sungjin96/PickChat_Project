package com.example.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.login.RemoteService.BASE_URL;


public class BBSReadActivity extends AppCompatActivity {
    CircleImageView soloimg;//프로필 사진
    TextView usernickname;//사용자닉네임
    Retrofit retrofit;
    RemoteService rs;
    BBSimgVO bbsVO;
    List<BBSimgVO> Rarray;
    ImageView readImg, imgContent, readHeart, replySend;
    TextView bbsTitle, bbsContent, readTag, bbsdate;
    RecyclerView contentList;
    BBSContentAdapter adapter;
    TextView pTitel, pUserid, rWriter;
    EditText edtPoliceContent, edtReply;
    int i = 0;
    int y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().setTitle("상세페이지");
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);

        //사용자 정보
        soloimg = findViewById(R.id.soloimg);
        usernickname = findViewById(R.id.usernickname);
        //게시판 내용 필드
        readImg = findViewById(R.id.readImg);
        bbsTitle = findViewById(R.id.bbsTitle);
        readTag = findViewById(R.id.readTag);
        bbsdate = findViewById(R.id.bbsdate);
        contentList = findViewById(R.id.contentList);
        imgContent = findViewById(R.id.imgContent);
        bbsContent = findViewById(R.id.bbsContent);
        readHeart = findViewById(R.id.readHeart);
        replySend = findViewById(R.id.replySend);
        edtReply = findViewById(R.id.edtReply);
        rWriter = findViewById(R.id.rWriter);


        //메인에서 받아온 게시판번호와 이미지패스;
        Intent intent = getIntent();
        final int bno = intent.getExtras().getInt("bno");
        Toast.makeText(BBSReadActivity.this, "BNO" + bno, LENGTH_LONG).show();
        String imgPath = intent.getStringExtra("imgPath");


        /*상세페이지 */
        Call<BBSimgVO> call = rs.bbsRead(bno);
        call.enqueue(new Callback<BBSimgVO>() {
            @Override
            public void onResponse(Call<BBSimgVO> call, Response<BBSimgVO> response) {
                bbsVO = response.body();
//                Call<UserVO> callUser = rs.readUser("01000020002");
                Glide.with(BBSReadActivity.this).load(bbsVO.getImgpath()).into(readImg);
                bbsTitle.setText(bbsVO.getTitle());
                bbsContent.setText(bbsVO.getContent());
                usernickname.setText(bbsVO.getWriter());
                readTag.setText(bbsVO.getTagword().toString());
                //글쓴일자
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년MM월dd일");
                String regDate = simpleDateFormat.format(bbsVO.getRegdate());
                bbsdate.setText(regDate);
            }

            @Override
            public void onFailure(Call<BBSimgVO> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
        /*좋아요 클릭*/
//        readHeart.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                final BBSimgVO vo = new BBSimgVO();
//                vo.setBno(bno);
//                /*로그인한 사람 */
//                vo.setLiker("01000020002");
//                i = 1 - i;
//                if (i != 0) {
//                    Call<Void> calllick = rs.lickInsert(vo);
//                    calllick.enqueue(new Callback<Void>() {
//                        @Override
//                        public void onResponse(Call<Void> call, Response<Void> response) {
//                            Toast.makeText(ReadActivity.this, vo.getLiker() + "좋아요", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<Void> call, Throwable t) {
//                        }
//                    });
//                    readHeart.setImageResource(R.drawable.heart2);
//                } else {
//                    Log.v("번호", "===" + vo.getLiker());
//                    Call<Void> callLD = rs.lickDelete(bno, vo.getLiker());
//                    Toast.makeText(ReadActivity.this, vo.getLiker() + "취소", Toast.LENGTH_SHORT).show();
//                    readHeart.setImageResource(R.drawable.heart);
//                }
//
//            }
//        });
        /*댓글 클릭시 */
//        imgContent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (y == 0) {
//                    Call<List<BBSimgVO>> callReply = rs.replyList(bno);
//                    callReply.enqueue(new Callback<List<BBSimgVO>>() {
//                        @Override
//                        public void onResponse(Call<List<BBSimgVO>> call, Response<List<BBSimgVO>> response) {
////                            Log.v("아아아아아아아ㅏ아", "====" + bno);
//                            Rarray = response.body();
//                            LinearLayoutManager manager = new LinearLayoutManager(ReadActivity.this);
//                            contentList.setLayoutManager(manager);
//
//                            adapter = new BBSContentAdapter(ReadActivity.this, Rarray);
//                            contentList.setAdapter(adapter);
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<BBSimgVO>> call, Throwable t) {
//                        }
//                    });
//                    contentList.setVisibility(View.VISIBLE);
//                    y = -1;
//                } else {
//                    contentList.setVisibility(View.GONE);
//                    y = 0;
//                }
//            }
//        });






        /*댓글 보내기*/
//        replySend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                final BBSReplyVO vo = new BBSReplyVO();
//                vo.setBno(bno);
//                vo.setContent(edtReply.getText().toString());
//                /*로그인한 사람 */
//                vo.setWriter(bbsVO.getLiker());
//                Log.i("댓글", "======" + vo.getContent());
//
//                replySend.setOnKeyListener(new View.OnKeyListener() {
//                    @Override
//                    public boolean onKey(View v, int keyCode, KeyEvent event) {
//                        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                            hidenkeyboard(replySend);
//                            return true;
//                        }
//                        return false;
//                    }
//                    private void hidenkeyboard(ImageView et) {
//                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
//
//                    }
//
//                });
//
//                BBSRemoteService rs = retrofit.create(BBSRemoteService.class);
//                Call<Void> callSend = rs.replyInsert(vo);
//                callSend.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//
//                        Toast.makeText(ReadActivity.this, "보내기완료", Toast.LENGTH_SHORT).show();
//                        edtReply.setText("");
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//
//                    }
//                });
//
//
//            }
//        });



    }//onCreate;

    //서버 메뉴 (신고하기,삭제하기,수정하기기)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.read, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent intent = getIntent();
//        final int bno = intent.getExtras().getInt("bno");
//        switch (item.getItemId()) {
//            case R.id.btnUpdate:
//                Intent UPintent = new Intent(BBSReadActivity.this, BBSUpdateActivity.class);
//                UPintent.putExtra("bno", bno);
//
//                Toast.makeText(BBSReadActivity.this, "수정." + bno, Toast.LENGTH_SHORT).show();
//                startActivity(UPintent);
//                break;
//
//            /*게시글 삭제*/
//            case R.id.btnDelete:
//                AlertDialog.Builder box = new AlertDialog.Builder(BBSReadActivity.this);
//                box.setTitle("알림");
//                box.setMessage("삭제하시겠습니까?");
//                box.setNegativeButton("취소", null);
//                box.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Call<Void> call = rs.bbsDelete(bno);
//                        call.enqueue(new Callback<Void>() {
//                            @Override
//                            public void onResponse(Call<Void> call, Response<Void> response) {
//                                Toast.makeText(BBSReadActivity.this, "삭제되였습니다.", Toast.LENGTH_SHORT).show();
//
//                                finish();
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<Void> call, Throwable t) {
//                            }
//                        });
//                    }
//                });
//                box.show();
//                break;
//            /*신고하기*/
//            case R.id.btnPolice:
////                Toast.makeText(ReadActivity.this, test, Toast.LENGTH_SHORT).show();
//                Context mContext = getApplicationContext();
//                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//                final View view = inflater.inflate(R.layout.bbs_hidden, (ViewGroup) findViewById(R.id.poppup));
//                pTitel = view.findViewById(R.id.pTitel);
//                pUserid = view.findViewById(R.id.pUserid);
//                pTitel.setText(bbsVO.getTitle());
//                pUserid.setText(bbsVO.getWriter());
//                AlertDialog.Builder pbox = new AlertDialog.Builder(BBSReadActivity.this);
//                pbox.setView(view);
//                /*신고스피너 아이템*/
//                Spinner spin = view.findViewById(R.id.spinner);
//                final ArrayAdapter pAdapter = ArrayAdapter.createFromResource(BBSReadActivity.this,
//                        R.array.policeContent, android.R.layout.simple_spinner_item);
//                pAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//                spin.setAdapter(pAdapter);
//                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(BBSReadActivity.this, "선택" + pAdapter.getItem(position), Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//
//                pbox.setNeutralButton("취소", null);
//                pbox.setPositiveButton("신고", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        BBSReplyVO vo = new BBSReplyVO();
//                        edtPoliceContent = view.findViewById(R.id.edtPoliceContent);
//                        vo.setContent(edtPoliceContent.getText().toString());
//                        RemoteService rs = retrofit.create(RemoteService.class);
//                        Call<Void> call = rs.bbsPolice(vo);
//                        call.enqueue(new Callback<Void>() {
//                            @Override
//                            public void onResponse(Call<Void> call, Response<Void> response) {
//                                Toast.makeText(BBSReadActivity.this, "신고되였습니다.", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onFailure(Call<Void> call, Throwable t) {
//
//                            }
//                        });
//                        finish();
//
//
//                    }
//                });
//
//                pbox.show();
//                break;
//
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
}
