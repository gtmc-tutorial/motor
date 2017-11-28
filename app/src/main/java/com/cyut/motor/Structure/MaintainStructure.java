package com.cyut.motor.Structure;

/**
 * Created by b4791 on 2017/11/28.
 */

public class MaintainStructure {
    public String day;
    public String Oil_gearoil_label;
    public int Oil_gearoil_price;
    public String Oil_gearoil_trip;

    public MaintainStructure() {
        // Default constructor required for calls to DataSnapshot.getValue(MapStructure.class)
    }

    public MaintainStructure(String day, String Oil_gearoil_label, int Oil_gearoil_price,String Oil_gearoil_trip) {
        this.day = day;
        this.Oil_gearoil_label = Oil_gearoil_label;
        this.Oil_gearoil_price = Oil_gearoil_price;
        this.Oil_gearoil_trip= Oil_gearoil_trip;
    }
}
