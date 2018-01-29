package com.cyut.motor.Structure;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by snake on 2018/1/29.
 */

public class UserStructure {
    public String ed_id;
    public String user_id;

    public UserStructure() {
        // Default constructor required for calls to DataSnapshot.getValue(MaintainStructure.class)
    }

    public UserStructure(String ed_id, String user_id) {
        this.ed_id = ed_id;
        this.user_id= user_id;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("day", ed_id);
        result.put("user_id", user_id);
        return result;
    }
}
