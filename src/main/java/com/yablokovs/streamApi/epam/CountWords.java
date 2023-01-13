package com.yablokovs.streamApi.epam;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CountWords {

    public static void main(String[] args) {

        String words = new String();


        List<String> collect =
                Pattern.compile("\\s+").splitAsStream(words)
                        .filter(Pattern.compile("\\w+").asPredicate())
                        .map(String::toLowerCase)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry::getKey))
                        .limit(5)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

    }
}
