package food.com.example.projectchatting;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
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
import android.widget.LinearLayout;
import android.widget.Toast;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    Intent intent;

    public static BottomSheetDialog getInstance() {
        return new BottomSheetDialog();
    }

    ImageView gallery, camera, video, mic, marker;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
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
               // intent=new Intent(getContext(),MapsActivity.class) ;
                Intent intent=new Intent(getContext(), MapsActivity.class);
                intent.putExtra("latitude", 37.478896);
                intent.putExtra("longitude",126.878680);
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



