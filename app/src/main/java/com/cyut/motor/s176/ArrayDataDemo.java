package com.cyut.motor.s176;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @version 0.1 king 2015-11
 */
public class ArrayDataDemo {

    private static final Map<String, Map<String, List<String>>> DATAs = new LinkedHashMap<>();

    private static void init() {
        if (!DATAs.isEmpty()) {
            return;
        }
        final String[] citys = {"基隆市", "台北市", "新北市", "桃園市", "桃園縣", "新竹市", "新竹縣", "苗栗縣", "台中市", "彰化縣", "南投縣", "雲林縣", "嘉義市", "嘉義縣", "台南市", "高雄市", "屏東縣", "台東縣", "花蓮縣", "宜蘭縣"};

        for (int i = 0; i < citys.length; i++) {
            Map<String, List<String>> city = new HashMap<>();
            for (int j = 0; j < 30; j++) {
                List<String> data = new ArrayList<>();
                for (int k = 0; k < 30; k++) {
                    data.add("zzz" + k);
                }
                city.put("xxx" + j, data);
            }
            DATAs.put(citys[i] , city);
        }
    }

    private static Random random = new Random();

    private static String getRandomText() {
        int num = random.nextInt(20);
        String str = "五";
        for (int i = 0; i < num; i++) {
            str += "五";
        }
        return str;
    }

    public static Map<String, Map<String, List<String>>> getAll() {
        init();
        return new HashMap<>(DATAs);
    }

}
