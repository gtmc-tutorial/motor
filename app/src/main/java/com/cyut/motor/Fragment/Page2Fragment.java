package com.cyut.motor.Fragment;

import android.support.v4.app.Fragment;

/**
 * Created by wubingyu on 2017/8/24.
 */

public class Page2Fragment extends Fragment {

//    private GoogleMap mMap;
//    float zoom;
//    private LocationManager locMgr;
//    String bestProv;
//
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.fragment_page2, container, false);
//
//        Fragment a = getChildFragmentManager().findFragmentById(R.id.fragment_view_map);
//        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.fragment_view_map);
//        mapFragment.getMapAsync(this);
//
//        // 取得定位服務
//        locMgr = (LocationManager) ((MainActivity) getContext()).getSystemService(Context.LOCATION_SERVICE);
//        // 取得最佳定位
//        Criteria criteria = new Criteria();
//        bestProv = locMgr.getBestProvider(criteria, true);
//
//        // 如果GPS或網路定位開啟，更新位置
//        if (locMgr.isProviderEnabled(LocationManager.GPS_PROVIDER) || locMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//            //  確認 ACCESS_FINE_LOCATION 權限是否授權
//            if (ActivityCompat.checkSelfPermission(((MainActivity) getContext()),
//                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                locMgr.requestLocationUpdates(bestProv, 1000, 1, this);
//            }
//        } else {
//            Toast.makeText(((MainActivity) getContext()), "請開啟定位服務", Toast.LENGTH_LONG).show();
//        }
//        return rootView;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        Log.e("onMapReady", "onMapReady");
//
//        mMap = googleMap;
//        LatLng cyut = new LatLng(24.069668, 120.714754);
//        zoom= 17;
//        mMap.addMarker(new MarkerOptions().position(cyut).title("預設位置"));
////        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cyut
////                ,zoom));
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        requestPermissions();
//        //mMap.addMarker(new MarkerOptions()
//               // .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker))
//                //.anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
//                //.position(new LatLng(24.172501, 120.662946)));
//
//
//
//        mMap = googleMap;
//        LatLng abc = new LatLng(24.115910, 120.679654);
//        zoom= 17;
//        mMap.addMarker(new MarkerOptions().position(abc).title("大買家"));
//
//        mMap = googleMap;
//        LatLng def = new LatLng(24.109159, 120.651143);
//        zoom= 17;
//        mMap.addMarker(new MarkerOptions().position(def).title("123"));
//
//
//
//        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER); // 設定定位資訊由 GPS提供
//        double lat = location.getLatitude();  // 取得經度
//        double lng = location.getLongitude(); // 取得緯度
//        LatLng HOME = new LatLng(lat, lng);
////        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HOME, 15.0f));//數字越大放越大
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(HOME
//                ,zoom));
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.e("Destory","Destory");
//        super.onDestroy();
//    }
//    public void onStart() {
//
//        super.onStart();
//    }
//    private void requestPermissions(){
//        if (Build.VERSION.SDK_INT >=23){
//            int hasPermission = ActivityCompat.checkSelfPermission(((MainActivity)getContext()), android.Manifest.permission.ACCESS_COARSE_LOCATION);
//            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(((MainActivity)getContext()),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
//                return;
//            }
//        }
//        setMyLocation();
//    }
//
//    public void onRequestPermissionsResult(int requestCode,String[] permissions,int [] grantResults){
//        if (requestCode == 1){
//            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                setMyLocation();
//            }else {
//                Toast.makeText(((MainActivity)getContext()),"未取得授權!",Toast.LENGTH_SHORT).show();
//                ((MainActivity)getContext()).finish();
//            }
//        }else {
//            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
//        }
//    }
//
//    private void setMyLocation() throws SecurityException{
//        mMap.setMyLocationEnabled(true);
//    }
//
//    //@Override
//    public void onLocationChanged(Location location) {
//        String x = "緯=" + Double.toString(location.getLatitude());
//        String y = "經=" + Double.toString(location.getLongitude());
//
//        LatLng Point = new LatLng(location.getLatitude(),location.getLongitude());
//        zoom = 17;
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Point,zoom));
//        Toast.makeText(((MainActivity)getContext()),x + "\n" + y, Toast.LENGTH_LONG).show();
//
//    }
//    //@Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        Criteria criteria = new Criteria();
//        bestProv = locMgr.getBestProvider(criteria, true);
//    }
//
//    //@Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    //@Override
//    public void onProviderDisabled(String provider) {
//
//    }
}
