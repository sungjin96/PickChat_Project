package com.example.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;


public class JHJ_BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    Intent intent;
    Retrofit retrofit;
    RemoteService rs;


    public static JHJ_BottomSheetDialog getInstance() {
        return new JHJ_BottomSheetDialog();
    }

    ImageView gallery, camera, video, mic, marker;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs=retrofit.create(RemoteService.class);

        View view = inflater.inflate(R.layout.jhj_bottom_sheet_dialog, container, false);
        gallery = view.findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivity(intent);
            }
        });
        camera = view.findViewById(R.id.camera);
        video = view.findViewById(R.id.video);
        mic = view.findViewById(R.id.mic);
        marker = view.findViewById(R.id.marker);

        gallery.setOnClickListener(this);
        camera.setOnClickListener(this);
        video.setOnClickListener(this);
        mic.setOnClickListener(this);
        marker.setOnClickListener(this);
        return view;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.gallery:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);
                break;
            case R.id.mic:
                intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivity(intent);
                break;
            case R.id.marker:
                intent=new Intent(getContext(),JHJ_MapsActivity.class) ;
                Intent intent=new Intent(getContext(), JHJ_MapsActivity.class);
                intent.putExtra("latitude", "37.478896");
                intent.putExtra("longitude","126.878680");
                intent.putExtra("usernickname",getActivity().getIntent().getStringExtra("userNickName"));
                intent.putExtra("img",getActivity().getIntent().getStringExtra("img"));
                startActivity(intent);
                break;
        }
    }

    public void permissionCheck() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }


    }
}



