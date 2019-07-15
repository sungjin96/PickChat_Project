package com.example.login;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HWJ_FAQActivity extends AppCompatActivity {
    RelativeLayout question, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    TextView content1, content2, content3, content4, content5, content6, content7, content8, content9;
    ImageView btnView1, btnView2, btnView3, btnView4, btnView5, btnView6, btnView7, btnView8, btnView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_faq);

        //RelativeLayout
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        //상세 내용
        content1 = findViewById(R.id.content1);
        content2 = findViewById(R.id.content2);
        content3 = findViewById(R.id.content3);
        content4 = findViewById(R.id.content4);
        content5 = findViewById(R.id.content5);
        content6 = findViewById(R.id.content6);
        content7 = findViewById(R.id.content7);
        content8 = findViewById(R.id.content8);
        content9 = findViewById(R.id.content9);

        //화살표 이미지
        btnView1 = findViewById(R.id.btnView1);
        btnView2 = findViewById(R.id.btnView2);
        btnView3 = findViewById(R.id.btnView3);
        btnView4 = findViewById(R.id.btnView4);
        btnView5 = findViewById(R.id.btnView5);
        btnView6 = findViewById(R.id.btnView6);
        btnView7 = findViewById(R.id.btnView7);
        btnView8 = findViewById(R.id.btnView8);
        btnView9 = findViewById(R.id.btnView9);

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("FAQ");

        //FAQ 목록 눌렀을 때 설정
        //1번
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content1.getVisibility() != View.VISIBLE) {
                    content1.setVisibility(View.VISIBLE);
                    btnView1.setImageResource(R.drawable.icon_top);
                } else {
                    content1.setVisibility(View.GONE);
                    btnView1.setImageResource(R.drawable.icon_down);
                }
            }
        });

        //2번
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content2.getVisibility() != View.VISIBLE) {
                    content2.setVisibility(View.VISIBLE);
                    btnView2.setImageResource(R.drawable.icon_top);
                } else {
                    content2.setVisibility(View.GONE);
                    btnView2.setImageResource(R.drawable.icon_down);
                }
            }
        });

        //3번
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content3.getVisibility() != View.VISIBLE) {
                    content3.setVisibility(View.VISIBLE);
                    btnView3.setImageResource(R.drawable.icon_top);
                } else {
                    content3.setVisibility(View.GONE);
                    btnView3.setImageResource(R.drawable.icon_down);
                }
            }
        });

        //4번
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content4.getVisibility() != View.VISIBLE) {
                    content4.setVisibility(View.VISIBLE);
                    btnView4.setImageResource(R.drawable.icon_top);
                } else {
                    content4.setVisibility(View.GONE);
                    btnView4.setImageResource(R.drawable.icon_down);
                }
            }
        });

        //5번
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content5.getVisibility() != View.VISIBLE) {
                    content5.setVisibility(View.VISIBLE);
                    btnView5.setImageResource(R.drawable.icon_top);
                } else {
                    content5.setVisibility(View.GONE);
                    btnView5.setImageResource(R.drawable.icon_down);
                }
            }
        });

        //6번
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content6.getVisibility() != View.VISIBLE) {
                    content6.setVisibility(View.VISIBLE);
                    btnView6.setImageResource(R.drawable.icon_top);
                } else {
                    content6.setVisibility(View.GONE);
                    btnView6.setImageResource(R.drawable.icon_down);
                }
            }
        });

        //7번
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content7.getVisibility() != View.VISIBLE) {
                    content7.setVisibility(View.VISIBLE);
                    btnView7.setImageResource(R.drawable.icon_top);
                } else {
                    content7.setVisibility(View.GONE);
                    btnView7.setImageResource(R.drawable.icon_down);
                }
            }
        });

        //8번
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content8.getVisibility() != View.VISIBLE) {
                    content8.setVisibility(View.VISIBLE);
                    btnView8.setImageResource(R.drawable.icon_top);
                } else {
                    content8.setVisibility(View.GONE);
                    btnView8.setImageResource(R.drawable.icon_down);
                }
            }
        });

        //9번
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content9.getVisibility() != View.VISIBLE) {
                    content9.setVisibility(View.VISIBLE);
                    btnView9.setImageResource(R.drawable.icon_top);
                } else {
                    content9.setVisibility(View.GONE);
                    btnView9.setImageResource(R.drawable.icon_down);
                }
            }
        });


        question = findViewById(R.id.question);
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //문의하기
                shareIntentSpecificApps();
            }
        });
    }

    //문의하기 버튼
    public void shareIntentSpecificApps() {
        List<Intent> targetShareIntents = new ArrayList<Intent>();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        PackageManager pm = HWJ_FAQActivity.this.getPackageManager();
        List<ResolveInfo> resInfos = pm.queryIntentActivities(shareIntent, 0);
        if (!resInfos.isEmpty()) {
            for (ResolveInfo resInfo : resInfos) {
                String packageName = resInfo.activityInfo.packageName;
                Log.i("Package Name", packageName);

                if (packageName.contains("com.google.android.gm") ||
                        packageName.contains("com.samsung.android.messaging") ||
                        packageName.contains("com.samsung.android.email.provider")) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
                    intent.putExtra("AppName", resInfo.loadLabel(pm).toString());
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");

                    //문의하기 멘트
                    intent.setData(Uri.parse("pickchat_help@gmail.com")); //개발자 이메일 입력 부분
                    intent.putExtra(Intent.EXTRA_SUBJECT, "[문의] 고객센터에 문의합니다");
                    intent.putExtra(Intent.EXTRA_TEXT, "내용:\n\n\n------------------------------"
                            + "\n회원님의 빠른 문제 해결을 위해 관련 등록정보를 함께 발송합니다. 해당 정보는 선택사항입니다\n"
                            + "\nOS구분 : Android " + Build.VERSION.RELEASE + " | " + Build.VERSION.SDK_INT + "\n모델명 : " + Build.MODEL
                            + "\nUUID : " + Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID) + "\n------------------------------");
                    intent.setPackage(packageName);
                    targetShareIntents.add(intent);
                }
            }
            if (!targetShareIntents.isEmpty()) {
                Collections.sort(targetShareIntents, new Comparator<Intent>() {
                    @Override
                    public int compare(Intent o1, Intent o2) {
                        return o1.getStringExtra("AppName").compareTo(o2.getStringExtra("AppName"));
                    }
                });
                Intent chooserIntent = Intent.createChooser(targetShareIntents.remove(0), "개발자에게 문의하기");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
                startActivity(chooserIntent);
            } else {
                Toast.makeText(HWJ_FAQActivity.this, "No app to share.", Toast.LENGTH_LONG).show();
            }
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
