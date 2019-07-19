package com.example.login;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class JHJ_MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    Retrofit retrofit;
    RemoteService rs;
    LatLng location;
    String strAddress;
    // ImageView marker;
    double latitude;
    double longitude;
    Marker marker;
    JHJ_AppointmentVO vo;
    JHJ_Test t = JHJ_Test.getTest();
    String youId=t.getId2();
    String myId=t.getId();
    EditText edt;
    int mYear, mMonth, mDay, mHour, mMinute;
    int show=0;
    Intent intent;
    String date, time;
    String strLatitude;
    String strlongitude;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = new Intent(JHJ_MapsActivity.this, JHJ_ChatRoomActivity.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jhj_maps);

        View view = (View) getLayoutInflater().
                inflate(R.layout.jhj_activity_chat_room, null);
        edt=view.findViewById(R.id.edtContent);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent=getIntent();
        latitude=Double.parseDouble(intent.getStringExtra("latitude"));
        longitude=Double.parseDouble(intent.getStringExtra("longitude"));

        //marker=findViewById(R.id.marker);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs=retrofit.create(RemoteService.class);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        LatLng sydney = new LatLng(37.478896, 126.878680);
//        mMap.addMarker(new MarkerOptions().position(sydney).title(strAddress));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));
        //setAddressText(latLng);
        mMap.setOnMapClickListener(this);
        location = new LatLng(latitude, longitude);
        addMarker(location, 16);
    }




    public void addMarker(final LatLng latLng, float zoomLevel){
        System.out.println(latLng);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title(strAddress));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(zoomLevel));
        setAddressText(latLng);

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                mMap.clear();
                mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                    @Override
                    public void onCameraIdle() {

                        marker = mMap.addMarker(new MarkerOptions().position(mMap.getCameraPosition().target).title(strAddress));
                        setAddressText(mMap.getCameraPosition().target);

                        strLatitude=Double.toString(mMap.getCameraPosition().target.latitude);
                        strlongitude=Double.toString(mMap.getCameraPosition().target.longitude);
                        System.out.println(mMap.getCameraPosition().target.latitude);
                    }
                });
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.println("눌르므르ㅡ"+strLatitude +"   "+strlongitude);
                System.out.println(strAddress);
                vo=new JHJ_AppointmentVO();
                vo.setUserid(myId);
                vo.setLatitude(strLatitude);
                vo.setLongitude(strlongitude);
                vo.setApaddress(strAddress);
                System.out.println(vo.toString());


                new DatePickerDialog(JHJ_MapsActivity.this, mDateSetListener, mYear,mMonth, mDay).show();

                intent.putExtra("read", strAddress);

//
//                     startActivity(intent);
                //  finish();
                return true;

            }
        });

    }

    public void Date(){
        new DatePickerDialog(JHJ_MapsActivity.this, mDateSetListener, mYear,mMonth, mDay).show();
    }

    public void Time(){
        new TimePickerDialog(JHJ_MapsActivity.this, mTimeSetListener, mHour,mMinute, false).show();
    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            UpdateNow();
            vo.setApdate(date);
            new TimePickerDialog(JHJ_MapsActivity.this, mTimeSetListener, mHour,mMinute, false).show();
            //텍스트뷰의 값을 업데이트함

        }
    };
    TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            UpdateNow1();
            vo.setAptime(time);
            Call<Void> insert =rs.insert(vo, youId);
            insert.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    System.out.println("response,,,,, ,,,,,,,,,,,,,,,,,,,,,"+response.body());

                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.i("실패",t.getMessage()+"");
                }
            });
            Call<UserProfileVO> callvo = rs.listProfile(myId);
            callvo.enqueue(new Callback<UserProfileVO>() {
                @Override
                public void onResponse(Call<UserProfileVO> call, Response<UserProfileVO> response) {
                    UserProfileVO vo = response.body();
                    intent.putExtra("myNickname",vo.getUsernickname());
                    intent.putExtra("userNickName",getIntent().getStringExtra("userNickName"));
                    intent.putExtra("img",getIntent().getStringExtra("img"));
                    intent.putExtra("id",myId);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<UserProfileVO> call, Throwable t) {

                }
            });
        }
    };
    void UpdateNow(){

        date=String.format("%d/%d/%d", mYear,mMonth + 1, mDay);
        intent.putExtra("date", date);
    }
    void UpdateNow1(){

        time=String.format("%d:%d", mHour, mMinute);
        intent.putExtra("time", time);
    }

    private void setAddressText(LatLng latLng){
        Geocoder geocoder=new Geocoder(this, Locale.KOREAN);
        List<Address> list=null;
        try{
            list=geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        }catch(Exception e){ }
        if(!list.isEmpty()){
            strAddress=list.get(0).getAddressLine(0).toString();
            //txtAddress.setText(strAddress);
        }
    }
    public void onMapClick(LatLng latLng) {
//        latitude=latLng.latitude;
//        longitude=latLng.longitude;
//        location = new LatLng(latitude, longitude);
//        addMarker(latLng, 16);
    }

}
