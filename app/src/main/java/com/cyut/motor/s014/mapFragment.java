package com.cyut.motor.s014;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.Structure.MapStructure;
import com.cyut.motor.R;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hipsre720 on 2017/8/17.
 */

public class mapFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    float zoom;
    private LocationManager locMgr;
    String bestProv;
    private ArrayList<MapStructure> mapList = new ArrayList<>();

    String elevation = "";
    final Marker[] finalMarker1 = {null};
    public static LatLng latLng2;

    private static final int MESSAGE_CHECKED = 0;

    @Nullable
    private Button movie_btn2,tv_btn2,anime_btn2;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page3, container, false);
        findById(view);
        Fragment a = getChildFragmentManager().findFragmentById(R.id.fragment_view_map);
        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.fragment_view_map);
        mapFragment.getMapAsync(this);

        // 取得定位服務
        locMgr = (LocationManager) ((MainActivity) getContext()).getSystemService(Context.LOCATION_SERVICE);
        // 取得最佳定位
        Criteria criteria = new Criteria();
        bestProv = locMgr.getBestProvider(criteria, true);

        // 如果GPS或網路定位開啟，更新位置
        if (locMgr.isProviderEnabled(LocationManager.GPS_PROVIDER) || locMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //  確認 ACCESS_FINE_LOCATION 權限是否授權
            if (ActivityCompat.checkSelfPermission(((MainActivity) getContext()),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locMgr.requestLocationUpdates(bestProv, 1000, 1, this);
            }
        } else {
            Toast.makeText(((MainActivity) getContext()), "請開啟定位服務", Toast.LENGTH_LONG).show();
        }

        readFireBaseData();
        return view;
    }
    private void findById(View view) {
        movie_btn2 =(Button)view.findViewById(R.id.movie_btn2);
        tv_btn2=(Button)view.findViewById(R.id.tv_btn2);
        anime_btn2=(Button)view.findViewById(R.id.anime_btn2);
    }


    private void readFireBaseData(){
        Firebase.setAndroidContext(getActivity());


        Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/place/battery");
        Query queryRef = myFirebaseRef.orderByChild("lat");

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                MapStructure mapStructure = snapshot.getValue(MapStructure.class);
                Log.e("FireBaseTraining", "lat = " + mapStructure.lat +"lng = " + mapStructure.lng+ " , Name = " + mapStructure.name+" , add = " + mapStructure.add);
                LatLng latLng = new LatLng(Double.parseDouble(mapStructure.lat), Double.parseDouble(mapStructure.lng));

//                getJSONContent("https://maps.googleapis.com/maps/api/elevation/json?locations="+Double.parseDouble(mapStructure.lat)+","+Double.parseDouble(mapStructure.lng)+"&key=AIzaSyALcD0X57AVTQJq8GoqK3-62Hia5TpoF2I");

                final String[] elevation = {""};

                Ion.with(getActivity())
                        .load("https://maps.googleapis.com/maps/api/elevation/json?locations="+Double.parseDouble(mapStructure.lat)+","+Double.parseDouble(mapStructure.lng)+"&key=AIzaSyALcD0X57AVTQJq8GoqK3-62Hia5TpoF2II")
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                elevation[0] = result.toString().substring(result.toString().indexOf(":",2),result.toString().indexOf(","));
                                Log.e("result",elevation[0]);
                            }
                        });

                mMap.addMarker(new MarkerOptions().position(latLng).title(mapStructure.name));

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    final Marker[] finalMarker = {null};

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e("onMapReady", "onMapReady");
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        requestPermissions();

        //mMap.addMarker(new MarkerOptions()
        // .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker))
        //.anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
        //.position(new LatLng(24.172501, 120.662946)));

        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER); // 設定定位資訊由 GPS提供
        double lat = 0,lng = 0;
        if(location != null){
            lat = location.getLatitude();  // 取得經度
            lng = location.getLongitude(); // 取得緯度
        }
        LatLng HOME = new LatLng(lat, lng);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HOME, 15.0f));//數字越大放越大
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(HOME,zoom));

        final String[] eleva = {""};
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {

                mapFragment.latLng2 = latLng;
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        String result = getJSONContent("https://maps.googleapis.com/maps/api/elevation/json?locations="+Double.parseDouble(String.valueOf(latLng.latitude))+","+Double.parseDouble(String.valueOf(latLng.longitude))+"&key=AIzaSyALcD0X57AVTQJq8GoqK3-62Hia5TpoF2I");

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray array = jsonObject.getJSONArray("results");
                            Log.e("array",array+"");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObjectelevation = array.getJSONObject(i);
                                elevation = jsonObjectelevation.getString("elevation");
                                Log.e("elevation",elevation+"");
                            }

                            Message message = mHandler.obtainMessage(MESSAGE_CHECKED);

                            Bundle bundle = new Bundle();
                            bundle.putString("elevation", elevation);
                            message.setData(bundle);
                            message.sendToTarget();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });
    }
    @Override
    public void onDestroy() {
        Log.e("Destory","Destory");
        super.onDestroy();
    }
    public void onStart() {

        super.onStart();
    }
    private void requestPermissions(){
        if (Build.VERSION.SDK_INT >=23){
            int hasPermission = ActivityCompat.checkSelfPermission(((MainActivity)getContext()), android.Manifest.permission.ACCESS_COARSE_LOCATION);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(((MainActivity)getContext()),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
                return;
            }
        }
        setMyLocation();
    }
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int [] grantResults){
        if (requestCode == 1){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                setMyLocation();
            }else {
                Toast.makeText(((MainActivity)getContext()),"未取得授權!",Toast.LENGTH_SHORT).show();
                ((MainActivity)getContext()).finish();
            }
        }else {
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }

    private void setMyLocation() throws SecurityException{
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationChanged(Location location) {
        String x = "緯=" + Double.toString(location.getLatitude());
        String y = "經=" + Double.toString(location.getLongitude());

        LatLng Point = new LatLng(location.getLatitude(),location.getLongitude());
        zoom = 17;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Point,zoom));
        Toast.makeText(((MainActivity)getContext()),x + "\n" + y,Toast.LENGTH_LONG).show();

    }
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Criteria criteria = new Criteria();
        bestProv = locMgr.getBestProvider(criteria, true);
    }
    //@Override
    public void onProviderEnabled(String provider) {

    }

    //@Override
    public void onProviderDisabled(String provider) {

    }


    public synchronized String getJSONContent(String apiUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();
//            Log.v("GTService", "getJSONContent Response Message :" + apiUrl.substring(0, apiUrl.indexOf("?")));
            Log.v("GTService", "getJSONContent Response Message :" + response.message());
            response.close();
            return jsonString;
        } catch (IOException e) {
            Log.e("e",e+"");
            e.printStackTrace();
            return null;
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            switch (msg.what) {
                case MESSAGE_CHECKED:
                    if(finalMarker1[0] != null)
                        finalMarker1[0].remove();
                    finalMarker1[0] =  mMap.addMarker(new MarkerOptions().position(latLng2).title(bundle.getString("elevation")));
                    finalMarker1[0].showInfoWindow();
            }
        }
    };
    
}




