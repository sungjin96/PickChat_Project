package com.example.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.Log;
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

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
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
    BBSVO bbsVO;
    ArrayList<BBSVO> arraylist;
    List<BBSReplyVO> Rarray;
    ImageView readImg, imgContent, readHeart, replySend;
    TextView bbsTitle, bbsContent, readTag, bbsdate;
    RecyclerView contentList;
    BBSContentAdapter adapter;
    BBSImageAdapter imageAdapter;
    BBSPoliceVO pvo;


    TextView pTitel, pUserid, rWriter;
    EditText edtPoliceContent, edtReply;
    int l, i = 0;
    int y = 0;

    String[] tagword;
    private InputMethodManager imm;
    int p;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbsread);
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

//로그인 한 사용자 정보
        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");

        //메인에서 받아온 게시판번호;
        Intent intent = getIntent();
        final int bno = intent.getExtras().getInt("bno");
        /*좋아요 한사람 체크*/
        Call<Integer> lickRead = rs.likeRead(bno, userid);
        lickRead.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    int result=response.body();
                    System.out.println("결과??????"+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

        /*상세페이지 */
        Call<BBSVO> call = rs.bbsRead(bno);
        call.enqueue(new Callback<BBSVO>() {
            @Override
            public void onResponse(Call<BBSVO> call, Response<BBSVO> response) {
                bbsVO = response.body();
//                if (l == 0) {
//                    readHeart.setImageResource(R.drawable.heart2);
//                } else {
//                    readHeart.setImageResource(R.drawable.heart);
//                }

                Picasso.with(BBSReadActivity.this).load(bbsVO.getSoloimg()).into(soloimg);
                Picasso.with(BBSReadActivity.this).load(bbsVO.getImgpath()).resize(readImg.getHeight(), readImg.getWidth() + 10).into(readImg);
                //글쓴일자
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년MM월dd일");
                String regDate = simpleDateFormat.format(bbsVO.getRegdate());
                bbsdate.setText(regDate);
                bbsTitle.setText(bbsVO.getTitle());
                bbsContent.setText(bbsVO.getContent());
                usernickname.setText(bbsVO.getWriter());

                tagword = bbsVO.getTagword();
                Log.i("ddddddddd", "ddddd" + tagword);
                String tag = "";
                int t;
                for (t = 0; t < bbsVO.getTagword().length; t++) {
                    tag += "#" + tagword[t] + " ";
                }
//                Log.v("태그", "========" + tagword[1]);
                ArrayList<int[]> hashtagSpans = (ArrayList<int[]>) getSpans(tag, '#');
                SpannableString tagsContent = new SpannableString(tag);
                for (t = 0; t < hashtagSpans.size(); t++) {
                    int[] span = hashtagSpans.get(t);
                    int hashTagStart = span[0];
                    int hashTagEnd = span[1];
                    BBSHashtag hashtag = new BBSHashtag(this);
                    hashtag.setOnClickEventListenner(new BBSHashtag.ClickEventListener() {
                        @Override
                        public void onClickEvent(String data) {

                        }
                    });
                    tagsContent.setSpan(hashtag, hashTagStart, hashTagEnd, 0);
                }

                if (readTag != null) {
                    readTag.setMovementMethod(LinkMovementMethod.getInstance());
                    readTag.setText(tagsContent);
                }


            }

            @Override
            public void onFailure(Call<BBSVO> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }

            public ArrayList<int[]> getSpans(String body, char prefix) {
                ArrayList<int[]> spans = new ArrayList<>();

                Pattern pattern = Pattern.compile(prefix + "\\w+");
                Matcher matcher = pattern.matcher(body);

                while (matcher.find()) {
                    int[] currentSpan = new int[2];
                    currentSpan[0] = matcher.start();
                    currentSpan[1] = matcher.end();
                    spans.add(currentSpan);
                }
                return spans;
            }
        });
        /*좋아요 클릭*/
        readHeart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final BBSVO vo = new BBSVO();
                vo.setBno(bno);
                /*로그인한 사람 */
                vo.setLiker(userid);
                i = 1 - i;
                if (i != 0) {
                    Call<Void> calllick = rs.lickInsert(vo);
                    calllick.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(BBSReadActivity.this, vo.getLiker() + "좋아요", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                        }
                    });
                    readHeart.setImageResource(R.drawable.heart2);
                } else {
//                    Log.v("번호", "===" + vo.getLiker());
                    Call<Void> callLD = rs.likeDelete(bno, vo.getLiker());
                    callLD.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(BBSReadActivity.this, vo.getLiker() + "취소", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                        }
                    });
                    readHeart.setImageResource(R.drawable.heart);
                }
            }
        });
        /*댓글 클릭시 */
        imgContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (y == 0) {
                    Call<List<BBSReplyVO>> callReply = rs.replyList(bno);
                    callReply.enqueue(new Callback<List<BBSReplyVO>>() {
                        @Override
                        public void onResponse(Call<List<BBSReplyVO>> call, Response<List<BBSReplyVO>> response) {
                            Rarray = response.body();
                            LinearLayoutManager manager = new LinearLayoutManager(BBSReadActivity.this);
                            contentList.setLayoutManager(manager);
                            adapter = new BBSContentAdapter(BBSReadActivity.this, Rarray);
                            if (Rarray.size() == 0) {
                                AlertDialog.Builder box = new AlertDialog.Builder(BBSReadActivity.this);
                                box.setTitle("알림");
                                box.setMessage("댓글이없습니다.");
                                box.setNegativeButton("확인", null);
                                box.show();
                            }

                            contentList.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<BBSReplyVO>> call, Throwable t) {
                        }
                    });
                    contentList.setVisibility(View.VISIBLE);
                    y = -1;
                } else {
                    contentList.setVisibility(View.GONE);
                    y = 0;
                }

            }
        });

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        /*댓글 보내기*/
        replySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final BBSReplyVO vo = new BBSReplyVO();
                vo.setBno(bno);
                vo.setContent(edtReply.getText().toString());
                vo.setWriter("아이유");
                Log.i("댓글", "======" + vo.getContent());
                RemoteService rs = retrofit.create(RemoteService.class);
                Call<Void> callSend = rs.replyInsert(vo);
                callSend.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(BBSReadActivity.this, "보내기완료", Toast.LENGTH_SHORT).show();
                        edtReply.setText("");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
                imm.hideSoftInputFromWindow(edtReply.getWindowToken(), 0);

            }

        });


    }//onCreate;

    //서버 메뉴 (신고하기,게시글삭제하기,수정하기기)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bbs_read, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        final int bno = intent.getExtras().getInt("bno");
        switch (item.getItemId()) {
            /*수정하기*/
            case R.id.btnUpdate:
                Intent UPintent = new Intent(BBSReadActivity.this, BBSUpdateActivity.class);
                UPintent.putExtra("bno", bno);
                Toast.makeText(BBSReadActivity.this, "수정." + bno, Toast.LENGTH_SHORT).show();
                startActivity(UPintent);
                break;

            /*게시글 삭제*/
            case R.id.btnDelete:
                AlertDialog.Builder box = new AlertDialog.Builder(BBSReadActivity.this);
                box.setTitle("알림");
                box.setMessage("삭제하시겠습니까?");
                box.setNegativeButton("취소", null);
                box.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<Void> call = rs.bbsDelete(bno);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(BBSReadActivity.this, "삭제되였습니다..", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(BBSReadActivity.this, "삭제를 할수없습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                box.show();
                break;
            /*신고하기*/
            case R.id.btnPolice:
//                Toast.makeText(ReadActivity.this, test, Toast.LENGTH_SHORT).show();
                Context mContext = getApplicationContext();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View view = inflater.inflate(R.layout.bbs_police_hidden, (ViewGroup) findViewById(R.id.poppup));
                pTitel = view.findViewById(R.id.pTitel);
                pUserid = view.findViewById(R.id.pUserid);
                pTitel.setText(bbsVO.getTitle());
                pUserid.setText(bbsVO.getWriter());
                AlertDialog.Builder pbox = new AlertDialog.Builder(BBSReadActivity.this);
                pbox.setView(view);
                /*신고스피너 아이템*/
                Spinner spin = view.findViewById(R.id.spinner);
                final ArrayAdapter pAdapter = ArrayAdapter.createFromResource(BBSReadActivity.this,
                        R.array.policeContent, android.R.layout.simple_spinner_item);
                pAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spin.setAdapter(pAdapter);
                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(BBSReadActivity.this, "" + pAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();

                        if (pAdapter.getItem(position).toString().equals("욕설 및 비방")) {
                            p = 1;
                        } else if (pAdapter.getItem(position).toString().equals("부적절한 사진")) {
                            p = 2;
                        } else if (pAdapter.getItem(position).toString().equals("허위 내용")) {
                            p = 3;
                        } else if (pAdapter.getItem(position).toString().equals("스팸 및 광고")) {
                            p = 4;
                        } else if (pAdapter.getItem(position).toString().equals("기타")) {
                            p = 5;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                pbox.setNeutralButton("취소", null);
                pbox.setPositiveButton("신고", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pvo = new BBSPoliceVO();
                        edtPoliceContent = view.findViewById(R.id.edtPoliceContent);
                        pvo.setBno(bno);
                        pvo.setSender(userid);
                        pvo.setPcontent(edtPoliceContent.getText().toString());
                        System.out.println(edtPoliceContent.getText().toString());
                        pvo.setReasonid(p);
                        Log.d("신고", "======" + pvo.getReasonid());
                        rs = retrofit.create(RemoteService.class);
                        Call<Void> call = rs.bbsPolice(pvo);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(BBSReadActivity.this, "신고되였습니다.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                            }
                        });
                        finish();
                    }
                });
                pbox.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
