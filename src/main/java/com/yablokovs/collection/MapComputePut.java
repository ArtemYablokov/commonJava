package com.yablokovs.collection;

import java.util.HashMap;
import java.util.Map;

public class MapComputePut {


    public static void main(String[] args) {

        Map<Integer, String> integerStringMap = new HashMap<>();

        int presentKey = 1;
        int absentKey = 0;

//        integerStringMap.put(absentKey, String.valueOf(absentKey));
        integerStringMap.put(presentKey, String.valueOf(presentKey));

        // STEP 1

        // TODO: 15.12.2022 add result of Function to MAP
        String computeIfAbsent1 = integerStringMap.computeIfAbsent(0, key -> {
            System.out.println("EXECUTE computeIfAbsent1");
            return "computed value";
        });

        // STEP 2

        // TODO: 15.12.2022 modify VALUE with a result of Function
        String computeIfPresent = integerStringMap.computeIfPresent(presentKey,
                ((integer, s) -> "computed value + present = " + s));

        // STEP 3

        // TODO: 15.12.2022 it is like modify value if it exists or null
        String computed = integerStringMap.compute(absentKey,
                ((integer, s) -> s == null ? "new string added to 0 key" : s + " + modified by compute method"));

        // STEP 4

        // TODO: 15.12.2022 first call return null, second -> "first value"
        String put_if_absent = integerStringMap.putIfAbsent(2, "first value");
        String put_if_absent2 = integerStringMap.putIfAbsent(2, "second value");

        int n = 0;

    }
}
