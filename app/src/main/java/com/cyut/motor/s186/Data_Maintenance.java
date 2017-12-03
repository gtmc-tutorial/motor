package com.cyut.motor.s186;

/**
 * Created by wubingyu on 2017/12/3.
 */

public class Data_Maintenance {
    private String name;
    private String sex;

    public Data_Maintenance(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
}
