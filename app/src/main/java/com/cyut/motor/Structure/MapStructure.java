package com.cyut.motor.Structure;

/**
 * Created by user on 2017/9/20.
 */

public class MapStructure {
    public String lng;
    public String lat;
    public String name;
    public String add;

    public MapStructure() {
        // Default constructor required for calls to DataSnapshot.getValue(MapStructure.class)
    }

    public MapStructure(String lng, String lat, String name,String add) {
        this.lng = lng;
        this.lat = lat;
        this.name = name;
        this.add= add;
    }

}