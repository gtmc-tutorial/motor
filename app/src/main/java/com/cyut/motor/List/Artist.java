package com.cyut.motor.List;

/**
 * Created by snake on 2018/3/8.
 */

public class Artist {

    String email;
    String name;
    String password;
    String user_id;

    public Artist(){

    }

    public Artist(String email,String name,String password,String user_id){
        this.email = email;
        this.name = name;
        this.password = password;
        this.user_id = user_id;

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getuser_id() {
        return user_id;
    }


}
