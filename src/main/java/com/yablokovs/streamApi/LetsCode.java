package com.yablokovs.streamApi;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LetsCode {

    public static void main(String[] args) throws IOException {



        // EPAM
        String chars = "aaabbbbccddd";
        char[] chars1 = chars.toCharArray();
        Stream<char[]> chars11 = Stream.of(chars1);
        chars11.distinct().forEach(System.out::println);


        List<String> list = List.of("abc", "def");
        List<String> strings = asList("abc", "def");

        list.forEach((s) -> System.out.println(s.length())); // без указания string не может вывести тип ? (похоже речь о List<Generic>)


        boolean digit = isDigit(' '); //

        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        int count2 = IntStream.of(1, 2, 3)
                .reduce(0, Integer::sum);

        Stream<String> lines = Files.lines(Paths.get(""));
        long count1 = lines.count();

        Stream<String> stringStream = lines.flatMap(s -> List.of("", s).stream());
        stringStream.forEach(System.out::println);


    }
}