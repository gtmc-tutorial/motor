package com.cyut.motor.s014;

import android.content.Context;

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
    private String type;
    public OwnIconRendered(Context context, GoogleMap map,ClusterManager<MyItem> clusterManager,String type) {
        super(context, map, clusterManager);
        this.type = type;

    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        if(type.equals("battery")){
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder4));
        }else if(type.equals("car_dealers")){
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder4));
        }else if(type.equals("gas")){
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder4));
        }
        markerOptions.snippet(item.getSnippet());
        markerOptions.title(item.getTitle());
        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
