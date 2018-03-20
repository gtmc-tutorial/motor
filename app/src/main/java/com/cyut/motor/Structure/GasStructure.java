package com.cyut.motor.Structure;

import java.util.HashMap;
import java.util.Map;

public class GasStructure {
    public String add;
    public String lat;
    public String lng;
    public String name;

    public GasStructure() {
        // Default constructor required for calls to DataSnapshot.getValue(MaintainStructure.class)
    }

    public GasStructure(String add, String lat, String lng, String name) {
        this.add = add;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("add", add);
        result.put("lat", lat);
        result.put("lng", lng);
        result.put("name", name);
        return result;
    }
}
