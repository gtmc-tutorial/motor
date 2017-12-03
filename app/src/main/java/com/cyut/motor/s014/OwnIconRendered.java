package com.cyut.motor.s014;

import android.content.Context;
import android.util.Log;

import com.cyut.motor.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by wubingyu on 2017/11/28.
 */

public class OwnIconRendered extends DefaultClusterRenderer<MyItem> {
    public OwnIconRendered(Context context, GoogleMap map,ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        Log.e("item","item");
        if(MapFragment.tpye.equals("battery")) {
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.battery_gps1));
        }
        else if(MapFragment.tpye.equals("car_dealers")){
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.motor_gps1));
        }else if(MapFragment.tpye.equals("gas")){
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.oil_gps10));
        }
        markerOptions.snippet(item.getSnippet());
        markerOptions.title(item.getTitle());
        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
