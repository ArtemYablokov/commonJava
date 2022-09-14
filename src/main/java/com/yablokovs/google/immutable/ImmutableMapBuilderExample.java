package com.yablokovs.google.immutable;

import com.google.common.collect.ImmutableMap;

public class ImmutableMapBuilderExample {

    public static void main(String[] args) {
        test();
    }

    private static void test() {

        ImmutableMap<String, Integer> WORD_TO_INT = new ImmutableMap.Builder<String, Integer>()
                .put("one", 1)
                .put("two", 2)
                .put("three", 3)
                .build();


        ImmutableMap.Builder<String, Integer> stringIntegerBuilder = new ImmutableMap.Builder<>();
        stringIntegerBuilder.put("one", 1);

        ImmutableMap<String, Integer> immutableMap = stringIntegerBuilder.build();
        immutableMap.forEach((key, value) -> System.out.println(key + " - " + value));

        // stringIntegerBuilder.put("two", 2);


        ImmutableMap<String, Integer> immutableMap2 = stringIntegerBuilder.build();
        immutableMap2.forEach((key, value) -> System.out.println(key + " - " + value));



    }


}
