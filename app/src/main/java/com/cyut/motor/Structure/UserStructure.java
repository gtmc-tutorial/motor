package com.cyut.motor.Structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snake on 2018/1/29.
 */

public class UserStructure {
    public String email;
    public String name;
    public String password;
    public String user_id;

    public UserStructure() {
        // Default constructor required for calls to DataSnapshot.getValue(MaintainStructure.class)
    }

    public UserStructure(String name,String email,String password,String user_id) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.user_id= user_id;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("name", name);
        result.put("password", password);
        result.put("user_id", user_id);
        return result;
    }
}
