package com.yablokovs.streamApi;

import java.util.Comparator;
import java.util.stream.Stream;

public class ComparatorEx {

    public static void main(String[] args) {

        Stream<String> sorted = Stream.of("", " ").sorted(String::compareToIgnoreCase);
        Stream<String> sorted3 = Stream.of("", " ").sorted(Comparator.reverseOrder());

        Stream<String> sorted2 = Stream.of("", " ").sorted(Comparator.comparing(String::length));



        Stream<Apple> appleStream = Stream.of(new Apple());

        appleStream.sorted(Comparator.reverseOrder());
        appleStream.sorted(Comparator.comparing(Apple::getWeight));

    }


    private void initApple() {
        ComparatorEx.Apple apple = new Apple(); // if try to init from other class - require to be static

        apple = new Apple();
    }

    static class Apple implements Comparable{
        int weight;

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
}
