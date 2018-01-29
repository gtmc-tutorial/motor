package com.cyut.motor.Structure;

import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snake on 2018/1/29.
 */

public class UserStructure {
    public String id;
    public String email;
    public String password;
   

    public UserStructure(String id,String email,String password) {
        this.id = id;
        this.email = email;
        this.password = password;

    }

    public UserStructure(EditText ed_id, EditText ed_email, EditText ed_password) {
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("email", email);
        result.put("password", password);
        return result;
    }
}
