package com.cyut.motor.List;

/**
 * Created by snake on 2018/3/8.
 */

public class Wist {

    String user_id;
    String day;
    String label;
    String price;
    String trip;
    String type;

    public Wist(){

    }

    public Wist(String user_id,String day,String label,String price,String trip,String type){
        this.user_id = user_id;
        this.day = day;
        this.label = label;
        this.price = price;
        this.trip = trip;
        this.type = type;

    }

    public String getuser_id() {
        return user_id;
    }

    public String getday() {
        return day;
    }

    public String getlabel() {
        return label;
    }

    public String getprice() {
        return price;
    }

    public String gettrip() {
        return trip;
    }

    public String gettype() {
        return type;
    }
}
