package food.com.example.projectchatting;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    LatLng location;
    String strAddress;
    double latitude;
    double longitude;
    ImageView marker;
    Marker marker1;
    LatLng getLocation;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent=getIntent();
        latitude=intent.getDoubleExtra("latitude", 0);
        longitude=intent.getDoubleExtra("longitude", 0);
        marker=findViewById(R.id.marker);

    }

//    @Override
//    public void onCameraMove() {
//        Marker marker = mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.7750, 122.4183))
//                .title("San Francisco")
//                .snippet("Population: 776733"));
//
//        System.out.println(strAddress);
//    }

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




    private void addMarker(final LatLng latLng, float zoomLevel){
        //mMap.clear();
         mMap.clear();
         mMap.addMarker(new MarkerOptions().position(location).title(strAddress));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
         mMap.moveCamera(CameraUpdateFactory.zoomTo(zoomLevel));
            setAddressText(latLng);

         mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                mMap.clear();
                mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                    @Override
                    public void onCameraIdle() {

                        mMap.addMarker(new MarkerOptions().position(mMap.getCameraPosition().target).title(strAddress));

                       setAddressText(mMap.getCameraPosition().target);

                    }
                });
               // setAddressText(latLng);
                System.out.println(strAddress);
                //mMap.clear();

                 //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                 //mMap.moveCamera(CameraUpdateFactory.zoomTo(16));

//               LatLng latLng1=new LatLng(latitude, longitude);
//                latitude=latLng1.latitude;
//                longitude=latLng1.longitude;
//                location = new LatLng(latitude, longitude);
               // LatLng position=mMap.getCameraPosition();
                //addMarker(mMap.getCameraPosition().target, 16);
               // setAddressText(strAddress);
            }
        });
     // getLocation=new L
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
