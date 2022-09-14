package com.yablokovs.streamApi;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {

        Integer integer = multiplyStreamEntries(Stream.of(1, 2, 3, 4));


        Optional<String> optional = Optional.of("");
        optional.ifPresent(s -> {
        });
        String s1 = optional.orElse("");
        String s2 = optional.orElseGet(() -> "");


        Stream<Integer> integerStream = List.of(1, 2).stream();
        integerStream = Stream.of(1, 2, 3, 4);

        Optional<Integer> max1 = integerStream.max(Comparator.comparing(Integer::intValue));
        Optional<Integer> max2 = integerStream.max(Integer::max);
        Optional<Integer> reduce = integerStream.reduce(Integer::sum);

        Integer reduce1 = integerStream.reduce(0, Integer::sum);

        Optional<Integer> reduce2 = integerStream.reduce((a, b) -> a * b);
        Integer reduce3 = multiplyStreamEntries(integerStream);

        Integer collect = integerStream.collect(Collectors.summingInt(Integer::intValue));


        IntStream intStream = integerStream.mapToInt(Integer::intValue); // ToIntFunction
        IntStream intStream2 = IntStream.of(1, 2); // Arrays.stream(new int[]{0, 8,}); UNDER THE HOOD

        int sum1 = intStream.sum();
        int sum2 = intStream.reduce(0, Integer::sum);// accumulator.applyAsInt(result, element);

        OptionalInt max = intStream.max();
        max.ifPresent(i -> {
        });
        int i = max.orElse(0);
        max.orElseGet(() -> 0);

        // collect = intStream.collect(() -> a, 0, (a,b) -> {});
    }

    public static Integer multiplyStreamEntries(Stream<Integer> integerStream) {
        return integerStream.reduce(1, getIntegerBinaryOperator());
    }

    private static BinaryOperator<Integer> getIntegerBinaryOperator() {
        BinaryOperator<Integer> integerBinaryOperator = (a, b) -> a * b;

        Function<Integer, String> function = Object::toString;

        BiFunction<Integer, Integer, String> integerIntegerStringBiFunction = integerBinaryOperator.andThen(function);

        return integerBinaryOperator;
    }
}
