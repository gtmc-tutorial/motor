package com.cyut.motor.Structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snake on 2018/1/29.
 */

public class UserStructure {
    public String ed_name;
    public String email;
    public String password;
    public String user_id;
   

    public UserStructure(String ed_name,String email,String password,String user_id) {
        this.ed_name = ed_name;
        this.email = email;
        this.password = password;
        this.user_id= user_id;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", ed_name);
        result.put("email", email);
        result.put("password", password);
        result.put("user_id", user_id);
        return result;
    }
}
