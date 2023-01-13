package com.yablokovs.streamApi;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OrderOfComputations {

    public static void main(String[] args) {

        IntStream intStream = IntStream.of(3, 2, 1, 5, 7, 1, 3, 2);

        String collect = intStream
                .filter(num -> num < 5)
                .peek(System.out::println)
                .distinct()
                .limit(2) // affect PEEK
                .sorted(/*Comparator.reverseOrder()*/) // WTF ???
//                .limit(2) - will not affect PEEK because of sorted operation requires all of objects
                .map(val -> val + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(collect);

    }
}
