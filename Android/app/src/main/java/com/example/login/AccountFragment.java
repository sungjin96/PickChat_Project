package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.login.RemoteService.BASE_URL;

public class AccountFragment extends Fragment {
    Retrofit retrofit;
    RemoteService rs;
    UserProfileVO vo;
    Bundle bundle;
    ImageView soloimg, question, friend_block, notice, terms_of_service, account, faq;
    TextView usernickname, userid, username, userjob, userage, localname, userpoint;
    TextView profile, charge, read_contact;
    Intent intent;
    String strUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_account,container,false);

        //상태바 아이콘색상변경
        getActivity().getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (root != null) {
                // 23 버전 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정
                root.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getActivity().getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));
            }
        }else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getActivity().getWindow().setStatusBarColor(Color.BLACK);
        }

        //아이디값 찾기
        usernickname = root.findViewById(R.id.usernickname);
        userid = root.findViewById(R.id.userid);
        username = root.findViewById(R.id.username);
        userjob = root.findViewById(R.id.userjob);
        userage = root.findViewById(R.id.userage);
        localname = root.findViewById(R.id.localname);
        soloimg = root.findViewById(R.id.soloimg);
        userpoint = root.findViewById(R.id.userpoint);

        //번들로vo받을 때
        bundle = getArguments();
       // UserProfileVO bundlevo = bundle.getParcelable("vo");

        //로그인부터 유저값 받아오기
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("userid",MODE_PRIVATE);
        strUser = sharedPreferences.getString("userid","");

        //사용자 프로필 사진 동그랗게
//        soloimg.setBackground(new ShapeDrawable(new OvalShape()));
//        soloimg.setClipToOutline(true);

        //서버 연결
        vo = new UserProfileVO();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        //"프로필" 버튼
        profile = root.findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), HWJ_ProfileActivity.class);
                startActivity(intent);
            }
        });

        //"충전" 버튼
        charge = root.findViewById(R.id.charge);
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HWJ_BottomSheetDialog bottomSheetDialog = HWJ_BottomSheetDialog.getInstance(vo.getUserpoint());
                bottomSheetDialog.show(getFragmentManager(), "bottomSheet");
            }
        });

        //"공유하기" 버튼
        read_contact = root.findViewById(R.id.read_contact);
        read_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), HWJ_ShareActivity.class);
                startActivity(intent);
            }
        });

        //"공지사항" 버튼
        notice = root.findViewById(R.id.notice);
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), HWJ_NoticeActivity.class);
                startActivity(intent);
            }
        });

        //"이용약관" 버튼
        terms_of_service = root.findViewById(R.id.terms_of_service);
        terms_of_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), HWJ_TOSActivity.class);
                startActivity(intent);
            }
        });

        //"내친구차단" 버튼
        friend_block = root.findViewById(R.id.friend_block);
        friend_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), HWJ_BlockActivity.class);
                startActivity(intent);
            }
        });

        //"문의내역" 버튼
        question = root.findViewById(R.id.question);
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), HWJ_QuestionListActivity.class);
                startActivity(intent);
            }
        });

        //"FAQ" 버튼
        faq = root.findViewById(R.id.faq);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), HWJ_FAQActivity.class);
                startActivity(intent);
            }
        });

        //"계정설정" 버튼
        account = root.findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), HWJ_AccountActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<UserProfileVO> call = rs.listProfile(strUser); //아이디 값 받아오는 구간
        call.enqueue(new Callback<UserProfileVO>() {
            @Override
            public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                vo = response.body();
                //이름 적용
                usernickname.setText(vo.getUsernickname().toString());
                userid.setText(vo.getUserid().toString());
                username.setText(vo.getUsername().toString());
                userjob.setText(vo.getUserjob().toString());
                userage.setText(vo.getUserage().toString());
                localname.setText(vo.getLocalname().toString());
                userpoint.setText(vo.getUserpoint() + "p");

                //이미지 적용
                Picasso.with(getContext())
                        .load(vo.getSoloimg())
                        .into(soloimg);
            }

            @Override
            public void onFailure(Call<UserProfileVO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}