package com.yablokovs.streamApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoolTaskTagSort {

    public static List<String> getHashtagsImpl(List<String> tweets) {
        return tweets
                .stream()
                .flatMap(s -> Stream.of(s.split("\\s")).filter(w -> w.startsWith("#")))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static List<String> getHashtags(List<String> twits) {
        return twits
                .stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> s.startsWith("#"))
                .map(s -> s = s.replace("#", ""))
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
